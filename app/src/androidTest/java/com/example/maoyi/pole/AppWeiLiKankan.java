package com.example.maoyi.pole;

import android.os.SystemClock;
import android.support.test.uiautomator.UiObject2;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class AppWeiLiKankan extends AbsApp implements ITest {


    AppWeiLiKankan() {
        appName = "微鲤看看";
        appOpenFlag = "头条";
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
    public void up(int steps, int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            up(steps);
            List<UiObject2> lis = findByText("展开查看全文");
            if (lis.size() > 0) {
                lis.get(0).click();
            }
           SystemClock.sleep(2000 + new Random().nextInt(500));
        }
    }


    @Override
    public void timepoint() {
        List<UiObject2> lis = findByID("cn.weli.story:id/iv_gold_gif");
        if (lis.size() > 0) {
            lis.get(0).click();
        } else
            LogHandle.e("未找到领取按钮");
    }


    private void readTv() throws Exception {
        int count = readCount;

        List<UiObject2> lis = findByText("视频");
        if (lis.size() > 0) {
            lis.get(lis.size() - 1).click();
        } else
            throw new Exception("遇到未知错误无法在继续读");
       SystemClock.sleep(2000);
        lis = findByID("cn.weli.story:id/ic_close");
        if (lis.size() > 0) {
            lis.get(0).click();
        }


        while (true) {

            for (int i = 0; i < 5; i++) {
                count--;
                lis = findByID("cn.weli.story:id/img_ic_play");
                if (lis.size()>0){
                    lis.get(lis.size()-1).click();
                   SystemClock.sleep(20000 + new Random().nextInt(20000));
                    //endregion
                }
                //往下刷两下
                up(200);
                //up(50);
                if (count <0)
                    back();
            }
            if (count <0)
                throw new Exception("遇到未知错误无法在继续读");


        }

    }

    @Override
    public void readItem() throws Exception {


        int count = readCount;
        while (true) {

            String cur = "";
            for (int i = 0; i < 5; i++) {
                count--;
                List<UiObject2> lis = findByID("cn.weli.story:id/tv_from");
                if (lis.size() > 0) {
                    if (lis.get(0).getParent().getChildCount() == 3) {
                        up(200);
                        continue;

                    }
                    count--;
                    if (count < 0) {
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
            }
            if (count < 0) {
                throw new Exception("遇到未知错误无法在继续读");
            }

        }

    }

    @Override
    public void refresh() {
        List<UiObject2> lis = findByText("头条");
        if (lis.size() > 0) {
            lis.get(0).click();
        } else
            LogHandle.e("未找到刷新按钮");

    }

    @Override
    public void test() {
        try {
            openApp();
           SystemClock.sleep(12000);
            updateApp();
            refresh();
           SystemClock.sleep(5000);
            readItem();
           SystemClock.sleep(1000);
            timepoint();
            readTv();

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
