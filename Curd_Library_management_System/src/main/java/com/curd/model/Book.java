package com.curd.model;

public class Book
{
	private int		book_Id;
	private String	title;
	private String	author;
	private int		quantity_Available;

	public Book(int book_Id, String title, String author)
	{
		
		this.book_Id = book_Id;
		this.title = title;
		this.author = author;
	}

	public Book() {
		
	}
	
	
	public int getBook_Id()
	{
		return book_Id;
	}

	public void setBook_Id(int book_Id)
	{
		this.book_Id = book_Id;
	}

	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public int getQuantity_Available()
	{
		return quantity_Available;
	}

	public void setQuantity_Available(int quantity_Available)
	{
		this.quantity_Available = quantity_Available;
	}

}
