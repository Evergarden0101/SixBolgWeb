package com.example.demo.Controller;

import com.example.demo.Dao.BlogDao;
import com.example.demo.Entity.Blog;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BlogRepository;
import com.example.demo.Tools.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

@Controller
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;


    @PostMapping(value ="/Blog/add")
    public String add(@RequestParam("label")String label, Blog blog, HttpSession session){
        blog.setDate(Time.getTime());
        blog.setLabel(Arrays.asList(label.split(" ")));
        User user=(User)session.getAttribute("user");
        blog.setAuthorid(user.getId());
        blog.setAuthorname(user.getUsername());
        blog.setNumber(0);
        //blogRepository.save(blog);
        System.out.println(blog.getContent());
        return "bingo";
    }

    @GetMapping(value ="/Blog/delete/{id}")
    public String delete(@PathVariable String id){

        blogRepository.deleteById(id);
        return "bingo";
    }

    @PostMapping(value ="/Blog/modify")
    public String modify(Blog blog){

        blog.setDate(Time.getTime());
        blog.setNumber(0);
        blogRepository.save(blog);
        return "modify";
    }


    @RequestMapping("/hello")
    public String helloWorld(){




        return "Bingo";
    }


    @GetMapping(value ="/blog/{id}")
    public String getbyid(@PathVariable String id, Model model,HttpSession session)
    {
        Blog blog=blogRepository.findById(id).get();
        session.setAttribute("blog",blog);
        model.addAttribute("blog",blog);
        return "...";
    }

    @PostMapping(value = "/blog/{name}")
    public  String search(@PathVariable String name, Model model)
    {
        List<Blog> blogList= BlogDao.search(name,blogRepository);
        model.addAttribute("blogs",blogList);
        for(int i=0;i<blogList.size();i++)
        System.out.println(blogList.get(i).getTheme());
        return "Bingo";
    }

    @GetMapping(value = "/blog/123")
    public  String search123()
    {
        List<Blog> blogList= BlogDao.search("Java",blogRepository);
        //model.addAttribute("blogs",blogList);
        for(int i=0;i<blogList.size();i++)
            System.out.println(blogList.get(i).getTheme());
        return "redirect:/hello";
    }

    @GetMapping(value ="/blog/{label}")
    public String getbylabel(@PathVariable String label, Model model) throws ParseException {
        List<Blog> blogList= BlogDao.findbylabel(label,blogRepository);
        model.addAttribute("blog",blogList);
        return "...";
    }




}
