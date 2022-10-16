# User Guide

## Features 

### List Tasks

- List all current todos/deadlines/events.

### Add Tasks

- Add todo/deadline/event as a task to list of tasks.

### Deleting Tasks

- Delete specified task from list of tasks.

### Mark Tasks

- Mark specified task as completed.
- Mark specified task as not completed.

### Find Tasks

- Find tasks containing specific keyword(s).

### Sort Tasks

- Sort tasks in chronological order.

## Usage

### `todo` - Adds todo

Adds a todo task to task list.

Example of usage:

`todo (description)

Expected outcome:

Adds todo to task list.

```
Alrighty! I've added this todo:
[T][ ] Eat dinner
```

### `deadline` - Adds deadline

Adds a deadline task to task list.

Example of usage:

`deadline (description) /by (yyyy-MM-dd HH:mm)`

Expected outcome:

Adds todo to task list.

```
Alrighty! I've added this deadline:
[D][ ] CS2101 CA1 Submission (by: Sep 17 2022 23:59)
```

### `event` - Adds event

Adds a event task to task list.

Example of usage:

`event (description) /at (yyyy-MM-dd HH:mm)`

Expected outcome:

Adds event to task list.

```
Alrighty! I've added this event:
[E][ ] CS2103T Tutorial (at: Sep 15 2022 10:00)
```

### `delete` - Deletes specified task

Deletes specified task from task list.

Example of usage:

`delete (index of task)`

Expected outcome:

Deletes task at specified index from task list.

```
Alrighty! I've deleted this task:
[T][ ] Eat dinner
```

### `mark` - Marks task as completed

Marks a specified task as completed.

Example of usage:

`mark (index of task)`

Expected outcome:

Sets the task at specified index as completed.

```
Alrighty! I've marked this task as completed:
[T][X] Watch lecture recording
```

### `unmark` - Marks task as not completed

Unmarks a specified task as not completed.

Example of usage:

`unmark (index of task)`

Expected outcome:

Sets the task at specified index as not completed.

```
Alrighty! I've marked this task as not completed:
[T][ ] Watch lecture recording
```

### `list` - Displays all tasks

Displays all tasks currently in task list.

Example of usage:

`list`

Expected outcome:

Outputs a list of all tasks in task list.

```
1. [D][ ] CS2101 CA1 Submission (by: Sep 17 2022 23:59)
2. [E][X] CS2103T Tutorial (at: Sep 15 2022 10:00)
3. [T][ ] Watch lecture recording
```

### `sort` - Sorts tasks in chronological order

Sort all tasks in task list from earliest to latest.

Example of usage:

`sort`

Expected outcome:

Outputs a list of all tasks sorted chronologically.

```
1. [E][X] CS2103T Tutorial (at: Sep 15 2022 10:00)
2. [D][ ] CS2101 CA1 Submission (by: Sep 17 2022 23:59
3. [T][ ] Watch lecture recording
```

### `find` - Finds tasks containing keyword(s)

Find tasks from task list containing keyword(s) provided.

Example of usage:

`find (keyword), ... (optional keyword)`

Expected outcome:

Outputs a list of tasks containing keyword(s) provided.

```
Here are the tasks you are looking for:
1. [D][ ] CS2101 CA1 Submission (by: Sep 17 2022 23:59)
2. [E][X] CS2103T Tutorial (at: Sep 15 2022 10:00)
```

### `exit` - Exits the app

Saves and exits the app.

Example of usage:

`exit`

Expected outcome:

Chacha outputs exit message and user can close the app.

```
I've saved all your tasks! You may close the app now,
Bye!
```
