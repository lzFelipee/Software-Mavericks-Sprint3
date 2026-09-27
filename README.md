# Software Mavericks

Projeto desenvolvido para a disciplina **Arquitetura Orientada a Serviços e Web Services (SOA)**.

## Objetivo do Projeto

O projeto tem como objetivo desenvolver uma API RESTful utilizando Spring Boot para gerenciamento de clientes, veículos e serviços automotivos.

A aplicação permite cadastrar clientes, associar veículos aos clientes e registrar serviços realizados nos veículos, seguindo conceitos de Arquitetura Orientada a Serviços (SOA), organização em camadas e boas práticas de desenvolvimento.

Nesta Sprint 3, foram implementados recursos de autenticação, autorização, JWT, tratamento padronizado de erros, testes automatizados e documentação da API.

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Maven
- H2 Database
- Flyway
- Swagger OpenAPI
- JWT
- JUnit
- MockMvc
- Git/GitHub

---

## Arquitetura do Projeto

O projeto utiliza arquitetura em camadas, com separação entre autenticação, segurança, controle das requisições, regras de negócio e persistência dos dados.

```text
Swagger / API Client
        ↓
AuthController
        ↓
AuthService
        ↓
Spring Security
        ↓
JwtService
        ↓
JWT Token
        ↓
JwtAuthenticationFilter
        ↓
SecurityConfig
        ↓
Controller
        ↓
Service
        ↓
Repository
        ↓
Banco de Dados (H2)

Flyway → Controle de Migrações
```

### Diagrama da Arquitetura

O diagrama detalhado da solução está disponível no arquivo:

`docs/arquitetura.md`

Estrutura principal do projeto:

```
src
 ├── config
 ├── controller
 ├── dto
 ├── exception
 ├── model
 ├── repository
 ├── security
 ├── service
 └── resources
```

---

## Funcionalidades

### Clientes

-  Cadastrar cliente 
-  Listar clientes 
-  Buscar cliente por ID 
-  Atualizar cliente 
-  Excluir cliente 

### Veículos

-  Cadastrar veículo 
-  Listar veículos 
-  Buscar veículo por ID 
-  Atualizar veículo 
-  Excluir veículo 

### Serviços

-  Cadastrar serviço 
-  Listar serviços 
-  Buscar serviço por ID 
-  Atualizar serviço 
-  Excluir serviço 

### Autenticação

-  Login utilizando usuário e senha 
-  Geração de token JWT 
-  Validação do token JWT 
-  Expiração do token 
-  Controle de acesso baseado em perfil 

---

## Autenticação e Autorização

A API utiliza **Spring Security** para autenticação e autorização.

O login é realizado através do endpoint:

```
POST /auth/login
```

Exemplo de requisição:

```
{
    "username": "admin",
    "senha": "admin123"
}
```

Após a autenticação, a API retorna um token JWT:

```
{
    "token": "TOKEN_JWT"
}
```

O token deve ser enviado nas requisições protegidas através do header:

```
Authorization: Bearer TOKEN_JWT
```

### Perfis de acesso

A aplicação possui dois perfis:

| Perfil | GET       | POST      | PUT       | DELETE    |
| ------ | --------- | --------- | --------- | --------- |
| USER   | Permitido | Negado    | Negado    | Negado    |
| ADMIN  | Permitido | Permitido | Permitido | Permitido |

O endpoint de login e a documentação Swagger permanecem acessíveis sem autenticação.

---

## JWT

A aplicação utiliza JWT para manter a autenticação das requisições.

O token possui tempo de expiração configurado em **1 hora**.

O fluxo de autenticação funciona da seguinte forma:

```
Usuário
   ↓
POST /auth/login
   ↓
Spring Security
   ↓
Validação do usuário
   ↓
JwtService
   ↓
Token JWT
   ↓
Requisição protegida
   ↓
JwtAuthenticationFilter
   ↓
Validação do JWT
   ↓
Controller
```

---

## Endpoints da API

### Autenticação

| Método | Endpoint    | Acesso  |
| ------ | ----------- | ------- |
| POST   | /auth/login | Público |

### Clientes

| Método | Endpoint       | Acesso       |
| ------ | -------------- | ------------ |
| GET    | /clientes      | USER / ADMIN |
| GET    | /clientes/{id} | USER / ADMIN |
| POST   | /clientes      | ADMIN        |
| PUT    | /clientes/{id} | ADMIN        |
| DELETE | /clientes/{id} | ADMIN        |

### Veículos

| Método | Endpoint       | Acesso       |
| ------ | -------------- | ------------ |
| GET    | /veiculos      | USER / ADMIN |
| GET    | /veiculos/{id} | USER / ADMIN |
| POST   | /veiculos      | ADMIN        |
| PUT    | /veiculos/{id} | ADMIN        |
| DELETE | /veiculos/{id} | ADMIN        |

### Serviços

| Método | Endpoint       | Acesso       |
| ------ | -------------- | ------------ |
| GET    | /servicos      | USER / ADMIN |
| GET    | /servicos/{id} | USER / ADMIN |
| POST   | /servicos      | ADMIN        |
| PUT    | /servicos/{id} | ADMIN        |
| DELETE | /servicos/{id} | ADMIN        |

---

## Maturidade REST

A API utiliza recursos orientados a entidades e métodos HTTP de acordo com suas responsabilidades.

```
GET     → Consulta recursos
POST    → Criação de recursos
PUT     → Atualização de recursos
DELETE  → Exclusão de recursos
```

Principais códigos HTTP utilizados:

| Código | Situação                          |
| ------ | --------------------------------- |
| 200    | Requisição processada com sucesso |
| 201    | Recurso criado com sucesso        |
| 204    | Recurso excluído com sucesso      |
| 400    | Dados ou JSON inválidos           |
| 403    | Acesso negado                     |
| 404    | Recurso não encontrado            |

---

## Tratamento de Erros

A aplicação possui um tratamento global de exceções através do `GlobalExceptionHandler`.

Os erros são retornados em formato padronizado:

```
{
    "status": 404,
    "message": "Cliente não encontrado"
}
```

São tratados:

-  Recursos não encontrados 
-  Dados de entrada inválidos 
-  JSON inválido 
-  Acesso não autorizado 

---

## Banco de Dados

O projeto utiliza banco H2 em memória para desenvolvimento.

As tabelas são criadas e atualizadas utilizando Flyway.

Arquivos de migração:

```
V1__create_tables.sql
V2__create_usuario.sql
```

A migration `V2__create_usuario.sql` é responsável pela criação da tabela de usuários utilizada na autenticação.

---

## Documentação da API

Após iniciar a aplicação, a documentação Swagger pode ser acessada em:

`http://localhost:8080/swagger-ui/index.html`

A documentação possui suporte à autenticação através de **Bearer Token (JWT)**.

Após realizar o login, o token pode ser informado através do botão **Authorize** do Swagger para testar os endpoints protegidos.

---

## Usuários para Teste

### Administrador

```
Usuário: admin
Senha: admin123
Perfil: ADMIN
```

### Usuário comum

```
Usuário: user
Senha: user123
Perfil: USER
```

Esses usuários são criados automaticamente pela aplicação durante a inicialização.

---

## Testes Automatizados

A aplicação possui testes automatizados utilizando **JUnit e MockMvc**.

Os testes verificam:

-  Inicialização do contexto da aplicação 
-  Login e geração do JWT 
-  Acesso a endpoint protegido sem token 
-  Acesso de usuário comum aos endpoints GET 
-  Bloqueio de operações administrativas para USER 
-  Permissão de operações administrativas para ADMIN 

Para executar os testes:

```
.\mvnw.cmd test
```

Resultado obtido:

```
Tests run: 6
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

---

## Como executar o projeto

Clone o repositório:

```
git clone https://github.com/lzFelipee/Software-Mavericks.git
```

Entre na pasta:

```
cd Software-Mavericks
```

Execute:

```
.\mvnw.cmd spring-boot:run
```

A aplicação iniciará em:

`http://localhost:8080`

A documentação Swagger estará disponível em:

`http://localhost:8080/swagger-ui/index.html`

---

## Integrantes

Luiz Felipe Motta da Silva — RM 559126

Pedro Henrique Faim dos Santos — RM 557440

Nicolas Lorenzo Ferreira da Silva — RM 557962

---

## Disciplina

Arquitetura Orientada a Serviços e Web Services

Professor: Carlos Eduardo Machado de Oliveira