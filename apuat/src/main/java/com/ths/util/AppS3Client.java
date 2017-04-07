package com.ths.util;

import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;


public class AppS3Client {
	public AWSCredentials credentials =null;
	public AmazonS3 conn =null;
	private String bucket=null;

	public AppS3Client(String accessKey,String secretKey,String bucket)
	{
		credentials = new BasicAWSCredentials(accessKey, secretKey);
		conn = new AmazonS3Client(credentials);
		conn.setEndpoint("s3.amazonaws.com");
		this.bucket=bucket;
	}
	public  InputStream getS3File(String filename)
	{
		S3Object object=conn.getObject(new GetObjectRequest(bucket, filename));
		return object.getObjectContent();
	}
	
	public void saveS3File(String filename,InputStream stream)
	{
		conn.putObject(bucket, filename, stream, new ObjectMetadata());
		conn.setObjectAcl(bucket, filename, CannedAccessControlList.PublicRead);
	}
	
	public void deleteS3File(String keyName){
		 try {
			 conn.deleteObject(new DeleteObjectRequest(bucket, keyName));
	        } catch (AmazonServiceException ase) {
	        } catch (AmazonClientException ace) {
        }
	}
}
