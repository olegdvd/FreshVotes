package com.freshvotes.repositories;

import com.freshvotes.domain.Comment;
import com.freshvotes.domain.Feature;
import com.freshvotes.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryShould {

    private static final Long FEATURE_ID = 1L;

    @Autowired
    private CommentRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp() {
        entityManager.persist(new Comment());
        entityManager.flush();
    }

    @Test
    public void test_get_comment() {
        List<Comment> all = repository.findAll();

        assertTrue(all.size() >= 1);
    }

    @Test
    public void getAllCommentsToFeatureByFeatureId() {

        Feature feature = new Feature();

        Comment testComment1 = createComment(1L, "Test Comment #1", null, LocalDateTime.parse("2007-12-03T10:15:30"));

        Comment testComment2 = createComment(2L, "Test Comment #2", null, LocalDateTime.parse("2007-12-03T10:15:31"));

        Comment testComment3 = new Comment(3L, "Test Comment #3", new User(), feature, new TreeSet<>(), testComment1, LocalDateTime.parse("2007-12-03T10:15:32"));
        testComment1.getComments().add(testComment3);

        Comment testComment4 = new Comment(4L, "Test Comment #4", new User(), feature, new TreeSet<>(), null, LocalDateTime.parse("2007-12-03T10:15:33"));

        Comment testComment5 = new Comment(5L, "Test Comment #5", new User(), feature, new TreeSet<>(), testComment1, LocalDateTime.parse("2007-12-03T10:15:34"));
        testComment1.getComments().add(testComment5);

        Comment testComment6 = new Comment(6L, "Test Comment #6", new User(), feature, new TreeSet<>(), testComment5, LocalDateTime.parse("2007-12-03T10:15:35"));
        testComment5.getComments().add(testComment6);

        List<Comment> commentsCollection = Stream
                .of(testComment1, testComment2, testComment3, testComment4, testComment5, testComment6)
                .collect(Collectors.toList());

//        when(commentRepo.findByFeatureId(FEATURE_ID)).thenReturn(commentsCollection);
//
//        List<Comment> commentsList = commentRepo.findByFeatureId(FEATURE_ID);
//
//        assertEquals(6, commentsList.size());
//        assertEquals(Long.valueOf(1), commentsList.get(0).getId());
//        assertEquals(LocalDateTime.parse("2007-12-03T10:15:35"), commentsList.get(5).getCreatedDate());
    }

    private Comment createComment(Long id, String text, Comment comment, LocalDateTime dateTime) {
        return new Comment(id, text, new User(), new Feature(), new TreeSet<>(), comment, dateTime);
    }


}