package com.pet.decaf.storage.aws;

import com.pet.decaf.entity.ContentEntity;
import com.pet.decaf.storage.StorageHandler;

import java.util.Date;
import java.util.List;

public class S3StorageHandler implements StorageHandler {

    @Override
    public List<ContentEntity> getAll() {
        return null;
    }

    @Override
    public ContentEntity getByDate(Date date) {
        return null;
    }

    @Override
    public void putContent(ContentEntity entity) {

    }
}
