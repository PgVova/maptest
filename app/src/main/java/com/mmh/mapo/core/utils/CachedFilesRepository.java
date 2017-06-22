package com.mmh.mapo.core.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;


import com.mmh.mapo.R;

import java.io.File;

/**
 * Created by vladimir on 29.07.16.
 */
public class CachedFilesRepository {
    public final String ROOT_DIR;
    public static final String AUDIO_CACHE_DIR = "audios";
    public static final String IMAGE_CACHE_DIR = "images";
    public static final String FILES_CACHE_DIR = "files";

    private static CachedFilesRepository ourInstance;

    private File mStorageDir;
    private File mRootCacheDir;
    private File mAudioCacheDir;
    private File mImageCacheDir;
    private File mFileCacheDir;

    private CachedFilesRepository(Context context) {
        ROOT_DIR = "."+context.getString(R.string.app_name);
    }

    public static synchronized CachedFilesRepository getInstance(Context context, boolean privateCacheDirs) {
        if (ourInstance == null)
            ourInstance = new CachedFilesRepository(context);

        initTruePathToStorage(privateCacheDirs, context);
        ourInstance.createCacheDirsIfNeed();
        return ourInstance;
    }

    public File getAudioCacheDir() {
        return mAudioCacheDir;
    }

    public File getFileCacheDir() {
        return mFileCacheDir;
    }

    public File getImageCacheDir() {
        return mImageCacheDir;
    }

    public File createAudioFile(String fileName){
        return new File(mAudioCacheDir, fileName+".mp3");
    }

    public File createImageFile(String fileName){
        return new File(mImageCacheDir, fileName+".png");
    }

    public File createSimpleFile(String fileName){
        return new File(mFileCacheDir, fileName+".file");
    }

    public File getMagazinePagePath(String magazineId, String pageNumber){
        return createImageFile(magazineId+"_"+pageNumber);
    }

    public File createTempImage(){
        return new File(mImageCacheDir,"temp_image"+".png");
    }

    public void clearAllCache(){
        File[] files = mAudioCacheDir.listFiles();
        if (files != null){
            for (File file : files){
                file.delete();
            }
        }

        files = mFileCacheDir.listFiles();
        if (files != null){
            for (File file : files){
                file.delete();
            }
        }

        files = mImageCacheDir.listFiles();
        if (files != null){
            for (File file : files){
                file.delete();
            }
        }
    }

    private void createCacheDirsIfNeed(){
        try {
            if (!mStorageDir.exists()){
                mStorageDir.mkdir();
            }

            mRootCacheDir = new File(mStorageDir, ROOT_DIR);
            if (!mRootCacheDir.exists())
                mRootCacheDir.mkdir();

            mAudioCacheDir = new File(mRootCacheDir, AUDIO_CACHE_DIR);
            if (!mAudioCacheDir.exists())
                mAudioCacheDir.mkdir();


            mFileCacheDir = new File(mRootCacheDir, FILES_CACHE_DIR);
            if (!mFileCacheDir.exists())
                mFileCacheDir.mkdir();

            mImageCacheDir = new File(mRootCacheDir, IMAGE_CACHE_DIR);
            if (!mImageCacheDir.exists()) {
                mImageCacheDir.mkdir();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
                return true;
        }
        return false;
    }

    private static void initTruePathToStorage(boolean privateCacheDirs, Context context){
        if (Environment.isExternalStorageEmulated() && isExternalStorageWritable()){
            if (!privateCacheDirs){
                if (Build.VERSION.SDK_INT >= 19){
                    File[] files = context.getExternalCacheDirs();
                    if (files.length == 1){
                        ourInstance.mStorageDir = files[0];
                    } else if (files.length > 1){
                        File fileC = files[0];
                        for (File file : files){
                            if (file != null && !file.getPath().contains("/storage/emulated")){
                                fileC = file;
                                break;
                            }
                        }
                        ourInstance.mStorageDir = fileC;
                    } else {
                        ourInstance.mStorageDir = Environment.getExternalStorageDirectory();
                    }
                } else {
                    ourInstance.mStorageDir = Environment.getExternalStorageDirectory();
                }
            } else {
                ourInstance.mStorageDir = context.getExternalCacheDir();
            }
        } else {
            ourInstance.mStorageDir = context.getCacheDir();
        }
    }
}
