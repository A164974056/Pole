package com.example.maoyi.pole;

import android.support.test.uiautomator.UiObject2;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import static java.lang.Thread.*;

public class AppQuDuoPai extends AbsApp implements ITest {

    AppQuDuoPai() {
        appName = "趣多拍";
    }

    @Override
    public void readItem() throws Exception {
        int count = readCount;
        while (true) {
            for (int i = 0; i < 5; i++) {
                List<UiObject2> lis = findByText("首页");
                if (lis.size() == 1) {
                    lis.get(0).click();
                    Thread.sleep(10000+new Random().nextInt(10000));
                }
                readCount--;
                if (readCount < 0) {
                    return;
                }
            }
        }
    }


    @Override
    public void checkIn() throws Exception {

        List<UiObject2> lis = findByText("我的");
        if (lis.size() == 1) {
            lis.get(0).click();
            Thread.sleep(2000+new Random().nextInt(500));
        } else {
            throw new Exception("遇到错误， 未找到我的");
        }

        lis = findByID("com.xike.yipai:id/img_pex2header_message");
        if (lis.size() == 1) {
            lis.get(0).click();
            Thread.sleep(1000+new Random().nextInt(500));
        }

        lis = findByID("com.xike.yipai:id/message_task_action");
        if (lis.size() == 1) {
            lis.get(0).click();
            Thread.sleep(1000+new Random().nextInt(500));
        }

        lis = findByText("确定");
        if (lis.size() == 1) {
            lis.get(0).click();
            Thread.sleep(1000+new Random().nextInt(500));
        }
        else
            back();

        Thread.sleep(1000+new Random().nextInt(500));

        lis = findByText("首页");
        if (lis.size() == 1) {
            lis.get(0).click();
            Thread.sleep(1000+new Random().nextInt(500));
        } else {
            throw new Exception("遇到错误， 未找到我的");
        }

    }


    @Override
    public void test()  {
        try {
            openApp();
            Thread.sleep(12000);
//            updateApp();
//            Thread.sleep(500);
            checkIn();
            Thread.sleep(2000+new Random().nextInt(500));
//            timepoint();
//            Thread.sleep(1000);
//            refresh();
//            Thread.sleep(5000);
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

    @Override
    public void setReadCount(Integer count) {
        this.readCount = count;
    }
}
