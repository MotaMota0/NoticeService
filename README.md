

# Task Manager Service
---

## Работа с Git

### 1. Клонирование репозитория
```bash
git clone https://github.com/MotaMota0/NoticeService
cd NoticeService
```

### 2. Создание ветки
Создайте новую ветку для ваших изменений:
```bash
git checkout -b feature/your-feature-name
```

### 3. Добавление изменений
Добавьте изменения в индекс:
```bash
git add .
```

### 4. Коммит изменений
Создайте коммит с описанием:
```bash
git commit -m "Add new feature: task priority"
```

### 5. Отправка изменений
Отправьте ветку на удаленный репозиторий:
```bash
git push origin feature/your-feature-name
```

### 6. Обновление локального репозитория
Получите последние изменения из главной ветки:
```bash
git pull origin main
```

### 7. Удаление ветки
После слияния удалите ветку:
```bash
git branch -d feature/your-feature-name
git push origin --delete feature/your-feature-name
```

---
После успешной открытии проекта используйте папку UserService, там весь исходный код!)

## Описание проекта
Task Manager Service — это RESTful API для управления задачами с использованием ролевой системы (администраторы и пользователи). Сервис предоставляет функционал для создания, обновления, удаления и просмотра задач, а также добавления комментариев к задачам.

### Основные возможности:
- **Регистрация и аутентификация пользователей** через JWT.
- **Управление задачами**:
  - Создание задач с указанием статуса (`PENDING`, `IN_PROGRESS`, `COMPLETED`) и приоритета (`HIGH`, `MEDIUM`, `LOW`).
  - Назначение задач исполнителям.
  - Изменение статуса и приоритета задач.
- **Комментарии к задачам**:
  - Добавление комментариев авторами и исполнителями.
- **Ролевая система**:
  - Администраторы могут управлять всеми задачами.
  - Пользователи могут управлять только своими задачами.

---

## Технологии
- **Backend:** Java 17+, Spring Boot, Spring Security, Spring Data JPA.
- **База данных:** PostgreSQL.
- **Аутентификация:** JWT (JSON Web Token).
- **Документация API:** Swagger/OpenAPI.
- **Тестирование:** Unit-тесты (JUnit, Mockito).

---

## Структура базы данных
База данных состоит из следующих таблиц:

1. **authors**:
   - Хранит информацию о пользователях (авторах и исполнителях).
   - Поля: `id`, `username`, `email`, `pswr` (пароль), `role`.

2. **tasks**:
   - Хранит задачи с указанием статуса (`task_status`) и приоритета (`task_priority`).
   - Поля: `id`, `title`, `description`, `status`, `priority`, `author_id`, `assignee_id`.

3. **comments**:
   - Хранит комментарии к задачам.
   - Поля: `id`, `task_id`, `author_id`, `content`, `created_at`.

---

## Локальный запуск

### Предварительные требования
1. Установленный Docker и Docker Compose.
2. JDK 17+.
3. Maven.

### Инструкции по запуску

1. **Клонируйте репозиторий:**
   ```bash
   git clone https://github.com/your-repo/task-manager.git
   cd task-manager
   ```

2. **Запустите контейнеры через Docker Compose:**
   ```bash
   docker-compose up --build
   ```

3. **Проверьте работу сервиса:**
   - API будет доступно по адресу: `http://localhost:8080`.
   - Swagger UI: `http://localhost:8080/swagger-ui.html`.

4. **Остановите контейнеры:**
   ```bash
   docker-compose down
   ```

---

## Примеры использования API

### 1. Регистрация пользователя
```http
POST /api/auth/register
Content-Type: application/json

{
    "email": "user@example.com",
    "password": "securePassword123"
}
```

### 2. Вход пользователя
```http
POST /api/auth/login
Content-Type: application/json

{
    "email": "user@example.com",
    "password": "securePassword123"
}
```
Ответ:
```json
{
    "token": "your-jwt-token"
}
```

### 3. Создание задачи
```http
POST /api/tasks/createTask
Authorization: Bearer <your-jwt-token>
Content-Type: application/json

{
    "title": "New Task",
    "description": "This is a new task",
    "status": "PENDING",
    "priority": "HIGH"
}
```

### 4. Получение задач автора
```http
GET /api/tasks/author/{authorId}
Authorization: Bearer <your-jwt-token>
```

### 5. Добавление комментария
```http
POST /api/comments/addComment
Authorization: Bearer <your-jwt-token>
Content-Type: application/json

{
    "taskId": 1,
    "content": "This is a comment"
}
```

---

## База данных

### Типы данных
- **task_status:** ENUM (`PENDING`, `IN_PROGRESS`, `COMPLETED`).
- **task_priority:** ENUM (`HIGH`, `MEDIUM`, `LOW`).

### Пример данных
#### Авторы:
| id  | username | email             | pswr          | role    |
|-----|----------|-------------------|---------------|---------|
| 1   | admin    | admin@example.com | hashed_password | ADMIN   |
| 2   | user     | user@example.com  | hashed_password | USER    |

#### Задачи:
| id  | title       | description       | status      | priority | author_id | assignee_id |
|-----|-------------|-------------------|-------------|----------|-----------|-------------|
| 1   | New Task    | This is a new task | PENDING     | HIGH     | 1         | 2           |

#### Комментарии:
| id  | task_id | author_id | content              | created_at          |
|-----|---------|-----------|----------------------|---------------------|
| 1   | 1       | 2         | This is a comment    | 2023-10-01 12:00:00  |

---

## Тестирование

### Unit-тесты
Проект включает Unit-тесты для проверки основных функций сервиса. Для запуска тестов выполните команду:
```bash
mvn test
```

---

## Дополнительно

### Swagger
Swagger UI доступен по адресу:
```
http://localhost:8080/swagger-ui.html
```

### Docker Compose
Файл `docker-compose.yml` настраивает PostgreSQL и само приложение. Все данные базы данных сохраняются в Docker-томе.

create type task_status as ENUM ('PENDING','IN_PROGRESS' ,'COMPLETED');

create type task_priority as ENUM ('HIGH','MEDIUM' ,'LOW');

create table authors(
id bigserial primary key,
username varchar(255) unique not null,
email varchar(255) not null,
pswr varchar(255) not null,
role VARCHAR(50) NOT NULL
);

create table tasks (
id BIGSERIAL primary key  ,
title VARCHAR(255) NOT NULL,
description Text NOT NULL,
status task_status not null ,
priority task_priority not null,
author_id bigint not null,
assignee_id bigint not null,

foreign key(author_id) references authors(id) on delete cascade,
foreign key(assignee_id) references authors(id) on delete set null
);


create table comments(

id bigserial primary key,
task_id bigint not null,
author_id bigint not null ,
content  text not null,
created_at  timestamp  default current_timestamp,
foreign key (task_id) references tasks(id) on delete cascade,
foreign key (author_id) references authors(id) on delete cascade

);
