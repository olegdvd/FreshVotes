package com.freshvotes.service;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.Product;
import com.freshvotes.repositories.FeatureRepository;
import com.freshvotes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {
    @Autowired
    ProductRepository productRepo;
    @Autowired
    FeatureRepository featureRepo;

    public Feature createFeature(Long productId) {
        Feature feature = new Feature();

        Optional<Product> productOpt = productRepo.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            feature.setProduct(product);
            product.getFeatures().add(feature);

            feature.setStatus("Pending review");
            return featureRepo.save(feature);
        }
        return feature;
    }

    public Feature save(Feature feature) {
        return featureRepo.save(feature);
    }

    public Optional<Feature> findById(Long featureId) {
        return featureRepo.findById(featureId);
    }
}
