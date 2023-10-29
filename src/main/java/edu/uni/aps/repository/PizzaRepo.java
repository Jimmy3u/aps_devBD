package edu.uni.aps.repository;

import org.springframework.data.repository.CrudRepository;

import edu.uni.aps.model.Pizza;

public interface PizzaRepo extends CrudRepository<Pizza, Long>{
    
}
