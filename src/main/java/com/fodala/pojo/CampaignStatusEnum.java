package com.fodala.pojo;

public enum CampaignStatusEnum {
    CLEAN("Clean"),
    CLOSE_TO_EXPIRY("Close To Expiry"),
    EXPIRED("Expired"),
    INITIAL("Initial");
    private final String status;

    private CampaignStatusEnum(String status) {
        this.status = status;
    }
}
