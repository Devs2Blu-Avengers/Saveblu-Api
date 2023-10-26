### Build inicial para deploy no servidor de produção somente

Durante o build o projeto maven é executado uma única vez para verificar se não há erros na compilação do projeto. 

Porém o build não tem acesso as variáveis do arquivo .env, então pata fazer o primeiro build foi necessário utilizar o seguinte comando:

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

### Próximas execuções após o build

Após realizar o build é possivel utilizar o comando ```docker-compose up -d``` para iniciar o projeto, as variaveis de ambiente podem ser alteradas utilizando para isso o arquivo .env que pode ser criado localmente a partir de uma copia do arquivo .env.example

Utilize o comando ```docker-compose up -d && docker logs -f saveblu-api-server-1``` para rodar o container e ao mesmo tempo visualizar o log em tempo de execução.


### Comandos úteis


```docker images``` para listar as imagens

```docker rmi IMAGE-ID``` para remover uma imagem

```docker rm CONTAINER-ID``` para remover um container