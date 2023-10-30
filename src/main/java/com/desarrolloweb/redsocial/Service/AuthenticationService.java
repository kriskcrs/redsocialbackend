package com.desarrolloweb.redsocial.Service;


import com.desarrolloweb.redsocial.Entity.User;
import com.desarrolloweb.redsocial.Repository.UserRepository;
import com.desarrolloweb.redsocial.Tools.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.HashMap;


@RestController
@CrossOrigin
@RequestMapping("v1")
public class AuthenticationService {

    //vars
    HashMap<String, String> response = new HashMap<>();

    @Autowired
    UserRepository userRepository;


    @GetMapping(path = "/revoke/{session}")
    private ResponseEntity<HashMap<String, String>> logout(@PathVariable String session) {
        response.clear();
        if (session != null) {
            User user = userRepository.findBySession(session);
            if (user != null) {
                user.setSession("");
                userRepository.save(user);
                response.put("message", "Sesion finalizada");
                return ResponseEntity.ok().body(response);
            } else {
                System.out.println("sesion no existe");
                return ResponseEntity.noContent().build();
            }
        }
        response.put("mesagge", "sesion no valida");
        return ResponseEntity.badRequest().body(response);

    }


    @PostMapping(path = "/login")
    private ResponseEntity<HashMap<String, String>> login(@RequestBody User user) {
        response.clear();
        if (user.getIdUser() != null && user.getPassword() != null) {

            User userData = userRepository.findByIdUserAndPassword(user.getIdUser(), new Encoding().MD5(user.getPassword()) );
            if (userData != null) {

                System.out.println(userData.getSession());
                if(userData.getSession() ==null || userData.getSession().equals("")){
                    String session = String.valueOf(new Encoding().SessionManager());
                    userData.setSession(session);
                    userData.setFechaIngreso(new Date());
                    userRepository.save(userData);
                    response.put("token", session);
                    System.out.println(response);
                    return ResponseEntity.ok(response);
                }else{

                    response.put("message", "Usuario cuenta con una sesion activa");
                    return ResponseEntity.badRequest().body(response);
                }

            } else {
                System.out.println("Usuario no existe");
                return ResponseEntity.noContent().build();
            }
        } else {
            response.put("Error", "Parametros incorrectos");
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping(path = "/dataUser/{session}")
    private ResponseEntity<User> dataUser(@PathVariable String session) {
        User user = userRepository.findBySession(session);
        if (user != null) {
            user.setPassword("");
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}