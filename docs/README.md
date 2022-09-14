# User Guide

## Features 
### Adding tasks
Add either a todo, event (occurs on a specified date) or deadline (due by a specified date) as tasks.

### Listing tasks
Displays a list of all tracked tasks.

### Deleting tasks
Deletes a specific task.

### Finding tasks
Finds tasks using a keyword.

### Marking tasks
Marks a specific task as completed.

### Unmarking tasks
Marks a specific task as incomplete.

### Exiting program
Exits the program.

## Usage
### Adding a task
You can add either a todo, event (occurs on a specified date) or deadline (due by a specified date) as tasks.

#### Adding a todo
Adds a todo.

Format: `todo TASK_DESCRIPTION`

Examples:
- `todo CS2103T tutorial`
- `todo watch movie`

Expected outcome:

Todo added to task list when valid description given.

```
Got it. I've added this task:
[T][ ] TASK_DESCRIPTION
Now you have X tasks in the list.
```

#### Adding a deadline
Adds a deadline.

Format: `deadline TASK_DESCRIPTION /by YYYY-MM-DD`

Examples:

- `deadline return book /by 2022-09-20`
- `deadline submit CS2102 assignment /by 2022-09-17`

Expected outcome:

Deadline added to task list when valid description and date given.

```
Got it. I've added this task:
[D][ ] TASK_DESCRIPTION (by: MMM DD YYYY)
Now you have X tasks in the list.
```
#### Adding an event
Adds an event.

Format: `event TASK_DESCRIPTION /at YYYY-MM-DD`

Examples:

- `event NUS internship day /at 2022-10-30`
- `event NUS Supernova /at 2022-09-22`

Expected outcome:

Event added to task list when valid description and date given.

```
Got it. I've added this task:
[E][ ] TASK_DESCRIPTION (at: MMM DD YYYY)
Now you have X tasks in the list.
```

### Listing all tasks
Displays a list of tasks added.

Format: `list`

Expected outcome:

```
Here are the tasks in your list:
1. ...
2. ...
```

### Deleting a task
Deletes the task from task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- `INDEX` refers to the index number shown after using `list`.

Examples:

- `delete 1` deletes the 1st task in `list` if it exists.

Expected outcome:

Deletes a task when valid index is given.

```
Noted. I've removed this task:
[X][ ] TASK_DESCRIPTION
Now you have X tasks in the list.
```

### Finding specific tasks
Display tasks where the description matches given keywords.

Format: `find KEYWORD`
- Search is case-sensitive. e.g. `homework` will not match with `Homework`
- Partial keywords can be used as well. e.g. `work` will match with `homework`

Examples:
- `find homework` returns a list of all tasks with description containing the keyword `homework`
- `find NUS concert` returns a list of all tasks with description containing the keyword `NUS concert`

Expected outcome:

```
Here are the matching tasks in your list:
1. ...
2. ...
```

### Marking a task
Marks a task as completed.

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as completed.
- `INDEX` refers to the index number shown after using `list`.

Examples:
- `mark 1` marks the first task in `list` if it exists.

Expected outcome:

Marks a task as completed when valid index is given.

```
Nice! I've marked this task as done:
[X][X] TASK_DESCRIPTION
```

### Unmarking a task
Marks a task as incomplete.

Format: `unmark INDEX`
- Marks the task at the specified `INDEX` as incomplete.
- `INDEX` refers to the index number shown after using `list`.

Examples:
- `unmark 1` marks the first task in `list` as incomplete if it exists.

Expected outcome:

Marks a task as incomplete when valid index is given.

```
OK, I've marked this task as not done yet:
[X][ ] TASK_DESCRIPTION
```

### Exiting the program
Exits the program.

Format: `bye`

Expected outcome:

Prints goodbye message and exits the program.

```
Bye. Hope to see you again soon!
```
