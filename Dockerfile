# Используем базовый образ с Java 21
FROM openjdk:21-jdk

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем собранный JAR файл в контейнер
COPY target/Cook_cakes-0.0.1-SNAPSHOT.jar /app/Cook_cakes.jar

# Указываем порт, который будет открыт в контейнере
EXPOSE 8089

# Задаем аргументы по умолчанию для адреса базы данных, имени пользователя и пароля
ARG DATABASE_URL=jdbc:mysql://localhost:3306/cookcakes
ARG DATABASE_USERNAME=root
ARG DATABASE_PASSWORD=915327

# Команда для запуска приложения при старте контейнера
CMD ["sh", "-c", "\
    java -jar \
    -D spring.datasource.url=$DATABASE_URL \
    -D spring.datasource.username=$DATABASE_USERNAME \
    -D spring.datasource.password=$DATABASE_PASSWORD \
    Cook_cakes.jar"
]
