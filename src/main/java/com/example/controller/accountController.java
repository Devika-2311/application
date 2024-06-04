package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.account;

import com.example.repo.accountRepo;
import com.example.service.accountService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/account")

public class accountController {
	@Autowired
	accountService as;
	@Autowired
	accountRepo ar;

	

	

	@PutMapping("/deposit")
	public String deposit(@SessionAttribute("username") String username, @RequestParam float amount) {
		as.deposit(username, amount);
		return "deposit successfull";
	}

	@PutMapping("/withdrawal")
    public ModelAndView withdrawal(@SessionAttribute("username") String username, @RequestParam float amount) {
        ModelAndView modelAndView = new ModelAndView();
        boolean withdrawalSuccessful = as.withdrawal(username, amount);
        if (withdrawalSuccessful) {
            modelAndView.addObject("message", "Withdrawal successful");
        } else {
            modelAndView.addObject("message", "Withdrawal unsuccessful: Insufficient balance");
        }
        modelAndView.setViewName("withdrawalResult"); // New view for displaying result
        return modelAndView;
    }


	@GetMapping("/checkBalance")
	public float checkBalance(HttpSession session) {
	    String username = (String) session.getAttribute("username");
	    return as.checkBalance(username);
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@PostMapping("/loginacc")
	public ModelAndView loginacc(@RequestParam String username, @RequestParam String password,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		boolean loginSuccessful = as.login(username, password);
		if (loginSuccessful) {
			
			session.setAttribute("username", username);
			modelAndView.setViewName("redirect:/account/indexPage");
		} else {
			modelAndView.setViewName("login");
			modelAndView.addObject("loginFailed", true);
		}
		return modelAndView;
	}

	@GetMapping("/indexPage")
	public ModelAndView indexPage(HttpSession session) {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("indexPage");
	    String username = (String) session.getAttribute("username");
	    modelAndView.addObject("username", username);
	    return modelAndView;
	}

	@GetMapping("/depositPage")
	public ModelAndView depositPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("depositPage");
		return modelAndView;
	}

	@GetMapping("/changePasswordPage")
	public ModelAndView changePasswordPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("changePasswordPage");
		return modelAndView;
	}

	@GetMapping("/withdrawalPage")
	public ModelAndView withdrawalPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("withdrawalPage");
		return modelAndView;
	}

	@GetMapping("/checkBalancePage")
	public ModelAndView checkBalancePage() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("checkBalancePage");
	    return modelAndView;
	}

	@GetMapping("/editProfile")
	public ModelAndView editProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editProfile");
		return modelAndView;
	}
	@GetMapping("/EmailPage")
	public ModelAndView EmailPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("EmailPage");
		return modelAndView;
	}
	@GetMapping("/PhonePage")
	public ModelAndView PhonePage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("PhonePage");
		return modelAndView;
	}
	@GetMapping("/AddressPage")
	public ModelAndView AddressPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("AddressPage");
		return modelAndView;
	}

	
	@PutMapping("/changeEmail")
	public String changeEmail(@SessionAttribute("username") String username, @RequestParam String email) {
		as.changeEmail(username, email);
		return "email changed successfully";
	}

	@PutMapping("/changePhone")
	public String changePhone(@SessionAttribute("username") String username, @RequestParam String phone) {
		as.changePhone(username, phone);
		return "phone changed  successfully"; 
	}

	@PutMapping("/changeAddress")
	public String changeAddress(@SessionAttribute("username") String username, @RequestParam String address) {
		as.changeAddress(username, address);
		return "address changed  successfully"; 
	}

	@PutMapping("/changePassword")
	public String changePassword(@SessionAttribute("username") String username, @RequestParam String password) {

		as.changePassword(username, password);
		return "password changed successfully";
	}

}
