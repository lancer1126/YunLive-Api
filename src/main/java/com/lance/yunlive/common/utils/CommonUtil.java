package com.lance.yunlive.common.utils;

public class CommonUtil {

    /**
     * 将“xx万”的字符串转为具体的数值
     */
    public static Integer NumStrToInt(String number) {
        int num = 0;
        if(number.contains("万")){
            int index = number.indexOf(".");
            String temp = number.substring(0, index)+number.substring(index+1, number.length()-1);
            temp = temp + (int)((Math.random()*9+1)*100);
            num = Integer.parseInt(temp);
        }else if (number.contains("亿")){
            int index = number.indexOf(".");
            String temp = number.substring(0, index)+number.substring(index+1, number.length()-1);
            temp = temp + (int)((Math.random()*9+1)*1000000);
            num = Integer.parseInt(temp);
        }else {
            return Integer.valueOf(number);
        }
        return num;
    }

}
