package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.service.IFileService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月2日
 * @description
 */
@Service
public class FileServiceImpl implements IFileService {

	@Value("${ftp.url}")
	private String ftpUrl;
	@Value("${ftp.username}")
	private String ftpUsername;
	@Value("${ftp.password}")
	private String ftpPassword;
	@Value("${ftp.port}")
	private Integer ftpPort;
	@Value("${ftp.filePath}")
	private String ftpFilePath;
	@Value("${ftp.httpUrl}")
	private String ftpHttpUrl;

	@Override
	public Map<String, Object> uploadFile(MultipartFile uploadFile) {
		Map<String, Object> retMap = new HashMap<>();
		if (uploadFile == null || uploadFile.isEmpty()) {
			retMap.put("error", 1);
			retMap.put("message", "不能上传空文件");
			return retMap;
		}
		String originFileName = uploadFile.getOriginalFilename();
		String newFileName = UUID.randomUUID() + originFileName.substring(originFileName.lastIndexOf('.'));
		String filePath = "/" + new DateTime().toString("yyyy-MM-dd") + "/";
		try {
			FtpUtil.uploadFile(ftpUrl, ftpPort, ftpUsername, ftpPassword, ftpFilePath, filePath, newFileName,
					uploadFile.getInputStream());
			retMap.put("error", 0);
			retMap.put("url", ftpHttpUrl + filePath + newFileName);
			return retMap;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retMap;
	}

}
