# API Financeira

## Diagrama de Classes (Domínio da API)
  ```mermaid
  classDiagram
    class User {
      -String name
      -Account account
      -Feature[] features
      -Card card
      -News[] news
    }

    class Account {
      -String number
      -String agency
      -Number balance
      -Number limit
    }

    class Feature {
      -String icon
      -String description
    }

    class Card {
      -String number
      -Number limit
    }

    class News {
      -String icon
      -String description
    }

    User "1" *-- "1" Account
    User "1" *-- "N" Feature
    User "1" *-- "1" Card
    User "1" *-- "N" News
  ```

## API Endpoints

### User Endpoints

  #### Get All Users
  - **Method**: GET
  - **URL**: `/users`
  - **Description**: Retorna uma lista de todos os usuários.

  #### Create User
  - **Method**: POST
  - **URL**: `/users`
  - **Description**: Cria um novo usuário com as informações fornecidas.

  #### Create All User
  - **Method**: POST
  - **URL**: `/users/createusers`
  - **Description**: Cria uma lista de usuários.

  #### Get User by ID
  - **Method**: GET
  - **URL**: `/users/{id}`
  - **Description**: Retorna as informações de um usuário específico com base no ID.

  #### Update User (Não implementado)
  - **Method**: PUT
  - **URL**: `/users/{id}`
  - **Description**: Atualiza as informações de um usuário específico com base no ID.

  #### Delete User
  - **Method**: DELETE
  - **URL**: `/users/{id}`
  - **Description**: Remove um usuário específico com base no ID.

  #### Search Users
  - **Method**: GET
  - **URL**: `/users/search`
  - **Description**: Permite buscar usuários com base em parâmetros de consulta, como nome e email.

### Account Endpoints
  #### Get All Accounts
  - **Method**: GET
  - **URL**: `/accounts`
  - **Description**: Retorna uma lista de todas as contas.

  #### Get Account by User ID
  - **Method**: GET
  - **URL**: `/accounts/users/{userId}`
  - **Description**: Retorna as informações da conta associada a um usuário específico.

  #### Get Account by Account ID
  - **Method**: GET
  - **URL**: `/accounts/{id}`
  - **Description**: Retorna as informações de uma conta específica com base no ID.

  #### Update Account (Não implementado)
  - **Method**: PUT
  - **URL**: `/accounts/{id}`
  - **Description**: Atualiza as informações de uma conta específica com base no ID.

### Feature Endpoints

  #### Get User Features
  - **Method**: GET
  - **URL**: `/features/users/{userId}`
  - **Description**: Retorna todas as features associadas a um usuário.

  #### Add Feature to User(Não implementado)
  - **Method**: POST
  - **URL**: `/featuresusers/{userId}/`
  - **Description**: Adiciona uma nova feature para um usuário.

  #### Delete Feature from User (Não implementado)
  - **Method**: DELETE
  - **URL**: `/features/{featureId}/users/{userId}`
  - **Description**: Remove uma feature específica de um usuário.

### Card Endpoints (Não implementado)

  #### Get User Card
  - **Method**: GET
  - **URL**: `/users/{userId}/card`
  - **Description**: Retorna as informações do cartão de um usuário específico.

  #### Update User Card
  - **Method**: PUT
  - **URL**: `/users/{userId}/card`
  - **Description**: Atualiza as informações do cartão de um usuário específico.

### News Endpoints (Não implementado)

  #### Get User News
  - **Method**: GET
  - **URL**: `/users/{userId}/news`
  - **Description**: Retorna todas as notícias associadas a um usuário.

  #### Add News to User
  - **Method**: POST
  - **URL**: `/users/{userId}/news`
  - **Description**: Adiciona uma nova notícia para um usuário.

  #### Delete News from User
  - **Method**: DELETE
  - **URL**: `/users/{userId}/news/{newsId}`
  - **Description**: Remove uma notícia específica de um usuário.
