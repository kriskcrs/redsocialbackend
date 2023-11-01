package com.desarrolloweb.redsocial.Service;


import com.desarrolloweb.redsocial.Entity.*;
import com.desarrolloweb.redsocial.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("v1")
@CrossOrigin
public class profile {

    //vars
    String okU = "Se actualiza correctamente";
    String failsU = "Hubo un problema al actualizar";
    HashMap<String, String> response = new HashMap<>();

    @Autowired
    UserRepository userRepository;




    //Modificar perfil
    @PutMapping(path = "/updateProfile/{id}")
    private HashMap<String, String> updateUser(@RequestBody User user, @PathVariable String id) {
        try {

            User userFind = userRepository.findByIdUser(id);
            userFind.setName(user.getName());
            userFind.setLastName(user.getName());
            userFind.setDob(user.getDob());
            userRepository.save(userFind);
            response.put("code", "200");
            response.put("message", okU);
            return response;

        } catch (Exception e) {
            System.out.println(e.getMessage() + " causa" + e.getCause());
            response.put("code", "500");
            response.put("message", failsU);
            return response;
        }
    }
    }


