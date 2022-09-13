# Cheese User Guide

## Table of Contents

- [Features](#features)
- [Commands](#commands)
    - [`todo`](#todo)
    - [`deadline`](#deadline)
    - [`event`](#event)
    - [`list`](#list)
    - [`mark`](#mark)
    - [`unmark`](#unmark)
    - [`snooze`](#snooze)
    - [`delete`](#delete)
    - [`find`](#find)
    - [`bye`](#bye)

## Features

### Keep track of your tasks

Cheese features **storage** to store and retrieve your tasks, even after closing the application.

### Add a variety of tasks

Cheese supports a variety of tasks, including: **Todo**, **deadline**, and **event**.

### Find tasks easily

Cheese can help you find tasks with a search query.

## Commands

### `todo`

Add new todo to task list.

**Syntax:** `todo <description>`

**Sample usage:** `todo Drink water`

Expected outcome: A new todo will be added to the end of the task list.

```
Gotcha! I have a paw-fect memory!
  [T][ ] Drink water
You have 1 task(s) in the list.
```

### `deadline`

Add new deadline with due date to task list.

**Syntax:** `todo <description> /by <due date>`

- Deadline must be in `yyyy-dd-MM HH:mm` format.

**Sample usage:** `deadline Submit homework /by 2022-12-02 12:00`

Expected outcome: A new deadline will be added to the end of the task list.

```
Gotcha! I have a paw-fect memory!
  [D][ ] Submit homework (by: 12-02-2022 12:00)
You have 1 task(s) in the list.
```

### `event`

Add new event with start time to task list.

**Syntax:** `event <description> /at <start time>`

**Sample usage:** `event Eat lunch /at 2022-12-02 12:00`

- Deadline must be in `yyyy-dd-MM HH:mm` format.

Expected outcome: A new event will be added to the end of the task list.

```
Gotcha! I have a paw-fect memory!
  [E][ ] Eat lunch (at: 12-02-2022 12:00)
You have 1 task(s) in the list.
```

### `list`

Display the task list.

**Syntax:** `list`

- Dates are displayed in `dd-MM-YYYY HH:mm` format.
- `[T]` indicates a todo. `[D]` indicates a deadline. `[E]` indicates an event.
- `[X]` indicates task is completed. `[ ]` indicates task is incomplete.

### `mark`

Mark a task as done.

**Syntax:** `mark <task number>`

**Sample usage:** `task 1`

Expected outcome: The first task will be marked as done.

```
Paw-some! Another task done!
  [T][X] Read book
```

### `unmark`

Mark a task as incomplete.

**Syntax:** `unmark <task number>`

**Sample usage:** `unmark 1`

Expected outcome: The first task will be marked as incomplete.

```
Okay master, I've marked this task as incomplete.
  [T][ ] Read book
```

### `snooze`

Delay date of task by 1 day.

**Syntax:** `snooze <task number>`

**Sample usage:** `snooze 1`

Expected outcome: The first task will be delayed by 1 day.

```
Gotcha! I've snoozed this task by 1 day. Wanna go for a walk now??
  [D][ ] Read book (by: 29-08-2022 12:30)
```

### `delete`

Delete a task from the task list.

**Syntax:** `delete <task number>`

**Sample usage:** `delete 1`

Expected outcome: The first task will be deleted.

```
Gotcha master! I'll forget about this task!
  [T][ ] Read book
You have 0 task(s) remaining.
```

### `find`

Search for a task from the task list.

**Syntax:** `find <search query>`

**Sample usage:** `find book`

- Search is case-insensitive.

Expected outcome: A list of tasks with descriptions containing the given query will be displayed.

```
1. [T][ ] Read book
2. [T][X] Write a book
```

### `bye`

Exit Cheese.

**Syntax:** `bye`
