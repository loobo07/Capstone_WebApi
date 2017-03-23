package com.cassandrabankapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cassandrabankapp.domain.Account;
import com.cassandrabankapp.domain.Member;
import com.cassandrabankapp.dto.RegisterForm;
import com.cassandrabankapp.repository.AccountRepository;
import com.cassandrabankapp.repository.MemberRepository;
import com.cassandrabankapp.dto.UserDetailsImpl;

@Service
public class MemberService implements UserDetailsService{

private static Logger logger = LoggerFactory.getLogger(Member.class);
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired 
	AccountRepository accountRepository;
	
	public void registerNewMember(RegisterForm registerForm) {
		Member member = new Member();
		Account account = new Account();
		member.setUsername(registerForm.getUsername());
		member.setFullname(registerForm.getFullname());
		member.setEmail(registerForm.getEmail());
		member.setPassword(registerForm.getPassword());
		member.setAccountNumber(registerForm.getaccountNumber());
		memberRepository.save(member);
		int count = accountRepository.existingAccount(registerForm.getaccountNumber());

		if(count == 0) {
			account.setAccountNumber(registerForm.getaccountNumber());
			accountRepository.save(account);
			logger.info(account.getAccountNumber());
		}
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUsername(username);
		if (member == null)
			throw new UsernameNotFoundException(username);
		
		return new UserDetailsImpl(member);
	}
	
	
	public boolean generateForgotPasswordBody(String username) {
		Member member = memberRepository.findByUsername(username);
		
		StringBuilder line = new StringBuilder();
		
		if (member != null) {
			line.append("Member: " + member.getFullname() + "\n\n");
			line.append("Your password is: " + member.getPassword() + "\n\n");
			line.append("CassandraWebTrader\n");
			logger.info(line.toString());
			//mailService.sendGenericMail(member.getEmail(), "CassandraWebTrader Forgot Password", line.toString());
			return true;
		} else {
			logger.info("Member not found!");
			return false;
		}
	}
}
