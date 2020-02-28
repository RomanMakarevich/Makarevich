package com.example.reposiroty;

import com.example.entity.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    @Query("SELECT b FROM BasketEntity b JOIN b.userEntity u WHERE u.id = :user_id")
    Optional<BasketEntity> findByUserId(@Param("user_id") Long user_id);
}
