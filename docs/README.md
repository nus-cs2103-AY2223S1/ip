# User Guide

Duke is a Personal Assistant Chatbot, optimised for Command Line usage, that helps a person keep track of various
things.

- Quick start
- Features
    - Listing all tasks: list
    - Adding a todo: todo
    - Adding a deadline: deadline
    - Adding an event: event
    - Updating the description of a task: update
    - Marking a task as done: mark
    - Marking a task as undone: unmark
    - Deleting an event from the list: delete
    - Finding tasks by keyword: find
- Command Summary

---

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer
2. Download the latest Duke.jar from here.
3. Go into the folder containing the jar file.
4. Run java -jar Duke.jar to launch the application.
5. Refer to the Features below for details of each command.

---

## Features

```
Notes about the command format:

Words in UPPER_CASE are the parameters to be supplied by the user. e.g. in todo TASK, TASK is a parameter which can be
used as todo cook dinner.
```

### Listing all tasks: `list`

List of all the tasks in your task list.

Format: `list`

Sample usage: `list`

### Adding a todo: `todo`

Add a todo task without any date/time attached to it.

Format: `todo TASK`

Sample usage: `todo Feed the fish`

### Adding a deadline: `deadline`

Add a deadline task that needs to be done before a specific date/time.

Format: `deadline TASK /by DATE`

Sample usage: `deadline Feed the fish /by 2022-09-16`

### Adding a event: `event`

Add an event task that starts at a specific time and ends at a specific time.

Format: `event TASK /at DATE`

Sample usage: `event NUS wellness day /at tomorrow`

### Updating the description of a task: `update`

Update the description of a task (Either todo, deadline or event)

Format: `update TASK_INDEX NEW_DESCRIPTION`

Sample usage: `update 2 Feed the dog`

### Marking a task as done: `mark`

Mark a task as done.

Format: `mark TASK_INDEX`

Sample usage: `mark 1`

### Marking a task as undone: `unmark`

Mark a task as undone.

Format: `unmark TASK_INDEX`

Sample usage: `unmark 2`

### Deleting an event from the list: `delete`

Delete an event from your task list.

Format: `delete TASK_INDEX`

Sample usage: `delete 3`

### Finding tasks by keyword: `find`

Find a task by searching for a keyword.

Format: `find KEYWORD`

Sample usage: `find fish`

---

## Command Summary

| Action   | Format                              |
|----------|-------------------------------------|
| List     | `list`                              |
| Todo     | `todo TASK`                         |
| Deadline | `deadline TASK /by DATE`            |
| Event    | `event TASK /at DATE`               |
| Update   | `update TASK_INDEX NEW_DESCRIPTION` |
| Mark     | `mark TASK_INDEX`                   |
| Unmark   | `unmark TASK_INDEX`                 |
| Delete   | `delete TASK_INDEX`                 |
| Find     | `find KEYWORD`                      |