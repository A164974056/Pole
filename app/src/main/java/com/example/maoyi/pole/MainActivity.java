package com.example.maoyi.pole;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;


    private homeFragment fragment1;
    private hotFragment fragment2;
    private personalFragment fragment3;
    private Fragment[] fragments;
    private int lastShowFragment = 0;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastShowFragment != 0) {
                        switchFrament(lastShowFragment, 0);
                        lastShowFragment = 0;
                    }
                    return true;

                case R.id.navigation_dashboard:
                    if (lastShowFragment != 1) {
                        switchFrament(lastShowFragment, 1);
                        lastShowFragment = 1;
                    }
                    return true;

                case R.id.navigation_notifications:
                    if (lastShowFragment != 2) {
                        switchFrament(lastShowFragment, 2);
                        lastShowFragment = 2;
                    }
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragments();
    }

    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.content, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    private void initFragments() {
        fragment1 = new homeFragment();
        fragment2 = new hotFragment();
        fragment3 = new personalFragment();
        fragments = new Fragment[]{fragment1, fragment2, fragment3};
        lastShowFragment = 0;
        getSupportFragmentManager().beginTransaction().add(R.id.content, fragment1).show(fragment1).commit();
    }

    private WindowManager windowManager;
    private Button button;

    /**
     * 点击按钮对应的方法
     *
     * @param v
     */
    public void runMyUiautomator(View v) {
        button = new Button(this);
        button.setText("悬浮窗 Zhang Phil @CSDN");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                // 作为测试，点击后删除该悬浮窗（即Button按钮）
                windowManager.removeView(button);
            }
        });
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        // 靠手机屏幕的左边居中显示
        // 类型
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        // 设置flag
        int flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = flags;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题

        params.gravity = Gravity.CENTER;
        windowManager.addView(button, params);
        moveTaskToBack(true);

// 更新
// windowManager.updateViewLayout(button, params);
    }
}
