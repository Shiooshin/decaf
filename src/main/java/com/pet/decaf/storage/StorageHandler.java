package com.pet.decaf.storage;

import com.pet.decaf.entity.ContentEntity;

import java.util.Date;
import java.util.List;

public interface StorageHandler {
    public List<ContentEntity> getAll();
    public ContentEntity getByDate(Date date);
    public void putContent(ContentEntity entity);
}
