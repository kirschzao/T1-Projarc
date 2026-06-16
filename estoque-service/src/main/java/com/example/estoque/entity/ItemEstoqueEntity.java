package com.example.estoque.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itens_estoque")
@Getter
@Setter
@NoArgsConstructor
public class ItemEstoqueEntity {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private IngredienteEntity ingrediente;

    @Column(nullable = false)
    private int quantidade;
}
