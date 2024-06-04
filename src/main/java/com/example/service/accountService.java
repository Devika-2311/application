package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.account;
import com.example.repo.accountRepo;

@Service
public class accountService {

	@Autowired
	accountRepo ar;

public accountService(accountRepo ar) {
	        this.ar = ar;
	    }
	
//	 public accountService(accountRepo mockAccountRepo) {
//		// TODO Auto-generated constructor stub
//	}

	@Transactional
	public void deposit( String username, float amount) {
		
		ar.deposit(username, amount);
	}

	 @Transactional
	public boolean withdrawal( String username, float amount) {
		Optional<account> Oldacc = ar.findById(username);
		float balance = Oldacc.get().getBalance();
		if(amount<balance)
		{
			ar.withdrawal(username, amount);
			return true;
		}
		return false;
	}

	
	public float checkBalance(String username) {
		
		Optional<account> Oldacc = ar.findById(username);
		float balance = Oldacc.get().getBalance();
		return balance;
	}
	
	 @Transactional
	public void changePassword( String username, String password) {
		
		ar.changePassword(username,password);
	}
	
	
	public boolean login(String username, String password) {
	   
	    int count =  ar.login(username, password);
	    
	   
	    return count > 0;
	}

	 @Transactional
	public void changeEmail( String username,String email) {
		
		ar.changeEmail(username,email);
	}
	 @Transactional
	public void changePhone(String username,String phone) {
		
		ar.changePhone(username,phone);
	}
	 @Transactional
	public void changeAddress(String username, String address) {
		
		ar.changeAddress(username,address);
	}
	
	
	public ModelAndView editProfile()
	{
		ModelAndView mv=new ModelAndView("editProfile.html");
		return mv;
	}
	
}
