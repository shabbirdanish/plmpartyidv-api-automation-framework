package com.santander.api.automation.steps;

import com.santander.api.automation.config.ApplicationConfig;
import com.santander.api.automation.service.GetResponse;
import com.santander.api.automation.service.JsonValueReader;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.stereotype.Component;

import javax.json.JsonReader;
import java.util.HashMap;

@Slf4j
@AllArgsConstructor
public class JWTTokenstep {

private final GetResponse getResponse;
private final JsonValueReader jsonValueReader;

private final  ApplicationConfig applicationConfig;


    @Given("A JWT token is generated if it is not present")
        public String aJWTTokenIsGeneratedIfItIsNotPresent() {
        applicationConfig.addProperties("src/test/resources/additional.properties");
        String path= applicationConfig.getProperty("jwtJsonPayload");
            JsonReader jwtjsonReader =  jsonValueReader.getJsonReader(path);
            String payload= jwtjsonReader.readObject().toString();
          Response response= getResponse.generateJWTtokenfirststep(payload);
            Assert.assertEquals(200, response.getStatusCode());
            JsonPath jp= response.getBody().jsonPath();
            String jwtCode = jp.get("jwt");
            HashMap<String, String> body = new HashMap<>();
            body.put("grant_type","urn:ietf:params:oauth:grant-type:jwt-bearer");
            body.put("assertion",jwtCode);
            body.put("client_id","axukf58a8xmtgyyib2g49cy84");
            body.put("scope","aggregator.write");
            body.put("aud","https://aws-at");
            Response jwtResponse= getResponse.generateJWTtokenSecondtstep(body);
            JsonPath jwtJson = jwtResponse.getBody().jsonPath();
            String jwtToken= jwtJson.get("access_token");
        return jwtToken;

        }
}
