package com.bolabeken.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolabeken.core.domain.Account;
import com.bolabeken.core.domain.User;
import com.bolabeken.core.dto.CustomResponse;
import com.bolabeken.core.dto.UserDTO;
import com.bolabeken.core.enums.ServiceStatus;
import com.bolabeken.core.mapper.UserMapper;
import com.bolabeken.core.repository.AccountRepository;
import com.bolabeken.core.repository.UserRepository;

/**
 * @Author : angripa
 * @Date : Apr 25, 2018
 * 
 **/

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;	
	private UserMapper userMapper;
	private AccountRepository accountRepository;
		
	@Autowired
	public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,UserMapper userMapper,AccountRepository accountRepository) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userMapper=userMapper;
		this.accountRepository=accountRepository;
	}

	@PostMapping("/register")
	public Object signUp(@RequestBody UserDTO userDTO,BindingResult bindingResult) {
		LOG.debug("Incoming request for sign-up");
		validate(bindingResult);
		try {
			if (null != userRepository.findByUsername(userDTO.getUsername())) {
				return CustomResponse.build(ServiceStatus.USER_EXIST);
			}
			User user = userMapper.convertToEntity(userDTO);
			user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
			user.setEnabled(true);
			user.setAccountNonLocked(true);
			user.setAccountNonExpired(true);
			user.setCredentialsNonExpired(true);
			userRepository.save(user);
			Account acc = new Account();
			acc.setUsername(user);
			acc.setAddress(userDTO.getAddress());
			acc.setEmail(userDTO.getEmail());
			accountRepository.save(acc);
			return CustomResponse.buildSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponse.buildUnexpectedErorr();
		}

	}
}
