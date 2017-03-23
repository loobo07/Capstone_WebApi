package com.cassandrabankapp.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.cassandrabankapp.domain.Account;
import com.cassandrabankapp.domain.Member;
import com.cassandrabankapp.dto.AccountForm;
import com.cassandrabankapp.repository.AccountRepository;
import com.cassandrabankapp.service.MemberService;
import com.cassandrabankapp.util.AccountMapper;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AccountMapper mapper;
	
	private Member member = memberService.getCurrentMember();
	
	@RequestMapping(method = RequestMethod.GET)
	public String showAccount(Model model) {
		
		Account account = accountRepository.findByAccountNumber(member.getAccountNumber());
		logger.info(Double.toString(account.getBalance()));
		model.addAttribute("account", account);
		
		return "account";
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String postAccountForm(@ModelAttribute("accountForm") @Valid AccountForm accountForm, BindingResult result) {
		if(result.hasErrors())
			return "account";
		
		Account account = mapper.map(accountForm);
		
		accountRepository.save(account);
		
		return "redirect:/account";
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String postAccountPage(@ModelAttribute("AccountForm") @Valid AccountForm accountForm, BindingResult result) {
		if (result.hasErrors())
			return "account-edit";
		
		Account account = mapper.map(accountForm);
		
		accountRepository.save(account);
		
		return "redirect:/account";
	}
	
}
