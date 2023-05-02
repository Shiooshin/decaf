package com.pet.decaf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class CommonConfig {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.access.key}")
    private String accKey;

    @Value("${aws.security.key}")
    private String secKey;

    @Value("${aws.session.token}")
    private String sesToken;

    @Bean
    public S3Client s3Client() {
        StaticCredentialsProvider credentialsProvider =
                StaticCredentialsProvider.create(AwsSessionCredentials.create(accKey, secKey, sesToken));

        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(credentialsProvider)
                .build();
    }
}
