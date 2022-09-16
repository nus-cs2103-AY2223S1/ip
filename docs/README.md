# User Guide - Duke Task Manager

LUNA is a **desktop app made for students to organise their daily tasks**, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
## Features:
- Add tasks
    - Todo
    - Event
    - Deadline
- Delete tasks
- Mark and Unmark tasks as done
- Give tasks priorities
- List out the current tasks
- Save tasks and Exit

### Adding a Todo task: `todo`
Adds a Todo task.\
Format: `todo TASK`\
Example:
- `todo Homework`
- `todo Tutorial`



### Adding a Event task: `event`
Adds a Event task.\
Format: `event TASK /at YYYY-MM-DD`\
Example:
- `event Dad's Birthday dinner /at 2022-08-29`
- `event Career Fair /at 2022-08-15`


### Adding a Deadline task: `deadline`
Adds a Deadline task.\
Format: `deadline TASK /by YYYY-MM-DD`\
Example:
- `deadline CS2103T iP Submission /by 2022-09-16`
- `deadline SEP Appication /by 2022-09-30`

### Deleting a task: `delete`
Deletes a task at the given `INDEX`.\
Format: `delete INDEX`\
Example:
- `delete 1`

### Mark a task as done: `mark`
Marks a task at the given `INDEX` as done.\
Format: `mark INDEX`\
Example:
- `mark 1`

### Unmark a previously done task: `unmark`
Unmarks a previously marked task at given `INDEX` as undone.\
Format: `unmark INDEX`\
Example:
- `unmark 1`

### Giving priorities to tasks: `priority`
Assigns a priority (`high`, `medium`, `low`) to task at given `INDEX`.\
Format: `priority INDEX PRIORITY`\
Example:
- `priority 1 low`
- `priority 2 medium`
- `priority 3 high`

### List out the current tasks: `list`
Displays the current tasks in the list
Format: `list`

### Save tasks and Exit: `bye`
Saves the current list and exits the app
Format: `bye`