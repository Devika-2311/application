package com.example.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class account {
    private String name;
    @Id
    private String username;
    @NotNull
    @Column(unique=true)
    private String password;
    private int age;
    private String ssn;
    private String address;
    private String email;
    private String phone;
    private float balance;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "account [name=" + name + ", username=" + username + ", password=" + password + ", age=" + age + ", ssn="
				+ ssn + ", address=" + address + ", email=" + email + ", phone=" + phone + ", balance=" + balance + "]";
	}
	public account(String name, String username,String password, int age, String ssn, String address,
			String email, String phone, float balance) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.age = age;
		this.ssn = ssn;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.balance = balance;
	}
	public account() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
