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
    public final String storageDirectoryPath = DefaultPath.ROOT_FOLDER;

    public String uploadToLocalFileSystem(MultipartFile file, String type, Long id) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path storageDirectory = Paths.get(storageDirectoryPath);
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
                case DefaultParam.EVENT:
                    imageDir = DefaultPath.EVENT_FOLDER + File.separator + id;
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
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return DefaultParam.AVATAR + "/" + id + "/" + fileName;
    }

    public byte[] getImageWithMediaType(String imageName, Long id, String type) throws IOException {
        Path destination = null;
        String imageDir = "";

        switch (type) {
            case DefaultParam.AVATAR:
                imageDir = DefaultPath.AVATAR_FOLDER + File.separator + id;
                break;
            case DefaultParam.EVENT:
                imageDir = DefaultPath.EVENT_FOLDER + File.separator + id;
                break;
        }

        destination = Paths.get(imageDir+"\\"+imageName);

        return IOUtils.toByteArray(destination.toUri());
    }
}
