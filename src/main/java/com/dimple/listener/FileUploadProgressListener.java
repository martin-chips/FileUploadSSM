package com.dimple.listener;

import com.dimple.entity.Progress;
import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @program: FileUploadSSM
 * @description: 上传文件的监听器
 * @author: Dimple
 * @create: 2018-08-03 11:50
 **/
@Component
public class FileUploadProgressListener implements ProgressListener {

	private HttpSession session;

	/**
	 * 设置Session，这样能够将状态保存在session域中
	 * @param session
	 */
	public void setSession(HttpSession session) {
		this.session = session;
		Progress status = new Progress();
		session.setAttribute("status", status);
	}

	/**
	 * 文件上传会回调的update方法
	 * @param bytesRead 已经读取到的字节数
	 * @param contentLength 该上传文件的总字节数
	 * @param items 当前是上传第几个文件，默认为1
	 */
	@Override
	public void update(long bytesRead, long contentLength, int items) {
		Progress status = (Progress) session.getAttribute("status");
		status.setBytesRead(bytesRead);
		status.setContentLength(contentLength);
		status.setItems(items);
	}
}
