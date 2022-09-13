# Duke User Guide

![UI screenshot](./Ui.png)

## What does it do?

Keeps track of your tasks and whether they are done, optionally with a due date or time range!

## Usage

Simply enter one of the following commands into the text box at the bottom, then hit Send or press Enter. Your command and its response will appear as a balloon above!

*Words written \[within square brackets\] are placeholders to be filled in with your own data.*

### `todo [name]` - Add a todo

Add a todo with a given name.

Example:

`todo Buy milk`

Response:

```
Got it. I've added this task:
[T][ ]  Buy milk
Now you have 2 tasks in the list.
```

### `deadline [name] /by [date]` - Add a deadline

Add a task with a given name and deadline date.

Example:

`deadline return book /by Sunday`

Response:

```
Got it. I've added this task:
[D][ ]  return book (by: 20 Jan 2022)
Now you have 2 tasks in the list.
```

### `event [name] /at [timerange]` - Add an event

Add a task with a given name and time range.

Example:

`event project meeting /at Mon 2-4pm`

Response:

```
Got it. I've added this task:
[E][ ]  project meeting (at: Mon 2-4pm)
Now you have 2 tasks in the list.
```

### `list` - List all tasks

List all tasks.

Example:

`list`

Response:

```
Here are the tasks in your list:
1. [T][ ]  borrow book
2. [D][X]  return book (by: 30 Aug 2022)
```

### `mark [number]` - Mark task as done

Mark the task with the given number as done. The current number of a task may be found using the `list` command.

Example:

`mark 1`

Response:

```
Nice! I've marked this task as done:
[T][X]  borrow book
```

### `unmark [number]` - Mark task as not done

Mark the task with the given number as not done. The current number of a task may be found using the `list` command.

Example:

`unmark 1`

Response:

```
Alright, I've marked this task as not done yet:
[T][ ]  borrow book
```

### `delete [number]` - Delete task

Delete the task with the given number. The current number of a task may be found using the `list` command.

Example:

`delete 1`

Response:

```
OK, I've deleted this task:
[T][ ]  borrow book
Now you have 1 task in the list.
```

### `find [keywords]` - Delete task

Search for tasks which match all of the given keywords.

Example:

`find book return`

Response:

```
Here are the tasks that match "book", "return":
1. [D][X]  return book (by: 20 Jan 2022)
2. [T][ ]  return new book
```

## Troubleshooting

### Duke starts me over with a new task list, although I already have one

Duke had a problem opening your task list. Make sure that `[working directory]/data/tasks.csv` is present and not damaged.

For now, **do not attempt to add or modify any tasks.** Copy the aforementioned file to another location, then re-add the tasks manually by using the file as a reference. The file is human-readable.