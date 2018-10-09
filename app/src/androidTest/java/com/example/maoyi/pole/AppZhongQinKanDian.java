package com.example.maoyi.pole;

import android.support.test.uiautomator.UiObject2;

import java.util.List;
import java.util.regex.Pattern;

public class AppZhongQinKanDian extends AbsApp implements ITest {



    AppZhongQinKanDian()
    {
        appName="中青看点";
        appOpenFlag="刷新";
    }




    @Override
    public void updateApp() {
        List<UiObject2> lis = findByID("cn.youth.news:id/ge");
        if (lis.size() > 0) {
            lis.get(0).click();
            back();
        }
    }



    @Override
    public void checkIn() throws Exception {

        List<UiObject2> lis=findByText("我的");
        if  (lis.size()==1){
            lis.get(0).click();
            Thread.sleep(1000);
        }
        else {
            throw new Exception("遇到错误， 未找到我的");
        }

        lis=findByText("任务中心");
        if  (lis.size()==1){
            lis.get(0).click();
            Thread.sleep(2000);
        }

        lis=findByText("签到");
        if  (lis.size()==1){
            lis.get(0).click();
            Thread.sleep(1000);
        }

        lis=findByText("头条");
        if  (lis.size()==1){
            lis.get(0).click();
            Thread.sleep(1000);
        }
        else {
            throw new Exception("遇到错误， 未找到我的");
        }

    }
    @Override
    public void readItem() throws Exception {
        int count = readCount;
        while (true) {

            String cur = "";
            for (int i = 0; i < 5; i++) {
                up(200);
                readCount--;
                List<UiObject2> lis = findByRegexText(Pattern.compile("\\d{1,9}阅读"));
                if (lis.size() > 0) {

                    if (readCount < 0) {
                        return;
                    }

                    if (cur.equals(lis.get(0).getText())) {
                        continue;
                    }
                    cur = lis.get(0).getText();
                    lis.get(0).click();

                    //region  上下滑动几下
                    Thread.sleep(2000);
                    up(100, 8);
                    dowm(100, 1);
                    //endregion
                    back();
                }
                //往下刷两下
                up(200);
                //up(50);

                if (count == readCount)
                    throw new Exception("遇到未知错误无法在继续读");

            }
        }

    }

    @Override
    public void test() {


        try {
            openApp();
            Thread.sleep(12000);
            updateApp();
            Thread.sleep(1000);
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
            back();
            back();
            back();
        }

    }

    @Override
    public void setReadCount(Integer count) {
            this.readCount=count;
    }





}
