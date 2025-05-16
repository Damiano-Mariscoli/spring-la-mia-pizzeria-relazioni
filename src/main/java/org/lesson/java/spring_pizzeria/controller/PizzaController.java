package org.lesson.java.spring_pizzeria.controller;
import org.lesson.java.spring_pizzeria.repo.IngredienteRepository;
import org.lesson.java.spring_pizzeria.repo.OffertaRepository;
import org.lesson.java.spring_pizzeria.repo.PizzaRepository;
import org.lesson.java.spring_pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.lesson.java.spring_pizzeria.model.Offerta;
import org.lesson.java.spring_pizzeria.model.Pizza;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.lesson.java.spring_pizzeria.controller.OffertaController;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    private final IngredienteRepository ingredienteRepository;
     @Autowired
     private PizzaService pizzaService;
     @Autowired
     private OffertaRepository offertaRepository;

    PizzaController(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

   @GetMapping
   public String index(Model model){
    List<Pizza> pizzas = pizzaService.findAll();
    model.addAttribute("pizzas", pizzas);
    return "pizzas/index";
   }

   @GetMapping("/{id}")
   public String show(@PathVariable Integer id, Model model){
    Pizza pizza = pizzaService.getById(id);
    model.addAttribute("pizza", pizza);
    model.addAttribute("ingredienti", ingredienteRepository.findAll());
    return "pizzas/show";
   }

   @GetMapping("/searchByNome")
   public String searchByName(@RequestParam() String nome, Model model){
    List <Pizza> pizzas = pizzaService.findByNome(nome);
    model.addAttribute("pizzas", pizzas);
    return "pizzas/index";

   }
   
   @GetMapping("/create")
   public String create(Model model){
     model.addAttribute("pizza", new Pizza());
     model.addAttribute("ingredienti", ingredienteRepository.findAll());
     return "pizzas/create";
   }

   @PostMapping("/create")
   public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model){
    model.addAttribute("ingredienti", ingredienteRepository.findAll());
     
    if (bindingResult.hasErrors()){
      return "pizzas/create";
    }
    pizzaService.create(formPizza);
     return "redirect:/pizzas";
   }

   @GetMapping("/edit/{id}")
   public String edit(@PathVariable Integer id, Model model ){
     model.addAttribute(pizzaService.getById(id));
     model.addAttribute("ingredienti", ingredienteRepository.findAll());
     return "pizzas/edit";
   }

   @PostMapping("/edit/{id}")
   public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model){
    model.addAttribute("ingredienti", ingredienteRepository.findAll());
     
    if (bindingResult.hasErrors()){
      return "pizzas/edit";
    }
    pizzaService.update(formPizza);
     return "redirect:/pizzas";
   }

   @PostMapping("/delete/{id}")
   public String delete(@PathVariable Integer id){

    Pizza pizza = pizzaService.getById(id);

    for (Offerta offertaToDelete : pizza.getOfferte()){
      offertaRepository.delete(offertaToDelete);
    }
  


    pizzaService.delete(pizza);

    return "redirect:/pizzas";
   }


   @GetMapping("/{id}/offerta")
   public String offerta(@PathVariable Integer id, Model model){
    Offerta offerta = new Offerta();
    offerta.setPizza(pizzaService.getById(id));
    model.addAttribute("offerta", offerta);
    return "offerte/create";
    }
}
