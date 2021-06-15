package com.behere.dynamic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.behere.dynamic.service.UserService2;

@Service
@DS("slave")
public class UserServiceImpl2 implements UserService2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List selectAll() {
		return jdbcTemplate.queryForList("select * from T_WITHDRAWAL");
	}

	@Override
	@DS("slave_2")
	public List selectByCondition() {
		return jdbcTemplate.queryForList("select * from T_USER_AUTH");
	}
}