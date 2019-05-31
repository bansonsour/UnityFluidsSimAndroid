package com.han.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by han on 2018/4/23.
 */

public class ajcTest {

    //获取当前时间
    public String getNowTime() {
        long time = System.currentTimeMillis();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE).format(new Date(time));
    }


    public String GetString() {
        return "111111";
    }

    ///DoSomething
    public void PeoPleDoSomething(IJavaProxy ijp) {
        ijp.Eat();
        ijp.Sleep();
        ijp.Code();
    }

    //runableTest
    public void RunableTest(Runnable ra) {
        ra.run();
    }
}
