# User Guide

## Features

### Viewing help

Shows a message with all the commands that John recognises.

### Creating a todo

Adds a todo. A todo is a task with only a description.

### Creating a deadline

Adds a deadline. A deadline is a task with a description
and end date.

### Creating an event

Adds an event. An event is a task with a description and
event date.

### Listing all tasks

Lists all the tasks previously added.

### Finding tasks

Find tasks containing a specific keyword.

### Mark task as complete

Mark tasks as complete.

### Unmark task as incomplete

Unmark tasks as incomplete.

### Delete task

Delete specific tasks.


## Usage

- Parameters are specified in `ALLCAPS`.
- Optional parameters are specified within `<OPTIONAL>`
- Commands are not case-sensitive.

### Creates a todo: `todo`

Create a todo task and saves it.

Format: `todo DESCRIPTION`

Example: `todo Hello World`

```
I've added this task!
[T][ ] Hello World
You have 1 task in your list.
```

### Creates a deadline: `deadline`

Create a deadline task and saves it.

Format: `deadline DESCRIPTION /by DATE <TIME>`

Example: `deadline Hello /by 12/9/2022 1500`

```
I've added this task!
[D][ ] Hello (by Sep 12 2022 03:00 PM)
You have 1 task in your list.
```

### Creates an event: `event`

Create a event task and saves it.

Format: `event DESCRIPTION /at DATE <TIME>`

Example: `event World /at 12/9/2022 1500`

```
I've added this task!
[E][ ] World (at Sep 12 2022 03:00 PM)
You have 1 task in your list.
```

### Mark Task: `mark`

Mark a specific task as complete.

Format: `mark INTEGER`

Example: `mark 1`

```
I've marked this task as complete!
[T][X] Hello World
```

### Unmark Task: `unmark`

Mark a specific task as incomplete.

Format: `unmark INTEGER`

Example: `unmark 1`

```
I've unmarked this task!
[T][ ] Hello World
```

### Delete Task: `delete`

Deletes a specific task.

Format: `delete INTEGER`

Example: `delete 1`

```
I've deleted this task!
[T][X] Hello World
You have 0 tasks in your list.
```

### List tasks: `list`

List tasks on a certain date if date is specified.
Otherwise, list all tasks.

Format: `list <DATE>`

Example: `list 12/12/2022`

```
2. [D][ ] Hello (by: Sep 12 2022 03:00 PM)
```

### Find tasks: `find`

Find tasks containing a specific keyword. The keywords
specified are case-sensitive.

Format: `find KEYWORD`

Example: `find Hello`

```
1. [T][ ] Hello
2. [D][ ] Hello (by: Sep 12 2022 03:00 PM)
```

### Exit the program: `bye`

Exits the program.

Format: `bye`

Example: `bye`

```
Goodbye!
```

### Viewing Help: `help`

Shows a help message.

Format: `help`