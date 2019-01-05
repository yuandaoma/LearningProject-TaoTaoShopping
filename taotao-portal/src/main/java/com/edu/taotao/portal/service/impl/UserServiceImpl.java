package com.edu.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edu.taotao.portal.service.IUserService;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.GsonUtil;
import com.taotao.common.utils.HttpUtil;
import com.taotao.pojo.TbUser;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月10日
 * @description 用户Service，用于调用远程的SSO服务，判断当前是否有用户登录，供拦截器使用
 */
@Service
public class UserServiceImpl implements IUserService {

	@Value("${sso.url}")
	public String ssoUrl;

	@Value("${ssoApi.findUserByToken}")
	public String ssoApiFindUserByToken;

	@Value("${ssoApi.showPageLogin}")
	public String ssoApiShowPageLogin;

	@Override
	public TbUser getUserFromSSOByToken(String token) {
		try {
			String retJson = HttpUtil.doGet(ssoUrl + ssoApiFindUserByToken + token);
			System.out.println("retJson = " + retJson);
			TaotaoResult result = GsonUtil.getGson().fromJson(retJson, TaotaoResult.class);
			if (result.getStatus() == 200) {
				String retDataJson = GsonUtil.getGson().toJson(result.getData());
				return GsonUtil.getGson().fromJson(retDataJson, TbUser.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
