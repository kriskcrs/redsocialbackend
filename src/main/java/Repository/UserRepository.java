package Repository;
import Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByIdUser(String iduser);
    public User findByCurrentSession(String session);
    public User findByIdUserAndPassword(String iduser, String password);
}