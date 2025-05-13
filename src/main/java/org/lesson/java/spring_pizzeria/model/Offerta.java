package org.lesson.java.spring_pizzeria.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "offerte")
public class Offerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //libro da cui dipendo
    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @NotNull(message = "La data dell'offerta non puo essere null")
    @PastOrPresent(message = "la data non puo essere nel futuro")
    private LocalDate dataOfferta;

    @PastOrPresent(message = "la data non puo essere nel futuro")
    private LocalDate fineOfferta;

     @NotBlank(message = "Il titolo non può essere vuoto")
    private String titolo;


    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Pizza return the pizza
     */
    public Pizza getPizza() {
        return pizza;
    }

    /**
     * @param pizza the pizza to set
     */
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    /**
     * @return LocalDate return the dataOfferta
     */
    public LocalDate getDataOfferta() {
        return dataOfferta;
    }

    /**
     * @param dataOfferta the dataOfferta to set
     */
    public void setDataOfferta(LocalDate dataOfferta) {
        this.dataOfferta = dataOfferta;
    }

    /**
     * @return LocalDate return the fineOfferta
     */
    public LocalDate getFineOfferta() {
        return fineOfferta;
    }

    /**
     * @param fineOfferta the fineOfferta to set
     */
    public void setFineOfferta(LocalDate fineOfferta) {
        this.fineOfferta = fineOfferta;
    }

    /**
     * @return String return the titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @param titolo the nome to set
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

}
