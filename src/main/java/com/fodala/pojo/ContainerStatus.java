package com.fodala.pojo;

public class ContainerStatus {
    Integer id;
    String name;
    Integer seq;

    public ContainerStatus() {

    }

    public ContainerStatus(Integer id, String name, Integer seq) {
        this.id = id;
        this.name = name;
        this.seq = seq;
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

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
