package edu.kits.movie.Service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.UUID;
import java.util.stream.Stream;

import edu.kits.movie.Service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {
    private final String root = "src/main/uploads";

    @Override
    public void init() {
        try {
            ClassPathResource classPathResource = new ClassPathResource(root);
            if (!classPathResource.exists()) {
                Files.createDirectory(Paths.get(classPathResource.getURI()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String save(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString();
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = uuidString + "." + ext;
            ClassPathResource classPathResource = new ClassPathResource(root);
            Path newPath = Paths.get(classPathResource.getURI()).resolve(fileName);
            Files.copy(inputStream, newPath, REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new IOException("Could not save the file!");
        }
    }

    @Override
    public Resource load(String filename) {
        Resource resource = new ClassPathResource(root.concat("/").concat(filename));
        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }

    @Override
    public void deleteAll() {
        // Not recommended to delete files in the classpath at runtime
        // If you need file deletion, consider using an external storage location
        // or a database to track file metadata and status.
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            ClassPathResource classPathResource = new ClassPathResource(root);
            return Files.walk(Paths.get(classPathResource.getURI()), 1)
                    .filter(path -> {
                        try {
                            return !path.equals(Paths.get(classPathResource.getURI()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .map(Paths.get(classPathResource.getURI())::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
