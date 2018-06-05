package com.ifox.zlf.lfblog.repository;

import com.ifox.zlf.lfblog.entity.EsBlogEO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRepository extends ElasticsearchRepository<EsBlogEO,String> {
    /**
     * 分页查询博客
     * */
    Page<EsBlogEO> findDistinctEsBlogEOByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);
}
