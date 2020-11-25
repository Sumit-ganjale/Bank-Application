package com.lti.banking.system.dao.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.banking.system.dao.AccountRepositoryDAO;
import com.lti.banking.system.entity.Account;
@Repository
public class AccountRepositoryDAOImpl implements AccountRepositoryDAO {

	@Autowired
	private SessionManager sessionManager;

	public AccountRepositoryDAOImpl(SessionManager sessionMangager) {
		this.sessionManager = sessionMangager;
	}

	public Session getCurrentSession() {
		return sessionManager.getCurrentSession();
	}

	@Override
	public void update(Account account) {
		sessionManager.openCurrentSessionwithTransaction();
		getCurrentSession().update(account);
		sessionManager.closeCurrentSessionwithTransaction();

	}

	/*@Override
	public long withdraw() {
		sessionManager.openCurrentSessionwithTransaction();
		getCurrentSession().update(account);
		sessionManager.closeCurrentSessionwithTransaction();
	}*/
}
