
# 🛡️ Spring Boot Security JWT

Projeto de API REST utilizando **Spring Boot**, **Spring Security** e **JWT (JSON Web Token)** para autenticação e autorização de usuários.

## 📌 Descrição

Este projeto demonstra como proteger endpoints de uma API utilizando autenticação baseada em token JWT. É uma implementação básica, porém robusta, de segurança em aplicações Java com Spring Boot.

Funcionalidades principais:

* Registro de usuários
* Login com geração de token JWT
* Validação de token para acesso a recursos protegidos
* Controle de acesso por perfis (Roles)
* Refresh de tokens (opcional)

## 🧰 Tecnologias e Ferramentas

* Java 17+ (ou compatível)
* Spring Boot
* Spring Security
* JWT (JJWT ou Auth0 Java JWT)
* Spring Data JPA
* Hibernate
* H2 / PostgreSQL / MySQL (configurável)
* Lombok
* Maven

## ⚙️ Configuração e Execução

### Pré-requisitos

* Java JDK 17+
* Maven
* IDE (IntelliJ, Eclipse, VSCode)

### Passos para executar o projeto

1. **Clone o repositório**

   ```bash
   git clone https://github.com/seu-usuario/springboot-security-jwt.git
   cd springboot-security-jwt
   ```

2. **Configure o `application.properties` ou `application.yml`**

   Exemplo:

   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=

   jwt.secret=secretoSuperSeguro
   jwt.expiration=3600000
   ```

3. **Execute a aplicação**

   ```bash
   mvn spring-boot:run
   ```

4. **Testar endpoints com Postman ou Insomnia**

   * `POST /api/auth/register` — Criação de usuário
   * `POST /api/auth/login` — Autenticação e retorno do JWT
   * `GET /api/users/me` — Endpoint protegido (necessita token JWT)

## 🔐 Segurança

Este projeto utiliza:

* Filtro JWT para interceptar e validar tokens
* Criptografia de senhas com BCrypt
* Autorização baseada em perfis (ex: ROLE\_USER, ROLE\_ADMIN)

## 📂 Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   └── com.example.securityjwt
│   │       ├── config
│   │       ├── controller
│   │       ├── dto
│   │       ├── model
│   │       ├── repository
│   │       ├── security
│   │       └── service
│   └── resources
│       └── application.properties
```

## ✅ Exemplos de Requisições

### Login

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "usuario",
  "password": "senha123"
}
```

**Resposta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

### Acesso protegido

```http
GET /api/users/me
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...
```

## 🧪 Testes

Use ferramentas como **Postman**, **Insomnia** ou **cURL** para testar os endpoints. O projeto pode incluir testes unitários com JUnit e Mockito.

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

## 🙋‍♂️ Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.
