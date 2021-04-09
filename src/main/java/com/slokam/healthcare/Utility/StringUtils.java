package com.slokam.healthcare.Utility;

public class StringUtils {
	
	public static boolean blackCheck(String name)
	{
		boolean flag =false;
		if(name != null && name.trim().length()>0)
		{
			flag =true;
		}
		return flag;
	}

}
