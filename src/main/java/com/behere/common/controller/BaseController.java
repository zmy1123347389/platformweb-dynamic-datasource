package com.behere.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.behere.common.utils.ShiroUtils;
import com.behere.system.dao.UserRoleDao;
import com.behere.system.domain.UserDO;
import com.behere.system.domain.UserRoleDO;

@Controller
public class BaseController {
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	public UserDO getUser() {
		UserDO user = ShiroUtils.getUser();
		String userId = user.getUserId();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		List<UserRoleDO> list = userRoleDao.list(map);
		ArrayList<String> roleIds = new ArrayList<String>();
		for (UserRoleDO userRoleDO : list) {
			roleIds.add(userRoleDO.getRoleId());
		}
		user.setRoleIds(roleIds);
		return ShiroUtils.getUser();
	}

	public String getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}

	public String getDeptId() {
		return getUser().getDeptId();
	}

	public List<String> getRoleIds() {
		return getUser().getRoleIds();
	}
}