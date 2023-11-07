# APS Desenvolvimetno com banco de dados

Para executar o programa com o banco de dados H2 em memoria basta usar o comando :
~~~
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
~~~
O banco H2 possui um painel online para poder fazer consultas e pode ser utilizado pelo link : http://127.0.0.1:8080/h2-console/ 

#### Configurações do painel online
~~~ 
JBDC URL : jdbc:h2:mem:aps
User name : teste
Senha : <Em branco>
~~~

### Para rodar usando o banco de dados MariaDB basta rodar

~~~
./mvnw spring-boot:run
~~~

### ATENÇÃO
Cheque as configurações de host do banco de dados estão localizados no arquivo `src/main/resources/application.properties`

**OBS: Caso deseje utilizar o banco de dados mysql, basta alterar as linhas :**
~~~
spring.datasource.url = jdbc:mariadb://127.0.0.1:3306/aps?createDatabaseIfNotExist=true
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
~~~
para :
~~~
spring.datasource.url = jdbc:mysql://127.0.0.1:3306/aps&createDatabaseIfNotExist=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
~~~

## Endpoints

### Get(/pizza)
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

### GET(/pizza/{id})
Retorna uma pizza especificada pelo ID, caso não exista retorna uma mensagem de error.
Ex:
~~~Json
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
Deleta uma pizza pelo ID e retorna uma mensagem caso tenha sido deletada com sucesso

### Put(/pizza/{id})
Recebe um JSON formatado e o ID, caso o ID seja valido atualiza os valores de acordo com o JSON e retorna o Objeto atualizado.
Exemplo do JSON recebido pela API:
~~~Json
{
           "sabor" : "<Novo Valor>",
           "descricao" : "<Novo Valor>",
           "valor" : <Novo Valor>
}
~~~

# TODO
- ~~Fazer validação dos dados~~
- Fazer views
- ~~Implementar GET por ID~~
- ~~Implementar PUT para alterar um registro~~
- ~~Implementar banco de dados persistente (mySql / MariaDB)~~
  
# Tecnologias utilizadas
- Spring Web
- Spring Data JPA
- Hibernate
- Lombok
- H2 Database
- MariaDB

# Referencias:
- [Accessing data with MySQL - Spring](https://spring.io/guides/gs/accessing-data-mysql/)
- [Acessing JPA data with JPA - Spring](https://spring.io/guides/gs/accessing-data-rest/)
- [Loading Initial data with Spring Boot](https://www.baeldung.com/spring-boot-data-sql-and-schema-sql)
