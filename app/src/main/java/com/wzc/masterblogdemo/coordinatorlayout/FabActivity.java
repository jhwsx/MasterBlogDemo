package com.wzc.masterblogdemo.coordinatorlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/10/18
 */
public class FabActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, FabActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Click fab", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .show();
            }
        });
    }
}
