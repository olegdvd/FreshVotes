package com.freshvotes.web;

import com.freshvotes.domain.Comment;
import com.freshvotes.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/products/{productId}/features/{featureId}/comments")
public class CommentController {

    private final CommentRepository commentRepo;

    @Autowired
    public CommentController(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    @GetMapping("")
    @ResponseBody
    public List<Comment> getComments(@PathVariable Long productId, @PathVariable Long featureId) {
        return commentRepo.findByFeatureId(featureId);
    }

}

