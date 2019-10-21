package com.worldpay.aws.awsuploaddemo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsUploadDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsUploadDemoApplication.class, args);

		System.out.println("Starting test");

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_WEST_2).build();

		System.out.println(s3Client.getS3AccountOwner().getDisplayName());

		s3Client.createBucket("tazbucket;");

		System.out.println("Ending  test");

	}

}
