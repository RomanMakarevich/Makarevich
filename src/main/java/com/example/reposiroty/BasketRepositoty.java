package com.example.reposiroty;

import com.example.entity.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepositoty extends JpaRepository<BasketEntity, Long> {
}
