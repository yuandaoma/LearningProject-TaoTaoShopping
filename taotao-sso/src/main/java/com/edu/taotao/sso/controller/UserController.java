package com.edu.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.taotao.sso.service.IUserService;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月8日
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * 检查用户输入参数 ， type为类型，可选参数1、2、3分别代表username、phone、email
	 * 
	 * @return
	 */
	@RequestMapping(value = "/check/{param}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public Object checkUserInputParam(@PathVariable String param, @PathVariable Integer type, String callback) {
		if ((StringUtils.isNotBlank(param) || type == null) && (1 == type || 2 == type || 3 == type)) { // 参数正常且有效
			TaotaoResult retVal = userService.checkUserInputParam(param, type);
			if (StringUtils.isBlank(callback)) {// 不使用jsonP
				return retVal;
			} else {
				MappingJacksonValue retJsonp = new MappingJacksonValue(retVal);
				retJsonp.setJsonpFunction(callback);
				return retJsonp;
			}
		} else {
			if (StringUtils.isBlank(callback)) {// 不使用jsonp
				return TaotaoResult.build(400, "您输入的数据不符合要求，请检查后重新提交");
			}
			MappingJacksonValue retJsonP = new MappingJacksonValue(TaotaoResult.build(400, "您输入的数据不符合要求，请检查后重新提交"));
			retJsonP.setJsonpFunction(callback);
			return retJsonP;
		}
	}

	/**
	 * 用户注册 //username 用户名 // password 密码 // phone 手机号 // email 邮箱
	 * 
	 * @param tbUser
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userRegister(TbUser tbUser) {
		if (StringUtils.isBlank(tbUser.getUsername())) {
			return TaotaoResult.build(400, "您的注册信息不符合要求，请核对后进行注册");
		}
		try {
			return userService.saveRegiterUserInfo(tbUser);
		} catch (Exception e) {
			return TaotaoResult.build(400, "注册失败");
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userLogin(String username, String password,
			HttpServletRequest request,HttpServletResponse response) {
		try {
			return userService.userLogin(username, password,request,response);
		} catch (Exception e) {
			return TaotaoResult.build(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		try {
			TaotaoResult result = userService.getUserByToken(token);
			if (StringUtils.isBlank(callback)) {
				return result;
			} else {
				MappingJacksonValue retJsonp = new MappingJacksonValue(result);
				retJsonp.setJsonpFunction(callback);
				return retJsonp;
			}
		} catch (Exception e) {
			return TaotaoResult.build(400, e.toString());
		}
	}

	@RequestMapping(value = "/logout/{token}", method = RequestMethod.GET)
	@ResponseBody
	public Object userLogout(@PathVariable String token, String callback) {
		try {
			TaotaoResult retVal = userService.userLogout(token);
			if (StringUtils.isBlank(callback)) {
				return retVal;
			}
			MappingJacksonValue retJsonp = new MappingJacksonValue(retVal);
			retJsonp.setJsonpFunction(callback);
			return retJsonp;
		} catch (Exception e) {
			return TaotaoResult.build(400, "退出登录系统异常");
		}
	}

}
