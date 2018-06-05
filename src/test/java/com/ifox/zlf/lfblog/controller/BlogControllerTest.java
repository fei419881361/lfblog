package com.ifox.zlf.lfblog.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogControllerTest {
    @Autowired
    BlogController blogController;

    @Test
    public void testSave(){
        String[] items = {"JAVA","美女"};
        blogController.save("title","sdadasdsdasda",items);
    }

    @Test
    public void testAddItem(){
        String result = blogController.addItem("帅哥","5afa9ac1aa46c430a096f0a5") ;
        System.out.println(result);
    }
    @Test
    public void deleteItem(){
        String result = blogController.deleteItemByItemName("5afa9ac1aa46c430a096f0a5","帅哥");
        System.out.println(result);
    }

    @Test
    public void testAddComment(){
        String result = blogController.addComment("5afa9ac1aa46c430a096f0a5","jack","nice");
        System.out.println(result);
    }

    @Test
    public void testDeleteComment(){
        String result = blogController.deleteComment("5afa9ac1aa46c430a096f0a5","5afa9ac1aa46c430a096f0a5");
        System.out.println(result);
    }

    @Test
    public void testList(){
        String result = blogController.bloglist(0,10);
        System.out.println(result);
    }

}
