package com.lti.banking.system.service;

import java.util.List;
import java.util.Set;

import com.lti.banking.system.entity.Account;
import com.lti.banking.system.entity.Customers;

public interface CustomerService {


	public void addCustomer(Customers customer);
	public void deleteCustomer(Customers customer);
	public List<Customers> findAll();
	public Customers findByID(String firstName,Integer phoneNumber);
	public void updateCustomer(Customers customer);
	public Set<Account> getAccountDetails(String firstName,Integer phoneNumber);


}
