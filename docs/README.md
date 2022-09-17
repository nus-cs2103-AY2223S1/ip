# User Guide

## Features 

### Viewing the List: List

Display current List of tasks

Format: list

### Creating a To-Do Task: todo

Create a to-do task

Format: todo [TASK NAME]

Example:

todo eat breakfast

### Creating a Deadline Task: deadline

Create a task with deadline

Format: deadline [TASK NAME] /by [DEADLINE]

Format of [DEADLINE] is in YYYY-MM-DD HH:MM

Example: 

deadline assignment 1 /by 2021-10-11 23:59

### Creating an Event Task: event

Create an event with start date and time

Format: event [TASK NAME] /at [DEADLINE]

Format of [DEADLINE] is in YYYY-MM-DD HH:MM

Example:

event movie screening /at 2022-10-23 10:00

### Creating a Fixed Duration Task: fixedtask

Create a task with a fixed duration

Format: fixedtask [TASK NAME] /for [DURATION]

No fixed format for [DURATION]

Example:

fixedtask lecture /for 2 hours


### Mark Task: mark

Mark a task as complete

Format: mark [INDEX]

[INDEX] is the integer value representing the task

Example:

mark 1

### Un-mark Task: unmark
Mark a task as incomplete

Format: unmark [INDEX]

[INDEX] is the integer value representing the task

Example:

unmark 1

#### Delete Task: delete

Delete a task

Format: delete [INDEX]

[INDEX] is the integer value representing the task

Example:
delete 2

### Exit: bye
Exits the program

Format: bye
