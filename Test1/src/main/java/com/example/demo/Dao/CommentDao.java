package com.example.demo.Dao;

import com.example.demo.Entity.Comment;
import com.example.demo.Repository.CommentRepository;

import java.util.List;

public class CommentDao {


    public static List<Comment> findbyblogid(String id, CommentRepository commentRepository)
    {
        List<Comment> commentList=commentRepository.findAll();
        for(int i=0;i<commentList.size();i++)
        {
            if(commentList.get(i).getBlogid().equals(id)==false)
            {
                commentList.remove(i);
            }
        }
        return  commentList;


    }



}