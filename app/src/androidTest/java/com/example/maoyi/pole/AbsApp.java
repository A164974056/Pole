package com.example.maoyi.pole;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

public abstract class AbsApp implements IApp, ITest {

    private UiDevice uiDevice;
    public String appName;


    public  int readCount;

    private int height;
    private int width;

    public abstract void getAppName();
    public abstract void getReadCount();

    @Override
    public void updateApp() {
        List<UiObject2> lis = findByText("以后更新");
        if (lis.size() > 0) {
            lis.get(0).click();
            back();
        }
    }

    @Override
    public void refresh() {
        List<UiObject2> lis = findByText("刷新");
        if (lis.size() > 0) {
            lis.get(0).click();
        } else
            LogHandle.e("未找到刷新按钮");

    }

    @Override
    public void checkIn() {
        List<UiObject2> lis = findByText("领取");
        if (lis.size() > 0) {
            lis.get(0).click();
        } else
            LogHandle.e("未找到领取按钮");
    }


    @Override
    public void readItem() throws Exception {

        while (true) {
            int count = readCount;
            for (int i = 0; i < 5; i++) {
                List<UiObject2> lis = findByRegexText(Pattern.compile("\\d{1,9}评"));
                if (lis.size() > 0) {
                    lis.get(0).click();
                    readCount--;
                    //region  上下滑动几下
                    Thread.sleep(2000);
                    up(8);
                    dowm(8);
                    //endregion
                    back();
                }
                //往下刷两下
                up();
                up();
            }
            if (count==readCount)
                throw new Exception("遇到未知错误无法在继续读");
            if( readCount==0){
                return;
            }
        }

    }


    private void setUiDevice() {

        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    }

    @Override
    public List<UiObject2> findByText(String txt) {
        return uiDevice.findObjects(By.text(txt));
    }

    @Override
    public List<UiObject2> findByRegexText(Pattern regex) {
        return uiDevice.findObjects(By.text(regex));
    }

    @Override
    public void clickItem(UiObject2 uiObject2) throws Exception {
        if (uiObject2 == null) {
            throw new Exception("未找到对应的 Item 咋点击");
        }
        uiObject2.click();
    }

    @Override
    public void openApp() throws Exception {

        if (appName == null)
            getAppName();
        if (uiDevice == null)
            setUiDevice();
        home();
        height = Objects.requireNonNull(uiDevice).getDisplayHeight();
        width = Objects.requireNonNull(uiDevice).getDisplayWidth();
        for (int i = 0; i < 4; i++) {
            List<UiObject2> list = findByText(appName);
            switch (list.size()) {
                case 0:
                    break;
                case 1: {
                    clickItem(list.get(0));
                    return;
                }
                default: {
                    break;
                }
            }
            left();
        }
        throw new Exception("未找到对应的App " + appName);
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
        uiDevice.swipe((int) (width * 0.25), (int) (height * 0.5), (int) (width * 0.75), (int) (height * 0.5), 20);
    }

    @Override
    public void left() {
        uiDevice.swipe((int) (width * 0.75), (int) (height * 0.5), (int) (width * 0.25), (int) (height * 0.5), 20);
    }

    @Override
    public void up() {
        uiDevice.swipe((int) (width * 0.5), (int) (height * 0.75), (int) (width * 0.5), (int) (height * 0.25), 20);
    }

    @Override
    public void dowm() {
        uiDevice.swipe((int) (width * 0.5), (int) (height * 0.25), (int) (width * 0.5), (int) (height * 0.75), 20);
    }

    @Override
    public void right(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            right();
            Thread.sleep(2000 + new Random().nextInt(2000));
        }
    }

    @Override
    public void left(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            left();
            Thread.sleep(2000 + new Random().nextInt(2000));
        }
    }

    @Override
    public void up(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            up();
            Thread.sleep(2000 + new Random().nextInt(500));
        }
    }

    @Override
    public void dowm(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            dowm();
            Thread.sleep(2000 + new Random().nextInt(2000));
        }
    }

}
