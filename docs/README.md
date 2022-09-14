# User Guide

Shiro Task Manager is a desktop app that remembers the tasks you need to do. It is friendly and definitely remembers your tasks clearly!

## Features 

### Create a task

You can create a task of the following types.

1. Todo: This task has a short description of the task.
2. Deadline: This task has a short description of the task, together with a due date.
3. Event: This task has a short description of the task, together with the event date.

### Mark and unmark a task

For each of the task, you can either set it to "Done" or "Not done".

### View all tasks

You can view all the tasks that you have stored.

### Find a task

You can find a task by search for a keyword, or by specifying the date range of the task.

## Usage

> Words that are in \<CAPS\> are variables that needs to be filled in.



### `todo` - Create a Todo task

> Creates a new Todo task with the given description.

Format: 

`todo <DESCRIPTION>`

Example:

`todo Buy a new headset`



### `deadline` - Create a Deadline task

Creates a new Deadline task with the given description and due date.

Format: 

`deadline <DESCRIPTION> /by yyyy-MM-dd HHmm`

Example:

`deadline Submit assignment /by 2022-10-11 2359`



### `event` - Create an Event

Creates a new Event with the given description and event date.

Format: 

`event <DESCRIPTION> /at yyyy-MM-dd HHmm`

Example:

`event Attend carnival /at 2022-10-13 1200`



### `list` - List all tasks

List all the tasks remembered by Shiro.

Format: 

`list`



### `mark` - Mark a task as "Done"

Marks a task as "Done" to show that it is completed.

Format: 

`mark <INDEX>`

Example:

`mark 1`



### `unmark` - Mark a task as "Not done"

Marks a task as "Not done" to show that it is not completed.

Format: 

`unmark <INDEX>`

Example:

`unmark 1`



### `delete` - Deletes a task

Deletes a task so Shiro will not remember it anymore.

Format: 

`delete <INDEX>`

Example:

`mark 1`



### `find` - Finds the related tasks

Find tasks that contain the given keyword in their description.

Format: 

`find <KEYWORD>`

Example:

`find homework`



### `between` - Find tasks between two dates

Find tasks with their due date or event date occurring within the given date range.

Format: 

`between yyyy-MM-dd HHmm yyyy-MM-dd HHmm`

Example:

`between 2022-09-01 0000 2022-10-15 2359`



### `snooze` - Snooze the task by a day

Push back the deadline or event date by 1 day.

Format: 

`snooze <INDEX>`

Example:

`snooze 1`



### `bye` - Exits the program

Says bye to Shiro and exits the program.

Format: 

`bye`



theme: Cayman