package com.desarrolloweb.redsocial.Service;


import com.desarrolloweb.redsocial.Entity.User;
import com.desarrolloweb.redsocial.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;


@RestController
@CrossOrigin
@RequestMapping("v1")
public class AuthenticationService {

    //messages
    String FailedLogin = "Usuario o contraseña incorrecta";
    String RequiredChangePassword = "Requiere cambio de contraseña";
    String CurrentSession = "Usuario ya cuenta con una sesión activa";
    String StatusUser = "El usuario no se encuentra activo";
    String FirstLogin = "Primer login";
    HashMap<String, String> response = new HashMap<>();

    @Autowired
    UserRepository userRepository;




    @GetMapping(path = "/revoke/{session}")
    private HashMap<String, String> logout(@PathVariable String session) {
        if (session != null) {
            User user = userRepository.findBySession(session);
            if (user != null) {
                user.setSession("");
                userRepository.save(user);
                response.put("code", "0");
                response.put("message", "Sesion finalizada");
                return response;
            } else {
                System.out.println("no esta");
                response.put("code", "1");
                response.put("message", "¡Sesion no valida!");
                return response;
            }
        }
        response.put("code", "1");
        response.put("message", "session no valida");
        return response;

    }


    @PostMapping(path = "/login")
    private ResponseEntity<String> login(@RequestBody User user) {

        if(user.getIdUser() !=null && user.getPassword() != null){
            System.out.println(user.getIdUser());
            if (user.getIdUser().equals("admin") && user.getPassword().equals("admin")) {
                return ResponseEntity.ok("exitoso");
            } else {
                return ResponseEntity.noContent().build();
            }
        }else{
            return ResponseEntity.badRequest().body("Valores incorrectos");
        }



    }




}