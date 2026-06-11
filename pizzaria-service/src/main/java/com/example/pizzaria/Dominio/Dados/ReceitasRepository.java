package com.example.pizzaria.Dominio.Dados;

import com.example.pizzaria.Dominio.Entidades.Receita;

public interface ReceitasRepository {
    Receita recuperaReceita(long id);
    
}
