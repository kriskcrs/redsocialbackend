package com.desarrolloweb.redsocial.Service;

import com.desarrolloweb.redsocial.Tools.ResizeImage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
@CrossOrigin
public class ResizeImageService {

    @PostMapping(path = "/resize")
    private String resize(){
        //new ResizeImage().resize();
        return "Yep";
    }
}
