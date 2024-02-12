package com.ty.bankingApp.Controller;

import java.util.List;
import java.util.Scanner;

import com.ty.bankingApp.dao.AccountDao;
import com.ty.bankingApp.dao.BankDao;
import com.ty.bankingApp.dao.BranchDao;
import com.ty.bankingApp.dao.UserDao;
import com.ty.bankingApp.entity.Account;
import com.ty.bankingApp.entity.Bank;
import com.ty.bankingApp.entity.Branch;
import com.ty.bankingApp.entity.User;

public class BankAppController {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserDao userDao = new UserDao();
		UserController userController = new UserController();

		System.out.println("------------WELCOME TO BANK-------------");
		while (true) {
			System.out.println("Enter choice");
			System.out.println("1.Login\n2.Register\n3.Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:// Login

				login(sc, userDao);

				break;

			case 2:// Register
				register(sc, userDao, userController);
				break;

			case 3:// Exit

				System.exit(0);
				break;

			default:
				System.out.println("INVALID CHOICE...!!!!!");
				break;
			}
		}

	}

	public static void login(Scanner sc, UserDao userDao) {
		System.out.println("Enter the email : ");
		String email = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		User user = userDao.getUserByEmailAndPassword(email, password);
		if (user != null) {
			verifyUser(sc, user);
		} else
			System.out.println("User doesnot exist!!!!");

	}

	public static void verifyUser(Scanner sc, User user) {
		while (true) {
			if (user.getRole().equalsIgnoreCase("manager")) {
				BranchDao branchDao = new BranchDao();
				BranchController branchController = new BranchController();
				BankDao bankDao = new BankDao();

				System.out.println("Select choice : ");
				System.out.println("1.Create Branch\n2.Delete Branch\n3.Update Branch\n4.Display all Branch\n5.exit");
				switch (sc.nextInt()) {
				case 1:// create branch

					createBranch(branchController,sc,bankDao,branchDao);
					break;

				case 2://delete 

					deleteBranch(sc,branchDao,bankDao);
					break;

				case 3://update the branch
					
					updateBranchName(sc,branchDao);
					break;

				case 4://display all branched
					
					displayAllBranch();
					break;

				case 5:// exit
					System.exit(0);
					break;

				default:System.out.println("INVALID CHOICE !!!!!!");
					break;
				}

			} else {
				
				System.out.println("Select choice : ");
				System.out.println("1.Create Account\n2.Delete Account\n3.Update Account\n5.exit");
				
				AccountDao accountDao = new AccountDao();
				AccountController accountController = new AccountController();
				switch (sc.nextInt()) {
				case 1:// create account

					createAccount(sc, accountController, accountDao, user);
					break;

				case 2://delete account
					deletAccount(sc,accountDao);
					break;

				case 3://update account type
					updateAccount(sc, accountDao);
					break;

				case 4:// exit
					System.exit(0);
					break;

				default:System.out.println("INVALID CHOICE !!!!!!");
					break;
				}

			}
		}

	}

	private static void updateAccount(Scanner sc,AccountDao accountDao) {
		System.out.println("Enter account id ");
		int id = sc.nextInt();
		System.out.println("Enter modified account type");
		String type =sc.next();
		Account account=accountDao.updateAccountType(id,type);		
	}

	private static void createAccount(Scanner sc,AccountController accountController,AccountDao accountDao,User user) {
		Account account = accountController.getAccount(sc);
		account=accountDao.createAccount(account,user);
		System.out.println("Account created successfully");
	}

	private static void deletAccount(Scanner sc,AccountDao accountDao) {
		System.out.println("Enter Account id to delete");
		int id = sc.nextInt();
		Boolean b =accountDao.deleteAccount(id);
		if(b==true)
		{
			System.out.println("Account deleted successfully");
		}
		else
			System.out.println("ACcount not found");
		
	}

	private static void displayAllBranch() {
		List<Branch> branchList =BranchDao.getAllBrach();
		for ( Branch branch : branchList) {
			System.out.println(branch);
		}
	}

	private static void updateBranchName(Scanner sc, BranchDao branchDao) {
		System.out.println("Enter id of branch that u want to update");
		int id = sc.nextInt();
		System.out.println("Enter the modified Branch name");
		String name = sc.next();
		Branch branch=branchDao.updateBranchName(id,name);
	}

	private static void deleteBranch(Scanner sc,BranchDao branchDao,BankDao bankDao) {
		System.out.println("Enter the branch id to delete the branch");
		boolean b= branchDao.deleteBranch(sc.nextInt(),bankDao);
		if(b==true)
			System.out.println("Branch deleted successfully");
		else
			System.out.println("Could not find the Branch");

	}

	private static void createBranch(BranchController branchController,Scanner sc,BankDao bankDao,BranchDao branchDao) {
		Branch branch = branchController.getBranch(sc, bankDao);
		branch = branchDao.addBranch(branch);
		Bank bank = bankDao.getBank();
		bank=bankDao.setBankBranch(bank, branch);
		bank=bankDao.updateBank(bank);
		System.out.println("Branch added successfully");
		
	}

	public static void register(Scanner sc, UserDao userDao, UserController userController) {
		User user = userController.getUser(sc);
		userDao.addUser(user);
	}

}
