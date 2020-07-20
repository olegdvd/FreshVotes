package com.freshvotes.web;

import com.freshvotes.repositories.FeatureRepository;
import com.freshvotes.repositories.ProductRepository;
import com.freshvotes.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
    @Autowired
    FeatureService featureService;

    @PostMapping("")
    public String CreateFeature(@PathVariable Long productId) {
        featureService.createFeature(productId);
        return "feature";
    }

}
