package org.lesson.java.spring_pizzeria.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.lesson.java.spring_pizzeria.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {


    
}
