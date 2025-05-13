package com.curd.model;

public class Member
{
	private int		member_Id;
	private String	full_Name;
	private String	username;
	private String	password;
	private String	mobile_Number;
	private String	adminOrUser_Flag;

	
	
	
	
	public Member(int memberId, String fullName, String mobileNumber, String username)
	{
		this.member_Id = memberId;
		this.full_Name = fullName;
		this.username = username;
		this.mobile_Number = mobileNumber;
	}

	public Member()
	{
	}

	public Member(int memberId, String fullName, String mobileNumber)
	{
		this.member_Id = memberId;
		this.full_Name = fullName;
		this.mobile_Number = mobileNumber;
	}

	public int getMember_Id()
	{
		return member_Id;
	}

	public void setMember_Id(int member_Id)
	{
		this.member_Id = member_Id;
	}

	public String getFull_Name()
	{
		return full_Name;
	}

	public void setFull_Name(String full_Name)
	{
		this.full_Name = full_Name;
	}

	

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMobile_Number()
	{
		return mobile_Number;
	}

	public void setMobile_Number(String mobile_Number)
	{
		this.mobile_Number = mobile_Number;
	}

	public String getAdminOrUser_Flag()
	{
		return adminOrUser_Flag;
	}

	public void setAdminOrUser_Flag(String adminOrUser_Flag)
	{
		this.adminOrUser_Flag = adminOrUser_Flag;
	}

}
