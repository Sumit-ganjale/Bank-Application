package com.lti.banking.system.service.impl;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.banking.system.dao.AccountRepositoryDAO;
import com.lti.banking.system.entity.Account;
import com.lti.banking.system.service.AccountService;

@Component
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepositoryDAO accountRepositoryDAO;

	private String savings = "savings";
	private String checking = "checking";
	private String money_market = "money_market";

	@Override
	public BigDecimal deposit(Set<Account> list, String fromAccount, long amount) {
			Account savingAccount = null;
			Account chackingAccount = null;
			Account moneyMarketAccount = null;

			for (Account account : list) {
				if (account.getAccountType().equals(savings)) {
					savingAccount = account;
				} else if (account.getAccountType().equals(checking)) {
					chackingAccount = account;
				} else if (account.getAccountType().equals(money_market)) {
					moneyMarketAccount = account;
				}
				if (fromAccount.equals(savings)) {
						savingAccount.setAmount(savingAccount.getAmount().add(new BigDecimal(amount)));
						accountRepositoryDAO.update(savingAccount);
						return savingAccount.getAmount();
				} else if (fromAccount.equals(checking)) {
					chackingAccount.setAmount(chackingAccount.getAmount().add(new BigDecimal(amount)));
						accountRepositoryDAO.update(chackingAccount);
						return chackingAccount.getAmount();

				} else if (fromAccount.equals(money_market)) {
						moneyMarketAccount.setAmount(moneyMarketAccount.getAmount().add(new BigDecimal(amount)));
						accountRepositoryDAO.update(moneyMarketAccount);
						return chackingAccount.getAmount();
					}
			}
			return null;
		}





	public boolean validateAmount(Account fromAccount, long amount) {
		return fromAccount.getAmount().longValue() > amount;
	}

	@Override
	public synchronized boolean withdraw(Set<Account> list, String fromAccount, long amount) {
		Account savingAccount = null;
		Account chackingAccount = null;
		Account moneyMarketAccount = null;

		for (Account account : list) {
			if (account.getAccountType().equals(savings)) {
				savingAccount = account;
			} else if (account.getAccountType().equals(checking)) {
				chackingAccount = account;
			} else if (account.getAccountType().equals(money_market)) {
				moneyMarketAccount = account;
			}
			if (fromAccount.equals(savings)) {
				if (validateAmount(savingAccount, amount)) {
					savingAccount.setAmount(savingAccount.getAmount().subtract(new BigDecimal(amount)));
					accountRepositoryDAO.update(savingAccount);
				} else {
					return false;
				}
			} else if (fromAccount.equals(checking)) {
				if (validateAmount(chackingAccount, amount)) {
					chackingAccount.setAmount(chackingAccount.getAmount().subtract(new BigDecimal(amount)));
					accountRepositoryDAO.update(chackingAccount);
				} else {
					return false;
				}
			} else if (fromAccount.equals(money_market)) {
				if (validateAmount(moneyMarketAccount, amount)) {
					moneyMarketAccount.setAmount(moneyMarketAccount.getAmount().subtract(new BigDecimal(amount)));
					accountRepositoryDAO.update(moneyMarketAccount);
				} else {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void findByName() {
		// TODO Auto-generated method stub

	}

}
