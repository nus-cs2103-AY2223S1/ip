# User Guide

## Features 

### Add tasks

Gina can add a task of one of 3 types:
####1. To-dos
A task with a description and completion status.
####2. Events
A task with a description, completion status 
and date and time of the event.
####3. Deadlines
A task with a description, completion status 
and date and time to complete the task by.

### Delete tasks
Gina can also delete any task whenever you want.

### Keep track of contacts
Gina can add and delete any of your contacts, and
you can also add additional information about the contact.

### List tasks and contacts
Gina allows you to see all your tasks and 
contacts in one place.

### Find tasks
Gina helps you find your tasks by
date or by keyword.

## Usage

### `list` - List all tasks and contacts

Retrieves all tasks and contacts.

Example of usage: 

`list`

Expected outcome:

```
expected output
```
### `todo` - Add to-do

Add a new to-do task to the task list.

Example of usage:

`todo DESCRIPTION`

Expected outcome:

```
expected output
```
### `event` - Add event

Add a new event to the task list. Date and time
should be given in the format 'yyyy-mm-dd hhmm'.

Example of usage:

`event DESCRIPTION /at DATE_AND_TIME`

Expected outcome:

```
expected output
```

### `deadline` - Add deadline

Add a new deadline to the task list. Date and time
should be given in the format 'yyyy-mm-dd hhmm'.

Example of usage:

`deadline DESCRIPTION /by DATE_AND_TIME`

Expected outcome:

```
expected output
```

### `mark` - Mark task as completed

Marks a task as completed. The task index used
to identify the task refers to the index of the task
in the list of all tasks.

Example of usage:

`mark TASK_INDEX`

Expected outcome:

```
expected output
```

### `unmark` - Mark task as not completed

Marks a task as not completed. The task index used
to identify the task refers to the index of the task
in the list of all tasks.

Example of usage:

`unmark TASK_INDEX`

Expected outcome:

```
expected output
```

### `find` - Find task with given keyword

Find tasks based on the given keyword. If the
task description contains the keyword, the task
will be shown.

Example of usage:

`find KEY_WORD`

Expected outcome:

```
expected output
```

### `on` - Find tasks with the specified date

Find tasks with the specified date. If an event
or deadline is on that date, it will be shown.
Date should be given in the format 'yyyy-mm-dd'.

Example of usage:

`on DATE`

Expected outcome:

```
expected output
```

### `contact` - Add contact

Adds contact with given name 
and (optional) additional information.
The optional argument is represented as 
'[OPTIONAL_ARGUMENT]'.

Example of usage:

`contact NAME [/info ADDITIONAL_INFO]`

Expected outcome:

```
expected output
```

### `delete` - Delete task or contact

Deletes the task at the specified index.
The task index used to identify the task
refers to the index of the task
in the list of all tasks.

Example of usage:

`delete TASK_INDEX`

Expected outcome:

```
expected output
```