# User Guide

## Features 

### Manages your tasks

Add or remove tasks, marking them when you are done.

### Finds your tasks easily

Finds tasks using keywords.

### Finds tasks that are due soon

Sorts the task list based on their deadlines.

### Saves your tasks

Saves your tasks for future uses of Tako.

## Usage

### `todo` - Adds a todo

Adds a todo to the task list.

Example of usage: 

`todo sleep`

Expected outcome:

The task list has a todo with your description and the total number of tasks.

```
added: [T][] sleep
Total tasks: 1
```

### `deadline` - Adds a deadline

Adds a deadline to the task list.

Example of usage: 

`deadline run /by 2022-09-12 02:35`

Expected outcome:

The task list has a deadline with your description, date and time.

```
added: [D][] run (by: Sep 12 2022 02:35)
Total tasks: 2
```

### `event` - Adds an event

Adds an event to the task list.

Example of usage: 

`event run /at 2022-09-12 02:35`

Expected outcome:

The task list has an event with your description, date and time.

```
added: [E][] run (at: Sep 12 2022 02:35)
Total tasks: 3
```

### `list` - Lists all tasks

Lists all tasks in the task list.

Example of usage: 

`list`

Expected outcome:

A list of tasks with their details displayed.

```
1.[T][] sleep
2.[D][] run (by: Sep 12 2022 02:35)
3.[E][] run (at: Sep 12 2022 02:35)
```

### `mark` - Marks a task

Marks a task based on their index.

Example of usage: 

`mark 1`

Expected outcome:

The task is marked as done.

```
marked: [T][X] sleep
```

### `delete` - Deletes a task

Deletes a task from the task list based on their index.

Example of usage: 

`delete 2`

Expected outcome:

The task is no longer in the task list.

```
deleted: [D][] run (by: Sep 12 2022 02:35)
Total tasks: 2
```

### `find` - Finds tasks

Finds tasks with descriptions matching the argument of find.

Example of usage: 

`find run`

Expected outcome:

A list of tasks with `run` in their description is displayed.

```
Here are the matching tasks in your list:
1.[E][] run (at: Sep 12 2022 02:35)
```

### `sort` - Sorts the task list

Sorts the task list either alphabetically or chronologically.
The list is sorted in ascending order by default. 
A second argument `desc` can be provided to sort the list in descending order.

Example of usage: 

`sort alphabet`

Expected outcome:

The task list is sorted alphabetically.

```
1.[E][] run (at: Sep 12 2022 02:35)
2.[T][] sleep
```

Another example of usage:

`sort date desc`

Expected outcome:

The task list is sorted chronologically in descending order.

```
1.[T][] sleep
2.[E][] run (at: Sep 12 2022 02:35)
```

### `bye` - Exits the program

Exits the program

Example of usage: 

`bye`

Expected outcome:

The program exits.
