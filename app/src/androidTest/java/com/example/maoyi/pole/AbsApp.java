package com.example.maoyi.pole;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiSelector;

import java.util.List;

public class AbsApp implements IApp {

    private UiDevice uiDevice;

    private String appName;

    @Override
    public boolean openApp() {

        List<UiObject2> list= uiDevice.findObjects(By.text(appName));
        return false;
    }

    @Override
    public void home() {
        uiDevice.pressHome();
    }

    @Override
    public void back() {
        uiDevice.pressBack();
    }

    @Override
    public void right() {

    }

    @Override
    public void left() {

    }

    @Override
    public void up() {

    }

    @Override
    public void dowm() {

    }

    @Override
    public void clickItem() {

    }
}
