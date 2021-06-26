package com.example.hystrix;

import lombok.Data;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Hystrix {

    /**
     * 时间窗内最大失败次数
     */
    public static final int FAIL_MAX_COUNT = 3;

    /**
     * 时间窗
     */
    public static final int TIME_WINDOW = 50;

    /**
     * 断路器默认状态为关闭
     */
    public HystrixStatus status = HystrixStatus.CLOSE;

    /**
     * 当前失败次数
     */
    public AtomicInteger failCount = new AtomicInteger(0);

    /**
     * 异步线程池
     */
    public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            3,
            Runtime.getRuntime().availableProcessors(),//获取当前机器的最大线程数 cpu密集型
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    /**
     * 记录失败次数
     */
    public void failCountAdd() {
        int count = this.failCount.getAndIncrement();
        System.out.println("当前失败次数" + count);
        if (count >= FAIL_MAX_COUNT) {
            this.setStatus(HystrixStatus.OPEN);
            System.out.println("断路器设置为开");
            //一段时间之后把断路器设置半开
            poolExecutor.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    this.setStatus(HystrixStatus.HALF_CLOSE);
                    System.out.println("断路器设置为半开");
                    //失败次数清零
                    this.failCount.set(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 定期清空失败次数
     */ {
        poolExecutor.submit(() -> {
            while (true) {
                TimeUnit.SECONDS.sleep(TIME_WINDOW);
                if (this.status == HystrixStatus.CLOSE) {
                    System.out.println("清空当前失败次数");
                    this.failCount.set(0);
                }
            }
        });
    }

}
