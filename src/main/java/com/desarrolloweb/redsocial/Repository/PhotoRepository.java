package com.desarrolloweb.redsocial.Repository;
import com.desarrolloweb.redsocial.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PhotoRepository extends JpaRepository<Photo,String> {

    Photo findByIdPhoto(String idPhoto);
}
