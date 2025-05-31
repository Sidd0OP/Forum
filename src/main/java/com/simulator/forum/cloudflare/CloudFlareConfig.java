package com.simulator.forum.cloudflare;

import java.net.URI;

import org.springframework.context.annotation.Bean;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

public class CloudFlareConfig {
	
	
	@Bean
	public S3Client clientConfig() 
	{
		S3Configuration serviceConfig = S3Configuration.builder()
               
                .pathStyleAccessEnabled(true)
                .chunkedEncodingEnabled(false)
                .build();
		
		return S3Client.builder()
                .httpClientBuilder(ApacheHttpClient.builder())
                .region(Region.of("auto"))
                .endpointOverride(URI.create(cloudflareProperties.getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(
                                cloudflareProperties.getAccessKey(),
                                cloudflareProperties.getSecretKey())))
                .serviceConfiguration(serviceConfig)
                .build();
		
		
	}

}
