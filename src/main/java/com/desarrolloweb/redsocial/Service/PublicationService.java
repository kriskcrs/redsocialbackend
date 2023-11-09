package com.desarrolloweb.redsocial.Service;

import com.desarrolloweb.redsocial.Entity.*;
import com.desarrolloweb.redsocial.Repository.CommentRepository;
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

    @Autowired
    CommentRepository commentRepository;

    HashMap<String, String> response = new HashMap<>();


    //devuevle todas las publicaciones
    @GetMapping(path = "/consult/publications")
    private ResponseEntity<List<PublicationPhoto>> publicationList() {
        List<Publication> publicationList = publicationRepository.findAll();
        List<Photo> photoList = photoRepository.findAll();
        List<PublicationPhoto> publicationPhotosList = new ArrayList<>();

        for (Publication publication : publicationList
        ) {
            for (Photo photo : photoList
            ) {
                if (photo.getIdPhoto().equals(publication.getPhotoIdPhoto())) {
                    PublicationPhoto publicationPhoto = new PublicationPhoto();
                    publicationPhoto.setPublication(publication);
                    publicationPhoto.setPhoto(photo);
                    publicationPhotosList.add(publicationPhoto);
                }
            }
        }
        return ResponseEntity.ok(publicationPhotosList);
    }

    //consulta solo publicaciones de un usuario
    @GetMapping(path = "/consult/publication/{id}")
    private ResponseEntity<List<PublicationPhoto>> publication(@PathVariable Integer id) {
        Publication publication = publicationRepository.findByIdPublication(id);
        List<Photo> photoList = photoRepository.findAll();
        List<PublicationPhoto> publicationPhotosList = new ArrayList<>();
        try {
            if(publication != null){
                for (Photo photo : photoList) {
                    if (photo.getIdPhoto().equals(publication.getPhotoIdPhoto())) {
                        PublicationPhoto publicationPhoto = new PublicationPhoto();
                        publicationPhoto.setPublication(publication);
                        publicationPhoto.setPhoto(photo);
                        publicationPhotosList.add(publicationPhoto);
                    }
                }
                return ResponseEntity.ok(publicationPhotosList);
            }
            else{
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            System.out.println("error -> " + e.getCause());
            response.put("message","valores invalidos");
            return ResponseEntity.badRequest().build();
        }

    }


    //consulta solo publicaciones de un usuario
    @GetMapping(path = "/consult/publicationUser/{id}")
    private ResponseEntity<List<Publication>> publicationUser(@PathVariable String id) {
        List<Publication> publicationList = publicationRepository.findAll();
        List<Publication> publicationUserList = new ArrayList<>();
        try {
            for (Publication publication: publicationList)
            {
                if (publication.getUserIdUser().equals(id)) {
                    publicationUserList.add(publication);
                }
            }
            return ResponseEntity.ok(publicationUserList);
        } catch (Exception e) {
            System.out.println("error -> " + e.getCause());
            response.put("message","valores invalidos");
            return ResponseEntity.badRequest().build();
        }

    }



    //Crea  publicacion
    @PostMapping(path = "/createPublication")
    private ResponseEntity<HashMap<String, String>> createPublication(@RequestBody Publication publication) {
        if (publication != null) {
            publication.setEmoji(0);
            publication.setCreationDate(new Date());
            publicationRepository.save(publication);
            response.put("message", "Publicacion Creada");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Llenar todos los campos");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Modifica Publicacion
    @PutMapping(path = "/modifyPublication/{id}")
    private ResponseEntity<HashMap<String, String>> modifyPublication(@PathVariable int id, @RequestBody Publication publication) {
        Publication dataPublication = publicationRepository.findByIdPublication(id);
            if (dataPublication != null) {
                dataPublication.setModificationDate(new Date());
                dataPublication.setDecription(publication.getDecription());
                dataPublication.setPhotoIdPhoto(publication.getPhotoIdPhoto());
                publicationRepository.save(dataPublication);
                response.put("message", "Publicacion Modificada");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Llenar todos los campos");
                return ResponseEntity.badRequest().body(response);
            }
    }

    //Modifica Publicacion
    @PutMapping(path = "/modifyPublication/emoji/{id}/{emoji}")
    private ResponseEntity<HashMap<String, String>> modifyPublicationEmoji(@PathVariable int id, @PathVariable int emoji) {
        Publication dataPublication = publicationRepository.findByIdPublication(id);
        if (dataPublication != null) {
            dataPublication.setEmoji(emoji);
            publicationRepository.save(dataPublication);
            response.put("message", "Reaccion cambiada");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "No se pudo cambiar");
            return ResponseEntity.badRequest().body(response);
        }
    }


    //Elimina  publicacion
    @DeleteMapping(path = "/delete/publication/{id}")
    private ResponseEntity<HashMap<String, String>> deletePublication(@PathVariable int id) {
        response.clear();
        List<Comment> comments =  commentRepository.findCommentByIdPublication(id);
        for (Comment c:comments
             ) {
            commentRepository.delete(c);
        }
        publicationRepository.deleteById(id);
        response.put("message", "Publicacion eliminada");
        return ResponseEntity.ok(response);
    }

}
