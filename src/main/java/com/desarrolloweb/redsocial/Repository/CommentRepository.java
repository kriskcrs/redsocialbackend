package com.desarrolloweb.redsocial.Repository;
import com.desarrolloweb.redsocial.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer>{

    List<Comment> findCommentByIdPublication( int idpublication);

    Comment findCommentByIdComment(int idcomment);
}
