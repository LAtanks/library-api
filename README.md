# 📚 Library API

API REST para gerenciamento de uma biblioteca, permitindo o cadastro de usuários e livros, além do controle de empréstimos.

---

## 🛠️ Tecnologias utilizadas

- **Java 17**
- **Spring Boot 3.5.13**
- **Spring Data JPA**
- **Spring Validation**
- **Hibernate**
- **MySQL**
- **Lombok**
- **Gradle**

---

## ⚙️ Como rodar o projeto

### Pré-requisitos

- Java 17+
- Gradle
- MySQL

### 1. Clone o repositório

```bash
git clone https://github.com/LAtanks/library-api.git
cd library-api
```

### 2. Configure o banco de dados

Crie um banco de dados MySQL:

```sql
CREATE DATABASE library_api;
```

No arquivo `src/main/resources/application.properties`, configure as credenciais:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_api
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Rode o projeto

```bash
./gradlew bootRun
```

A API estará disponível em `http://localhost:8080`

---

## 📌 Endpoints

### 👤 Usuários

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/users/` | Cadastra um novo usuário |
| `GET` | `/users/{id}` | Busca usuário por ID |
| `GET` | `/users/{id}/books` | Retorna os livros emprestados do usuário |

#### Cadastrar usuário — `POST /users/`

```json
{
    "name": "João Silva",
    "email": "joao@email.com",
    "password": "12345678"
}
```

---

### 📖 Livros

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/books/` | Cadastra um novo livro |
| `GET` | `/books?id={id}` | Busca livro por ID |
| `GET` | `/books?title={title}` | Busca livro por título (parcial) |
| `POST` | `/books/borrowBook/{bookId}` | Realiza o empréstimo de um livro |

#### Cadastrar livro — `POST /books/`

```json
{
    "title": "Harry Potter e a Pedra Filosofal",
    "author": "J.K. Rowling",
    "description": "Um jovem bruxo descobre seus poderes e entra para Hogwarts.",
    "category": "FANTASY"
}
```

#### Emprestar livro — `POST /books/borrowBook/{bookId}`

`bookId` é o ID do livro que será emprestado.

```json
{
    "userId": 1,
    "loanStart": "2026-04-10T10:00:00",
    "loanEnd": "2026-04-20T10:00:00"
}
```

---

## 📋 Regras de negócio

- Um usuário pode ter no máximo **3 livros emprestados** simultaneamente
- Não é possível emprestar um livro que já está emprestado
- A data de início do empréstimo deve ser posterior à data atual
- A data de término deve ser posterior à data de início
- O nome do usuário não pode conter números
- O e-mail deve ser válido e único

---

## 🗂️ Estrutura do projeto

```
src/main/java/br/com/latanks/library_api/
├── book/
│   ├── Book.java
│   ├── BookController.java
│   └── IBookRepository.java
├── user/
│   ├── User.java
│   ├── UserController.java
│   └── IUserRepository.java
└── exceptions/
    ├── RestExceptionHandler.java
    ├── InvalidCredentialsExceptions.java
    └── BookBorrowFailedException.java
```

---

## 👨‍💻 Autor

Feito por **LAtanks** — [GitHub](https://github.com/LAtanks)