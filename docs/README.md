# User Guide

## Features 

### Adding a todo

Adds a todo to the list.
A todo is a type of task, that only has a description.

### Adding a deadline

Adds a deadline to the list.
A deadline is a type of task, that has to be done by a given date.

### Adding a event

Adds an event to the list.
An event is a type of task, that occurs on a given date.

### Marking a task

Marks a task as done.

### Un-marking a task

Marks a task as not done.

### Deleting a task

Deletes a task from the list.

### Listing all current tasks

Shows the list of all current tasks.

### Locating a task by keyword

Finds all tasks whose description contains any of the given keywords.

### Locating a task by date

Finds all tasks which occur on the given date.

### Saving and exiting the program

Saves the current tasks and exits the program.

## Usage

### Notes about the command format:

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
- e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo example`.

### `todo` - Adds a todo to the list

Adds a todo with a given description to the list.

Example of usage: 

`todo description`

Expected outcome:

A todo with the given description will be added to the list.

```
Got it. I've added this task:
    [T][] description
Now you have 1 task in the list
```

### `deadline` - Adds a deadline to the list

Adds a deadline with a given description and due date to the list.

Date should be in the YYYY-MM-DD format.

Example of usage:

`deadline description /by 2022-09-14`

Expected outcome:

A deadline with the given description and date will be added to the list.

```
Got it. I've added this task:
    [D][] description (by: Sep 14 2022)
Now you have 2 tasks in the list
```

### `event` - Adds an event to the list

Adds an event with a given description and event date to the list.

Date should be in the YYYY-MM-DD format.

Example of usage:

`event description /at 2022-09-14`

Expected outcome:

An event with the given description and date will be added to the list.

```
Got it. I've added this task:
    [E][] description (at: Sep 14 2022)
Now you have 3 tasks in the list
```

### `mark` - Marks a task as done

Marks the task at a given index as done.

Example of usage:

`mark 1`

Expected outcome:

The first task in the list will be marked as done.

```
Nice! I've marked this task as done:
    [T][X] description
```

### `unmark` - Marks a task as not done

Marks the task at a given index as not done.

Example of usage:

`unmark 1`

Expected outcome:

The first task in the list will be marked as done.

```
OK, I've marked this task as not done yet:
    [T][] description
```

### `delete` - Deletes a task from the list

Deletes the task at a given index from the list

Example of usage:

`delete 1`

Expected outcome:

The first task in the list will be deleted.

```
Got it. I've removed this task:
    [T][] description
Now you have 2 tasks in the list
```

### `list` - Shows the list of all current tasks

Shows the list of all current tasks.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
    1. [T][] description
    2. [D][X] description (by: Sep 14 2022)
```

### `find` - Finds matching tasks by keyword

Finds all tasks that have a description containing the given keyword.

Example of usage:

`find gro`

Expected outcome:

Finds all matching tasks.

```
Here are the matching tasks in your list:
1. [T][] pick up groceries
2. [D][] grow plants (by: Sep 25 2022)
```

### `schedule` - Finds matching tasks by date

Finds all tasks that occur or is due on the given date.

Example of usage:

`schedule 2022-09-16`

Expected outcome:

The first task in the list will be marked as done.

```
Here are the scheduled tasks in your list:
    [E][] concert (at Sep 16 2022)
    [D][] homework assignment due (by Sep 16 2022)
```

### `bye` - Saves and exits the program

Saves and exits the program

Example of usage:

`bye`

Expected outcome:

All the current tasks are saved and the program can be exited.

```
Bye. Hope to see you again soon!
```



