# User Guide

## Features 

### Create tasks

Allows you to create tasks, of different kinds. Namely, todos, deadlines and events

### Mark and un-mark tasks

You're able to keep track of tasks you've done and tasks you've realised need additional work.

### Delete tasks

Delete tasks you're no longer tracking.

### Undo creation/deletion

Made a mistake? Undo your latest action. Realise that it's actually right? Redo it.

## Usage

### `todo` - Adds a todo

The todo command creates a simple task with only a title.

Example of usage: 

`todo Film a project video`

Expected outcome:

A message informing you of a successful task creation.

```
Got it. I've added this task:
  [T][ ] Film a project video
Now you have 1 tasks in the list.
```

### `deadline` - Adds a task with a deadline

The todo command creates a simple task with a title and deadline. The deadline must be in the format: yyyy-MM-dd

Example of usage:

`deadline Collect laptop /by 2022-10-10`

Expected outcome:

A message informing you of a successful deadline creation.

```
Got it. I've added this task:
  [D][ ] Collect laptop (by: Oct 10 2022 )
Now you have 2 tasks in the list.
```
### `event` - Adds a task with a date

The todo command creates a simple task with a title and date.

Example of usage:

`event Forum /at Wednesday 2-3pm`

Expected outcome:

A message informing you of a successful event creation.

```
Got it. I've added this task:
  [E][ ] Forum (at: Wednesday 2-3pm )
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
1.[T][ ] Film a project video
2.[D][ ] Collect laptop (by: Oct 10 2022)
3.[E][ ] Forum (at: Wednesday 2-3pm)
```

### `mark` - Mark a certain task as done

The list commands marks a specified task as done.

Example of usage:

`mark 2`

Expected outcome:

A message informing you of a successful change.

```
Nice! I've marked this task as done:
  [D][X] Collect laptop (by: Oct 10 2022)
```
### `unmark` - Mark a certain task as un-done

The list commands marks a specified task as un-done.

Example of usage:

`unmark 2`

Expected outcome:

A message informing you of a successful change.

```
OK, I've marked this task as not done yet:
  [D][] Collect laptop (by: Oct 10 2022)
```

### `undo` - Undo/redo the most recent command

The undo command undoes the previous non-undo command, or reverts a recent
undo (redo).

Example of usage:

`undo`

Expected outcome:

A message informing you of that the command was undone and the corresponding message.

```
(if the previous command was unmark)
Nice! I've marked this task as done:
  [D][X] Collect laptop (by: Oct 10 2022)
```