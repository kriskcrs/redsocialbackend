package com.desarrolloweb.redsocial.Service;
import com.desarrolloweb.redsocial.Entity.ChangePassword;
import com.desarrolloweb.redsocial.Entity.User;
import com.desarrolloweb.redsocial.Repository.*;
import com.desarrolloweb.redsocial.Tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;



@RestController
@RequestMapping("v1")
@CrossOrigin
public class UserService {

    //vars
    HashMap<String, String> response = new HashMap<>();

    //parametros para generacion de contraseña por email
    int MaxCharterPassword = 25;
    int uppercaseCount = 2;
    int lengtPasswordTemp = 8;
    int lowercaseCount = 3;
    int digitCount = 3;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/md5/{text}")
    private String Md5(@PathVariable String text) {
        return new Encoding().MD5(text);
    }

    @PostMapping(path = "/createUser")
    private ResponseEntity<HashMap<String, String>> createUser(@RequestBody User user) {
        User userReadi = userRepository.findByIdUser(user.getIdUser());
        if(userReadi != null){
            response.put("message", "Usuario ya existe");
            return ResponseEntity.badRequest().body(response);
        }else if(user != null) {
            user.setPassword(new Encoding().MD5(user.getPassword()));
            user.setRequiredChange("1");
            userRepository.save(user);
            response.put("message", "Usuario creado");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Llenar todos los campos");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping(path = "/users")
    private ResponseEntity<List<User>> userList() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping(path = "/recover")
    private ResponseEntity<HashMap<String, String>> recover(@RequestBody User user){
        response.clear();
        try{
            User userRecover = userRepository.findByIdUser(user.getIdUser());
            if(userRecover != null){
                //generate password
                String generatedPassword = new PasswordGenerator().generatePassword(lengtPasswordTemp, uppercaseCount, lowercaseCount, digitCount);
                SendPassword.sendPasswordByEmail(userRecover.getIdUser(), generatedPassword);
                userRecover.setPassword(new Encoding().MD5(generatedPassword));
                userRecover.setRequiredChange("1");
                userRepository.save(userRecover);
                response.put("message", "Contraseña enviada exitosamente");
                return ResponseEntity.ok(response);
            }else{
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            System.err.println(e.getCause() + e.getMessage());
            response.put("Error", "Contraseña no pudo ser envíada");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping(path = "/setPassword")
    private ResponseEntity<HashMap<String, String>> setPassword(@RequestBody ChangePassword changePassword){
        response.clear();
        changePassword.setPassword(new Encoding().MD5(changePassword.getPassword()));
        User user = userRepository.findByIdUserAndPassword(changePassword.getIdUser(), changePassword.getPassword());
        if(user != null){
            if(changePassword.getPasswordNew().equals(changePassword.getPasswordConfirm())){
                user.setPassword(new Encoding().MD5(changePassword.getPasswordNew()));
                user.setRequiredChange("0");
                userRepository.save(user);
                response.put("message", "Contraseña cambiada exitosamente");
                return ResponseEntity.ok(response);
            }else{
                response.put("Error", "Contraseñas no coinciden");
                return ResponseEntity.badRequest().body(response);
            }
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
