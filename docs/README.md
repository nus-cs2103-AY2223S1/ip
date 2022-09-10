# Duke User Guide

Duke is a task management application.

## Features 

| Feature        | Function                   |
|----------------|----------------------------|
| ```help```     | Displays list of commands  |
| ```list```     | Lists all active tasks     |
| ```todo```     | Adds a todo task           |
| ```deadline``` | Adds a deadline task       |
| ```event```    | Adds an event task         |
| ```mark```     | Marks a task as complete   |
| ```unmark```   | Marks a task as incomplete |
| ```delete```   | Deletes a task             |
| ```find```     | Finds a list of task       |


## Usage

### `help` 

Lists all the commands.

Usage format: `help`

Example of usage: 

`help`

Expected outcome:

All the commands

```
List of commands:
help - shows all commands
list - lists tasks
mark <index> - mark task as complete
unmark <index> - mark task as incomplete
todo <description> - creates a todo task
event <description> /at <date> - creates an
event task
deadline <description> /by <date> - creates a
deadline task
find <query> - finds task with query
delete <index> - deletes a task
bye - exits the program
```

## `todo`

Adds a todo task to the task list.

Usage format: `todo <description>`

Example of usage: 
`todo complete iP`

Expected Outcome:

The todo task is created and added to the task list.

```
Got it. I've added this task:
[T][] complete iP
Now you have 1 tasks.
```

## `deadline`

Adds a deadline task to the task list.

Usage format: `deadline <description> /by <yyyy-mm-dd>`

Example of usage:
`deadline complete iP /by 2022-09-10`

Expected Outcome:

The deadline task is created and added to the task list.

```
Got it. I've added this task:
[D][] complete iP (by: Sep 10 2022)
Now you have 2 tasks.
```

## `event`

Adds an event task to the task list.

Usage format: `event <description> /at <yyyy-mm-dd>`

Example of usage:
`event graduation /at 2025-01-01`

Expected Outcome:

The event task is created and added to the task list.

```
Got it. I've added this task:
[E][] graduation (at: Jan 1 2025)
Now you have 3 tasks.
```

## `mark`

Marks a specified task as complete.

Usage format: `mark <index>`

Example of usage:
`mark 1`

Expected Outcome:

The specified task was marked as complete.

```
Nice! I've marked this task as done:
[D][X] complete iP (by: Sep 10 2022)
```

## `unmark`

Marks a specified task as incomplete.

Usage format: `unmark <index>`

Example of usage:
`unmark 1`

Expected Outcome:

The specified task was marked as incomplete.

```
OK. I've marked this task as not done yet:
[D][] complete iP (by: Sep 10 2022)
```

## `delete`

Deletes a specified task.

Usage format: `delete <index>`

Example of usage:
`delete 1`

Expected Outcome:

The specified task is deleted and removed from task list.

```
Noted. I've removed this task:
[D][] complete iP (by: Sep 10 2022)
Now you have 2 tasks.
```

## `find`

Returns a list of tasks that matches the given query.

Usage format: `find <query>`

Example of usage:
`find graduation`

Expected Outcome:

A list of tasks matching the given query is returned.

```
Here are the matching tasks in your list:
1. [E][] graduation (at: Jan 1 2025)
```

## `list`

Lists all the tasks.

Usage format: `list`

Example of usage:
`list`

Expected Outcome:

Displays a list of all the tasks saved.

```
1. [E][] graduation (at: Jan 1 2025)
2. [T][] complete iP
```
