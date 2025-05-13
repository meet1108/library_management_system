package com.curd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curd.connection.allQuery;
import com.curd.connection.connection_DB;
import com.curd.model.Book;
import com.curd.model.Member;
import com.curd.service.ManagementService.FlagHolder;

public class ManagementDao
{
	Connection conn = connection_DB.getConnection();
	Member member = new Member();

	//check the member exixts or not
	public boolean isUserExits(String username) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.userExistsQuery);

		pstmt.setString(1, username);
//		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			int count = rs.getInt(1);
			if(count>0) {
				return true;
			}
		}
		return false;
		
	}
	
	//check member is user or Admin
	public boolean flagAdminOrUser(String username, String password, FlagHolder flagHolder) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.getFlagAdminOrUserQuery);

		pstmt.setString(1, username);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();
		int flag = 0 ;
		int memberId=-1;
		
		if (rs.next())
		{
			int count = rs.getInt(1);
			if (count > 0)
			{
				flag = rs.getInt("flag");
				flagHolder.value =flag;
				memberId = rs.getInt("member_id");
				flagHolder.memberId =memberId;
				return true;
			}else {
				memberId = rs.getInt("member_id");
				flagHolder.memberId =memberId;
				flagHolder.value =flag;
				return true;
			}
		}else {
			
			flagHolder.value=-1;
			return false;
		}
	
	}

	//add new User
	public boolean addUser(String fullName, String userName, String password, String mobileNumber) throws SQLException
	{

		PreparedStatement pstmt = conn.prepareStatement(allQuery.addUserDetailsQuery);

		pstmt.setString(1, fullName);
		pstmt.setString(2, userName);
		pstmt.setString(3, password);
		pstmt.setString(4, mobileNumber);

		int rows = pstmt.executeUpdate();

		if (rows != 0)
		{
			System.out.println("user added successfully");
			return true;

		}
		else
		{
			System.out.println("user not added");
			return false;
		}

	}

	
	//add Book 
	public boolean addBook(String title, String author, int quantity_available) throws SQLException 
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.addNewBookQuery);
		pstmt.setString(1, title);
		pstmt.setString(2, author);
		pstmt.setInt(3, quantity_available);
		
		int rows =  pstmt.executeUpdate();
		
		if (rows != 0)
		{
			System.out.println("Book added successfully");
			return true;

		}
		else
		{
			System.out.println("Book not added");
			return false;
		}

		
	}
	
	
	
	
	//is Book Exists
	public boolean isBookExists(String id) throws SQLException {
		
		PreparedStatement pstmt = conn.prepareStatement(allQuery.bookExistsQuery);
		pstmt.setString(1, id);
		
	
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			int count = rs.getInt(1);
			if(count>0) {
				return true;
			}
		}
		return false;
		
		
	}

	
	//check all books are return then Delete
	public boolean allBookReturn(String id) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.allbookReturnQuery);
		pstmt.setString(1, id);
		pstmt.setString(2, "0");
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			int count = rs.getInt(1);
			if(count>0) {
				return true;
			}
		}
		return false;
		
	}

	
	
	//check all books are return the User
		public boolean allBookReturnByUser(int id) throws SQLException
		{
			PreparedStatement pstmt = conn.prepareStatement(allQuery.allbookReturnByUserQuery);
			pstmt.setInt(1, id);
			pstmt.setString(2, "0");
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				if(count>0) {
					return true;
				}
			}
			return false;
			
		}
		
		
		
	//add Existing book
	public void addExistBook(String title) throws SQLException
	{
		PreparedStatement pstmt =conn.prepareStatement(allQuery.addExistBookQuery);
		pstmt.setString(1, title);
		
		int rowUpdate = pstmt.executeUpdate();
		
		if(rowUpdate!=0) {
			System.out.println("book added");
		}else {
			System.out.println("book not added");
		}
	}

	
	//delete Book
	public void deleteExistsBook(String id) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.deleteBookQuery);
		pstmt.setString(1, id);
		
		int rowBookDelete = pstmt.executeUpdate();
		
		if(rowBookDelete!=0) {
			System.out.println("book Deleted");
		}else {
			System.out.println("book not Deleted");
		}
		
	}

	
	//delete Member
	public void deleteExistsMember(int id ) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.deleteMemberQuery);
		pstmt.setInt(1, id);
		
		int rowmemberDelete = pstmt.executeUpdate();
		
		if(rowmemberDelete!=0) {
			System.out.println("Member Deleted");
		}else {
			System.out.println("Member not Deleted");
		}
		
	}

	
	//check the book available
	public int isBookAvailable(int bookId) throws SQLException
	{

		PreparedStatement pstmt = conn.prepareStatement(allQuery.getQuantityBookAvailableQuery);

		pstmt.setInt(1, bookId);

		ResultSet rs = pstmt.executeQuery();
		int bookcount = 0;
		if (rs.next())
		{
			int count = rs.getInt(1);
			if (count > 0)
			{
				bookcount = rs.getInt("quantity_available");
			}
		}
		
		return bookcount;
	}

	//borrow a book
	public void borrowBook(int bookId, int memberId, String dobb) throws SQLException
	{
		PreparedStatement pstmt =conn.prepareStatement(allQuery.borrowBookQuery);
		pstmt.setInt(1, bookId);
		pstmt.setInt(2, memberId);
		pstmt.setString(3, dobb);
		pstmt.setString(4, "0");
		
		int borrowcount =pstmt.executeUpdate();
		if(borrowcount!=0) {
			System.out.println("Book borrow Succefully");
		}else {
			System.out.println("Book borrow not Succefull");
		}
		
	}
	
	
	//return a Book
	public boolean returnBook(int borrow_id,String dateofreturn) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(allQuery.returnBookQuery);
		pstmt.setString(1, "1");
		pstmt.setString(2, dateofreturn);
		pstmt.setInt(3, borrow_id);
		
		int returncount = pstmt.executeUpdate();
		if(returncount!=0) {
			System.out.println("Book return Succefully");
			return true;
		}else {
			System.out.println("Book return not Succefull");
			return false;
		}
		
	}

	//fetch user Details by its id
	public Member userDetails(int memberId) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.userDetailsQuery);
		pstmt.setInt(1, memberId);
		ResultSet rs = pstmt.executeQuery();
		Member m =new Member();
		
		if(rs.next()) {

			m.setFull_Name(rs.getString("full_name"));
			m.setUsername(rs.getString("username"));
			m.setPassword(rs.getString("password"));
			m.setMobile_Number(rs.getString("mobile_number"));
		}
		return m;
		
		
		
	}

	//update fullname
	public void updateFullName(String fullname, int memberId) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.updateFulllNameQuery);	
		pstmt.setString(1,fullname);
		pstmt.setInt(2, memberId);
		
		int upadaterowfname = pstmt.executeUpdate();
		
		if(upadaterowfname>0) {
			System.out.println("Full name updated successfully");
		}else {
			System.out.println("Something went wrong!!!");
		}
	
	}

	//update Password
	public void updatePassword(String password,int memberId) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.updatePasswordQuery);	
		pstmt.setString(1,password);
		pstmt.setInt(2, memberId);
		
		int upadaterowpass = pstmt.executeUpdate();
		
		if(upadaterowpass>0) {
			System.out.println("Password updated successfully");
		}else {
			System.out.println("Something went wrong!!!");
		}		
	}

	
	//update mobile number
	public void updateMobileNumber(String mobileNumber,int memberId) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.updateMobilenumberQuery);	
		pstmt.setString(1,mobileNumber);
		pstmt.setInt(2, memberId);
		
		int upadaterowmob = pstmt.executeUpdate();
		
		if(upadaterowmob>0) {
			System.out.println("Mobile number updated successfully");
		}else {
			System.out.println("Something went wrong!!!");
		}			
	}

	
	//fetch all Users
	public List<Member> allUsers(Member member) throws SQLException
	{
		List<Member> members = new ArrayList<Member>();
		
		PreparedStatement pstmt = conn.prepareStatement(allQuery.allUsersQuery);
	   	 ResultSet rs = pstmt.executeQuery();
	   	 
	   	 while(rs.next()) {
	   		
	   		 if(rs.getInt("flag")==0) {
	   			 
	   			 int memberId = rs.getInt("member_id");
	   			 String fullName = rs.getString("full_name");
	   			 String username = rs.getString("username");
	   			 String mobileNumber = rs.getString("mobile_number");
	   			 
	   			 members.add(new Member(memberId,fullName,mobileNumber,username));
	   		 }
	   	 }
		
		return members;
		
	}

//	Users Have Same Book
	public List<Member> usersHaveSameBook(int bookId) throws SQLException
	{
		
		List<Member> members = new ArrayList<Member>();
		PreparedStatement pstmt = conn.prepareStatement(allQuery.userHaveSameBookQuery);
		pstmt.setInt(1, bookId);
	   	 ResultSet rs = pstmt.executeQuery();
	   	 
	   	 while(rs.next()) {
	   		
	   			 
	   			 int memberId = rs.getInt("member_id");
	   			 String fullName = rs.getString("full_name");
	   			 String mobileNumber = rs.getString("mobile_number");
	   			 
	   			 members.add(new Member(memberId,fullName,mobileNumber));
	   	 }
		
		return members;
	}

	
	//fetch all Books
	public List<Book> allBooks(Member member) throws SQLException
	{

		List<Book> books = new ArrayList<Book>();
		
		PreparedStatement pstmt = conn.prepareStatement(allQuery.allBooksQuery);
	   	 ResultSet rs = pstmt.executeQuery();
	   	 
	   	 while(rs.next()) {
	   		
	   			 
	   			 int bookId = rs.getInt("book_id");
	   			 String bookName = rs.getString("title");
	   			 String authorName = rs.getString("author_name");
	   			 
	   			 
	   			 books.add(new Book(bookId,bookName,authorName));
	   	 }
		
		return books;
	}

	
	//book borrowed by user
	public List<Book> booksBorrowedByUser(int id) throws SQLException
	{
		List<Book> books = new ArrayList<Book>();

		PreparedStatement pstmt = conn.prepareStatement(allQuery.booksByUserQuery);
		pstmt.setInt(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			 int bookId = rs.getInt("book_id");
   			 String bookName = rs.getString("title");
   			 String authorName = rs.getString("author_name");
   			 
   			 
   			 books.add(new Book(bookId,bookName,authorName));
		}
		
		
		return books;
	}

//	fetch User id from username
	public int fetchId(String userName) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(allQuery.fetchUserIdQuery);
		pstmt.setString(1, userName);
		
		ResultSet rs = pstmt.executeQuery();
		int id=0;
		while(rs.next()) {
			 id = rs.getInt("member_id");
			 
			return id;
		}
		return id;
		
	}

	//check user Already boorow a book or not
	public boolean userAlreadyBorrowBook(int memberId, int bookId) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement(allQuery.checkUserBorrowBook);
		pstmt.setInt(1, memberId);
		pstmt.setInt(2, bookId);
		pstmt.setInt(3, 0);
		
		ResultSet rs = pstmt.executeQuery();
		int count =0;
		if(rs.next()) {
			count = rs.getInt(1);
			if(count>0) {
				return true;
			}
		}
		return false;
	}
	
	
}















