# API de Controle de Estoque

## Visão Geral

Esta é a API backend de um sistema de controle de estoque para uma indústria, responsável por gerenciar produtos e matérias-primas. O sistema permite realizar operações CRUD (Criar, Ler, Atualizar e Deletar) de produtos e matérias-primas, associar matérias-primas aos produtos e calcular quais produtos podem ser produzidos com os estoques disponíveis, priorizando os produtos de maior valor.

A API foi desenvolvida utilizando **Quarkus** e suporta bancos de dados relacionais como **Postgres, MySQL ou Oracle**.

---

## Funcionalidades

### Produtos

- Operações CRUD para produtos.
- Cada produto possui:
  - `id` (identificador único)
  - `name` (nome do produto)
  - `price` (valor)

### Matérias-Primas

- Operações CRUD para matérias-primas.
- Cada matéria-prima possui:
  - `id` (identificador único)
  - `name` (nome da matéria-prima)
  - `quantity_in_stock` (quantidade em estoque)

### Associação Produto - Matéria-Prima

- Associar matérias-primas aos produtos, definindo a quantidade necessária para produção.
- CRUD para gerenciar essas associações.

### Planejamento de Produção

- Verificar quais produtos podem ser produzidos com o estoque disponível.
- Sugerir quantidades de produção de forma a maximizar o valor total.
- Priorizar produtos de maior valor.

---

## Tecnologias Utilizadas

- **Framework Backend:** Quarkus
- **Linguagem:** Java 21
- **Banco de Dados:** Postgres
- **Build:** Maven
- **API:** RESTful

---

## Como Executar

### Clonar o Repositório

```bash
git clone https://github.com/uandersoncosta/app-autoflex.git
cd app-autoflex
```

### Configurar Banco de Dados

Edite o arquivo application.properties com as informações do seu banco de dados:

```
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=seu_usuario
quarkus.datasource.password=sua_senha
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/inventory_db
quarkus.hibernate-orm.database.generation=update
```

### Build e Execução

```
mvn clean install
mvn quarkus:dev
```

## Endpoints da API

**Produtos**
- `GET /products` - Listar todos os produtos  
- `GET /products/{id}` - Obter produto por ID  
- `POST /products` - Criar um novo produto  
- `PUT /products/{id}` - Atualizar produto  
- `DELETE /products/{id}` - Deletar produto  

**Matérias-Primas**
- `GET /raw-materials` - Listar todas as matérias-primas  
- `GET /raw-materials/{id}` - Obter matéria-prima por ID  
- `POST /raw-materials` - Criar nova matéria-prima  
- `PUT /raw-materials/{id}` - Atualizar matéria-prima  
- `DELETE /raw-materials/{id}` - Deletar matéria-prima  

**Associação Produto - Matéria-Prima**
- `POST /product-materials` - Associar matéria-prima a produto  
- `PUT /product-materials/{id}` - Atualizar associação  
- `DELETE /product-materials/{id}` - Remover associação  

**Planejamento de Produção**
- `GET /production/suggest` - Listar produtos que podem ser produzidos com o estoque disponível (priorizando os de maior valor)


### Testes
```mvn test```