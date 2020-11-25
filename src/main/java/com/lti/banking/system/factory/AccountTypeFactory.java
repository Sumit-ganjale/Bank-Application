package com.lti.banking.system.factory;

import com.lti.banking.system.entity.Account;
import com.lti.banking.system.entity.AccountType;
import com.lti.banking.system.entity.ChackingAccount;
import com.lti.banking.system.entity.MoneyMarket;
import com.lti.banking.system.entity.SavingAccount;

public class AccountTypeFactory {

	public Account getAccount(AccountType accountType) {

		switch (accountType) {

		case SAVING:
			return new SavingAccount();
		case CHACKING:
			return new ChackingAccount();
		case MONEYMARKET:
			return new MoneyMarket();
		default:
			return new SavingAccount();

		}

	}
}
