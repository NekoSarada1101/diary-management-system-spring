package com.example.diary.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Around("execution(* *..*.*Controller.*(..))")
    public Object startLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("メソッド開始：" + joinPoint.getSignature());
        Object[] names = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        Object[] values = joinPoint.getArgs();
        for (int i = 0; i < values.length; i++) {
            System.out.println(names[i] + " = " + values[i]);
        }
        System.out.println();

        try {
            Object result = joinPoint.proceed();

            System.out.println("メソッド終了：" + joinPoint.getSignature());

            return result;

        } catch (Exception e) {
            System.out.println("メソッド異常終了：" + joinPoint.getSignature());
            e.printStackTrace();
            throw e;
        }
    }

    @Around("execution(* *..*.*Dao*.*(..))")
    public Object daoLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("メソッド開始：" + joinPoint.getSignature());
        Object[] names = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        Object[] values = joinPoint.getArgs();
        for (int i = 0; i < values.length; i++) {
            System.out.println(names[i] + " = " + values[i]);
        }

        System.out.println();

        try {
            Object result = joinPoint.proceed();

            System.out.println("メソッド終了：" + joinPoint.getSignature());

            return result;

        } catch (Exception e) {
            System.out.println("メソッド異常終了：" + joinPoint.getSignature());
            e.printStackTrace();
            throw e;
        }
    }
}
