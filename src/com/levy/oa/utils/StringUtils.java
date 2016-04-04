package com.levy.oa.utils;

import java.util.regex.Pattern;

/*
 * 字符串工具类
 * */
public class StringUtils
{
	 /*
	  * 判断字符串str是否为空
	  * */
      public static boolean isEmpty(String str){
    	  return str==null||"".equals(str);
      }
      /*
 	  * 判断字符串str是否不为空
 	  * */
      public static boolean isNotEmpty(String str){
    	  return !isEmpty(str);
      }
      /*
 	  * 将字符串转为大写
 	  * */
      public static String toUpperCase(String str){
    	  return str==null?str:str.toUpperCase();
      }
      /*
  	  * 将字符串转为小写
  	  * */
       public static String toLowerCase(String str){
     	  return str==null?str:str.toLowerCase();
       }
       /*
   	  * 将字符串转为首字母大写，其他不变
   	  * */
        public static String toUpperCaseFirst(String str){
      	   if(str==null) return null;
      	   if("".equals(str)) return str;
      	   String first = String.valueOf(str.charAt(0));
      	   str.replaceFirst(first,first.toUpperCase());
      	   return str;
        }
        /*
      	  * 将字符串转为首字母小写，其他不变
      	  * */
           public static String toLowerCaseFirst(String str){
         	   if(str==null) return null;
         	   if("".equals(str)) return str;
         	   String first = String.valueOf(str.charAt(0));
         	   str.replaceFirst(first,first.toLowerCase());
         	   return str;
           }
           /**
       	 * 不会抛NullPointerException 的trim()
       	 *  用于去除首尾的空格
       	 * 传入null会返回null
       	 */
       	public static String trim(String str) {
       		return str == null ? null : str.trim();
       	}
       	/**
    	 * 过滤 ,把null和长度为0的""当成同一种情况处理; <br>
    	 * 当instr==null||"".equals(instr)时返回defaultValue ;其它情况返回 instr
    	 * 
    	 * @param instr
    	 * @param defaultValue
    	 * @return
    	 */
    	public static String nvl(String instr, String defaultValue) {
    		return instr == null || "".equals(instr) ? defaultValue : instr;
    	}
    	/**
    	 * 过滤 ;当instr==null时返回长度为0的""; <br>
    	 * 与 nvl(...)系的区别在于只处理null ,不处理长度为0的"";
    	 * 
    	 * @param instr
    	 * @return
    	 */
    	public static String nvl(String instr) {
    		return nvl(instr, "");
    	}
      /*
       * 比较字符串str1和str2，当两者都为空时返回ture，两者不为空时调用比较函数
       * 
       * **/
       public static boolean equals(String str1,String str2){
    	   if(str1==null&&str2==null) return true;
    	   if(str1!=null&&str1.equals(str2)) return true;
    	   return false;
       }
       /**
   	 * 清除str中出现的所有str2字符序列 直到结果中再也找不出str2为止 str2 == null时 返回str
   	 * @param str
   	 *            原始字符串
   	 * @param str2
   	 *            清除的目标
   	 * @return
   	 */
   	public static String clear(String str, String str2) {
   	     if(str==null) return str;
   	     if(str2==null) return str;
   	     String regex = "("+str2+")+";
   	     Pattern pattern = Pattern.compile(regex);
   	     while(pattern.matcher(str).find())
   	     {
   	    	 str.replaceAll(regex,"");
   	     }
   	     return str;
   	}
   	/**清除字符串中的空格
   	 * 
   	 * */
   	public static String clear(String str){
   		return clear(str,"");
   	}
   	/**
   	 *  如果Str的长度超过c，则Str取subString（0，c-sub.length（）），后面加上sub字符串
   	 * 
   	 **/
   	public static String suojin(String str, int c, String sub) {
		if (isEmpty(str))
			return str;
		if (str.length() <= c)
			return str;
		sub = nvl(sub);
		c = c - sub.length();
		c = c > str.length() ? 0 : c;
		str = str.substring(0, c);
		return str + sub;
	}
   	/**
	 * 如果str的长度超过了length,取前length位然后拼上...
	 * 
	 */
	public static String suojin(String str, int length) {
		return suojin(str, length,"…");
	}
	

    /**
     * 将撰写记录类型由中文转换成英文格式
     * 
     * @param ch
     * @return ch_en
     */
    public String convertChineseToEnglish(String ch) {
        String en = null;
            if(ch==null){
                ch="";
            }
        if (ch.equals("工作总结")) {
            en = "summary";
        } else if (ch.equals("工作计划")) {
            en = "plan";
        } else if (ch.equals("安排任务")) {
            en = "task";
        }
        return en;
    }


      
}
