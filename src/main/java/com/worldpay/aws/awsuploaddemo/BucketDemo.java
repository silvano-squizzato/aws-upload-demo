package com.worldpay.aws.awsuploaddemo;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

public class BucketDemo {

    public static void main(String[] args) throws IOException {
        String bucketName = "test-bucket-1";
        try {
            STSAssumeRoleSessionCredentialsProvider credentials = new STSAssumeRoleSessionCredentialsProvider.Builder("arn:aws:iam::388570974987:role/wp_power_role", "test")
                    .build();

            // Provide temporary security credentials so that the Amazon S3 client
            // can send authenticated requests to Amazon S3.
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.EU_WEST_1)
                    .withCredentials(credentials)
                    .build();

            System.out.println("Creating a bucket " + bucketName);

            s3Client.createBucket(bucketName);

            System.out.println("Created bucket " + bucketName);

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }
}