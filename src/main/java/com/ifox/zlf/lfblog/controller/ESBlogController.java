package com.ifox.zlf.lfblog.controller;

import com.ifox.zlf.lfblog.entity.EsBlogEO;
import com.ifox.zlf.lfblog.repository.EsBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class ESBlogController {
    @Autowired
    private EsBlogRepository esBlogRepository;
    @GetMapping
    public List<EsBlogEO> list(
            @RequestParam("title")String title,
            @RequestParam("summary")String summary,
            @RequestParam("content")String content,
            @RequestParam(value = "pageIndext",defaultValue = "0")int pageIndex,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Pageable pageable = new PageRequest(pageIndex,pageSize);
        Page<EsBlogEO> page = esBlogRepository.findDistinctEsBlogEOByTitleContainingOrSummaryContainingOrContentContaining(title,summary,content,pageable);
        return page.getContent();
    }
}
