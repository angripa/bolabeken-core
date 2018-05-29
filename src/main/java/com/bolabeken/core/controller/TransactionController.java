package com.bolabeken.core.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolabeken.core.dto.CustomResponse;
import com.bolabeken.core.dto.transaction.TransactionDTO;
import com.bolabeken.core.dto.transfer.TransferDTO;
import com.bolabeken.core.mapper.TransactionMapper;
import com.bolabeken.core.mapper.TransferMapper;
import com.bolabeken.core.repository.TransactionsRepository;
import com.bolabeken.core.repository.TransferRepository;

/**
* @Author  : angripa
* @Date    : May 23, 2018
* 
**/
@RestController
public class TransactionController {
	@Autowired
	TransactionsRepository transactionsRepository;
	@Autowired
	TransactionMapper tMapper;
	@Autowired
	TransferRepository transferRepository;
	@Autowired
	TransferMapper tfMapper;
	
	@PostMapping("/transactions/list/{page}/{size}")
	public Object transactionList(Principal principal,@PathVariable("page") int page,@PathVariable("size") int size){
		List<TransactionDTO> list = tMapper.convertToDto(transactionsRepository.findAll(new PageRequest(page, size)));
		if(list.isEmpty()){
			return CustomResponse.buildNotFound();
		}
		return CustomResponse.buildSuccess(list);
	}
	@PostMapping("/transfer/list/{page}/{size}")
	public Object transferList(Principal principal,@PathVariable("page") int page,@PathVariable("size") int size){
		List<TransferDTO> listTransfer = tfMapper.convertToDto(transferRepository.findAll(new PageRequest(page, size)));
		if(listTransfer.isEmpty()){
			return CustomResponse.buildNotFound();
		}
		return CustomResponse.buildSuccess(listTransfer);
	}
}


