package com.intalink.configoperations.utils;

import java.util.UUID;

/**
 * 
 * @描述 唯一主键生成工具类
 */
public class PrimaryKeyGenarator {
	
	static String[] chars = { "a", "b", "c", "d", "e", "f", 
		    "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", 
		    "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", 
		    "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", 
		    "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", 
		    "W", "X", "Y", "Z" };

	static String[] chars2 = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };
	
	/**
	 * 随机生成10位不重复的以SYS开头的随机数
	 * @return
	 */
	public static String getId(){
		StringBuffer shortBuffer = new StringBuffer();
	    String uuid = UUID.randomUUID().toString().replace("-", "");
	    for (int i = 0; i < 7; i++) {
	      String str = uuid.substring(i * 4, i * 4 + 4);
	      int x = Integer.parseInt(str, 16);
	      shortBuffer.append(chars[(x % 62)]);
	    }
	    return "SYS" + shortBuffer.toString();
	}

	/**
	 * 随机生成32位不重复大写字母
	 * @return
	 */
	public static String getId32(){
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		uuid=uuid+uuid+uuid+uuid+uuid;
		for (int i = 0; i < 32; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars2[(x % 26)]);
		}
		return shortBuffer.toString();
	}

	public static void main(String[] args) {
		for(int i=0;i<20;i++)
			System.out.println(getId());
	}
}
