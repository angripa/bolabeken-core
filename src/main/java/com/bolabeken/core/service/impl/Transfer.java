package com.bolabeken.core.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolabeken.core.domain.Account;
import com.bolabeken.core.domain.AccountBalance;
import com.bolabeken.core.dto.CustomResponse;
import com.bolabeken.core.dto.transaction.TransactionDTO;
import com.bolabeken.core.dto.transfer.TransferDTO;
import com.bolabeken.core.enums.ServiceStatus;
import com.bolabeken.core.repository.AccountBalanceRepository;
import com.bolabeken.core.repository.AccountRepository;
import com.bolabeken.core.service.AwanTunaiService;

/**
 * @Author : angripa
 * @Date : May 23, 2018
 * 
 **/
@Service("Transfer")
public class Transfer extends InitTransfer implements AwanTunaiService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired	
	AccountBalanceRepository accountBalanceRepository;
	@Override
	public CustomResponse withdrawal(TransactionDTO dto) {
		return null;
	}

	@Override
	public CustomResponse topup(TransactionDTO dto) {
		return null;
	}

	@Override
	public CustomResponse transfer(TransferDTO dto) {
		try {
			Account sender = accountRepository.findByUsernameUsername(dto.getUsername());
			if(null == sender){
				return CustomResponse.build(ServiceStatus.ACCOUNT_NOT_FOUND);
			}
			Account recipient = accountRepository.findByUsernameUsername(dto.getAcRecipient());
			if(null == recipient){
				return CustomResponse.build(ServiceStatus.ACCOUNT_NOT_FOUND);
			}
			AccountBalance abSender= accountBalanceRepository.findByAccountUsernameUsername(dto.getUsername());
			if(null == abSender){
				return CustomResponse.build(ServiceStatus.NOT_FOUND);
			}
			AccountBalance abRecipient= accountBalanceRepository.findByAccountUsernameUsername(dto.getAcRecipient());
			if(null == abRecipient){
				abRecipient = new AccountBalance();
				abRecipient.setAccount(recipient);
				abRecipient.setBalance(BigDecimal.ZERO);
			}
			if(abSender.getBalance().compareTo(dto.getAmount())<0)
				return CustomResponse.build(ServiceStatus.INSUFFICIENT_BALANCE);
			//update balance
			abSender.setBalance(abSender.getBalance().subtract(dto.getAmount()));
			abRecipient.setBalance(abRecipient.getBalance().add(dto.getAmount()));
			accountBalanceRepository.save(Arrays.asList(abSender,abRecipient));
			//create history transfer
			dto.setRecipient(recipient);
			dto.setSender(sender);			
			createTransfer(dto);
			return CustomResponse.buildSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponse.buildUnexpectedErorr();
		}
	}

}
