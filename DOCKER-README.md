### Build inicial para deploy no servidor de produção (SOMENTE)

Durante o build, o projeto maven é executado uma única vez no final do processo para verificar se não há erros na compilação do projeto. 

Porém o build não tem acesso as variáveis do arquivo .env, então para fazer o primeiro build é necessário utilizar o seguinte comando:

```
export SPRING_DATASOURCE_URL=jdbc:postgresql://avengers.cmw2whm4hmzl.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=core && \
export SPRING_DATASOURCE_USERNAME=postgres && \
export SPRING_DATASOURCE_PASSWORD=coloque_a_senha_do_BD_aqui && \
docker build  \
--build-arg SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL \
--build-arg SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD \
--build-arg SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME \
 -t avengers:latest .
```
OBS: lembre-se de informar a senha do banco de dados

___

### Próximas execuções após o build

Após realizar o build, é possível utilizar o comando ```docker-compose up -d``` para iniciar o projeto. As variáveis de ambiente podem ser alteradas utilizando para isso o arquivo .env que pode ser criado localmente a partir de uma cópia do arquivo .env.example

Utilize o comando ```docker-compose up -d && docker logs -f saveblu-api-server-1``` para rodar o container e ao mesmo tempo visualizar o log em tempo de execução.

___

### Comandos úteis


```docker images``` para listar as imagens

```docker rmi IMAGE-ID``` para remover uma imagem

```docker rm CONTAINER-ID``` para remover um container