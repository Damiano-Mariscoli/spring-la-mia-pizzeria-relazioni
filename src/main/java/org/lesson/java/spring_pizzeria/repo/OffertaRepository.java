package org.lesson.java.spring_pizzeria.repo;
import org.lesson.java.spring_pizzeria.model.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffertaRepository extends JpaRepository<Offerta, Integer> {
    
}
