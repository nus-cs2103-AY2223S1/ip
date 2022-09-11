# Maria User Guide

Maria is a task management system in the form of a chatbot. Manage your work with ease with Maria!

## Features 

### Task operations

Maria allows you to add and delete tasks, mark and unmark tasks as done/not done. 
You can keep track of the tasks that you need to do with ease.

### Task search

Maria can show you all your tasks at once, or you can search for a particular task as well.

## Usage

> Note: Names of tasks must not contain any spaces

### `todo` - Creates a Todo task

Format: `todo <name> <isDone (true/false)>`

Creates a todo task, which is a task without a set deadline or timing.

Examples of usage: 

`todo Walk false`

`todo Sleep true`

Expected outcome example:

```
Added a todo task [Todo] Walk [Not Done]
```

### `deadline` - Creates a Deadline task

Format: `deadline <name> <isDone (true/false)> <deadline (YYYY-MM-DD)>`

Creates a deadline task, which is a task with a set deadline.

Examples of usage:

`deadline Sleep false 2022-12-01`

`deadline Sleep true 2025-01-01`

Expected outcome example:

```
Added a deadline task [Deadline] Sleep [Not Done] (by: 1 December 2022)
```

### `event` - Creates an Event task

Format: `event <name> <isDone (true/false)> <start (YYYY-MM-DD)> <end (YYYY-MM-DD)>`

Creates an event task, which is a task with a start and end date.

Examples of usage:

`event Everest false 2023-05-05 2023-05-15`

`event Europe true 2021-11-29 2021-12-22`

Expected outcome example:

```
Added a event task [Event] Europe [Done] (from 29 November 2021 to 22 December 2021)
```

### `list` - Lists out all the tasks

Format: `list`

Lists out all the tasks.

Expected outcome example:

```
Your tasks are 
1. [Todo] PayMaria [Done]
2. [Event] Bday [Not Done] (from 1 January 2022 to 31 December 2022)
3. [Event] Birthday [Done] (from 1 January 2022 to 1 January 2022)
4. [Deadline] Walk [Not Done] (by: 11 September 2022)
5. [Todo] Walk [Not Done]
6. [Todo] Sleep [Done]
7. [Deadline] Sleep [Not Done] (by: 1 December 2022)
8. [Event] Europe [Done] (from 29 November 2021 to 22 December 2021)
```

### `mark` - Sets a task as completed

Format: `mark <index>`

Sets a task, specified by the index seen in the command `list`, as completed.

Examples of usage:

`mark 8`

Expected outcome example:

```
Your task Europe has been completed.
```

### `unmark` - Sets a task as not completed

Format: `unmark <index>`

Sets a task, specified by the index seen in the command `list`, as not completed.

Examples of usage:

`unmark 8`

Expected outcome example:

```
Your task Europe has been un-completed.
```

### `delete` - Deletes a task

Format: `delete <index>`

Deletes a task specified by the index seen in the command `list`.

Examples of usage:

`delete 8`

Expected outcome example:

```
Your task Europe has been removed.
```

### `find` - Finds a task

Format: `find <search_text>`

Finds a task which name **contains** the `<search_text>`

Examples of usage:

`find leep`

Expected outcome example:

```
Your matching tasks are 
- [Todo] Sleep [Done]
- [Deadline] Sleep [Not Done] (by: 1 December 2022)
```

### `bye` - Terminates the program

Format: `bye`

## Summary