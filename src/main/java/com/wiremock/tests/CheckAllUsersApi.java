package com.wiremock.tests;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.wiremock.BaseApiTest;
import com.wiremock.files.FileReader;
import com.wiremock.pojo.ResponseEntity;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class CheckAllUsersApi extends BaseApiTest {

    @Test
    public void checkFirstUserReturns() {
        String body = FileReader.readFileAsString("/Users/yshtapauk/IdeaProjects/wiremock/src/main/resources/requests.body/allUsersResponseBody.json");

        MappingBuilder mappingBuilder =
                get(urlPathMatching("/users/"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(body));

        super.stubFor(mappingBuilder);
        List<ResponseEntity> response = RestAssured.given()
                .when()
                .get("/users/")
                .then()
                .extract()
                .body()
                .jsonPath().getList(".", ResponseEntity.class);
        Assert.assertEquals(response.size(), 10);

    }
}
