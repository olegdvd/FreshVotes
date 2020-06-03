package com.freshvotes.repositories;

import com.freshvotes.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager em;

    @Before
    public void setUp() {
        List<User> list = Arrays.asList(
                new User("test1"),
                new User("test2"),
                new User("test3")
        );
        list.forEach(em::persist);
        em.flush();
    }

    @Test
    public void findByUsername() {
        String name = "test1";
        User result = repository.findByUsername(name);

        assertNotNull(result);
        assertEquals(name, result.getUsername());
    }
}