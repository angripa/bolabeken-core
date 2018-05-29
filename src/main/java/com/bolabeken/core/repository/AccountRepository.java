package com.bolabeken.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolabeken.core.domain.Account;

/**
* @Author  : angripa
* @Date    : May 23, 2018
* 
**/
public interface AccountRepository extends JpaRepository<Account, Long>{	
	public Account findByUsernameUsername(String username);
}


