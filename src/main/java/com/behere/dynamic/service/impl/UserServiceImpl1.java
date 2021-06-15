package com.behere.dynamic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.behere.dynamic.service.UserService1;

@Service
@DS("slave")
public class UserServiceImpl1 implements UserService1 {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List selectAll() {
		return jdbcTemplate.queryForList("select * from lawapp_answer_info");
	}

	@Override
	@DS("slave_1")
	public List selectByCondition() {
		return jdbcTemplate.queryForList("select * from lawapp_app_no");
	}
}