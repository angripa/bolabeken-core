package com.bolabeken.core.service.impl;

import java.math.BigDecimal;

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
@Service("Withdrawal")
public class Withdrawal extends InitTransaction implements AwanTunaiService {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountBalanceRepository accountBalanceRepository;
	@Override
	public CustomResponse withdrawal(TransactionDTO dto) {
		try {
			Account account = accountRepository.findByUsernameUsername(dto.getUsername());
			if (null == account) {
				CustomResponse.buildNotFound();
			}
			AccountBalance ab= accountBalanceRepository.findByAccountUsernameUsername(dto.getUsername());
			if(null == ab){
				ab=new AccountBalance();
				ab.setAccount(account);
				ab.setBalance(BigDecimal.ZERO);
			}
			if (ab.getBalance().compareTo(dto.getAmount()) < 0)
				return CustomResponse.build(ServiceStatus.WITHDRAWAL_EXCEED_BALANCE);

			ab.setBalance(ab.getBalance().subtract(dto.getAmount()));
			accountBalanceRepository.save(ab);
			return CustomResponse.buildSuccess();
		} catch (Exception e) {
			return CustomResponse.buildUnexpectedErorr();
		}
	}

	@Override
	public CustomResponse topup(TransactionDTO dto) {
		return null;
	}

	@Override
	public CustomResponse transfer(TransferDTO dto) {
		return null;
	}

}
