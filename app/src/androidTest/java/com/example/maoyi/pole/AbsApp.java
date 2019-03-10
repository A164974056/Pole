package com.example.maoyi.pole;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.util.Log;

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


        LogHandle.e("获取更新消息--开始");
        List<UiObject2> lis = findByID("以后更新");
        if (lis!=null) {
            lis.get(0).click();
            back();
        }
        LogHandle.e("获取更新消息--结束");
    }

    @Override
    public void refresh() {
        LogHandle.e("刷新主页");
        List<UiObject2> lis = findByText("刷新");
        if (lis!=null) {
            lis.get(0).click();
        } else
            LogHandle.e("未找到刷新按钮");

    }


    @Override
    public void checkIn() throws Exception {
        LogHandle.e("打开我的按钮");
        List<UiObject2> lis = findByText("我的");
        if (lis!=null) {
            lis.get(0).click();

           // sleep(1000 + new Random().nextInt(500));
        } else {
            throw new Exception("遇到错误， 未找到我的");
        }
        LogHandle.e("打开'我的'按钮--完成");
//        lis = findByID( Pattern.compile("com.jifen.qukan:id/zg"));
//        if (lis!=null) {
//            lis.get(0).click();
//            //sleep(1000 + new Random().nextInt(500));
//        }
        LogHandle.e("获取弹出广告");
        lis = findByID("com.jifen.qukan:id/zg");
        if (lis!=null) {
            lis.get(0).click();
            //sleep(1000 + new Random().nextInt(500));
        }
        LogHandle.e("获取弹出广告--完成");
//        lis = findByID("com.jifen.qukan:id/zg");
//        if (lis!=null) {
//            lis.get(0).click();
//            //sleep(1000 + new Random().nextInt(500));
//        }
        LogHandle.e("签到");
        lis = findByText("签到");
        if (lis!=null) {
            lis.get(0).click();
           // sleep(1000 + new Random().nextInt(500));
        }
        LogHandle.e("签到--完成");
        lis = findByText("头条");
        if (lis!=null) {
            lis.get(0).click();
            //sleep(1000 + new Random().nextInt(500));
        } else {
            throw new Exception("遇到错误， 未找到我的");
        }

    }


    @Override
    public void timepoint() {
        List<UiObject2> lis = findByText("领取");
        if (lis!=null) {
            lis.get(0).click();
        } else
            LogHandle.e("未找到领取按钮");
    }

    @Override
    public void readItem() throws Exception {
        LogHandle.e("开始读取---->  新闻");
        int count = readCount;
        while (true) {
            String cur = "";
            count--;
            for (int i = 0; i < 5; i++) {
                count--;
                List<UiObject2> lis = findByRegexText(Pattern.compile("\\d{1,9}评"));
                if (lis!=null) {
                    if (count < 0) {
                        back();
                        return;
                    }
                    LogHandle.e("开始读取---->  新闻 "+lis.get(0).getText() );
                    if (cur.equals(lis.get(0).getText())) {
                        up(200);
                        continue;
                    }
                    cur = lis.get(0).getText();
                    lis.get(0).click();
                    //region  上下滑动几下
                    sleep(2000);
                    up(100, 8);
                    dowm(100, 1);
                    //endregion
                    back();
                }
                //往下刷两下
                up(200);
                //up(50);
                if (count < 0)
                    throw new Exception("遇到未知错误无法在继续读");
            }
            if (count < 0)
                throw new Exception("遇到未知错误无法在继续读");

        }

    }


    private void setUiDevice() {

        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    }

    @Override
    public List<UiObject2> findByText(String txt) {


        //List<UiObject2> list= uiDevice.wait(Until.findObjects(By.text(txt)),10000);
        return uiDevice.wait(Until.findObjects(By.text(txt)),10000);

        //return uiDevice.findObjects(By.text(txt));
    }

    @Override
    public List<UiObject2> findByRegexText(Pattern regex) {

        return uiDevice.wait(Until.findObjects(By.text(regex)),10000);


        //return uiDevice.findObjects();
    }


    @Override
    public List<UiObject2> findByID(String id) {

        //uiDevice.findObjects(By.res(Pattern.compile(id));
        return uiDevice.wait(Until.findObjects(By.res(id)),10000);
      // return uiDevice.findObjects(By.res(id));
    }

/*
正则表达式匹配
 */
    @Override
    public List<UiObject2> findByID(Pattern pattern) {


        return uiDevice.wait(Until.findObjects(By.res(pattern)),10000);
        //return uiDevice.findObjects(By.res(pattern));
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
            throw new Exception("未设置AppName ");
        if (uiDevice == null)
            setUiDevice();
        home();
        List<UiObject2> lis = null;
        height = Objects.requireNonNull(uiDevice).getDisplayHeight();
        width = Objects.requireNonNull(uiDevice).getDisplayWidth();
        for (int i = 0; i < 4; i++) {
            List<UiObject2> list = uiDevice.findObjects(By.text(appName));
            switch (list.size()) {
                case 0:
                    break;
                case 1: {
                    LogHandle.e("打开APP--"+appName);
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
            sleep(2000 + new Random().nextInt(2000));
        }
    }

    @Override
    public void left(int steps, int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            left(steps);
            sleep(2000 + new Random().nextInt(2000));
        }
    }

    @Override
    public void up(int steps, int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            up(steps);
            sleep(2000 + new Random().nextInt(500));
        }
    }

    @Override
    public void dowm(int steps, int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            dowm(steps);
            sleep(2000 + new Random().nextInt(2000));
        }
    }

    @Override
    public void sleep(long s) throws InterruptedException {
        uiDevice.waitForIdle(s);
        Thread.sleep(s);
    }
}
