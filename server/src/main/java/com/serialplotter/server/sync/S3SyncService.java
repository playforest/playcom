package com.serialplotter.server.sync;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class S3SyncService {
    private static final String PROFILE_NAME = "029121535257_PowerUserAccess";
    private static final String BUCKET_NAME = "serialstreams";

    public S3SyncService() {
    }

    public Boolean writeObject(String key, String data) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new ProfileCredentialsProvider(PROFILE_NAME))
                .build();

        try {
            InputStream inputStream = new ByteArrayInputStream(data.getBytes());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(data.getBytes().length);

            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, inputStream, metadata);
            s3.putObject(putObjectRequest);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            return false;
        }
        System.out.println("done");
        return true;
    }

    public void listObjects(String bucketName) {
        System.out.format("objects in s3 bucket %s:\n", bucketName);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new ProfileCredentialsProvider(PROFILE_NAME))
                .build();
        ListObjectsV2Result result = s3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        for (S3ObjectSummary os : objects) {
            System.out.println("* " + os.getKey());
        }
    }

    public String downloadObject(String key) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new ProfileCredentialsProvider(PROFILE_NAME))
                .build();

        StringBuilder result = new StringBuilder();

        try {
            S3Object o = s3.getObject(BUCKET_NAME, key);
            S3ObjectInputStream s3is = o.getObjectContent();

//            FileOutputStream fos = new FileOutputStream(new File(key));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
//            while ((read_len = s3is.read(read_buf)) > 0) {
//                System.out.println(read_buf);
//                fos.write(read_buf, 0, read_len);
//            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(s3is, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                result.append(line).append("\n");
            }

            s3is.close();
//            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        return result.toString();
    }

    public void deleteObject(String bucketName, String key) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new ProfileCredentialsProvider(PROFILE_NAME))
                .build();
        try {
            s3.deleteObject(bucketName, key);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }
}
