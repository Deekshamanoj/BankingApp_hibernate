package com.ty.bankingApp.Controller;

import java.util.Scanner;

import com.ty.bankingApp.dao.BankDao;
import com.ty.bankingApp.dao.BranchDao;
import com.ty.bankingApp.dao.UserDao;
import com.ty.bankingApp.entity.Bank;
import com.ty.bankingApp.entity.Branch;
import com.ty.bankingApp.entity.User;

public class BankAppController {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserDao userDao = new UserDao();
		UserController userController = new UserController();
		
		

		System.out.println("------------WELCOME TO BANK-------------");
		while(true)
		{
			System.out.println("ENter choice");
			System.out.println("1.Login\n2.Register\n3.Exit");
			int choice = sc.nextInt();
			switch (choice ) {
			case 1://Login

				login(sc,userDao);

				break;

			case 2://Register
				register(sc,userDao,userController);
				break;

			case 3://Exit
				
				System.exit(0);
				break;

			default:System.out.println("INVALID CHOICE...!!!!!");
			break;
			}
		}


	}

	public static void login(Scanner sc,UserDao userDao) {
		System.out.println("Enter the email : ");
		String email = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		User user =userDao.getUserByEmailAndPassword(email,password);
		if(user!=null)
		{
			verifyUser(sc,user);
		}
		else
			System.out.println("User doesnot exist!!!!");

	}

	public static void verifyUser(Scanner sc,User user) {
		while(true) {
			if(user.getRole().equalsIgnoreCase("manager"))
			{
				BranchDao branchDao = new BranchDao();
				BranchController branchController= new BranchController();
				BankDao bankDao = new BankDao();
				
				System.out.println("Select choice : ");
				System.out.println("1.Create Branch\n2.Delete Branch\n3.Update Branch\n4.Display all Branch\n5.exit");
				switch (sc.nextInt()) {
				case 1://create branch
					
					Branch branch = branchController.getBranch(sc,bankDao);
					branchDao.addBranch(branch);
					
					break;
					
				case 2:
					break;
					
				case 3:
					break;
					
				case 4:
					break;
					
				case 5://exit
					
					System.exit(0);
					break;

				default:
					break;
				}

			}
			else
			{

			}
		}
		
	}

	public static void register(Scanner sc,UserDao userDao, UserController userController) {
		User user=userController.getUser(sc);
		userDao.addUser(user);
	}

}
