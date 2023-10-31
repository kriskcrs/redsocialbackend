package com.desarrolloweb.redsocial.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comentario")
@Getter
@Setter
public class Comentario {
    @Id
    @Column(name = "id_comentario")
    private Integer idComentario;
    @Column(name = "texto")
    private String texto;
    @Column(name = "id_usuario")
    private String idUsuario;
    @Column(name = "id_publicacion")
    private Integer idPublicacionn;
}
