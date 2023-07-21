package edu.kits.movie.Service.Impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import edu.kits.movie.Service.FileStorageService;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectories(this.root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String save(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()){
            UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString();
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            Path newPath = root.resolve(uuidString + "." + ext);
            Files.copy(inputStream,newPath, REPLACE_EXISTING);
            return uuidString + "." + ext;
        } catch (IOException e) {
            throw new IOException("Could");
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
//        try {
//            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not load the files!");
//        }
        return null;
    }
}
