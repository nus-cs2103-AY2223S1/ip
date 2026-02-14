# Duke Aemon of Old — Developer Guide 🛠

This guide is intended for developers who wish to understand, modify, or extend Duke Aemon of Old.

---

## Tech Stack
- Java 17
- JavaFX 21
- Gradle 7.6
- JUnit 5

---

## Project Structure

```
Duke-Aemon/
├── src/main/java
│   ├── duke
│   ├── ui
│   ├── logic
│   ├── storage
│   └── model
├── src/test/java
├── build.gradle
├── gradle/
└── docs/
```

---

## Architecture Overview

The application follows a layered architecture:
- **UI**: JavaFX interface and user interaction
- **Logic**: Command parsing and execution
- **Model**: Task and list representations
- **Storage**: Persistent saving and loading

---

## Build & Run

### Build
```bash
./gradlew build
```

### Run
```bash
./gradlew run
```

---

## Testing

JUnit 5 is used for unit testing.

```bash
./gradlew test
```

---

## JavaFX Configuration

JavaFX is managed using the `org.openjfx.javafxplugin`.  
ARM-native JavaFX binaries are automatically resolved for Apple Silicon.

---

## Extending Duke

To add a new command:
1. Create a new Command class in `logic.commands`
2. Update the parser to recognise the command
3. Add tests for expected behaviour

---

## Coding Standards
- Follows Java coding conventions
- Enforced via Checkstyle

---

## Known Limitations
- Single-user application
- No cloud sync

---

## Future Enhancements
- Recurring tasks
- Task prioritisation
- Export/import functionality
