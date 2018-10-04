package com.example.maoyi.pole;

import android.support.test.uiautomator.UiObject2;

import java.util.List;

public class AppQuTaoTiao extends AbsApp {
    @Override
    public void getAppName() {
        appName = "趣头条";
    }

    @Override
    public void getReadCount() {
        readCount=7;
    }


    @Override
    public void test() {
        try {
            openApp();
            Thread.sleep(6000);
            updateApp();
            Thread.sleep(500);
            checkIn();
            Thread.sleep(500);
            refresh();
            Thread.sleep(2000);
            readItem();
        } catch (Exception e) {

            LogHandle.d(e.getMessage());

        }
    }


}
