# APS Desenvolvimetno com banco de dados

Para executar o programa basta usar o comando :
~~~
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
~~~

Para usar o banco de dados basta entrar em : http://127.0.0.1:8080/h2-console/ e utilizar :

~~~
JBDC URL : jdbc:h2:mem:aps
User name : teste
Senha : <Em branco>
~~~

## Endpoints

### Get(/pizzas)
Retorna um JSON com todas cadastras no banco, exemplo : 
~~~Json
    {
        "id":1,
        "sabor":"Segredo",
        "descricao":"Misterio",
        "valor":35.2
    },
    {
        "id":2,
        "sabor":"Margeritta",
        "descricao":"Molho de tomate, mussarela, manjericão e parmesão. Um sabor clássico e muito querido.",
        "valor":35.2
    },
    {
        "id":3,
        "sabor":"Mussarela",
        "descricao":"Molho de tomate, queijo tipo mussarela, azeitonas pretas e orégano e massa com fermentação natural",
        "valor":30.0
    }
~~~

### Post(/pizza)
Recebe um JSON formatado e insere no banco de dados.<br>
Exemplo de JSON:
~~~Json
{
    "sabor" : "Exemplo",
    "descricao" : "Misterio",
    "valor" : "35.2"
}
~~~

### Delete(/pizza/{id})
Deleta uma pizza pelo ID é isso


# TODO
- Fazer validação dos dados
- Fazer views
- Implementar GET por ID
- Implementar PUT para alterar um registro
- Implementar banco de dados persistente (mySql / MariaDB)
  
# Tecnologias utilizadas
- Spring Webg
- Spring Data JPA
- Lombok
- H2 Database
