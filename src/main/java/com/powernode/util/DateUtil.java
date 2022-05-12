package com.powernode.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义日期工具类
 */
public class DateUtil {

    /**
     * 将date类型转化成string类型
     * @param date
     * @return
     */
    public static String date2String(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

}
