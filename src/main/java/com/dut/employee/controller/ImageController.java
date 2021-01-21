package com.dut.employee.controller;

import com.dut.employee.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "api/images")
public class ImageController {
    public final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

//    @PostMapping(value = "upload")
//    public ResponseEntity uploadImage(@RequestParam MultipartFile file, @RequestParam String type) {
//        return this.imageService.uploadToLocalFileSystem(file, type);
//    }

    @GetMapping(
            value = "getImage/{imageName:.+}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable(name = "imageName") String fileName) throws IOException {
        return this.imageService.getImageWithMediaType(fileName);
    }
}
