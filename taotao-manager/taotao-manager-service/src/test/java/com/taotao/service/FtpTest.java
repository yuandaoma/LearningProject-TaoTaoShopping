package com.taotao.service;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月2日
 * @description 测试FTP文件上传
 */
public class FtpTest {

	@Test
	public void testFtpFileUpload() throws Exception {
		FTPClient client = new FTPClient();
		// 连接Ftp服务器
		client.connect("192.168.37.128");
		// 登录
		client.login("ftpuser", "ftpuser");
		// 更改上传目录
		client.changeWorkingDirectory("/home/ftpuser/upload");
		client.setFileType(FTP.BINARY_FILE_TYPE);
		FileInputStream is = new FileInputStream(new File("C:\\Users\\Administrator\\Pictures\\擎天柱.jpg"));
		client.storeFile("hello.jpg", is);
		is.close();
		client.logout();
	}

}
