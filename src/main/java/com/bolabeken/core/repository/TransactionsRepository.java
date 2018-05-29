package com.bolabeken.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolabeken.core.domain.Transactions;

/**
* @Author  : angripa
* @Date    : May 23, 2018
* 
**/
public interface TransactionsRepository extends JpaRepository<Transactions, Long>{

}


