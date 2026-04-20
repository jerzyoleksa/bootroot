package org.example.module3.repository;

import org.example.module3.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getProductsByType(@Param("type") String type);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:search%")
    List<Product> findByNameContainingIgnoreCase(@Param("search") String search);


}
