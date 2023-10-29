package Service;


import Entity.*;
import Repository.*;
import Tools.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
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
    @Autowired
    CompanyRepository companyRepository;


    @PostMapping(path = "/login/{ip}")
    private HashMap<String, String> login(@RequestBody User user, @PathVariable String ip) {
        //encoding password
        user.setPassword(new Encoding().crypt(user.getPassword()));
        //Validate user exist
        User userExist = userRepository.findByIdUser(user.getIdUser());
        if (userExist != null) {
            //validate user and password
            User userLogin = userRepository.findByIdUserAndPassword(user.getIdUser(), user.getPassword());
            if (userLogin != null) {
                if (userLogin.getIdStatusUser() != 1) {
                    response.put("code", "1");
                    response.put("message", StatusUser);
                    return response;
                } else {
                    //Valide new user -> first login
                    if (userLogin.getLastDateOfEntry() == null || userLogin.getLastDateOfEntry().equals("")) {
                        response.put("code", "2");
                        userLogin.setCurrentSession(String.valueOf(new Encoding().SessionManager()));
                        userRepository.save(userLogin);
                        response.put("session", userLogin.getCurrentSession());
                        response.put("user", userLogin.getIdUser());
                        response.put("message", FirstLogin);
                        return response;
                    } else {
                        //validate require change password
                        if (userLogin.getRequiresChangingPassword() != 0) {
                            response.put("code", "3");
                            response.put("message", RequiredChangePassword);
                            response.put("user", userLogin.getIdUser());
                            //creando session
                            userLogin.setCurrentSession(String.valueOf(new Encoding().SessionManager()));
                            userRepository.save(userLogin);
                            return response;
                        } else {
                            //validate session
                            if (userLogin.getCurrentSession() == null || userLogin.getCurrentSession().equals("")) {
                                //Login OK
                                //SesionID, Access Attemps = 0
                                userLogin.setCurrentSession(String.valueOf(new Encoding().SessionManager()));
                                userLogin.setAccessAttempts(0);
                                userLogin.setLastDateOfEntry(new Date());
                                //response
                                response.put("code", "0");
                                response.put("message", "ok");
                                response.put("nameUser", userLogin.getName() + " " + userLogin.getLastName());
                                response.put("session", userLogin.getCurrentSession());
                                response.put("user", userLogin.getIdUser());
                                //validate user inactive
                                userRepository.save(userLogin);
                                return response;
                            } else {
                                response.put("code", "1");
                                response.put("message", CurrentSession);
                                return response;
                            }
                        }
                    }
                }
            } else {
                FailedLogin(userExist);
                String message = loginBloqueado(user);
                if (message.equals("")) {
                    response.put("code", "1");
                    response.put("message", FailedLogin);
                    return response;
                } else {
                    response.put("code", "1");
                    response.put("message", message);
                    return response;
                }
            }
        } else {
            response.put("code", "1");
            response.put("message", FailedLogin);
            return response;
        }

    }


    private void FailedLogin(User user) {
        //check rule
        Company company = companyRepository.findByIdCompany(1);
        user.setAccessAttempts(user.getAccessAttempts() + 1);
        if (user.getAccessAttempts() >= company.getPasswordAmountAttemptsBeforeBlocking()) {
            user.setIdStatusUser(Long.valueOf(2));
        }
        userRepository.save(user);
    }


    private String loginBloqueado(User user) {

        User userFind = userRepository.findByIdUser(user.getIdUser());
        int intentos = userFind.getAccessAttempts();
        Company company = companyRepository.findByIdCompany(1L);

        if (intentos >= company.getPasswordAmountAttemptsBeforeBlocking()) {
            return StatusUser;
        }

        return "";
    }


    @GetMapping(path = "/revoke/{session}")
    private HashMap<String, String> logout(@PathVariable String session) {
        if (session != null) {
            User user = userRepository.findByCurrentSession(session);
            if (user != null) {
                user.setCurrentSession("");
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

}