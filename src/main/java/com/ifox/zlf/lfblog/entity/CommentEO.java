package com.ifox.zlf.lfblog.entity;


/**
 * 评论信息
 * */

public class CommentEO {
    private String id;
    private String content;
    private String date;
    private String username;

    public CommentEO(String id, String content, String date, String username) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.username = username;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
