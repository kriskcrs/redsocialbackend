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
public class Publicacion {
    @Id
    @Column(name = "id_publicacion")
    private Integer idPublicacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "reaccion")
    private String reaccion;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;
}
