package com.example.maoyi.pole;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

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


    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    public void runMyUiautomator(View v) {


        new UiautomatorThread().start();







        //region 这些没用的，浪费我时间
        //        final Button btn1Start= new Button(this);
        //        final Button btnEnd = new Button(this);
        //        final LinearLayout frameLayout=new LinearLayout(this);
        //        frameLayout.setOrientation(LinearLayout.HORIZONTAL);
        //
        //        btn1Start.setGravity(Gravity.CENTER);
        //        btnEnd.setGravity(Gravity.CENTER);
        //        //textView.setBackgroundColor(Color.BLACK);
        //        btn1Start.setText("开始");
        //        btnEnd.setText("结束");
        //        btn1Start.setTextSize(10);
        //        btnEnd.setTextSize(10);
        //        btn1Start.setTextColor(Color.RED);
        //        btnEnd.setTextColor(Color.BLUE);
        //
        //        //frameLayout.setBackgroundColor(Color.TRANSPARENT);
        //        //frameLayout.setLayoutParams(new LinearLayout.LayoutParams(100,WRAP_CONTENT));
        //        frameLayout.addView(btn1Start);
        //        frameLayout.addView(btnEnd);
        //        //类型是TYPE_TOAST，像一个普通的AndroidToast一样。这样就不需要申请悬浮窗权限了。
        //        final WindowManager.LayoutParams params = new
        //                WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_PHONE);
        //
        //        //初始化后不首先获得窗口焦点。不妨碍设备上其他部件的点击、触摸事件。
        //        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //        params.gravity=Gravity.TOP;
        //        params.width = 400;
        //        params.height = 150;
        //        //params.gravity=Gravity.BOTTOM;
        //        params.alpha = 0.8f;
        //         final WindowManager windowManager = (WindowManager)
        //                getApplication().getSystemService(WINDOW_SERVICE);
        //         /*
        //         拖动
        //          */
        //        frameLayout.setOnTouchListener(new View.OnTouchListener() {
        //            long startTime;
        //            long endTime;
        //            float mTouchStartX;
        //            float mTouchStartY;
        //            boolean isClick=false;
        //            @Override
        //            public boolean onTouch(View view, MotionEvent motionEvent) {
        //                int x = (int) motionEvent.getRawX();
        //                int y = (int) motionEvent.getRawY();
        //                int action = motionEvent.getAction();
        //                switch (action) {
        //                    case MotionEvent.ACTION_DOWN:
        //                        startTime = System.currentTimeMillis();
        //                        mTouchStartX = motionEvent.getX();
        //                        mTouchStartY = motionEvent.getY();
        //                        break;
        //                    case MotionEvent.ACTION_MOVE:
        //                        float moveX = motionEvent.getX();
        //                        float moveY = motionEvent.getY();
        //                        if (Math.abs(mTouchStartX - moveX) > 3 && Math.abs(mTouchStartY - moveY) > 3) {
        //                            params.x = (int)(x- mTouchStartX);
        //                            params.y = (int)(y - mTouchStartY);
        //                            assert windowManager != null;
        //                            windowManager.updateViewLayout(frameLayout,params);
        //                            return false;
        //                        }
        //                    case MotionEvent.ACTION_UP:
        //                        endTime = System.currentTimeMillis();
        //                        isClick = endTime - startTime < 3 * 1000L;
        //                        break;
        //                }
        //
        //                return true;
        //            }
        //        });
        //
        //
        //        final UiautomatorThread[] uiautomatorThread = new UiautomatorThread[1];
        //
        //        /*
        //        开始
        //        */
        //        btn1Start.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //
        //                if(!btn1Start.isEnabled()) {
        //                    Toast.makeText(getApplication(), "已经开始了,无法在开始....", Toast.LENGTH_SHORT).show();
        //                    return;
        //                }
        //                btn1Start.setEnabled(false);
        //                Toast.makeText(getApplication(), "开始啦！", Toast.LENGTH_SHORT).show();
        //                Log.i(TAG, "runMyUiautomator: ");
        //                uiautomatorThread[0] = new UiautomatorThread();
        //                uiautomatorThread[0].start();
        //            }
        //        });
        //
        //        /*
        //        结束
        //         */
        //        btnEnd.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //
        //                if(btn1Start.isEnabled()) {
        //                    Toast.makeText(getApplication(), "已经结束啦了,无法在结束....", Toast.LENGTH_SHORT).show();
        //                    return;
        //                }
        //
        //                btn1Start.setEnabled(true);
        //                Toast.makeText(getApplication(), "结束啦！", Toast.LENGTH_SHORT).show();
        //              if(  uiautomatorThread[0]!=null)
        //                  uiautomatorThread[0].stop();
        //            }
        //        });
        //        assert windowManager != null;
        //        windowManager.addView(frameLayout, params);


    }


    /**
     * 运行uiautomator是个费时的操作，不应该放在主线程，因此另起一个线程运行
     */
    class UiautomatorThread extends Thread {
        @Override
        public void run() {
            super.run();
            String command = generateCommand("com.example.maoyi.pole", "ExampleInstrumentedTest", "demo");
            CMDUtils.CMD_Result rs = CMDUtils.runCMD(command, true, true);
            Log.e(TAG, "run: " + rs.error + "-------" + rs.success);
        }

        /**
         * 生成命令
         *
         * @param pkgName 包名
         * @param clsName 类名
         * @param mtdName 方法名
         * @return
         */
        String generateCommand(String pkgName, String clsName, String mtdName) {

            int qtt= parseInt(((EditText)findViewById(R.id.qtt)).getText().toString());
            int zqkd=parseInt(((EditText)findViewById(R.id.zqkd)).getText().toString());
            int qdp=parseInt(((EditText)findViewById(R.id.qdp)).getText().toString());

            int wlkk=parseInt(((EditText)findViewById(R.id.wlkk)).getText().toString());
            int times=parseInt(((EditText)findViewById(R.id.times)).getText().toString());

            String s=times+ "#"+0+"_"+qtt + "#"+1+"_"+zqkd+ "#"+2+"_"+qdp+ "#"+3+"_"+wlkk;

            String command = "su -c am instrument --user 0 -w -r -e debug false -e key "+s+" -e class '"
                    + pkgName + "." + clsName + "#" + mtdName + "' "
                    + pkgName + ".test/android.support.test.runner.AndroidJUnitRunner";
            Log.e("test1: ", command);
            return command;
        }
    }


}
