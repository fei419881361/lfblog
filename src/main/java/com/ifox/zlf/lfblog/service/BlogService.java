package com.ifox.zlf.lfblog.service;

import com.ifox.zlf.lfblog.entity.BlogEO;
import com.ifox.zlf.lfblog.entity.CommentEO;
import com.mongodb.client.result.UpdateResult;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<BlogEO> listBlogsByPage(int pageIndex, int pageSize);

    UpdateResult addItems(String item, String id);

    UpdateResult deleteItems(String item, String id);

    UpdateResult addComment(CommentEO comment, String id);

    UpdateResult deleteComment(String commentId,String blogId);

    BlogEO save(BlogEO blogEO);

    Optional<BlogEO> findById(String id);

    Integer findAll();

    void deleteById(String id);
}
