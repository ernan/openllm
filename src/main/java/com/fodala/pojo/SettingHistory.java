package com.fodala.pojo;

public class SettingHistory {
    private Integer id;
    private Integer settingId;
    private String category;
    private String name;
    private String value;
    private String comment;
    private String seq;
    public SettingHistory(Integer id, Integer settingId, String category, String name, String value, String comment, String seq) {
        this.id = id;
        this.settingId = settingId;
        this.category = category;
        this.name = name;
        this.value = value;
        this.comment = comment;
        this.seq = seq;
    }

    public SettingHistory() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
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
