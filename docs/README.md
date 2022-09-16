# User Guide

UwuBot is **a chatbot that helps you keep track of all your tasks, optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, UwuBot can get your tasks 
management done faster than traditional GUI apps.

## Features

### Viewing all commands: `help`

Shows a message listing all the commands available in the program.

**Command Format:**
- `help`

### Adding a task: `todo` `event` `deadline`

Adds a task of type todo, event, or deadline. 

**Command Format:**
- `todo TASK_DESCRIPTION`
- `event TASK_DESCRIPTION /at DATE_TIME`
- `deadline TASK_DESCRIPTION /by DATE_TIME`

**Examples:** 
- `todo read book`
- `event birthday party /at 2022-07-28 18:00`
- `deadline CS2103T project /by 2022-09-16`

### Listing tasks: `list`
Lists all of your tasks in a message, in the following format:
```
1. [TASK_TYPE][IS_DONE] TASK_DESCRIPTION
2. [TASK_TYPE][IS_DONE] TASK_DESCRIPTION
...
```

**Command Format:**
- `list`

### Marking tasks: `mark` `unmark`
Mark your task as complete or incomplete using `mark` or `unmark` respectively.

**Command Format:**
- `mark INDEX_OF_TASK`
- `unmark INDEX_OF_TASK`

**Examples:**
- `mark 1`
- `unmark 1`

### Deleting tasks: `delete`
Deletes a task from your list.

**Command Format:**
- `delete INDEX_OF_TASK`

**Examples:**
- `delete 1`

### Finding tasks: `find`
Finds tasks that contain a keyword.

**Command Format:**
- `find KEYWORD`

**Examples:**
- `find CS2103T`

### Exiting the program: `bye`
Exits the program.

**Command Format:**
- `delete`

## Command Summary
| Command      | Format, Examples                                                                         |
|--------------|------------------------------------------------------------------------------------------|
| **todo**     | `todo TASK_DESCRIPTION` e.g `todo feed cat`                                              |
| **event**    | `event TASK_DESCRIPTION /at DATE_TIME` e.g `event christmas party /at 2022-12-25 16:00`  |
| **deadline** | `deadline TASK_DESCRIPTION /by DATE_TIME` e.g `deadline CS2100 lab /by 2022-11-16 23:59` |
| **mark**     | `mark INDEX_OF_TASK` e.g `mark 1`                                                        |
| **unmark**   | `unmark INDEX_OF_TASK` e.g `unmark 1`                                                    |
| **delete**   | `delete INDEX_OF_TASK` e.g `delete 1`                                                    |
| **find**     | `find KEYWORD` e.g `find CS2103T`                                                        |
| **list**     | `list`                                                                                   |
| **help**     | `help`                                                                                   |
| **bye**      | `bye`                                                                                    |

