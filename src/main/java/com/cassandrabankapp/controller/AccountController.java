package com.cassandrabankapp.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cassandrabankapp.domain.Account;
import com.cassandrabankapp.domain.Member;
import com.cassandrabankapp.dto.AccountForm;
import com.cassandrabankapp.repository.AccountRepository;
import com.cassandrabankapp.service.MemberService;
import com.cassandrabankapp.util.AccountMapper;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AccountMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showAccount(Model model) {
		Member member = memberService.getCurrentMember();
		Account account = accountRepository.findByAccountNumber(member.getAccountNumber());
		model.addAttribute("account", account);
		
		return "account";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String postAccountForm(@ModelAttribute("accountForm") @Valid AccountForm accountForm, BindingResult result) {
		if(result.hasErrors())
			return "account";
		
		Account account = mapper.map(accountForm);
		
		accountRepository.save(account);
		
		return "redirect:/account";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView editAccountPage(@RequestParam(value = "accountNumber", required = true) String accountNumber) {
		ModelAndView modelAndeView = new ModelAndView("account-edit");
		
		Account account = accountRepository.findByAccountNumber(accountNumber);
		AccountForm accountForm = mapper.map(account);
		modelAndeView.addObject(accountForm);
		return modelAndeView;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String postAccountPage(@ModelAttribute("AccountForm") @Valid AccountForm accountForm, BindingResult result) {
		if (result.hasErrors())
			return "account-edit";
		
		Account account = mapper.map(accountForm);
		
		accountRepository.save(account);
		
		return "redirect:/account";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteWatchlistPage(@RequestParam(value = "symbol", required = true) String accountNumber) {
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		
		accountRepository.delete(account);
		
		return "redirect:/account";		
	}
	
}
