# User Guide

Froggy is a chat-bot like desktop app for managing your tasks, optimized for use via a
Command Line Interface**(CLI).

## Features 

### Listing all tasks: `list`
Shows all the tasks that you have on your Todo List.
Format: `list`

### Adding a ToDo task: `todo`
Adds a Todo task to your tasks.
Format: `todo TASK_NAME`
Examples: 
- `todo borrow book` creates a task called borrow book

### Adding a Deadline task: `deadline`
Adds a Deadline task to your tasks which is a task that needs to be done before a specific date.
Format: `deadline TASK_NAME /by DATE`
Examples:
- `deadline return book /by 2019-10-15` creates a task called return book with a deadline of `2019-10-15`. 
> Note that the date time should be of a specified format `yyyy-mm-dd` e.g. (`2019-10-15`)

### Adding a Event task: `event`
Adds a Event task to your tasks which is a task that ends at a specific date.
Format: `event TASK_NAME /at DATE`
Examples:
- `event project meeting /at 2019-10-15` creates a task called project meeting which ends at `2019-10-15`.
> Note that the date time should be of a specified format `yyyy-mm-dd` e.g. (`2019-10-15`)

### Marking a task as done: `mark`
Marks a task as done.
Format: `mark INDEX`
Examples: 
- `mark 1` marks the first task in the list as done

### Unmarking task: `unmark`
Unmarks a task, setting it as incomplete.
Format: `unmark INDEX`
Examples:
- `unmark 1` marks the first task in the list as not done

### Deleting a task: `delete`
Deletes a task.
Format: `delete INDEX`
Examples:
- `delete 1` deletes the first task in the list

### Updating a task: `update`
Updates a task
Format:
- `update INDEX /description hello`
- `update INDEX /dateTime DATE`
Examples:
- `update 1 /description hello` updates the description of the first task to be `hello`
- `update 1 /dateTime 15-10-2022` updates the date time of the first task to be `15-10-2022`

### Finding a task: `find`
Finds a task based on the matching keyword
Examples:
- `find borr` finds a task with the description containing the description `borr`

### Goodbye: `bye`
Exits the program
