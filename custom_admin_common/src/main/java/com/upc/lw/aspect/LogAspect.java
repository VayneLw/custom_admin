package com.upc.lw.aspect;

import com.alibaba.fastjson.JSON;
import com.upc.lw.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/14 16:02
 */
@Aspect
@Component
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(log)")
    public void logPointcut(Log log){

    }

    @Around("logPointcut(log)")
    public void logAround(ProceedingJoinPoint joinPoint,Log log) throws Throwable {
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        logger.info("方法 {} 入参: {} ", methodName, JSON.toJSONString(joinPoint.getArgs()));
        Object proceed = joinPoint.proceed();
        logger.info("方法 {} 结果: {} ", methodName, proceed);
    }
}
