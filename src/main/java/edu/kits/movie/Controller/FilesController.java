package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Response.ResponseMessageDto;
import edu.kits.movie.Service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(Api.BASE)
@RequiredArgsConstructor
public class FilesController {

    private final FileStorageService storageService;
    @PostMapping
    public ResponseEntity<ResponseMessageDto> uploadFile(@RequestParam("file") MultipartFile file){
        String message = "";
        try {
            storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDto(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageDto(message));
        }
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        // Replace the base URL with your actual Firebase storage base URL
        String baseUrl = "https://firebasestorage.googleapis.com/v0/b/movie-1fb03.appspot.com/o/";
        String imageUrl = baseUrl + fileName + "?alt=media";

        // Create a RestTemplate to make the HTTP request
        RestTemplate restTemplate = new RestTemplate();

        // Request the image data as a byte array
        byte[] imageData = restTemplate.getForObject(imageUrl, byte[].class);

        // Prepare the HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the media type according to your image format

        // Create a ByteArrayResource from the image data and set the content length
        assert imageData != null;
        ByteArrayResource resource = new ByteArrayResource(imageData);

        // Return the image data as a resource with appropriate headers and status
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(imageData.length)
                .body(resource);
    }

}
