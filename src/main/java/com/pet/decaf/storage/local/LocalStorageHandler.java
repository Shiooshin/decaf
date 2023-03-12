package com.pet.decaf.storage.local;

import com.pet.decaf.entity.ContentEntity;
import com.pet.decaf.storage.StorageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class LocalStorageHandler implements StorageHandler {

    @Value("${local.path}")
    private String localFilePath;

    @Override
    public List<ContentEntity> getAll() {
        if(isDirectory()) {
            return FileUtils.getAllContent(Paths.get(localFilePath));
        }
        return new LinkedList<>();
    }

    @Override
    public ContentEntity getByDate(Date date) {
        return FileUtils.getContentByDate(localFilePath, date);
    }

    @Override
    public void putContent(ContentEntity entity) {
        FileUtils.saveContentLocally(localFilePath, entity);

    }

    private boolean isDirectory() {
        Path contentFolder = Paths.get(localFilePath);
        return Files.exists(contentFolder) && Files.isDirectory(contentFolder);
    }
}
