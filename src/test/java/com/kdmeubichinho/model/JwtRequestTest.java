package com.kdmeubichinho.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtRequestTest {

    @Test
    void testAllConstructorsAndGetter(){
        JwtRequest jwtRequest = new JwtRequest("username", "password", "claims");

        assertEquals("username", jwtRequest.getUsername());
        assertEquals("password", jwtRequest.getPassword());
        assertEquals("claims", jwtRequest.getSpecificClaim());
    }

    @Test
    void testConstructorsAndGetter(){
        JwtRequest jwtRequest = new JwtRequest("username", "password");

        assertEquals("username", jwtRequest.getUsername());
        assertEquals("password", jwtRequest.getPassword());
    }

    @Test
    void nonArgsConstructor(){
        JwtRequest jwtRequest = new JwtRequest();
        assertNotNull(jwtRequest);
    }

    @Test
    void testSetter(){
        JwtRequest j = new JwtRequest();
        j.setPassword("testSetter1");
        j.setUsername("testSetter12");
        j.setSpecificClaim("testSetter123");

        assertEquals("testSetter12", j.getUsername());
        assertEquals("testSetter1", j.getPassword());
        assertEquals("testSetter123", j.getSpecificClaim());
    }
}