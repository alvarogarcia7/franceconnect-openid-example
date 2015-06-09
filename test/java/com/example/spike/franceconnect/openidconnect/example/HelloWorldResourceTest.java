package com.example.spike.franceconnect.openidconnect.example;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HelloWorldResourceTest {

    @Test
    public void the_url_should_remain_constant() {
        assertThat(new HelloWorldResource().getLoginAddress(), is("<html><head><meta http-equiv=\"refresh\" content=\"0; url=https://fcp.integ01.dev-franceconnect.fr/api/v1/authorize?response_type=code&client_id=&redirect_uri=http://localhost:8080/callback&scope=openid%20profile&state=nclikn1m2lkm1231kj23n12l31l2jk312&nonce=123123123231112313\" />"));
    }


}