package com.desarrolloweb.redsocial.Repository;

import com.desarrolloweb.redsocial.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Long> {
    public User findByIdUser(String iduser);

}