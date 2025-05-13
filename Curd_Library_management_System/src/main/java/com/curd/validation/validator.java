package com.curd.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validator
{

	static String NameRegex				=		"^[A-Za-z][A-Za-z_ ]{2,25}$";
	static String userNameRegex 		=		"^[A-Za-z][A-Za-z0-9_ ]{3,25}$";
	static String passwordRegex			=		"^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
	static String cheackDigitRegex		=		"^[1-9]\\d*$";
	static String mobileNumberRegex		=		"^[6-9]\\d{9}$";
	
	
	static Pattern	NamePattern			= Pattern.compile(NameRegex);
	static Pattern	mobileNumberPattern	= Pattern.compile(mobileNumberRegex);
	static Pattern	userNamePattern		= Pattern.compile(userNameRegex);
	static Pattern	passwordPattern		= Pattern.compile(passwordRegex);
	static Pattern	digitPattern		= Pattern.compile(cheackDigitRegex);
	
	
	public static boolean validateUserName(String userName)
	{
		Matcher matcher = userNamePattern.matcher(userName);
		if(matcher.find())
		{
			return true;
		}
		return false;
	}
	
	public static boolean validatePassword(String password)
	{
		Matcher matcher = passwordPattern.matcher(password);
		if(matcher.find())
		{
			return true;
		}
		return false;
	}
	
	public static boolean validateInputDigit(String number)
	{
		Matcher matcher = digitPattern.matcher(number);
		if(matcher.find())
		{
			return true;
		}
		return false;
	}
	
	public static boolean validateMobileNumber(String MobileNumber)
	{
		Matcher matcher = mobileNumberPattern.matcher(MobileNumber);
		if(matcher.find())
		{
			return true;
		}
		return false;
	}
	
	
	public static boolean validateFullName(String name)
	{
		Matcher matcher = NamePattern.matcher(name);
		if(matcher.find())
		{
			return true;
		}
		return false;
	}
	
	
	
	
	
}
