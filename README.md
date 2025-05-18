## ✅ **Todas as Rotas da API**

### 🎬 `/api/filmes` (FilmeController)

| Método | Rota                         | Descrição                                       |
| ------ | ---------------------------- | ----------------------------------------------- |
| POST   | `/api/filmes`                | Criar um novo filme                             |
| GET    | `/api/filmes`                | Listar todos os filmes                          |
| GET    | `/api/filmes/{id}`           | Buscar filme por ID                             |
| PUT    | `/api/filmes/{id}`           | Atualizar filme por ID                          |
| DELETE | `/api/filmes/{id}`           | Deletar filme por ID                            |
| GET    | `/api/filmes/buscar?titulo=` | Buscar filmes por título (parcial, ignore case) |
| GET    | `/api/filmes/por-genero/{g}` | Buscar filmes por gênero                        |

---

### 🎟️ `/api/ingressos` (IngressoController)

| Método | Rota                  | Descrição                 |
| ------ | --------------------- | ------------------------- |
| POST   | `/api/ingressos`      | Criar um novo ingresso    |
| GET    | `/api/ingressos`      | Listar todos os ingressos |
| GET    | `/api/ingressos/{id}` | Buscar ingresso por ID    |
| PUT    | `/api/ingressos/{id}` | Atualizar ingresso por ID |
| DELETE | `/api/ingressos/{id}` | Deletar ingresso por ID   |

---

### 🪑 `/api/salas` (SalaController)

| Método | Rota              | Descrição             |
| ------ | ----------------- | --------------------- |
| POST   | `/api/salas`      | Criar uma nova sala   |
| PUT    | `/api/salas/{id}` | Atualizar sala por ID |

> ⚠️ **Não há métodos GET/DELETE implementados para Sala.**

---

### 📽️ `/api/sessoes` (SessaoController)

| Método | Rota                          | Descrição                                                       |
| ------ | ----------------------------- | --------------------------------------------------------------- |
| POST   | `/api/sessoes`                | Criar uma nova sessão                                           |
| GET    | `/api/sessoes`                | Listar todas as sessões                                         |
| GET    | `/api/sessoes/{id}`           | Buscar sessão por ID                                            |
| PUT    | `/api/sessoes/{id}`           | Atualizar sessão por ID                                         |
| DELETE | `/api/sessoes/{id}`           | Deletar sessão por ID                                           |
| GET    | `/api/sessoes/por-filme/{id}` | Listar sessões por ID do filme                                  |
| GET    | `/api/sessoes/futuras`        | Listar sessões futuras (provavelmente usando a data/hora atual) |

---