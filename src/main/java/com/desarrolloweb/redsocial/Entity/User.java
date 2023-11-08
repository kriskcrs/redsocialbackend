package com.desarrolloweb.redsocial.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id_usuario")
    private String idUser;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellido")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "fecha_nacimiento")
    private Date dob;
    @Column(name = "session")
    private String session;
    @Column(name = "requiere_cambio")
    private String requiredChange;
    @Column(name = "fecha_ingreso")
    private Date dateOfAdmission;
    @Column(name = "foto_id_foto")
    private Integer photoIdPhoto;


}
