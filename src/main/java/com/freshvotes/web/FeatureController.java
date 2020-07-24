package com.freshvotes.web;

import com.freshvotes.domain.Feature;
import com.freshvotes.service.FeatureService;
import com.sun.xml.bind.v2.runtime.SwaRefAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
    Logger log = LoggerFactory.getLogger(FeatureController.class);

    @Autowired
    FeatureService featureService;

    @PostMapping("")
    public String CreateFeature(@PathVariable Long productId) {
        Feature feature = featureService.createFeature(productId);
        return "redirect:/products/" + productId + "/features/" + feature.getId();
    }

    @GetMapping("/{featureId}")
    public String getFeature(ModelMap model, @PathVariable Long productId, @PathVariable Long featureId) {
        Optional<Feature> featureOpt = featureService.findById(featureId);
        featureOpt.ifPresent(feature -> model.put("feature", feature));
        return "feature";
    }

    @PostMapping("{featureId}")
    public String updateFeature(Feature feature, @PathVariable Long productId, @PathVariable Long featureId) {
        feature = featureService.save(feature);
        String encodedProductName;
        try {
            encodedProductName = URLEncoder.encode(feature.getProduct().getName(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            log.warn("Unable to encode URL string: " +  feature.getProduct().getName() + " Redirecting to dashboard.");
            return "redirect:/dashboard";
        }
        return "redirect:/p/" + encodedProductName;
    }
}
