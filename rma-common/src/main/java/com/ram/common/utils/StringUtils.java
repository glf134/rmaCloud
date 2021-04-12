package com.ram.common.utils;

/**
 * @author glf
 */
public class StringUtils {
    /**
     * 把struid第四位+1后返回
     * @param struid
     * @return
     */
    public static String updateStruIdValue(String struid,int sum){
        // 系统约定：StruID应该是其父节点的StruID
        String beforelast4 = "";
        String last4 = "";
        if (struid.length() > 4) {
            beforelast4 = struid.substring(0, struid.length() - 4);
            last4 = struid.substring(struid.length() - 4,
                    struid.length());
        } else {
            last4 = struid;
        }
        String currentStruID = "";
        int i = Integer.parseInt(last4,36) + sum;
        currentStruID += Integer.toString(i, 36);
        if (currentStruID.length()==1) {
            currentStruID =  "000" + currentStruID;
        } else if (currentStruID.length()==2) {
            currentStruID =  "00" + currentStruID;
        } else if (currentStruID.length()==3) {
            currentStruID =  "0" + currentStruID;
        }
        return beforelast4+currentStruID;
    }
    /**
     * 去除字符中的html的字符
     * @return
     */
    public static String getRemoveHtmlString(String str){
        if(StringUtils.isNotEmpty(str)){
            int startIndex = 0;
            int endIndex = 0;
            while((startIndex = str.indexOf('<'))!= -1 && (endIndex = str.indexOf('>'))!= -1){
                String calculFormulaName = str.substring(startIndex,endIndex+1);
                str = str.replace(calculFormulaName, "");
            }
        }
        return str;
    }
    /**
     * 判断字符串是否为 null 或为空字符串。
     */
    public static final boolean isEmpty(String s) {
        return (s == null || s.trim().length() == 0);
    }
    public static final boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * 判断两字符串是否严格相等（包括字符串为 null 为空字符串的情况）。
     */
    public static boolean equals(String s1, String s2) {
        if (isEmpty(s1)) {
            return isEmpty(s2);
        }
        return s1.equals(s2);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    public static String replace(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, -1);
    }
    public static String replace(String text, String searchString, String replacement, int max) {
        return replace(text, searchString, replacement, max, false);
    }

    private static String replace(String text, String searchString, String replacement, int max, boolean ignoreCase) {
        if (!isEmpty(text) && !isEmpty(searchString) && replacement != null && max != 0) {
            String searchText = text;
            if (ignoreCase) {
                searchText = text.toLowerCase();
                searchString = searchString.toLowerCase();
            }

            int start = 0;
            int end = searchText.indexOf(searchString, start);
            if (end == -1) {
                return text;
            } else {
                int replLength = searchString.length();
                int increase = replacement.length() - replLength;
                increase = increase < 0 ? 0 : increase;
                increase *= max < 0 ? 16 : (max > 64 ? 64 : max);

                StringBuilder buf;
                for(buf = new StringBuilder(text.length() + increase); end != -1; end = searchText.indexOf(searchString, start)) {
                    buf.append(text, start, end).append(replacement);
                    start = end + replLength;
                    --max;
                    if (max == 0) {
                        break;
                    }
                }

                buf.append(text, start, text.length());
                return buf.toString();
            }
        } else {
            return text;
        }
    }
}