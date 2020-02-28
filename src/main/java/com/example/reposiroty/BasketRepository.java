package com.example.reposiroty;

import com.example.entity.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    @Query(value = "SELECT b.* FROM basket b JOIN user u ON b.user_id = u.id WHERE u.id = 1", nativeQuery = true)
    BasketEntity findByUserId(Long userId);

}
