package com.pengniao.rdpm.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: rdpm
 * @description: 产生日期时间
 * @author: lj
 * @create: 2019-09-24 11:33
 **/
public class DateTime {

    /** 功能描述: 获取系统当前日期时间
     * @param
    * @return: java.lang.String 返回系统日期 yyyy-MM-dd HH:mm:ss
    * @Author: lj
    * @Date: 2019/9/24 11:36
    */
    public static String getNow()
    {
        SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = df.format(new Date());
        return str;
    }

    /** 功能描述: 获取系统当前日期
     * @param
    * @return: java.lang.String yyMMdd
    * @Author: lj
    * @Date: 2019/9/24 11:38
    */
    public static String getNowDT(){
        SimpleDateFormat df =new SimpleDateFormat("yyMMdd");
        String str = df.format(new Date());
        return str;
    }

    /** 功能描述: 获取系统当前日期
     * @param
     * @return: java.lang.String yyyy-MM-dd
     * @Author: lj
     * @Date: 2019/9/24 11:42
     */
    public static String getCurDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /** 功能描述: 获取当前日期yyyy年MM月dd日  星期
     * @param
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/9/24 11:38
    */
    public static String getNowWeek(){
        SimpleDateFormat df =new SimpleDateFormat("yyyy年MM月dd日  EEEE");
        String str = df.format(new Date());
        return str;
    }

    /**
     *
     * 描述：
     * @param n
     * @return
     */

    /** 功能描述: 返回n天前日期 格式yyyy-MM-dd
     * @param n
    * @return: java.lang.String yyyy-MM-dd
    * @Author: lj
    * @Date: 2019/9/24 11:39
    */
    public static String getForNDay(int n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -n);
        String  dayDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return dayDate;
    }

    /** 功能描述: 返回n天后日期 格式yyyyMMdd
     * @param n
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/9/24 11:40
    */
    public static String getForNRDay(int n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, n);
        String  dayDate = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        return dayDate;
    }

    /** 功能描述: 得到前n个月的月份字符串
     * @param n
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/9/24 11:40
    */
    public static String getForNMonth(int n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -n);
        String  monthDate = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
        return monthDate;
    }

    /** 功能描述: 将日期字符串类型转换成Integer类型 ,格式为 yyyyMMdd 例：返回20091011
     * @param str
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/9/24 11:40
    */
    public static Integer getIntDate(String str) {
        str = str.replace("-", "").substring(0,8);
        Integer intDate = Integer.parseInt(str);
        return intDate;
    }

    /** 功能描述: 计算bdate-smdate之间的天数
     * @param smdate
     * @param bdate
    * @return: int
    * @Author: lj
    * @Date: 2019/9/24 11:40
    */
    public static int daysBetween(String smdate,String bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /** 功能描述: 获取当前年
     * @param
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/9/24 11:41
    */
    public static String getCurDateY() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    /** 功能描述: 字符串转换成日期时间
     * @param str
    * @return: java.util.Date 返回被格式化的日期 yyyy-MM-dd HH:mm:ss
    * @Author: lj
    * @Date: 2019/9/24 11:41
    */
    public static Date strToDateTime(String str)
    {
        SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /** 功能描述: 获取系统当前月第一天日期
     * @param
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/9/24 11:43
    */
    public static String getFirstDateByCurMonth(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date)+"-01";
    }

    public static String getCurMonth() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }
    /** 功能描述: 获取从当前月向后推一年的日期
     * @param
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/9/24 11:43
    */
    public static String getLastOneYear() {
        Date _date = new Date();
        SimpleDateFormat _format = new SimpleDateFormat("yyyy-MM");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(_date);
        calendar.add(Calendar.YEAR, -1);
        _date=calendar.getTime();
        String currentDate=_format.format(_date);
        return currentDate;
    }

    /** 功能描述: 当前月向前推1个月的日期  2016-12-06向前一月是2016-11-07
     * @param
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/9/24 11:43
    */
    public static String getLastOneMonth() {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)-1);
        curr.set(Calendar.DAY_OF_MONTH,curr.get(Calendar.DAY_OF_MONTH)+1);
        Date _date=curr.getTime();
        SimpleDateFormat _format = new SimpleDateFormat("yyyy-MM-dd");
        return  _format.format(_date);

    }

    /** 功能描述: 获取从当前月向后推一年的日期
     * @param
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/9/24 11:44
    */
    public static String getLastOneYears() {
        Date _date = new Date();
        SimpleDateFormat _format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(_date);
        calendar.add(Calendar.YEAR, -1);
        _date=calendar.getTime();
        String currentDate=_format.format(_date);
        return currentDate;
    }
}
