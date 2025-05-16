package org.lesson.java.spring_pizzeria.controller;
import org.lesson.java.spring_pizzeria.repo.IngredienteRepository;
import org.lesson.java.spring_pizzeria.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

import org.lesson.java.spring_pizzeria.model.Ingrediente;
import org.lesson.java.spring_pizzeria.model.Pizza;



@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("ingredienti", ingredienteService.findAll());
        return "ingredienti/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingrediente", ingredienteService.getById(id));

        return "ingredienti/show";
    }
    
    
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("ingrediente", new Ingrediente());
        return "ingredienti/create-or-edit";
    }

        
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente , BindingResult bindingResult ,Model model ){
        if(bindingResult.hasErrors()){
            return "ingredienti/create-or-edit";
        }
        ingredienteService.create(formIngrediente);        
        return "redirect:/ingredienti";
    }

   @GetMapping("/edit/{id}")
   public String edit(@PathVariable Integer id, Model model ){
     model.addAttribute(ingredienteService.getById(id));
     model.addAttribute("edit" , true);
     return "ingredienti/create-or-edit";
   }

   @PostMapping("/edit/{id}")
   public String update(@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente, BindingResult bindingResult, Model model){
     
    if(bindingResult.hasErrors()){
            return "ingredienti/create-or-edit";
        }
        ingredienteService.update(formIngrediente); 
              
        return "redirect:/ingredienti";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id ) {
        
        Ingrediente ingredienteToRemove = ingredienteService.getById(id);
        for(Pizza pizzaLinked : ingredienteToRemove.getPizze()){
            pizzaLinked.getIngredienti().remove(ingredienteToRemove);
        }
        ingredienteService.delete(ingredienteToRemove);;
        return "redirect:/ingredienti";
    }
    

    
    
}
