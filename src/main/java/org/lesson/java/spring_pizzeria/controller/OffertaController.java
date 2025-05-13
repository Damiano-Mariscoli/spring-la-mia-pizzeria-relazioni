package org.lesson.java.spring_pizzeria.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.lesson.java.spring_pizzeria.model.Offerta;
import org.springframework.web.bind.annotation.PostMapping;
import org.lesson.java.spring_pizzeria.repo.OffertaRepository;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RequestMapping("/offerte")
public class OffertaController {

    @Autowired
    private OffertaRepository repository;


    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()){
            return "offerte/create";
        }
        repository.save(formOfferta);

        return "redirect:/pizzas/" + formOfferta.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute(repository.findById(id).get());
        return "offerte/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "offerte/edit";
        }
        repository.save(formOfferta);
         return "redirect:/pizzas/" + formOfferta.getPizza().getId(); 
    }


}

