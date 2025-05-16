package org.lesson.java.spring_pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.lesson.java.spring_pizzeria.model.Offerta;

import org.lesson.java.spring_pizzeria.repo.OffertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OffertaService {

@Autowired
private OffertaRepository offertaRepository;

public List <Offerta> findAll(){
    return offertaRepository.findAll();
}

public List <Offerta> findByDataOfferta(){
    return offertaRepository.findAll(Sort.by("DataOfferta"));
}

 public Offerta create(Offerta offerta){
    return offertaRepository.save(offerta);
 }

  public Offerta update(Offerta offerta){
    return offertaRepository.save(offerta);
 }

 public Offerta getById( Integer id){
     Optional<Offerta> offertaAttempt = offertaRepository.findById(id);
        
        if(offertaAttempt.isEmpty()){
            //lanciamo una not found    
        }
        return offertaAttempt.get();
    }
 public void deleteById(Integer id){
    offertaRepository.deleteById(id);
 }
  public void delete(Offerta offerta){
    offertaRepository.delete(offerta);
    
 }
}
