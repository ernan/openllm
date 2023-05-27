package com.fodala.pojo;


public class Container {
    private Integer id;
    private String name;
    private String status;
    private String cleaner;
    private String cleaned;
    private String expires;
    private String created;
    private String updated;

    public Container() {

    }

    public Container(Integer id, String name, String status, String cleaner, String cleaned, String expires, String created, String updated) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.cleaner = cleaner;
        this.cleaned = cleaned;
        this.expires = expires;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCleaned() {
        return cleaned;
    }

    public void setCleaned(String cleaned) {
        this.cleaned = cleaned;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCleaner() {
        return cleaner;
    }

    public void setCleaner(String cleaner) {
        this.cleaner = cleaner;
    }
}
