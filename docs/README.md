# User Guide
Duke is a desktop app that helps you manage tasks primarily using Command Line Interface (CLI). Duke will support the adding and tracking of various types of tasks.
## Features 
- [`list` - List stored tasks](#list---list-stored-tasks)
- [`todo` - Add a 'Todo' task](#todo---add-a-todo-task)
- [`deadline` - Add a 'Deadline' task](#deadline---add-a-deadline-task)
- [`event` - Add a 'Event' task](#event---add-a-event-task)
- [`mark` - Mark a task as complete](#mark---mark-a-task-as-complete)
- [`unmark` - Mark a task as incomplete](#unmark---mark-a-task-as-incomplete)
- [`delete` - Delete a given task](#delete---delete-a-given-task)
- [`find` - Search for a given task](#find---search-for-a-given-task)
- [`bye` - Exit the program](#bye---exit-the-program)

## IMPORTANT
The file `duke.txt` is saved in `data` folder. 

If any errors are caused when the program runs, ensure that there are no other copies of `duke.txt`.

## Usage

### `list` - List stored tasks

Lists all the stored tasks in the file

Format: `list`

### `todo' - Add a 'Todo' task

Adds a task to task list that does not require a specified date or timing.

Format: `todo [TASK_NAME]`

Examples:
- `todo Go to School`

### `deadline` - Add a 'Deadline' task

Adds a task to task list that needs to be completed within a certain date.
Dates follow a YYYY-MM-DD format.

Format: `deadline [TASK_NAME] /by [END_DATE]`

Examples:
- `deadline SUbmit Assignment 1 /by 2022-10-11`

### `event` - Add a 'Event' task
Add a task to task list with a start date/time and an end date/time

Format: `event [TASK_NAME] /at [START_DATE] [START_TIME] to [END_DATE] [END_TIME]`

Examples:
- `event Formal Hall Dinner /at 2022-11-01 1900 to 2022-11-01 2200`

### `mark` - Mark a task as complete
Marks the task given as complete. Only requirement is that task number to mark must exist within the task list.

Format: `mark [TASK_NUMBER]`

Examples:
- `mark 3`

### `unmark` - Mark a task as incomplete
Marks the task given as incomplete. Only requirement is that task number to unmark must exist within the task list.

Format: `unmark [TASK_NUMBER]`

Examples:
- `unmark 3`

### `delete` - Delete a given task
Deletes the task given from the task list. Only requirement is that task number to delete must exist within the task list.

Format: `delete [TASK_NUMBER]`

Examples:
- `delete 3`

### `find` - Search for a given task
Search for task that contains the keyword that is entered.

Format: `find [KEYWORD]`

Examples:
- `find homework`

### `bye` - Exit the program
Exits the program

format: `bye`





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
