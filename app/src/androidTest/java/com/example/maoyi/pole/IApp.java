package com.example.maoyi.pole;

import android.support.test.uiautomator.UiObject2;

import java.util.List;
import java.util.regex.Pattern;

public interface IApp {


    /*
    选择按照文本内容选择 元素 严格匹配名称
     */
    List<UiObject2> findByText(String txt);

    /*
      选择按照文本内容选择 元素 正则表达式匹配名称
      */
    List<UiObject2> findByRegexText(Pattern regex);

    /*
     选择按照ID
     */
    List<UiObject2> findByID(String id);


   List<UiObject2>   findByID(Pattern pattern);
    /*
    点击 某个 Item的方法
     */
    void clickItem(UiObject2 uiObject2) throws Exception;

    /*
    打开App
     */
    void openApp() throws Exception;

    /*
    home 按钮
     */
    void home();

    /*
    返回按钮
     */
    void back();

    /*
    向右滑动
     */
    void right(int steps);

    /*
    向右滑动
     */
    void left(int steps);

    /*
    向上滑动
     */
    void up(int steps);

    /*
    向下滑动
     */
    void dowm(int steps);


        /*
     向右滑动
      */
    void right(int steps,int count) throws InterruptedException;

    /*
    向右滑动
     */
    void left(int steps,int count) throws InterruptedException;

    /*
    向上滑动
     */
    void up(int steps,int count) throws InterruptedException;

    /*
    向下滑动
     */
    void dowm(int steps,int count) throws InterruptedException;

    /*
    刷新
     */
    void refresh();


    /*
    处理怎么去更新APP 一般为不更新
     */
    void updateApp();

    /*
    签到
     */
    void checkIn() throws Exception;


    /*
    整点
     */
    void timepoint();

    /*
    定义怎么读取咨询
     */
    void readItem() throws Exception;

    /*
    睡眠
     */
    void sleep(long s) throws InterruptedException;
}
