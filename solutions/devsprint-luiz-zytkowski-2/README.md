# Parky - API Estacionamento

## Principais recursos

- Consulta de vagas
- Consulta de vaga por ID
- Consulta de veículo por ID
- Check-in em vaga
- Check-out em vaga
- Consulta de movimentações de uma vaga

## Dicionário de termos

| Português      | Inglês             |
|----------------|--------------------|
| Veículo        | Vehicle            |
| Vaga           | Parking Spot       |
| Evento de vaga | Parking Spot Event |

## Executando o programa

**Importante:** Não se esqueça de configurar o JDK do projeto com a versão 11.
![img.png](_readme_resources/img-01.png)

Após a configuração da JDK, suba o container do docker utilizando o comando abaixo:
```shell
docker-compose up -d  
```

Agora, basta utilizar a task ``bootRun`` para iniciar o servidor:

![img.png](_readme_resources/img-02.png)

Quando terminar, é uma boa prática "descer" o container do docker, você pode fazer isso utilizando:
```shell
docker-compose down  
```