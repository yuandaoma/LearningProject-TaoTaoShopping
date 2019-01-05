package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.GsonUtil;
import com.taotao.service.IFileService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月2日
 * @description 文件上传Controller 
 */
@Controller
public class FileController {
	
	@Autowired
	private IFileService fileService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String getFileUploadResult(MultipartFile uploadFile) {
		Map<String, Object> retMap = fileService.uploadFile(uploadFile);
		return GsonUtil.getGson().toJson(retMap);
	}

}
