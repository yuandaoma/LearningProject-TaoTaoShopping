package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月2日
 * @description 
 */
public interface IFileService {

	/**
	 * 上传文件
	 * @param uploadFile
	 * @return
	 */
	Map<String, Object> uploadFile(MultipartFile uploadFile);

}
