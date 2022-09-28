# User Guide

![Ui](Ui.png)

## Quickstart

> Note: Run the jar in the terminal instead of double-clicking on the jar file!

1. Copy the jar file into an empty folder
1. Open a command window in that folder
1. Run the command java -jar {filename}.jar e.g., java -jar Duke.jar (i.e., run the command in the same folder as the jar file)

## Features 

### Add

Add a todo/event/deadline to the task list.

### List

Lists all tasks in the task list.

### Mark/Unmark

Mark a task as done or not done.

### Delete

Delete a task.

### Find

Find tasks based on date with `on` or description with `find`.

### Bye

Close the application.

## Usage

### `list`

Lists all tasks in the task list.

Example of usage:

`list`

Expected outcome:

![list command demo](demo/list.png)

### `mark`

Marks a selected task as done.

Example of usage:

`mark 3`

Expected outcome:

![mark command demo](demo/mark.png)

### `unmark`

Marks a selected task as not done.

Example of usage:

`unmark 3`

Expected outcome:

![unmark command demo](demo/unmark.png)

### `todo`

Add a todo with a given description.

Example of usage:

`todo description which can have many words`

Expected outcome:

![todo command demo](demo/todo.png)

### `deadline`

Adds a deadline with a given description and due date.

Example of usage:

`deadline gess1025 report /by 2022-09-11`

Expected outcome:

![deadline command demo](demo/deadline.png)


### `event` 

Add an event with the given description and date.

Example of usage:

`event cs2103t lecture /at 2022-09-09`

Expected outcome:

![event command demo](demo/event.png)

### `find`

Find a task based on its description.

Example of usage:

`find cs1101s`

Expected outcome:

![img.png](demo/find.png)

### `on`

Find tasks on a certain date.

Example of usage:

`on 2022-09-16`

Expected outcome:

![on command demo](demo/on.png)

### `delete`

Delete tasks. Supports deleting multiple indexes at once.

Example of usage:

`delete 4 6`

Expected outcome:

![delete command demo](demo/delete.png)

### `bye`

Closes the application.

Example of usage:

`bye`

Expected outcome:

The application is closed.
