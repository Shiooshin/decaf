package com.pet.decaf.repository;

import com.pet.decaf.entity.ContentEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class ContentRepository {

    public List<ContentEntity> getAll() {
        return new LinkedList<>();
    }

    public ContentEntity getByDate(Date date) {
        return ContentEntity.builder().build();
    }

    public void putContent(ContentEntity entity) {

    }

}
