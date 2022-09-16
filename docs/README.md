# User Guide

## Features 

### Add Task

Duke can add and store todos, events, and deadlines.

### Delete Task

Duke can delete tasks you have previously added.

### List all Tasks

Duke can display all the tasks you have previously added.

### Find Tasks

Duke can search for task(s) using a search term.

### Mark and Unmark Task

Duke can help you to mark a task as done. It can also
unmark them as well.

### Undo Command

Duke can remember the commands you last executed and 
undo them.

## Usage

### `Todo` - Add a todo

Example of usage: 

`todo buy groceries`

Expected outcome:

A new todo with the description "buy groceries" is
added to your list of tasks.

### `Event` - Add an event

Example of usage:

`event my birthday /at 2022-01-01`

Expected outcome:

A new event with the description "my birthday"
occuring at 2022-01-01 is added to your list 
of tasks.

### `Deadline` - Add a deadline

Example of usage:

`deadline draft contract /by 2022-01-01`

Expected outcome:

A new deadline with the description "draft contract"
,which must be done by 2022-01-01, is added to 
your list of tasks.

```
expected output
```

### `Delete` - Delete a task

Example of usage:

`delete 1`

Expected outcome:

The task with index number 1 is deleted from your
list of tasks.

### `List` - Display your list of tasks

Example of usage:

`list`

Expected outcome:

Your list of tasks is displayed neatly.

### `Find` - Search for tasks

Example of usage:

`find buy`

Expected outcome:

Tasks with the phrase "buy" in their description
are displayed neatly.

### `Mark` - Mark a task

Example of usage:

`mark 1`

Expected outcome:

The task with index number 1 is marked as done.
It will be indicated as done in the list view.

### `Unmark` - Unmark a task

Example of usage:

`unmark 1`

Expected outcome:

The task with index number 1 is marked as not done. It will
be indicated as not done in the list view.

### `Undo` - Undo your previous command

Example of usage:

`undo`

Expected outcome:

Your previous command is undone.