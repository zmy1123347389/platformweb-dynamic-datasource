package com.behere.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 日期处理
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    public final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    
    public final static String DATE_YEAR = "yyyy";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    public final static String start_hours = " 00:00:00";
    
    public final static String end_hours = " 23:59:59";
    
    public final static String YYYYMMDDTHHMMSSSSSXXX = " yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    
    public static String formatNowDate() {
        return format(new Date(), DATE_TIME_PATTERN);
    }
    
    public static String formatNowStartDate() {
        return format(new Date(), DATE_PATTERN)+start_hours;
    }
    
    public static String formatNowEndDate() {
        return format(new Date(), DATE_PATTERN)+end_hours;
    }
    
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }
    
    public static String YYYYMMDD() {
    	return format(new Date(), DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    public static Date parse(String dateStr){
    	SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
        Date date = null;
        try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
    }
    
    /**
     * 获取今年
     * @return
     */
    public static String YYYY() {
        return format(new Date(), DATE_YEAR);
    }
    
    public static String monthFirstDayYYYYMMDD() {
		Calendar c = Calendar.getInstance();        
        c.add(Calendar.MONTH, -1);    
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天     
        String monthFirstDay = format.format(c.getTime());
        Date parse = parse(monthFirstDay);
		return  format(parse, DATE_PATTERN);
	}
    
    /**
     * 获取当前月第一天
     * @return
     */
	public static String monthFirstDay() {
		Calendar c = Calendar.getInstance();        
        c.add(Calendar.MONTH, 0);    
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天     
        String monthFirstDay = format.format(c.getTime());
		return monthFirstDay + start_hours;
	}
	
	/**
     * 获取当前月最后一天 
     * @return
     */
	public static String monthLastDay() {
		Calendar ca = Calendar.getInstance();        
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));      
        String monthLastDay = format.format(ca.getTime()); 
		return monthLastDay + end_hours;
	}
	
	public static String monthLastDayYYYYMMDD() {
		Calendar ca = Calendar.getInstance();        
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));      
        String monthLastDay = format.format(ca.getTime()); 
		return monthLastDay;
	}
    
    /**
     * 获取前一天
     * @return
     */
	public static String yesterday() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = (Date) calendar.getTime();
		return format(date, DATE_TIME_PATTERN);
	}
	
	/**
	 * 昨天开始
	 * @return
	 */
	public static String yesterdayStart() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = (Date) calendar.getTime();
        return format(date, DATE_PATTERN)+start_hours;
    }
    
	/**
	 * 昨天结束
	 * @return
	 */
    public static String yesterdayEnd() {
    	Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = (Date) calendar.getTime();
        return format(date, DATE_PATTERN)+end_hours;
    }
    
    public static String yesterdayYYYYMMDD() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 0);
		date = (Date) calendar.getTime();
		return format(date, DATE_PATTERN);
	}
    
    public static String yesterdayYYYYMMDD(int day) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		date = (Date) calendar.getTime();
		return format(date, DATE_PATTERN);
	}
    
    public static String yesterdayYYYYMMDD3() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -3);
		date = (Date) calendar.getTime();
		return format(date, DATE_PATTERN);
	}
    
    public static String monthFirst() {
		Calendar c = Calendar.getInstance();        
        c.add(Calendar.MONTH, 0);    
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天     
        String monthFirstDay = format.format(c.getTime());
		return monthFirstDay;
	}
    
    //根据string字符串日期返回年龄
    public static int getAge(String str) throws Exception {
		ArrayList<Integer> bir = new ArrayList<Integer>();
		int i = 0;
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		int age = 0;
		// System.out.println(year+" "+month+" "+date+"");
		try {
			str = str.trim();
			while (str.trim().charAt(0) == '-') {
				str = str.substring(1);
			}
			String temp[] = str.trim().split("-");
			if (temp.length == 3) {
				for (; i < 3; i++) {
					bir.add(Integer.parseInt(temp[i]));
				}
			}
			if (year - bir.get(0) > 0) {
				age = year - bir.get(0) - 1;
				if (month >= bir.get(1)) {
					if (date >= bir.get(2)) {
						age += 1;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("无法解析为年月日的结构");
			throw e;
		}
		return age;

	}

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }
    
    /**
     * 2010-01-01T00:00:00.000+08:00
     * 将获取到的结束日期+1秒作为起始时间
     * @param beginTime
     * @return
     */
    public static String beginTimeYYYYMMDDTHHMMSSSSSXXX(String beginTime) {
    	
    	SimpleDateFormat df = new SimpleDateFormat(YYYYMMDDTHHMMSSSSSXXX);
        Date date = null;
        try {
			date = df.parse(beginTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, 1);
		date = (Date) calendar.getTime();
		return format(date, YYYYMMDDTHHMMSSSSSXXX);
    }
    
    /**
     * 2010-01-01T00:00:00.000+08:00
     * 结束日期
     * @param beginTime
     * @return
     */
    public static String endTimeYYYYMMDDTHHMMSSSSSXXX() {
    	Date date = new Date();
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, 1);
		date = (Date) calendar.getTime();
		return format(date, YYYYMMDDTHHMMSSSSSXXX);
    }
    
    public static void main(String[] args) {
    	
        String format2 = format(new Date(), "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date1 = null;
        Date date = new Date();
        date1 = date;
        String format3 = format(date1, DATE_TIME_PATTERN);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, 1);
		date = (Date) calendar.getTime();
		String format4 = format(date, DATE_TIME_PATTERN);
        
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int doy = cal.get(Calendar.DAY_OF_YEAR);
 
        System.out.println("当期时间: " + cal.getTime());
        System.out.println("日期: " + day);
        System.out.println("月份: " + month);
        System.out.println("年份: " + year);
        System.out.println("一周的第几天: " + dow);  // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
        System.out.println("一月中的第几天: " + dom);
        System.out.println("一年的第几天: " + doy);
        
//        Map<String, Object> map = new HashMap<String, Object>();
//        Map<String, Object> mapData = new HashMap<String, Object>();
//        String sendPost = "{'content':[{'id':5,'name':'张三','month':'1月'},{'id':6,'name':'李四','month':'5月'},{'xl':'793','wjxaqjc':'6999','mvjgjg':'4789','dgddf':'4589','lkjkm':'78921','jhgsjf':'45689','qwert':'313','iop':'562','wjx2cj':'1113','wjxxxcj':'14113','wrkg':'535','tajj':'12','shbeyphcc':'1560','month':'8月'}]}";
//		JSONObject parseObject = JSONObject.parseObject(sendPost);
//		JSONArray  jsonArray = parseObject.getJSONArray("content");
//		for (int i = 0; i < jsonArray.size(); i++) {
//			JSONObject object = new JSONObject(true);
//			object = (JSONObject) jsonArray.get(i);
//			String monthStr = (String) object.get("month");
//			if(monthStr.equals(month+"月")) {
//				map.put("valueList", object);
//				mapData = object;
//				break;
//			}
//		}
//		List<Integer> list = new ArrayList<Integer>();
//		for (String key : mapData.keySet()) {
//			String keys = key;
//			if(!key.equals("month")) {
//				String object = (String) mapData.get(key);
//				list.add(Integer.parseInt(object));
//			}
//		}
//        Collections.reverse(list);
//        List<Integer> subList = list.subList(0, 10);
//        
//        List<Integer> valueList = new ArrayList<Integer>();
//        List<String> nameList = new ArrayList<String>();
//        for (String key : mapData.keySet()) {
//        	if(!key.equals("month")) {
//        		String object = (String) mapData.get(key);
//        		int parseInt = Integer.parseInt(object);
//        		if(subList.contains(parseInt)) {
//        			valueList.add(parseInt);
//        			nameList.add(key);
//        		}
//			}
//        }
    }
    
    /**
     * 当前时间所在一周的周一和周日时间
     * @param time 当前时间
     * @return
	 * @throws ParseException 
     */
    public static Map<String,String> getWeekDate() throws ParseException {
        Map<String,String> map = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 
         Calendar cal = Calendar.getInstance();  
         cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
         int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
         if(dayWeek == 1){
             dayWeek = 8;
         }
         //System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
 
         cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
         //2020-07-27 00:00:00
         Date mondayDate = cal.getTime();
         //Date mondayDate = sdf.parse("2020-07-27");
         String weekBegin = sdf.format(mondayDate);  
         //System.out.println("所在周星期一的日期：" + weekBegin);  
         cal.setTime(mondayDate);
 
         //2020-08-02 23:59:59	
         cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
         Date sundayDate = cal.getTime();
         //Date sundayDate = sdf.parse("2020-08-02");
         
         String weekEnd = sdf.format(sundayDate);  
         //System.out.println("所在周星期日的日期：" + weekEnd);
         int week = cal.get(Calendar.WEEK_OF_MONTH);
         //System.out.println("第     "+ week +"   周");
 
         map.put("mondayDate", weekBegin);
         map.put("sundayDate", weekEnd);
         map.put("weekDate", week+"");
        return map;
    }
    static final DateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    /**
     * 把yyyyMMddHHmmss格式字符串转换成 Date
     *
     * @return
     */
     public static Date getUtilDateTimeByShortStr(String datestr) {
        try {
            return YYYYMMDDHHMMSS_FORMAT.parse(datestr);
        } catch (ParseException e) {
            logger.info(e.getMessage(), e);
            return null;
        }
    }
     /**
      * 日期相减
      *
      * @param date 日期
      * @param date1 日期
      * @return 返回相减后的天数
      */
     public static int diffDate(Date date, Date date1) {
         return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
     }
     public static int diffDateToMonth(Date date, Date date1) {
         return (int) ((getMillis(date) - getMillis(date1)) / (30 * 24 * 3600 * 1000));
     }

     public static int diffDateToHour(Date date, Date date1) {
         return (int) ((getMillis(date) - getMillis(date1)) / (1000 * 60 * 60));
     }

     public static int diffDateToMinute(Date date, Date date1) {
         return (int) ((getMillis(date) - getMillis(date1)) / (1000 * 60));
     }
     /**
      * 返回毫秒
      *
      * @param date 日期
      * @return 返回毫秒
      */
     public static long getMillis(Date date) {
         Calendar c = Calendar.getInstance();
         c.setTime(date);
         return c.getTimeInMillis();
     }
     /**
      * 根据传入的日期格式(yyyyMMddHHmmss)返回yyyy-MM-dd HH:mm:ss格式日期
      *
      * @param date (yyyyMMddHHmmss)
      * @return yyyy-MM-dd HH:mm:ss日期
      */
     public static String getDefinableDate(String date) {
         if (date == null) {
             return "";
         }
         return getDefinableTime(getUtilDateTimeByShortStr(date), "yyyy-MM-dd HH:mm:ss");
     }
     /**
      * 返回指定时间的自定义格式String
      *
      * @param dateFormat
      * @return 自定义格式的日期
      *
      *         自定义支持的格式有： yyyy-MM-dd HH:mm:ss yyyy年MM月dd日 HH时mm分ss秒 yyyy年MM月dd日 HH时mm分 yyyy-MM-dd
      *         HH:mm yyyyMMddHH:mm:ss yyyy-MM-dd yyyyMMdd HHmmss yyyy年MM月dd日 HH:mm:ss" HH时mm分ss秒
      *         ......(很多)
      */
     public static String getDefinableTime(Date date, String dateFormat) {
         if (null == date || null == dateFormat || "".equals(dateFormat.trim())) {
             return "";
         }
         SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
         String dateString = formatter.format(date);
         return dateString;
     }
     
     
     /**
      * yyyyMMdd
      * 计算num天之前/之后的日期
      * @param beginTime
      * @return
      */
     public static String getDateByNum(Date date,int num,String format) {
        Calendar calendar = Calendar.getInstance();
 		calendar.setTime(date);
 		calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+ num);
 		date = (Date) calendar.getTime();
 		return format(date, format);
     }
}
