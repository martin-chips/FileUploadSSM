package com.dimple.entity;

/**
 * @program: FileUploadSSM
 * @description: 进度条数据显示
 * @author: Dimple
 * @create: 2018-08-03 11:46
 **/
public class Progress {
	private long bytesRead;//已经上传的字节数
	private long contentLength;//所有文件的总长度
	private long startTime = System.currentTimeMillis();//开始上传的时间
	private int items;//正在上传第几个文件

	public long getBytesRead() {
		return bytesRead;
	}

	public void setBytesRead(long bytesRead) {
		this.bytesRead = bytesRead;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Progress{" +
				"bytesRead=" + bytesRead +
				", contentLength=" + contentLength +
				", startTime=" + startTime +
				", items=" + items +
				'}';
	}
}
