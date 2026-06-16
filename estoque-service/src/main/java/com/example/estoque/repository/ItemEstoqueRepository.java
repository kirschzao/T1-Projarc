package com.example.estoque.repository;

import com.example.estoque.entity.ItemEstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoqueEntity, Long> {
    Optional<ItemEstoqueEntity> findByIngredienteId(Long ingredienteId);
}
