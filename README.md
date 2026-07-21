# 🥷 CadastroDeNinjas

API RESTful e Interface Web para cadastro e gestão de ninjas e suas missões, desenvolvida com **Spring Boot** e **Java 21**.

---

## 📋 Índice

- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Funcionalidades](#-funcionalidades)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar](#-como-executar)
- [Estrutura do Projecto](#-estrutura-do-projecto)
- [Endpoints da API](#-endpoints-da-api)
- [Modelos de Dados](#-modelos-de-dados)
- [Interface Web](#-interface-web)
- [Melhorias Futuras](#-melhorias-futuras)
- [Autor](#-autor)

---

## 🛠 Tecnologias Utilizadas

| Tecnologia          | Versão        |
|---------------------|---------------|
| Java                | 21            |
| Spring Boot         | 4.1.0         |
| Maven               | 3.9+          |
| H2 Database         | (runtime)     |
| JPA / Hibernate     | (Spring Data) |
| Thymeleaf           | (templates)   |
| Flyway              | (migrações)   |
| Lombok              | 1.18.38       |
| HTML + CSS          | (interface)   |

---

## ⚙ Funcionalidades

- ✅ **CRUD completo de Ninjas** — Criar, listar, actualizar e remover ninjas
- ✅ **CRUD completo de Missões** — Criar, listar, actualizar e remover missões
- ✅ **Associação Ninja ↔ Missão** — Cada ninja pode estar ligado a uma missão
- ✅ **Rank de Ninjas** — Genin, Chunin, Jounin e Kage
- ✅ **Interface Web (Thymeleaf)** — Páginas HTML com CSS moderno
- ✅ **API REST** — Endpoints JSON para integração com outros serviços
- ✅ **Banco de dados em memória** — Fácil de testar sem configuração externa
- ✅ **Console H2** — Consulta directa à base de dados pelo browser

---

## 📦 Pré-requisitos

- **Java 21** (JDK) instalado
- **Maven 3.9+** (ou usar o `mvnw` incluído)
- **Git** (opcional, para clonar o repositório)

Verifique a instalação:

```bash
java --version
mvn --version
```

---

## 🚀 Como Executar

### 1. Clonar o repositório

```bash
git clone https://github.com/JoelsonBotelho/CadastroDeNinjas.git
cd CadastroDeNinjas
```

### 2. Configurar variáveis de ambiente

Crie um ficheiro `.env` na raiz do projecto (ou exporte as variáveis):

```env
DATABASE_URL=jdbc:h2:mem:cadastrodados
DATABASE_USERNAME=sa
DATABASE_PASSWORD=
```

> **Nota:** O H2 é um banco em memória, por isso estes valores são os padrão. Pode alterar conforme necessário.

### 3. Executar com Maven

```bash
# Usando o Maven Wrapper (recomendado)
./mvnw spring-boot:run

# Ou se tiver Maven instalado globalmente
mvn spring-boot:run
```

A aplicação iniciará em: **http://localhost:8080**

### 4. Aceder às interfaces

| Interface             | URL                              |
|-----------------------|----------------------------------|
| Web UI — Listar Ninjas | http://localhost:8080/ninjas/ui/listar |
| Web UI — Adicionar Ninja | http://localhost:8080/ninjas/ui/adicionar |
| Console H2            | http://localhost:8080/h2-console  |

> **Console H2:** JDBC URL: `jdbc:h2:mem:cadastrodados` — User: `sa` — Password: *(vazio)*

---

## 🗂 Estrutura do Projecto

```
CadastroDeNinjas/
├── src/
│   ├── main/
│   │   ├── java/dev/jav10x/CadastroDeNinjas/
│   │   │   ├── CadastroDeNinjasApplication.java    # Classe principal
│   │   │   ├── Ninjas/
│   │   │   │   ├── NinjaModel.java                 # Entidade JPA
│   │   │   │   ├── NinjaDTO.java                   # Data Transfer Object
│   │   │   │   ├── NinjaMapper.java                # Mapper Model ↔ DTO
│   │   │   │   ├── NinjaRepository.java            # Repositório JPA
│   │   │   │   ├── NinjaService.java               # Lógica de negócio
│   │   │   │   ├── NinjaController.java            # Controller REST
│   │   │   │   └── NinjaControllerUI.java          # Controller Web (Thymeleaf)
│   │   │   └── Missoes/
│   │   │       ├── MissoesModel.java               # Entidade JPA
│   │   │       ├── MissoesDTO.java                 # Data Transfer Object
│   │   │       ├── MissoesMapper.java              # Mapper Model ↔ DTO
│   │   │       ├── MissoesRepository.java          # Repositório JPA
│   │   │       ├── MissoesService.java             # Lógica de negócio
│   │   │       └── MissoesController.java          # Controller REST
│   │   ├── resources/
│   │   │   ├── application.properties              # Configurações
│   │   │   ├── templates/
│   │   │   │   ├── listarNinjas.html               # Página de listagem
│   │   │   │   └── adicionarNinja.html             # Página de criação/edição
│   │   │   ├── static/css/
│   │   │   │   └── style.css                       # Estilos da interface
│   │   │   └── db/migrations/
│   │   │       └── V2__Add_rank_tb_cadastro.sql    # Migração Flyway
│   └── test/
│       └── java/dev/jav10x/CadastroDeNinjas/
│           └── CadastroDeNinjasApplicationTests.java
├── pom.xml                                         # Configuração Maven
├── mvnw / mvnw.cmd                                 # Maven Wrapper
├── .gitignore
└── README.md                                       # (este ficheiro)
```

---

## 🌐 Endpoints da API

### Ninjas (`/ninjas`)

| Método | Rota                  | Descrição                    | Corpo (JSON) |
|--------|-----------------------|------------------------------|--------------|
| `GET`  | `/ninjas/boasvindas`  | Mensagem de boas-vindas      | —            |
| `POST` | `/ninjas/criar`       | Criar um novo ninja          | `NinjaDTO`   |
| `GET`  | `/ninjas/listar`      | Listar todos os ninjas       | —            |
| `GET`  | `/ninjas/listar/{id}` | Buscar ninja por ID          | —            |
| `PUT`  | `/ninjas/alterar/{id}` | Actualizar ninja por ID     | `NinjaDTO`   |
| `DELETE` | `/ninjas/deletar/{id}` | Remover ninja por ID       | —            |

### Missões (`/missoes`)

| Método | Rota                    | Descrição                     | Corpo (JSON) |
|--------|-------------------------|-------------------------------|--------------|
| `POST` | `/missoes/criar`       | Criar uma nova missão         | `MissoesDTO` |
| `GET`  | `/missoes/listar`      | Listar todas as missões       | —            |
| `GET`  | `/missoes/listar/{id}` | Buscar missão por ID          | —            |
| `PUT`  | `/missoes/alterar/{id}` | Actualizar missão por ID     | `MissoesDTO` |
| `DELETE` | `/missoes/deletar/{id}` | Remover missão por ID       | —            |

### Interface Web (`/ninjas/ui`)

| Método | Rota                     | Descrição                          |
|--------|--------------------------|------------------------------------|
| `GET`  | `/ninjas/ui/listar`      | Página com lista de ninjas         |
| `GET`  | `/ninjas/ui/adicionar`   | Página para adicionar novo ninja   |
| `POST` | `/ninjas/ui/salvar`      | Acção do formulário de criação     |
| `GET`  | `/ninjas/ui/deletar/{id}` | Acção para remover ninja por ID   |

---

## 📊 Modelos de Dados

### Ninja (`tb_cadastro`)

| Campo     | Tipo     | Descrição                    |
|-----------|----------|------------------------------|
| `id`      | `Long`   | Identificador único (PK)     |
| `nome`    | `String` | Nome do ninja                |
| `email`   | `String` | Email (único)                |
| `idade`   | `int`    | Idade do ninja               |
| `rank`    | `String` | Rank: Genin, Chunin, Jounin, Kage |
| `missoes` | `MissoesModel` | Missão associada (FK → `tb_missoes`) |

### Missão (`tb_missoes`)

| Campo         | Tipo     | Descrição                    |
|---------------|----------|------------------------------|
| `id`          | `Long`   | Identificador único (PK)     |
| `nome`        | `String` | Nome da missão               |
| `dificuldade` | `String` | Nível de dificuldade         |

**Relacionamento:** Uma missão pode ter vários ninjas (`OneToMany`), e cada ninja pode ter uma missão (`ManyToOne`).

---

## 🖥 Interface Web

A interface foi desenvolvida com **Thymeleaf** e **CSS customizado**, oferecendo:

- 🎨 Design moderno com gradientes e sombras
- 📱 Layout responsivo (adaptável a dispositivos móveis)
- 🏷 Exibição de rank com badge estilizado
- 🔗 Acções rápidas de editar e excluir
- ➕ Formulário completo para adicionar ninjas com:
  - Selecção de rank (Genin, Chunin, Jounin, Kage)
  - Selecção de missão a partir de dados reais da base
  - Validação HTML5 integrada

---

## 🔮 Melhorias Futuras

- [ ] Página de edição de ninjas (actualmente o botão "Editar" redirecciona para uma rota não implementada)
- [ ] CRUD completo de missões via interface web
- [ ] Autenticação e autorização (Spring Security)
- [ ] Testes unitários e de integração mais abrangentes
- [ ] Paginação na listagem de ninjas
- [ ] Dockerização da aplicação
- [ ] Migração para banco de dados persistente (PostgreSQL, MySQL)

---

## 👤 Autor

Desenvolvido por **Joelson Botelho** como projecto de estudo do curso **Java 10x**.

---

<p align="center">
  Feito com ☕ e 🥷
</p>

