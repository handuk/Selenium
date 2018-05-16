package com.example.demo;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/** Method Desc :
 * @author LKH <khlim@crizen.com>
 * @since 2010. 1. 6.
 */
public class DateUtil {
	
	/**
	 * 기본 입력 일자시간 포멧
	 */    
    public static final String DEFAULT_DIPLAY_DATE_YYYYMMDD = "yyyyMMdd";
    
    private static final String[] day = {"일", "월", "화", "수", "목", "금", "토" };
    
	public static String getCurrentYMD(){
		Date d =new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(d);
	}
	
	public static String getCurrentHIS(){
		Date d =new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		return sdf.format(d);
	}
	
	 /**
     * 날짜 포맷을 yyyy-MM-dd 형식으로 리턴한다.<br>
     * 사용 예 : ${ufn:formatDate("20061212")} -> 리턴 값 : 2006-12-12 
     * @param date
     * @return
     */
    public static String formatDate(String date) {
    	if(date == null || "".equals(date)){
    		return "";
    	}
    	else if(date.length() != 8){
    		return date;
    	}
    	else if(date.indexOf(" ") > 0){
    		return date;
    	}
    	else{
    		return formatDate(date, "yyyy/MM/dd");
    	}
    }
    
    
    public static String formatDateFormat(String date, String format) {
    	if ( "".equals(StringUtil.nullTrim(format)) ) {
    		format = "dd/MM/yyyy";
    	}
    	
    	date = StringUtil.nullTrim(date);
    	
    	if ( date == null || date.equals("") ) return "";
    	if ( date.equals( "00000000" ) ) return "";
    	
    	return formatDate(date, format);
    }
    
    public static String formatDate(String date, String format) {
    	if ( "".equals(StringUtil.nullTrim(format)) ) {
    		format = "yyyy-MM-dd";
    	}
    	
    	date = StringUtil.nullTrim(date);
    	
    	if ( date == null || date.equals("") ) return "";
    	if ( date.equals( "00000000" ) ) return "";
    	
    	SimpleDateFormat formatter = new SimpleDateFormat(format);
    	return formatter.format(chkDate(date, "yyyyMMdd"));
    }
    
    /**
     * 일자를 체크한다.
     * @param _date
     * @param format
     * @return
     */
    private static Date chkDate(String inputDate, String format) {
    	String _date = null;
    	_date = inputDate;
    	
    	if (_date == null) return null;
    	else {
    		_date = StringUtil.removeChar(inputDate, '-');
    		_date = StringUtil.removeChar(_date, '/');
    		_date = StringUtil.removeChar(_date, '.');
    		_date = StringUtil.removeChar(_date, ':');
    	}
    	
    	SimpleDateFormat formatter = new SimpleDateFormat(format, java.util.Locale.KOREA);
    	Date date = null;
    	
    	try {
    		date = formatter.parse(_date);
    	}
    	catch ( ParseException e ) {
    		return null;
    	}
    	
    	if ( !formatter.format(date).equals(_date) ) return null;
    	
    	return date;
    }
    
    /**
	 * Compute of DateDiff 
	 * @param dateTime basic date
	 * @param addDAy add day
	 * @return
	 */
	public static int DateDiff(String preDate, String nextDate) throws Exception {
		
		SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DIPLAY_DATE_YYYYMMDD);
		java.util.Date PreDate = formatter.parse(preDate, new ParsePosition(0));
		java.util.Date NextDate = formatter.parse(nextDate, new ParsePosition(0));
		
		if(PreDate==null) throw new Exception("날짜타입이 일치하지 않습니다. :: YYYYMMDD");
		if(NextDate==null) throw new Exception("날짜타입이 일치하지 않습니다. :: YYYYMMDD");

		return (int)((NextDate.getTime() - PreDate.getTime()) / (1000*60*60*24));
	}
	
	/**
	 *
	 * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
	 *
	 * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with  your pattern.
	 */
	public static int getNumberByPattern(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(new java.util.Date());
		return Integer.parseInt(dateString);
	}
	
 	/**
    * 현재날짜의 년도를 구하는 Method
    *
    * @param
    * @exception
    * @author
    */
	public static int getYear() { return getNumberByPattern("yyyy"); }
	
	/**
	 * Method Desc : startDate 와 endDate 사이의 일수를 구한다.
	 * 2010. 10. 17. dhkim < dhkim@crizen.com>
	 * @update:
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDiffDayCount(String startDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			return (int) ((sdf.parse(endDate).getTime() - sdf.parse(startDate).getTime()) / 1000 / 60 / 60 / 24);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * Add day to date 
	 * @param dateTime basic date
	 * @param addDAy add day
	 * @return
	 */
	public static String addDay(String dateTime, int addDAy){

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		ParsePosition pos = new ParsePosition(0);
		java.util.Date date = formatter.parse(dateTime, pos);

		Calendar cd = Calendar.getInstance(Locale.KOREAN);
				cd.setTime(date);
				cd.add(Calendar.DATE,addDAy);
	
		return formatter.format(cd.getTime());

	}
	/**
	 * 입력받은 기준일로 부터 month 월 수 만큼 더한다 
	 * @param dateTime
	 * @param addMonth
	 * @return
	 */
	public static String addMonth(String dateTime, int month) {
		SimpleDateFormat formatter_ = new SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		Calendar calendar = Calendar.getInstance(java.util.Locale.KOREA);
		java.util.Date _date = null;
		
		try {
			_date = formatter_.parse(dateTime);
		}
		catch ( Exception e ) {
			e.printStackTrace();
			return null;
		}
		
		calendar.setTime(_date);
		calendar.add(Calendar.MONTH, month);
		return formatter_.format(calendar.getTime());
	}
	
	/**
	 * Add year to date
	 * @param dateTime basic date
	 * @param addYear add year
	 * @return
	 */
	public static String addYear(String dateTime, int addYear){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		java.util.Date date = formatter.parse(dateTime, pos);
	
		Calendar cd = Calendar.getInstance();
				 cd.setTime(date);
				 cd.add(Calendar.YEAR,addYear);
		return formatter.format(cd.getTime());
	}	
	
    /**
     * 현재 날짜를 yyyy-MM-dd 형식으로 리턴한다.<br>
     * 사용 예 : ${ufn:currentDate()} -> 리턴 값 : 2006-12-12
     * @return
     */
    public static String currentDate() {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	return formatter.format(new Date());
    }
    
 	public static int getDayOfWeek(String yyyy, String mm, String dd) {
		Calendar Cal = Calendar.getInstance();   
		Cal.set(Integer.parseInt(yyyy),Integer.parseInt(mm)-1,Integer.parseInt(dd));				
		return getDayOfWeek( Cal );  
	} 
 	
	public static int getDayOfWeek(Calendar cal ) {
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY: return 0;
			case Calendar.MONDAY: return 1;
			case Calendar.TUESDAY: return 2;
			case Calendar.WEDNESDAY: return 3;
			case Calendar.THURSDAY: return 4;
			case Calendar.FRIDAY: return 5;
			case Calendar.SATURDAY: return 6;
			default: return 7;
		}
	}
	
  public static String getDayOfWeek(String ymd) {
		Calendar cal = Calendar.getInstance();   
		cal.set(Integer.parseInt(ymd.substring(0,4)),Integer.parseInt(ymd.substring(4,6))-1,Integer.parseInt(ymd.substring(6,8)));
		
		return day[cal.get(java.util.Calendar.DAY_OF_WEEK)-1];  
	 }
	
  public static String formattedDate(String date, String fromFormatString, String toFormatString){
		SimpleDateFormat fromFormat = new SimpleDateFormat(fromFormatString);
		SimpleDateFormat toFormat = new SimpleDateFormat(toFormatString);
		Date fromDate = null;
		
		if(StringUtil.isEmpty(date)){
			return "";
		}
		
		try{
			fromDate = fromFormat.parse(date);
		}catch(ParseException e){
			fromDate = new Date();
		}
		return toFormat.format(fromDate);
	}
  
    public static void main(String[] args) {
    	String ymd = "2015-03-14";
    	String format = "yyyyMMdd";
    	
    	System.out.println( formatDateFormat(ymd, format));
    	
	}
}
