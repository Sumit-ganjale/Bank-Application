package com.lti.banking.system.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.banking.system.entity.Account;
import com.lti.banking.system.entity.Customers;
import com.lti.banking.system.entity.Errors;
import com.lti.banking.system.service.AccountService;
import com.lti.banking.system.service.CustomerService;
import com.lti.banking.system.service.FundTransferService;

@RestController
@RequestMapping(path = "/Bank")
public class BankController {

	@Autowired
	CustomerService customerService;

	@Autowired
	AccountService accountService;

	@Autowired
	FundTransferService fundTransferService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(path = "/getCustomer", produces = "application/json")
	public ResponseEntity getAllCustomer() {
		List<Customers> customer = this.customerService.findAll();
		if (customer != null)
			return new ResponseEntity(customer, HttpStatus.OK);
		else
			return new ResponseEntity("No Customer Found", HttpStatus.BAD_REQUEST);

	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/addCustomer")
	public ResponseEntity addCustomer(@RequestBody Customers customer) {
		try {
			Customers customer1 = this.customerService.findByID(customer.getFirstName(), customer.getPhoneNumber());
			if(customer1==null){
			customerService.addCustomer(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body("Account  Created successfully");
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Already Exists");
			}
		} catch (ConstraintViolationException e) {
			List<Errors> message = createMessage(e.getConstraintViolations());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					"Server encountered internal error,please contact system administrator");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(path = "/{firstName}/{phoneNumber}", produces = "application/json")
	public ResponseEntity findById(@PathVariable(value = "firstName") String firstName,
			@PathVariable(value = "phoneNumber") Integer phoneNumber) {
		Customers customer = this.customerService.findByID(firstName, phoneNumber);
		if (customer != null)
			return new ResponseEntity(customer, HttpStatus.OK);
		else
			return new ResponseEntity("No Customer Found", HttpStatus.BAD_REQUEST);
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/delete")
	public ResponseEntity deleteCustomer(@RequestBody Customers customer) {
		try {
			customerService.deleteCustomer(customer);
			return ResponseEntity.status(HttpStatus.OK).body("Customers deleted successfully");

		} catch (ConstraintViolationException e) {
			List<Errors> message = createMessage(e.getConstraintViolations());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					"Server encountered internal error please contact system administator");
		}

	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/update")
	public ResponseEntity updateCustomer(@RequestBody Customers customer) {

		try {
			customerService.updateCustomer(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body("Customer updated successfully");

		} catch (ConstraintViolationException e) {
			List<Errors> message = createMessage(e.getConstraintViolations());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					"Server encountered internal error please contact system administator");
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(path = "/Account/{firstName}/{phoneNumber}", produces = "application/json")
	public ResponseEntity getAccountDetails(@PathVariable(value = "firstName") String firstName,
			@PathVariable(value = "phoneNumber") Integer phoneNumber) {
		try {
			Set<Account> accountList = customerService.getAccountDetails(firstName, phoneNumber);
			if (accountList != null)
				return new ResponseEntity(accountList, HttpStatus.OK);
			else
				return new ResponseEntity("No Customer Found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					"Server encountered internal error please contact system administator");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/fundTransfer")
	public ResponseEntity fundTransfer(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "phoneNumber") Integer phoneNumber,
			@RequestParam(value = "toAccount") String toAccount,
			@RequestParam(value = "fromAccount") String fromAccount,
			 @RequestParam(value = "amount") long amount) {
		Set<Account> accountList = customerService.getAccountDetails(firstName, phoneNumber);
		if (this.fundTransferService.tranferToOwnBankAccounts(accountList, fromAccount, toAccount, amount)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Fund transfer successfull");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
		}
	}

	@PutMapping("/deposit")
	public ResponseEntity deposit(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "phoneNumber") Integer phoneNumber,
			@RequestParam(value = "toAccount") String toAccount, @RequestParam(value = "amount") long amount) {
		Set<Account> accountList = customerService.getAccountDetails(firstName, phoneNumber);
		BigDecimal amounts=this.accountService.deposit(accountList, toAccount, amount);
			return ResponseEntity.status(HttpStatus.CREATED).body("Amount of " +amount+ "$ credited successfully account balance is "+amounts+"$");

	}
	@PutMapping("/withdraw")
	public ResponseEntity withdraw(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "phoneNumber") Integer phoneNumber,
			@RequestParam(value = "fromAccount") String fromAccount,
			 @RequestParam(value = "amount") long amount) {
		Set<Account> accountList = customerService.getAccountDetails(firstName, phoneNumber);
		if (this.accountService.withdraw(accountList, fromAccount, amount)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Amount withdraw successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
		}
	}

	private List<Errors> createMessage(Set<ConstraintViolation<?>> set) {
		List<Errors> list = new ArrayList<Errors>();
		for (ConstraintViolation violation : set) {
			Errors error = new Errors();
			String attribute = violation.getPropertyPath().toString();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Error parsing Attribute :");

			stringBuilder.append(attribute);
			stringBuilder.append(",");
			stringBuilder.append(" ");
			stringBuilder.append("reason : ");
			stringBuilder.append(violation.getMessage());
			error.setMessage(stringBuilder.toString());
			error.setStatusCode(400);
			list.add(error);
		}
		return list;

	}
}