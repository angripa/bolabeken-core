package com.bolabeken.core.service;

import com.bolabeken.core.dto.CustomResponse;
import com.bolabeken.core.dto.transaction.TransactionDTO;
import com.bolabeken.core.dto.transfer.TransferDTO;

/**
* @Author  : angripa
* @Date    : May 23, 2018
* 
**/
public interface AwanTunaiService {
	public CustomResponse withdrawal(TransactionDTO dto);
	
	public CustomResponse topup(TransactionDTO dto);
	
	public CustomResponse transfer(TransferDTO dto);
}


