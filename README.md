# Meower 🐱 — a task-manager chatbot

Meower is a desktop **task-manager chatbot** built by me for the NUS **CS2103 (Software Engineering)** individual project. I started from the SE-EDU *Duke* template and extended it into a cat-themed personal assistant with a JavaFX GUI — you type plain commands and Meower manages your tasks.

![Ui](docs/Ui.png)

## Features
- **Three task types:** todos, deadlines, and events (with dates).
- **Manage tasks:** add, delete, list, and mark / unmark as done.
- **Find:** search tasks by keyword.
- **Save & load:** persist tasks to a log file, or load from a file path you choose.
- **Archiving:** save named task configurations and reload them at will; list or clear archives.
- **JavaFX GUI** with a custom chat-style interface.

## Commands (see the [User Guide](docs/README.md) for exact syntax)
`todo` · `deadline` · `event` · `list` · `mark` / `unmark` · `delete` · `find` · `archive` / `list archive` / `load archive` / `clear` · `bye`

## Tech & engineering practices
- **Java 11**, **JavaFX**, built with **Gradle**
- **JUnit** tests · **Checkstyle** coding standards · assertions · JavaDoc
- Packaged as a runnable **JAR**

## Running it
```bash
./gradlew run
```
…or run the packaged jar: `java -jar ip.jar`

---
*Based on the AddressBook-Level3 / Duke template from the [SE-EDU initiative](https://se-education.org).*
