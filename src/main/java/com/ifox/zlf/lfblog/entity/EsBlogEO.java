package com.ifox.zlf.lfblog.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(indexName = "blog",type = "blog")
public class EsBlogEO implements Serializable {
    private static final long seriaVersionUID=1L;
    @Id
    private String id;
    private String title;
    private String summary;
    private String content;

    public EsBlogEO(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public EsBlogEO(){}
    @Override
    public String toString() {
        return "EsBlogEO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
