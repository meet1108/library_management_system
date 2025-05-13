package com.curd.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.curd.dao.ManagementDao;
import com.curd.model.Book;
import com.curd.model.Member;
import com.curd.validation.validator;

public class ManagementService
{
	private final ManagementDao	managementDao	= new ManagementDao();
	Scanner						sc				= new Scanner(System.in);

	//Inner Class
	public class FlagHolder
	{
		public int	value	= -1;
		public int	memberId;
	}

	FlagHolder	flagHolder	= new FlagHolder();
	Member		member		= new Member();

	//who is login check and login.
	public FlagHolder loginMember() throws SQLException
	{

		System.out.println("Enter Your Username:");
		String username = sc.nextLine().trim();

		while (!validator.validateUserName(username))
		{
			System.out.println("Enter your proper userName ");
			System.out.println("Enter 0 for Exit!");
			username = sc.nextLine().trim();
			if(username =="0") {
				break;
			}
		
		}


		if (managementDao.isUserExits(username))
		{

			System.out.println("Enter Your Password:");
			String password = sc.nextLine().trim();
			
			while (!validator.validatePassword(password))
			{
				System.out.println("Enter your proper Password(min-8,AAaa@@11)");
				password = sc.nextLine().trim();
			}
			
			if (managementDao.flagAdminOrUser(username, password, flagHolder))
			{
				return flagHolder;
			}
			else
			{
				System.out.println("User Password Wrong(min-8,AAaa@@11)!!!");
				return flagHolder;
			}

		}
		else
		{
//			System.out.println("Wrong User Name(a-zA_Z_ whitespace)!!");
//			System.out.println("Please Check Your UserName and Password, Rewrite Both!!");
			return flagHolder;
		}

	}

	//add user by Admin
	public void addNewUser() throws SQLException, IOException
	{
		System.out.println("-------Add New User(Member)--------\n\n");
		System.out.println("Enter your Full Name:\n");
		String fullName = sc.nextLine().trim();
		while (!validator.validateFullName(fullName))
		{
			System.out.println("Enter your proper fullName (a-zA_Z_ whitespace): ");
			fullName = sc.nextLine().trim();
		}
		System.out.println("Enter your Username:\n");
		String userName = sc.nextLine().trim();
		while (!validator.validateUserName(userName))
		{
			System.out.println("Enter your proper userName (a-zA_Z_ whitespace) :");
			userName = sc.nextLine().trim();
		}

		System.out.println("Enter your Password:\n");
		String password = sc.nextLine().trim();
		
		while (!validator.validatePassword(password))
		{
			System.out.println("Enter your proper Password (min-8,AAaa@@11)");
			password = sc.nextLine().trim();
		}
		System.out.println("Eneter your Mobile Number:\n");
		String mobileNumber = sc.nextLine().trim();
		
		while (!validator.validateMobileNumber(mobileNumber))
		{
			System.out.println("Enter your MobileNumber proper(10-numbers start with-6,7,8,9):");
			mobileNumber = sc.nextLine().trim();
		}

		managementDao.addUser(fullName, userName, password, mobileNumber);

	}

	//add new book
	public void addnewBook() throws SQLException
	{
		System.out.println("-------Add book Details-------\n\n");
		
		System.out.println("Enter New Book Title: \n");
		String title = sc.nextLine().trim();
		while (!validator.validateFullName(title))
		{
			System.out.println("Enter your proper BookName (a-zA_Z_ whitespace): ");
			title = sc.nextLine().trim();
		}
		
		System.out.println("Enter Author Name of the Book: \n");
		String author = sc.nextLine().trim();
		while (!validator.validateFullName(author))
		{
			System.out.println("Enter your proper Author Name (a-zA_Z_ whitespace): ");
			author = sc.nextLine().trim();
		}

		while(true) {
			
			try{
				System.out.println("Enter the Quatity of book:\n");
				int quantity_available = sc.nextInt();
				while (!validator.validateInputDigit(Integer.toString(quantity_available)))
				{
					System.out.println("Enter your proper quantity of books (1 to many) ");
					System.out.println("Enter the Quatity of book:\n");
					quantity_available = sc.nextInt();
					break;
				}
				if (managementDao.isBookExists(title))
				{

					managementDao.addExistBook(title);
					break;
				}
				else
				{
					managementDao.addBook(title, author, quantity_available);
					break;

				}
			}catch(InputMismatchException ie) {
				System.out.println("Enter your proper quantity of books (1 to many) ");
				
				 sc.nextLine();
			}
			
		}

		

	}

	//delete book
	public void deleteBook() throws SQLException
	{
		System.out.println("--------Delete Book--------");
		System.out.println("Enter book id which one you can delete:\n");
		String id = sc.nextLine().trim();
		

		while (!validator.validateInputDigit(id))
		{
			System.out.println("Enter your proper BookId:");
			id = sc.nextLine().trim();
		}
		if (managementDao.isBookExists(id))
		{
			if(!managementDao.allBookReturn(id)) {
				managementDao.deleteExistsBook(id);
				
			}else {
				System.out.println("All Books are not Return in Library!!");
			}
		}
		else
		{
			System.out.println("Book does not Exists in library");
		}

	}

	//delete user
	public void deleteMember() throws SQLException
	{

		System.out.println("--------Delete Member--------");
		System.out.println("Enter Member's Username whom to Delete:\n");
		String username = sc.nextLine().trim();
		while (!validator.validateUserName(username))
		{
			System.out.println("Enter your proper userName:");
			username = sc.nextLine().trim();
		}

		if (managementDao.isUserExits(username))
		{
			int id = managementDao.fetchId(username);
			if(!managementDao.allBookReturnByUser(id)) {
				managementDao.deleteExistsMember(managementDao.fetchId(username));
				
			}else {
				System.out.println("All Books are not Return in Library by user!!");
				System.out.println("Member Not Deleted!!!");
			}
		}
		else
		{
			System.out.println("user Does not Exists");
		}

	}

	//Borrowed  a book
	public void userBorrowBook(int id) throws SQLException
		{

			System.out.println("--------Borrow a Book--------");
			int memberId = id;

			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			String dobb = ft.format(new java.util.Date());

			while(true) {
				
				try{
					System.out.println("Enter Borrow Book Id:\n");
					int bookId = sc.nextInt();
					if(bookId==0) break;
						if (managementDao.isBookAvailable(bookId) > 0)
						{
							if(!managementDao.userAlreadyBorrowBook(memberId,bookId)) {
								managementDao.borrowBook(bookId, memberId, dobb);
								break;
								
							}else {
								System.out.println("You can Already Borrowed This Book!!");
								break;
							}
							
						}
						else
						{
							System.out.println("Book is not available");
						}
				}catch(InputMismatchException ie) {
					System.out.println("Please Enter Valid Input(only digit)!");
					System.out.println("------For Exit Enter 0 ");
					 sc.nextLine();
				}
				
			}
		}

	
	
	
	//Return Book
	public void userReturnBook() throws SQLException
	{
		System.out.println("-------Return Book-------");
		
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		String dorb = ft.format(new java.util.Date());

		while(true) {
			
			try{
				System.out.println("Enter your borrowed Id:\n");
				int borrowId = sc.nextInt();
				if(borrowId==0) break;
				if (managementDao.returnBook(borrowId, dorb))
				{
//					System.out.println("your book Succesfully return ");
					break;
				}
				
			}catch(InputMismatchException ie) {
				System.out.println("Please Enter Valid Input(only digit)!");
				System.out.println("------For Exit Enter 0 ");
				 sc.nextLine();
			}
			
		}
		
	}

	
	
	
	//User Edit their profile
	public void userEditProfile(int memberId) throws SQLException
	{
		System.out.println("------Edit your Profile--------\n");
		//		System.out.println("Enter your Member Id:\n");
		//		int memberId = sc.nextInt();

		boolean option = true;
		while (option)
		{
			Member member = managementDao.userDetails(memberId);
			printUserData(member);
			System.out.println("(Note: You can't change the your UserName.)");
			System.out.println("which information you can change(Can't change-0,Full name-1,Password-2,Mobile number-3)\n");
			String change = sc.nextLine();
			if(validator.validateInputDigit(change)) {
				switch (change)
				{
					case "0":
						printUserData(member);
						option = false;
						break;
					case "1":
						System.out.println("Enter Your Updated Full Name:\n");
						String fname = sc.nextLine();
						if (fname == "")
						{//regex
							break;
						}
						else
						{
							managementDao.updateFullName(fname, memberId);
						}
						break;
					case "2":
						System.out.println("Enter Your Updated Password:\n");
						String password = sc.nextLine();
						if (password == "")
						{//regex
							break;
						}
						else
						{
							managementDao.updatePassword(password, memberId);
						}

						break;
					case "3":
						System.out.println("Enter Your Updated Mobile Number:\n");
						String mobileNumber = sc.nextLine();
						if (mobileNumber == "")
						{//regex
							break;
						}
						else
						{
							managementDao.updateMobileNumber(mobileNumber, memberId);
						}
						break;
					default:
						System.out.println("Please Enter Valid Input Form Given options!!\n");
				}
	
			}else {
				
				System.out.println("Please Enter Only Numbers!!\n");
				
			}
			
			
			
		}

	}

	//Print user Data
	public static void printUserData(Member member)
	{
		System.out.println("******Your Profile Details******\n\n");
		System.out.println("-----> Full name: " + member.getFull_Name());
		System.out.println("-----> User name: " + member.getUsername());
		System.out.println("-----> Password: " + member.getPassword());
		System.out.println("-----> MobileNumber: " + member.getMobile_Number());
		System.out.println("\n\n******         End         ******\n\n");
	}
	
	

	//Print All Users 
	public void viewAllUser() throws SQLException
	{
		List<Member> users = new ArrayList<Member>();
		System.out.println("\n\n--------All User Details--------");
		users = managementDao.allUsers(member);

		System.out.println("MemberId       ||       Full Name       ||       UserName       ||       MobileNumber\n");
		for (Member member : users)
		{
			System.out.print(member.getMember_Id() + "\t\t\t");
			System.out.print(member.getFull_Name() + "\t\t\t");
			System.out.print(member.getUsername() + "\t\t\t");
			System.out.println(member.getMobile_Number() + "\t\t\t");

		}
		System.out.println("\n\n");
	}

	
	
	//	Users Borrow same Book
	public void usersBorrowSameBook() throws SQLException
	{
		List<Member> users = new ArrayList<Member>();
		while(true) {
			
			try{
				System.out.println("Enter Book Id :\n");
				int bookId = sc.nextInt();
				if(bookId==0) break;
				users = managementDao.usersHaveSameBook(bookId);

				System.out.println("\n-------Users have Same Book-------");
				System.out.println("Book Id : " + bookId + "\n");
				System.out.println("MemberId       ||       Full Name       ||       MobileNumber\n");
				for (Member member : users)
				{
					System.out.print(member.getMember_Id() + "\t\t\t");
					System.out.print(member.getFull_Name() + "\t\t\t");
					System.out.println(member.getMobile_Number() + "\t\t\t");
				}
				System.out.println("\n\n");
			}catch(InputMismatchException ie) {
				System.out.println("--------for Exit Enter 0\n");
				System.out.println("Please Enter Valid Input(only digit)!");
				 sc.nextLine();
			}
			
		}

	}

	//view all books
	public void viewAllBook() throws SQLException
	{
		List<Book> books = new ArrayList<Book>();
		System.out.println("\n\n--------All Books Details--------");
		books = managementDao.allBooks(member);

		System.out.println("BookId       ||       Book Name       ||       Author Name       \n");
		for (Book book : books)
		{
			System.out.print(book.getBook_Id() + "\t\t\t");
			System.out.print(book.getTitle() + "\t\t\t");
			System.out.print(book.getAuthor() + "\t\t\t");

		}
		System.out.println("\n\n");
	}

	//user borrowed books
	public void userborrowedBook(int id) throws SQLException
	{
		List<Book> books = new ArrayList<Book>();
		System.out.println("-------All books borrowed by you-------");
		System.out.println("BookId       ||       Book Name       ||       Author Name       \n");
		if (managementDao.booksBorrowedByUser(id).isEmpty())
		{

			System.out.println("----------You can No one Book Borrowed!!!");
		}
		else
		{

			books = managementDao.booksBorrowedByUser(id);

			for (Book book : books)
			{

				System.out.print(book.getBook_Id() + "\t\t\t");
				System.out.print(book.getTitle() + "\t\t\t");
				System.out.print(book.getAuthor() + "\t\t\t");

			}
		}

		System.out.println("\n\n");
	}

}
