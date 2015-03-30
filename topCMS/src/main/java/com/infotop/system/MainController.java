package com.infotop.system;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotop.common.BasicController;
import com.infotop.system.account.entity.User;
import com.infotop.system.account.service.ShiroDbRealm.ShiroUser;


@Controller
@RequestMapping(value = "/home")
public class MainController extends BasicController {

	
	/**
	 * 首页系统选择页面
	 * @return
	 */
	@RequestMapping(value = "")
	public String mainframe() {
		return "mainFrame";
	}
	
	
	
	
	
}