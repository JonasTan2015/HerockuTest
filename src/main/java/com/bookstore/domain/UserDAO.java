package com.bookstore.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	
//	public void insertUser(User user)
//	{
//		entityManager.persist(user);
//	}

}
