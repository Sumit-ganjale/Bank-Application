package com.lti.banking.system.service.impl;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.banking.system.dao.AccountRepositoryDAO;
import com.lti.banking.system.entity.Account;
import com.lti.banking.system.entity.AccountType;
import com.lti.banking.system.service.AccountService;
import com.lti.banking.system.service.CustomerService;
import com.lti.banking.system.service.FundTransferService;

@Component
public class FundTransferServiceImpl implements FundTransferService {


	@Autowired
	AccountRepositoryDAO accountRepositoryDAO;

	@Autowired
	AccountService accountService;
	@Autowired
	CustomerService customerService;

	private String savings="savings";
	private String checking="checking";
	private String money_market="money_market";

	@Override
	public void TranferToOtherBankAccounts() {

	}

	public boolean validateAmount(Account fromAccount, long amount) {
		return fromAccount.getAmount().longValue() > amount;
	}

	@Override
	public synchronized boolean tranferToOwnBankAccounts(Set<Account> accountList, String fromAccount, String toAccount, long amount) {
		Account savingAccount = null;
		Account chackingAccount = null;
		Account moneyMarketAccount = null;

		for (Account account : accountList) {
			if (account.getAccountType().equals(savings)) {
				savingAccount = account;
			} else if (account.getAccountType().equals(checking)) {
				chackingAccount = account;
			} else if (account.getAccountType().equals(money_market)) {
				moneyMarketAccount = account;
			}

		}
		if (fromAccount.equals(savings) && toAccount.equals(checking)) {
			if (validateAmount(savingAccount, amount)) {
				savingAccount.setAmount(savingAccount.getAmount().subtract(new BigDecimal(amount)));
				chackingAccount.setAmount(chackingAccount.getAmount().add(new BigDecimal(amount)));
				accountRepositoryDAO.update(savingAccount);
				accountRepositoryDAO.update(chackingAccount);
			} else {
				return false;
			}
		} else if (fromAccount.equals(checking) && toAccount.equals(savings)) {
			if (validateAmount(chackingAccount, amount)) {
				savingAccount.setAmount(savingAccount.getAmount().add(new BigDecimal(amount)));
				chackingAccount.setAmount(chackingAccount.getAmount().subtract(new BigDecimal(amount)));
				accountRepositoryDAO.update(savingAccount);
				accountRepositoryDAO.update(chackingAccount);
			} else {
				return false;
			}
		} else if (fromAccount.equals(savings) && toAccount.equals(money_market)) {
			if (validateAmount(savingAccount, amount)) {
				savingAccount.setAmount(savingAccount.getAmount().subtract(new BigDecimal(amount)));
				moneyMarketAccount.setAmount(moneyMarketAccount.getAmount().add(new BigDecimal(amount)));
				accountRepositoryDAO.update(savingAccount);
				accountRepositoryDAO.update(moneyMarketAccount);
			} else {
				return false;
			}
		} else if (fromAccount.equals(money_market) && toAccount.equals(savings)) {
			if (validateAmount(moneyMarketAccount, amount)) {
				savingAccount.setAmount(savingAccount.getAmount().add(new BigDecimal(amount)));
				moneyMarketAccount.setAmount(moneyMarketAccount.getAmount().subtract(new BigDecimal(amount)));
				accountRepositoryDAO.update(savingAccount);
				accountRepositoryDAO.update(chackingAccount);
			} else {
				return false;
			}
		} else if (fromAccount.equals(checking) && toAccount.equals(money_market)) {
			if (validateAmount(chackingAccount, amount)) {
				chackingAccount.setAmount(chackingAccount.getAmount().subtract(new BigDecimal(amount)));
				moneyMarketAccount.setAmount(moneyMarketAccount.getAmount().add(new BigDecimal(amount)));
				accountRepositoryDAO.update(chackingAccount);
				accountRepositoryDAO.update(moneyMarketAccount);
			} else {
				return false;
			}
		} else if (fromAccount.equals(money_market) && toAccount.equals(checking)) {

			if (validateAmount(moneyMarketAccount, amount)) {
				chackingAccount.setAmount(moneyMarketAccount.getAmount().add(new BigDecimal(amount)));
				moneyMarketAccount.setAmount(moneyMarketAccount.getAmount().subtract(new BigDecimal(amount)));
				accountRepositoryDAO.update(chackingAccount);
				accountRepositoryDAO.update(moneyMarketAccount);
			} else {
				return false;
			}
		}
		return true;

	}

}