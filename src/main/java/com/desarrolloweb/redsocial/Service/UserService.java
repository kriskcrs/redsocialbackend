package com.desarrolloweb.redsocial.Service;
import com.desarrolloweb.redsocial.Repository.*;
import com.desarrolloweb.redsocial.Tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("v1")
@CrossOrigin
public class UserService {
    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/md5/{text}")
    private String Md5(@PathVariable String text) {
        return Encoding.MD5(text);
    }



}
