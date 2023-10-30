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
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "password")
    private String password;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Column(name = "session")
    private String session;
    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

}
