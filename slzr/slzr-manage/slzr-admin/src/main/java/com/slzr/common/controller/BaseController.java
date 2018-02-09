package com.slzr.common.controller;

import com.slzr.common.utils.ShiroUtils;
import org.springframework.stereotype.Controller;
import com.slzr.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}

}