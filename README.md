# Backend Sistema de Tele Pizza 🍕

Este repositório contém a implementação do backend para o sistema de gestão de pedidos automatizados de uma pizzaria online. O projeto é referente ao Trabalho Final - Parte 1 da disciplina de Projeto e Arquitetura de Software.

## 👥 Integrantes da Equipe

- Bernardo Kirsch
- Bernardo Medeiros
- Erick Carpes

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+** \* **Spring Boot 3.x** (Web, Security, Data JPA, Validation)
- **PostgreSQL** (Banco de dados relacional)
- **Lombok** (Produtividade e redução de código boilerplate)
- **SpringDoc OpenAPI / Swagger** (Documentação interativa da API)

---

## ⚙️ Pré-requisitos

Antes de iniciar, certifique-se de ter instalado em sua máquina:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
- [PostgreSQL](https://www.postgresql.org/download/) rodando localmente (ou via Docker).
- Uma IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code).

---

## 🚀 Como Bootar a aplicação

### 1. Configuração do Banco de Dados

Antes de rodar a aplicação, você precisa criar o banco de dados no PostgreSQL.
Abra o seu terminal do Postgres (psql) ou sua ferramenta visual (pgAdmin, DBeaver) e execute:

```sql
CREATE DATABASE telepizzadb;
```

Em seguida, configure as credenciais no arquivo `src/main/resources/application.properties` (ou `application.yml`):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/telepizzadb
spring.datasource.username=SEU_USUARIO_POSTGRES
spring.datasource.password=SUA_SENHA_POSTGRES

# Permite que o Hibernate crie as tabelas automaticamente baseado nas entidades
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 2. Executando o Projeto

Você pode inicializar a aplicação diretamente pela sua IDE (rodando a classe principal `Application.java`) ou utilizando o Maven Wrapper pelo terminal.

Na raiz do projeto, execute o comando abaixo:

**No macOS / Linux:**

```bash
./mvnw spring-boot:run
```

**No Windows:**

```cmd
mvnw.cmd spring-boot:run
```

Se tudo estiver configurado corretamente, a aplicação iniciará na porta `8080`.

---

## 📖 Acessando a Documentação (Swagger)

Com a aplicação rodando, você pode acessar a interface interativa do Swagger para visualizar e testar os endpoints da API desenvolvida para o aplicativo móvel.

Acesse no seu navegador:
👉 **[http://localhost:8080/swagger-ui.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui.html)**

---

## 📂 Estrutura do Projeto

O projeto procura manter os níveis com um isolamento adequado uns dos outros , seguindo os princípios da **Arquitetura Limpa** conforme discutido em aula e apresentado no estudo de caso.
# T1-Projarc
