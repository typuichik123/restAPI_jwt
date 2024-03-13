# Rest api + security

При старте проекта в базе данных создаются таблицы с пользователями и ролями для удобства проверки

## Регистрация

При post-запросе на localhost:8080/registration в requstBody необходимо указать данные пользователя в json:

{
"username": "John",
"password": 123,
"confirmPassword": 123,
"email": "Jonh@.com",
"role": "POSTS"
}
В ответ получаем json для аутентификации

## Аутентификация

Полученный json после регистрации вставляем в requstBody post-запроса на localhost:8080/auth
Получаем токен, время жизни которого составляет 10 мин.

## Обработчики

Полученный после аутентификации токен вставляем в Authorization header запроса после Bearer . Далее выполняем
доступные get, post, put, delete-запросы к эндпоинтам;

- localhost:8080/api/users/**
- localhost:8080/api/posts/**
- localhost:8080/api/albums/**
  Пример Authorization header с токеном:
  `
  Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1BPU1RTIl0sInN1YiI6ImoxaG8xbiIsImlhdCI6MTcxMDM1NTQxNSwiZX
  hwIjoxNzEwMzU2MDE1fQ.vjjs-E_yXisR1zuMujkeDE8qBhVFDonK-8Ac2WrXI7M
  `
Результаты работы всех методов проверены через Postman