package com.alpha.core.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * AOP打印慢查询
 * 
 * @author Li Yunfa
 * @date 2017年7月2日
 */
@Aspect()
@Component()
public class AopServiceMonitor {

	private static final Logger logger = LoggerFactory.getLogger(AopServiceMonitor.class);

	private static int expireTime = 200;

	@Pointcut(value = "execution(* (com.alpha..service.* || com.alpha..dao.*).*(..))")
	public void monitorResponseTime() {
	}

	@Around("monitorResponseTime()")
	public Object monitor(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object obj = joinPoint.proceed(joinPoint.getArgs());
		long times = System.currentTimeMillis() - start;
		if (times >= expireTime) {
			logger.warn("慢方法响应时间:{}ms,方法:{}.{}()", times, className, methodName);
		}
		return obj;
	}
}
