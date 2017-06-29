package top.hkfix.code;

public class StringComparison {
	/** 
	 * 判断字符串中是否仅包含字母数字和汉字 
	 */  
	public static boolean numAzChina(String str){  
	  String a = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";  
	  return str.matches(a);  
	}
	
	public static boolean stringBig(String str){
		if(str.length() >= 8){
			return true;
		}
		return false;
	}
	/** 
	 * 判断字符串中是否仅包含字母数字 
	 */  
	public static boolean numAz(String str){  
	  String regex = "^[a-z0-9A-Z]+$";  
	  return str.matches(regex);  
	}
	
	/** 
	 * 判断Email地址合法性的正则表达式
	 */  
	public static boolean email(String str){  
	  String regex = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]*@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
	  return str.matches(regex);  
	}
}
