# User Guide

## Features

### Managing Tasks

- Add or delete todos/ events/ deadlines.
- Mark or unmark tasks as completed.

### Finding Tasks

- Find tasks with specified keywords.

### Updating Tasks

- Update dates of existing tasks.

## Usage

### `todo` - Adds a todo to your task list.

How to use:

`todo (description of task)`

Example of usage:

`todo Math Homework`

Expected outcome:

```
Got it. I've added this task:
    [T][] Math Homework
Now you have 1 task(s) in the list.
```

### `event` - Adds an event to your task list.

How to use:

`event (description of task) /at (yyyy-mm-dd)`

Example of usage:

`event Math Test /at 2022-09-09`

Expected outcome:

```
Got it. I've added this task:
    [E][] Math Test (at: Sep 9 2022)
Now you have 2 task(s) in the list.
```

### `deadline` - Adds a deadline to your task list.

How to use:

`deadline (description of task) /by (yyyy-mm-dd)`

Example of usage:

`deadline Math Assignment /by 2022-09-09`

Expected outcome:

```
Got it. I've added this task:
    [D][] Math Assignment (by: Sep 9 2022)
Now you have 3 task(s) in the list.
```

### `delete` - Deletes a task from your task list.

How to use:

`delete (index of task)`

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've remove this task:
    [T][] Math Homework
Now you have 2 task(s) in the list.
```

### `mark` - Marks a task from your task list.

How to use:

`mark (index of task)`

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [E][X] Math Test (at: Sep 9 2022)
```

### `unmark` - Unmarks a task from your task list.

How to use:

`unmark (index of task)`

Example of usage:

`unmark 2`

Expected outcome:

```
OK, I've marked this task as not done yet:
    [D][] Math Assignment (by: Sep 9 2022)
```

### `list` - Displays your task list.

How to use:

`list`

Example of usage:

`list`

Expected outcome:

```
Here are the task(s) in your list:
    1. [E][X] Math Test (at: Sep 9 2022)
    2. [D][] Math Assignment (by: Sep 9 2022)
```
### `find` - Finds tasks with specified keyword(s) from your task list.

How to use:

`find (keywords)`

Example of usage:

`find Test`

`find Test Assignment` 

Expected outcome:

```
Here are the matching task(s) in your list:
    1. [E][X] Math Test (at: Sep 9 2022)
```

```
Here are the matching task(s) in your list:
    1. [E][X] Math Test (at: Sep 9 2022)
    2. [D][] Math Assignment (by: Sep 9 2022)
```
### `update` - Updates the date of a task in your task list.

How to use:

`update (index of task) /to (yyyy-mm-dd)`

Example of usage:

`update 1 /to 2020-06-06`

Expected outcome:

```
OK, I've updated this task's date:
    [E][X] Math Test (at: Jun 6 2022)
```

### `bye` - Exits the program.

How to use:

`bye`

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```