package com.inspur.common.util;

import java.util.UUID;

/**
 * Created by wu.bing on 2018/11/13.
 */
public class UUIDutil {
    public static String UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
