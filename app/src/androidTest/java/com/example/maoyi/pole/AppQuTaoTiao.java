package com.example.maoyi.pole;

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
    public void test()  {
        try {
            openApp();
            Thread.sleep(10000);
            updateApp();
            Thread.sleep(500);
            checkIn();
            Thread.sleep(500);
            timepoint();
            Thread.sleep(1000);
            refresh();
            Thread.sleep(5000);
            readItem();
        } catch (Exception e) {
            LogHandle.d(e.getMessage());
        } finally {
            back();
            back();
            back();
            back();
        }
    }


}
