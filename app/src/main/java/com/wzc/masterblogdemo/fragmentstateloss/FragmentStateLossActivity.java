package com.wzc.masterblogdemo.fragmentstateloss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.wzc.masterblogdemo.BaseSingleFragmentActivity;

/**
 * http://toughcoder.net/blog/2016/11/28/fear-android-fragment-state-loss-no-more/
 * https://stackoverflow.com/questions/16265733/failure-delivering-result-onactivityforresult
 * https://stackoverflow.com/questions/7992496/how-to-handle-asynctask-onpostexecute-when-paused-to-avoid-illegalstateexception
 * https://stackoverflow.com/questions/8040280/how-to-handle-handler-messages-when-activity-fragment-is-paused
 * @author wzc
 * @date 2018/9/3
 */
public class FragmentStateLossActivity extends BaseSingleFragmentActivity {
    public static final String TAG = "MyTag";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "FragmentStateLossActivity onCreate: ");
        super.onCreate(savedInstanceState);
//        new MyAsyncTask().execute();
    }

    @Override
    protected Fragment createFragment() {
        return FragmentStateLossFragment.newInstance();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "FragmentStateLossActivity onStart: ");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "FragmentStateLossActivity onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "FragmentStateLossActivity onDestroy: ");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "FragmentStateLossActivity onSaveInstanceState: ");
//        getSupportFragmentManager().beginTransaction().commit();
        super.onSaveInstanceState(outState);
//        getSupportFragmentManager().beginTransaction().commit();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "FragmentStateLossActivity onPause: ");
        super.onPause();
    }

    @Override
    protected void onResume() {
//        getSupportFragmentManager().beginTransaction().commit();
        Log.d(TAG, "FragmentStateLossActivity onResume: ");
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "FragmentStateLossActivity onPostResume: ");
    }

    @Override
    protected void onResumeFragments() {
        Log.d(TAG, "FragmentStateLossActivity onResumeFragments: ");
        super.onResumeFragments();
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            SystemClock.sleep(5000);
            getSupportFragmentManager().beginTransaction().commit();
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "FragmentStateLossActivity onActivityResult: ");
    }
}
