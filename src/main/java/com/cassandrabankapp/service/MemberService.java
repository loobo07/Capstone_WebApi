package com.cassandrabankapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassandrabankapp.domain.Member;
import com.cassandrabankapp.dto.RegisterForm;
import com.cassandrabankapp.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService{

private static Logger logger = LoggerFactory.getLogger(Member.class);
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void registerNewMember(RegisterForm registerForm) {
		Member member = new Member();
		member.setUsername(registerForm.getUsername());
		member.setFullname(registerForm.getFullname());
		member.setEmail(registerForm.getEmail());
		member.setPassword(registerForm.getPassword());
		member.setAccountNumber(registerForm.getaccountNumber());
		
		memberRepository.save(member);
	}
}
