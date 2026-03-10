# Estágio 1: Build (Compilação)
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# Instalando o Maven manualmente (já que você não usa o mvnw)
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://archive.apache.org/dist/maven/maven-3/3.9.8/binaries/apache-maven-3.9.8-bin.zip && \
    unzip apache-maven-3.9.8-bin.zip -d /opt && \
    ln -s /opt/apache-maven-3.9.8 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn && \
    rm apache-maven-3.9.8-bin.zip && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

ENV MAVEN_HOME=/opt/maven
ENV PATH=$MAVEN_HOME/bin:$PATH

# Copia o pom.xml e baixa as dependências (otimização de cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B || true

# Copia o código fonte e compila
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Run (Execução)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copia o JAR gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# O Render define a variável PORT automaticamente
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]