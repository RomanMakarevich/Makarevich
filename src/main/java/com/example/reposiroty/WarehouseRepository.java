package com.example.reposiroty;

import com.example.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
    @Query(value = "SELECT w.* FROM warehouse w JOIN product p ON w.prodict_id = p.id WHERE u.id = :productId", nativeQuery = true)
    WarehouseEntity findByProcuctId(@Param("productId") Long productId);
}
