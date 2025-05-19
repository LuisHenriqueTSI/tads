Entendi perfeitamente! Aqui está a tabela atualizada com as **rotas completas**:

---

## ✅ **Todas as Rotas da API**

> ⚠️ **Atenção**: Todas as rotas abaixo são **protegidas por autenticação HTTP Basic**.
> Para acessar, use:
>
> * **Usuário**: `admin`
> * **Senha**: `admin`


### 🎬 `http://localhost:8080/api/filmes`

| Método | Rota                                              | Descrição                                       |
| ------ | ------------------------------------------------- | ----------------------------------------------- |
| POST   | `http://localhost:8080/api/filmes`                | Criar um novo filme                             |
| GET    | `http://localhost:8080/api/filmes`                | Listar todos os filmes                          |
| GET    | `http://localhost:8080/api/filmes/{id}`           | Buscar filme por ID                             |
| PUT    | `http://localhost:8080/api/filmes/{id}`           | Atualizar filme por ID                          |
| DELETE | `http://localhost:8080/api/filmes/{id}`           | Deletar filme por ID                            |
| GET    | `http://localhost:8080/api/filmes/buscar?titulo=` | Buscar filmes por título (parcial, ignore case) |

---

### 🎟️ `http://localhost:8080/api/ingressos`

| Método | Rota                                       | Descrição                 |
| ------ | ------------------------------------------ | ------------------------- |
| POST   | `http://localhost:8080/api/ingressos`      | Criar um novo ingresso    |
| GET    | `http://localhost:8080/api/ingressos`      | Listar todos os ingressos |
| GET    | `http://localhost:8080/api/ingressos/{id}` | Buscar ingresso por ID    |
| PUT    | `http://localhost:8080/api/ingressos/{id}` | Atualizar ingresso por ID |
| DELETE | `http://localhost:8080/api/ingressos/{id}` | Deletar ingresso por ID   |

---

### 🪑 `http://localhost:8080/api/salas` 

| Método | Rota                                   | Descrição             |
| ------ | -------------------------------------- | --------------------- |
| POST   | `http://localhost:8080/api/salas`      | Criar uma nova sala   |
| GET    | `http://localhost:8080/api/salas/`     | Lista todas as salas  |
| GET    | `http://localhost:8080/api/salas/{id}` | Busca sala por ID     |
| PUT    | `http://localhost:8080/api/salas/{id}` | Atualizar sala por ID |

---

### 📽️ `http://localhost:8080/api/sessoes` 

| Método | Rota                                               | Descrição                                                       |
| ------ | -------------------------------------------------- | --------------------------------------------------------------- |
| POST   | `http://localhost:8080/api/sessoes`                | Criar uma nova sessão                                           |
| GET    | `http://localhost:8080/api/sessoes`                | Listar todas as sessões                                         |
| GET    | `http://localhost:8080/api/sessoes/{id}`           | Buscar sessão por ID                                            |
| PUT    | `http://localhost:8080/api/sessoes/{id}`           | Atualizar sessão por ID                                         |
| DELETE | `http://localhost:8080/api/sessoes/{id}`           | Deletar sessão por ID                                           |
| GET    | `http://localhost:8080/api/sessoes/por-filme/{id}` | Listar sessões por ID do filme                                  |
| GET    | `http://localhost:8080/api/sessoes/futuras`        | Listar sessões futuras (provavelmente usando a data/hora atual) |

---