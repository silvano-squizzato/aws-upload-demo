package com.worldpay.aws.awsuploaddemo;

import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsUploadDemoApplication {

	public static void main(String[] args) {
		Regions clientRegion = Regions.EU_WEST_1;
		SpringApplication.run(AwsUploadDemoApplication.class, args);

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new STSAssumeRoleSessionCredentialsProvider("arn:aws:iam::388570974987:role/wp_power_role", "test"))
				.withRegion(clientRegion)
				.build();
		System.out.println(s3Client.getS3AccountOwner().getDisplayName());

		s3Client.createBucket("test-bucket-creation-in-the-most-convoluted-way");

		System.out.println("Ending  test");

	}

}
