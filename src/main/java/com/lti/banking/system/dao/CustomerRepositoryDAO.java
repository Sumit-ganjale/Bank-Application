package com.lti.banking.system.dao;

import java.util.List;
import java.util.Set;

import com.lti.banking.system.entity.Customers;

public interface CustomerRepositoryDAO {

	public List<Customers> findAll();

	public void delete(Customers customer);

	public void update(Customers customer);

	public void persist(Customers customer);

	public Customers findByNumber(String firstName, Integer phoneNumber);
}
