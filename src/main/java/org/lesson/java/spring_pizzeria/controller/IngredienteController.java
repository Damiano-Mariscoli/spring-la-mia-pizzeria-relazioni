package org.lesson.java.spring_pizzeria.controller;
import org.lesson.java.spring_pizzeria.repo.IngredienteRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {
    @Autowired
    private IngredienteRepository IngredienteRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("ingredienti", IngredienteRepository.findAll());
        return "ingredienti/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingrediente", IngredienteRepository.findById(id).get());

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
        IngredienteRepository.save(formIngrediente);        
        return "redirect:/ingredienti";
    }

   @GetMapping("/edit/{id}")
   public String edit(@PathVariable Integer id, Model model ){
     model.addAttribute(IngredienteRepository.findById(id).get());
     model.addAttribute("edit" , true);
     return "ingredienti/create-or-edit";
   }

   @PostMapping("/edit/{id}")
   public String update(@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente, BindingResult bindingResult, Model model){
     
    if(bindingResult.hasErrors()){
            return "ingredienti/create-or-edit";
        }
        IngredienteRepository.save(formIngrediente); 
              
        return "redirect:/ingredienti";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id ) {
        
        Ingrediente ingredienteToRemove = IngredienteRepository.findById(id).get();
        for(Pizza pizzaLinked : ingredienteToRemove.getPizze()){
            pizzaLinked.getIngredienti().remove(ingredienteToRemove);
        }
        IngredienteRepository.delete(ingredienteToRemove);
        return "redirect:/ingredienti";
    }
    

    
    
}
