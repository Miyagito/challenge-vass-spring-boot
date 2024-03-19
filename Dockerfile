# Utilizamos una imagen base de OpenJDK 17
FROM openjdk:17-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/challenge-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8080 en el contenedor
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]