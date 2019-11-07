package com.worldpay.aws.awsuploaddemo;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

public class UploadDemo {

    private static AWSCredentialsProvider getAnonymousCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new AnonymousAWSCredentials());
    }

    private static AWSCredentialsProvider getAssumeRoleCredentialsProvider() {
        return new STSAssumeRoleSessionCredentialsProvider.Builder(
                "arn:aws:iam::388570974987:role/wp_power_role",
                "demo")
                .build();
    }

    private static AmazonS3 getS3Client() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .withCredentials(getAssumeRoleCredentialsProvider())
                .build();
    }

    public static void main(String[] args) {
        String bucketName = "secure-tms-upload";
        String fileObjKeyName = "demo.zip";
        String filePath = UploadDemo.class.getClassLoader().getResource("demo.zip").getPath();

        try {
            AmazonS3 s3Client = getS3Client();

            System.out.println("S3 client created");

            // Upload a file as a new object with ContentType and title specified.
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(filePath));
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("application/zip");
            metadata.addUserMetadata("x-amz-meta-title", "demo");
            request.setMetadata(metadata);
            s3Client.putObject(request);

            System.out.println("Upload successful, file url: " + s3Client.getUrl(bucketName, fileObjKeyName));

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