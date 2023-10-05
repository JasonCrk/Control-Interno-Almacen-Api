package com.ADS2.controlinternoalmacenapi.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    Storage firebaseStorage() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("firebase-account.json").getInputStream());
        return StorageOptions
                .newBuilder()
                .setCredentials(googleCredentials).build()
                .getService();
    }
}
