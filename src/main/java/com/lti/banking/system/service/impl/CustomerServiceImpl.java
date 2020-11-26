package com.lti.banking.system.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.banking.system.dao.CustomerRepositoryDAO;
import com.lti.banking.system.entity.Account;
import com.lti.banking.system.entity.Customers;
import com.lti.banking.system.service.CustomerService;

@Component
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepositoryDAO customerRepositoryDao;

@Autowired
	public CustomerServiceImpl(CustomerRepositoryDAO customerRepositoryDao){
		this.customerRepositoryDao=customerRepositoryDao;
	}

	@Override
	public void addCustomer(Customers customer) {
		customerRepositoryDao.persist(customer);

	}

	@Override
	public void deleteCustomer(Customers customer) {
		customerRepositoryDao.delete(customer);

	}

	@Override
	public List<Customers> findAll() {
		List<Customers>customerList=customerRepositoryDao.findAll();
		if(customerList!=null){
		return customerList.stream().distinct().collect(Collectors.toList());
	}else
		return null;
	}

	@Override
	public Customers findByID(String firstName,Integer phoneNumber) {
		return customerRepositoryDao.findByNumber(firstName,phoneNumber);
	}

	@Override
	public void updateCustomer(Customers customer) {
		customerRepositoryDao.update(customer);
	}

	@Override
	public Set<Account> getAccountDetails(String firstName, Integer phoneNumber) {
		//return null;
		Customers customer=customerRepositoryDao.findByNumber(firstName,phoneNumber);
		if(customer!=null){
		return customer.getAccount();
		}
		return null;

}}
