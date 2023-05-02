package com.pet.decaf.storage.aws;

import com.pet.decaf.entity.ContentEntity;
import com.pet.decaf.storage.StorageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class S3StorageHandler implements StorageHandler {

    @Autowired
    private S3Client s3Client;
    @Value("${aws.bucket.name}")
    private String bucketName;

    @Override
    public List<ContentEntity> getAll() {
        try {
            ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .bucket(bucketName)
                    .build();

            ListObjectsResponse res = s3Client.listObjects(listObjects);
            List<S3Object> objects = res.contents();
            for (S3Object myValue : objects) {
                String filename = myValue.key();
                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(filename)
                        .build();

                ResponseInputStream<GetObjectResponse> object = s3Client.getObject(getObjectRequest);
            }

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return new LinkedList<>();
    }

    @Override
    public ContentEntity getByDate(Date date) {
        return null;
    }

    @Override
    public void putContent(ContentEntity entity) {

    }
}
