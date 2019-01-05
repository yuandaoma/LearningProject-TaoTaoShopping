package com.edu.taotao.portal.service;

import org.springframework.beans.factory.annotation.Value;

import com.taotao.pojo.TbUser;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月10日
 * @description
 */
public interface IUserService {


	/**
	 * 从单点登录系统依据Token获取用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	TbUser getUserFromSSOByToken(String token);
}
