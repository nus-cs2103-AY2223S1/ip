# User Guide

## Features 

### Create tasks

Allows you to create tasks, of different kinds. Namely, todos, deadlines and events

### Mark and un-mark tasks

You're able to keep track of tasks you've done and tasks you've realised need additional work.

### Delete tasks

Delete tasks you're no longer tracking.

## Usage

### `todo` - Adds a todo

The todo command creates a simple task with only a title.

Example of usage: 

`todo (title)`

Expected outcome:

A message informing you of a successful task creation.

```
Got it. I've added this task:
  [T][ ] (title)
Now you have 1 tasks in the list.
```

### `deadline` - Adds a task with a deadline

The todo command creates a simple task with a title and deadline.

Example of usage:

`deadline (title) /by (deadline)`

Expected outcome:

A message informing you of a successful task creation.

```
Got it. I've added this task:
  [D][ ] (title) (by: (deadline) )
Now you have 2 tasks in the list.
```
### `event` - Adds a task with a date

The todo command creates a simple task with a title and date.

Example of usage:

`deadline (title) /at (date)`

Expected outcome:

A message informing you of a successful task creation.

```
Got it. I've added this task:
  [E][ ] (title) (at: (date) )
Now you have 3 tasks in the list.
```

### `list` - Lists out tasks

The list commands informs you of all current tasks.

Example of usage:

`list`

Expected outcome:

A message informing you of all existing tasks.

```
Here are the tasks in your list:
(tasks)
```

### `mark` - Mark a certain task as done

The list commands marks a specified task as done.

Example of usage:

`mark (index)`

Expected outcome:

A message informing you of a successful change.

```
Nice! I've marked this task as done:
  [D][X] (task details)
```
### `unmark` - Mark a certain task as un-done

The list commands marks a specified task as un-done.

Example of usage:

`unmark (index)`

Expected outcome:

A message informing you of a successful change.

```
OK, I've marked this task as not done yet:
  [D][] (task details)
```

### `undo` - Undo/redo the most recent command

The undo command undoes the previous non-undo command, or reverts a reccent
undo.

Example of usage:

`undo`

Expected outcome:

A message informing you of what command was undone and the result.

```
(depends on the previous command)
```