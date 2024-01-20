# Branas - Curso Clean Code e Clean Architecture - Exercício 01

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

# Descrição do projeto
Utilizando as técnicas de refactoring que vimos na aula, refatore o código do UC1 - Signup, disponível em:
https://github.com/rodrigobranas/cccat15_1/blob/master/src/signup.ts

## Requisitos:
UC1 - Signup
Ator: Passageiro, Motorista
Input: name, email, cpf, carPlate, password, isPassenger, isDriver
Output: account_id

* deve verificar se o email já existe e lançar um erro caso já exista
* deve gerar o account_id (uuid)
* deve validar o nome, email e cpf
* deve apenas salvar a senha, por enquanto em claro

Para testar adequadamente o UC1 será necessário criar o UC2 - GetAccount.

UC2 - GetAccount
Input: account_id
Output: todas as informações da conta

Observações:
Crie uma API REST para interagir com os use cases criados por meio do protocolo HTTP e não se esqueça de também criar testes para a API.
O modelo de dados está disponível em https://github.com/rodrigobranas/cccat15_1/blob/master/create.sql


# Tecnologias utilizadas
1. Java 17
2. Spring Boot 3.2.2
3. Spring Web MVC
4. Spring Data JPA
5. Lombok
6. Postgres 15.1
7. Flyway
8. JUnit

# Setup do Projeto

Para realizar o setup do projeto é necessário possuir o Java 17, docker 24 e docker-compose 1.29 instalado em sua máquina.
Faca o download do projeto (https://github.com/mnishimori/branasCleanCodeCleanArchitectureEx01) e atualize suas dependências com o gradle.
Antes de iniciar o projeto é necessário criar o banco de dados. O banco de dados está programado para ser criado em um container. 
Para criar o container, execute o docker-compose.
Acesse a pasta raiz do projeto, no mesmo local onde encontra-se o arquivo docker-compose.yml. Para executá-lo, execute o comando docker-compose up -d (para rodar detached e não prender o terminal).
Para iniciar o projeto, basta executar o Spring Boot Run no IntelliJ.
