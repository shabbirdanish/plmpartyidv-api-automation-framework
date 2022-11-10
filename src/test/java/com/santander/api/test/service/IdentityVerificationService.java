package com.santander.api.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.HashMap;

import static io.restassured.RestAssured.given;

@Slf4j

@RequiredArgsConstructor
@Service
public class IdentityVerificationService {

    @Qualifier("anonymousRequestSpecification")
    private final RequestSpecification anonymousRequestSpecification;

    private String jwtUrlFirst = "https://utilities.dev.identity.private.gamma.tlzproject.com/jwt/generate";
    private String jwtUrlSecond = "https://engine.dev.identity.private.gamma.tlzproject.com/as/token.oauth2";

    private final ObjectMapper objectMapper;
    private final String initialUrl = "/plm/partyidv/v1/initiate";
    private final String statusUrl = "/plm/partyidv/v1/status/{{partyIdvId}}";

    public String getIdentityByCustomer(String accessToken, String customerId) {
        String url = String.format("/v1/customers/%s/identity", customerId);

        Response response = given().spec(anonymousRequestSpecification.header("Authorization", "Bearer " ))
                .when()
                .get(url)
                .then().log().all().extract().response();

        response.then().statusCode(200);

        return response.getBody().asString();
    }

    public Response getIdentityByCustomerInitiate(String accessToken, String payLoad) throws JsonProcessingException {


        Response response = given().spec(anonymousRequestSpecification)
                .when()
                .header("Authorization", "Bearer " + accessToken)
                .body(payLoad)
                .post(initialUrl)
                .then().log().all()
                .extract()
                .response();

        return response;
    }

//    public Response getIdentityByCustomerInitiate(String accessToken, InitiateRequest payLoad) throws JsonProcessingException {
//        Response response = given().spec(anonymousRequestSpecification)
//                .when()
//                .body(objectMapper.writeValueAsString(payLoad))
//                .post(initialUrl)
//                .then().log().all()
//                .extract().response();
//
//
//
//        return response;
//    }

    public Response getParyIDVStatus(String token, String partyIdvId) {
        String url = String.format("/plm/partyidv/v1/status/%s", partyIdvId);
        return given().spec(anonymousRequestSpecification)
                .when()
                .header("Authorization", "Bearer " + token)
                .get(url)
                .then().log().all()
                .extract().response();
    }

    public Response postExecutepartyIdvId(String token, String partyIdvId) {

        return given().spec(anonymousRequestSpecification)
                .when()
                .header("Authorization", "Bearer " + token)
                .post("v1/plmpartyidv/execute/{partyIdvId}", partyIdvId)
                .then().log().all()
                .extract().response();
    }

//    public Response postCompletepartyIdvId(String token, CompleteRequest payLoad) throws JsonProcessingException {
//
//        return given().spec(anonymousRequestSpecification)
//                .when()
//                .header("Authorization", "Bearer " + token)
//                .body(objectMapper.writeValueAsString(payLoad))
//                .post("v1/plmpartyidv/complete")
//                .then().log().all()
//                .extract().response();
//    }

    public Response generateJWTtokenfirststep(String payLoad){


      return   given()
                .when().relaxedHTTPSValidation().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(payLoad)
                .post(jwtUrlFirst)
                .then().log().all()
                .extract().response();


    }
    public Response generateJWTtokenSecondtstep(HashMap<String, String> payLoad){


        return   given()
                .when().relaxedHTTPSValidation().header("Content-Type", "application/x-www-form-urlencoded").formParams(payLoad)
                .post(jwtUrlSecond)
                .then().log().all()
                .extract().response();


    }

}

