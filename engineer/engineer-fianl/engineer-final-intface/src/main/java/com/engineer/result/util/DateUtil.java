package com.engineer.result.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param
     * @return
     */
    public static String timeStamp2Date() {

       String format = "yyyy年MM月dd日 HH时mm分ss秒";

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(System.currentTimeMillis());
    }
    /**
     * 日期格式字符串转换成时间戳
     * @param
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

    /*public static void main(String[] args) {
        String timeStamp = timeStamp();
        System.out.println("timeStamp="+timeStamp); //运行输出:timeStamp=1470278082
        System.out.println(System.currentTimeMillis());//运行输出:1470278082980
        //该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数


        System.out.println("date="+date);//运行输出:date=2016-08-04 10:34:42

        String timeStamp2 = date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");
        System.out.println(timeStamp2);  //运行输出:1470278082
    }*/
}