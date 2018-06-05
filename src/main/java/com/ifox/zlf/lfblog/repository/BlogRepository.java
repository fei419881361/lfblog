package com.ifox.zlf.lfblog.repository;

import com.ifox.zlf.lfblog.entity.BlogEO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<BlogEO,String> {
}
