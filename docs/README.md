# User Guide

## Features

### `todo XXX` - Adds a todo task 

Adds a new ToDo task to the Duke's tasklist.

Example of usage: 

`todo project`

### `deadline XXX /by DD/MM/YYYY HHMM` - Adds a deadline task

Adds a new Deadline task to the Duke's tasklist.

The date and time must be in the format DD/MM/YYYY HHMM (24 hour).

Example of usage:

`deadline homework /by 20/12/2022 1800`

### `event XXX /at DD/MM/YYYY HHMM` - Adds a event task

Adds a new Event task to the Duke's tasklist.

The date and time must be in the format DD/MM/YYYY HHMM (24 hour).

Example of usage:

`event meeting /at 20/11/2022 1100`

### `delete i` - Delete task at index i

Deletes a task in Duke's taskList.
The index i refers to the index number shown in the displayed tasklist. 
Index i range = [1, total tasks in taskList].

Example of usage:

`delete 1` deletes the first task in Duke's taskList.

### `mark i` - Mark task at index i as done

Mark the specified task in Duke's taskList as done.
The index i refers to the index number shown in the displayed tasklist.
Index i range = [1, total tasks in taskList].

Example of usage:

`mark 1` mark the first task in Duke's taskList.

### `unmark i` - Unmark task at index i as not done

Unmark the specified task in Duke's taskList as not done.
The index i refers to the index number shown in the displayed tasklist.
Index i range = [1, total tasks in taskList].

Example of usage:

`unmark 1` unmark the first task in Duke's taskList.

### `find XXX` - Find the tasks containing XXX

Find tasks in Duke's taskList that contains XXX.

Example of usage:

`find project` returns tasks containing "project" in the description.

`find 12 Dec` returns tasks with "12 Dec" as their dates.

### `count completed` - Get the number of completed tasks.

Get the number of completed tasks in Duke's taskList.

### `count uncompleted` - Get the number of uncompleted tasks.

Get the number of uncompleted tasks in Duke's taskList.

### `bye` - Exit the program

Exit the program.

### `list` - Display taskList

Show a list of all the tasks in Duke's taskList.
