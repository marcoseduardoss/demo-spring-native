# Criando uma Imagem Docker para Compilar e Executar o Projeto Native

Este guia mostra como criar uma imagem Docker baseada na `oraclelinux:9` que compila e executa o projeto Spring Native.

## Pré-requisitos

- Docker instalado na sua máquina.
- Código fonte do projeto Spring Native (`demo-native`) disponível.

## Estrutura do Projeto

Certifique-se de que a estrutura do seu projeto esteja organizada da seguinte forma:

```
demo-native/
│
├── src/
│   ├── main/
│   └── test/
│
├── pom.xml
└── Dockerfile
```

## Criação do Dockerfile

Crie um arquivo `Dockerfile` na raiz do projeto (`demo-native/`) com o seguinte conteúdo:

```Dockerfile
# Utiliza a imagem base oraclelinux:9
FROM oraclelinux:9

# Instala as dependências necessárias
RUN yum update -y && \
    yum install -y gcc gcc-c++ wget unzip zlib-devel

# Instala o Maven manualmente
RUN wget https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz && \
    tar -xvzf apache-maven-3.8.6-bin.tar.gz && \
    mv apache-maven-3.8.6 /opt/apache-maven && \
    ln -s /opt/apache-maven/bin/mvn /usr/bin/mvn && \
    rm apache-maven-3.8.6-bin.tar.gz

# Instala o GraalVM
RUN wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.0/graalvm-ce-java17-linux-amd64-22.3.0.tar.gz && \
    tar -xzf graalvm-ce-java17-linux-amd64-22.3.0.tar.gz && \
    mv graalvm-ce-java17-22.3.0 /usr/lib/graalvm && \
    rm graalvm-ce-java17-linux-amd64-22.3.0.tar.gz

# Configura o GraalVM no PATH
ENV PATH="/usr/lib/graalvm/bin:$PATH"

# Instala o Native Image
RUN echo "hosts: files dns" > /etc/nsswitch.conf && \
    gu install native-image

# Define o diretório de trabalho
WORKDIR /app

# Copia o código fonte para o diretório de trabalho
COPY . .

# Compila o projeto usando Maven para gerar o executável nativo
RUN mvn -Pnative native:compile -DskipTests

# Lista os arquivos no diretório target para verificar o nome do arquivo gerado
RUN ls -l target

# Define o comando padrão para rodar a aplicação (ajustar após verificar o nome do arquivo gerado)
CMD ["./target/demo-native"]
```

## Construção da Imagem Docker

Navegue até o diretório do projeto (`demo-native/`) e execute o seguinte comando para construir a imagem Docker:

```bash
docker build -t demo-native-image .
```

## Execução do Container

Depois que a imagem Docker for construída, você pode executar um container a partir dela com o seguinte comando:

```bash
docker run --rm -p 8080:8080 demo-native-image
```

Este comando faz com que o container exponha a porta 8080, permitindo que você acesse a aplicação através de `http://localhost:8080`.

## Verificação

Acesse `http://localhost:8080/ping` para verificar se a aplicação está funcionando corretamente dentro do container Docker.