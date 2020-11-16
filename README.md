# Dígito Único Inter

### Aplicação SpringBoot construída para o teste do Banco Inter

![Badge](https://img.shields.io/static/v1?label=Springboot&message=Framework&color=green&style=for-the-badge&logo=Spring) ![Badge](https://img.shields.io/static/v1?label=Postman&message=RestAPI&color=orange&style=for-the-badge&logo=Postman) ![Badge](https://img.shields.io/static/v1?label=Swagger&message=DOCS&color=green&style=for-the-badge&logo=Swagger)

___

### Pré-requisitos

1. IDE Spring Tool Suite (Para execução da Aplicação)
2. APP Postman (Para testes de API)

### Como rodar?

O projeto foi criado em Maven, sendo assim siga os passos para importar e executar na sua IDE:

1. Com o GIT instalado em sua máquina e a URL do projeto em mãos, crie um algum lugar do seu PC uma pasta para clonar o repositório.
2. Após criar a pasta, clique com o botão direito do mouse, opção GIT BASH HERE e digite o comando abaixo:

    ```git clone https://gitlab.com/ismarleya7x/digito-unico-inter.git```
3. Após o download, abra sua IDE e faça o IMPORT do projeto (File -> Import -> Maven -> Existing Maven Project)
4. Após o IMPORT basta clicar com o botão direito do mouse sobre o projeto -> RUN AS -> SPRING BOOT APP

### Como executar testes via Postman/Swagger

Com a aplicação rodando na sua IDE, basta acessar localhost:8080/swagger-ui.html para ter acesso a documentação e aos ENDPOINTS. Através do SWAGGER é possível realizar os testes de API.

Para realizar os testes via Postman basta seguir o exemplo:

Para realizar o cálculo de dígito único:

- **URL:** http://localhost:8080/digitounico
- **METHOD:** POST
- **BODY:** 
```json
{
    "numero": "1234",
    "repeticao": 2
}
```

Com isso tenha o **resultado**:
```json
{
  "numero": "1234",
  "repeticao": 2,
  "resultado": 2
}
```

Caso queira associar o cálculo a um usuário, basta informar a ID deste usúario:
```json
{
    "numero": "1234",
    "repeticao": 2,
    "usuario": {
        "id": 1
    }
}
```

Caso tenha associado um dígito único a um usuário, ele será apresentado na pesquisa de usuários:
```json
  {
    "id": 2,
    "nome": "Ismarley",
    "email": "teste@teste.com",
    "digito": [
      {
        "numero": "1234",
        "repeticao": 2,
        "resultado": 2
      }
    ]
  }
```

### Testes online
Você pode executar testes online via Heroku com a URL: https://digito-unico-inter.herokuapp.com/
