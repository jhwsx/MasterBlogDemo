/*
 **
 ** Copyright 2007, The Android Open Source Project
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 **
 **     http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 */

package com.wzc.masterblogdemo.apk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This is a utility class for defining some utility methods and constants
 * used in the package installer application.
 */
public class PackageUtil {

    public static class AppSnippet {
        public CharSequence label;
        public Drawable icon;

        public AppSnippet(CharSequence label, Drawable icon) {
            this.label = label;
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "AppSnippet{" +
                    "label=" + label +
                    ", icon=" + icon +
                    '}';
        }
    }

    public static AppSnippet getAppSnippet(
            Context pContext, ApplicationInfo appInfo, File sourceFile) {
        final String archiveFilePath = sourceFile.getAbsolutePath();
        Resources pRes = pContext.getResources();
        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, archiveFilePath);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Resources res = new Resources(assetManager, pRes.getDisplayMetrics(), pRes.getConfiguration());
        CharSequence label = null;
        // Try to load the label from the package's resources. If an app has not explicitly
        // specified any label, just use the package name.
        if (appInfo.labelRes != 0) {
            try {
                label = res.getText(appInfo.labelRes);
            } catch (Resources.NotFoundException e) {
            }
        }
        if (label == null) {
            label = (appInfo.nonLocalizedLabel != null) ?
                    appInfo.nonLocalizedLabel : appInfo.packageName;
        }
        Drawable icon = null;
        // Try to load the icon from the package's resources. If an app has not explicitly
        // specified any resource, just use the default icon for now.
        if (appInfo.icon != 0) {
            try {
                icon = res.getDrawable(appInfo.icon);
            } catch (Resources.NotFoundException e) {
            }
        }
        if (icon == null) {
            icon = pContext.getPackageManager().getDefaultActivityIcon();
        }
        return new AppSnippet(label, icon);
    }
}
