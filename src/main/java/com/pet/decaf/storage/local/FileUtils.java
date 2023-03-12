package com.pet.decaf.storage.local;

import com.pet.decaf.entity.ContentEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public final class FileUtils {
    private static final DateFormat FORMATTER = new SimpleDateFormat("yyy-MM-dd");

    private FileUtils() {
    }

    public static List<ContentEntity> getAllContent(final Path contentPath) {
        final List<ContentEntity> result;
        try (Stream<Path> entries = Files.list(contentPath)) {
            result = new LinkedList<>(entries.map(path -> {
                try {
                    return buildEntityFromPath(path);
                } catch (IOException e) {
//                    throw new RuntimeException(e);
                    return ContentEntity.builder().build();
                }
            }).toList());
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return new LinkedList<>();
        }
        return result;
    }

    public static void saveContentLocally(final String contentPath, final ContentEntity entity) {
        Path path = Paths.get(composeFilepath(contentPath, entity.getContentDate()));

        // string -> bytes
        try {
            Files.writeString(path, entity.getContent());
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }

    }

    public static ContentEntity getContentByDate(String localFilePath, Date date) {
        Path path = Paths.get(composeFilepath(localFilePath, date));
        if (Files.exists(path)) {
            try {
                return buildEntityFromPath(path);
            } catch (IOException e) {
//                throw new RuntimeException(e);
                return null;
            }
        }
        return null;
    }

    private static String composeFilepath(final String folderPath, final Date date) {
        return folderPath + "/" + FORMATTER.format(date) + ".md";
    }

    private static String cutExtension(final String filename) {
        return filename.substring(0, filename.indexOf('.'));
    }

    private static Date convertDate(String date) {
        return Date.from(LocalDate.parse(date).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static ContentEntity buildEntityFromPath(final Path contentPath) throws IOException {
        return ContentEntity.builder()
                .contentDate(convertDate(cutExtension(contentPath.getFileName().toString())))
                .content(new String(Files.readAllBytes(contentPath))).build();
    }
}
