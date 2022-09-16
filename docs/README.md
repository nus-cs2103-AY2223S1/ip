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

### `todo` - Add to-do

Add a new to-do task to the task list.

Command format:

`todo DESCRIPTION`

Example of usage:

`todo homework`

Expected outcome:

```
Got it. I've added this task:
    [T][] homework
Now you have 1 task in the list.
```
### `event` - Add event

Add a new event to the task list. Date and time
should be given in the format 'yyyy-mm-dd hhmm'.

Command format:

`event DESCRIPTION /at DATE_AND_TIME`

Example of usage:

`event party /at 2022-10-31 1600`

Expected outcome:

```
Got it. I've added this task:
    [E][] party (at: 31 Oct 2022 04:00PM)
Now you have 2 tasks in the list.
```

### `deadline` - Add deadline

Add a new deadline to the task list. Date and time
should be given in the format 'yyyy-mm-dd hhmm'.

Command format:

`deadline DESCRIPTION /by DATE_AND_TIME`

Example of usage:

`deadline reflection /by 2022-10-10 2359`

Expected outcome:

```
Got it. I've added this task:
    [D][] reflection (by: 10 Oct 2022 11:59PM)
Now you have 3 tasks in the list.
```

### `mark` - Mark task as completed

Marks a task as completed. The task index used
to identify the task refers to the index of the task
in the list of all tasks.

Command format:

`mark TASK_INDEX`

Example of usage:

`mark 1`

Expected outcome:

```
Finally, you did something useful!
 I've marked this task as done:
[T][X] homework
```

### `unmark` - Mark task as not completed

Marks a task as not completed. The task index used
to identify the task refers to the index of the task
in the list of all tasks.

Command format:

`unmark TASK_INDEX`

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][] homework
```

### `find` - Find task with given keyword

Find tasks based on the given keyword. If the
task description contains the keyword, the task
will be shown.

Command format:

`find KEY_WORD`

Example of usage:

`find reflection`

Expected outcome:

```
I found 1 task for "reflection":
T1 [D][] reflection (by: 10 Oct 2022 11:59PM)
```

### `on` - Find tasks with the specified date

Find tasks with the specified date. If an event
or deadline is on that date, it will be shown.
Date should be given in the format 'yyyy-mm-dd'.

Command format:

`on DATE`

Example of usage:

`on 2022-10-31`

Expected outcome:

```
These are the tasks on 2022-10-31:
[E][] party (at: 31 Oct 2022 04:00PM)

You have 1 task on 2022-10-31.
```

### `contact` - Add contact

Adds contact with given name 
and (optional) additional information.
The optional argument is represented as 
'[OPTIONAL_ARGUMENT]'.

Command format:

`contact NAME [/info ADDITIONAL_INFO]`

Example of usage:

`contact jess /info loves tea`

Expected outcome:

```
Wow you actually have friends. Good for you!
I've added this contact:
jess (info: loves tea)
```

### `delete` - Delete task or contact

Deletes the task or contact at the specified 
index. The index will start with 'C' or 'T'
depending on whether the item to be deleted is a 
contact or task. The letter is followed by a number
that indicated the position of the task or contact
in the list.

Command format:

`delete INDEX`

Example of usage:

'delete T2'

Expected outcome:

```
Done! 
[E][] party (at: 31 Oct 2022 04:00PM) has been deleted :(
Now you have 2 tasks left.
```

### `list` - List all tasks and contacts

Retrieves all tasks and contacts.

Command format:

`list`

Example of usage:

`list`

Expected outcome:

```
Tasks:
T1 [T][] homework
T2 [T][] reflection (by: 10 Oct 2022 11:59PM)

You have 2 tasks!

Contacts:
C1 jess (info:loves tea)
```

### `bye` - Exit the program

Exits the program. The current task and
contacts list will be saved, and reloaded
when Gina is restarted again.

Command format:

`bye`

Example of usage:

`bye`

Expected outcome:

```
Bye. Gina Linetti out.
```