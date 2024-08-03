# demo-native

Este projeto demonstra a criação e execução de uma aplicação Spring Boot com suporte nativo usando GraalVM no Windows. O guia a seguir detalha os passos necessários para configurar o ambiente e compilar o projeto.

## Pré-requisitos

- Visual Studio (precisa do `cl.exe`, o compilador do Microsoft Visual C++).
- GraalVM instalado.

## Passos para Configuração e Compilação

### 1. Instalação do Visual Studio

Antes de compilar, é necessário instalar o Visual Studio e configurar as variáveis de ambiente `INCLUDE`, `LIB` e `PATH`.

- Certifique-se de instalar os componentes necessários, incluindo o compilador C++ (`cl.exe`).

### 2. Configuração do PATH

Após a instalação, adicione o caminho para `cl.exe` ao PATH do sistema. O `cl.exe` está localizado em:
```
C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.40.33807\bin\Hostx64\x64
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

Execute o comando abaixo para definir as variáveis de ambiente necessárias na sessão do `cmd`:

```cmd
C:\desenvolvimento\workspace\pocs\java-native\demo-native\vcvarsall.bat x64
```

Depois, execute o comando Maven para compilar o projeto de forma nativa:

```cmd
mvn -Pnative native:compile -DskipTests
```

### 6. Execução do Projeto

#### Execução em Java Normal

Para executar a aplicação no modo Java padrão:

```cmd
java -jar target\demo-native-0.0.1-SNAPSHOT.jar
```

#### Execução Nativa

Para executar a aplicação compilada de forma nativa:

```cmd
target\demo-native.exe
```

### 7. Comparação de Tempo de Inicialização

- **Java Normal**: 3.283s
- **Nativo**: 0.221s

### Teste de Ping

Acesse `http://localhost:8080/ping` para verificar se a aplicação está funcionando corretamente.