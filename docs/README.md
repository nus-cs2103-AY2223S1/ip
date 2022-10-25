# User Guide

1. Install Java 11
2. Download the jar file from the repo
3. Copy the jar file into an empty folder
4. Run the command `java -jar {filename}.jar` e.g., `java -jar Duke.jar` (i.e., run the command in the same folder as the jar file)
5. Enter commands using the GUI


## Features 

### Adding 3 different types of tasks - deadlines, events and todos

Add 3 different types of tasks to Duke

### Mark tasks as  done or undone

Tasks can be marked as done or undone.

### List down current tasks 

List down current tasks in the task list

### Delete tasks

Delete tasks from the task list

### Find tasks

Find tasks that contain the input string

## Usage

### `bye` - Exits the Duke program

Exits the Duke program and closes the GUI.

Example of usage: 

`bye`

Expected outcome:

```
// program exits, GUI closes, no response returned in the GUI
```

### `deadline` - Adds a deadline task to the task list

Adds a deadline task to the task list

Example of usage:

`deadline <description> /by yyyy-mm-dd`

Expected outcome:

```
Got it, I've added this task:
[D][ ] <description>  (by: MMM d yyyy)
Now you have <number> tasks in the list.
```
### `delete` - Deletes the given task from the task list

Deletes the given task from the task list. Task is accessed by its index.

Example of usage:

`delete <index>`

Expected outcome:

```
Noted. I've removed this task:
<task>
Now you have <number> tasks in the list.
```
### `event` - Adds an event task to the task list

Adds an event task to the task list.

Example of usage:

`event <description> /at yyyy-mm-dd`

Expected outcome:

```
Got it, I've added this task:
[E][ ] <description>  (at: MMM d yyyy)
Now you have <number> tasks in the list.
```
### `find` - Finds all matching tasks with the given argument

Finds all matching tasks with the given argument.

Example of usage:

`find <argument>`

Expected outcome:

```
Here are the matching tasks in your list:
<task>
```
### `list` - Describe action

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

```
Here are your list of tasks!
<task>

You have <number> tasks in the list.
```
### `mark` - Marks the given task as done

Marks the given task as done. Task is accessed by its index.

Example of usage:

`mark <index>`

Expected outcome:

<sub>If given task was initially marked as undone:
```
Good job for doing this task!
[<task type>][X] <description>
```
<sub>If given task was initially marked as done:
```
This task has already been marked done.
[<task type>][X] <description>
```
### `todo` - Adds a todo task to the task list

Adds a todo task to the task list.

Example of usage:

`todo <description>`

Expected outcome:

```
Got it, I've added this task:
[T][ ] <description>
Now you have <number> tasks in the list.
```
### `unmark` - Marks the given task as undone

Marks the given task as undone. Task is accessed by its index.

Example of usage:

`unmark <index>`

Expected outcome:

<sub>If given task was initially marked as done:
```
Task shall be marked as undone.
[<task type>][ ] <description>
```
<sub>If given task was initially marked as undone:
```
This task has already been marked undone.
[<task type>][ ] <description>
```
