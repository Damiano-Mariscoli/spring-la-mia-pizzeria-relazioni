package org.lesson.java.spring_pizzeria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.lesson.java.spring_pizzeria.repo.OffertaRepository;
import org.lesson.java.spring_pizzeria.repo.PizzaRepository;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;
import org.lesson.java.spring_pizzeria.model.Offerta;

import org.lesson.java.spring_pizzeria.model.Pizza;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OffertaRepository offertaRepository;



    public List <Pizza> findAll(){
        return pizzaRepository.findAll();
    }
    public List <Pizza> findAllSortedByName(){
        return pizzaRepository.findAll(Sort.by("name"));
    }

    public Pizza getById(Integer id){
        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);
        
        if(pizzaAttempt.isEmpty()){
            //lanciamo una not found    
        }
        return pizzaAttempt.get();
    }

    public List <Pizza> findByNome(String nome){
        return pizzaRepository.findByNomeContaining(nome);
    }

    public Pizza create(Pizza pizza){
        //aggiornamento di alcuni campi solamente in seguito alla creazione
        return pizzaRepository.save(pizza);
    }


        public Pizza update(Pizza pizza){
            //aggiornamento di altri campi
        return pizzaRepository.save(pizza);
    }

    public void deleteById(Integer id){
        Pizza pizza = getById(id);  
        for(Offerta offertaToDelete : pizza.getOfferte()){
        offertaRepository.delete(offertaToDelete);

        }
        pizzaRepository.delete(pizza);
        
    }


    public void delete(Pizza pizza){
        for(Offerta offertaToDelete : pizza.getOfferte()){
        offertaRepository.delete(offertaToDelete);

        }
        pizzaRepository.delete(pizza);
    }


    public Boolean existById(Integer id){
        return pizzaRepository.existsById(id);
    }
    public Boolean exist(Pizza pizza){
        return existById(pizza.getId());
    }
}
