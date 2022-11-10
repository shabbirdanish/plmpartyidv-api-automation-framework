package com.santander.api.test.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.santander.api.test.service.IdentityVerificationService;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.json.Json;
import javax.json.JsonReader;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class Intiatestep {

    @Autowired
    JWTTokenstep jwtTokenstep;
    @Autowired
    IdentityVerificationService identityVerificationService;
    @When("Call INITIATE endpoint with valid payload")
    public Response callINITIATEEndpointWithValidPayload() throws JsonProcessingException {
        String token = jwtTokenstep.aJWTTokenIsGeneratedIfItIsNotPresent();
        InputStream is = getClass().getClassLoader().getResourceAsStream("testdata/intiate/Initiate/partyIdv_initiateTemplate.json");
        JsonReader reader = Json.createReader(is);
        String request =   reader.readObject().toString();
        Response response = identityVerificationService.getIdentityByCustomerInitiate(token, request);
        assertEquals(200, response.getStatusCode());
        return response;
    }
}
