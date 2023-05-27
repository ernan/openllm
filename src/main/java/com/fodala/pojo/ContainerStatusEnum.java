package com.fodala.pojo;

public enum ContainerStatusEnum {
    CLEAN("Clean"),
    CLOSE_TO_EXPIRY("Close To Expiry"),
    EXPIRED("Expired"),
    INITIAL("Initial");
    private final String status;

    private Integer sequenceID;

    private ContainerStatusEnum(String status) {
        this.status = status;
    }
}
