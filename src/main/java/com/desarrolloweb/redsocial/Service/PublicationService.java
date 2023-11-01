package com.desarrolloweb.redsocial.Service;

import com.desarrolloweb.redsocial.Entity.Publication;
import com.desarrolloweb.redsocial.Repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
@CrossOrigin
public class PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    @GetMapping(path = "/consult/publications")
    private ResponseEntity<List<Publication>> publicationList() {
        return ResponseEntity.ok(publicationRepository.findAll());
    }


}
