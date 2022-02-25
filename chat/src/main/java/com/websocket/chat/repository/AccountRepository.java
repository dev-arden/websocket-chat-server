package com.websocket.chat.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.websocket.chat.mapper.AccountMapper;
import com.websocket.chat.model.Account;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
	
	private final AccountMapper accountMapper;
	
	public int login(String id, String password) {
		return accountMapper.login(id, password);
	}
	
	public Account save(Account account,String role){
		accountMapper.insertUser(account);
		accountMapper.insertUserAutority(account.getId(), role);
		return account;
	}

	public Account findById(String username) {
		// TODO Auto-generated method stub
		return accountMapper.readAccount(username);
	}
	
	public List<String>findauthoritiesbyid(String username){
		return accountMapper.readAutorities(username);
	}
}