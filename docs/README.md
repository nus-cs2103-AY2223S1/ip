# User Guide

Duke is a desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can help you keep track of your tasks faster than traditional GUI apps.

## Features 

### Adding a Todo task: `todo`

Adds a Todo task to the list.

Format: `todo DESCRIPTION`

Examples:

- `todo Read book`



### Adding a Deadline task: `deadline`

Adds a Deadline task to the list with a date in `DD-MM-YYYY` and an optional time

Format: `deadline DESCRIPTION /by DATE TIME`

Examples:

- `deadline Buy book /by 10-1-2022`
- `deadline Buy book /by 10-1-2022 2000`



### Adding an Event task: `event`

Adds an Event task to the list.

Format: `deadline DESCRIPTION /at DATE`

Examples:

- `event Party /at Monday 19th Sep`
- `event Study /at 7th October 2022`



### See current tasks: `list`

View the current tasks that you have.

Format: `list`



### Reschedule Deadline tasks: `reshedule`

Reschedule a specific Deadline task based on task number. Note the task rescheduled has be a `Deadline` task.

Format: `reschedule TASK_NUMBER DATE OPTIONAL_TIME`

Examples:
- `reschedule 2 20-2-2022` Reschedules task number 2 with a date of 20th February 2022
- `reschedule 2 20-2-2022 2pm` Reschedules task number 2 with a date of 20th February 2022 and a time at 2pm



### Mark task: `mark`

Marks the task as done based on task number.

Format: `mark TASK_NUMBER`



### Unmark task: `unmark`

Marks the task as undone based on task number.

Format: `unmark TASK_NUMBER`



### Delete task: `delete`

Deletes the task based on task number.

Format: `delete TASK_NUMBER`

Example:
- `delete 2` Deletes task number 2 from the list.



### Find task: `find`

Filters the task list by description and returns tasks with the keyword(s) given.

Format: `find KEYWORD_1 KEYWORD_2 ...`

Examples:
- `find book` Returns task 3 since it has the description book in it.



### Close app: `bye`

Exits the app.

Format: `bye`


## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
