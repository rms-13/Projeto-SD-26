# Projeto-SD-26
# Googol - Meta 1
Estrutura inicial de um motor de pesquisa distribuído em Java com RMI.
## Requisitos
Java JDK 17 ou superior e Maven.
## Compilação
Na raiz do projeto, executar `mvn compile`.
O pom.xml inicial não inclui ainda dependências externas; adicionar jsoup ao implementar o Downloader.
## Organização
- `src/main/java/googol/client`: cliente de consola e callbacks.
- `src/main/java/googol/gateway`: pedidos, balanceamento e failover.
- `src/main/java/googol/downloader`: download e análise de HTML.
- `src/main/java/googol/queue`: fila e reservas de URLs.
- `src/main/java/googol/barrel`: índice, pesquisa e ligações recebidas.
- `src/main/java/googol/replication`: multicast fiável e sincronização.
- `src/main/java/googol/persistence`: gravação e recuperação.
- `src/main/java/googol/remote`: interfaces RMI.
- `src/main/java/googol/model`: objetos serializáveis partilhados.
- `src/main/java/googol/util`: normalização e configuração.
- `src/test`: testes e recursos HTML.
- `config`: configuração dos processos.
- `data`: dados persistentes gerados em execução.
- `scripts`: scripts de arranque a implementar.
- `docs`: relatório e registo de testes.
## Execução
A estrutura ainda não contém classes executáveis nem scripts de arranque.
A implementação deverá permitir iniciar uma Gateway, uma fila, dois Downloaders, dois Barrels e um ou mais clientes.