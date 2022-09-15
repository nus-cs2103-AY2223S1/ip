# User Guide: John Doe
John Doe is a **desktop app which helps users keep track of their to-dos, deadlines and events. It is optimised for use via Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Table of Content
[Quick start](#quick-start)

[Features](#features)

[Command summary](#command-summary)

## Quick start
1. Ensure that you have Java `11` installed on your computer. If not, please install it from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)
2. Download the latest `duke.jar` file
3. To start the app, double-click the file. GUI should look similar to image shown below. <br> ![Ui](Ui.png)
4. Type your command in the text box and hit enter for John Doe to process your command!
5. To find more details about the [features](#Features), refer below.

## Features

### Listing all tasks
Shows a list of all tasks added by user.

Format: `list`

Examples:

```
Here are the tasks in your list:
1. [E][][LOW] CS2101 OP1 Presentation (at: Sep 16 2022)
2. [T][X][HIGH] Memorise script for presentation
```

### Adding ToDos
Adds a todo to list of tasks.

Format: `todo DESCRIPTION`

Examples:

```
todo borrow book
```

Outcome:

```
Got it. I've added this task:
added: [T][][LOW] borrow book
Now you have 1 task in the list.
```

### Adding Deadlines
Adds a deadline to list of tasks.

Format: `deadline DESCRIPTION /by yyyy-mm-dd`

Examples:

```
deadline return book /by 2022-09-15
```

Outcome:

```
Got it. I've added this task:
added: [D][][LOW] return book (by: Sep 15 2022)
Now you have 2 tasks in the list.
```

### Adding Events
Adds an event to list of tasks.

Format: `event DESCRIPTION /at yyyy-mm-dd`

Examples:

```
event party /at 2022-09-16
```

Outcome:

```
Got it. I've added this task:
added: [E][][LOW] party (at: Sep 16 2022)
Now you have 3 tasks in the list.
```

### Marking tasks
Mark tasks with provided index as done.

Format: `mark INDEX`

Examples:

```
mark 1
```

Outcome:

```
Nice! I've marked this task as done:
[T][X][LOW] borrow book
```

### Unmarking tasks
Unmark tasks with provided index as undone.

Format: `unmark INDEX`

Examples:

```
unmark 1
```

Outcome:

```
OK, I've marked this task as not done yet:
[T][][LOW] borrow book
```

### Finding tasks
Finds tasks with description containing search keywords provided.

Format: `find KEYWORD(S)`

Examples:

```
find borrow
```

Outcome:

```
Here is the task in your list.
1. [T][][LOW] borrow book
```

### Prioritising tasks
Sets task with provided index to a certain priority.

Format: `priority INDEX low/medium/high`

Examples:

```
priority 3 high
```

Outcome:

```
Nice! I've changed the priority of this task:
[E][][HIGH] party (at: Sep 16 2022)
```

### Deleting tasks
Deletes task with provided index.

Format: `delete INDEX`

Examples:

```
delete 3
```

Outcome:

```
Noted. I've removed this task:
[E][][HIGH] party (at: Sep 16 2022)
```

### Saving data
Tasks are saved after each command is ran automatically in the hard disk.

## Command summary

| Action | Format | Examples
| --- | --- | --- |
| **list** | `list` | `list` |
| **todo** | `todo DESCRIPTION` | `todo eat` |
| **deadline** | `deadline DESCRIPTION /by yyyy-mm-dd` | `deadline return book /by 2022-09-15` |
| **event** | `event DESCRIPTION /at yyyy-mm-dd` | `event party /at 2022-09-16` |
| **mark** | `mark INDEX` | `mark 1` |
| **unmark** | `unmark INDEX` | `unmark 1` |
| **find** | `find KEYWORD(S)` | `find book` |
| **priority** | `priority INDEX low/medium/high` | `priority 1 high` |
| **delete** | `delete INDEX` | `delete 1` |
