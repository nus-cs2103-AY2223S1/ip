# User Guide

## Features

### Add task

Add a task (deadline, event or todo) to the list.

### Delete Task

Delete the task from the list.

### List Tasks

Display the list of task that are currently recorded.

### Find Task
Find a task based on a keyword.

### Mark/Unmark Task

Mark/Unmark a task in the list as completed.

### Search Task

A more flexible version of Find where only a partial word is required.

## Usage

### `todo` - Create a todo task

Create a todo task and add it to the list.

Example of usage:

`todo laundry`

Expected outcome:

Returns a notification that the todo is added.

```
Got it. I've added this task:
[T][ ] laundry
Now you have 1 task in the list.
```

### `deadline` - Create a deadline task

Create a deadline task and add it to the list.

Example of usage:

`deadline homework /by 2022-11-09`

Expected outcome:

Returns a notification that the deadline is added.

```
Got it. I've added this task:
[D][ ] homework (by: Nov 9 2022)
Now you have 2 tasks in the list.
```

### `event` - Create an event task

Create an event task and add it to the list.

Example of usage:

`event concert /at 2022-11-08`

Expected outcome:

Returns a notification that the event is added.

```
Got it. I've added this task:
[E][ ] concert (at: Nov 8 2022)
Now you have 3 tasks in the list.
```
### `list` - Display current task list

Display the list of tasks currently kept track of.

Example of usage:

`list`

Expected outcome:

Returns the list of tasks.

```
Here are your current tasks:
1. [T][ ] laundry
2. [D][ ] homework (by: Nov 9 2022)
3. [E][ ] concert (at: Nov 8 2022)
```

### `mark` - Mark a task as done

Mark a task in the current task list as completed.

Example of usage:

`mark 1`

Expected outcome:

Returns a notification that the task has been marked.

```
Nice! I've marked this task as done:
[T][X] laundry
```

### `unmark` - Unmark a task as done

Unmark a task in the current task list as completed.

Example of usage:

`unmark 1`

Expected outcome:

Returns a notification that the task has been unmarked.

```
OK, I've marked this task as not done yet:
[T][ ] laundry
```

### `find` - Find a task

Find matching tasks in the current task list with a keyword.

Example of usage:

`find laundry`

Expected outcome:

Returns a list of tasks that has matching keyword

```
Here are the matching tasks in your list:
1. [T][ ] laundry
```

### `search` - Search a task

Search matching tasks in the current task list with a partial word.

Example of usage:

`search home`

Expected outcome:

Returns a list of tasks that matches the partial word.

```
Here are the matching tasks in your list:
1. [D][ ] homework (by: Nov 9 2022)
```

### `delete` - Delete a task

Delete a task from the list.

Example of usage:

`delete 3`

Expected outcome:

Returns a notification of task being deleted.

```
Noted. I've removed this task:
[E][ ] concert (at: Nov 8 2022)
Now you have 2 tasks in the list
```

### `bye` - Exit the program

Exit the program automatically after 3 seconds.

Example of usage:

`bye`
