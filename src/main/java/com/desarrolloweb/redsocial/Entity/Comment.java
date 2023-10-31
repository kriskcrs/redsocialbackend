package com.desarrolloweb.redsocial.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comentario")
@Getter
@Setter
public class Comment {
    @Id
    @Column(name = "id_comentario")
    private Integer idComment;
    @Column(name = "texto")
    private String text;
    @Column(name = "id_usuario")
    private String idUser;
    @Column(name = "id_publicacion")
    private Integer idPublication;
}