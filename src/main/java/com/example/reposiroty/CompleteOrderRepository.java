package com.example.reposiroty;

import com.example.entity.CompleteOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompleteOrderRepository extends JpaRepository<CompleteOrderEntity, Long> {
}
