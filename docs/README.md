# User Guide

This product is a desktop app for managing tasks mainly through the use of a Command Line Interface (CLI). This application supports the adding and tracking of various types of tasks.

## Features 
- [`help` - Viewing help](#help---viewing-help)
- [`bye` - Exit the program](d#bye---exit-the-program)
- [`list` - List stored tasks](#list---list-stored-tasks)
- [`todo` - Add a "Todo" task](#todo---add-a-todo-task)
- [`deadline`- Add a "Deadline" task](#deadline---add-a-deadline-task)
- [`event` - Add an "Event" task](#event---add-an-event-task)
- [`mark`- Mark a task as complete](https://github.com/NicholasTYD/ip/blob/master/docs/README.md#mark--mark-a-task-as-complete)
- [`unmark` - Mark a task as incomplete](#unmark---mark-a-task-as-incomplete)
- [`delete` - Delete a task](#delete---delete-a-task)
- [`find` - Search for a given task](#find---search-for-a-given-task)

### Feature - Case insensitive keywords

Keywords (E.g `help`, `mark` etc) are case insensitive. Keywords such as `HeLP`, `D`, `TOdo` etc are accepted as valid keywords.

## Usage

### `help` - Viewing Help
Displays a help message.

Alternate command: `h`

Format: `help`

### `bye` - Exit the program
Exits the program after a short delay.

Alternate command: `b`

Format: `bye`

### `list` - List stored tasks 
Lists all the stored tasks

Alternate command: `l`

Format: `list`

### `todo` - Add a "Todo" task
Adds a task to your task list without any specified date.

Alternate command: `t`

Format: `todo [TASK_NAME]`

Examples: 

- `todo Homework`
- `t Household chores`

### `deadline` - Add a "Deadline" task 
Adds a task to your task list with deadline at a certain date and time. 
- Dates follow a YYYY-MM-DD format while times follow a HH:MM format.
- The `/by` flag is case-sensitive.

Alternate command: `d`

Format: `deadline [TASK_NAME] /by [END_DATE] [END_TIME]`

Examples: 

- `deadline Math assignment /by 2022-09-20 13:00`
- `d ip /by 2022-12-20 23:59`

### `event` - Add an "Event" task
Adds a task to your task list with a start date/time and an end date/time.
- Dates follow a YYYY-MM-DD format while times follow a HH:MM format.
- The `/at` flag is case-sensitive.

Alternate command: `e`

Format: `event [TASK_NAME] /at [START_DATE] [START_TIME] [END_DATE] [END_TIME]`

Examples: 

- `event Team Meeting /at 2022-09-12 08:00 2022-09-12 09:30`
- `e CS exam /at 2022-10-12 12:00 2022-10-12 14:00`

### `mark`- Mark a task as complete
Marks a given task as complete.
- The task number to mark must exist in the task list.

Alternate command: `m`

Format: `mark [TASK_NUMBER]`

Examples:

- `mark 5`
- `m 2`

### `unmark` - Mark a task as incomplete
Marks a given task as incomplete.
- The task number to unmark must exist in the task list.

Alternate command: `um`

Format: `unmark [TASK_NUMBER]`

Examples: 

- `unmark 3`
- `um 1`

### `delete` - Delete a task
Deletes a given task from the task list.
- The task number to delete must exist in the task list.

Alternate command: `del`

Format: `del [TASK_NAME]`

Examples:

- `delete 5`
- `del 12`

### `find` - Search for a given task
Search for tasks that contains the search text.
- The search will return results that contains the search text.
- The search is case insensitive.

Alternate command: `f`

Format: `find [TEXT_TO_SEARCH]`

Examples: 

- `find math`
- `f Math assignment`
