package com.ty.bankingApp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.ty.bankingApp.entity.Account;
import com.ty.bankingApp.entity.User;
import com.ty.bankingApp.util.BankUtil;

public class AccountDao {

	public Account createAccount(Account account, User user) {
		EntityManager manager = BankUtil.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		account.setUser(user);
		transaction.begin();
		manager.persist(account); 
		transaction.commit();
	
		
		return null;
	}

	public Boolean deleteAccount(int id) {
		EntityManager manager = BankUtil.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Account account=manager.find(Account.class, id);
		if(account!=null)
		{
			transaction.begin();
			manager.remove(account);
			transaction.commit();
			return true;
		}
		return false;
	}

	public Account updateAccountType(int id, String type) {
		EntityManager manager = BankUtil.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Account account=manager.find(Account.class, id);
		account.setType(type);
		transaction.begin();
		manager.merge(account);
		transaction.commit();
		System.out.println("Account updated successfully");
		return account;
	}

}
