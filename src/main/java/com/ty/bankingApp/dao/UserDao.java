package com.ty.bankingApp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.ty.bankingApp.entity.User;
import com.ty.bankingApp.util.BankUtil;

public class UserDao {

	public  void addUser(User user)
	{
		EntityManager manager=BankUtil.createEntityManager();
		EntityTransaction transaction=manager.getTransaction();
		transaction.begin();
		manager.persist(user);
		transaction.commit();
		System.out.println("user added successfully");
	}
	
	public  User findUserById(int id) {
		return BankUtil.createEntityManager().find(User.class, id);
	}

	public User getUserByEmailAndPassword(String email, String password) {
		String jpql = "select u from User u where u.email=?1 and u.password = ?2";
		Query query = BankUtil.createEntityManager().createQuery(jpql);
		query.setParameter(1, email);
		query.setParameter(2, password);
		List<User> list= query.getResultList();
		if(list!=null && !(list.isEmpty()))
		{
			System.out.println("Login successful");
			return list.get(0);
		}
		return null;
	}

}
