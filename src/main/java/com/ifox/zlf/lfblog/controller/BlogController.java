package com.ifox.zlf.lfblog.controller;


import com.google.gson.Gson;
import com.ifox.zlf.lfblog.entity.BlogEO;
import com.ifox.zlf.lfblog.entity.CommentEO;
import com.ifox.zlf.lfblog.service.BlogService;
import com.ifox.zlf.lfblog.util.DateTimeUtil;
import com.ifox.zlf.lfblog.util.UUIDUtil;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;


    @PostMapping
    @ResponseBody
    public String save(String title,String content, @RequestParam(value = "items[]")String[] items){
        BlogEO blog = new BlogEO();
        blog.setItems(Arrays.asList(items));
        blog.setTitle(title);
        blog.setContent(content);
        blog.setSize(content.length());
        blog.setLikesNum((long) 0);
        blog.setClickNum((long) 0);
        blog.setComment(null);
        blog.setUploadDate(DateTimeUtil.getCurrentDateAsString(null));
        blog.setModifyDate(DateTimeUtil.getCurrentDateAsString(null));
        Map<String,Object> result = new HashMap<>();
        if (blogService.save(blog) != null){
            result.put("code",200);
            result.put("msg","success");
        }else {
            result.put("code",707);
            result.put("msg","error");
        }
        return new Gson().toJson(result);
    }

    @GetMapping("/list")
    @ResponseBody
    public String bloglist(Integer page,Integer limit){
        List<BlogEO> blogEOList = blogService.listBlogsByPage(page-1,limit);
        Integer count = blogService.findAll();
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",blogEOList);
        return new Gson().toJson(map);
    }

    @GetMapping("/write")
    public ModelAndView write(){
        return new ModelAndView("/blog/writeblog","writeblog",null);
    }

    @GetMapping("/all")
    public ModelAndView list(){
        return new ModelAndView("/blog/blogList","blogList",null);
    }

    @GetMapping("/{id}")
    public String lookBlogById(@PathVariable String id,Model model){
        model.addAttribute("title","my_title");
        Optional<BlogEO> blog = blogService.findById(id);
        if (blog.isPresent()){
            model.addAttribute("viewblog",blog.get());
        }else {
            model.addAttribute("msg","error");
            System.out.println("查询失败");
        }
        return "blogview";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteBlogById(@PathVariable String id){

        System.out.println("delete id:"+ id);
        blogService.deleteById(id);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("msg","success");

        return new Gson().toJson(result);




    }

    @PostMapping("/addItem")
    @ResponseBody
    public String addItem(String itemName,String id){
        UpdateResult result = blogService.addItems(itemName,id);
        if(result.getModifiedCount() == 1){
            return "success";
        }else {
            return "failed";
        }
    }

    /**
     * @param id blog'id
     * */
    @DeleteMapping("/deleteItem/{id}/{itemName}")
    @ResponseBody
    public String deleteItemByItemName(@PathVariable String id,@PathVariable String itemName){
        UpdateResult result = blogService.deleteItems(itemName,id);
        if(result.getModifiedCount() >= 1){
            return "success";
        }else {
            return "failed";
        }
    }

    @PostMapping("/addComment")
    @ResponseBody
    public String addComment(String id,String userName,String content){
        CommentEO commentEO = new CommentEO(UUIDUtil.randomUUID()
        ,content,DateTimeUtil.getCurrentDateAsString(null),userName);
        UpdateResult result = blogService.addComment(commentEO,id);
        if (result.getModifiedCount() == 1) {
            return "success";
        }else {
            return "failed";
        }
    }

    @DeleteMapping("/deleteComment/{blogId}/{commentId}")
    @ResponseBody
    public String deleteComment(@PathVariable String blogId,@PathVariable String commentId){
        UpdateResult result = blogService.deleteComment(commentId,blogId);
        if (result.getModifiedCount() == 1) {
            return "success";
        }else {
            return "failed";
        }
    }
}
