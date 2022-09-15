# User Guide

Byu is a desktop app for managing your tasks, optimized for use via a Graphical User Interface (GUI).

## Notes about the command format

- Words in UPPER_CASE are the parameters to be supplied by the user.
  e.g. in todo NAME, NAME is a parameter which can be used as todo cycle.

## Features

### `todo` - adding a task

Adds a task to the list. The task has no specified date or time.

Format: `todo NAME`

- `NAME` is the name of the task.

Example: 

`todo jog`

Expected output:

```
[T][ ] jog
```
### `deadline` - adding a deadline

Adds a deadline to the list. A deadline has a specified date and time.

Format: `deadline NAME /by DD/MM HH:mm`

- `NAME` is the name of the task.
- `DD/MM HH:mm` is the date and time of the deadline.
- `DD` is the day, `MM` is the month, `HH:mm` is the time in 24 hour format.

Example: 

`deadline assignment 2 /by 16/09 16:00`.

Expected output:

```
[D][ ] assignment 2 (by: 16 Sep 4:00pm)
```

### `event` - adding an event

Adds an event to the list. An event has 2 specified dates and times.

Format: `event NAME /at DD/MM HH:mm to DD/MM HH:mm`

- `NAME` is the name of the event.
- the first `DD/MM HH:mm` is the date and time of the start of event.
- the second `DD/MM HH:mm` is the date and time of the end of event.
- `DD` is the day, `MM` is the month, `HH:mm` is the time in 24 hour format.

Example: 

`event interview /at 16/09 14:00 to 16/09 15:00`.

Expected output:

```
[E][ ] interview (at: 16 Sep 2:00pm to 16 Sep 3:00pm)
```

### `mark` - marking a task as complete

Marks a task as complete. The task will now be displayed with a cross.  

Format: `mark INDEX`

- `INDEX` is the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example: 

`mark 1` marks the first task in the list as complete.

Expected output:

```
1. [T][X] meeting
```

### `unmark` - marking a task as incomplete

Marks a task as incomplete. The task will now be displayed without a cross.  

Format: `unmark INDEX`

- `INDEX` is the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example: 

`unmark 2` marks the second task in the list as incomplete.

Expected output:

```
2. [T][ ] do assignment 1
```

### `list` - listing all tasks

Lists all the tasks in the list.

Format: `list`

Example: 

`list` shows the 3 tasks in the list.

Expected output:

```
1. [T][ ] meeting
2. [T][ ] do assignment 1
3. [D][X] assignment 2 (by: 16 Sep 4:00pm)
```

### `find` - locating tasks by name

Finds tasks whose names contain the keyword.

Format: `find KEYWORD`

Example: 

`find assignment` returns tasks 2 and 3

Expected output:

```
2. [T][ ] do assignment 1
3. [D][X] assignment 2 (by: 16 Sep 4:00pm)
```

### `delete` - deleting a task

Deletes a task from the list. The task will no longer be shown in the task list.

Format: `delete INDEX`

- `INDEX` is the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example: 

`delete 2` deletes the second task in the list.
