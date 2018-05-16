package com.example.demo;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
	
	private static final Logger log = LoggerFactory.getLogger(StringUtil.class);
	
	public static String getAlertMessage(String msg){
   		msg = stringReplace(msg, "~'", "\\");
   		return "alert('" + msg + "');\n";
	}
	
	/**
	 * 문자열을 원본에서 바꾸어야 될 문자열을 찾아 str3로 바꾼다.
	 * @param String str1 : 원본 문자열
	 * @param String str2 : 바꾸어야 될 문자열
	 * @param String str3 : 바꿀 문자열
	 * @return String str1 : 바뀐 문자열
	 */
	public static String stringReplace(final String str1, final String str2, final String str3) 
	{
        if(str1 == null) return "";
        else return stringReplace( str1, str2, str3, 0 , str1.length());
	}
	
	/**
	*	Method : 구간을 정하여 문자열을 치환한다.
	*	@param String str1 : 원본 문자열
	*	@param String str2 : 바꾸어야 될 문자열
	*	@param String str3 : 바꿀 문자열
	*	@param start int: 바꿀 문자열의 시작 위치
	*	@param end	 int    : 바꿀 문자열의 마지막 위치
	*	@return String str1 : 바뀐 문자열
	*/
	public static String stringReplace( final String str, final String pattern, final String replace, final int start, final int end) 
	{
		if( str == null ) return "";
		StringBuffer result = new StringBuffer();
		int s = start;
		int e = end;
		 

		 while ((e = str.indexOf(pattern, s)) >= 0) {
		   result.append(str.substring(s, e));
		   result.append(replace);
		   s = e+pattern.length();
		 }
		 result.append(str.substring(s));
		 return result.toString();
	}

	/**
	 * String 이 null 일 경우를 check하고 null 이면 공백을 리턴한다.
	 * 작성 날짜: (2000-07-28 오후 1:47:48)
	 * @return java.lang.String
	 * @param str java.lang.String
	 */
	public static String nullTrim( final String str) {
		return str==null?"":str.trim();
	}
	
	/**
	*	Method : 문자열에서 특정 문자를 제거한다.
	*	@param 	String str : 제거할 문자가 들어간 문자열
	*	@param 	char rmChar : 제거되어야할 문자
	*	@return 	String str : rmChar이 제거된 문자열
	*/
  public static String removeChar( String str, final char rmChar ) 
  {
		int index = 0;
		int start = 0;

		if( str ==null ) return null;
		
		StringBuffer tempBuf = new StringBuffer();

		while( true ) 
		{	
				 index = str.indexOf( rmChar, start );

				if( index == -1 ) {
					if ( start == 0 )	
					{
						return str;
					} 
					else 
					{
						tempBuf.append( str.substring(start) );
					}

					break;
				}
				tempBuf.append( str.substring( start, index));
				start  = index+1;
				
			}

			return tempBuf.toString();
	}
  
  	
  /**
	 * 파라메터로 넘오온 문자열에 대한 인코딩을 설정한다.
	 * @param paramValue
	 * @return
	 */
	public static String param(String paramValue){
//		return paramValue==null?"":convertCharSet( paramValue, "8859_1", "KSC5601");
		return paramValue==null?"":paramValue;
	}
	
	public static String param(String paramValue, String paramValue1){
		return paramValue==null||("".equals(paramValue))?paramValue1:
														convertCharSet( paramValue, "8859_1", "KSC5601");
//		return paramValue==null?"":paramValue;
	}
	
	/**
	 * 기 능 : 문자열 원하는 문자셋 코드로 만든다.
	 *
	 * @param String source : 전환되어야 할 문자열
	 * @param String decoder : 현재 문자 코드셋
	 * @param String encoder : 전환될 문자 코드셋
	 * @return String : 문자 코드셋이 바뀐 문자열.
	 */
	public static String convertCharSet( final String source, final String decoder, final String encoder ) 
	{
		try 
		{
			return new String( source.getBytes( decoder),encoder);
		}
		catch(java.io.UnsupportedEncodingException ce) {
			return  "";
		}
	}
	
	 /**
     * str의 값에서 "."를 기준으로 앞자리(코드값)을 리턴.
     * <p>
     * 사용 예 : StringUtil.getPickupCode("10.코드값");  ==> 결과 : "10"
     * @param str 원본문자열
     */
    public static String getPickupCode(String str) {
        if ( str == null ) return "";

        int nGubnIdx = str.indexOf(".");

        if ( nGubnIdx == -1 ) return str;
        else {
            str = str.substring(0, nGubnIdx);
        }
        return str;
    }

    /**
     * str의 값에서 "."를 기준으로 뒷자리(코드설명)을 리턴.
     * <p>
     * 사용 예 : StringUtil.getPickupText("10.코드값");  ==> 결과 : "코드값"
     * @param str 원본문자열
     */
    public static String getPickupText(String str) {
        if ( "".equals(StringUtil.nullTrim(str)) ) return "";

        int nGubnIdx = str.indexOf(".");

        if ( nGubnIdx == -1 ) return str;
        else {
            str = str.substring(nGubnIdx+1);
        }
        return str;
    }
    
    public static String removeChars( String str, final String rmChars ) 
    {
    	String retStr = str;
    	for(int i=0; i<rmChars.length(); i++) {
    		retStr = removeChar(retStr, rmChars.charAt(i));
    	}
    	return retStr;
    }
    
    /**
	 * 문자열이 null 혹은 ""면 true를 반환한다.
	 * 
	 * @param s
	 *            검사할 문자열
	 * @return
	 */
	public static boolean isEmpty(String s) {

		if (s == null)
			return true;

		String rVal = s.trim();

		return ("".equals(rVal)) ? true : false;
	}
	
	  /**
     * Method Desc : 배열변수를 받아 문자 삽입후 리턴
     * @author EKW <kwjojo@crizen.com>
     * @since 2010. 10. 8.
     * @param values 배열 변수 전화번호, 우편번호등.. [011,0000,0000]
     * @param ch 삽입할 특정문자 "-"
     * @return 011-0000-0000
     */
    public static String addChar(String[] values, String ch) {
    	String returnValue = "";
    	if ( values.length < 1 ) return returnValue;
    	
    	for ( int i=0; i<values.length; i++ ) {
    		if ( isEmpty(values[i]) ) return "";
    		
    		if ( i == 0 ) {
    			returnValue += nullTrim(values[i]);
    		}
    		else {
    			returnValue += ch + values[i];
    		}
    		 
    	}
    	return returnValue;
    }
    
    /**
     * str의 값에서 "."를 기준으로 앞자리(코드값)을 리턴.
     * <p>
     * 사용 예 : StringUtil.getPickupCode("10.코드값");  ==> 결과 : "10"
     * @param str 원본문자열
     */
    public static String getPickupFalst(String str) {
        if ( str == null ) return "";

        int nGubnIdx = str.indexOf(".");

        if ( nGubnIdx == -1 ) return str;
        else {
            str = str.substring(0, nGubnIdx);
        }
        return str;
    }
    
    /**
     * str의 값에서 "."를 기준으로 뒷자리(코드설명)을 리턴.
     * <p>
     * 사용 예 : StringUtil.getPickupText("10.코드값");  ==> 결과 : "코드값"
     * @param str 원본문자열
     */
    public static String getPickupLast(String str) {
        if ( "".equals(StringUtil.nullTrim(str)) ) return "";

        int nGubnIdx = str.indexOf(".");

        if ( nGubnIdx == -1 ) return str;
        else {
            str = str.substring(nGubnIdx+1);
        }
        return str;
    }
    
    /**
	  * 
	  * 2009. 11. 17. : YYS <ysyang@crizen.com>
	  * @param str
	  * @return
	  * @throws Exception
	  * Method Desc : 한글 인코딩
	  */
	 public static String encodingHangul(String str) throws Exception{
		return str==null?"":new String(str.getBytes("8859_1"), "EUC-KR");
	 }
	 
	 /**
	  * 
	  * 2009. 11. 18. : YYS <ysyang@crizen.com>
	  * @param replace
	  * @return
	  * Method Desc : 지정한 컬럼의 데이터에서 특수문자(',",\)를 제거
	  */
	 public static Object replaceColumnData(String replace, HttpServletRequest request){
		 Object replaceStr = "";
		 try{
			 // 얻어온 컬럼에 해당하는 Value값이 있을 경우
			 if(!StringUtil.isEmpty(request.getParameter(replace.trim()))){
				 
				 // 해당 Value값이 배열이 아닐 경우
				 if ( request.getParameterValues(replace.trim()).length == 1 ) {
					 	//replaceStr = encodingHangul(request.getParameter(replace.trim()));	// 한글 인코딩
					 replaceStr = request.getParameter(replace.trim());
					 replaceStr = getReplaceStr((String)replaceStr);	// 특수문자 변환(',",\)
				 }
				 // 해당 Value값이 배열일 경우
				 else {
					 String[] tmpStr = request.getParameterValues(replace.trim());
					 for(int j=0; j<tmpStr.length; j++){
						 	//tmpStr[j] = encodingHangul(tmpStr[j]);	// 한글 인코딩
						 tmpStr[j] = encodingHangul(tmpStr[j]);	// 한글 인코딩
						 tmpStr[j] = getReplaceStr((String)tmpStr[j]);	// 특수문자 변환(',",\)
					 }
					 
					 replaceStr = tmpStr;
				 }
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		 }
		 
		 return replaceStr; 
	 }
	 
	 /**
	  * 
	  * 2009. 11. 16. : YYS <ysyang@crizen.com>
	  * @param str
	  * @return
	  * Method Desc : BM 신청서 특수문자 변환 (' , " , \)
	  */
	 public static String getReplaceStr(String str){
		if(str != null || !str.equals("")){
			str = str.replace("\"", "");
			str = str.replace("\'", "");
			str = str.replace("\\", "");
		}
		return str;
	 }
	
	 /**
	 * Method Desc : cValue 가 null 이거나 공백일 경우 rValue 를 리턴한다.
	 * 
	 * @author EKW <kwjojo@crizen.com>
	 * @since 2010. 9. 30.
	 * @param cValue
	 * @param rValue
	 * @return
	 */
	public static String NVL(String cValue, String rValue) {
		if (isEmpty(cValue)) {
			return rValue;
		} else {
			return cValue;
		}
	}

	public static boolean isNull(String property) {
		return (property==null||"".equals(property)) ? true : false;

	}
	
	/**
	 * Method Desc : 구분자를 기준으로 index 
	 * 2011. 7. 27. kwjojo < kwjojo@crizen.com>
	 * @update:
	 * @param str
	 * @param token
	 * @param index
	 * @return
	 */
	public static String getStrIndex(String str, String token, int index) {
		if ( isEmpty(str) ) return "";
		
		String[] arrTmp = str.split("\\"+token);
		
		if ( index >= arrTmp.length ) return "0";
		
		return arrTmp[index];
	}
	
	public static String getRtoMidFee(String infoRto, String rtoKind, int index) {
		if ( isEmpty(infoRto) ) return ""; 
		
		String[] tmp = infoRto.split(",");
		HashMap<String, String> map = new HashMap<String, String>();
		
		for ( String value : tmp ) {
			map.put(value.split("=")[0], value.split("=")[1]);
		}
		return getStrIndex(map.get(rtoKind), "|", index);
    }
	
	/**
	 * Method Desc : 입력받은 값을 주민번호 형태로 변환하여 리턴한다.
	 * @author EKW <kwjojo@crizen.com>
	 * @since 2010. 9. 28.
	 * @param ssn
	 * @return
	 */
	public static String formatSsn(String ssn) {
	   	if ( ssn == null || ssn.equals("") ) return "";
	   	
	   	if ( ssn.length() == 13 ) {
	   		return ssn.substring(0, 6) + "-" + ssn.substring(6);
	   	}
	   	else return ssn;    	
	}
	
    /**
     * 주민번호의 특정 위치의 값을 리턴한다.
     * 사용 예 : ${ufn:getSsn("1234561234567", 2)} -> 리턴 값 : "1234567"
     * 인덱스 의미 : 1 - 주민번호 앞자리, 2 - 주민번호 뒷자리
     * @param ssn
     * @param idx
     * @return
     */
    public static String getSsn(String ssn, int idx) {
    	String returnString = "";
    	if ( "".equals(StringUtil.nullTrim(ssn)) ) return "";
    	
    	if ( ssn.length() == 13 ) {
    		if ( idx == 1 ) {
    			returnString = ssn.substring(0, 6);
    		}
    		else if ( idx == 2 ) {
    			returnString = ssn.substring(6);
    		}
    		else returnString = "인덱스지정오류";
    	}
    	else {
    		if ( ssn.length() < 7 ) {
    			if ( idx == 1 ) {
    				returnString = ssn.substring(0, ssn.length());
    			}
    			else {
    				returnString = "";
    			}
    		}
    		else {
    			if ( idx == 1 ) {
    				returnString = ssn.substring(0, 6);
    			}
    			else if ( idx == 2 ) {
    				returnString = ssn.substring(6, ssn.length());
    			}
    		}
    	}
    	
    	return returnString;
    }	
    /**
     * 전화번호의 특정 위치의 값을 리턴한다.
     * 사용 예 : ${ufn:getTelNo("0212345678", 3)} -> 리턴 값 : "5678"
     * 인덱스 의미 : 1 - 국번, 2 - 전화번호 중간자리, 3 - 전화번호 마지막자리
     * @param telNo
     * @param idx
     * @return
     */
    public static String getTelNo(String telNo, int idx) {
    	String returnValue = "";
    	
    	if ( telNo == null ) return "";
    	
    	try {
	    	String[] arrTmp = telNo.split("-");
	    	returnValue = arrTmp[idx-1];
    	}
    	catch ( Exception e ) {
    		returnValue = "";
    	}
    	
    	return returnValue;
    }
    /**
	 * lpad 함수
	 * 
	 * @param str
	 *            원본문자열
	 * @param sizeByte
	 *            문자열길이
	 * @param charPad
	 *            padding 할 문자
	 * @return
	 */
	public static String lpadByte(String str, int sizeByte, String charPad) {

		if (isEmpty(charPad)) {
			charPad = " ";
		}

		int strLen = str.getBytes().length;

		if (strLen < sizeByte) {
			String sResult = "";
			for (int icnt = 0; icnt < (sizeByte - strLen); icnt++) {
				sResult += charPad;
			}
			return sResult + str;
		} else if (strLen == sizeByte) {
			return str;
		} else {
			byte[] ret = new byte[sizeByte];
			byte[] byteStr = str.getBytes();

			for (int i = 0; i < sizeByte; i++) {
				ret[i] = byteStr[i];
			}

			return new String(ret);

		}
	}
	
	/**
     * 
     * Method Desc : 입력받은 값을 전화번호 형식으로 리턴
     * 2010. 11. 29. kwjojo < kwjojo@crizen.com>
     * @update:
     * @param telNo
     * @return
     */
    public static String formatTelNo(String telNo) {
    	if ( telNo == null || telNo.equals("") ) return "";
    	
    	if ( telNo.length() == 7 ) {
    		return telNo.substring(0, 3) + "-" + telNo.substring(3);
    	}
    	else if ( telNo.length() == 8 ) {
    		return telNo.substring(0, 4) + "-" + telNo.substring(4);
    	}
    	else if ( telNo.length() == 9 ) {
    		return telNo.substring(0, 2) + "-" + telNo.substring(2, 5) + "-" + telNo.substring(5);
    	}
    	else if ( telNo.length() == 10 ) {
    		if ( telNo.substring(0, 2).equals("02") ) { 
    			return telNo.substring(0, 2) + "-" + telNo.substring(2, 6) + "-" + telNo.substring(6);
    		}
    		else {
    			return telNo.substring(0, 3) + "-" + telNo.substring(3, 6) + "-" + telNo.substring(6);
    		}	
    	}
    	else if ( telNo.length() == 11 ) {
    		return telNo.substring(0, 3) + "-" + telNo.substring(3, 7) + "-" + telNo.substring(7); 
    	}
    	else return telNo;
    }
    
    /**
	 * Method Desc : 개행 및 특수 문자를 포함한 Text 를 웹페이지 맞게 변환해준다. 
	 * 2011. 7. 10. dhkim < dhkim@crizen.com>
	 * @update:
	 * @param txt
	 * @param doQuotes  true 인 경우 작은,쌍 따옴표 또한 변환한다. 
	 * @return
	 */
	public static String txt2WebTxt(String txt, boolean doQuotes) {
		log.debug("★★★★★개행 및 특수 문자를 포함한 Text 를 웹페이지 맞게 변환해준다.★★★★★["+txt+"]["+doQuotes+"]");
		if (txt == null) {
			return txt;
		}
		int len = txt.length();
		StringBuffer buf = new StringBuffer(len + 50);
		char c = '\0';

		for (int i = 0; i < len; i++) {
			c = txt.charAt(i);
			if (doQuotes && c == '"')
				buf.append("&quot;");
			else if (doQuotes && c == '\'')
				buf.append("&#039;");
			else if (c == '&')
				buf.append("&amp;");
			else if (c == '<')
				buf.append("&lt;");
			else if (c == '>')
				buf.append("&gt;");
			else
				buf.append(c);
		}
		
		//시스템 상의 차이로 Unix와 Window 간의 캐리지리턴값이 틀림.
		String rtn_str = buf.toString().replaceAll("\r\n", "<br>"); //Unix상의 CarrigeReturn
		rtn_str = rtn_str.replaceAll("\n", "<br>");
				
		
		return rtn_str;
	}

    /**
     * Method Desc : 은행 계좌번호 형식에 맞게 "-" 를 붙여서 리턴한다 
     * 2010. 12. 9. dhkim < dhkim@crizen.com>
     * @update:
     * @param c3_bank	금결원 은행코드 
     * @param account
     * @return
     */
    public static String formatBankAccount(String c3_bank, String account) {
		if (isEmpty(account)) 
			return "";
		
		// 저축은행 
		if ("050".equals(c3_bank) && account.length()== 14) {
			return account.substring(0, 3) + "-" + account.substring(3, 5) + "-" + account.substring(5, 7) + "-" + account.substring(7);			
		}else if ("020".equals(c3_bank) && account.length()== 14) {
			return account.substring(0, 6) + "-" + account.substring(6, 8) + "-" + account.substring(8);			
		} else if(account.length() == 15){
			account =  account.substring(0, 2) + "-" + account.substring(2, 6) + "-" + account.substring(6,13) + "-" + account.substring(13,15);			
		}
		
		return account;
	}
    
    /**
     * @Method Name  : getBgn
     * 2013. 5. 22. bwko <bwko@crizen.com>
     * @Method 설명 : 주민번호를 입력받아 생년월일성별 을 리턴 ex) 8016241125489 -> 198006241
     * @param ssn
     * @return 
     */
    public static String getBgn(String ssn, int size) {

		if(isEmpty(ssn)) return "";

		ssn = ssn.replaceAll("-", "");	//'-' 제거

		//주민번호 숫자여부 체크
		try {
			Double.parseDouble(ssn);
		} catch (Exception e) {
			return "";
		}

		//주민번호 전체 자리수 체크
		int len = ssn.length();
		if(len < 7) return "";

		String bgn = "";
//
//		int key = NumberUtil.parseInt(ssn.substring(6, 7));
//
//		switch (key) {
//		case 1:
//		case 2:
//		case 5:
//		case 6:
//			bgn = "19"+ ssn.substring(0, size);
//			break;
//		case 3:
//		case 4:
//		case 7:
//		case 8:
//			bgn = "20"+ ssn.substring(0, size);
//			break;
//
//		default:
//			bgn = "18"+ ssn.substring(0, size);
//			break;
//		}

		return bgn;
	}
    
    /**
     * str의 값에서 "."를 기준으로 앞자리(코드값)을 리턴.
     * <p>
     * 사용 예 : StringUtil.getPickupCode("10.코드값");  ==> 결과 : "10"
     * @param str 원본문자열
     */
    public static String getPickupFirst(String str) {
        if ( str == null ) return "";

        int nGubnIdx = str.indexOf(".");

        if ( nGubnIdx == -1 ) return str;
        else {
            str = str.substring(0, nGubnIdx);
        }
        return str;
    }
}
