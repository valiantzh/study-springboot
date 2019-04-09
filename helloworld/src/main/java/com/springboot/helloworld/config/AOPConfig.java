package com.springboot.helloworld.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
public class AOPConfig {
    @Around("@within(org.springframework.stereotype.Controller) ")
    public Object simpleAop(final ProceedingJoinPoint pjp) throws Throwable{
        try{
            Object[] args = pjp.getArgs();
            System.out.println("" + Arrays.asList(args));
            //调用原有方法
            Object o = pjp.proceed();
            System.out.println("return :"+ o);
            return o;
        }catch (Throwable e){
            throw e;
        }
    }
}
