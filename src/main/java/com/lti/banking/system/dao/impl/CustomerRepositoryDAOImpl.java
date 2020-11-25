package com.lti.banking.system.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.banking.system.dao.CustomerRepositoryDAO;
import com.lti.banking.system.entity.Account;
import com.lti.banking.system.entity.Customers;

@Repository
public class CustomerRepositoryDAOImpl implements CustomerRepositoryDAO {

	@Autowired
	private SessionManager sessionMangager;


	public CustomerRepositoryDAOImpl(SessionManager sessionMangager) {
		this.sessionMangager = sessionMangager;
	}

	public Session getCurrentSession() {
		return sessionMangager.getCurrentSession();
	}

	@Override
	@Transactional
	public List<Customers> findAll() {
		sessionMangager.openCurrentSessionwithTransaction();
		Criteria criteria = getCurrentSession().createCriteria(Customers.class);
		criteria.setFetchMode("account", FetchMode.EAGER);
		List<Customers> customerList = (List<Customers>)criteria.list();
		sessionMangager.closeCurrentSessionwithTransaction();
		if(!customerList.isEmpty())
			return customerList;
			else
				return null;
		}

	@Override
	@Transactional
	public Customers findByNumber(String firstName, Integer phoneNumber) {
		sessionMangager.openCurrentSessionwithTransaction();
		Criteria criteria = getCurrentSession().createCriteria(Customers.class);
		criteria.setFetchMode("account", FetchMode.EAGER);
		criteria.add(Restrictions.eq("firstName", firstName));
		criteria.add(Restrictions.eq("phoneNumber", phoneNumber));
		List<Customers> customerList = (List<Customers>)criteria.list();
		sessionMangager.closeCurrentSessionwithTransaction();
		if(!customerList.isEmpty())
		return customerList.get(0);
		else
			return null;
	}

	@Override
	@Transactional
	public void delete(Customers customer) {
		sessionMangager.openCurrentSessionwithTransaction();
		getCurrentSession().delete(customer);
		sessionMangager.closeCurrentSessionwithTransaction();

	}

	@Override
	@Transactional
	public void update(Customers customer) {
		sessionMangager.openCurrentSessionwithTransaction();
		for(Account account:customer.getAccount()){
			customer.addAccount(account);
		}
		getCurrentSession().saveOrUpdate(customer);
		sessionMangager.closeCurrentSessionwithTransaction();

	}

	@Override
	@Transactional
	public void persist(Customers customer) {
		sessionMangager.openCurrentSessionwithTransaction();
		for(Account account:customer.getAccount()){
			customer.addAccount(account);

		}
		getCurrentSession().persist(customer);
		sessionMangager.closeCurrentSessionwithTransaction();
	}

}
