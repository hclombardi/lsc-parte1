# lsc-parte1

Parte 1 do exercicio Le Salva Calcula

## Iniciando

Estas instruções ajudarão a configurar e executar uma cópia do projeto em sua maquina. Por favor siga os passos abaixo.

### Pre-requisitos

Antes de começar, os seguintes programas devem estar instalados:

```
Java 8 (JDK 8)
Maven
Banco de Dados MySQL
```

### Instalação

Primeiramente deve ser criado o banco de dados gft e o usuário gftuser no banco MySQL:

```
CREATE DATABASE gft;

INSERT INTO mysql.user (User,Host,authentication_string,ssl_cipher,x509_issuer,x509_subject) VALUES('gftuser','localhost',PASSWORD('gftuser123'),'','','');

FLUSH PRIVILEGES;

GRANT ALL PRIVILEGES ON gft.* to gftuser@localhost;

FLUSH PRIVILEGES;

SHOW GRANTS FOR 'gftuser'@'localhost';
```

Depois o projeto deve ser clonado na máquina: 
```
git clone https://github.com/hclombardi/lsc-parte1.git
```

No arquivo resources/application.properties deve ser alterado a pasta onde estarão os arquivos a serem processados:
```
path.dir.json.files= /home/hamilton/gft/docs/massa/
```

Para compilar o projeto:
```
mvn clean package
```

Para compilar o projeto:
```
mvn clean package
```

## Execução dos testes

No proprio processo de build já são executados os testes unitários.


## Autor

* **Hamilton Lombardi** - *Commits iniciais* - [hclombardi](https://github.com/hclombardi)

