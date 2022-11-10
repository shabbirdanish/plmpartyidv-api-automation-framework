package com.santander.api.test.steps;

import com.santander.api.automation.config.ApplicationConfig;
import com.santander.api.test.service.IdentityVerificationService;
import com.santander.api.test.service.JsonValueReader;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.json.JsonReader;
import java.util.HashMap;





public class JWTTokenstep {

    IdentityVerificationService identityVerificationService;

   JsonValueReader reader = new JsonValueReader();
    @Autowired
    ApplicationConfig appConfig;
    @Given("A JWT token is generated if it is not present")
        public String aJWTTokenIsGeneratedIfItIsNotPresent() {
        String path= appConfig.getProperty("jwtJsonPayload");
        System.out.println(path);
            JsonReader jwtjsonReader =  reader.getJsonReader(appConfig.getProperty("jwtJsonPayload"));
            String payload= jwtjsonReader.readObject().toString();
            Response response= identityVerificationService.generateJWTtokenfirststep(payload);
            Assert.assertEquals(200, response.getStatusCode());
            JsonPath jp= response.getBody().jsonPath();
            String jwtCode = jp.get("jwt");
            HashMap<String, String> body = new HashMap<>();
            body.put("grant_type","urn:ietf:params:oauth:grant-type:jwt-bearer");
            body.put("assertion",jwtCode);
            body.put("client_id","axukf58a8xmtgyyib2g49cy84");
            body.put("scope","aggregator.write");
            body.put("aud","https://aws-at");
            Response jwtResponse= identityVerificationService.generateJWTtokenSecondtstep(body);
            JsonPath jwtJson = jwtResponse.getBody().jsonPath();
            String jwtToken= jwtJson.get("access_token");
            return jwtToken;

        }
}
