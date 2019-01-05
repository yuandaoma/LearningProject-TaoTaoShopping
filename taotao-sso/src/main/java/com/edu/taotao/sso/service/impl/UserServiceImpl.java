package com.edu.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.edu.taotao.sso.dao.IJedisDao;
import com.edu.taotao.sso.service.IUserService;
import com.google.gson.Gson;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.GsonUtil;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserCriteria;
import com.taotao.pojo.TbUserCriteria.Criteria;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月8日
 * @description
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private TbUserMapper tbUserMapper;

	@Autowired
	private IJedisDao jedisDao;

	@Value("${REDIS_KEY_USER_TOKEN}")
	private String REDIS_KEY_USER_TOKEN;

	@Value("${REDIS_KEY_USER_EXPIRE}")
	private Integer REDIS_KEY_USER_EXPIRE;

	@Override
	public TaotaoResult checkUserInputParam(String param, Integer type) {
		TbUserCriteria example = new TbUserCriteria();
		Criteria criteria = example.createCriteria();
		switch (type) { // 对数据进行校验：1、2、3分别代表username、phone、email
		case 1:
			criteria.andUsernameEqualTo(param);
			break;
		case 2:
			criteria.andPhoneEqualTo(param);
			break;
		case 3:
			criteria.andEmailEqualTo(param);
			break;
		default:
			break;
		}
		List<TbUser> userList = tbUserMapper.selectByExample(example);
		if (userList == null || userList.size() == 0) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult saveRegiterUserInfo(TbUser tbUser) {
		tbUser.setCreated(new Date());
		tbUser.setUpdated(new Date());
		tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
		tbUserMapper.insert(tbUser);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult userLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		TbUserCriteria example = new TbUserCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> users = tbUserMapper.selectByExample(example);
		if (users == null || users.size() != 1) {
			return TaotaoResult.build(250, "用户名或密码错误,请重新登录!");
		}
		TbUser loginUser = users.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(loginUser.getPassword())) {
			return TaotaoResult.build(250, "用户名或密码错误,请重新登录!");
		} else {
			String token = UUID.randomUUID().toString();
			loginUser.setPassword(null);
			jedisDao.set(REDIS_KEY_USER_TOKEN + ":" + token, GsonUtil.getGson().toJson(loginUser));
			jedisDao.expire(REDIS_KEY_USER_TOKEN + ":" + token, REDIS_KEY_USER_EXPIRE);
			// 设置cookies信息
			CookieUtils.setCookie(request, response, "TT_TOKEN", token);
			return TaotaoResult.ok(token);
		}
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		String retJson = jedisDao.get(REDIS_KEY_USER_TOKEN + ":" + token);
		if (StringUtils.isBlank(retJson)) {
			return TaotaoResult.build(300, "您的登录已过期，请重新登录！");
		} else {
			return TaotaoResult.ok(GsonUtil.getGson().fromJson(retJson, TbUser.class));
		}
	}

	@Override
	public TaotaoResult userLogout(String token) {
		try {
			jedisDao.del(REDIS_KEY_USER_TOKEN + ":" + token);
		} catch (Exception e) {
			return TaotaoResult.build(400, "用户退出登录redis异常");
		}
		return TaotaoResult.ok();
	}

}
