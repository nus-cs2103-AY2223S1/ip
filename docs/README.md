# User Guide

## Features 
_Here's the list of features that Dave responds to:_

### Feature-List
Lists out tasks the user has added.

### Feature-Todo
Adds a todo to the task list.

### Feature-Deadline
Adds a deadline to the task list.

### Feature-Event
Adds an event to the task list.

### Feature-Fixed Duration Task
Adds a fixed duration task to the task list.

### Feature-Mark
Marks a task.

### Feature-Unmark
Unmarks a task.

### Feature-Delete
Deletes a task from the task list

### Feature-Find
Lists out tasks matching the keyword.

## Usage

### `list` - Lists out tasks the user has added.

Duke goes through all the tasks you have added and lists them out in chronological order.

Example of usage: 

`list`

Expected outcome:

The names, types, completion status & extra information of all previously added tasks.

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: May 2 2023)
3. [F][X] read newspaper (needs: 1 hour)
4. [E][ ] book meeting (at: Dec 1 2022)
```

### `todo` - Adds a todo to the task list

Adds a todo with the name given to the task list.
The task list is automatically saved.

Example of usage: 

`todo read book`

Expected outcome:

Acknowledgement that the todo has been added, containing the name of the todo.

```
added todo: read book
```

### `deadline /by` - Adds a deadline to the task list

Adds a deadline with the name and time given to the task list.
The time marker after /by should follow the format of YYYY-MM-DD and will be automatically reformatted into an easier to read format.
The task list is automatically saved.

Example of usage: 

`deadline return book /by 2023-05-02`

Expected outcome:

Acknowledgement that the deadline has been added, containing the name of the deadline.

```
added deadline: return book
```

### `event /at` - Adds an event to the task list

Adds an event with the name and time given to the task list.
The task list is automatically saved.

Example of usage: 

`event book meeting /at Dec 1 2022`

Expected outcome:

Acknowledgement that the event has been added, containing the name of the event.

```
added event: book meeting
```

### `fdt /for` - Adds a fixed duration task to the task list

Adds a fixed duration task with the name and time given to the task list.
The task list is automatically saved.

Example of usage: 

`fdt read newspaper /for 1 hour`

Expected outcome:

Acknowledgement that the fixed duration task has been added, containing the name of the fixed duration task.

```
added fixed duration task: read newspaper
```

### `mark` - Marks a task

Marks the task as completed via task index.
Task index changes depending on whether a `find` command was recently executed.

Example of usage: 

`mark 1`

Expected outcome:

Marks the first task in the list and sends an acknowledgement of the task marked by name.

```
This task is now marked:
read book
```

### `unmark` - Unmarks a task

Unmarks the task via task index. 
Task index changes depending on whether a `find` command was recently executed.

Example of usage: 

`unmark 1`

Expected outcome:

Unmarks the first task in the list and sends an acknowledgement of the task unmarked by name.

```
This task is now unmarked:
read book
```

### `delete` - Deletes a task

Deletes a task from the task list so that it no longer appears.
The task list is automatically saved.

Example of usage: 

`delete 1`

Expected outcome:

Deletes the first task in the list and sends an acknowledgement of the task deleted by name.

```
This task is now removed:
read book
```

### `find` - Lists out tasks matching the keyword

Lists out only tasks that contain the keyword that is searched for.

Example of usage: 

`find read`

Expected outcome:

The names, types, completion status & extra information of all previously added tasks that contain the keyword within itself.

```
Here are the tasks in your list:
1. [T][ ] read book
2. [F][X] read newspaper (needs: 1 hour)
```

Other commands (e.g. delete, mark, unmark) will also be modified to adhere to the shortened list until another command is executed.

```
unmark 2

Here are the tasks in your list:
1. [T][ ] read book
2. [F][ ] read newspaper (needs: 1 hour)
```