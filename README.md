# Plataforma Mobi Bike
O ciclista cadastrado na plataforma terá um cadastro de pessoa fisíca verificado, utilizando API de verificação da Assertiva.

![plataforma](docs/plataforma-mobi-bike.png)


## Serviços

### Coletor
Aplicação responsável por receber os dados coletados pelo aplicativo, tempo real ou offline
   - Recebe a Geolocation 
   - Recebe dados do acelerometro
   - Status de uso do smartphone

### Farejador 
Serviço que ficará processando os dados do Coletor, para verificar inconsistências nas informações “fraudes”, baseado informações coletadas pelo Centralizador, no mesmo intervalo de tempo que o viagem foi realizada.

Metricas de verificação velocidade, localização, histórico, trânsito
	
Dentro do período de D+3 validar no sistema da sptrans se o ciclista cadastrado utilizou o transporte público simultaneamente ao percurso do ciclista

### Historico
Aplicação para consultar as validações de creditos realizadas, baseado no score gerado pelo Farejador
* Histórico de todos créditos concedidos
* Motivo pelo qual o crédito não foi validado ex:
    *  Aplicativo não coletou as coordenadas do percurso
    *  Uso do bilhete único
    *  Inconsistencia de velocidade
    *  Classificação da percurso utilizado, ex. Andou na 23 de maio
    *  Região com ciclofaixa proem o usuário demorou muito tempo para fazer o percurso.

### Centralizador
Aplicação responsável por gerar métricas segundárias, ao trajeto feito pelo ciclista, para identificar os possíveis maus cliclistas que tentarem bular o sistema.

Consultar linhas da API Sptrans olhou vivo
Consulta região que o ciclista passou, com base na latitude e longitude
Consultar utilização do bilhete único em algum modal do transporte público
Consultar numero do bilhete se é válido	
Consultar rotas possíveis já testada por usuários, com origem e destino no BikeMap

### Transparência
Aplicação de consultas publicas, disponíves para todos os cidadãos 

* Quantidade de creditos valídados  
* Regiões com mais utilização de bicicletas e  valor total da liberação dos crédito
* Credito de carbono gerados

### Autenticacão
Todos os serviços que disponibilizam consultas, as requisições serão autenticados verificando o perfil de acesso,  para proteger as informações sensiveis do usuário.

## Aplicações: 
### Aplicativo mobile
Grava o percurso do ciclista
Coleta dados dos senhores, GPS, acelerômetro
Registra o ponto de partida e chegada
Efetuar o primeira cadastro

![Login](docs/telas/App-Login.png)

![Cadastro](docs/telas/App-Cadastro.png)

![Rota](docs/telas/App-Origem-Destino.png)

![Origem destino](docs/telas/App-Origem-Destino-Preench-N-Ativo.png)

![Origem destino](docs/telas/App-Origem-Destino-Preench-Ativo.png)

![Amigos](docs/telas/App-Amigos.png)

![Amigos Detalhe](docs/telas/App-Amigos-Detalhe.png)

![Trocar aviso](docs/telas/App-Mobis-Trocar-Aviso.png)

![aviso](docs/telas/App-Mobis-Trocar.png)

![App perfil](docs/telas/App-Perfil.png)

![App perfil Conquista](docs/telas/App-Perfil-Conquistas.png)

![App perfil liberadas](docs/telas/App-Perfil-Conquistas-Tem.png)

![Verificacao de seguranca](docs/telas/App-Validar-BU.png)

![Verificacao de seguranca noti](docs/telas/App-Validar-BU-notif.png)

### Plataforma de gestão
Monitoramento das tentativa de fraudes
Definição dos score  de mau ciclista,
Com base no score, quantas métricas deverão ser aplicadas para validar o trajeto
