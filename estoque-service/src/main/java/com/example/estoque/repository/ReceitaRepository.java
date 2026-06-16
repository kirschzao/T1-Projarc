package com.example.estoque.repository;

import com.example.estoque.entity.ReceitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<ReceitaEntity, Long> {
}
