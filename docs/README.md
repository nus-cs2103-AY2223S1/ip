# Cheese User Guide

## Table of Contents

- [Features](#features)
- [Commands](#commands)
    - [`todo`](#todo)
    - [`deadline`](#deadline)
    - [`event`](#event)
    - [`bye`](#bye)

## Features

### Add todos, deadlines, and events

Description of the feature.

### Snooze tasks

Description of the feature.

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

### bye

Exit Cheese.

**Syntax:** `bye`
