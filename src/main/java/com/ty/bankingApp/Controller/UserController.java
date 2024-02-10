package com.ty.bankingApp.Controller;

import java.util.Scanner;

import com.ty.bankingApp.entity.User;

public class UserController {

	public User getUser(Scanner sc) {
		User user = new User();
		System.out.println("Enter Name : ");
		user.setName(sc.next());
		user.setRole("Customer");
		System.out.println("Enter the email : ");
		user.setEmail(sc.next());
		System.out.println("Enter the password : ");
		user.setPassword(sc.next());
		
		return user;
	}

}
