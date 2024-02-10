package com.ty.bankingApp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

}
