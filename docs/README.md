# User Guide

## Features 

### Manage your tasks

Add and delete various types of tasks, including:

- Todo
- Deadline
- Event

### Mark and unmark

Mark finished tasks as done, and unmark if needed.

### Finding

Filter tasks in your list powerfully using regex.

### Snoozing

Snooze timed events and customise the snooze length.

## Usage

### `todo` - Add a To-Do task

Adds a to-do task to the task list.

**Example of usage:** `todo DESCRIPTION`

**Expected outcome:** To-do task added confirmation is shown, and save file is updated.

```
Very well, added another task:
	[T][ ] DESCRIPTION
```

### `deadline` - Add a Deadline task

Adds a Deadline task to the task list with specified time.

**Example of usage:** `deadline DESCRIPTION /by dd/MM/YYYY [HH:mm]`

**Expected outcome:** Deadline task added confirmation is shown, and save file is updated.

```
Very well, added another task:
	[D][ ] DESCRIPTION (by: dd MMM YYYY)
```

### `event` - Add an Event task

Adds an Event task to the task list with specified time.

**Example of usage:** `event DESCRIPTION /at dd/MM/YYYY [HH:mm]`

**Expected outcome:** Event task added confirmation is shown, and save file is updated.

```
Very well, added another task:
	[E][ ] DESCRIPTION (at: dd MMM YYYY)
```

### `list` - List all tasks

Lists all tasks in order of addition.

**Example of usage:** `list`

**Expected outcome:** All tasks are shown.

```
1. [D][ ] DESCRIPTION (by: dd MMM YYYY)
2. [T][X] DESCRIPTION
```

### `mark` - Mark task as done

Marks a specified task as done.

**Example of usage:** `mark INDEX`

**Expected outcome:** Confirmation of marked task, with `X` denoting marking, and save file is updated..

```
Very well, this task is done:
	[D][X] DESCRIPTION (by: dd MMM YYYY)
```

### `unmark` - Mark task as undone

Marks a specified task as undone.

**Example of usage:** `unmark INDEX`

**Expected outcome:** Confirmation of unmarked task, removing the `X`, and save file is updated..

```
Very well, this task is now undone:
	[D][ ] DESCRIPTION (by: dd MMM YYYY)
```

### `delete` - Delete task

Deletes a specified task.

**Example of usage:** `delete INDEX`

**Expected outcome:** Confirmation of task deletion, and save file is updated..

```
Very well, task deleted:
	[D][ ] DESCRIPTION (by: dd MMM YYYY)
```

### `find` - Delete task

Finds all tasks that match the query pattern.

**Example of usage:** `find QUERY`

**Expected outcome:** All tasks matching pattern are shown.

```
1. [D][ ] ABCD QUERY (by: dd MMM YYYY)
2. [T][X] QUERY EFGH
```

### `snooze` - Snooze task

Snoozes specified task for a given period of time.

For days, use `D`. For months, use `M`. For hours, use `h`. For minutes, use `m`.

**Example of usage:** `snooze INDEX /for DURATION(D|M|h|m)`

**Expected outcome:** Snoozes task for a given time, and save file is updated..

```
Very well, snoozed this task:
	[D][ ] DESCRIPTION (by: new dd MMM YYYY)
```