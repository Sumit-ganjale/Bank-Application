package com.lti.banking.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private long account_id;
	@Column(name = "ACCOUNT_TYPE")
	private String accountType;
	@Column(name = "AMOUNT")

	private BigDecimal amount;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "ID",referencedColumnName = "ID", nullable = false)

	@Valid
	private Customers customer;
	@Column(name="ACCOUNT_NUMBER")

	private long accountNumber;

	public Customers getCustomer() {
		return customer;
	}
	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
@NotNull(message="AccountNumber cannot be blank")
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Account() {
	}


	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	@NotBlank(message="Account Type cannot be empty!")
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@NotNull(message="Amount can not be null!")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



}
