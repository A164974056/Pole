package com.example.maoyi.pole;

import android.os.SystemClock;
import android.support.test.espresso.AppNotIdleException;
import android.support.test.uiautomator.UiObject2;

import java.util.List;

public class AppQuTaoTiao extends AbsApp {


    AppQuTaoTiao() {
        appName = "趣头条";
        appOpenFlag = "刷新";
    }

    @Override
    public void setReadCount(Integer count) {
        this.readCount = count;
    }


    @Override
    public void test() {
        try {
            openApp();
            SystemClock.sleep(12000);
            updateApp();
            SystemClock.sleep(2000);
            checkIn();
            SystemClock.sleep(2000);
            timepoint();
            SystemClock.sleep(2000);
            refresh();
            SystemClock.sleep(5000);
            readItem();
        } catch (Exception e) {
            LogHandle.d(e.getMessage());
        } finally {
            back();
            back();
            back();
            back();
            back();
            back();
            back();
        }
    }


}
