package com.tkk.androidsummary.knowledgepoint.frame.Luban;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import top.zibin.luban.InputStreamProvider;

/**
 * Created  on 2018/6/25 0025
 *
 * @author 唐开阔
 * @describe 图片压缩工具类
 */
public class PhotoCompressUtils {
    private static final String DEFAULT_DISK_CACHE_DIR = "photo_cache";
    private String mTargetDir;//文件夹
    private boolean focusAlpha = false;//是否保持alpha

    /**
     * 压缩图片
     * @param context
     * @param path 文件路径
     * @return 压缩后的文件
     * @throws IOException
     */
    public String compress(Context context, String path) throws IOException {
        InputStreamProvider inputStreamProvider = new InputStreamProvider() {
            @Override
            public InputStream open() throws IOException {
                return new FileInputStream(path);
            }
            @Override
            public String getPath() {
                return path;
            }
        };
        File result = null;
        File outFile = getImageCacheFile(context, Checker.SINGLE.extSuffix(inputStreamProvider));
        result = new Engine(inputStreamProvider, outFile, focusAlpha).compress();
        return result.getPath();
    }

    /**
     * 获取图片缓存文件
     * @param context
     * @param suffix  图片后缀格式
     * @return
     */
    private File getImageCacheFile(Context context, String suffix) {
        if (TextUtils.isEmpty(mTargetDir)) {
            mTargetDir = getImageCacheDir(context, DEFAULT_DISK_CACHE_DIR).getAbsolutePath();
        }
        String cacheBuilder = mTargetDir + "/" +
                System.currentTimeMillis() +
                (int) (Math.random() * 1000) +
                (TextUtils.isEmpty(suffix) ? ".jpg" : suffix);
        return new File(cacheBuilder);
    }

    /**
     * 获取缓存图片的文件夹
     *
     * @param context
     * @param cacheName
     * @return
     */
    private static File getImageCacheDir(Context context, String cacheName) {
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir != null) {
            File result = new File(cacheDir, cacheName);
            if (!result.mkdirs() && (!result.exists() || !result.isDirectory())) {
                return null;
            }
            return result;
        }
        return null;
    }
}
