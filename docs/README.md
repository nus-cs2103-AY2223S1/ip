# User Guide

## Features 

### Adding tasks

Three different types of tasks can be added: To Dos, Deadlines, Events.

### Deleting tasks

Tasks can be deleted with ease.

### Finding tasks

You can search for tasks with keywords.

### Displaying tasks

Displays the entire list of tasks, or individual tasks.

### Archiving tasks

Tasks can be archived into a file, as well as loaded from an archive.

## Usage

### `list` - Displays a list

Displays all your tasks in an indexed list.

Example of usage: 

`list`

Expected outcome:

All your tasks will be displayed.

```
here! ur tasks:
1.[T][ ] read book
2.[E][X] swim (at: Feb 3 2022 5-6pm)
3.[T][ ] cs2103t increments
```

### `mark` - Marks a task as done

Marks a task in your task list at the given index.

Example of usage: 

`mark 3`

Expected outcome:

The third task in your task list will be marked as completed.

```
oke this is done now:
[T][X] read book
```
### `unmark` - Marks a task as undone

Unmarks a task in your task list at the given index.

Example of usage: 

`unmark 3`

Expected outcome:

The third task in your task list will be marked as undone.

```
oke this is undone now:
[T][ ] read book
```
### `delete` - Deletes a task

Deletes a task from your task list at the given index.

Example of usage: 

`delete 1`

Expected outcome:

Deletes the first task in the task list and displays the remaining number of tasks.

```
oke i deleted this:
[T][ ] read book
now u have 2 task(s)!
```
### `show` - Displays a task

Shows a task at the given index.

Example of usage: 

`show 2`

Expected outcome:

Prints the second task in the list.

```
Here:
[T][ ] cs2103t increments
```
### `find` - Finds tasks matching a keyword

Finds tasks that contain a keyword.

Example of usage: 

`find read`

Expected outcome:

Displays a list of tasks that match the keyword.

```
here are the matching tasks: 
1.[T][ ] read book
2.[T][ ] read notes
```
### `archive` - Archives a task

Removes a task from your task list and saves it to an archive file.

Example of usage: 

`archive 3`

Expected outcome:

The task is removed and archived.

```
this has been archived:
[T][ ] read notes
```
### `archive all` - Archives all tasks

Puts all tasks into an archive file and clears the current task list.

Example of usage: 

`archive all`

Expected outcome:

The task list is cleared and the archive file is populated.

```
archived all tasks!!
```
### `archive load` - Loads all tasks from the archive

Loads all the archived tasks into the current task list.
If the current task list has items, the loaded tasks will be appended to the back.

Example of usage: 

`archive load`

Expected outcome:

The tasks in the archived file are added to the current task list.

```
now u have 4 task(s)!
```
### `todo` - Adds a todo task

Adds a todo task to the tasklist.

Example of usage: 

`todo read book`

Expected outcome:

A task named 'read book' will be added to the task list.

```
oke i added this:
[T][ ] read book
now u have 4 task(s)!
```
### `deadline` - Adds a deadline task

Adds a task with a deadline in the format of 'YYYY-MM-DD'

Example of usage: 

`deadline submit paper /by 2022-08-25`

Expected outcome:

Adds a deadline task to the task list.

```
oke i added this:
[D][ ] submit paper (by: Aug 25 2022)
now u have 5 task(s)!
```
### `event` - Adds an event task

Adds an event to the tasklist with a date format of 'YYYY-MM-DD'.
The timing can be added in any string format following the date.

Example of usage: 

`event party /at 2022-11-13 2-5pm`

Expected outcome:

Description of the outcome.

```
oke i added this:
[E][ ] party (at: Nov 13 2022 2-5pm)
now u have 5 task(s)!
```