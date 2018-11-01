package com.example.rssinsidesuper.data;

public class RssInsideSuper {
    private String id;

    private String presentId;

    private String insideName;

    private String insidePost;

    private String superRecord;

    private String crtUser;

    private String crtTime;

    private String updUser;

    private String updTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPresentId() {
        return presentId;
    }

    public void setPresentId(String presentId) {
        this.presentId = presentId == null ? null : presentId.trim();
    }

    public String getInsideName() {
        return insideName;
    }

    public void setInsideName(String insideName) {
        this.insideName = insideName == null ? null : insideName.trim();
    }

    public String getInsidePost() {
        return insidePost;
    }

    public void setInsidePost(String insidePost) {
        this.insidePost = insidePost == null ? null : insidePost.trim();
    }

    public String getSuperRecord() {
        return superRecord;
    }

    public void setSuperRecord(String superRecord) {
        this.superRecord = superRecord == null ? null : superRecord.trim();
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser == null ? null : crtUser.trim();
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime == null ? null : crtTime.trim();
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser == null ? null : updUser.trim();
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime == null ? null : updTime.trim();
    }
}