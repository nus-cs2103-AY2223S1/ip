# User Guide

Duke is a task list manager that talks to you! Uses Command Line Interface, and supports several task types.

## Quick Start

Place `duke.jar` in a folder. In shell, navigate to that folder and run `java -jar duke.jar` to start using Duke!

## Features 

### Task List

- 3 types of tasks: To-Dos, Deadlines, Events - discussed in detail later!
- Add and remove tasks easily with simple commands
- View all tasks at once with a single command
- Find a certain task in the list with a single command

### Saves and Writes to File

Duke saves and writes to file so that it remembers your list! Stored in `duke.txt` in the same folder as `duke.jar`.

## Usage

### `todo`, `deadline`, `event` - Task Addition

Example of usage: 

1. `todo TASK INFO` for a simple to-do.
2. `deadline DEADLINE INFO /by YYYY-MM-DD` for a deadline by a certain date.
3. `event EVENT INFO /at YYYY-MM-DD` for an event at a certain date.

For all 3 cases you will see Duke acknowledging the addition of the task.

```
Got it. I've added this task:
  [T] [ ] INFO
Now you have 1 tasks in the list.
```

### `delete` - Task Deletion

Example of usage:

`delete 3`

Deletes the task labelled as `3`.

### `mark` - Task Completion

Example of usage:

`mark 1`

Marks the task labelled as `1` as complete.

### `unmark` - Task Completion

Example of usage:

`unmark 2`

Marks the task labelled as `2` as incomplete.

### `list` - Task Enumeration

Example of usage:

`list`

Lists the description and completion status of all tasks in the list.

### `find` - Search Command

Example of usage:

`find Cookie`

Finds and lists all the tasks containing "cookie" in its description. This search is case-insensitive.

### `bye`

Terminates the application with immediate effect.