package com.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.wiremock.properties.loader.PropertiesLoader;
import com.wiremock.properties.loader.PropertiesSupplier;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class BaseApiTest {
    private WireMockServer wireMockServer;

    private boolean isFakeBackend;

    @BeforeSuite
    public void loadGlobalProperties() {
        PropertiesLoader.loadGlobalProperties(this.getClass(), "base.properties");
        isFakeBackend = PropertiesSupplier.getPropertyAsBoolean("use.fake.backend");
        RestAssured.baseURI = isFakeBackend
                ? PropertiesSupplier.getProperty("fake.server")
                : PropertiesSupplier.getProperty("real.server");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        if (isFakeBackend) {
            enableFakeBackend();
        }
    }

    @AfterSuite
    public void shutDown() {
        if (isFakeBackend) {
            wireMockServer.stop();
        }
    }

    protected void stubFor(MappingBuilder mappingBuilder) {
        if (isFakeBackend) {
            WireMock.stubFor(mappingBuilder);
        }
    }

    private void enableFakeBackend() {
        RestAssured.port = PropertiesSupplier.getPropertyAsInteger("port");
        wireMockServer = new WireMockServer(options().port(PropertiesSupplier.getPropertyAsInteger("port")));
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());

    }


}
