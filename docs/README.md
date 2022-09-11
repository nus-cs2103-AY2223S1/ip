# User Guide
Duke is a **desktop app for managing tasks via a Command Line Interface** (CLI). 

Below is a list of commands that you can use in the Duke application.

## Viewing help: help
Shows a message explaining how to access the help page.

Format: `help`

## Viewing tasks: list
Prints all the current tasks in the task list.

Format: `list`

## Exiting application: bye
Exits the Duke application.

Format: `bye`

## Adding ToDo task: todo
Adds a ToDo task to the task list.

Format: `todo TASK_DESCRIPTION`

Examples:

- `todo go for a run`
- `todo return books`

## Adding Deadline task: deadline
Adds a Deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by DATE (in YYYY-MM-DD format)`

Examples:

- `deadline finish homework /by 2022-06-06`
- `deadline finish report /by 2022-07-07`

## Adding Event task: event
Adds a Event task to the task list.

Format: `event TASK_DESCRIPTION /at DATE (in YYYY-MM-DD format)`

Examples:

- `event meeting /at 2022-06-06`
- `event watch tv /at 2022-07-07`

## Marking task as done: mark
Marks a task in the task list as done.

Format: `mark LIST_INDEX`

Example:
- `mark 1`

## Marking task as not done: unmark
Marks a task in the task list as not done.

Format: `unmark LIST_INDEX`

Example:
- `unmark 1`

## Deleting task: delete
Deletes a task from the task list.

Format: `delete LIST_INDEX`

Example:
- `delete 3`
