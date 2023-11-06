package com.desarrolloweb.redsocial.Service;
import com.desarrolloweb.redsocial.Entity.Photo;
import com.desarrolloweb.redsocial.Entity.Publication;
import com.desarrolloweb.redsocial.Entity.PublicationPhoto;
import com.desarrolloweb.redsocial.Entity.User;
import com.desarrolloweb.redsocial.Repository.PhotoRepository;
import com.desarrolloweb.redsocial.Tools.Encoding;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    PhotoRepository photoRepository;

    HashMap<String, String> response = new HashMap<>();

    @GetMapping(path = "/consult/publications")
    private ResponseEntity<List<PublicationPhoto>> publicationList() {
        List<Publication> publicationList= publicationRepository.findAll();
        List<Photo> photoList= photoRepository.findAll();
        List<PublicationPhoto> publicationPhotosList= new ArrayList<>();

        for (Publication publication:publicationList
             ) {
            for (Photo photo:photoList
                 ) {
                if(photo.getIdPhoto().equals(publication.getPhotoIdPhoto())){
                    PublicationPhoto publicationPhoto = new PublicationPhoto();
                    publicationPhoto.setPublication(publication);
                    publicationPhoto.setPhoto(photo);
                    publicationPhotosList.add(publicationPhoto);
                }
            }
        }
        return ResponseEntity.ok(publicationPhotosList);
    }


    //Crea  publicacion
    @PostMapping(path = "/createPublication")
    private ResponseEntity<HashMap<String, String>> createPublication(@RequestBody Publication publication){
        if(publication != null) {
            int idPublication = publicationRepository.findAll().size();
            idPublication++;
            publication.setIdPublication(idPublication);
            publication.setCreationDate(new Date());
            publicationRepository.save(publication);
            response.put("message", "Publicacion Creada");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Llenar todos los campos");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Modifica Publicacion
    @PutMapping(path = "/modifyPublication")
    private ResponseEntity<HashMap<String, String>> modifyPublication(@RequestBody Publication publication){
        if(publication != null) {
            publication.setModificationDate(new Date());
            publicationRepository.save(publication);
            response.put("message", "Publicacion Modificada");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Llenar todos los campos");
            return ResponseEntity.badRequest().body(response);
        }
    }


    //Elimina  publicacion
    @DeleteMapping(path = "/delete/publication/{id}")
    private ResponseEntity<HashMap<String, String>> deletePublication(@PathVariable int id) {
        response.clear();
        publicationRepository.deleteById(id);
        response.put("message", "Publicacion eliminada");
        return ResponseEntity.ok(response);
    }

}
