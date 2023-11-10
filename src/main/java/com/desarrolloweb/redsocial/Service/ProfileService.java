package com.desarrolloweb.redsocial.Service;


import com.desarrolloweb.redsocial.Entity.*;
import com.desarrolloweb.redsocial.Repository.PhotoRepository;
import com.desarrolloweb.redsocial.Repository.UserRepository;
import com.desarrolloweb.redsocial.Tools.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;


@RestController
@RequestMapping("v1")
@CrossOrigin
public class ProfileService {

    //vars
    String okU = "Se actualiza correctamente";
    String failsU = "Hubo un problema al actualizar";
    HashMap<String, String> response = new HashMap<>();

    @Autowired
    UserRepository userRepository;
    @Autowired
    PhotoRepository photoRepository;


    @GetMapping(path = "/consult/profile/{iduser}")
    private ResponseEntity<User> consultProfileUser(@PathVariable String iduser) {
        return ResponseEntity.ok(userRepository.findByIdUser(iduser));
    }

    //Modificar perfil
    @PutMapping(path = "/updateProfile/{id}")
    private ResponseEntity<HashMap<String, String>> updateUser(@RequestBody User user, @PathVariable String id) {
        response.clear();
        try {
            System.out.println("id de la foto -> " + user.getFotoIdFoto());
            User userfind = userRepository.findByIdUser(id);
            userfind.setName(user.getName());
            userfind.setLastName(user.getLastName());
            userfind.setDob(user.getDob());
            userfind.setPassword(new Encoding().MD5(user.getPassword()));
            userfind.setFotoIdFoto(user.getFotoIdFoto());
            userRepository.save(userfind);
            response.put("message", "Usuario actualizado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("error por " + e.getCause());
            response.put("message", "error al actualizar usuario");
            return ResponseEntity.badRequest().body(response);
        }
    }


}




