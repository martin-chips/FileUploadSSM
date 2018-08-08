package com.dimple.controller;

import com.dimple.entity.Progress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @program: FileUploadSSM
 * @description: 上传文件的控制器
 * @author: Dimple
 * @create: 2018-08-03 11:53
 **/
@Controller
public class FileUploadController {
	/**
	 * 文件上传的处理
	 *
	 * @param request request用于获取Session，方便向session存值
	 * @param file    文件上传类
	 * @return 文件上传状态字符串：ok，error
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile[] file) {
		String path = request.getSession().getServletContext().getRealPath("upload");
		for (int i = 0; i < file.length; i++) {
			String fileName = file[i].getOriginalFilename();//获取原始的文件名称
			System.out.println(path);
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();//创建文件夹
			}
			try {
				file[i].transferTo(targetFile);//将文件转移到指定的文件夹中
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}
		return "ok";
	}

	/**
	 * 用于处理客户端轮询获取文件上传进度
	 * @param request 用于从session中取出值
	 * @return 如果文件上传完毕返回stop，告诉客户端停止轮询；如果还没有上传完，返回当前进度。
	 * @throws IOException
	 */
	@RequestMapping("getInfo")
	@ResponseBody
	public String getProgress(HttpServletRequest request) throws IOException {
		Progress progress = (Progress) request.getSession().getAttribute("status");
		System.out.println(progress);
		DecimalFormat decimalFormat = new DecimalFormat("0");
		String result = decimalFormat.format((float) progress.getBytesRead() / (float) progress.getContentLength() * 100);
		if ("100".equals(result)) {
			return "stop";
		}
		return result;
	}
}