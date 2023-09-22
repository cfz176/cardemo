package com.cfz.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SendSms {

	/**
	 * 发送短信
	 * @param phoneNum:电话号码
	 * @param code:验证码
	 * @return
	 * @throws IOException
	 */
    public static String sendMessage(String phoneNum,String code) throws IOException {
    	Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = SendSms.class.getClassLoader().getResourceAsStream("sendSms.properties");
        // 使用properties对象加载输入流
        properties.load(in);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", properties.get("accessKeyId").toString(),properties.get("accessSecret").toString());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNum);//接收短信的手机号
        request.putQueryParameter("SignName", "陈富洲的网站");//签名
        request.putQueryParameter("TemplateCode",properties.get("templeteCode").toString());//模板编号
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");//参数
        try {
            CommonResponse response = client.getCommonResponse(request);
           return  response.getData();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * 生成随机码
     * @param lenth:验证码长度
     * @return
     */
    public static String getVerificationCode(Integer lenth) {
    	String str="0123456789";
    	Random random1=new Random();
    	//指定字符串长度，拼接字符并toString
    	StringBuffer sb=new StringBuffer();
    	for (int i = 0; i < lenth; i++) {
    	//获取指定长度的字符串中任意一个字符的索引值
    	 int number=random1.nextInt(str.length());   
    	 //根据索引值获取对应的字符
    	 char charAt = str.charAt(number);
    	      sb.append(charAt);
    	 }
    	  return sb.toString();
    }
}
