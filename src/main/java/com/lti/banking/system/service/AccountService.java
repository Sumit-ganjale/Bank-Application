package com.lti.banking.system.service;

import java.math.BigDecimal;
import java.util.Set;

import com.lti.banking.system.entity.Account;

public interface AccountService {
	public BigDecimal deposit(Set<Account> list, String fromAccount, long amount);

	public boolean withdraw(Set<Account> list, String from, long amount);

	public void findByName();
}
