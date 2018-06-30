package com.wzc.masterblogdemo.dialogfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wzc.masterblogdemo.BaseSingleFragmentActivity;

/**
 * https://android-developers.googleblog.com/2012/05/using-dialogfragments.html
 * @author wzc
 * @date 2018/6/24
 */
public class MyActivity extends BaseSingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        showEditDialog();
        return MyFragment.newInstance();
    }


    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        EditNameDialog editNameDialog = new EditNameDialog();
        editNameDialog.show(fm, "fragment_edit_name");
    }

}
