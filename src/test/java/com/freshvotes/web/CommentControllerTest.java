package com.freshvotes.web;

import com.freshvotes.domain.Comment;
import com.freshvotes.repositories.CommentRepository;
import com.freshvotes.service.FeatureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentControllerTest {
    @Mock
    private FeatureService featureService;

    @Mock
    private CommentRepository commentRepo;

    @InjectMocks
    private CommentController commentController;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);


        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void shouldGetComments() throws Exception {
        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment(1L, "text1", null, null, new TreeSet<>(), null, null);
        comments.add(comment1);
        Comment comment2 = new Comment(2L, "text2", null, null, new TreeSet<>(), null, null);
        comments.add(comment2);

        when(commentRepo.findByFeatureId(1L)).thenReturn(comments);

        mockMvc.perform(get("/products/1/features/1/comments")
                .with(user("oleg.dvd@gmail.com").password("123123").roles("USER", "ADMIN")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isArray());


    }
}