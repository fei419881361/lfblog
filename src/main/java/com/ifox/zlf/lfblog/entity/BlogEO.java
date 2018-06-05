package com.ifox.zlf.lfblog.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * 博客文章
 * */

@Document(collection = "blog")
public class BlogEO {
    @Id  // 主键
    private String id;
    private Long size;  //字数
    private String title;//文章标题
    private String content; // 文章内容
    private Long likesNum; //点赞数
    private Long clickNum ;//点击量
    private String uploadDate;
    private String modifyDate;

    private List<CommentEO> comment;//评论列表
    @Indexed
    private List<String> items; // 类别列表

    public BlogEO(){}

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", size=" + size +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likesNum=" + likesNum +
                ", clickNum=" + clickNum +
                ", uploadDate='" + uploadDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", comment=" + comment +
                ", items=" + items +
                '}';
    }

    public Long getClickNum() {
        return clickNum;
    }

    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }


    public Long getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(Long likesNum) {
        this.likesNum = likesNum;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public List<CommentEO> getComment() {
        return comment;
    }

    public void setComment(List<CommentEO> comment) {
        this.comment = comment;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
