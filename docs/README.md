# User Guide

## Features 

### Add tasks of various types 📃

Skyler provides support for tracking three types of tasks:
1. `todo`: simple task without any date/time attached to it e.g. buy groceries
2. `deadline`: task that needs to be _done before_ a specific date/time e.g. submit assignment by 30/11/2022 5pm
3. `event`: task that _occurs at_ a specific time e.g. project meeting at 15/09/2022 3pm

### Reschedule tasks ⏱

Entered the wrong date/time for a deadline or an event? 

Reschedule tasks by specifying the index of the task and the new date/time

### Mark tasks as done/not done ✔

Easily keep track of task status by marking each task as complete/incomplete

### Find tasks 🔍

Look for a task by specifying a keyword

## Usage

### `todo` - Creates a simple task

Creates a simple ToDo task and adds it to the task list.

Example of usage: 

`todo buy groceries`

Expected output:
```
I've added the following task:
  [T][] buy groceries
Total number of tasks: 1
```

Skyler responds with the added todo task.

### `deadline` - Creates a task that needs to be completed by a specified date/time

Creates a task with a deadline and adds it to the task list.

Example of usage:

`deadline submit assignment /by 2022-11-30 2359`

Expected output:
```
I've added the following task:
  [D][] submit assignment (by: Nov 30 2022 11:59PM)
Total number of tasks: 2
```

Skyler responds with the added deadline.

### `event` - Creates a task that occurs at a specified date/time

Creates a task that occurs at a specific time and adds it to the task list.

Example of usage:

`event fundraiser /at 2022-12-25 1800`

Expected output:
```
I've added the following task:
  [E][] fundraiser (at: Dec 25 2022 6:00PM)
Total number of tasks: 3
```

Skyler responds with the added event.

### `list` - Lists all existing tasks

Example of usage:

`list`

Expected output:
```
Tasks:
1.[T][] buy groceries
2.[D][] submit assignment (by: Nov 30 2022 11:59PM)
3.[E][] fundraiser (at: Dec 25 2022 6:00PM)
```

Skyler responds with all existing tasks in the list.

### `reschedule` - Reschedules the date/time of a deadline or event

Change the date/time of a particular deadline or event at the specified INDEX.

Example of usage:

`reschedule 2 2022-10-30 2359`

Expected output:
```
The following task has been rescheduled:
  [D][] submit assignment (by: Oct 30 2022 11:59PM)
```

Skyler changes the date of the deadline task at INDEX 2 to 30 Oct 2022.

### `mark` - Mark a task as completed

Example of usage:

`mark 1`

Expected output:
```
You have completed this task:
  [T][X] buy groceries
```

Skyler marks the task at INDEX 1 as completed.

### `unmark` - Mark a task as not completed

Example of usage:

`unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
  [T][] buy groceries
```

Skyler marks the task at INDEX 1 as not completed.

### `delete` - Deletes a task in the list

Example of usage:

`delete 3`

Expected output:
```
The following task will be removed:
  [E][] fundraiser (at: Dec 25 2022 6:00PM)
Total number of tasks: 2
```

Skyler deletes the task at INDEX 3 from the list.

### `find` - Finds a task in the list by a certain keyword

Example of usage:

`find assignment`

Expected output:
```
Here are the matching tasks in your list:
1.[D][] submit assignment (by: Oct 30 2022 11:59PM)
```

Skyler returns task(s) with a matching keyword.

### `help` - Returns a list of available commands

Returns a list of commands supported by Skyler, and the required format for each command, if any.

Example of usage:

`help`

Expected output:
```
Try entering one of the following commands...
1. todo <task>
2. deadline <item> /by <YYYY-MM-DD> <hhmmm>
3. event <occasion> /at <YYYY-MM-DD> <hhmm>
4. reschedule <INDEX> <YYYY-MM-DD> <hhmm>
5. mark <INDEX>
6. unmark <<INDEX>
7. delete <INDEX>
8. find <keyword>
9. list
10. help
11. bye
```

Skyler returns a list of available commands.

### `bye` - Quits bot

Example of usage:

`bye`

Bot window is closed.