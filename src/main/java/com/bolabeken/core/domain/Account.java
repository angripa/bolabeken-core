package com.bolabeken.core.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
* @Author  : angripa
* @Date    : May 23, 2018
* 
**/
@Getter
@Setter
@Entity
@Table(name="account")
public class Account extends AuditableEntity<Long>{
	@Basic(optional = false)
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.LAZY)
	private User username;
	@Column(name="address")
	private String address;
	@Column(name="email")
    private String email;
}


