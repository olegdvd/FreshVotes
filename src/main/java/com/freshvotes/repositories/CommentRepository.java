package com.freshvotes.repositories;

import com.freshvotes.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long>{

    List<Comment> findByFeatureId(Long featureId);
}
