package com.example.hystrix;


public enum HystrixStatus {

    OPEN(0, "开"),
    CLOSE(1, "关"),
    HALF_CLOSE(2, "半开");

    HystrixStatus(Integer code, String desc) {
    }
}
