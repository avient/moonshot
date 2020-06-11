package utils;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import infrastructure.Configuration;
import infrastructure.Logger;

import java.io.File;

public class S3Util {
    private static S3Util instance = null;
    private static final String DownloadsDirectory = Configuration.getInstance().getProperty("tempDataDirectory");

    private S3Util() {
    }

    public static synchronized S3Util getInstance() {
        if (instance == null) {
            instance = new S3Util();
        }
        return instance;
    }

    public void uploadToS3(String fileName) {
        AWSCredentials credentials = new BasicAWSCredentials(
                Configuration.getInstance().getProperty("aws_access_key_id_env"),
                Configuration.getInstance().getProperty("aws_secret_access_key_env")
        );
        String bucketName = Configuration.getInstance().getProperty("aws_bucket_name_env");
        Logger.getInstance().info(String.format("Uploading file %s into %s S3 bucket", fileName, bucketName));

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withRegion(Configuration.getInstance().getProperty("aws_region_env"))
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .build();

            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, new File(DownloadsDirectory + File.separator + fileName));
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/text");
            request.setMetadata(metadata);
            s3Client.putObject(request);
            Logger.getInstance().info("Uploading to S3 finished");
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }
}
