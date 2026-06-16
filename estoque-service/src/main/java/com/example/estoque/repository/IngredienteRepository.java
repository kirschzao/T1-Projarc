package com.example.estoque.repository;

import com.example.estoque.entity.IngredienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<IngredienteEntity, Long> {
}
