package com.freshvotes.web;

import com.freshvotes.domain.User;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class TestConfig {


//        @Bean
//        @Primary
//        public UserDetailsService userDetailsService() {
//            User basicUser = new User("Basic User", "user@company.com", "password");
//            UserActive basicActiveUser = new UserActive(basicUser, Arrays.asList(
//                    new SimpleGrantedAuthority("ROLE_USER"),
//                    new SimpleGrantedAuthority("PERM_FOO_READ")
//            ));
//
//            User managerUser = new UserImpl("Manager User", "manager@company.com", "password");
//            UserActive managerActiveUser = new UserActive(managerUser, Arrays.asList(
//                    new SimpleGrantedAuthority("ROLE_MANAGER"),
//                    new SimpleGrantedAuthority("PERM_FOO_READ"),
//                    new SimpleGrantedAuthority("PERM_FOO_WRITE"),
//                    new SimpleGrantedAuthority("PERM_FOO_MANAGE")
//            ));
//
//            return new InMemoryUserDetailsManager(Arrays.asList(
//                    basicActiveUser, managerActiveUser
//            ));
//        }

}
