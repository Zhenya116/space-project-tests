# Space Project – API Auto-тесты

Автотесты REST API для сервиса **spaces**, с мок-сервером WireMock, валидацией JSON схем и отчётами через Allure.

---

## Содержание

- Описание проекта  
- Технологии  
- Предпосылки  
- Запуск локально  
- Структура проекта  
- CI / GitHub Actions  
- Контакты

---

## Описание проекта

Проект предназначен для автоматизированного тестирования REST API `spaces`  
Используются mock’и (WireMock) для эмуляции backend, RestAssured + JSON Schema Validator для проверки ответов, Allure для отчётов.  
Цель: шаблон, который показывает “best practices” для API автотестов.

---

## Технологии

- Java 17  
- Gradle (Kotlin DSL)  
- JUnit 5 (Jupiter)  
- RestAssured 5.x  
- JSON Schema Validator  
- WireMock  
- Allure (allure-junit5, allure-rest-assured)  
- Docker / Docker Compose  
- Git / GitHub

---

## Предпосылки

Для работы локально:

- JDK 17  
- Docker & Docker Compose  
- (опционально) установка Allure CLI

---

## Запуск локально

1. Запустить сервер mock:
   ```bash
   docker compose up -d wiremock

2. Запустить тесты:
   ```bash 
   ./gradlew clean test
3. Сгенерировать отчет:
   ```bash 
   ./gradlew allureReport
4. Открыть отчет:
   ```bash
   open build/reports/allure-report/index.html

