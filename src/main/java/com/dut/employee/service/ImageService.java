package com.dut.employee.service;

import com.dut.employee.constant.DefaultParam;
import com.dut.employee.constant.DefaultPath;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {
    public String uploadToLocalFileSystem(MultipartFile multipartFile, String type, Long id) {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path storageDirectory = Paths.get(DefaultPath.ROOT_FOLDER);
        String imageDir = "";
        Path imageDirPath = null;

        if(!Files.exists(storageDirectory)) {
            try {
                Files.createDirectories(storageDirectory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            switch(type) {
                case DefaultParam.AVATAR:
                    imageDir = DefaultPath.AVATAR_FOLDER + File.separator + id;
                    break;
                case DefaultParam.DEPARTMENT:
                    imageDir = DefaultPath.DEPARTMENT_FOLDER + File.separator + id;
                    break;
            }

            imageDirPath = Paths.get(imageDir);

            if(!Files.exists(imageDirPath)) {
                try {
                    Files.createDirectories(imageDirPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Path destination = Paths.get(imageDirPath.toString() + "\\" + fileName);

        try {
            Files.copy(multipartFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return type + "/" + id + "/" + fileName;
    }

    public byte[] getImageWithMediaType(String imageName, Long id, String type) throws IOException {
        Path destination = null;
        String imageDir = "";

        switch (type) {
            case DefaultParam.AVATAR:
                imageDir = DefaultPath.AVATAR_FOLDER + File.separator + id;
                break;
            case DefaultParam.DEPARTMENT:
                imageDir = DefaultPath.DEPARTMENT_FOLDER + File.separator + id;
                break;
        }

        destination = Paths.get(imageDir+"\\"+imageName);

        return IOUtils.toByteArray(destination.toUri());
    }
}
