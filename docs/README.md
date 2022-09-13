# `Duke` User Guide

`Duke` is graphical task manager that helps you keep track of your tasks so that you don't need to.

## Features

### Type of tasks

- `todo`: A simple TODO task
- `event`: A event with a date and time
- `deadline`: A task with a deadline

### Mark a task as completed ✅

Using the `mark` and `unmark` commands, you can mark a task as completed/uncompleted.

### Tag a task #️⃣

- Using the `tag` command, you tag a task with a special tag (e.g. `#school`, `#cs2103`).
- Alternatively, users can insert tags directly in the task title, and it will be tagged automatically
  - e.g. `todo task 1 #school`

## Usage

### `todo` - Create a simple TODO task

Creates a simple todo task and add it to the task list.

#### Example usage:

`todo <task-title>`

**Required parameters:**

- `<task-title>`: String
    - The title of the todo task to be added

#### Expected outcome:

`Duke`'s reply:

```
Got it. I've added this task:
    [T][ ] <task-title>
Now you have 1 tasks in the list
```

### `deadline` - Create a task with a deadline

Creates a task with a deadline and add it to the task list.

#### Example usage:

`deadline <task-title> /by <date> [<time>]`

**Required parameters:**

- `<task-title>`: String
    - The title of the task to be added
- `<date>`: Date
    - The deadline (date) of the task
    - Expected format: `YYYY-mm-dd`

**Optional parameters:**

- `<time>`: Time
    - The deadline (time) for the task
    - Expected format: `MM:ss`

#### Expected outcome:

`Duke`'s reply:

```
Got it. I've added this task:
    [D][ ] <task-title> (<deadline>)
Now you have 1 tasks in the list
```

### `event` - Create an event task with a timing

Creates an event task with a timing and add it to the task list.

#### Example usage:

`event <event-title> /at <date> [<time>]`

**Required parameters:**

- `<task-title>`: String
    - The title of the event to be added
- `<date>`: Date
    - The timing (date) of the event
    - Expected format: `YYYY-mm-dd`

**Optional parameters:**

- `<time>`: Time
    - The timing (time) for the event
    - Expected format: `MM:ss`

#### Expected outcome:

`Duke`'s reply:

```
Got it. I've added this task:
    [E][ ] <event-title> (<timing>)
Now you have 1 tasks in the list
```

### `list` - List all tasks in the task list

Display the full list of tasks in the task list.

#### Example usage:

`list [<tag>]`

**Optional parameters:**

- `tag`: Tag
  - A task tag
  - Expected format: `#<tag-name>`

#### Expected outcome:

Sample `Duke` replies:

**`list`:**

```
1. [T][X] (#nus) task-1
2. [D][ ] task-2 (by: 23/8/22, 8:00 PM)
3. [E][X] (#school, #nus) task-3 (at: 23/8/22)
```

**`list #nus`:**

```
1. [T][X] (#nus) task-1
2. [E][X] (#school, #nus) task-3 (at: 23/8/22)
```

### `mark` - Mark a task as completed

Mark a task in the task list as completed

#### Example usage:

`mark <task-id>`

**Required parameters:**

- `<task-id>`: Integer
  - The task number of the task to be marked as completed

#### Expected outcome:

`Duke`'s reply:

```
Nice! I've mark this task as done:
  [T][X] <task-title>
```

### `unmark` - Mark a task as uncompleted

Mark a task in the task list as uncompleted

#### Example usage:

`unmark <task-id>`

**Required parameters:**

- `<task-id>`: Integer
  - The task number of the task to be marked as uncompleted

#### Expected outcome:

`Duke`'s reply:

```
OK, I've marked this task as not done yet:
  [T][ ] <task-title>
```

### `tag` - Tag a task

Tag a task in the task list. A task can be tagged multiple times.

#### Example usage:

`tag #<tag-name> <task-id>`

**Required parameters:**

- `<tag-name>`: String
  - The tag name
- `<task-id>`: Integer
  - The task number of the task to be marked as uncompleted
  
#### Expected outcome:

`Duke`'s reply:

```
OK, I've tag this task
  [T][ ](#<tag-name>) <task-title>
```