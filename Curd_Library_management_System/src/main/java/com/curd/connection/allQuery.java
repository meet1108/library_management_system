package com.curd.connection;

public class allQuery
{
	
	public static String userExistsQuery				=	"SELECT COUNT(*) FROM member WHERE BINARY username =?";
	public static String addUserDetailsQuery			=	"Insert into member (full_name,username,password,mobile_number) value(?,?,?,?)";
	public static String addNewBookQuery				=	"Insert into book (title,author_name,quantity_available) value(?,?,?)";
	public static String bookExistsQuery				=	"select count(*) from book where book_id = ?";
	public static String addExistBookQuery				=	"update book set quantity_available = quantity_available + 1 where title = ?";
	public static String deleteBookQuery				=	"delete from book where book_id=?";
	public static String getFlagAdminOrUserQuery		=	"select flag,member_id from member where BINARY	username=? and password=?";
	public static String deleteMemberQuery				=	"delete from member where member_id=?";
	public static String getQuantityBookAvailableQuery	=	"select quantity_available from book where book_id=?";
	public static String borrowBookQuery				=	"insert into userborrowedbook (book_id,member_id,dateofborrow,status) value(?,?,?,?)";
	public static String returnBookQuery				=	"update userborrowedbook set status = ? ,dateofreturn=? where borrow_id=?";
	public static String userDetailsQuery				=	"Select * from member where member_id = ?";
	public static String updateFulllNameQuery			=	"update member set full_name=? where member_id = ?";
	public static String updatePasswordQuery			=	"update member set password=? where member_id = ?";
	public static String updateMobilenumberQuery		=	"update member set mobile_number=? where member_id = ?";
	public static String allUsersQuery					= 	"Select * from member";
	public static String userHaveSameBookQuery          =	"SELECT m.* FROM member m JOIN userborrowedbook ub ON m.member_id = ub.member_id WHERE ub.book_id = ?";
	public static String allBooksQuery					=	"select * from book";
	public static String booksByUserQuery				=	"select * from book where book_id IN (select book_id from userborrowedbook where member_id = ?);";
	public static String fetchUserIdQuery				=	"select member_id from member where BINARY username=?";
	public static String allbookReturnQuery				=	"select count(*) from userborrowedbook where book_id=? and status=?";
	public static String allbookReturnByUserQuery		=	"select count(*) from userborrowedbook where member_id=? and status=?";
	public static String checkUserBorrowBook			=	"select count(*) from userborrowedbook where member_id=? and book_id=? and status =?"; 
	
}



