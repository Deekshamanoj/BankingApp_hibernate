package com.ty.bankingApp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.service.spi.Manageable;

import com.ty.bankingApp.entity.Bank;
import com.ty.bankingApp.entity.Branch;
import com.ty.bankingApp.util.BankUtil;

public class BankDao {

	public Bank findBankById(int bankId) {
		EntityManager manager = BankUtil.createEntityManager();
		return manager.find(Bank.class, bankId);
	}

	public Bank getBank() {
		String jpql = "select u from Bank u";
		Query query = BankUtil.createEntityManager().createQuery(jpql);
		List<Bank> listOfBank = query.getResultList();
		Bank bank = listOfBank.get(0);
		return bank;
	}

	public Bank setBankBranch(Bank bank, Branch branch) {

		List<Branch> listOfBranch = bank.getBranches();
		if (listOfBranch == null) {
			listOfBranch = new ArrayList<Branch>();
		}
		listOfBranch.add(branch);
		bank.setBranches(listOfBranch);
		return bank;
	}

	public Bank updateBank(Bank bank) {
		EntityManager manager = BankUtil.createEntityManager();
	    EntityTransaction transaction =manager.getTransaction();
	    
	    transaction.begin();
	    manager.merge(bank);
	    transaction.commit();
	    
		return bank;
	}

	
	
	//bank
	//listBranchs

}
