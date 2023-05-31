# PI-Analise-Dados

## Sobre

Este projeto é do PI (Projeto Integrador ) do curso de Engenheria de Computação da Univesp. O principal tema é análise de dados utilizando datasets públicos. 

Este repositório contém o backend. O frontend pode ser encontrado no outro repositório: https://github.com/lucasdclopes/PI-Analise-Dados-Frontend

O backend é feito em Java e utiliza o Spring Boot 3.0.6 e usa o servidor incluído, o TomCat

## Requisitos

Para executar este projeto você precisa do OpenJDK 17. O sistema foi testado com a build da Azul x86 64 bits: https://www.azul.com/downloads/?version=java-17-lts&architecture=x86-64-bit&package=jdk#zulu

O banco de dados utilizado é o Microsoft SQL Server 2022 (16.0.1050.5).

A IDE utilizada é o Eclipse, mas pode-se trabalhar com a IDE de sua preferência

## Banco de dados

Os arquivos para criar o banco de dados estão no repositório.

`create_schema.sql` cria a estrutura(schema) do banco de dados: campos, tabelas, chaves e índices 
https://github.com/lucasdclopes/PI-Analise-Dados/blob/main/create_schema.sql

`insert_datasql.sql` com a estrutura criada, este script insere os dados nas tabelas 
https://github.com/lucasdclopes/PI-Analise-Dados/blob/main/insert_datasql.sql

Note que o banco de dados não é criado automáticamente. É necessário utilizar os scripts acima. Para gerenciar o SQL Server, recomanda-se utilizar o SQL Server Managment Studio: https://learn.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver16

## Acesso ao banco de dados

Os dados de acesso ao banco de dados estão no arquivo `/src/main/resources/application.properties`

## Executando

Basta executar a classe `br.univesp.analisedados.PiAnaliseDadosApplication` como Java Application.
