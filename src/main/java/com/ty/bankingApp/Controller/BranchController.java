package com.ty.bankingApp.Controller;

import java.util.Scanner;

import com.ty.bankingApp.dao.BankDao;
import com.ty.bankingApp.entity.Branch;

public class BranchController {

	public Branch getBranch(Scanner sc, BankDao bankDao) {
		Branch branch = new Branch();
		System.out.println("Enter Name : ");
		branch.setName(sc.next());
		System.out.println("Enter IFSC : ");
		branch.setIfscCode(sc.next());

		return branch;
	}

}
