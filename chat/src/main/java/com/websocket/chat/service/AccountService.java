package com.websocket.chat.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.websocket.chat.model.Account;
import com.websocket.chat.repository.AccountRepository;


@Service
public class AccountService implements UserDetailsService{
	
	
	@Autowired
	AccountRepository accounts;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account=accounts.findById(username);
		account.setAuthorities(getAuthorities(username));
		
		UserDetails userDetails=new UserDetails() {
			
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return account.isEnabled();
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return account.isCredentialsNonExpired();
			}
			
			@Override
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return account.isAccountNonLocked();
			}
			
			@Override
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return account.isAccountNonExpired();
			}
			
			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return account.getId();
			}
			
			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return account.getPassword();
			}
			
			@Override
			public Collection getAuthorities() {
				// TODO Auto-generated method stub
				
				return account.getAuthorities();
			}
		};
		return account;
	}
	public Account save(Account account,String role) {
		// TODO Auto-generated method stub

		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setAccountNonExpired(true);
		account.setAccountNonLocked(true);
		account.setCredentialsNonExpired(true);
		account.setEnabled(true);
		return accounts.save(account, role);
	}

	public Collection<GrantedAuthority> getAuthorities(String username) 
	{ 
		List<String> string_authorities = accounts.findauthoritiesbyid(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
		for (String authority : string_authorities) 
		{ 
			authorities.add(new SimpleGrantedAuthority(authority)); 
		} 
		return authorities; 
	}
 
}