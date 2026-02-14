# Duke Aemon of Old 🏰

Duke Aemon of Old is a free, lightweight task management application designed to help you bring order to your day. Aemon allows you to add, delete, update, sort, and search tasks with ease, while automatically saving your task list so you can return to it anytime.

The application features a clean graphical interface and supports different task types such as todos, deadlines, and events.

![Duke Aemon UI](https://github.com/Aishwarya-Hariharan-Iyer/ip/blob/master/docs/Ui.png)

---

## 🚀 Getting Started

### Prerequisites
- **Java 17** (recommended)
- macOS, Windows, or Linux

### Installation
1. Download the latest release of **Duke Aemon of Old** from GitHub.
2. Ensure Java is installed by running:
   ```bash
   java -version
   ```
3. Run the application:
   ```bash
   java -jar duke.jar
   ```
4. Start managing your tasks through the application window that opens.

---

## ✨ Features Overview

- Greet Duke and learn his backstory  
- View all available commands  
- Add todo, deadline, and event tasks  
- Delete tasks permanently  
- Mark and unmark tasks as complete  
- Sort tasks by date, time, and description  
- Find tasks using keywords  
- View all stored tasks  
- Exit safely with automatic saving  

---

## 📘 Command Usage Guide

### `hello` — Greet Duke
```
hello
```

### `help` — Display Commands
```
help
```

### `todo` — Add Todo Task
```
todo TASK_DESCRIPTION
```

### `event` — Add Event
```
event TASK_DESCRIPTION/yyyy-MM-dd HH:mm HH:mm
```

### `deadline` — Add Deadline
```
deadline TASK_DESCRIPTION/yyyy-MM-dd HH:mm
```

### `delete` — Delete Task
```
delete TASK_NUMBER
```

### `mark` — Mark Task as Complete
```
mark TASK_NUMBER
```

### `unmark` — Mark Task as Incomplete
```
unmark TASK_NUMBER
```

### `sort` — Sort Tasks
```
sort
```

### `find` — Find Tasks
```
find KEYWORD
```

### `list` — Display Task List
```
list
```

### `bye` — Exit Application
```
bye
```

---

## 💾 Data Storage
All tasks are automatically saved after every command. You can safely close the application and resume later without losing data.

---

## 🛠 Built With
- Java 17
- JavaFX
- Gradle
