package com.ifox.zlf.lfblog.service.impl;

import com.ifox.zlf.lfblog.entity.BlogEO;
import com.ifox.zlf.lfblog.entity.CommentEO;
import com.ifox.zlf.lfblog.repository.BlogRepository;
import com.ifox.zlf.lfblog.service.BlogService;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<BlogEO> listBlogsByPage(int pageIndex, int pageSize) {
        Page<BlogEO> page = null;
        List<BlogEO> list = null;

        Sort sort = new Sort(Sort.Direction.ASC,"uploadDate");
        Pageable pageable = PageRequest.of(pageIndex,pageSize,sort);

        page = blogRepository.findAll(pageable);
        list = page.getContent();
        return list;
    }

    @Override
    public UpdateResult addItems(String item, String id) {
        Update update = new Update().push("items",item);
        Query query = Query.query(new Criteria().andOperator(Criteria.where("id").is(id)));
        return mongoTemplate.updateFirst(query,update,BlogEO.class);
    }

    @Override
    public UpdateResult deleteItems(String item, String id) {
        Update update = new Update();
        update.pull("items",item);
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.updateFirst(query,update,BlogEO.class);
    }

    @Override
    public UpdateResult addComment(CommentEO comment, String id) {
        System.out.println("---->"+comment.toString());
        Update update = new Update().push("comment",comment);
        Query query = Query.query(new Criteria().andOperator(Criteria.where("id").is(id)));
        return mongoTemplate.updateFirst(query,update,BlogEO.class);
    }

    @Override
    public UpdateResult deleteComment(String commentId, String blogId) {
        Update update = new Update();
        update.pull("comment",new BasicDBObject("id",commentId));
        Query query = Query.query(Criteria.where("id").is(blogId));
        System.out.println("------>"+mongoTemplate.find(query,BlogEO.class));
        return mongoTemplate.updateFirst(query,update,BlogEO.class);
    }

    @Override
    public BlogEO save(BlogEO blogEO) {
        return blogRepository.save(blogEO);
    }

    @Override
    public Optional<BlogEO> findById(String id) {
        return blogRepository.findById(id);
    }

    @Override
    public Integer findAll() {
        return blogRepository.findAll().size();
    }

    @Override
    public void deleteById(String id) {
        blogRepository.deleteById(id);
    }


}
