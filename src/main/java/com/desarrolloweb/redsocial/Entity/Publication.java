package com.desarrolloweb.redsocial.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "publicacion")
@Getter
@Setter
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacion")
    private Integer idPublication;
    @Column(name = "fecha_creacion")
    private Date creationDate;
    @Column(name = "fecha_modificacion")
    private Date modificationDate;
    @Column(name = "usuario_id_usuario")
    private String userIdUser;
    @Column(name = "descripcion")
    private String decription;
    @Column(name = "foto_id_foto")
    private String photoIdPhoto;
    @Column(name = "emoji")
    private Integer emoji;
}
