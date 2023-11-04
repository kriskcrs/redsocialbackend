package com.desarrolloweb.redsocial.Service;


import com.desarrolloweb.redsocial.Entity.Photo;
import com.desarrolloweb.redsocial.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
@CrossOrigin
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;



    @GetMapping (path = "/photos")
    private List<Photo> photoList(){
        return photoRepository.findAll();
    }

    @PostMapping(path = "/upPhoto")
    private Photo photoCreate(@RequestBody Photo photo){




        return photoRepository.save(photo);
    }

}
