package com.ty.bankingApp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BankUtil {
	
	public static EntityManager createEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dee");
		return factory.createEntityManager();
	}

}
