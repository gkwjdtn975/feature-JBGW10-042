package com.nhnacademy;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class App1 {
    public static void main( String[] args ) {
        String str1 = null;
        String str2 = "";
        String str3 = "Hello";

        System.out.println("Objects.isNull(str1):" + Objects.isNull(str1));
        System.out.println("Objects.isNull(str2):"+ Objects.isNull(str2));

        if( str2 != null){
            System.out.println("str2.isEmpty():" + str2.isEmpty());
        }
        System.out.println("StringUtils.isEmpty(str1):" + StringUtils.isEmpty(str1));
        System.out.println("StringUtils.isEmpty(str2):" + StringUtils.isEmpty(str2));
        System.out.println("StringUtils.isEmpty(str3):" + StringUtils.isEmpty(str3));
    }
}
