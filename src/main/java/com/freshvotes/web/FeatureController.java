package com.freshvotes.web;

import com.freshvotes.domain.Comment;
import com.freshvotes.domain.Feature;
import com.freshvotes.domain.User;
import com.freshvotes.service.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
    private static final Logger LOG = LoggerFactory.getLogger(FeatureController.class);

    @Autowired
    private FeatureService featureService;

    @PostMapping("")
    public String createFeature(@AuthenticationPrincipal User user, @PathVariable Long productId) {
        Feature feature = featureService.createFeature(user, productId);
        return "redirect:/products/" + productId + "/features/" + feature.getId();
    }

    @GetMapping("/{featureId}")
    public String getFeature(@AuthenticationPrincipal User user, ModelMap model, @PathVariable Long productId, @PathVariable Long featureId) {
        Optional<Feature> featureOpt = featureService.findById(featureId);
        featureOpt.ifPresent(feature -> {
            model.put("feature", feature);
            model.put("comments", getCommentsWithoutDuplicates(0, new HashSet<>(), feature.getComments()));
        });
        model.put("user", user);
        return "feature";
    }

    private Set<Comment> getCommentsWithoutDuplicates(int page, Set<Long> visitedComments, Set<Comment> comments) {
        page++;
        Iterator<Comment> itr = comments.iterator();
        while (itr.hasNext()) {
            Comment comment = itr.next();
            boolean addedToVisitedComments = visitedComments.add(comment.getId());
            if (!addedToVisitedComments) {
                itr.remove();
                if (page != 1) return comments;
            }
            if (addedToVisitedComments && !comment.getComments().isEmpty())
                getCommentsWithoutDuplicates(page, visitedComments, comment.getComments());
        }
        return comments;
    }

    @PostMapping("{featureId}")
    public String updateFeature(@AuthenticationPrincipal User user, Feature feature, @PathVariable Long productId, @PathVariable Long featureId) {
        feature.setUser(user);
        feature = featureService.save(feature);

        String encodedProductName;
        try {
            encodedProductName = URLEncoder.encode(feature.getProduct().getName(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            LOG.warn("Unable to encode URL string: {} Redirecting to dashboard.", feature.getProduct().getName());
            return "redirect:/dashboard";
        }
        return "redirect:/p/" + encodedProductName;
    }
}
