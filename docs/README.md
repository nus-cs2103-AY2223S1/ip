# User Guide

## Quick start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest duke.jar from here.
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Run the jar file to start the app. 
5. Type the command in the command box and press Enter to execute it. 
6. Some example commands you can try:
- `list`: lists all tasks.
- `todo my first task`: adds a todo task called `my first task` to Duke.

## Features 

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo TASK`, `TASK` is a parameter which can be used as `todo read`.


### Adding to-do: `todo`

Adds a todo task to Duke.

Format: `todo TASK`

Example: 
- `todo read`


### Adding deadline: `deadline`

Adds a deadline task to Duke.

Format: `deadline TASK /by YYYY-MM-DD`

Example:
- `deadline 2103T iP /by 2022-09-13`


### Adding event: `event`

Adds an event task to Duke.

Format: `event TASK /at TIME`

Example:
- `event dinner with friends /at this evening 6pm`


### Listing all tasks: `list`

Shows a list of all tasks in Duke.

Format: `list`


### Marking task as done: `mark`

Marks a task as done.

Format: `mark INDEX`
- Marks as done the task at specified `INDEX`. 
- The index refers to the index number shown in the displayed task list. 
- The index **must be a positive integer** 1, 2, 3, …

Example:
- `mark 1`


### Marking task as not done: `unmark`

Marks a task as not done.

Format: `unmark INDEX`
- Marks as not done the task at specified `INDEX`. 
- The index refers to the index number shown in the displayed task list. 
- The index **must be a positive integer** 1, 2, 3, …

Example:
- `unmark 1`


### Deleting a task: `delete`

Deletes the specified task from Duke.

Format: `delete INDEX`
- Deletes the task at specified `INDEX`. 
- The index refers to the index number shown in the displayed task list. 
- The index **must be a positive integer** 1, 2, 3, …

Example:
- `delete 1`


### Locating tasks by name: `find`

Find tasks which names contain given keyword.

Format: `find KEYWORD`
- The search is case-sensitive. e.g 2103t will not match 2103T
- Partial words will be matched. e.g 2103 will match 2103t and 2103T

Example:
- `find 2103`


### Archiving the data: `archive`

Archive data into a Txt file named in the format `yyyyMMddHHmmss`

Format: `archive`


### Saying bye to Duke: `bye`

Says bye to Duke out of courtesy, does not exit the program. Click the 'X' 
    in the top right corner to exit.

Format: 'bye'


### Saving the data
Duke data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.