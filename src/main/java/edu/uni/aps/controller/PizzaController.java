package edu.uni.aps.controller;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uni.aps.model.Pizza;
import edu.uni.aps.repository.PizzaRepo;

@RestController
public class PizzaController {

    // Cria um objeto do repositorio para realizar as operações CRUD
    private final PizzaRepo pizzaRepo;

    public PizzaController(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    // Le todos os registros do banco de dados.
    @GetMapping("/pizzas")
    public List<Pizza> getAll() {
        return (List<Pizza>) pizzaRepo.findAll();
    }

    /**
     * Recebe um JSON com sabor, descricao e valor e insere no banco de dados
     * {
     * "sabor" : "",
     * "descricao" : "",
     * "valor" : ""
     * }
     * 
     */
    @PostMapping(path = "/pizza", consumes = "application/json")
    public void insertNewEntry(@RequestBody Pizza p) {
        pizzaRepo.save(p);
    }

    /*
     * Deleta uma pizza pelo seu ID, caso o ID não existe joga uma exceção
     */
    @DeleteMapping("/pizza/{id}")
    public void deleteEntry(@PathVariable Long id) throws NotFoundException {
        if (pizzaRepo.existsById(id)) {
            pizzaRepo.deleteById(id);
            System.out.println("Deletado com sucesso");
        } else {
            throw new NotFoundException();
        }
    }

    @PutMapping("/pizza/up/{id}")
    public ResponseEntity<Pizza> updateEntry(@PathVariable long id, @RequestBody Pizza pizzaPayload) throws NotFoundException{
        
        Pizza pizzaAtualizada = pizzaRepo.findById(id).orElseThrow(() -> new NotFoundException());

        if(pizzaPayload.sabor != null) { pizzaAtualizada.setSabor(pizzaPayload.sabor); }
        if(pizzaPayload.descricao != null) { pizzaAtualizada.setDescricao(pizzaPayload.descricao); }
        if(pizzaPayload.valor != 0) { pizzaAtualizada.setValor(pizzaPayload.valor); }

        pizzaRepo.save(pizzaAtualizada);
        return ResponseEntity.ok(pizzaAtualizada);
    }
}
