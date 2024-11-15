# Fulfillment Centers API

## Описание проекта
Fulfillment Centers API - это REST API для управления продуктами в различных центрах выполнения. API позволяет добавлять, обновлять, удалять продукты и центры выполнения, а также получать информацию о продуктах и центрах выполнения.

## Технологии
- Java 17
- Spring Boot 3.3.3
- PostgreSQL
- Spring Data JPA
- Swagger (Springdoc OpenAPI)
- Docker (опционально)

## Запуск проекта

### Шаг 1: Клонирование репозитория
Клонируйте репозиторий проекта с помощью команды:
```bash
$ git clone https://github.com/MylashevTimyr/Fulfillment_Centers.git
$ cd Fulfillment_Centers
```

### Шаг 2: Настройка базы данных
Убедитесь, что у вас установлен PostgreSQL. Создайте базу данных, используя следующую команду:
```sql
CREATE DATABASE ********;
```

### Шаг 3: Настройка конфигурационного файла
Отредактируйте файл `application.yml`, чтобы указать правильные данные для подключения к вашей базе данных:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/*******
    username: ********
    password: ********
```

### Шаг 4: Сборка проекта и запуск
Соберите и запустите проект с помощью Maven:
```bash
$ mvn clean install
$ mvn spring-boot:run
```

После успешного запуска сервер будет доступен по адресу: `http://localhost:8080`

## Основные операции API

### Операции с Центрами Выполнения (Fulfillment Centers)
- **Добавить центр выполнения**: POST `/api/fulfillment-centers`
    - Пример тела запроса:
      ```json
      {
        "name": "Центр №4",
        "location": "Воронеж"
      }
      ```
- **Получить все центры выполнения**: GET `/api/fulfillment-centers`
- **Получить центр выполнения по ID**: GET `/api/fulfillment-centers/{id}`
- **Обновить центр выполнения**: PUT `/api/fulfillment-centers/{id}`
    - Пример тела запроса:
      ```json
      {
        "name": "Обновленный центр",
        "location": "Обновленная локация"
      }
      ```
- **Удалить центр выполнения**: DELETE `/api/fulfillment-centers/{id}`

### Операции с Продуктами (Products)
- **Добавить продукт**: POST `/api/products`
    - Пример тела запроса:
      ```json
      {
        "name": "Помидор",
        "description": "Черри",
        "quantity": 20,
        "status": "Sellable",
        "value": 100,
        "fulfillmentCenter": { "id": 1 }
      }
      ```
- **Переместить продукт**: PUT `/api/products/{productId}/move`
    - Параметры: `targetFulfillmentCenterId`
- **Обновить количество продукта**: PUT `/api/products/{productId}/update-quantity`
    - Параметры: `newQuantity`
- **Обновить статус продукта**: PUT `/api/products/{productId}/update-status`
    - Параметры: `newStatus`
- **Получить все продукты**: GET `/api/products`
- **Фильтровать продукты по статусу**: GET `/api/products/filter?status=Sellable`
- **Получить общую стоимость всех продуктов со статусом "Sellable"**: GET `/api/products/total-value/sellable`
- **Получить общую стоимость продуктов в центре выполнения**: GET `/api/products/total-value/fulfillment-center/{fulfillmentCenterId}`

## Документация API (Swagger)
Для просмотра документации API используйте Swagger UI по адресу:
```
http://localhost:8080/swagger-ui.html
```

## Тестирование
Проект включает unit-тесты для основных операций контроллеров. Для запуска тестов используйте:
```bash
$ mvn test
```

