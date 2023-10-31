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
    @Column(name = "id_publicacion")
    private Integer idPublication;
    @Column(name = "nombre")
    private String name;
    @Column(name = "reaccion")
    private String reaction;
    @Column(name = "fecha_creacion")
    private Date creationDate;
    @Column(name = "fecha_modificacion")
    private Date modificationDate;
}
