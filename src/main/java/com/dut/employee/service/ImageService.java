package com.dut.employee.service;

import com.dut.employee.constant.DefaultParam;
import com.dut.employee.constant.DefaultPath;
import org.apache.commons.io.IOUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class ImageService {

    public final String storageDirectoryPath = DefaultPath.ROOT_FOLDER;

    public String uploadToLocalFileSystem(MultipartFile file, String type, Long id) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path storageDirectory = Paths.get(storageDirectoryPath);
        String imageDir = "";

        if(!Files.exists(storageDirectory)) { // if the folder does not exist
            try {
                Files.createDirectories(storageDirectory);

                switch (type) {
                    case DefaultParam.AVATAR:
                        imageDir = DefaultPath.AVATAR_FOLDER;
                        break;
                    case DefaultParam.EVENT:
                        imageDir = DefaultPath.EVENT_FOLDER;
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();// print the exception
            }
        }

        Path destination = Paths.get(storageDirectory.toString() + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);// we are Copying all bytes from an input stream to a file

        } catch (IOException e) {
            e.printStackTrace();
        }

        // the response will be the download URL of the image
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/images/getImage/")
                .path(fileName)
                .toUriString();
        // return the download image url as a response entity
        return fileDownloadUri;
    }

    public byte[] getImageWithMediaType(String imageName) throws IOException {
        Path destination = Paths.get(storageDirectoryPath+"\\"+imageName);// retrieve the image by its name
        return IOUtils.toByteArray(destination.toUri());
    }

}
