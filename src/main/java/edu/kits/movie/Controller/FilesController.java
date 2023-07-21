package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Response.ResponseMessageDto;
import edu.kits.movie.Service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public  ResponseEntity<?> getImage(@PathVariable String fileName){
        return ResponseEntity.ok(storageService.load(fileName));
    }

}
