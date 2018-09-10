//package com.njust.chatonline.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class ExceptionHandler {
//    @Pointcut("execution(* com.njust.chatonline.controller..*.*(..))")
//    public void exception() {
//    }
//
//    @Before("exception()")
//    public void before(){
//        System.out.println("===========before");
//    }
//
//    @After("exception()")
//    public void after(){
//        System.out.println("==========after");
//    }
//
//    @AfterReturning("exception()")
//    public void afterreturning(){
//        System.out.println("=========afterreturning");
//    }
//
//    @Around("exception()")
//    public void around(ProceedingJoinPoint joinPoint){
//        System.out.println("=========around");
//        try {
//            joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }
//
//}
