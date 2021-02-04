package com.dut.employee.controller;

import com.dut.employee.service.EmployeeService;
import com.dut.employee.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/images")
public class ImageController {
    public final ImageService imageService;

    public final EmployeeService employeeService;

    @GetMapping(
            value = "getImage/{type}/{id}/{imageName:.+}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable(name = "type") String type,
                                 @PathVariable(name = "id") Long id,
                                 @PathVariable(name = "imageName") String fileName) throws IOException {
        return this.imageService.getImageWithMediaType(fileName, id, type);
    }
}
