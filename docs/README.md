# User Guide for Duke

Duke is a user-friendly Command Line Interface chat bot that keeps track of your
daily tasks. It is free to use and simple to understand. Just follow the commands
below :)

## Features 

### Adds a new todo task - `todo`

Adds a task without any date/time attached.

Format: `todo TASK_NAME`

Example:

`todo Chemistry Homework`

### Adds a new deadline - `deadline`

Adds a task that has to be done before a specific date.

Format: `deadline TASK_NAME /by YYYY-MM-DD`

Example:

`deadline Math Assignment /by 2022-10-23`

### Adds a new event - `event`

Adds a event at specific date.

Format: `event TASK_NAME /at YYYY-MM-DD`

Example:

`event Orientation /at 2023-02-08`

### Marks a task - `mark`

Marks a task using its index as done by indicating a cross 'X' in the box.

Format: `mark INDEX`

Example:

`mark 2`

Expected outcome:

Marks the 2nd task as completed, as shown by the cross in the second box.

```
Nice! I've marked this task as done:
[D][X] Math Assignment (by: Oct 23 2022)
```

### Unmarks a task - `unmark`

Unmarks a task using its index by indicating removing the cross 'X' in the box.

Format: `unmark INDEX`

Example:

`unmark 1`

Expected outcome:

Unmarks the 1st task as completed, as shown by the lack of cross in the second box.

```
OK, I've marked this task as not done yet:
[T][ ] Chemistry Homework
```

### Deletes a task - `delete`

Deletes a task using its index.

Format: `delete INDEX`

Example:

`delete 3`

Expected outcome:

Deletes the 3rd task in the task list. Shows the task that was deleted, and the remaining number
of tasks left in the task list.

```
Noted. I've removed this task:
[T][X] Physics Presentation
Now you have 2 tasks in the list.
```

### Lists the tasks - `list`

Lists all tasks that are currently in the task list. Details such as index, type and status of
task is shown.

Format: `list`

Example:

`list`

Expected outcome:

Shows the current saved tasks in the list.

```
1. [T][ ] Math Homework
2. [D][X] Coding Assignment (by: Oct 10 2022)
```

### Finds all tasks with keyword - `list`

Lists all tasks that include the keyword specified.
Offers high flexibility in search, where tasks can be found even if
keyword matches the tasks partially.

Format: `find KEYWORD`

Example:

`find Chemistry` will return `[T][ ] Chemistry Test` and
`[D][ ] Chemistry Homework (by: Oct 23 2022)`

### Terminate the program - `bye`

Terminates the program.

Format: `bye`