package com.desarrolloweb.redsocial.Repository;
import com.desarrolloweb.redsocial.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    public User findByIdUser(String iduser);
    public User findBySession(String session);
    public User findByIdUserAndPassword(String iduser, String password);
}