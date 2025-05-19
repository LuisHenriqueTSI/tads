# 🎬 Projeto de Gerenciamento de Cinema

Este é um projeto de API REST para gerenciamento de um cinema, com controle de filmes, sessões, salas e ingressos. O ambiente já está todo configurado com Docker, então é só seguir os passos abaixo para rodar tudo rapidamente em qualquer máquina.

---

## 🛠️ Requisitos

Antes de começar, certifique-se de que o novo PC tem os seguintes softwares instalados:

* [Docker](https://docs.docker.com/get-docker/)
* [Docker Compose](https://docs.docker.com/compose/)

---

## 🚀 Passo a passo para rodar o projeto

### ✅ 1. Copie o projeto para o seu computador

* Clonando o repositório com Git:

  ```bash
  git clone https://github.com/LuisHenriqueTSI/tads.git
  ```
---

### ✅ 2. Verifique se o Docker e o Docker Compose estão instalados

Abra o terminal e execute:

```bash
docker --version
docker compose version
```

Se algum deles não estiver instalado, instale com:

```bash
sudo apt install docker-compose
```

---

### ✅ 3. Acesse a pasta do projeto

No terminal, entre na pasta onde os arquivos estão:

```bash
cd /caminho/do/projeto
```

---

### ✅ 4. Suba os containers com Docker Compose

Execute o seguinte comando:

```bash
docker compose up -d
```

> O `-d` serve para deixar os containers rodando em segundo plano.

---

### ✅ 5. Verifique se os containers subiram corretamente

Execute:

```bash
docker ps
```

Você deve ver containers como `cinema-mariadb`, `app` e outros em execução.

---

### ✅ 6. Inicialize o banco de dados (se necessário)

Se o projeto precisar de estrutura ou dados iniciais no banco, confira se:

* Há um script SQL configurado para rodar na primeira execução;
* Ou se a aplicação está usando JPA/Hibernate com `spring.jpa.hibernate.ddl-auto=update` ou `create`;
* Ou apenas rode a aplicação normalmente, que ela cuidará da criação das tabelas.

---

## 🔐 Acesso à API

Todas as rotas da API são protegidas por autenticação HTTP Basic.

Use as seguintes credenciais para acessar:

* **Usuário**: `admin`
* **Senha**: `admin`

---

## 🧪 Como Testar as Rotas da API

Você pode testar as rotas de duas formas: **via terminal com `curl`** ou **usando o Postman**.

---

### 🧰 Testando com `curl` (Terminal)

Exemplo: Buscar todos os filmes

```bash
curl -u admin:admin http://localhost:8080/api/filmes
```

Exemplo: Criar um novo filme (JSON no corpo da requisição)

```bash
curl -u admin:admin -X POST http://localhost:8080/api/filmes \
  -H "Content-Type: application/json" \
  -d '   {
              "titulo": "Super Mario Bros",
              "duracao": "PT1H32M",
              "genero": "Animação",
              "classificacao": "Livre",
              "diretor": "Aaron Horvath"
              }'
```

Você pode seguir essa lógica para as demais rotas, substituindo a URL e o corpo (`-d`) conforme necessário.

---

### 🧪 Testando com o Postman

1. Abra o **Postman**.

2. Crie uma nova **requisição**.

3. No campo de URL, coloque o endpoint desejado, por exemplo:

   ```
   http://localhost:8080/api/filmes
   ```

4. No menu suspenso de método HTTP (GET, POST, etc.), selecione o método da requisição.

5. Vá na aba **Authorization**:

    * Tipo: `Basic Auth`
    * Usuário: `admin`
    * Senha: `admin`

      6. Se estiver fazendo um **POST** ou **PUT**:

          * Vá para a aba **Body**
          * Selecione **raw** e **JSON**
          * Insira o corpo da requisição, exemplo:

            ```json
              {
              "titulo": "Super Mario Bros",
              "duracao": "PT1H32M",
              "genero": "Animação",
              "classificacao": "Livre",
              "diretor": "Aaron Horvath"
              }

            ```

7. Clique em **Send** para enviar a requisição e ver a resposta.

---

## 📡 Rotas da API

---

### 🎬 Filmes

`http://localhost:8080/api/filmes`

| Método | Rota                         | Descrição                                       |
| ------ | ---------------------------- | ----------------------------------------------- |
| POST   | `/api/filmes`                | Criar um novo filme                             |
| GET    | `/api/filmes`                | Listar todos os filmes                          |
| GET    | `/api/filmes/{id}`           | Buscar filme por ID                             |
| PUT    | `/api/filmes/{id}`           | Atualizar filme por ID                          |
| DELETE | `/api/filmes/{id}`           | Deletar filme por ID                            |
| GET    | `/api/filmes/buscar?titulo=` | Buscar filmes por título (parcial, ignore case) |

---

### 🎟️ Ingressos

`http://localhost:8080/api/ingressos`

| Método | Rota                  | Descrição                 |
| ------ | --------------------- | ------------------------- |
| POST   | `/api/ingressos`      | Criar um novo ingresso    |
| GET    | `/api/ingressos`      | Listar todos os ingressos |
| GET    | `/api/ingressos/{id}` | Buscar ingresso por ID    |
| PUT    | `/api/ingressos/{id}` | Atualizar ingresso por ID |
| DELETE | `/api/ingressos/{id}` | Deletar ingresso por ID   |

---

### 🪑 Salas

`http://localhost:8080/api/salas`

| Método | Rota              | Descrição             |
| ------ | ----------------- | --------------------- |
| POST   | `/api/salas`      | Criar uma nova sala   |
| GET    | `/api/salas/`     | Listar todas as salas |
| GET    | `/api/salas/{id}` | Buscar sala por ID    |
| PUT    | `/api/salas/{id}` | Atualizar sala por ID |

---

### 📽️ Sessões

`http://localhost:8080/api/sessoes`

| Método | Rota                          | Descrição                                            |
| ------ | ----------------------------- | ---------------------------------------------------- |
| POST   | `/api/sessoes`                | Criar uma nova sessão                                |
| GET    | `/api/sessoes`                | Listar todas as sessões                              |
| GET    | `/api/sessoes/{id}`           | Buscar sessão por ID                                 |
| PUT    | `/api/sessoes/{id}`           | Atualizar sessão por ID                              |
| DELETE | `/api/sessoes/{id}`           | Deletar sessão por ID                                |
| GET    | `/api/sessoes/por-filme/{id}` | Listar sessões por ID do filme                       |
| GET    | `/api/sessoes/futuras`        | Listar sessões futuras (com base na data/hora atual) |

---

## 🧑‍💻 Tecnologias Utilizadas

* Java + Spring Boot
* Docker + Docker Compose
* MariaDB
* JPA/Hibernate
* RESTful API

---

## 📂 Estrutura Esperada do Projeto

```
/seu-projeto/
│
├── docker-compose.yml
├── src/
│   └── main/
│       └── java/
│       └── resources/
├── .env (opcional)
└── README.md
```

---