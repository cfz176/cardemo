package com.cfz.aspect;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cfz.annotation.SystemLog;
import com.cfz.entity.Customer;
import com.cfz.entity.Customerservice;
import com.cfz.entity.Syslog;
import com.cfz.service.SyslogService;
import com.cfz.utils.IPUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//定义一个切面
@Aspect
@Component //采用组件扫描
public class LogAopAction {
	//	@Aspect 注解 作为切面被读取
    //注入service,用来将日志信息保存在数据库
    @Resource
    private SyslogService syslogService;
    
     //配置接入点
     @Pointcut("execution(* com.cfz.controller..*.*(..))")
     private void controllerAspect(){}//定义一个切入点
 
     @Around("controllerAspect()")
     public Object around(ProceedingJoinPoint pjp) throws Throwable {
         //常见日志实体对象
         Syslog log = new Syslog();
         //获取登录用户账户
         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         log.setUserid(this.getUserID(request.getSession()));
         //获取系统时间
         String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
         log.setDates(time);
         //获取系统ip
         String ip = IPUtil.getIPAddress(request);
         log.setIp(ip);
        //方法通知前获取时间
         long start = System.currentTimeMillis();
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();
        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        if (null != method) {
            // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
            if (method.isAnnotationPresent(SystemLog.class)) {
                SystemLog systemlog = method.getAnnotation(SystemLog.class);
                log.setModule(systemlog.module());
                log.setMethod(systemlog.methods());
                try {
                    object = pjp.proceed();
                    long end = System.currentTimeMillis();
                    //将计算好的时间保存在实体中
                    log.setResponseDate(""+(end-start));
                    log.setCommit("执行成功！");
                    //保存进数据库
                    syslogService.saveLog(log);
                } catch (Throwable e) {
                    long end = System.currentTimeMillis();
                    log.setResponseDate(""+(end-start));
                    log.setCommit("执行失败");
                    syslogService.saveLog(log);
                }
            } else {//没有包含注解
                object = pjp.proceed();
            }
        } else { //不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
     }
     
     public String getUserID(HttpSession  session){
    	 //判断 客服
    	if(session.getAttribute("customer")!=null&&(!(session.getAttribute("customer")+"").equals(""))) {
            Customerservice customer = (Customerservice) session.getAttribute("customer");
            return "客服：" + customer.getId();
    	}else if(session.getAttribute("mid")!=null&&(!(session.getAttribute("mid")+"").equals(""))) {
    		return "工程师："+session.getAttribute("mid").toString();
    	}else  if(session.getAttribute("phone")!=null&&(!(session.getAttribute("phone")+"").equals(""))) {
            Customer phone = (Customer) session.getAttribute("phone");
            return "客户：" + phone.getId();
    	}else {
    		return "未登陆";
    	}
     }
}