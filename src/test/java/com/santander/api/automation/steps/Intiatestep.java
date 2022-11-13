package com.santander.api.automation.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.santander.api.automation.config.ApplicationConfig;
import com.santander.api.automation.service.GetResponse;
import com.santander.api.automation.service.IdentityVerificationService;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.json.Json;
import javax.json.JsonReader;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
@Slf4j
@AllArgsConstructor
public class Intiatestep {
 private final ApplicationConfig applicationConfig;
 private final  JWTTokenstep jwtTokenstep;

 private final GetResponse getResponse;
    @When("Call INITIATE endpoint with valid payload")
    public Response callINITIATEEndpointWithValidPayload() throws JsonProcessingException {
        String token = jwtTokenstep.aJWTTokenIsGeneratedIfItIsNotPresent();
        applicationConfig.addProperties("src/test/resources/additional.properties");
        String path= applicationConfig.getProperty("initiateJsonPayload");
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        JsonReader reader = Json.createReader(is);
        String request =   reader.readObject().toString();
        Response response = getResponse.getIdentityByCustomerInitiate(token, request);
        assertEquals(200, response.getStatusCode());
        return response;
    }
}
