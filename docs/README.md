# User Guide

Duke is a desktop application for managing tasks, optimized for use via a Command Line Interface (CLI),
while still having the benefits of a Graphical User Interface (GUI).

Users can keep track of different types of tasks, such as ***Deadline***, ***Event*** and ***ToDo***.

Users can perform these commands: **deadline**, **event**, **todo**, **mark**, **unmark**, **list**,
**delete**, **find**, **bye**, as well as mass operation of commands.

### Table of Content
- [Quick Start](#quick-start)
- [Features](#features)
  - [Creating a Deadline task: `deadline`](#creating-a-deadline-task-deadline)
  - [Creating a Event task: `event`](#creating-a-event-task-event)
  - [Creating a Todo task: `todo`](#creating-a-todo-task-todo)
  - [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
  - [Marking a task as not done: `unmark`](#marking-a-task-as-not-done-unmark)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding tasks: `find`](#finding-tasks-find)
  - [Mass operation of commands](#mass-operation-of-commands)
  - [Exiting the program: `bye`](#exiting-the-program-bye)

## Quick Start
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest duke.jar from [here](https://github.com/Puakii/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your Duke.
4. Double-click the file or run `java -jar duke.jar` at the directory containing the jar file to start the application.

## Features 

### Creating a Deadline task: `deadline`

Creates a Deadline task.

Format: `deadline TASK_DETAILS /by YYYY-MM-DD`

Example:

`deadline physics assignment /by 2022-10-10`

Output:
```
Got it. I've added this task:
[D][ ] physics assignment (by: Oct 10 2022)
Now you have 1 tasks in the list.
```

### Creating a Event task: `event`

Creates a Event task.

Format: `event TASK_DETAILS /at YYYY-MM-DD`

Example:

`event jake's birthday party /at 2022-10-10`

Output:
```
Got it. I've added this task:
[E][ ] jake's birthday party (at: Oct 10 2022)
Now you have 1 tasks in the list.
```

### Creating a ToDo task: `todo`

Creates a ToDo task.

Format: `todo TASK_DETAILS`

Example:

`todo maths tutorial`

Output:
```
Got it. I've added this task:
[T][ ] maths tutorial
Now you have 1 tasks in the list.
```

### Marking a task as done: `mark`

Marks a task as done.

Format: `mark INDEX`

- The index refers to the index number shown in the task list. 
  The index must be a **positive integer** 1, 2, 3, ...

Example:

`mark 1`

Output:
```
Nice! I've marked this task as done:
[D][X] physics assignment (by: Oct 10 2022)
```

### Marking a task as not done: `unmark`

Marks a task as not done.

Format: `unmark INDEX`

- The index refers to the index number shown in the task list.
  The index must be a **positive integer** 1, 2, 3, ...

Example:

`unmark 1`

Output:
```
OK, I've marked this task as not done yet:
[D][ ] physics assignment (by: Oct 10 2022)
```

### Listing all tasks: `list`

List all tasks in the task list.

Format: `list`

Example:

`list`

Output:
```
Here are the tasks in your list:
1. [D][ ] physics assignment (by: Oct 10 2022)
```

### Deleting a task: `delete`

Deletes a task in the task list.

Format: `delete INDEX`

- The index refers to the index number shown in the task list.
  The index must be a **positive integer** 1, 2, 3, ...

Example:

`delete 1`

Output:
```
Noted. I've removed this task:
[D][ ] physics assignment (by: Oct 10 2022)
Now you have 0 tasks in the list.
```

### Finding tasks: `find`

Finds the tasks that match the keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- More than 1 keyword can be used.

Example:

`find assignment`

Output:
```
Here are the matching tasks in your list:
1. [D][ ] physics assignment (by: Oct 10 2022)
```

### Mass Operation of commands

Key in more than 1 command by adding `@` behind each command

Format: `@COMMAND @MORE_COMMAND`

- More than 1 command can be keyed in.

Example:

`@todo maths tutorial @deadline physics assignment /by 2022-10-10 
 @event jake's birthday party /at 2022-10-10`

Output:
```
Got it. I've added this task:
[T][ ] maths tutorial
Now you have 1 tasks in the list.

Got it. I've added this task:
[D][ ] physics assignment (by: Oct 10 2022)
Now you have 2 tasks in the list.

Got it. I've added this task:
[E][ ] jake's birthday party (at: Oct 10 2022)
Now you have 3 tasks in the list.
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

Example:

`bye`

Output:
```
Bye! Hope you had fun!
```