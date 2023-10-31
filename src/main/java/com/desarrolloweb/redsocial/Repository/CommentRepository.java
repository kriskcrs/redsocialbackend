package com.desarrolloweb.redsocial.Repository;
import com.desarrolloweb.redsocial.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CommentRepository extends JpaRepository<Comment,Integer>{
}
