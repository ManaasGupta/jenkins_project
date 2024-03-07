package com.spring.restApp.cucumberglue;

import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CucumberMySteps {

    @LocalServerPort
    String port;

    ResponseEntity<String> lastResponse;

    @When("the client calls endpoint {string}")
    public void whenClientCalls(String url){
        lastResponse = new RestTemplate().exchange("http/localhost:"+port+url, HttpMethod.GET,null,String.class);
    }

}
