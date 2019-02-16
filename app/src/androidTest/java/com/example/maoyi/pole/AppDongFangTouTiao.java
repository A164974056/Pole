package com.example.maoyi.pole;

import android.os.SystemClock;
import android.support.test.uiautomator.UiObject2;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class AppDongFangTouTiao extends AbsApp implements ITest {


    AppDongFangTouTiao() {
        appName = "东方头条";
    }


    @Override
    public void setReadCount(Integer count) {
        this.readCount = count;
    }





    @Override
    public void readItem() throws Exception {
        int count = readCount;
        while (true) {
            String cur = "";
            count--;
            for (int i = 0; i < 5; i++) {
                count--;
                List<UiObject2> lis = findByRegexText(Pattern.compile("\\d{1,9}小时前"));
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
                   sleep(2000);
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


    @Override
    public void checkIn() throws Exception {

        List<UiObject2> lis=findByText("任务");
        if  (lis.size()==1){
            lis.get(0).click();
           sleep(1000+new Random().nextInt(500));
        }
        else {
            throw new Exception("遇到错误， 未找到任务");
        }

        lis=findByID("com.songheng.eastnews:id/u6");
        if  (lis.size()==1){
            lis.get(0).click();
           sleep(1000+new Random().nextInt(500));
        }

        lis=findByText("签到");
        if  (lis.size()==1){
            lis.get(0).click();
           sleep(1000+new Random().nextInt(500));
        }

        lis=findByText("头条");
        if  (lis.size()==1){
            lis.get(0).click();
           sleep(1000+new Random().nextInt(500));
        }
        else {
            throw new Exception("遇到错误， 未找到我的");
        }

    }




    @Override
    public void test() throws Exception {
        try {
            openApp();

           sleep(12000);
//            updateApp();
//           sleep(2000);
            checkIn();
           sleep(2000);

            timepoint();
            back();

           sleep(2000);
            refresh();
           sleep(5000);
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
