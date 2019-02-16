package com.example.maoyi.pole;

import android.os.SystemClock;
import android.support.test.uiautomator.UiObject2;

import java.util.List;
import java.util.regex.Pattern;

public class AppHuiTaoTiao extends AbsApp implements ITest {


    AppHuiTaoTiao() {
        appName = "惠头条";
    }


    @Override
    public void setReadCount(Integer count) {
        this.readCount = count;
    }


    public  void hulue()
    {
        List<UiObject2> lis =  findByText("忽略");
        if (lis.size() > 0) {
            lis.get(0).click();
        } else
            LogHandle.e("未找到领取按钮");
    }

    @Override
    public void timepoint() {

        List<UiObject2> lis = findByText("点击领取");
        if (lis.size() > 0) {
            lis.get(0).click();
        } else
            LogHandle.e("未找到领取按钮");

        lis = findByText("忽略");
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
    public void test() throws Exception {
        try {
            openApp();

            SystemClock.sleep(12000);
//            updateApp();
//            SystemClock.sleep(2000);
//            checkIn();
//            SystemClock.sleep(2000);
            hulue();
            timepoint();
            back();
            hulue();
            SystemClock.sleep(2000);
            refresh();
            SystemClock.sleep(5000);
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
