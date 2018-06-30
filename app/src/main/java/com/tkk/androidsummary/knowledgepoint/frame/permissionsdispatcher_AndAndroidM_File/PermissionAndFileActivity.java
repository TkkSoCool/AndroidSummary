package com.tkk.androidsummary.knowledgepoint.frame.permissionsdispatcher_AndAndroidM_File;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.knowledgepoint.frame.Luban.LubanActivity;
import com.tkk.androidsummary.knowledgepoint.frame.Luban.PhotoCompressUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "运行时权限和7.0文件")
@BindLayout(R.layout.activity_permission_and_file)
public class PermissionAndFileActivity extends BaseActivity {
    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    private static final int REQ_ZOOM_PHOTO = 2;
    @BindView(R.id.tv_path)
    TextView tvPath;
    @BindView(R.id.imageView)
    ImageView imageView;
    private String imgPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + String.valueOf(System.currentTimeMillis()) + ".jpg";
    private File takePhotoFile;
    private boolean mShouldCrop = false;
    private TakePictureManager takePictureManager;
    PhotoCompressUtils photoCompressUtils;

    @Override
    protected void initView() {
        photoCompressUtils = new PhotoCompressUtils();
    }

    @OnClick(R.id.bt_chose)
    void chosePic() {
        takePictureManager = TakePictureManager.Builder.from(this).isCompressor(false).isTailor(false).creat();
        PermissionAndFileActivityPermissionsDispatcher.openCameraWithPermissionCheck(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null)
                .setItems(items, (DialogInterface dialog, int which) -> {
            switch (which) {
                case 0: // 选择本地照片
                    takePictureManager.getByAlbum(new TakePictureManager.TakePictureListener() {
                        @Override
                        public void successful(File outFile) {
                            tvPath.setText(outFile.getPath());
                            Glide.with(PermissionAndFileActivity.this).load(outFile).into(imageView);
                        }

                        @Override
                        public void failed(int errorCode, List<String> deniedPermissions) {

                        }

                    });
                    break;
                case 1: // 拍照
                    takePictureManager.getByCarema(new TakePictureManager.TakePictureListener() {
                        @Override
                        public void successful(File outFile) {
                            tvPath.setText(outFile.getPath());
                            Glide.with(PermissionAndFileActivity.this).load(outFile).into(imageView);
                            Log.d(TAG, ">>>压缩前---" + outFile.length());

                            Observable.just(outFile.getPath())
                                    .cast(String.class)
                                    .observeOn(Schedulers.io())
                                    .map(path -> {
                                        Log.d(TAG, ">>>onCreate---" + Thread.currentThread().getName());
                                        return photoCompressUtils.compress(PermissionAndFileActivity.this,path);
                                    })
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<String>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(String s) {
                                            Log.d(TAG, ">>>压缩后---" + new File(s).length());
                                            Log.d(TAG, ">>>onNext---" + Thread.currentThread().getName());
                                            Log.d(TAG, ">>>onNext---" + s);

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        }



                        @Override
                        public void failed(int errorCode, List<String> deniedPermissions) {

                        }

                    });
                    break;
            }
        });
        builder.create().show();
    }


    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    void openCamera() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, (dialog, which) -> {
            switch (which) {
                case 0: // 选择本地照片
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, CHOOSE_PICTURE);
                    break;
                case 1: // 拍照

                    takePhotoFile = new File(imgPath);
                    if (!takePhotoFile.getParentFile().exists()) {
                        takePhotoFile.getParentFile().mkdirs();
                    }
                    Uri imgUri = null;
                    if (Build.VERSION.SDK_INT < 24) {
                        // 从文件中创建uri
                        imgUri = Uri.fromFile(takePhotoFile);
                    } else {
                        imgUri = FileProvider.getUriForFile(getApplicationContext(), "com.tkk.androidsummary", takePhotoFile);
                    }
                    Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent1.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                    startActivityForResult(intent1, TAKE_PICTURE);
                    break;
            }
        });
        builder.create().show();

    }

    //把本地的onActivityResult()方法回调绑定到对象
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePictureManager.attachToActivityForResult(requestCode, resultCode, data);
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        takePictureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
