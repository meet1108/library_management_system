package com.curd.model;

import java.sql.Date;

public class UserBorrowedBooks
{
	private int		borrowed_Id;
	private int 	book_Id;
	private String	member_Id;
	private String	full_Name;
	private String	dateOfBorrow;
	private String	title;
	private String 	status;
	private String	dateOfreturn;
	

	
	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getDateOfreturn()
	{
		return dateOfreturn;
	}

	public void setDateOfreturn(String dateOfreturn)
	{
		this.dateOfreturn = dateOfreturn;
	}

	public int getBorrowed_Id()
	{
		return borrowed_Id;
	}
	
	public void setBorrowed_Id(int borrowed_Id)
	{
		this.borrowed_Id = borrowed_Id;
	}
	
	public int getBook_Id()
	{
		return book_Id;
	}
	
	public void setBook_Id(int book_Id)
	{
		this.book_Id = book_Id;
	}

	public String getMember_Id()
	{
		return member_Id;
	}

	public void setMember_Id(String member_Id)
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

	public String getDateOfBorrow()
	{
		return dateOfBorrow;
	}

	

	public void setDateOfBorrow(String dateOfBorrow)
	{
		this.dateOfBorrow = dateOfBorrow;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

}
