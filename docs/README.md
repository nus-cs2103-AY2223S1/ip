# User Guide

Duke is keyboard-primary task manager to help you keep track of your busy schedule and todos. Simply type a command, 
and Duke will respond to you!

## Features

### Add tasks of different types
- [x] Add a simple todo quickly.
- [x] Add a deadline.
- [x] Add an event.

### Common actions are provided for easy use!
- [x] Delete a task.❌
- [x] List all tasks.📝
- [x] Mark (and unmark) a task as done. ✅
- [x] Search for tasks by keyword.🔎
- [x] Undo the last command.↶

## Usage

### `todo <description>` - Add a todo

Add a todo quickly with the specified `description`.

**Parameters**

`description: String` - the description of the todo

**Example of usage:**

`todo buy milk`

**Expected outcome:**
```
Got it. I've added this task:
    [T][ ] buy milk
Now you have 1 tasks in the list
```

### `event <event-name> /at <date-time> ` - Add an event

Add an event at the specified `date-time`.

**Parameters**

`event-name: String` - the event name

`date-time: String` - the date and time of the event
- The format of `date-time` is `YYYY-MM-DD HH:MM`.

**Example of usage:**

`event project meeting /at 2023-12-31 23:59`

**Expected outcome:**
```
Got it. I've added this task:
  [E][ ] project meeting (at: 31 Dec 2023 23:59)
Now you have 1 tasks in the list.
```

### `deadline <description> /at <date-time> ` - Add a deadline

Add an event at the specified `date-time`.

**Parameters**

`description: String` - the action to perform by the deadline

`date-time: String` - the date and time of the deadline
- The format of `date-time` is `YYYY-MM-DD HH:MM`.

**Example of usage:**

`deadline return book /by 2022-08-24 14:00`

**Expected outcome:**
```
Got it. I've added this task:
  [D][ ] return book (by: 24 Aug 2022 14:00)
Now you have 1 tasks in the list.
```

### `delete <task-index>` - Delete a task

Delete a task quickly with the specified `task-index`.

**Parameters**

`task-index: Integer` - the index of the task

**Example of usage:**

`delete 1`

**Expected outcome:**
```
Noted. I've removed this task:
  [D][ ] return book (by: 24 Aug 2022 14:00)
Now you have 2 tasks in the list.
```

### `list` - List all tasks

List all tasks with this command. It also returns the task index for each task.

**Example of usage:**

`list`

**Expected outcome:**
```
Here are the tasks in your list:
  1.[T][X] borrow book
  2.[E][ ] project meeting (at: 31 Dec 2023 23:59)
```

### `mark <task-index>` - Mark a task as done

Mark a task specified by the `task-index` as done.

**Parameters**

`task-index: Integer` - the index of the task

**Example of usage:**

`mark 1`

**Expected outcome:**
```
Nice! I've marked this task as done:
  [T][X] borrow book
```

### `unmark <task-index>` - Mark a task as _not_ done

Mark a task specified by the `task-index` as _not_ done.

**Parameters**

`task-index: Integer` - the index of the task

**Example of usage:**

`unmark 1`

**Expected outcome:**
```
OK, I've marked this task as not done yet:
  [T][ ] buy carrots
```

### `find <keyword>` - Find a task based on a keyword

Find tasks whose descriptions partially or fully match the specified `keyword`.

**Parameters**

`keyword: String` - the keyword to match by

**Example of usage:**

`find milk`

**Expected outcome:**
```
Here are the matching tasks in your list:
 1. [T][ ] buy 2 bottles of milk
 2. [T][ ] buy baby milk powder
```

### `undo` - Undo the last command

Undo the last undoable command. Undoable commands are: `todo`, `event`, `deadline`, `mark`, `unmark`, `delete`.

**Example of usage:**

`undo`

**Expected outcome:**
```
Previous command successfully undone
```

### `bye` - Say bye to Duke

Used to quit Duke. There is a 1-second delay before the app exits.

**Example of usage:**

`bye`

**Expected outcome:**
```
Bye. This doesn't have to be the end!
```