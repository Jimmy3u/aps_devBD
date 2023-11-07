package edu.uni.aps.repository;

import org.springframework.data.repository.CrudRepository;

import edu.uni.aps.model.Pizza;

/* 
    Cria um Repositorio que extende CrudRepository, que contem as operações basicas CRUD
    Para uma melhor explicação dos metodos disponiveis consultar o javadoc
    https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
*/ 
public interface PizzaRepo extends CrudRepository<Pizza, Long>{
    
}
