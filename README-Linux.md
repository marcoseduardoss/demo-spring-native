# demo-native

Este projeto demonstra a criação e execução de uma aplicação Spring Boot com suporte nativo usando GraalVM no Linux. O guia a seguir detalha os passos necessários para configurar o ambiente e compilar o projeto, com instruções específicas para Oracle Linux.

## Pré-requisitos

- GraalVM instalado.
- Ferramentas de desenvolvimento (GCC) instaladas.

## Passos para Configuração e Compilação

### 1. Instalação do GraalVM

Instale o GraalVM e configure as variáveis de ambiente necessárias.

1. Baixe e extraia o GraalVM:
    ```bash
    wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-21.3.0/graalvm-ce-java11-linux-amd64-21.3.0.tar.gz
    tar -xzf graalvm-ce-java11-linux-amd64-21.3.0.tar.gz
    ```

2. Adicione o GraalVM ao PATH:
    ```bash
    export PATH=$PATH:/path/to/graalvm-ce-java11-21.3.0/bin
    ```

3. Verifique a instalação:
    ```bash
    gu install native-image
    ```

### 2. Instalação e Configuração do GCC no Oracle Linux

Para compilar o projeto nativo, você precisa ter o GCC instalado e configurado corretamente no Oracle Linux.

1. Instale o GCC:

    ```bash
    sudo yum install gcc gcc-c++
    ```

2. Verifique a instalação:
    ```bash
    gcc --version
    ```

3. Adicione o GCC ao PATH:

    Certifique-se de que o diretório contendo o `gcc` está no seu PATH. Adicione o seguinte ao seu arquivo `~/.bashrc` ou `~/.zshrc`:
    ```bash
    export PATH=/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:$PATH
    ```

    Depois, recarregue seu shell:
    ```bash
    source ~/.bashrc
    # ou
    source ~/.zshrc
    ```

### 3. Configurações do Projeto

Crie um novo projeto Spring Boot com as seguintes dependências:
- **Spring Native**: Necessário para adicionar suporte nativo ao Spring Boot.
- **Spring Web**: Necessário para criar APIs RESTful.

### 4. Atualização do `pom.xml`

Adicione o plugin `native-maven-plugin` ao arquivo `pom.xml` para suporte à compilação nativa. A configuração do `pom.xml` deve incluir:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.graalvm.buildtools</groupId>
            <artifactId>native-maven-plugin</artifactId>
        </plugin>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

### 5. Compilação do Projeto

Execute o comando Maven para compilar o projeto de forma nativa:

```bash
mvn -Pnative native:compile -DskipTests
```

### 6. Execução do Projeto

#### Execução em Java Normal

Para executar a aplicação no modo Java padrão:

```bash
java -jar target/demo-native-0.0.1-SNAPSHOT.jar
```

#### Execução Nativa

Para executar a aplicação compilada de forma nativa:

```bash
./target/demo-native
```

### 7. Comparação de Tempo de Inicialização

- **Java Normal**: 3.283s
- **Nativo**: 0.221s

### Teste de Ping

Acesse `http://localhost:8080/ping` para verificar se a aplicação está funcionando corretamente.