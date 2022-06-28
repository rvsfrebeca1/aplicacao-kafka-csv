package com.rebecacorp.aplicacaokafkacsv.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;


import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3Util {

    private static final String BUCKET = "bucker-rebeca";

    public static void uploadFile(String fileName, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException, InterruptedException,
            ExecutionException {
        
                AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                        System.getenv("AWS_ACCESS_KEY"),
                        System.getenv("AWS_SECRET_KEY"));
        S3Client client = S3Client.builder().region(Region.US_EAST_1).credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(fileName)
                .build();

        client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));

    }
}