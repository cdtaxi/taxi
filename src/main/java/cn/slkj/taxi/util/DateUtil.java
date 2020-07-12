package cn.slkj.taxi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
  private static final SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
  private static final SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
  private static final SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
  private static final SimpleDateFormat sdfDayss = new SimpleDateFormat("yyyyMMddHHmmss");
  private static final SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  public static String getYear()
  {
    return sdfYear.format(new Date());
  }
  
  public static String getDay()
  {
    return sdfDay.format(new Date());
  }
  
  public static String getDays()
  {
    return sdfDays.format(new Date());
  }
  
  public static String getDayss()
  {
    return sdfDayss.format(new Date());
  }
  
  public static String getTime()
  {
    return sdfTime.format(new Date());
  }
  
  public static boolean compareDate(String s, String e)
  {
    if ((fomatDate(s) == null) || (fomatDate(e) == null)) {
      return false;
    }
    return fomatDate(s).getTime() >= fomatDate(e).getTime();
  }
  
  public static Date fomatDate(String date)
  {
    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      return fmt.parse(date);
    }
    catch (ParseException e) {}
    return null;
  }
  
  public static boolean isValidDate(String s)
  {
    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      fmt.parse(s);
      return true;
    }
    catch (Exception e) {}
    return false;
  }
  
  public static int getDiffYear(String startTime, String endTime)
  {
    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      return (int)((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / 86400000L / 
        365L);
    }
    catch (Exception e) {}
    return 0;
  }
  
  public static long getDaySub(String beginDateStr, String endDateStr)
  {
    long day = 0L;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date beginDate = null;
    Date endDate = null;
    try
    {
      beginDate = format.parse(beginDateStr);
      endDate = format.parse(endDateStr);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    day = (endDate.getTime() - beginDate.getTime()) / 86400000L;
    
    return day;
  }
  
  public static String getAfterDayDate(String days)
  {
    int daysInt = Integer.parseInt(days);
    
    Calendar canlendar = Calendar.getInstance();
    canlendar.add(5, daysInt);
    Date date = canlendar.getTime();
    
    SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateStr = sdfd.format(date);
    
    return dateStr;
  }
  
  public static String getAfterDayWeek(String days)
  {
    int daysInt = Integer.parseInt(days);
    
    Calendar canlendar = Calendar.getInstance();
    canlendar.add(5, daysInt);
    Date date = canlendar.getTime();
    
    SimpleDateFormat sdf = new SimpleDateFormat("E");
    String dateStr = sdf.format(date);
    
    return dateStr;
  }
  
  public static String getDateOfYear(String mydate)
  {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      Date date = simpleDateFormat.parse(mydate);
      calendar.setTime(date);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    String year = String.valueOf(calendar.get(1));
    return year;
  }
  
  public static String getDateOfMonth(String mydate)
  {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      Date date = simpleDateFormat.parse(mydate);
      calendar.setTime(date);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    String month = String.valueOf(calendar.get(2) + 1);
    return month;
  }
  
  public static String getDateOfDay(String mydate)
  {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      Date date = simpleDateFormat.parse(mydate);
      calendar.setTime(date);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    String day = String.valueOf(calendar.get(5));
    return day;
  }
  
  public static String dataToUpper(String dateStr)
  {
    String res = "";
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try
    {
      date = df.parse(dateStr);
    }
    catch (Exception e)
    {
      System.out.println("日期型字符串格式错误");
    }
    if (date != null)
    {
      Calendar ca = Calendar.getInstance();
      ca.setTime(date);
      int year = ca.get(1);
      int month = ca.get(2) + 1;
      int day = ca.get(5);
      res = numToUpper(year) + "年" + monthToUppder(month) + "月" + dayToUppder(day) + "日";
    }
    return res;
  }
  
  public static String numToUpper(int num)
  {
    String[] u = { "○", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
    char[] str = String.valueOf(num).toCharArray();
    String rstr = "";
    for (int i = 0; i < str.length; i++) {
      rstr = rstr + u[Integer.parseInt(new StringBuilder(String.valueOf(str[i])).toString())];
    }
    return rstr;
  }
  
  public static String monthToUppder(int month)
  {
    if (month < 10) {
      return numToUpper(month);
    }
    if (month == 10) {
      return "十";
    }
    return "十" + numToUpper(month - 10);
  }
  
  public static String dayToUppder(int day)
  {
    if (day < 20) {
      return monthToUppder(day);
    }
    char[] str = String.valueOf(day).toCharArray();
    if (str[1] == '0') {
      return numToUpper(Integer.parseInt(new StringBuilder(String.valueOf(str[0])).toString())) + "十";
    }
    return numToUpper(Integer.parseInt(new StringBuilder(String.valueOf(str[0])).toString())) + "十" + numToUpper(Integer.parseInt(new StringBuilder(String.valueOf(str[1])).toString()));
  }
  
  public static String computeDate(String strDate)
  {
    if ((!strDate.equals("")) && (strDate != null)) {
      try
      {
        if (strDate.length() > 8) {
          return strDate;
        }
        DateFormat fmt = new SimpleDateFormat();
        if (Integer.parseInt(strDate.substring(4, 6)) > 31) {
          fmt = new SimpleDateFormat("yyyyMdd");
        } else {
          fmt = new SimpleDateFormat("yyyyMMdd");
        }
        Date date = fmt.parse(strDate);
        DateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        strDate = sdf3.format(date);
      }
      catch (ParseException e)
      {
        return strDate;
      }
    }
    return strDate;
  }
  
  public static int getCount(String str, String sub)
  {
    int index = 0;
    int count = 0;
    while ((index = str.indexOf(sub, index)) != -1)
    {
      index += sub.length();
      count++;
    }
    return count;
  }
  
  public static void main(String[] args)
  {
    System.err.println(isValidDate(""));
  }
}
