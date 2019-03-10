package com.example.maoyi.pole;

import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;


import java.lang.reflect.Array;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.maoyi.pole", appContext.getPackageName());
    }


    @Test
    public void demo() throws Exception {
        ITest[] iTests = new ITest[]{new AppQuTaoTiao(), new AppQuDuoPai(), new AppZhongQinKanDian(), new AppWeiLiKankan(), new AppHuiTaoTiao()};
        Bundle a = InstrumentationRegistry.getArguments();

        String bb = a.getString("key");


        //String bb="4#0_10";

        assert bb != null;
        String[] temp = bb.split("#");
        Integer times = Integer.parseInt(temp[0]);

        if (times > 0) {
            for (int j = 0; j < times; j++) {
                try {
                    for (int i = 1; i < temp.length; i++) {
                        String[] temp_app = temp[i].split("_");
                        Integer app_index = Integer.parseInt(temp_app[0]);
                        Integer app_times = Integer.parseInt(temp_app[1]);
                        if (app_times == 0) {
                            continue;
                        }
                        iTests[app_index].setReadCount(app_times);
                        iTests[app_index].test();
                    }
                } catch (Exception e) {
                    LogHandle.e(e.getMessage());
                }
            }
        }

    }

}
