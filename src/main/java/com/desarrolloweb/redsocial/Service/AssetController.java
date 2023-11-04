/*
package com.desarrolloweb.redsocial.Service;

import com.desarrolloweb.redsocial.Entity.vm.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping ("v1")
public class AssetController {

    @Autowired
    private S3Service s3Service;

    @PostMapping(path = "/up")
    Map<String,String> upload(@RequestParam MultipartFile file){
        String key = s3Service.putObject(file);
        Map<String, String> result = new HashMap<>();

        result.put("key",key);
        result.put("url",s3Service.getObjectUrl(key));
        return result;
    }

    @GetMapping (value = "/getObject", params = "key")
    ResponseEntity<ByteArrayResource> getObject(@RequestParam String key){
        Asset asset = s3Service.getObject(key);
        ByteArrayResource resource = new ByteArrayResource(asset.getContent());

        return ResponseEntity
                .ok()
                .header("Content-type", asset.getContenType())
                .contentLength(asset.getContent().length)
                .body(resource);
    }


    @DeleteMapping(value ="/delete-object", params = "key")
    void deleteObject(@RequestParam String key){
        s3Service.deleteObject(key);
    }


}
*/
