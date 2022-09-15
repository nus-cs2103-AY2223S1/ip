# User Guide

## General Command Format
- Words in `UPPER_CASE` are the parameters to be supplied by the user, e.g. in `todo TODO_DESCRIPTION`, `TODO_DESCRIPTION` is a parameter that can be used as `todo Do laundry`.
- All commands must be in lower case.
- Parameters in square brackets are optional but at least one must be provided. e.g. `\by [YYYYMMDD, HHMM]` can be used as `\by 20220916` or `\by 20220916 2359` or `\by 2359`.
- `YYYYMMDD` refers to the date format used in this application, e.g. `20220904`. Invalid date formats won't be accepted.
- `HHMM` refers to the time format used in this application, e.g. `2359`. Invalid time formats won't be accepted. If the user only specifies the time without any date, the date will be regarded as today's date.
- If parameters are specified for commands that do not take in parameters, the input will be rejected and error message will be shown.

## Commands Summary
- `todo TODO_DESCRIPTION`
- `deadline DEADLINE_DESCRIPTION \by [YYYYMMDD, HHMM]`
- `event EVENT_DESCRIPTION \at [YYYYMMDD, HHMM]`
- `list`
- `mark TASK_INDEX`
- `unmark TASK_INDEX`
- `delete TASK_NUMBER`
- `find KEYWORD`
- `bye`

## Commands

### Add To-Do - `todo`

Adds a To-Do task to the current task list.

Format: `todo TODO_DESCRIPTION`

Example of usage: 

- `todo do laundry`
- `todo meet Prof`

### Add Deadline - `deadline`

Adds a Deadline task to the current task list.

Format: `deadline DEADLINE_DESCRIPTION \by [YYYYMMDD, HHMM]`

Notes:
An error will be shown if the input satisfies one of the conditions below:
- Deadline description and date time not separated by `\by`
- Date and time format is not valid


Example of usage:
- `deadline 2103 submit iP \by 20220916`
- `deadline 3191 submit report \by 2359`
- `deadline 3123 submit presentation \by 20221009`

### Add Event - `event`

Adds an Event task to the current task list.

Format: `event EVENT_DESCRIPTION \at [YYYYMMDD, HHMM]`

Note:
An error will be shown if the input satisfies one of the conditions below:
- Event description and date time not separated by `\at`
- Date and time format is not valid


Example of usage:
- `event 3122 CA1 \at 20220916 1100`
- `event 2103 group project meeting \at 20220918`
- `event dinner with Mike \at 1930`

### List all tasks - `list`

Shows the list containing all tasks.

Format: `list`

Note:
An error will be shown if parameter is given

### Mark a task as done - `mark`

Marks a task of given index in the task list as done.

Format: `mark TASK_INDEX`

Note:
1. `TASK_INDEX` is the index of the task in the list and starts from `1`.
1. An error will be shown if `TASK_INDEX` satisfies one of the conditions below:
- Is not a positive integer
- Exceeds the task list size

Example of usage:
- `mark 1`
- `mark 5`

###  Unmark a task as not done - `unmark`

Unmarks a task of given index in the task list as not done.

Format: `unmark TASK_INDEX`

Note:
1. `TASK_INDEX` is the index of the task in the list and starts from `1`.
1. An error will be shown if `TASK_INDEX` satisfies one of the conditions below:
- Is not a positive integer
- Exceeds the task list size

Example of usage:
- `unmark 3`
- `unmark 8`

### Delete a task - `delete`

Deletes a task in the task list.

Format: `delete TASK_INDEX`

Note:
1. `TASK_INDEX` is the index of the task in the list and starts from `1`.
1. An error will be shown if `TASK_INDEX` satisfies one of the conditions below:
- Is not a positive integer
- Exceeds the task list size

Example of usage:
- `delete 2`
- `delete 6`

### Find task - `find`

Finds a task that matches the keyword given.

Format: `find KEYWORD`

Note:
Only one keyword can be given per one search, e.g. `find eat dorayaki` will search all tasks containing the keyword `eat dorayaki`.

Example of usage:
- `find laundry`
- `find 2103`

### Exit - `bye`

Sends bye to Mew and exits the application.

Format: `bye`

Note:
An error will be shown if parameter is given
