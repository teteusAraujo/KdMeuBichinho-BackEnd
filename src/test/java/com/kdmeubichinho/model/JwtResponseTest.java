package com.kdmeubichinho.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtResponseTest {

    @Test
    void getToken() {
        JwtResponse j = new JwtResponse("getToken");
        assertEquals("getToken", j.getToken());
    }
}