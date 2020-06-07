package com.freshvotes.service;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class UserDetailServiceTest {

    @Test
    public void generate_encrypted_password() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123123";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
        assertThat(rawPassword, not(encodedPassword));


    }
}