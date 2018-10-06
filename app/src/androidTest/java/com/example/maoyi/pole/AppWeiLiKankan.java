package com.example.maoyi.pole;

import android.support.test.uiautomator.UiObject2;

import java.util.List;
import java.util.regex.Pattern;

public class AppWeiLiKankan extends AbsApp implements ITest {


    public AppWeiLiKankan() {
        appName = "微鲤看看";

    }

    @Override
    public void setReadCount(Integer count) {
        this.readCount = count;
    }

    @Override
    public void updateApp() {
        List<UiObject2> lis = findByID("cn.weli.story:id/iv_take");
        if (lis.size() > 0) {
            lis.get(0).click();

        }
        lis = findByID("cn.weli.story:id/iv_close");
        if (lis.size() > 0) {
            lis.get(0).click();
        }
    }



    @Override
    public void readItem() throws Exception {
        int count = readCount;
        while (true) {

            String cur = "";
            for (int i = 0; i < count; i++) {
                List<UiObject2> lis = findByID("cn.weli.story:id/tv_from");
                if (lis.size() > 0) {
                    if (lis.get(0).getParent().getChildCount()==3)
                    {
                        continue;
                    }
                    readCount--;
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
            Thread.sleep(8000);
            updateApp();
//            Thread.sleep(500);
//            checkIn();

            Thread.sleep(5000);
            readItem();

//            Thread.sleep(500);
//            timepoint();
//            Thread.sleep(1000);
//            refresh();
//            Thread.sleep(5000);
//            readItem();
        } catch (Exception e) {
            LogHandle.d(e.getMessage());
        } finally {
            back();
            back();
            back();
            back();
        }
    }

}
