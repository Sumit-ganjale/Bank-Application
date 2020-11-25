package com.lti.banking.system.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name= "CUSTOMER")
public class Customers implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long customer_id;
	@Column(name = "FIRST_NAME")

	private String firstName;
	@Column(name = "LAST_NAME")

	private String lastName;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "PHONE_NUMBER")
	private Integer phoneNumber;
	@Column(name = "SOCIAL_SECURITY_NUMBER")
	private int socialSecurityNumber;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@Valid
	@NotEmpty
	public Set<Account> account;


	public Customers() {

	}

	@NotBlank(message="FirstName cannot be blank")
	@Size(max=100 ,message="FirstName Size should be in ranges of 10-20")
	@Pattern(regexp="^[^<>\"*={}`^!]*$", message="FirstName value contains invalid characters")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@NotBlank(message="LastName cannot be blank")
	@Size(max=100 ,message="LastName Size should be in ranges of 10-20")
	@Pattern(regexp="^[^<>\"*={}`^!]*$", message="FirstName value contains invalid characters")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@NotBlank(message="Address cannot be blank")
	@Size(max=20,message="Address Size should be in ranges of 10-20")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@NotNull(message="PhoneNumber cannot be blank")
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public void setSocialSecurityNumber(Integer socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public Set<Account> getAccount() {
		return account;
	}

	public void setAccount(Set<Account> account) {
		this.account = account;
	}

	public void addAccount(Account account) {
		account.setCustomer(this);
	}

}