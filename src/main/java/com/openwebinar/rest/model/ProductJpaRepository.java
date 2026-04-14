package com.openwebinar.rest.model;

import org.springdoc.core.converters.models.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
    @Query("""
            select p
            from Product p
            where p.price <= ?1
            """)
    List<Product> query(double maxPrice, Sort sortDirection);
}
