package com.ty.bankingApp.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import com.ty.bankingApp.dao.BankDao;
import com.ty.bankingApp.entity.Bank;
import com.ty.bankingApp.entity.Branch;
import com.ty.bankingApp.util.BankUtil;

public class BranchController {

	public Branch getBranch(Scanner sc,BankDao bankDao) {
		Branch branch = new Branch();
		System.out.println("Enter Name : ");
		branch.setName(sc.next());
		System.out.println("Enter IFSC : ");
		branch.setIfscCode(sc.next());
		
		Bank bank = bankDao.getBank();
		
	    bankDao.setBankBranch(bank,branch);
		
		
		System.out.println("Branch added successfully");
		return branch;
	}

}
