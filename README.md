# FlixPay 💳

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de **planos de assinatura** (mensal, trimestral, semestral e anual), inspirada em sistemas de cobrança de serviços de streaming.

## ✨ Funcionalidades

- Cadastro de planos de assinatura
- Listagem de planos ativos
- Busca de plano por ID
- Desativação (soft delete) de planos
- Validação de dados de entrada
- Tratamento global de exceções com respostas de erro padronizadas

## 🛠️ Tecnologias utilizadas

- **Java 21**
- **Spring Boot 4.0.6**
  - Spring Web (MVC)
  - Spring Data JPA
  - Bean Validation (Jakarta Validation)
  - H2 Console
  - Spring Boot DevTools
- **MapStruct** — mapeamento entre entidades e DTOs
- **Lombok** — redução de boilerplate
- **H2 Database** — banco de dados em memória para desenvolvimento/testes
- **Maven**

## 📁 Estrutura do projeto

```
src/main/java/com/vb_code/FlixPay
├── controller
│   └── PlanoController.java
├── database
│   ├── entity
│   │   ├── PlanoModel.java
│   │   └── enums
│   │       └── PlanoStatusEnum.java
│   └── repository
│       └── IPlanoRepository.java
├── dto
│   ├── request
│   │   └── PlanoRequestDTO.java
│   └── response
│       ├── PlanoResponseDTO.java
│       └── ErrorResponse.java
├── exception
│   ├── GlobalException.java
│   ├── ConflitoException.java
│   ├── IdNaoEncontradoException.java
│   └── CampoInvalidoException.java
├── mapper
│   └── IPlanoMapper.java
├── service
│   └── PlanoService.java
└── FlixPayApplication.java
```

## ⚙️ Configuração

O projeto utiliza o banco de dados **H2** em memória, configurado no arquivo `application.properties`:

```properties
spring.application.name=FlixPay
spring.datasource.url=jdbc:h2:mem:flixpay
spring.datasource.drive-class-name=org.h2.Driver
spring.datasource.username=vb
spring.datasource.password=1234
spring.jpa.database-plataform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

O console do H2 fica disponível em:
```
http://localhost:8080/h2-console
```

## ▶️ Como executar

### Pré-requisitos
- JDK 21 ou superior
- Maven instalado (ou utilizar o `mvnw` incluso no projeto)

### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/flixpay.git

# Entre na pasta do projeto
cd flixpay

# Execute a aplicação
./mvnw spring-boot:run
```

A aplicação será iniciada em:
```
http://localhost:8080
```

## 📌 Endpoints da API

Base URL: `/v1/api/planos`

| Método | Endpoint                  | Descrição                          |
|--------|----------------------------|-------------------------------------|
| POST   | `/v1/api/planos`           | Cadastra um novo plano               |
| GET    | `/v1/api/planos`           | Lista todos os planos ativos         |
| GET    | `/v1/api/planos/{id}`      | Busca um plano pelo ID               |
| DELETE | `/v1/api/planos/{id}`      | Desativa (soft delete) um plano      |

### Exemplo de requisição — Cadastrar plano

**POST** `/v1/api/planos`

```json
{
  "nome": "Plano Mensal Premium",
  "valor": 29.90,
  "ciclo": "MENSAL"
}
```

**Resposta (201 Created)**

```json
{
  "id": "f3b1c2d4-5678-90ab-cdef-1234567890ab",
  "nome": "Plano Mensal Premium",
  "valor": 29.90,
  "ciclo": "MENSAL",
  "criadoEm": "2026-06-11T10:30:00",
  "ativo": true
}
```

### Ciclos disponíveis (`PlanoStatusEnum`)

- `MENSAL`
- `TRIMESTRAL`
- `SEMESTRAL`
- `ANUAL`

## ⚠️ Tratamento de erros

A API retorna respostas de erro padronizadas no seguinte formato:

```json
{
  "timestamp": "2026-06-11T10:30:00",
  "status": 409,
  "erro": "Conflito de Dados",
  "mensagens": [
    "Já existe uma categoria ativa com esse nome."
  ]
}
```

| Status | Situação                                              |
|--------|--------------------------------------------------------|
| 400    | Erro de validação dos campos enviados                   |
| 404    | Plano não encontrado para o ID informado                |
| 409    | Conflito (nome de plano duplicado, plano já inativo)    |

## 📄 Licença

Este projeto está sob a licença MIT. Sinta-se livre para utilizá-lo e modificá-lo.
