package com.desarrolloweb.redsocial.Repository;
import com.desarrolloweb.redsocial.Entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PublicationRepository extends JpaRepository<Publication,Integer>{
    public Publication findByUserIdUser(String idUser);

    public Publication findByIdPublication(Integer idUser);
}
