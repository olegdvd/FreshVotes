package com.freshvotes.web;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.service.FeatureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureWebMvc
public class FeatureControllerTest {

    private static final User USER = new User("testUser");
    private static final Long PRODUCT_ID =1L;

    @Mock
    private FeatureService featureService;

    private FeatureController controller;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        Feature feature = new Feature();
        feature.setUser(USER);
        Product product = new Product();
        product.setId(PRODUCT_ID);
        feature.setProduct(product);
//        when(featureService.createFeature(USER, PRODUCT_ID)).thenReturn(feature);
        controller = new FeatureController();
    }

    @Test
    public void createFeature() throws Exception {
//        int status = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/products/%s/features", PRODUCT_ID)))
//                .andReturn().getResponse().getStatus();
//        String feature = controller.createFeature(USER, PRODUCT_ID);
//
//        assertTrue(feature.contains(PRODUCT_ID.toString()));
//        assertEquals(200, status);
    }

    @Test
    public void getFeature() {
    }

    @Test
    public void updateFeature() {
    }
}