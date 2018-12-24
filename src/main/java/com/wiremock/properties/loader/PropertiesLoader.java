package com.wiremock.properties.loader;

import java.io.IOException;
import java.io.InputStream;

public class PropertiesLoader {

    public static void loadGlobalProperties(Class loaderClass, String path) {
        try (final InputStream stream = loaderClass.getClassLoader().getResourceAsStream(path)) {
            PropertiesSupplier.loadGlobalProperties(stream);
        } catch (IOException e) {
            throw new ResourseReadFailedException("Problem occurred during reading properties file", e);
        }
    }

    public static void loadSystemProperties() {
        PropertiesSupplier.loadSystemProperties();
    }

    public static void loadEnvProperties() {
        PropertiesSupplier.loadEnvProperties();
    }
}
