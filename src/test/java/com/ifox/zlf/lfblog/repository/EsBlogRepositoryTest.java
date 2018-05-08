package com.ifox.zlf.lfblog.repository;

import com.ifox.zlf.lfblog.entity.EsBlogEO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogRepositoryTest {
    @Autowired
    private EsBlogRepository esBlogRepository;

    @Before
    public void initRepository(){
        esBlogRepository.deleteAll();
        esBlogRepository.save(new EsBlogEO("标题哈哈","hello world","java java"));
        esBlogRepository.save(new EsBlogEO("干巴爹","就hi的","青山白云"));
        esBlogRepository.save(new EsBlogEO("你看见","欢迎收看","我是歌手"));
    }

    @Test
    public void testFindDistinctESBlog(){
        Pageable pageable = new PageRequest(0,10);
        String title = "000";
        String summary = "000";
        String content = "青";
        Page<EsBlogEO> page = esBlogRepository.findDistinctEsBlogEOByTitleContainingOrSummaryContainingOrContentContaining(title,summary,content,pageable);
        assertThat(page.getTotalElements()).isEqualTo(1);
        System.out.println("-------1---------");
        for (EsBlogEO esBlogEO : page.getContent()) {
            System.out.println(esBlogEO.toString());
        }
        System.out.println("---------2--------");

    }
}
