package com.example.maoyi.pole;

import android.support.test.espresso.AppNotIdleException;
import android.support.test.uiautomator.UiObject2;

import java.util.List;

public class AppQuTaoTiao extends AbsApp {



    public  AppQuTaoTiao( int readCount)
    {
        appName="趣头条";
        this.readCount=readCount;
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
            Thread.sleep(4000);
            readItem();
        } catch (Exception e) {

            LogHandle.d(e.getMessage());

        }
        finally {
            back();
            back();
            back();
            back();
        }
    }


}
