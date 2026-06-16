package com.example.estoque.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredientes")
@Getter
@Setter
@NoArgsConstructor
public class IngredienteEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String descricao;
}
