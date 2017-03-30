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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cassandrabankapp.domain.Account;
import com.cassandrabankapp.domain.Member;
import com.cassandrabankapp.dto.AccountForm;
import com.cassandrabankapp.repository.AccountRepository;
import com.cassandrabankapp.service.MemberService;

@Controller
@RequestMapping("/account")
public class AccountController {

	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showAccount(Model model) {
		Member member = memberService.getCurrentMember();
		Account account = accountRepository.findByAccountNumber(member.getAccountNumber());
		
		model.addAttribute("account", account);
		model.addAttribute("accountForm",new AccountForm());
		
		return "account";
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String postAccountForm(@ModelAttribute("accountForm") @Valid AccountForm accountForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			logger.info("ERROR: "+ result.toString());
			return "account";
		}
		Member member = memberService.getCurrentMember();
		Account account = accountRepository.findByAccountNumber(member.getAccountNumber());
		double newBalance = account.getBalance() - accountForm.getBalance();
		account.setBalance(newBalance);
		logger.info("Current Bal: " + Double.toString(accountForm.getBalance()));
		logger.info("Sub: " + Double.toString(newBalance));
		if(newBalance >= 0 ){
			accountRepository.save(account);
		}
		else {
			redirectAttributes.addFlashAttribute("flashType", "error");
			redirectAttributes.addFlashAttribute("flashMessage", "Not Enough money in bank!!");
		}
		return "redirect:/account";
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String postAccountPage(@ModelAttribute("accountForm") @Valid AccountForm accountForm, BindingResult result) {
		if (result.hasErrors())
			return "account";
		Member member = memberService.getCurrentMember();
		Account account = accountRepository.findByAccountNumber(member.getAccountNumber());
		double newBalance = account.getBalance() + accountForm.getBalance();
		account.setBalance(newBalance);
		logger.info("Current Bal: " + Double.toString(accountForm.getBalance()));
		logger.info("Sum: " + Double.toString(newBalance));
		accountRepository.save(account);
		return "redirect:/account";
	}
	
}
