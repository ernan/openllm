package com.fodala.pojo;

public class Setting {
    private Integer id;
    private String category;
    private String name;
    private String value;
    private String comment;

    private String seq;
    private String username;

    public Setting(Integer id, String category, String name, String value, String comment, String seq, String username) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.value = value;
        this.comment = comment;
        this.seq = seq;
        this.username = username;
    }

    public Setting() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
