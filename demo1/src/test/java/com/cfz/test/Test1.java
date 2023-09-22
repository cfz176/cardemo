package com.cfz.test;

import com.cfz.utils.SendSms;
import org.junit.Test;

import java.io.IOException;

public class Test1 {

    @Test
    public void t1() {
        try {
            SendSms.sendMessage("18191396409","1234");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
