package com.desarrolloweb.redsocial.Service;


import com.desarrolloweb.redsocial.Entity.*;
import com.desarrolloweb.redsocial.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("v1")
@CrossOrigin
public class profile {

    @Autowired
    UserRepository userRepository;


}
