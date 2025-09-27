# Space Project – API Autotests

Автотесты REST API для сервиса `spaces`, с мок-сервером WireMock, валидацией JSON-схем и отчётами через Allure.

---

## 📚 Содержание

- [Описание проекта](#описание-проекта)  
- [Технологии](#технологии)  
- [Предпосылки](#предпосылки)  
- [Запуск локально](#запуск-локально)  
- [Структура проекта](#структура-проекта)  
- [Отчёты](#отчёты)  
- [CI / GitHub Actions](#ci--github-actions)  
- [Будущие улучшения](#будущие-улучшения)  
- [Контакты](#контакты)  

---

## 📝 Описание проекта

Проект предназначен для автотестирования REST API `spaces`.  
Используется:
- WireMock как мок-сервер для эмуляции API  
- RestAssured + JSON Schema Validator для проверки тела ответов  
- Allure для генерации красивых отчётов  
- Тесты пишутся на Java / JUnit 5

Цель: продемонстрировать грамотную архитектуру API-тестов с моками и отчётами, пригодную и для учебных задач, и как стартовый шаблон.

---

## 🛠 Технологии

- Java 17  
- Gradle (Kotlin DSL)  
- JUnit 5 (Jupiter)  
- RestAssured 5.x  
- JSON Schema Validator  
- WireMock  
- Allure (allure-junit5, allure-rest-assured)  
- Git / GitHub  

---

## ✅ Предпосылки

Чтобы запустить проект локально, у тебя должно быть:

- Установлен JDK 17  
- Gradle wrapper (лежит в проекте)  
- Docker & Docker Compose (для WireMock контейнера)  
- (опционально) Allure CLI, если хочешь откладывать отчёты вручную  

---

## 🚀 Запуск проекта локально

1. Запустить WireMock через Docker Compose:

   ```bash
   docker compose up -d wiremock
2. Запустить тесты:
   ```bash

   ./gradlew -Denv=test :modules:api-tests:test

3. Сгенерировать отчет Allure:
   ```bash

   ./gradlew :modules:api-tests:allureReport

4. Открыть отчет в браузере:
   ```bash

   open modules/api-tests/build/reports/allure-report/index.html


Структура проекта:
   ```bash

.
├── modules
│   └── api-tests
│       ├── src/test/java
│       ├── src/test/resources/schemas
│       └── build.gradle.kts
├── wiremock
│   ├── mappings
│   └── __files
├── compose.yml
├── build.gradle.kts (корневой)
└── settings.gradle.kts

schemas/space.json — JSON-схема для Space
wiremock/mappings & __files — правила мока
api-tests — код автотестов + ресурсы

Отчеты: 
Результаты тестов будут в:
modules/api-tests/build/allure-results/

Сгенерированный HTML-отчёт:
modules/api-tests/build/reports/allure-report/index.html
