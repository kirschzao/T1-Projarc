package com.example.pizzaria.Dominio.Dados;

import java.util.List;

import com.example.pizzaria.Dominio.Entidades.Ingrediente;

public interface IngredientesRepository {
    List<Ingrediente> recuperaTodos();
    List<Ingrediente> recuperaIngredientesReceita(long id);
}
