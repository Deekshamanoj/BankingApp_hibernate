package com.ty.bankingApp.Controller;

import java.util.Scanner;

import com.ty.bankingApp.entity.Account;

public class AccountController {

	public Account getAccount(Scanner sc) {
		Account account = new Account();
		System.out.println("Enter the account type");
		account.setType(sc.next());
		System.out.println("Enter the balance ");
		account.setBalance(sc.nextDouble());
		return account;
	}

}
