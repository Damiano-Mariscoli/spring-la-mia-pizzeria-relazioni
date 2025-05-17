package org.lesson.java.spring_pizzeria.controller;

import java.util.List;
import org.lesson.java.spring_pizzeria.model.Pizza;
import org.lesson.java.spring_pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {
    @Autowired
    private PizzaService service;

    @GetMapping
    public List<Pizza> index(){
        List<Pizza> pizzas = service.findAll();
        return pizzas;
    }

    @GetMapping("/{id}")
    public Pizza show(@PathVariable Integer id){
        Pizza pizza = service.getById(id);
        return pizza;
    }

    @PostMapping
    public Pizza store(@RequestBody Pizza pizza){
        return service.create(pizza);
    }

    @PutMapping("/{id}")
    public Pizza update(@RequestBody Pizza pizza, @PathVariable Integer id){
        pizza.setId(id);
        return service.update(pizza);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id ){
         service.deleteById(id);
    }
}
