# User Guide

## Features 

### Differentiation of Task

Ability to store tasks as `Todo`, `Deadline` and `Event` with different features.

### Search by date

Able to search for `Deadline` by date.

### Search by keyword

Able to search for tasks by keyword.

### Load & Save

Loads and save tasks from and to a file in the local directory.

## Usage

### `bye` - Exits the program

Exits Renaissance Duke without saving.

Shortcut: `b`

Example of usage:

`bye`

Expected outcome:

`Program exits`

---------
### `date` - searches date

Searches for `Deadline` by date, prints out the `Deadline` that matches the date.

Example of usage: 

`date 2019-10-15 1800`

Expected outcome:

A list of `Deadline` that matches the date.

```
These tasks matches the date:
1. [D][ ] return book (by: 18:00, Tue, Oct 15 2019)
```
---------
### `dateline` - Adds a dateline

Adds a deadline task to the list.

Shortcut: `dl`

Example of usage:

`deadline return book /by 2019-10-15 1800`

Expected outcome:

Deadline task is added to the list.

```
Got it. I've added this task:
 [D][ ] return book (by: 18:00, Tue, Oct 15 2019)
Now you have 1 task(s) in the list.
```
---------
### `delete` - Deletes a task

Deletes a task from the list.

Shortcut: `d`

Example of usage:

`delete 3`

Expected outcome:

Specified task is deleted from the list.

```
Noted. I've removed this task:
 [E][ ] project meeting (at: Mon 2-4pm)
Now you have 2 task(s) in the list.
```
---------
### `event` - Adds an event

Adds an event task to the list.

Shortcut: `e`

Example of usage:

`event project meeting /at Mon 2-4pm`

Expected outcome:

An event task is added to the list.

```
Got it. I've added this task:
 [E][ ] project meeting (at: Mon 2-4pm)
Now you have 3 task(s) in the list.
```
---------
### `find` - Find keyword matches

Searches for tasks by keyword, prints out the tasks that matches the keyword.

Shortcut: `f`

Example of usage:

`find book`

Expected outcome:

A list of tasks that matches the keyword.

```
Here are the matching tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: 18:00, Tue, Oct 15 2019)
3. [T][ ] read book
```
---------
### `list` - Lists all tasks

Lists all tasks in the list.

Shortcut: `ls`

Example of usage:

`list`

Expected outcome:

Prints out a list of all tasks in the list.

```
Here are the task(s) in your list:
1.[T][X] borrow book
2.[D][ ] return book (by: 18:00, Tue, Oct 15 2019)
3.[E][ ] project meeting (at: Mon 2-4pm)
```
---------
### `load` - Loads tasks from file

Loads all tasks from a file in the local directory.

Example of usage:

`load`

Shortcut: `ld`

Expected outcome:

All tasks in the file are loaded into the list.

```
Loading...
```
---------
### `mark` - Marks a task

Marks a task as done.

Shortcut: `m`

Example of usage:

`mark 1`

Expected outcome:

The specified task is marked `[X]` at the second column.

```
Nice! I've marked this task as done:
 [T][X] borrow book
```
---------
### `save` - Saves tasks to file

Saves all tasks in the list to a file in the local directory.

Shortcut: `s`

Example of usage:

`save`

Expected outcome:

All tasks in the list are saved to the file.

```
Saving...
```
---------
### `todo` - Adds a todo task

Adds a todo task to the list.

Shortcut: `t`

Example of usage:

`todo borrow book`

Expected outcome:

A todo task is added to the list.

```
Got it. I've added this task:
 [T][ ] borrow book
Now you have 1 task(s) in the list.
```
---------
### `unmark` - Unmarks a task

Unmarks a task `[ ]` at the second column.

Shortcut: `u`

Example of usage:

`unmark 1`

Expected outcome:

The specified task is unmarked `[ ]` at the second column.

```
Nice! I've marked this task as done:
 [T][ ] borrow book
```