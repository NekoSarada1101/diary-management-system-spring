package com.example.diary.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Before("execution(* *..*.*Controller.*(..))")
    public void startLog(JoinPoint joinPoint){
        System.out.println("メソッド開始" +joinPoint.getSignature());
    }
}
