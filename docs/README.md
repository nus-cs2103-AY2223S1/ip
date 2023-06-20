# User Guide

## Features

### Manage tasks

Adds, deletes, marks as done, and finds tasks in the task list.

Tasks are automatically saved to a file on your computer.

### Manage notes

Adds and deletes notes in the list of saved text files

## Usage

### `todo` - Adding a todo task

Adds a todo task to the task list.

Example of usage:

`todo TASK_DESCRIPTION`

Expected outcome:

A single todo task should be added into the list with `TASK_DESCRIPTION` as the description.

```
Task has been added!: [T][] TASK_DESCRIPTION
Total tasks: NUMBER_OF_TOTAL_TASKS
```

### `deadline` - Adding a deadline task

Adds a deadline task to the task list.

Example of usage:

`todo TASK_DESCRIPTION /by DATE`

Expected outcome:

A single deadline task should be added into the list with `TASK_DESCRIPTION` as the description and `DATE` as the deadline.

```
Task has been added!: [D][] TASK_DESCRIPTION (by: DATE)
Total tasks: NUMBER_OF_TOTAL_TASKS
```

### `event` - Adding a event task

Adds a event task to the task list.

Example of usage:

`event TASK_DESCRIPTION /at DATE`

Expected outcome:

A single event task should be added into the list with `TASK_DESCRIPTION` as the description and `DATE` as the date.

```
Task has been added!: [E][] TASK_DESCRIPTION (at: DATE)
Total tasks: NUMBER_OF_TOTAL_TASKS
```

### `list` - Listing all tasks

Lists all tasks in the task list.

Example of usage:

`list`

Expected outcome:

All tasks in the task list should be listed.

```
1) [T][] TASK_DESCRIPTION
2) [D][] TASK_DESCRIPTION (by: DATE)
3) [E][] TASK_DESCRIPTION (at: DATE)
...
```

### `reminder` - Listing all tasks with deadlines that are not done

Lists all tasks with deadlines that are not done in the task list sorted by their deadlines.

Example of usage:

`reminder`

Expected outcome:

All tasks with deadlines that are not done in the task list should be listed.

```
1) [D][] TASK_DESCRIPTION (by: DATE)
...
```

### `sort` - Sorting all tasks

Lexicographically sorts all tasks in the task list by their descriptions.

Example of usage:

`sort`

Expected outcome:

All tasks in the task list should be sorted.

```
1) [T][] TASK_DESCRIPTION
2) [D][] TASK_DESCRIPTION (by: DATE)
3) [E][] TASK_DESCRIPTION (at: DATE)
...
```

### `find` - Finding tasks

Finds all tasks in the task list that contain the keyword.

Example of usage:

`find KEYWORD`

Expected outcome:

All tasks in the task list that contain `KEYWORD` in their descriptions should be listed.

```
1) [T][] TASK_DESCRIPTION
2) [D][] TASK_DESCRIPTION (by: DATE)
3) [E][] TASK_DESCRIPTION (at: DATE)
...
```

### `mark` - Marking a task as done

Marks a task as done in the task list.

Example of usage:

`mark TASK_NUMBER`

Expected outcome:

The task with `TASK_NUMBER` as the index should be marked as done.

```
Task TASK_NUMBER is marked as done!
```

### `unmark` - Marking a task as not done

Marks a task as not done in the task list.

Example of usage:

`unmark TASK_NUMBER`

Expected outcome:

The task with `TASK_NUMBER` as the index should be marked as not done.

```
Task TASK_NUMBER is marked as not done!
```

### `delete` - Deleting a task

Deletes a task from the task list.

Example of usage:

`delete TASK_NUMBER`

Expected outcome:

The task with `TASK_NUMBER` as the index should be deleted.

```
Task TASK_NUMBER is deleted!
```

### `add_note` - Adding a note

Adds a note to the list of saved text files.

Example of usage:

`add_note NOTE_NAME /note NOTE_CONTENT`

Expected outcome:

A single note should be added into the list with `NOTE_NAME` as the name and `NOTE_CONTENT` as the content.

```
Note added.
```

### `load_note` - Loading a note

Loads a note from the list of saved text files.

Example of usage:

`load_note NOTE_NAME`

Expected outcome:

The note with `NOTE_NAME` as the name should be loaded.

```
Here is your note:
NOTE_CONTENT
```

### `delete_note` - Deleting a note

Deletes a note from the list of saved text files.

Example of usage:

`delete_note NOTE_NAME`

Expected outcome:

The note with `NOTE_NAME` as the name should be deleted.

```
Note deleted successfully!
```
