package org.lesson.java.spring_pizzeria.service;

import java.util.List;

import org.lesson.java.spring_pizzeria.model.Ingrediente;
import org.lesson.java.spring_pizzeria.model.Offerta;
import org.lesson.java.spring_pizzeria.repo.IngredienteRepository;
import org.lesson.java.spring_pizzeria.repo.OffertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {



    
    @Autowired
    private IngredienteRepository ingredienteRepository;




    public List <Ingrediente> findAll(){
        return ingredienteRepository.findAll();
    }

    public Ingrediente getById(Integer id){
        return ingredienteRepository.findById(id).get(); 
    }

    public Ingrediente create(Ingrediente ingrediente){
        return ingredienteRepository.save(ingrediente);
    }
    public Ingrediente update(Ingrediente ingrediente){
        return ingredienteRepository.save(ingrediente);
    }

    public List<Ingrediente> findByNome(){
        return ingredienteRepository.findAll(Sort.by("nome"));

    }

     public void deleteById(Integer id){
    ingredienteRepository.deleteById(id);
 }
  public void delete(Ingrediente ingrediente){
    ingredienteRepository.delete(ingrediente);
    
 }
}
