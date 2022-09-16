# Sally User Guide

Sally is a desktop application as a **daily task manager bot**.

## Features


### Adds `todo` task

- written in`todo DESCRIPTION`, where `DESCRIPTION` is the todo description.
- Example: `todo read book`

### Adds `deadline` task

- written in`deadline DESCRIPTION /by TIME`, where `DESCRIPTION` is the deadline description, and `TIME` is the given deadline.
- `TIME` using dates must be written in the form of `dd-mm-yyyy`, otherwise can be written without any format.
- Example: `deadline do CS2103T quiz /by 16-09-2022` or `deadline do CS2103T quiz /by tonight`

### Adds `event` task

- written in`event DESCRIPTION /at VENUE`, where `DESCRIPTION` is the event description, and `VENUE` is the event venue.
- Example: `event tP meeting /at COM3`

### `list` all tasks

- displays all the task list.
- Example: `list`

### `mark` a task

- marks a task based on the given number.
- written in `mark INDEX` where `INDEX` is the task number to be marked as done.
- Example: `mark 2`

### `unmark` a task

- unmarks a task based on the given number.
- written in `unmark INDEX` where `INDEX` is the task number to be unmarked.
- Example: `unmark 3`

### `delete` a task

- deletes a task based on the given number.
- written in `delete INDEX` where `INDEX` is the task number to be deleted.
- Example: `delete 2`

### `find` a task

- finds a task containing the given keyword.
- written in `find KEYWORD` where `KEYWORD` is the keyword used to filter tasks.
- Example: `find meeting`

### `help`

- displays command lists
- Example: `help`

### say `bye` to Sally

- bid farewell to Sally to save your list
- Example: `bye`
