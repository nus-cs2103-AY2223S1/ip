# User Guide

## Features 

### list

Lists all current tasks.

### mark

Marks a task as done.

### unmark

Marks a task as undone.

### todo

Adds a todo task.

### deadline

Adds a deadline task.

### event

Adds an event task.

### priority

Assigns a priority level to a task.

### delete

Deletes a task.

### find

Searches for tasks via a filter.

## Usage

### `list` - Lists all current tasks

Example of usage: 

`list`

Expected outcome:

All current tasks of the user are displayed in the GUI.

```
1. [T][ ] clean room || Priority: HIGH
```

### `mark` - Marks a task as done

Example of usage: 

`mark 1`

Expected outcome:

Task 1 will be marked as done.

```
1. [T][x] clean room || Priority: HIGH
```

### `unmark` - Marks a task as undone.

Example of usage: 

`unmark 1`

Expected outcome:

Task 1 will be marked as undone.

```
1. [T][ ] clean room || Priority: HIGH
```

### `todo` - Adds a todo task.

Example of usage: 

`todo eat dinner`

Expected outcome:

A todo task with the description: 'eat dinner' is added.

```
1. [T][ ] clean room || Priority: HIGH
2. [T][ ] eat dinner || Priority: NONE
```

### `deadline` - Adds a deadline task.

Example of usage: 

`deadline finish homework /by tomorrow night`

Expected outcome:

A deadline task with the description 'finish homework' and deadline 'tomorrow night' is added.

```
1. [T][ ] clean room || Priority: HIGH
2. [T][ ] eat dinner || Priority: NONE
3. [D][ ] finish homework (by: tomorrow night) || Priority: NONE
```

### `event` - Adds a event task.

Example of usage: 

`event basketball /at nearby court`

Expected outcome:

An event task with the description 'basketball' and location 'nearby court' is added.

```
1. [T][ ] clean room || Priority: HIGH
2. [T][ ] eat dinner || Priority: NONE
3. [D][ ] finish homework (by: tomorrow night) || Priority: NONE
4. [E][ ] basketball (at: there) || Priority: NONE
```

### `priority` - Assigns a priority level to a task

Example of usage: 

`priority 3 low`

Expected outcome:

Task 3 will be assigned the priority 'LOW'.

```
1. [T][ ] clean room || Priority: HIGH
2. [T][ ] eat dinner || Priority: NONE
3. [D][ ] finish homework (by: tomorrow night) || Priority: LOW
4. [E][ ] basketball (at: there) || Priority: NONE
```

### `delete` - Deletes a task.

Example of usage: 

`delete 4`

Expected outcome:

Task 4 will be deleted.

```
1. [T][ ] clean room || Priority: HIGH
2. [T][ ] eat dinner || Priority: NONE
3. [D][ ] finish homework (by: tomorrow night) || Priority: LOW
```

### `find` - Searches for tasks via a filter.

Example of usage: 

`find dinner`

Expected outcome:

Task 2 will be listed.

```
1. [T][ ] eat dinner || Priority: NONE
```
