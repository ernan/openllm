package com.fodala.pojo;

public class Attribute {
    private Integer id;
    private Integer objectId;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    private String objectType;
    private String name;
    private String value;
    private String comment;
    private String seq;
    public Attribute(Integer id, Integer objectId, String objectType, String name, String value, String comment, String seq) {
        this.id = id;
        this.objectId = objectId;
        this.objectType = objectType;
        this.name = name;
        this.value = value;
        this.comment = comment;
        this.seq = seq;
    }
    public Attribute() {
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
