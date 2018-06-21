package com.tkk.androidsummary.knowledgepoint.frame.permissionsdispatcher_AndAndroidM_File;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import java.io.File;

import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "运行时权限和7.0文件")
@BindLayout(R.layout.activity_permission_and_file)
public class PermissionAndFileActivity extends BaseActivity {
    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    private static final int REQ_ZOOM_PHOTO = 2;
    private String imgPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + String.valueOf(System.currentTimeMillis()) + ".jpg";
    private File takePhotoFile;
    private boolean mShouldCrop = true;

    @Override
    protected void initView() {
    }

    @OnClick(R.id.bt_chose)
    void chosePic() {
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
                    PermissionAndFileActivityPermissionsDispatcher.openCameraWithPermissionCheck(this);
                    break;
            }
        });
        builder.create().show();
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void openCamera() {
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
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_PICTURE:
                    Uri selectedImage = data.getData();
                    Log.d(TAG, ">>>onActivityResult---选择" + data.getData());
                    if (mShouldCrop){
                        zoomPhoto(selectedImage);
                    }
                    break;
                case TAKE_PICTURE:
                    Log.d(TAG, ">>>onActivityResult---拍照" + takePhotoFile.getPath());
                    if (mShouldCrop) {
                        zoomPhoto(new File(imgPath));
                    }
                    break;
                case REQ_ZOOM_PHOTO:
                    Log.d(TAG, ">>>onActivityResult---裁剪" + data.getData());
                    break;
            }
        } else {
            if (requestCode == CHOOSE_PICTURE || requestCode == TAKE_PICTURE) {
                Toast.makeText(PermissionAndFileActivity.this, "已取消", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionAndFileActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showMessageForDial(final PermissionRequest request) {
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void noCameraPermission() {
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void dontAsk() {
    }

    /**
     * 裁剪照片
     *
     * @param inputFile
     */
    private void zoomPhoto(File inputFile) {
        Uri inputUri;
        if (Build.VERSION.SDK_INT < 24) {
            // 从文件中创建uri
            inputUri = Uri.fromFile(inputFile);
        } else {
            inputUri = FileProvider.getUriForFile(getApplicationContext(), "com.tkk.androidsummary", inputFile);
        }
        zoomPhoto(inputUri);
    }
    private void zoomPhoto(Uri inputUri) {
        File outputFile = new File(generateImgePath());
        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(inputUri, "image/*");
//设置剪裁图片宽高比
        intent.putExtra("mAspectX", 1);
        intent.putExtra("mAspectY", 1);

        //设置剪裁图片大小
        intent.putExtra("mOutputX", 500);
        intent.putExtra("mOutputY", 500);

        // 是否返回uri
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, REQ_ZOOM_PHOTO);
    }

    /**
     * 获取SD下的应用目录
     */
    private String getExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        String ROOT_DIR = "Android/data/" + getPackageName();
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        return sb.toString();
    }

    /**
     * 产生图片的路径，带文件夹和文件名，文件名为当前毫秒数
     */
    private String generateImgePath() {
        return getExternalStoragePath() + File.separator + String.valueOf(System.currentTimeMillis()) + ".jpeg";
    }
}
