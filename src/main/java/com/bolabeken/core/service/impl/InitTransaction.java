package com.bolabeken.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bolabeken.core.domain.Account;
import com.bolabeken.core.domain.Transactions;
import com.bolabeken.core.dto.transaction.TransactionDTO;
import com.bolabeken.core.dto.transfer.TransferDTO;
import com.bolabeken.core.mapper.TransactionMapper;
import com.bolabeken.core.repository.AccountRepository;
import com.bolabeken.core.repository.TransactionsRepository;

/**
* @Author  : angripa
* @Date    : May 23, 2018
* 
**/
public class InitTransaction {
	@Autowired
	TransactionsRepository transactionsRepository;
	@Autowired
	TransactionMapper mapper;
	@Autowired
	AccountRepository accountRepository;
	public void initTransactions(TransactionDTO dto){
		Account acc = accountRepository.findByUsernameUsername(dto.getUsername());
		Transactions t = mapper.convertToEntity(dto);
		t.setAccount(acc);
		transactionsRepository.save(t);	
	}
	public void initTransfer(TransferDTO dto){
//		Account acc = accountRepository.findByUsernameUsername(dto.getUsername());
//		Transactions t = mapper.convertToEntity(dto);
//		t.setAccount(acc);
//		transactionsRepository.save(t);	
	}
}


