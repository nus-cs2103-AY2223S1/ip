# User Guide

## Features 

### Different types of tasks

3 different types of tasks can be managed:
- Todo
- Event
- Deadline

### Marking completed tasks

Tasks can be marked as done / not done.

### Searching for specific tasks

A keyword can be used to search through the list of tasks for matching tasks.

## Usage

### `list` - Lists all tasks

Shows a list of all tasks.

Format: `list`

### `todo` - Adds a Todo task

Adds a task that needs to be done in the future.

Format: `todo TASK_DESCRIPTION`

Example of usage:

- `todo read book`

### `deadline` - Adds a Deadline task

Adds a task with a specified deadline.

Format: `deadline TASK_DESCRIPTION /by DATE`

- `DATE` must be in the format `YYYY-MM-DD`.

Example of usage:

- `deadline return book /by 2022-04-20`

### `event` - Adds an Event task

Adds a task happening on a specified date.

Format: `event TASK_DESCRIPTION /at DATE`

- `DATE` must be in the format `YYYY-MM-DD`.

Example of usage:

- `event project meeting /at 2022-08-20`

### `mark` - Mark tasks as done

Marks specified tasks as done.

Format: `mark TASK_INDEX [TASK_INDICES]`

- Marks the task at the specified `TASK_INDEX` as done.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...
- Additional indices separated by whitespace can be specified. All tasks at the specified indices will be marked as done.

Examples of usage:
- `mark 1`
- `mark 2 10 8`

### `unmark` - Mark tasks as not done

Marks specified tasks as not done.

Format: `unmark TASK_INDEX [TASK_INDICES]`

- Marks the task at the specified `TASK_INDEX` as not done.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...
- Additional indices separated by whitespace can be specified. All tasks at the specified indices will be marked as not done.

Examples of usage:
- `unmark 1`
- `unmark 2 10 8`

### `delete` - Deletes tasks

Deletes specified tasks

Format: `delete TASK_INDEX [TASK_INDICES]`

- Deletes the task at the specified `TASK_INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...
- Additional indices separated by whitespace can be specified. All tasks at the specified indices will be deleted.

Examples of usage:
- `delete 1`
- `delete 2 10 8`


### `find` - Finds tasks containing a keyword

Searches the list of tasks for tasks that contain a specified keyword

Format: `find KEYWORD`

Example of usage:
- `find meeting`
- `find club`

### `exit` - Exits the program

Exits the program

Format: `exit`
