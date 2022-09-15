# Welcome to **`Duke`** 

`Duke` is a task tracker app that can keep you right on schedule. This is a user guide on how to use `Duke`.

## Types of Tasks

### ToDo

Input Format: `todo <task name>`

Parameter: 
- `<task name>`: Name of task

### Deadline

Input Format: `deadline <task name> /by <yyyy-mm-dd>`

Parameters:
- `<task name>`: Name of task
- `<yyyy-mm-dd>`: Time to do task by

## Event

Input Format: `event <task name> /at <yyyy-mm-dd>`

Parameters:
-`<task name>`: Name of task
- `<yyyy-mm-dd>`: Timing of task

## Task Functions

### `mark`

Marks a task at an index as complete.

Input Format: `mark <index>`

Parameters:
- `<index>`: Index of task to mark as complete

Sample input: `mark 2`

Sample output:
```
Good Job on completing the task! I've marked this task as done:
[T][X] Wash Clothes
```

### `unmark`

Marks a task at an index as incomplete.

Input Format: `unmark <index>`

Parameters:
- `<index>`: Index of task to mark as incomplete

Sample input: `unmark 2`

Sample output:
```
OK, I've marked this task as not done yet:
[T][ ] Wash Clothes
```

### `priority`

Changes priority of task at an index.

Input Format: `priority <index> <priority symbol>`

Parameters:
- `<index>`: Index of task to change priority
- `<priority symbol>`: String

Sample input: `priority 2 h`

Sample output:
```
Noted, I've changed the priority of this task to high:
[T][ ][!!!] Wash Clothes
```

### Types of Priorities

| Priority Level | Priority Symbol |
|----------------|:---------------:|
| **Low**        |       `l`       |
| **Medium**     |       `m`       |
| **High**       |       `h`       |
| **None**       |       `-`       |

### `delete`

Deletes task at an index. 

Input Format: `delete <index>`

Parameters:
- `<index>`: Index of task to delete

Sample input: `delete 2`

Sample output:
```
Noted, I've removed this task:
[T][ ][!!!] Wash Clothes
Now you have 4 tasks in the list.
```

## List Functions

### `list`

Input Format: `list`

Sample output:
```
Here are the tasks in your list:
1. [T][ ] Do tutorial 1
2. [T][ ] Do tutorial 2
3. [T][ ] Do tutorial 3
4. [T][ ] Do tutorial 4
```

### `find`

### a. Find by name

Input Format: `find <name substring>`

Parameters:
- `<name substring>`: Part of name of task to find

Input Format: `find tutorial`

Sample output:
```
Here are the tasks in your list:
1. [T][ ][!!!] Do tutorial 1
2. [T][ ][!!!] Do tutorial 2
3. [T][ ] Do tutorial 3
4. [T][ ] Do tutorial 4
```

### b. Find by priority

Input Format: `find /p <priority symbol>`

Parameters:
- `<priority symbol>`: Priority level to search for

Input Format: `find /p h`

Sample output:
```
Here are the tasks of priority h in your list:
1. [T][ ][!!!] Do tutorial 1
2. [T][ ][!!!] Do tutorial 2
```

### `quit`

Input Format: `quit`