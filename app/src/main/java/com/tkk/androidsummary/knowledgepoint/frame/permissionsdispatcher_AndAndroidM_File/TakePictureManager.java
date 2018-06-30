package com.tkk.androidsummary.knowledgepoint.frame.permissionsdispatcher_AndAndroidM_File;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created  on 2018/6/22 0022
 *
 * @author 唐开阔
 * @describe
 */
public class TakePictureManager {

    private Activity mActivity;
    private Fragment mFragment;
    private Activity tempActivity;
    //拿到未裁剪相片的回调码（拍照后）
    private static final int CODE_ORIGINAL_PHOTO_CAMERA = 101;
    //拿到未裁剪相片的回调码（选择本地图库后）
    private static final int CODE_ORIGINAL_PHOTO_ALBUM = 102;
    //拿到已裁剪相片的回调码
    private static final int CODE_TAILOR_PHOTO = 103;
    //是否在activity中使用
    private boolean isActicity;
    //上下文
    private Context mContext;
    //FileProvider的主机名：一般是包名+".fileprovider"
    private String FILE_PROVIDER_AUTHORITY;
    //调用相机拍照的文件地址
    private String takePhotoPath;
    //裁剪后的图片Uri
    private Uri curUri;
    private TakePictureListener takeCallBacklistener;
    private PermissionListener permissionListener;
    private Builder builder;

    private TakePictureManager(Builder builder) {
        this.builder = builder;
        if (builder.mActivity != null) {
            this.mActivity = builder.mActivity;
            tempActivity = mActivity;
            isActicity = true;
            mContext = mActivity;
        }
        if (builder.mFragment != null) {
            this.mFragment = builder.mFragment;
            isActicity = false;
            mContext = mFragment.getActivity();
            mActivity = mFragment.getActivity();
            tempActivity = mFragment.getActivity();
        }
        FILE_PROVIDER_AUTHORITY = mContext.getPackageName() + ".fileprovider";

    }


    //开始拍照
    public void getByCarema(@NonNull TakePictureListener listener) {
        takeCallBacklistener = listener;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果是6.0或6.0以上，则要申请运行时权限，这里需要申请拍照和写入SD卡的权限
            requestRuntimePermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}
                    , new PermissionListener() {
                        @Override
                        public void onGranted() {
                            startOpencamera();
                        }

                        @Override
                        public void onDenied(List<String> deniedPermissions) {
                            if (takeCallBacklistener != null) {
                                takeCallBacklistener.failed(1, deniedPermissions);
                            }
                        }
                    });
            return;
        }
        startOpencamera();
    }

    /**
     * 本地选择图片
     */
    public void getByAlbum(@NonNull TakePictureListener listener) {
        takeCallBacklistener = listener;
        takePhotoPath = FileUtils.generateImgePath(mContext);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果是6.0或6.0以上，则要申请运行时权限，这里需要申请拍照和写入SD卡的权限
            requestRuntimePermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionListener() {
                @Override
                public void onGranted() {
                    startAlbum();
                }

                @Override
                public void onDenied(List<String> deniedPermissions) {
                    takeCallBacklistener.failed(1, deniedPermissions);
                }
            });
            return;
        }
        startAlbum();
    }

    private void startAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (isActicity) {
            mActivity.startActivityForResult(intent, CODE_ORIGINAL_PHOTO_ALBUM);
        } else {
            mFragment.startActivityForResult(intent, CODE_ORIGINAL_PHOTO_ALBUM);
        }
    }

    /**
     * 裁剪方法
     *
     * @param srcFile
     */
    private void statZoom(File srcFile) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(FileUtils.getImageContentUri(mContext, srcFile), "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);// 去黑边

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", builder.aspectX);
        intent.putExtra("aspectY", builder.aspectY);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", builder.outputX);
        intent.putExtra("outputY", builder.outputY);
        intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
        intent.putExtra("scaleUpIfNeeded", true);//黑边
        intent.putExtra(MediaStore.EXTRA_OUTPUT, curUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        if (isActicity) {
            mActivity.startActivityForResult(intent, CODE_TAILOR_PHOTO);
        } else {
            mFragment.startActivityForResult(intent, CODE_TAILOR_PHOTO);
        }

    }


    /**
     * 处理拍照或者选择的图片文件
     * 裁剪-压缩
     */
    private void handlePhoto(File file) {
        if (builder.isTailor) {
            File tailorOutPutFile = new File(FileUtils.generateImgePath(mContext));
            curUri = Uri.fromFile(tailorOutPutFile);
            statZoom(file);
        } else if (builder.isCompressor) {
            File temFile = FileUtils.outputIamge(mContext, FileUtils.compressImage(FileUtils.decodeUriAsBitmap(mContext, Uri.fromFile(file)), 100));
            takeCallBacklistener.successful(temFile);
        } else {
            takeCallBacklistener.successful(file);
        }
    }


    //打开相机
    private void startOpencamera() {
        takePhotoPath = FileUtils.generateImgePath(mContext);
        File imgFile = new File(takePhotoPath);
        Uri imgUri = null;

        //判断当前手机版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imgUri = FileProvider.getUriForFile(mContext, FILE_PROVIDER_AUTHORITY, imgFile);
        } else {
            imgUri = Uri.fromFile(imgFile);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);

        //判断当前在哪启动
        if (isActicity) {
            mActivity.startActivityForResult(intent, CODE_ORIGINAL_PHOTO_CAMERA);
        } else {
            mFragment.startActivityForResult(intent, CODE_ORIGINAL_PHOTO_CAMERA);
        }
    }

    //权限回调
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    //被拒绝权限
                    if (deniedPermissions.isEmpty()) {
                        permissionListener.onGranted();
                    } else {
                        permissionListener.onDenied(deniedPermissions);
                    }
                }
                break;
        }

    }

    /**
     * 获取到的相片回调方法，
     * 必须要在当前的Activity或Fragment中的onActivityResult下调用！
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void attachToActivityForResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            //拍照后得到的图片
            case CODE_ORIGINAL_PHOTO_CAMERA:
                handlePhoto(new File(takePhotoPath));
                break;
            //选择相册后得到的图片
            case CODE_ORIGINAL_PHOTO_ALBUM:
                if (data != null) {
                    Uri sourceUri = data.getData();
                    String pickPath = FileUtils.getPathByUri(mContext, sourceUri);
                    handlePhoto(new File(pickPath));
                } else {
                    takeCallBacklistener.failed(0, null);
                }
                break;

            //裁剪后的图片：
            case CODE_TAILOR_PHOTO:
                if (data != null) {
                    if (curUri != null) {
                        //裁剪后再压缩
                        File temFile = new File(FileUtils.getPathByUri(mContext,curUri));
                        if (builder.isCompressor) {
                            temFile = FileUtils.outputIamge(mContext, FileUtils.compressImage(FileUtils.decodeUriAsBitmap(mContext, Uri.fromFile(temFile)), 100));
                        }
                        takeCallBacklistener.successful(temFile);


                    }
                } else {
                    takeCallBacklistener.failed(0, null);
                }
                break;
        }
    }


    /**
     * 申请运行时权限
     */
    private void requestRuntimePermission(String[] permissions, PermissionListener listener) {
        permissionListener = listener;
        List<String> permissionList = Arrays.asList(permissions);
        //此处兼容了无法在fragment回调监听事件
        if (!permissionList.isEmpty()) {
            if (isActicity) {
                ActivityCompat.requestPermissions((Activity) mContext, permissionList.toArray(new String[permissionList.size()]), 1);
            } else {
                mFragment.requestPermissions(permissionList.toArray(new String[permissionList.size()]), 1);
            }
        } else {
            permissionListener.onGranted();
        }
    }


    public interface TakePictureListener {
        /**
         * 成功回调
         *
         * @param outFile
         */
        void successful(File outFile);

        /**
         * 失败回调
         */
        void failed(int errorCode, List<String> deniedPermissions);

    }


    private interface PermissionListener {
        void onGranted();

        void onDenied(List<String> deniedPermissions);
    }

    public static class Builder {
        private Activity mActivity;
        private Fragment mFragment;
        private boolean isTailor = true;
        //裁剪宽高的比例,默认是是 1 ：1
        private int aspectX = 1;
        private int aspectY = 1;
        //裁剪图片的宽高,默认是是 1 ：1
        private int outputX = 350;
        private int outputY = 350;
        //是否压缩图片 默认开启压缩图片的
        private boolean isCompressor = true;

        public static Builder from(Activity mActivity) {
            return new Builder(mActivity);
        }

        public static Builder from(Fragment mFragmentActivity) {
            return new Builder(mFragmentActivity);
        }

        private Builder(Activity mActivity) {
            this.mActivity = mActivity;
        }

        private Builder(Fragment mFragment) {
            this.mFragment = mFragment;
        }

        public Builder setAspectXy(int aspectX, int aspectY) {
            this.aspectX = aspectX;
            this.aspectY = aspectY;
            return this;
        }

        public Builder setOutputXy(int outputX, int outputY) {
            this.outputX = outputX;
            this.outputY = outputY;
            return this;
        }

        public Builder isTailor(boolean tailor) {
            isTailor = tailor;
            return this;
        }

        public Builder isCompressor(boolean compressor) {
            isCompressor = compressor;
            return this;
        }

        public TakePictureManager creat() {
            return new TakePictureManager(this);
        }
    }

}
