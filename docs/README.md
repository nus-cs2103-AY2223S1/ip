# User Guide

## Features 

### Adding tasks

You can add different types of tasks: **todos**, **deadlines** and **events**.

### Deleting tasks

You can easily delete the tasks that you don't need anymore.

## Usage

### `todo` - Adds a todo

Adds a todo to your task list.

Example of usage: 

`todo homework`

Expected outcome:

A todo named "homework" will be added to your task list.

```
Okay, I've added this task:
[T][ ] homework
You now have 1 tasks.
```
### `deadline` - Adds a deadline

Adds a deadline to your task list with a given due date.

Example of usage:

`deadline assignment /by 2022-02-03`

Expected outcome:

A deadline named "assignment" will be added to your task list, with its deadline as Feb 3 2022.

```
Okay, I've added this task:
[D][ ] deadline (by: Feb 3 2022)
You now have 2 tasks.
```

### `event` - Adds an event

Adds an event to your task list with the date it will take place.

Example of usage:

`event exam /at 2022-09-10`

Expected outcome:

An event named "exam" will be added to your task list, with its date as Sep 10 2022.

```
Okay, I've added this task:
[E][ ] exam (at: Sep 10 2022)
You now have 3 tasks.
```

### `list` - Views list of tasks

Views the list of all current tasks that you have.

Example of usage:

`list`

Expected outcome:

Your current task list.

```
Here are your tasks:
1. [T][ ] homework
2. [D][ ] deadline (by: Feb 3 2022)
3. [E][ ] exam (at: Sep 10 2022)
```

### `mark` - Marks task as done

Marks the task at the given index as done.

Example of usage:

`mark 2`

Expected outcome:

The second task in the task list is marked as done.

```
I've marked this as done:
[D][X] deadline (by: Feb 3 2022)
```

### `unmark` - Unmarks task as done

Unmarks the task at the given index as done.

Example of usage:

`unmark 2`

Expected outcome:

The second task in the task list is marked as done.

```
I've unmarked this as done:
[D][ ] deadline (by: Feb 3 2022)
```

### `delete` - Deletes task

Deletes the task at the given index as done.

Example of usage:

`delete 2`

Expected outcome:

The second task in the task list is marked as done.

```
I'll remove this task:
[D][ ] deadline (by: Feb 3 2022)
You now have 2 tasks.
```