package com.websocket.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websocket.chat.mapper.AccountMapper;
import com.websocket.chat.model.Account;
import com.websocket.chat.service.AccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;

	@Autowired
	AccountMapper accountMapper;
	
	
	
	
	/*ADMIN 계정 부여
	@GetMapping("/create")
	public Account create(){
		Account account=new Account();
		account.setId("admin");
		account.setPassword("1234");
		accountService.save(account, "ROLE_ADMIN");
		return account;
	}
	*/
	
	@GetMapping("/members")
	public String members(Model model) {
		return "account/signup";
	}
	
	@PostMapping("/members")
	//@ResponseBody
	public String addUser(@RequestParam String id, @RequestParam String password) {
		Account account = new Account();
		account.setId(id);
		account.setPassword(password);
		accountService.save(account, "ROLE_USER");
		return "redirect:/login";
	}

}