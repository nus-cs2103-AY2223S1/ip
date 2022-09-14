# User Guide

Primer is a app that helps you to manage tasks, deadlines and events.

--------------------------------------------------------------------------------------------------------------------

## Features 

- [x] Manages your todos, deadlines and events.
- [x] Mark or unmark them as completed
- [x] Sort your deadlines/events chronologically.

## Usage

### `help` - View help

help would tell you the different commands available.

Use help COMMAND to check how to use the command.

Examples:

- `help find`
- `help unmark`

Expected outcome:

```
find: Find tasks in the task list.
Parameters: DESCRIPTION. Example: find book
```

```
unmark: Mark a task as not done.
Parameters: INDEX. Example: unmark 2
```

### `deadline` - Creates a new deadline

Adds a new deadline to the tasklist and show you the new updated list

Example of usage: 

`deadline return book /by 2022-08-22`

Expected outcome:

Shows you the new updated task list.

```
I shall add this task.
[D][] return book (by: Aug 22 2022 0000)
Now you have 1 tasks in the list.
```
