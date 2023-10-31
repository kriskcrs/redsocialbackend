package com.desarrolloweb.redsocial.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "foto")
@Getter
@Setter
public class Photo {
    @Id
    @Column(name = "id_foto")
    private String idPhoto;
    @Column(name = "ip_server")
    private String ipServer;
    @Column(name = "ruta")
    private String route;
    @Column(name = "id_usuario")
    private String idUser;
    @Column(name = "id_publicacion")
    private Integer idPublication;
}
