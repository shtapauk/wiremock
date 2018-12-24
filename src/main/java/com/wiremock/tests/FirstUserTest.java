package com.wiremock.tests;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.wiremock.BaseApiTest;
import com.wiremock.files.FileReader;
import com.wiremock.pojo.ResponseEntity;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class FirstUserTest extends BaseApiTest {

    @Test
    public void checkFirstUserReturns() {
        String body = FileReader.readFileAsString("/Users/yshtapauk/IdeaProjects/wiremock/src/main/resources/requests.body/firstUserResponseBody.json");

        MappingBuilder mappingBuilder =
                get(urlPathMatching("/users/"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(body));

        super.stubFor(mappingBuilder);

        ResponseEntity response = RestAssured
                .given()
                .when()
                .get("/users/1").getBody().as(ResponseEntity.class);
        System.out.println(response.toString());
        System.out.println("body:");
        System.err.println(body);

    }
}
