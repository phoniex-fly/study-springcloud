package com.example.hystrix;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
@Aspect
public class HystrixAspect {

    /**
     * execution(* com.example.controller..*.*(..))
     * execution(任意返回类型 包名..类名。方法名（任意参数）)
     */
    public static final String POINTCUT = "execution(* com.example.controller..*.*(..))";

    private static Map<String, Hystrix> hystrixMap = new HashMap<>();

    static {
        hystrixMap.put("order-service", new Hystrix());//每个服务都有自己的断路器
    }

    @Around(value = POINTCUT)
    public Object around(ProceedingJoinPoint joinPoint) {
        Object res = null;
        Hystrix hystrix = hystrixMap.get("order-service");
        HystrixStatus status = hystrix.status;
        switch (status) {
            case OPEN:
                return "断路器OPEN";
            case CLOSE:
                try {
                    return joinPoint.proceed();
//                    return "断路器CLOSE成功";
                } catch (Throwable throwable) {
                    //调用失败，记录失败次数
                    hystrix.failCountAdd();
                    return "断路器CLOSE失败";
                }
            case HALF_CLOSE:
                //半开的时候，少许流量尝试
                int i = new Random().nextInt(5);
                System.out.println("随机数：" + i);
                if (i == 1) {//20%的几率尝试
                    try {
                        joinPoint.proceed();
                        //状态置为关闭
                        hystrix.setStatus(HystrixStatus.CLOSE);
                        return "断路器HALF_CLOSE成功";
                    } catch (Throwable throwable) {
                        //不置为open,否则会一直open下去
                        return "断路器HALF_CLOSE失败";
                    }
                }
            default:
                return "default:" + status;
        }
    }
}
