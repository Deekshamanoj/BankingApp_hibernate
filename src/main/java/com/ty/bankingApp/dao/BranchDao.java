package com.ty.bankingApp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.ty.bankingApp.entity.Bank;
import com.ty.bankingApp.entity.Branch;
import com.ty.bankingApp.util.BankUtil;

public class BranchDao {

	public Branch addBranch(Branch branch) {

		EntityManager manager = BankUtil.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		transaction.begin();
		manager.persist(branch);
		transaction.commit();

		return branch;

	}

	public boolean deleteBranch(int id, BankDao bankDao) {
		EntityManager manager = BankUtil.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		Branch branch = manager.find(Branch.class, id);

		if (branch != null) {
			Bank bank = bankDao.getBank();
			List<Branch> list = bank.getBranches();

			List<Branch> branches = new ArrayList<Branch>();

			for (Branch b : list) {

				if (!(b.getId() == id)) {
					branches.add(branch);
				}
			}
			bank.setBranches(branches);
			transaction.begin();
			manager.merge(bank);
			transaction.commit();

			transaction.begin();
			manager.remove(branch);
			transaction.commit();
			return true;
		}
		return false;
	}

	public Branch updateBranchName(int id,String name) {
		EntityManager manager = BankUtil.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		Branch branch=manager.find(Branch.class, id);
		branch.setName(name);
		
		transaction.begin();
		manager.merge(branch);
		transaction.commit();
		System.out.println("Branch name updated successfully");
		return branch;
	}

	public static List<Branch> getAllBrach() {
		EntityManager manager = BankUtil.createEntityManager();
		
		Bank bank = manager.find(Bank.class, 1);
		return bank.getBranches();
		
	}

}
