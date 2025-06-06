package org.lesson.java.spring_pizzeria.controller;

import java.util.List;

import java.util.Optional;
import org.lesson.java.spring_pizzeria.model.Pizza;
import org.lesson.java.spring_pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/advanced/pizzas")
public class PizzaRestAdvancedController {
    @Autowired
    private PizzaService service;

    @GetMapping
    public List<Pizza> index(){
        List<Pizza> pizzas = service.findAll();
        return pizzas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id){
        Optional<Pizza> pizzaAttempt = service.findById(id);

        if(pizzaAttempt.isEmpty()){
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pizza>(pizzaAttempt.get() , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pizza> store(@Valid @RequestBody Pizza pizza){

         return new ResponseEntity<Pizza>(service.create(pizza) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@Valid @RequestBody Pizza pizza, @PathVariable Integer id){
        pizza.setId(id);
        if(service.findById(id).isEmpty()){
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Pizza>(service.update(pizza) ,  HttpStatus.OK)    ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id ){
        if(service.findById(id).isEmpty()){
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }
        service.deleteById(id) ;
         return new ResponseEntity<Pizza>(  HttpStatus.OK) ;
    }
}