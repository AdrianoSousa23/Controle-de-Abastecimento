# Controle de Abastecimento de Veículos

Este projeto é uma aplicação desktop desenvolvida em Java no 3º semestre da faculdade, utilizando a biblioteca Swing (JFrame) para criar uma interface gráfica. A aplicação tem como objetivo gerenciar o controle de abastecimento de veículos, permitindo o cadastro de veículos, abastecimentos, e postos de combustíveis, além de calcular a média de quilômetros por litro.


``` mermaid
classDiagram
    class Veiculo {
        +String placaDoCarro
        +String modeloDoCarro
        +int anoDoCarro
    }

    class Abastecimento {
        +Date dataDeAbastecimento
        +String tipoCombustivel
        +double precoPago
        +double quantidadeDeLitros
        +double distanciaPercorrida
    }

    class Posto {
        +String nome
        +String localizacao
    }

    Veiculo --> Abastecimento : Realiza
    Posto --> Abastecimento : Realiza
```

## Funcionalidades

- **Cadastro de Veículos:** Permite o cadastro de veículos, informando a placa, modelo e ano de fabricação.
- **Cadastro de Abastecimentos:** Permite o cadastro de abastecimentos realizados, incluindo dados como data, tipo de combustível, preço pago, quantidade de litros, e distância percorrida.
- **Cadastro de Postos de Combustíveis:** Permite o cadastro dos postos de combustíveis onde os abastecimentos foram realizados.
- **Listagem de Veículos:** Exibe uma lista de veículos cadastrados juntamente com informações dos abastecimentos realizados.
- **Cálculo de Média por Litro:** Calcula a média de quilômetros percorridos por litro de combustível com base nos dados informados.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Swing (JFrame)**: Biblioteca para criação da interface gráfica.
- **MySQL**: Banco de dados utilizado para armazenar as informações.
- **JDateChooser**: Componente utilizado para seleção de datas na interface gráfica.


## Pré-requisitos

- **JDK 8 ou superior**: Necessário para compilar e executar o código Java.
- **MySQL**: Banco de dados para armazenar as informações dos veículos, abastecimentos, e postos de combustíveis.


## Configuração do Banco de Dados

1. Instale o MySQL em sua máquina e crie um banco de dados chamado `controle_abastecimento`.
2. Execute os seguintes comandos SQL para criar as tabelas necessárias:

```sql
CREATE TABLE Veiculo (
    placaDoCarro VARCHAR(10) PRIMARY KEY,
    modeloDoCarro VARCHAR(50),
    anoDoCarro INT
);

CREATE TABLE Posto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    veiculo_placaDoCarro VARCHAR(10),
    nome VARCHAR(100),
    localizacao VARCHAR(100),
    FOREIGN KEY (veiculo_placaDoCarro) REFERENCES Veiculo(placaDoCarro)
);

CREATE TABLE Abastecimento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    veiculo_placaDoCarro VARCHAR(10),
    dataDeAbastecimento DATE,
    tipoCombustivel VARCHAR(20),
    precoPago DOUBLE,
    quantidadeDeLitros DOUBLE,
    distanciaPercorrida DOUBLE,
    mediaPorLitro DOUBLE,
    FOREIGN KEY (veiculo_placaDoCarro) REFERENCES Veiculo(placaDoCarro)
);
