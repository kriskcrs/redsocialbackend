package com.desarrolloweb.redsocial.Service;
import com.desarrolloweb.redsocial.Entity.User;
import com.desarrolloweb.redsocial.Repository.*;
import com.desarrolloweb.redsocial.Tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping("v1")
@CrossOrigin
public class UserService {
    HashMap<String, String> response = new HashMap<>();

    //vars
    String okU = "Se actualiza correctamente";
    String okC = "Se creo correctamente";
    String failsU = "Hubo un problema al actualizar";
    String failsC = "Hubo un problema al crear";
    String delete = "El registro fue eliminado exitosamente";
    String deleteE = "El registro tiene mas dependencias no puede ser borrado";
    String sesionFail = "Sesion no valida";
    Integer calendaryDay = 20;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/md5/{text}")
    private String Md5(@PathVariable String text) {
        return new Encoding().MD5(text);
    }

    @PostMapping(path = "/createUser")
    private ResponseEntity<HashMap<String, String>> createUser(@RequestBody User user) {
        if(user != null) {
            user.setPassword(new Encoding().MD5(user.getPassword()));
            user.setDateOfAdmission(new Date());
            userRepository.save(user);
            response.put("message", "Usuario creado");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Llenar todos los campos");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
