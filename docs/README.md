# MOTOONG User Guide

## Features 

### Keep track of tasks, events and deadlines

List tasks, create new tasks, delete or mark done or undone tasks.

### Persistent storage

Tasks are stored on disk so the application does not have to be left on.

### Fun and attractive GUI

Increase chances that user continues to use the product.

## Usage

### `list` - Lists all tasks

Lists all tasks, including their task types and status. Additionally, dates are printed for Event and Deadline task types.

Example of usage: 

`list`

Expected outcome:

All tasks will be printed

```
1. [T][ ] 2103 project
2. [E][ ] 2103 project meeting (at: Sep 12 2022)
```
### `todo/event/deadline <task> <optional: date>` - Adds a new todo/event/deadline task

Adds new todo, event or deadline task. Date (YYYY-MM-DD format) is accepted only for event and deadline tasks.

Example of usage:

`deadline 2103 project /by 2022-09-12`

`event 2103 meeting /at 2022-09-12`


Expected outcome:

A deadline and an event will be added with the specified dates.

```
1. [D][ ] 2103 project (by: Sep 12 2022)
2. [E][ ] 2103 meeting (at: Sep 12 2022)
```

### `mark/unmark <task number>` - Sets the status of a task to done or undone.

Sets the status of a task to done or undone.

Example of usage:

`mark 1`

`mark 2`

Expected outcome:

```
1. [D][X] 2103 project (by: Sep 12 2022)
2. [E][X] 2103 meeting (at: Sep 12 2022)
```

### `find <query>` - Finds a task that matches the user's input

Looks through the task list and returns tasks that matches the user's input.

Example of usage:

`find 2103`


Expected outcome:

Tasks that contain "2103" will be returned.

```
Woof! This is what I found:
1. [D][X] 2103 project (by: Sep 12 2022)
2. [E][X] 2103 meeting (at: Sep 12 2022)
```

### `delete <number>` - Removes selected task

Removes selected task from memory.

Example of usage:

`delete 2`

Expected outcome:

The second task will be deleted.

```
1. [D][X] 2103 project (by: Sep 12 2022)
```

### `bye` - Quits the program

Saves the data to disk and quits the program.

Example of usage:

`bye`