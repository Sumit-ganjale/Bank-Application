package com.lti.banking.system.service;

import java.util.Set;

import com.lti.banking.system.entity.Account;


public interface FundTransferService {

	public boolean tranferToOwnBankAccounts(Set<Account> list,String fromAccount, String toAccount, long amount);
	public void TranferToOtherBankAccounts();

}
