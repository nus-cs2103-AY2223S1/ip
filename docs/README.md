# Duke User Guide

Duke is a GUI task manager with support for unix-like syntax commands.

## Features

### Keep track of your tasks!

Add todos, events, and deadlines with support for date input!

### Mark your tasks as done

Mark each task as done to keep track of your undone tasks.

### Search through your task list

Using the find command, you will be able to quickly find all the tasks with content
that match your search keyword.

## Usage

### `todo` - Adds a todo to your task list

A new todo with the specified content will be added to your tasks list.

**Usage:**

`todo <task-name>`

**Parameters:**

`task-name`: The content of the task to be added.

**Expected outcome:**

```
Added: [T] [] <task-name>
Now you have 1 task in the list
```

<br />

### `event` - Adds an event with date to your task list

A new event with the specified content and date will be added to your tasks list.

**Usage:**

`event <task-name> /at <date>`

**Parameters:**

`task-name`: The content of the task to be added.

`date`: The date of event in `yyyy-MM-dd HH:mm` format

**Expected outcome:**

```
Added: [E] [] <task-name> (at: <date>)
Now you have 1 task in the list
```

<br />

### `deadline` - Adds a deadline with date to your task list

A new deadline with the specified content and date will be added to your tasks list.

**Usage:**

`deadline <task-name> /by <date>`

**Parameters:**

`task-name`: The content of the task to be added.

`date`: The deadline in `yyyy-MM-dd HH:mm` format

**Expected outcome:**

```
Added: [D] [] <task-name> (by: <date>)
Now you have 1 task in the list
```

<br />

### `list` - List out all tasks in the list

Displays the full list of all tasks in the list.

**Usage:**

`list`

**Expected outcome:**

```
Your Tasks:
1. [T] [] Find hat
2. [D] [] Coldplay concert (at: Dec 22 2022 05:30pm)
```

<br />

### `mark` - Mark task as done

The specified task will be marked as completed

**Usage:**

`mark <task-index>`

**Parameters:**

`task-index`: The index of the task to be marked.

**Expected sample outcome:**

```
OK, I've marked this task as done: [T] [X] Find hat
```

<br />

### `unmark` - Unmark task as uncompleted

The specified task will be marked as not completed

**Usage:**

`unmark <task-index>`

**Parameters:**

`task-index`: The index of the task to be unmarked.

**Expected sample outcome:**

```
OK, I've unmarked this task as not done yet: [T] [] Find hat
```

<br />

### `delete` - Delete task

The specified task will be deleted

**Usage:**

`delete <task-index>`

**Parameters:**

`task-index`: The index of the task to be deleted

**Expected sample outcome:**

```
Removed: [T] [] Find hat
Now you have 0 task in the list
```

<br />

### `bye` - Exits the `Duke` Program

Terminates the program

**Usage:**

`bye`

**Expected outcome:**

The program window will close.