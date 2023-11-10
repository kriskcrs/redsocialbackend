package com.desarrolloweb.redsocial.Service;

import com.desarrolloweb.redsocial.Entity.Comment;
import com.desarrolloweb.redsocial.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("v1")
@CrossOrigin
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    //vars
    HashMap<String, String> response = new HashMap<>();


    //crud de comentarios

    //consulta de todos los comentarios
    @GetMapping(path = "/consult/comment")
    private ResponseEntity<List<Comment>> consultComment() {
        return ResponseEntity.ok(commentRepository.findAll());
    }

    //consulta de comentarios por publicacion
    @GetMapping(path = "/consult/comment/{idpublication}")
    private ResponseEntity<List<Comment>> consultCommentUser(@PathVariable int idpublication) {
        return ResponseEntity.ok(commentRepository.findCommentByIdPublication(idpublication));
    }

    //crea comentario por publicacion
    @PostMapping(path = "/create/comment")
    private ResponseEntity<HashMap<String, String>> createComment(@RequestBody Comment comment) {
        response.clear();
        try {
            commentRepository.save(comment);
            response.put("message", "Comentario agreado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("error por " + e.getCause());
            response.put("message", "error al crear comentario");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //elimina  comentario por publicacion
    @DeleteMapping(path = "/delete/comment/{id}")
    private ResponseEntity<HashMap<String, String>> deleteComment(@PathVariable int id) {
        response.clear();
        commentRepository.deleteById(id);
        response.put("message", "Comentario eliminado");
        return ResponseEntity.ok(response);
    }


    //actualiza comentario por publicacion
    @PostMapping(path = "/update/comment")
    private ResponseEntity<HashMap<String, String>> updateComment(@RequestBody Comment comment) {
        response.clear();
        try {
            commentRepository.save(comment);
            response.put("message", "Comentario actualizado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("error por " + e.getCause());
            response.put("message", "error al actualizar comentario");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
