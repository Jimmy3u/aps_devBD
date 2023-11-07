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

/*
 * Controlador REST que cria nossos endpoints para interagirmos com nosso repositorio
 * e o banco de dados.
 */

@RestController
public class PizzaController {

    /*
     * Cria um objeto do Repositorio, para ser utilizado em nossas chamadas
     */
    private final PizzaRepo pizzaRepo;

    public PizzaController(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    /*
     * Cria um endpoint GET, que retorna uma lista com todas as pizzas do Banco de
     * dados
     */
    @GetMapping("/pizza")
    public List<Pizza> getAll() {
        return (List<Pizza>) pizzaRepo.findAll();
    }

    /*
     * Cria um endpoint GET, que retorna uma pizza por seu ID ou um erro caso não
     * exista, usa o wildcard <?> para caso não exista um registro com tal id
     */
    @GetMapping("/pizza/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        /*
         * Cria uma variavel temporaria para armazenar o resultado do metodo findById do
         * repositorio e depois usa o metodo isPresent() para checar se o ID existe
         * e retorna o objeto, caso não exista retorna uma mensagem de error
         */
        var p = pizzaRepo.findById(id);

        if (p.isPresent()) {
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.status(404).body("ID não existe");
        }

    }

    /*
     * Cria um endpoint POST, que recebe(Consome) um objeto JSON com os dados da
     * pizza,
     * salva ela no banco de dados e retorna o Objeto salvo.
     */
    @PostMapping(path = "/pizza", consumes = "application/json")
    public ResponseEntity<Pizza> insertNewEntry(@RequestBody Pizza p) {
        pizzaRepo.save(p);
        return ResponseEntity.ok(p);
    }

    /*
     * Cria um endpoint DELETE, que recebe o ID verifica a existencia no banco de dados
     * caso contrario retorna um erro.
     */
    @DeleteMapping("/pizza/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {

        if (pizzaRepo.existsById(id)) {
            pizzaRepo.deleteById(id);
            return ResponseEntity.status(200)
                    .body("Pizza com ID: " + id + " deletada com sucesso");
        } else {
            return ResponseEntity.status(404)
                    .body("ID não encontrado");
        }
    }

    /*
     * Cria um endpoint PUT que recebe o ID e um Json com as informações a serem atualizadas
     * e retorna o objeto atualizado, caso o ID seja invalido retorna uma exceção
     * Sendo a formatação do JSON a seguinte :
     * {
     *      "sabor" : "<Novo Valor>",
     *      "descricao" : "<Novo Valor>",
     *      "valor" : <Novo Valor>
     * }
     */
    @PutMapping("/pizza/{id}")
    public ResponseEntity<Pizza> updateEntry(@PathVariable long id, @RequestBody Pizza p)
            throws NotFoundException {

        Pizza pizzaAtt = pizzaRepo.findById(id).orElseThrow(() -> new NotFoundException());

        if (p.sabor != null) {
            pizzaAtt.setSabor(p.sabor);
        }
        if (p.descricao != null) {
            pizzaAtt.setDescricao(p.descricao);
        }
        if (p.valor != 0) {
            pizzaAtt.setValor(p.valor);
        }

        pizzaRepo.save(pizzaAtt);
        return ResponseEntity.ok(pizzaAtt);
    }
}
