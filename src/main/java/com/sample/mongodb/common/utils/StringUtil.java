package com.sample.mongodb.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringUtil {
    /**
     * String 의 값을 체크하여 null 일 경우 공백을 리턴한다.
     * @param str 객체
     * @return 문자열
     */
    public static String nvl(String str) {
        if (str == null || "".equals(str)) {
            str = "";
        }
        return str;
    }

    /**
     * String 의 값을 체크하여 null 일 경우 공백을 리턴한다.
     * @param str 객체
     * @return 문자열
     */
    public static String nvlWithTrim(String str) {
		if (str == null || "".equals(str.trim())) {
			str = "";
		}
		return str.trim();
    }

    /**
     * Object의 값을 체크하여 null 일 경우 대체 문자열을 리턴한다.
     *
     * @param str 체크 할 문자열
     * @param replaceStr 대체 문자열
     * @return 문자열
     */
    public static String nvlToReplaceStr(String str, String replaceStr) {
		if(str == null || "".equals(str)) {
			return replaceStr;
		} else {
			return str;
		}
	}

    /**
     * Object 의 값을 체크하여 숫자로 변환 후 리턴한다.
     * @param str 객체
     * @return 형변환(숫자) 리턴
     */
	public static int nvlInt(Object str) {
		try {
			if(str == null || "".equals(str)) {
				return 0;
			} else {
				return Integer.parseInt(str.toString());
			}
		} catch(Exception e) {
			return 0;
		}
	}

	/**
	 * Object 의 값을 체크하여 Double 숫자로 변환 후 리턴한다.
	 * @param str 객체
	 * @return 형변환(더블) 반환
	 */
    public static Double nvlDouble(Object str) {
        try {
            if(str == null || "".equals(str)) {
                return 0D;
            } else {
                return Double.parseDouble(str.toString());
            }
        } catch(Exception e) {
            return 0D;
        }
    }
 
	/**
	 * 입력된 스트링에서 cutLength 만큼 글자를 잘라준다.
	 *
	 * <pre>
	 *
	 * [사용 예제]
	 *
	 * shortCutString("하나둘셋넷", 6)	===> 하나둘
	 * shortCutString("abidingly", 6)	===> abidin
	 *
	 * </pre>
	 * @param strTarget
	 * @param cutLength
	 * @return
	 */
    public static String shortCutString(String strTarget, int cutLength) {
        try {
            if(strTarget == null) return null;

            int len = strTarget.length();
            int cnt = 0;
			int index = 0;
            while (index < len && cnt < cutLength) {
                if(strTarget.charAt(index++) < 256) cnt++;
                else cnt += 2;
            }

            if(index < len) strTarget = strTarget.substring(0, index);
        } catch (Exception e) {
            return null;
        }
        return strTarget;
    }

	/**
	 * 왼쪽으로 남은 자리수를 채운다
	 * @param strContext
	 * @param iLen
	 * @param strChar
	 * @return
	 */
    public static String setLPad( String strContext, int iLen, String strChar ) {
        String strResult = "";
        StringBuilder sbAddChar = new StringBuilder();
        for(int i = strContext.length(); i < iLen; i++) {
            sbAddChar.append( strChar );
        }
        strResult = sbAddChar + strContext;

        return strResult;
    }

	/**
	 * 오른쪽으로 남은 자리수를 채운다
	 * @param strContext
	 * @param iLen
	 * @param strChar
	 * @return
	 */
    public static String setRPad( String strContext, int iLen, String strChar ) {
        String strResult = "";
        StringBuilder sbAddChar = new StringBuilder();
        for(int i = strContext.length(); i < iLen; i++) {
            sbAddChar.append( strChar );
        }
        strResult = strContext + sbAddChar;

        return strResult;
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    
    /**
     * 이름 가운데 글자 마스킹
     * @param name
     * @return
     * @throws Exception
     */
    public static String maskingMiddleName(String name) throws Exception {
        // 한글만 (영어, 숫자 포함 이름은 제외)
        String regex = "(^[가-힣]+)$";
        Matcher matcher = Pattern.compile(regex).matcher(name);

        if(matcher.find()) {
            int length = name.length();

            String middleMask = "";
            if(length > 2) {
                middleMask = name.substring(1, length - 1);
            } else {
                middleMask = name.substring(1, length);
            }

            String dot = "";
            for(int i = 0; i < middleMask.length(); i++) {
                dot += "*";
            }

            if(length > 2) {
                return name.substring(0, 1) + middleMask.replace(middleMask, dot) + name.substring(length-1, length);
            } else {
                return name.substring(0, 1) + middleMask.replace(middleMask, dot);
            }
        }

        return name;
    }

	/**
	 * 주민번호를 마스킹 한다.
	 * @param str 주민번호
	 * @return 마스킹된 주민번호
	 */
    public static String maskingSSN(String str) {
        if(str == null) return "";

        String r = "";
        if(str.length() == 13) {
            String first = str.substring(0, 2);
            String middle = str.substring(6, 7);

            r = setRPad(first, 6, "*") + setRPad(middle, 7, "*");
        } else {
            int len = str.length();
            String first = str.substring(0, 2);

            r = setRPad(first, len, "*");
        }

        return r;
    }

	/**
	 * 전화번호를 암호화 한다.
	 * @param str 전화번호
	 * @return 마스킹된 전화번호
	 */
    public static String maskingTel(String str) {
        if(str == null) return "";

        String r = "";
        int len = str.length();

        if(len > 4) {
            String first = str.substring(0, len - 4);

            r = setRPad(first, len, "*");
        } else {
            r = str;
        }

        return r;
    }
}