# User Guide: ChadBot

ChadBot is a **desktop app that helps users keep track of their tasks, optimised for use via a Control Line Interface** (CLI).

## Table of Content

* [Quick Start](#quick-start)

* [Features](#features)

* [Command summary](#command-summary)

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `duke.jar` from [here](https://github.com/jetlfj/ip/releases).

3. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](Ui.png)

4. Type the command in the command box and press Enter to execute it.

5. Refer to the [Features](#features) below for details of each command.

## Features

### Listing all tasks: `list`

Shows a list of all the tasks added.

Format: `list`

Example outcome(s):

```
There are no tasks in your list.
```

```
Here are the tasks in your list:
1. [T][ ] Tutorial 1
2. [D][ ] Lab 2 (by: Aug 25 2022) 
3. [E][ ] Lecture 3 (at: Aug 25 2022) 
```

### Adding a ToDo: `todo`

Adds a task with the provided description.

Format: `todo DESCRIPTION`

Example of usage:

```
todo Tutorial 1
```

Example outcome:

```
Noted. I've added this task: 
[T][ ] Tutorial 1
Now you have 1 task in the list.
```

### Adding a Deadline: `deadline`

Adds a task with the provided description and due date.

Format: `deadline DESCRIPTION /by DATE`

Example of usage:

```
deadline Lab 2 /by 2022-08-25
```

Example outcome:

```
Noted. I've added this task: 
[D][ ] Lab 2 (by: Aug 25 2022)
Now you have 2 tasks in the list.
```

### Adding an Event: `event`

Adds a task with the provided description and start date.

Format: `event DESCRIPTION /at DATE`

Example of usage:

```
event Lecture 3 /at 2022-08-25
```

Example outcome:

```
Noted. I've added this task: 
[E][ ] Lecture 3 (at: Aug 25 2022) 
Now you have 3 tasks in the list.
```

### Marking a task: `mark`

Marks the task with the provided index as completed.

Format: `mark INDEX`

Example of usage:

```
mark 1
```

Example outcome:

```
Noted. I've marked this task as done: 
[T][X] Tutorial 1
```

### Unmarking a task: `unmark`

Marks the task with the provided index as uncompleted.

Format: `unmark INDEX`

Example of usage:

```
unmark 1
```

Example outcome:

```
Noted. I've marked this task as not done yet: 
[T][ ] Tutorial 1
```

### Finding a task: `find`

Finds the task(s) with description containing the provided keyword(s).

Format: `find KEYWORD [MORE_KEYWORDS]`

Example of usage:

```
find Tutorial
```

Example outcome:

```
Here is the matching task in your list: 
[T][ ] Tutorial 1
```

### Prioritising a task: `high`

Sets the task with the provided index to high priority.

Format: `high INDEX`

Example of usage:

```
high 1
```

Example outcome:

```
Noted. I've set this task to high priority: 
[T][ ][!] Tutorial 1
```

### Deleting a task: `delete`

Deletes the task with the provided index.

Format: `delete INDEX`

Example of usage:

```
delete 1
```

Example outcome:

```
Noted. I've removed this task: 
[T][ ] Tutorial 1
Now you have 2 tasks in the list.
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

Task data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Command summary
| Action | Format | Example |
| --- | --- | --- |
| **List** | `list` | `list` |
| **Add ToDo** | `todo DESCRIPTION` | `todo Tutorial 1` |
| **Add Deadline** | `deadline DESCRIPTION /by DATE` | `deadline Lab 2 /by 2022-08-25` |
| **Add Event** | `event DESCRIPTION /at DATE` | `event Lecture 3 /at 2022-08-25` |
| **Mark** | `mark INDEX` | `mark 1` |
| **Unmark** | `unmark INDEX` | `unmark 1` |
| **Find** | `find KEYWORD [MORE_KEYWORDS]` | `find Tutorial` |
| **Prioritise** | `high INDEX` | `high 1` |
| **Delete** | `delete INDEX` | `delete 1` |
| **Exit** | `bye` | `bye` |
