package com.desarrolloweb.redsocial.Service;

import com.desarrolloweb.redsocial.Entity.Comment;
import com.desarrolloweb.redsocial.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
@CrossOrigin
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    //crud de comentarios

    //consulta de todos los comentarios
    @GetMapping (path = "/consult/comment")
    private ResponseEntity<List<Comment>> consultComment(){
        return ResponseEntity.ok(commentRepository.findAll());
    }

    //consulta de comentarios por publicacion
    @GetMapping (path = "/consult/comment/{idpublication}")
    private ResponseEntity<List<Comment>> consultCommentUser(@PathVariable int idpublication){
        return ResponseEntity.ok(commentRepository.findCommentByIdPublication(idpublication));
    }


}
