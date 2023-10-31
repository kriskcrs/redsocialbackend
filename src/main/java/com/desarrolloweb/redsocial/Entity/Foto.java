package com.desarrolloweb.redsocial.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "foto")
@Getter
@Setter
public class Foto {
    @Id
    @Column(name = "id_foto")
    private String idFoto;
    @Column(name = "ip_server")
    private String ipServer;
    @Column(name = "ruta")
    private String ruta;
    @Column(name = "id_usuario")
    private String idUser;
    @Column(name = "id_publicacion")
    private Integer idPublicacion;
}
