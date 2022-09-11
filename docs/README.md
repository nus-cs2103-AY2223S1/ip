# User Guide

This product is a desktop app for managing tasks mainly through the use of a Command Line Interface (CLI). This application supports the adding and tracking of various types of tasks.

## Features 
- `help` - Viewing help
- `bye` - Exit the program
- `list` - List stored tasks 
- `todo` - Add a "Todo" task
- `deadline`- Add a "Deadline" task 
- `event` - Add an "Event" task
- `mark`- Mark a task as complete
- `unmark` - Mark a task as incomplete
- `delete` - Delete a task
- `find` - Search for a given task

### Feature-XYZ

Description of the feature.

## Usage

### `help` - Viewing Help
Displays a help message.

Alternate command: `h`

Format: `help`

### `bye` - Exit the program
Exits the program after a while.

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

### `deadline` - Add a "Deadline" task 
Adds a task to your task list with deadline at a certain date and time. Dates follow a YYYY-MM-DD format while times follow a HH:MM format.

Alternate command: `d`

Format: `deadline [TASK_NAME] /by [END_DATE] [END_TIME]`

### `event` - Add an "Event" task
Adds a task to your task list with a start date/time and an end date/time. Dates follow a YYYY-MM-DD format while times follow a HH:MM format.

Alternate command: `e`

Format: `event [TASK_NAME] /at [START_DATE] [START_TIME] [END_DATE] [END_TIME]`

### `mark`- Mark a task as complete
Marks a given task as complete.
- The task number to mark must exist in the task list.

Alternate command: `m`

Format: `mark [TASK_NUMBER]`

### `unmark` - Mark a task as incomplete
Marks a given task as incomplete.
- The task number to unmark must exist in the task list.

Alternate command: `um`

Format: `unmark [TASK_NUMBER]`

### `delete` - Delete a task
Deletes a given task from the task list.
- The task number to delete must exist in the task list.

Alternate command: `del`

Format: `del [TASK_NAME]`

### `find` - Search for a given task
Search for tasks that contains the search text.
- The search will return results that contains the search text.
- The search is case sensitive.

Alternate command: `f`

Format: `find [TEXT_TO_SEARCH]`


### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
