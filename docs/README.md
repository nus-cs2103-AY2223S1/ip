# User Guide

## Features

### A virtual to-do list

![Duke user interface](https://tsammeow.github.io/ip/Ui.png)

Add different types of tasks, mark them as completed, delete them, and show a list of your tasks.

### Easy searching

Find a particular task easily by keywords.

### Speedy commands

Command aliases allow you to use commands quickly with minimal typing.

## Usage

### `todo` - Add a new todo item

Alias: `t`

Adds a new todo item into your task list.

Usage:

`todo [description]`

Example:

`todo submit assignment`

Expected outcome:

The todo item is added to your list of tasks.

```
Added new todo: [T][ ] submit assignment
```

### `deadline` - Add a new deadline

Alias: `d`

Adds a new deadline into your task list.

Usage:

`deadline [description] /by [YYYY-MM-DD]`

Example:

`deadline put up christmas tree /by 2022-12-25`

Expected outcome:

The deadline is added to your list of tasks.

```
Added new deadline: [D][ ] put up christmas tree (by: 25 Dec 2022)
```

### `event` - Add a new deadline

Alias: `e`

Adds a new event into your task list.

Usage:

`event [description] /at [YYYY-MM-DD]`

Example:

`event project meeting /at 2022-09-20`

Expected outcome:

The event is added to your list of tasks.

```
Added new event: [E][ ] project meeting (at: 20 Sep 2022)
```

### `list` - List all of your tasks

Alias: `l`

Gets all your tasks and shows them in a list.

Usage: 

`list`

Example:

`list`

Expected outcome:

The list of tasks is shown.

```
1. [T][ ] submit assignment
2. [E][ ] project meeting (at: 20 Sep 2022)
3. [T][X] water plants
```

### `mark` - Mark task as done

Alias: `m`

Marks a given task as done.

Usage:

`mark [task number]`

Example:

`mark 1`

Expected outcome:

The specified task is marked as done.

```
OK, this task is done:
[T][X] submit assignment
```

### `unmark` - Mark task as undone

Alias: `u`

Marks a given task as undone.

Usage:

`unmark [task number]`

Example:

`unmark 3`

Expected outcome:

The specified task is marked as undone.

```
OK, this task is undone:
[T][ ] water plants
```

### `delete` - Delete a task

Deletes a given task.

Usage:

`delete [task number]`

Example:

`delete 3`

Expected outcome:

The specified task is deleted.

```
OK, this task has been deleted:
[T][X] water plants
```

### `find` - Find tasks by keyword

Alias: `f`

Looks for tasks given a keyword.

Usage:

`find [keyword]`

Example:

`find meeting`

Expected outcome:

The list of tasks matching the keyword is shown.

```
2. [E][ ] project meeting (at: 20 Sep 2022)
```

### `bye` - Quit the program

Use this command before quitting the program in order to quit safely.

Usage:

`bye`

Example:

`bye`

Expected outcome:

The program no longer accepts input. You may now close the program.

```
Goodbye!
```
