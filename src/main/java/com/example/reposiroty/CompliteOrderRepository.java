package com.example.reposiroty;

import com.example.entity.CompliteOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompliteOrderRepository extends JpaRepository<CompliteOrderEntity, Long> {
}
