package com.freshvotes.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freshvotes.domain.Comment;
import com.freshvotes.domain.Feature;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CommentControllerShould {

    private static final Long FEATURE_ID = 1L;
    private static final Long PRODUCT_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private CommentRepository commentRepo;
    private CommentController commentController;
    private List<Comment> commentsCollection;

    @Before
    public void setUp() {
        commentController = new CommentController(commentRepo);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController)
                .build();
        commentsCollection = Arrays.asList(
                createComment(1L, "Test Comment #1", null, LocalDateTime.parse("2007-12-03T10:15:30")),
                createComment(2L, "Test Comment #2", null, LocalDateTime.parse("2007-12-03T10:15:31"))
        );
    }

    private Comment createComment(Long id, String text, Comment comment, LocalDateTime dateTime) {
        return new Comment(id, text, new User(), new Feature(), new TreeSet<>(), comment, dateTime);
    }

    @Test
    public void getCommentsWithoutDuplicatesSortedByDate() throws Exception {
        when(commentRepo.findByFeatureId(FEATURE_ID)).thenReturn(commentsCollection);
        String collectionAsJson = mapper.writeValueAsString(commentsCollection);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/1/features/1/comments")
                        .param("featureId", FEATURE_ID.toString())
                        .param("productId", PRODUCT_ID.toString())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(collectionAsJson));

    }

}