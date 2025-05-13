package com.curd;

import java.util.Scanner;


import com.curd.service.ManagementService;
import com.curd.service.ManagementService.FlagHolder;

public class Library_Mangement_System 
{
	private final static ManagementService managementservice = new ManagementService();
	
	
	public static void main(String[] args)
	{

		Scanner sc = new Scanner(System.in);
		System.out.println("//-----------Library Management System------------\\\\\n\n");

		
		
		System.out.println("""
				***************System on*****************
				*                                       *
				*    Enter 0 for Exit the System        *
				*    Enter 1 for the Login              *
				*                                       *
				*****************************************
				""");
		
		boolean flag = true;
		
		while(flag == true) {
			String action = "";
			while (true) {
			    System.out.println("Enter your input (login-1, Exit system-0):");
			    String input = sc.nextLine().trim();

			    if (input.matches("[01]")) 
			    {
			        action = input;
			        break;  			    
			    } 
			    else 
			    {
			        System.out.println("Invalid input. Please enter 0 or 1 only.");
			    }
			}
			
			
			switch (action)
			{
				case "0":
					flag=false;
					System.out.println("Thank You! You exit from System");
					
					break;
					
				case "1":
					
					try
					{
						FlagHolder user = managementservice.loginMember();
						int adminUserFlag = user.value;
						int id = user.memberId;
						
						
						switch(adminUserFlag) {
							case 0:
								boolean userFlag = true;
								
								while(userFlag) {
									
									System.out.println("""
										***************System on*****************
										*                                       *
										*    Enter 0 for Logout                 *
										*    Enter 1 Borrow a Book              *
										*    Enter 2 Return a Book              *
										*    Enter 3 Edit Your profile          *
										*    Enter 4 View All books             *
										*    Enter 5 view borrowed book by user *
										*                                       *
										*****************************************
										""");
									String userAction = "";
									while (true) {
										System.out.println("Enter your Choice(0,1,2,3,4)");
									    String input = sc.nextLine().trim();

									    if (input.matches("\\d+")) 
									    {
									    	userAction = input;
									        break;  			    
									    } 
									    else 
									    {
									        System.out.println("Invalid input. Please enter Valid option.");
									    }
									}
									
									switch(userAction) {
										case "0":
											userFlag = false;
											System.out.println("you are logout!!");
											break;
										case "1":
											managementservice.userBorrowBook(id);
											break;
										case "2":
											managementservice.userReturnBook();
											break;
										case "3":
											managementservice.userEditProfile(id);
											break;
										case "4":
											managementservice.viewAllBook();
											break;
										case "5":
											managementservice.userborrowedBook(id);
											break;
										default:
											System.out.println("Invalid input. Please enter Valid option.");
									}
								}
								break;
							
							
							case 1:
								boolean adminFlag = true;
								
								while(adminFlag) {
									System.out.println("""
										***************System on************************
										*                                              *
										*    Enter 0 for Logout                        *
										*    Enter 1 Add New User                      *
										*    Enter 2 Add New Book                      *
										*    Enter 3 Delete a Book                     *
										*    Enter 4 Delete a Member                   * 
										*    Enter 5 View All Users                    *
										*    Enter 6 View All users borrow same Book   *
										*    Enter 7 View All books                    *
										************************************************
										""");
									System.out.println("Enter your Choice(0 to 6)");
									String adminAction = sc.nextLine().trim();
									
									switch(adminAction) {
										case "0":
											adminFlag = false;
											System.out.println("you are logout!!");
											break;
										case "1":
											managementservice.addNewUser();
											break;
										case "2":
											managementservice.addnewBook();
											break;
										case "3":
											managementservice.deleteBook();
											break;
										case "4":
											managementservice.deleteMember();
											break;
										case "5":
											managementservice.viewAllUser();
											break;
										case "6":
											managementservice.usersBorrowSameBook();
											break;
										case "7":
											managementservice.viewAllBook();
											break;
										default:
											System.out.println("Invalid input. Please enter Valid option.");
									}
								}
								break;
								
							default:
//								System.out.println("Please Enter Valid UserName and Password!!!");
								
						}
					}
					catch (Exception e)
					{
						
						e.printStackTrace();
					}
					
				default:
			}
		}


	}
}
