package com.example.maoyi.pole;

import android.os.Handler;
import android.os.SystemClock;
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


    public String appOpenFlag = "首页";

    public int readCount;

    private int height;
    private int width;



    @Override
    public void updateApp() {
        List<UiObject2> lis = findByID("以后更新");
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
    public void checkIn() throws Exception {

        List<UiObject2> lis=findByText("我的");
        if  (lis.size()==1){
            lis.get(0).click();
           SystemClock.sleep(1000+new Random().nextInt(500));
        }
        else {
            throw new Exception("遇到错误， 未找到我的");
        }

        lis=findByID("com.jifen.qukan:id/p2");
        if  (lis.size()==1){
            lis.get(0).click();
           SystemClock.sleep(1000+new Random().nextInt(500));
        }

        lis=findByText("签到");
        if  (lis.size()==1){
            lis.get(0).click();
           SystemClock.sleep(1000+new Random().nextInt(500));
        }

        lis=findByText("头条");
        if  (lis.size()==1){
            lis.get(0).click();
           SystemClock.sleep(1000+new Random().nextInt(500));
        }
        else {
            throw new Exception("遇到错误， 未找到我的");
        }

    }



    @Override
    public void timepoint() {
        List<UiObject2> lis = findByText("领取");
        if (lis.size() > 0) {
            lis.get(0).click();
        } else
            LogHandle.e("未找到领取按钮");
    }

    @Override
    public void readItem() throws Exception {
        int count = readCount;
        while (true) {
            String cur = "";
            count--;
            for (int i = 0; i < 5; i++) {
                count--;
                List<UiObject2> lis = findByRegexText(Pattern.compile("\\d{1,9}评"));
                if (lis.size() > 0) {
                    if (count < 0) {
                        back();
                        return;
                    }
                    if (cur.equals(lis.get(0).getText())) {
                        up(200);
                        continue;
                    }
                    cur = lis.get(0).getText();
                    lis.get(0).click();
                    //region  上下滑动几下
                   SystemClock.sleep(2000);
                    up(100, 8);
                    dowm(100, 1);
                    //endregion
                    back();
            }
                //往下刷两下
                up(200);
                //up(50);
                if (count <0)
                    throw new Exception("遇到未知错误无法在继续读");
            }
            if (count <0)
                throw new Exception("遇到未知错误无法在继续读");

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
    public List<UiObject2> findByID(String id){
        return uiDevice.findObjects(By.res(id));
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
            throw new Exception("未设置AppName " );
        if (uiDevice == null)
            setUiDevice();
        home();
        List<UiObject2> lis=null;
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
                    //throw new Exception("未找到首页 " + appName);
                }
                default: {
                    break;
                }
            }
            left(20);
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
    public void right(int steps) {
        uiDevice.swipe((int) (width * 0.25), (int) (height * 0.5), (int) (width * 0.75), (int) (height * 0.5), steps);
    }

    @Override
    public void left(int steps) {
        uiDevice.swipe((int) (width * 0.75), (int) (height * 0.5), (int) (width * 0.25), (int) (height * 0.5), steps);
    }

    @Override
    public void up(int steps) {
        uiDevice.swipe((int) (width * 0.5), (int) (height * 0.75), (int) (width * 0.5), (int) (height * 0.25), steps);
    }

    @Override
    public void dowm(int steps) {
        uiDevice.swipe((int) (width * 0.5), (int) (height * 0.25), (int) (width * 0.5), (int) (height * 0.75), steps);
    }

    @Override
    public void right(int steps, int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            right(steps);
           SystemClock.sleep(2000 + new Random().nextInt(2000));
        }
    }

    @Override
    public void left(int steps, int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            left(steps);
           SystemClock.sleep(2000 + new Random().nextInt(2000));
        }
    }

    @Override
    public void up(int steps, int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            up(steps);
           SystemClock.sleep(2000 + new Random().nextInt(500));
        }
    }

    @Override
    public void dowm(int steps, int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            dowm(steps);
           SystemClock.sleep(2000 + new Random().nextInt(2000));
        }
    }

}
