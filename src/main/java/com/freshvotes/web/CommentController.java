package com.freshvotes.web;

import com.freshvotes.domain.Comment;
import com.freshvotes.domain.Feature;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.CommentRepository;
import com.freshvotes.repositories.FeatureRepository;
import org.aspectj.bridge.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products/{productId}/features/{featureId}/comments")
public class CommentController {


    private final CommentRepository commentRepo;

    @Autowired
    public CommentController(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Autowired
    public FeatureRepository featureRepo;

    @GetMapping("")
    @ResponseBody
    public List<Comment> getComments(@PathVariable Long productId, @PathVariable Long featureId) {
        return commentRepo.findByFeatureId(featureId);
    }

    @PostMapping("")
    public String postComment(@AuthenticationPrincipal User user, @PathVariable Long productId,
                              @PathVariable Long featureId,
                              @RequestParam(required = false) Long parentId,
                              @RequestParam(required = false) String childCommentText) {

        Comment comment = new Comment();
        if (parentId != null) {
            Optional<Comment> parentCommentOpt = commentRepo.findById(parentId);
            parentCommentOpt.ifPresent(comment::setComment);
        }
        Optional<Feature> featureOpt = featureRepo.findById(featureId);
        featureOpt.ifPresent(comment::setFeature);
        comment.setUser(user);
        comment.setCreatedDate(LocalDateTime.now());
        comment.setText(childCommentText);
        commentRepo.save(comment);

        return "redirect:/products/" + productId + "/features/" + featureId;
    }

}

