# User Guide

## Features 

### Adding a task

Adds a task to the task list.

### Deleting a task

Deletes a task from the task list.

### Listing all tasks

Shows all the tasks in the task list.

### Marking a task

Marks a task as completed

### Unmarking a task

Marks a task as not completed.

### Finding tasks

Finds all tasks based on the given keywords.

### Providing an overview of all tasks

Lists all upcoming tasks, tasks completed over the week, and all tasks that are marked as completed.

## Usage

- Words in `UPPER_CASE` are arguments supplied by the user.
- Items in square brackets are optional.

### `todo` - Adds a Todo task

Adds a Todo task to the task list

Format: `todo TASK`

Example of usage: `todo read book`

Expected outcome:

```
Got it! I have added this task:

[T][ ] read book

Now you have 1 task in the list!
```
### `deadline` - Adds a Deadline task

Adds a Deadline task to the task list.

Format: `deadline TASK /by DATE`

Example of usage: `deadline return book /by 20/09/2022`

Expected outcome:

```
Got it! I have added this task:

[D][ ] return book (by: 20 Sep 2022)

Now you have 1 task in the list!
```

### `event` - Adds an Event task

Adds an Event task to the task list.

Format: `event TASK /at DATE`

Example of usage: `event cooking class /at 20/09/2022`

Expected outcome:

```
Got it! I have added this task:

[E][ ] cooking class (at: 20 Sep 2022)

Now you have 1 task in the list!
```

### `delete` - Deletes a task

Deletes a task from the task list.

Format: `delete TASK_NUMBER`
-  `TASK_NUMBER` **must be a positive integer**.

Example of usage: `delete 1`

Expected outcome:

```
Noted, I have removed this task:

[T][ ] read book

Now you have 0 task in the list!
```

### `list` - Lists all tasks

Lists all the task in the task list. If a date is provided, tasks that fall on that specified date are listed.

Format: `list [DATE]`

Example of usage: `list`

Expected outcome:

```
Here is the task in your list :D

1. [T][ ] read book
```

### `mark` - Marks a task

Marks a task as completed.

Format: `mark TASK_NUMBER`

- `TASK_NUMBER` **must be a positive integer**.

Example of usage: `mark 1`

Expected outcome:

```
Nice! I have marked this task as done:

[T][X] read book
```

### `unmark` - Unmarks a task

Marks a task as not completed.

Format: `unmark TASK_NUMBER`

- `TASK_NUMBER` **must be a positive integer**.

Example of usage: `unmark 1`

Expected outcome:

```
Okay! I have marked this task as not done:

[T][ ] read book
```

### `find` - Finds all the tasks that matches the keywords

Finds all the tasks that contains the all the keywords provided.

Format: `find KEYWORDS`

Example of usage: `find read book`

Expected outcome:

```
Here is the matching task in your list:

1. [T][ ] read book
```

### `summary` - Providing an overview

Lists the following items
- Upcoming tasks (if any).
- Tasks completed over the week (if any). 
- All tasks that are marked as completed (if any).

Format: `summary`

Example of usage: `summary`

Expected outcome:

```
Your activity log :D

No upcoming tasks!

0 tasks completed!

No activities in the past week!
```
