# User Guide

## Features 

### Keep track of a list of tasks

*Duke* will help the user to keep track of a list of tasks. Tasks can be in the form of `todo`, `event`, or `deadline`.
This list of tasks can be added, deleted and marked as done or undone. If properly triggered, *Duke* will record the
list of tasks when it is closed and load this record when it is opened next time.

## Usage

Please note that all the sample output below are displayed as text sent from the *Duke* in GUI application.

### `list` - List out tasks

By entering the `list` command, *Duke* will print out a list of current tasks *(both marked as done and undone)*.

#### Example

Command: `list`

```
Here are the tasks in your list:
1. [T][ ]: borrow book
2. [D][X]: return book (by: Sunday)
3. [E][ ]: project meeting (at: Mon 2-4pm)
```

### Add new Task

Depending on the nature of the task, you can add `Todo`, `Event` and `Deadline`.

- `Todo`: `todo {description}`. For example, `todo buy milk` will add a new `Todo` with description `buy milk`
to the pending list;
- `Event`: `event {description} /at {time}`. For example, `event party /at 2019-10-10` will add a new `Event` with
description `party` at the date specified as `Oct 10 2019`;
- `Deadline`: `deadline {description} /by {time}`. For example, `deadline homework /by 2019-10-10` will add a new 
`Deadline` with description `homework` at the date specified as `Oct 10 2019`.

Please note that *duke* only supports the time format of `yyyy-MM-dd`. Otherwise, the date cannot be recognised.

#### Example

Command: `todo buy milk`

```
Got it. I've added this task:
[T][ ]: buy milk
```

Command: `event party /at 2019-10-10`

```
Got it. I've added this task:
[E][ ]: party (at: Oct 10 2019)
```

Command: `deadline homework /by 2019-10-10`

```
Got it. I've added this task:
[D][ ]: homework (by: Oct 10 2019)
```

### `mark` - Mark Task done and undone

`mark {index}` will mark the task in the `index` position to be done.
Similarly, `unmark {index}` will mark the task in the `index` position to be undone.

#### Example

Command: `mark 1` assuming the first item is `[T][ ]: buy milk`

```
Nice! I've marked this task as done:
[T][X]: buy milk
```

Command: `unmark 1` after the previous command.

```
Nice! I've marked this task as not done yet:
[T][ ]: buy milk
```

### `delete` - Delete Task

`delete {index}` will delete the task at the `index` position. 

#### Example

Command: `delete 9` assuming the 9-th item is `[T][ ] buy milk`

```
Noted. I've removed this task:
[T][ ]: buy milk
Now you have 8 tasks in the list.
```

### `Find` - Search in list of Tasks

`find {argument}` will find all the tasks with `{argument}` included in its description.

#### Example

Command: `find print` assuming there is one task as `[T][ ]: print CS2100 Lab sheet` and there is no other task
which contains the keyword `print` in its description.

```
Here are the tasks in your list:
1. [T][ ]: print CS2100 Lab sheet
```

### `Help` - Find sample commands

`help` command will get *Duke* to print a list of commands for in-app reference.

### Read and Store task

`bye` command will terminate the program and gets *Duke* to store the current task for usage next time.
When opening the app, *Duke* will automatically load tasks from the previous usage.

#### Example

Command: `bye`

```
Bye. Hope to see you again soon! Please close the app!
```

Afterwards, no matter what input is given to *Duke*, *Duke* will not process and only reply by

```
This program has terminated. No input is taken any more. Please close and reopen program.
```

