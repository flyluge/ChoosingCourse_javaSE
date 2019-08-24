package com.lu.util;

import java.util.UUID;

/**
 * @Author: Luge
 * @DATE: 2019/8/22 9:48
 */
public class UUIDUtil {
    public static String getUUIDid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
