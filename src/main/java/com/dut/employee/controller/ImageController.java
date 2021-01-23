package com.dut.employee.controller;

import com.dut.employee.constant.DefaultParam;
import com.dut.employee.model.Employee;
import com.dut.employee.service.EmployeeService;
import com.dut.employee.service.ImageService;
import org.springframework.http.HttpStatus;
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
    public final EmployeeService employeeService;

    public ImageController(ImageService imageService, EmployeeService employeeService) {
        this.imageService = imageService;
        this.employeeService = employeeService;
    }

    @PostMapping(value = "upload")
    public ResponseEntity uploadImage(@RequestParam MultipartFile file, @RequestParam String type, @RequestParam Long id) {
        switch (type) {
            case DefaultParam.AVATAR:
                Employee employee = employeeService.getEmployeeById(id);
                employee.setAvatar(this.imageService.uploadToLocalFileSystem(file, type, id));
                employeeService.updateEmployee(employee);
                break;
            case DefaultParam.EVENT:
                break;
        }
        return new ResponseEntity(this.imageService.uploadToLocalFileSystem(file, type, id), HttpStatus.OK);
    }

    @GetMapping(
            value = "getImage/{type}/{id}/{imageName:.+}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable(name = "type") String type, @PathVariable(name = "id") Long id, @PathVariable(name = "imageName") String fileName) throws IOException {
        return this.imageService.getImageWithMediaType(fileName, id, type);
    }
}
