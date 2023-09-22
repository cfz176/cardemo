package com.cfz.utils;

/**
 * 返回结果工具类
 * @author AKU
 *
 */
public class PageUtil {
	
	private String code;//返回的编码格式LaUI默认0为正确
	private String count;//列表总数
	private String msg;//返回信息
	private String data;//具体的内容
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code==null?"0":code;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	//同步锁
	@Override
	public synchronized String toString() {
		return "{\"code\":"+this.code+",\"msg\":\""+this.msg+"\",\"count\":"+this.count+",\"data\":"+this.data+"}";
	}
}
