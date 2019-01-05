package com.edu.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月8日
 * @description
 */
public interface IUserService {

	/**
	 * 检查用户输入的参数
	 * 
	 * @param param
	 * @param type
	 * @return
	 */
	TaotaoResult checkUserInputParam(String param, Integer type);

	/**
	 * 用户注册 //username 用户名 // password 密码 // phone 手机号 // email 邮箱
	 * 
	 * @param tbUser
	 * @return
	 */
	TaotaoResult saveRegiterUserInfo(TbUser tbUser);

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @param response
	 * @param request
	 * @return
	 */
	TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 依据Token获取session中是否有用户
	 * 
	 * @param token
	 * @return
	 */
	TaotaoResult getUserByToken(String token);

	/**
	 * 用户退出登录
	 * 
	 * @param token
	 * @return
	 */
	TaotaoResult userLogout(String token);

}
