# Duke Aemon of Old — User Guide 🏰

Duke Aemon of Old is a simple, lightweight task management application that helps you organise your daily tasks. It supports todos, deadlines, and events, with automatic saving so your data is never lost.

![Duke Aemon UI](https://github.com/Aishwarya-Hariharan-Iyer/ip/blob/master/docs/Ui.png)

---

## Getting Started

### Prerequisites
- **Java 17**
- macOS, Windows, or Linux

### Installation
1. Download the latest `duke.jar` from the GitHub releases page.
2. Verify Java installation:
   ```bash
   java -version
   ```
3. Run the application:
   ```bash
   java -jar duke.jar
   ```

---

## Commands

### `hello`
Greets Duke Aemon and introduces his backstory.

### `help`
Displays all supported commands.

### `todo TASK_DESCRIPTION`
Adds a todo task without date or time.

### `event TASK_DESCRIPTION/yyyy-MM-dd HH:mm HH:mm`
Adds an event with date and start/end times.

### `deadline TASK_DESCRIPTION/yyyy-MM-dd HH:mm`
Adds a task with a deadline.

### `delete TASK_NUMBER`
Deletes a task permanently.

### `mark TASK_NUMBER`
Marks a task as completed.

### `unmark TASK_NUMBER`
Marks a task as incomplete.

### `sort`
Sorts tasks by date, time, and description.

### `find KEYWORD`
Finds tasks containing the keyword.

### `list`
Displays all tasks.

### `bye`
Exits the application safely.

---

## Data Storage
All changes are saved automatically after every command.
