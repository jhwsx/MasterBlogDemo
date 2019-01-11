package com.wzc.masterblogdemo.apk;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wzc.masterblogdemo.R;

import java.io.File;

/**
 * @author wzc
 * @date 2018/11/2
 */
public class ApkActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk);
        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getApkInfo();
                    }
                }).start();
            }
        });
    }

    private void getApkInfo() {
        Uri uri = MediaStore.Files.getContentUri("external");
        String selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.apk'" + ") and " + MediaStore.Files.FileColumns.SIZE + " >1 ";
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID));
                    String data = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
                    long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE));
                    String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME));
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.TITLE));
                    long dateAdded = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_ADDED));
                    long dateModified = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED));
                    String mimeType = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE));
                    Log.d("ApkActivity", "id=" + id + ", data=" + data + ", size=" + size + ", displayName="
                            + displayName + ", title=" + title + ", dateAdded=" + dateAdded + ", dateModified=" + dateModified
                            + ", mimeType=" + mimeType);

                    PackageInfo packageInfo = getPackageManager().getPackageArchiveInfo(data, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
                    if (packageInfo != null) {
                        String packageName = packageInfo.packageName;
                        int versionCode = packageInfo.versionCode;
                        String versionName = packageInfo.versionName;
                        // String label = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                        String label = PackageUtil.getAppSnippet(ApkActivity.this, packageInfo.applicationInfo, new File(data)).label.toString();
                        Log.d("ApkActivity", "packageName=" + packageName + ", versionCode=" + versionCode + ", versionName=" + versionName + ", labe=" + label);
                    } else {
                        Log.d("ApkActivity", "apk broken");
                    }

                    cursor.moveToNext();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cursor.close();
            }
        }
    }
}
