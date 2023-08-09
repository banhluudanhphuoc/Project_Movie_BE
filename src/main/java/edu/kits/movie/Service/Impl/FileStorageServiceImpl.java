package edu.kits.movie.Service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.UUID;
import java.util.stream.Stream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
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
    private String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/movie-1fb03.appspot.com/o/%s?alt=media";
    private final String root = "uploads";
    private String uploadFileToFireBase(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("movie-1fb03.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(Files.probeContentType(file.toPath())).build();
        ClassPathResource serviceAccount = new ClassPathResource("movie-1fb03-firebase-adminsdk-c1twk-7f70b5fa97.json");
        Storage storage = StorageOptions.newBuilder().
                setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).
                setProjectId("movie").build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }
    @Override
    public String save(MultipartFile multipartFile) {
        try {
            UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString();
            String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = uuidString + "." + ext;                     // to get original file name
            // to generated random string values for file name.
            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String TEMP_URL = this.uploadFileToFireBase(file, fileName);
            // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return fileName;                     // Your customized response
        } catch (Exception e) {
            throw new RuntimeException("Upload file unsuccessfully!");
        }
    }
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

//    @Override
//    public String save(MultipartFile file) throws IOException {
//        try (InputStream inputStream = file.getInputStream()) {
//            UUID uuid = UUID.randomUUID();
//            String uuidString = uuid.toString();
//            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
//            String fileName = uuidString + "." + ext;
//            ClassPathResource classPathResource = new ClassPathResource(root);
//            Path newPath = Paths.get(classPathResource.getURI()).resolve(fileName);
//            Files.copy(inputStream, newPath, REPLACE_EXISTING);
//            return fileName;
//        } catch (IOException e) {
//            throw new IOException("Could not save the file!");
//        }
//    }

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
