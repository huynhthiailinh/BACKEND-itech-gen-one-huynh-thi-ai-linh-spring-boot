package com.dut.employee.controller;

import com.dut.employee.service.EmployeeService;
import com.dut.employee.service.ImageService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

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

    private final Path p = Paths.get(System.getProperty("user.home") + "/source/repos/iTechGenOne/employee/images/");

    @RequestMapping(value={ "/", "/home"})
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/post_upload_avatar_file", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadAvatarFile(@RequestParam("uploadfile") MultipartFile uploadfile) {
        JSONObject resJsonData=new JSONObject();
        try {
            if(uploadfile.isEmpty()){
                System.out.println("Empty");
            }

            Files.copy(uploadfile.getInputStream(), p.resolve(uploadfile.getOriginalFilename()));

            resJsonData.put("status", 200);
            resJsonData.put("message", "Success!");
            resJsonData.put("data", uploadfile.getOriginalFilename());
        }catch (Exception e) {
            System.out.println(e.getMessage());
            resJsonData.put("status", 400);
            resJsonData.put("message", "Upload Image Error!");
            resJsonData.put("data", "");
        }
        return resJsonData.toString();
    }

    @GetMapping("files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serverFile(@PathVariable String filename){
        Resource file = loadAsResource(filename);
        return ResponseEntity
                .ok()
                .body(file);
    }

    public Resource loadAsResource(String filename) {
//        try {
//            Path file = p.resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//            if(resource.exists() || resource.isReadable()) {
//                return resource;
//            }
//            else {
//                System.out.println("no file");
//            }
//        } catch (MalformedURLException e) {
//            System.out.println(e);
//        }
        return null;
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(p, 1)
                    .filter(path -> !path.equals(p))
                    .map(path -> p.relativize(path));
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
