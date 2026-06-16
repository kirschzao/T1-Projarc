package com.example.estoque.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "receitas")
@Getter
@Setter
@NoArgsConstructor
public class ReceitaEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @ManyToMany
    @JoinTable(
        name = "receita_ingrediente",
        joinColumns = @JoinColumn(name = "receita_id"),
        inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<IngredienteEntity> ingredientes;
}
