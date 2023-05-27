package com.fodala.pojo;

import java.io.Serializable;
import java.time.Instant;

public abstract class DateAudit implements Serializable {
    private static final long serialVersionUID = -8854879032150315096L;

    private Instant createdAt;

    private Instant updatedAt;

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}