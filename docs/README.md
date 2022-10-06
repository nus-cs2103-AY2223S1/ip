# Dude's User Guide

## Features 

### Task manager

Keeps track of a list of tasks that you added.

### Supports storage

It can pick up from where you left off the previous round without needing to re-add all the tasks again.

### Supports mass operations for certain commands.

It is possible to mark multiple tasks as completed or not completed in a single command, or to delete multiple tasks in a single command.

## Usage

### `list` - Shows current tasks in the list.

Syntax: `list`

Example of usage:

`list`

Expected outcome:

A list of tasks being added, excluding deleted tasks, will be shown.

### `todo`, `deadline`, `event` - Adds a todo/deadline/event type task respectively

Syntaxes:
- `todo <task>`
- `deadline <task> /by <deadline>`
- `event <task> /at <time>`

Example of usage: 

- `todo return book`
- `deadline finish economics essay /by tomorrow`
- `event birthday party /at 6pm`

Expected outcome:

- Added a todo type task called "return book"
- Added a deadline type task called "finish economics essay" with deadline "tomorrow"
- Added an event type task called "birthday party" with time "6pm"

### `find` - Search for tasks that contain the keyword

Syntax: `find <keyword>`

Example of usage:

`find return`

Expected outcome:

Returns a list of tasks that contain "return" in the task name.

### `mark`, `unmark` - marks tasks as done/not done in the list respectively.

Syntaxes:
- `mark <task numbers in list>`
- `unmark <task numbers in list>`

Example of usage:

- `mark 1 5`
- `unmark 3 2`

Expected outcome:

- Marks tasks 1 and 5 in the list as done.
- Marks tasks 3 and 2 in the list as not done.

### `delete` - deletes tasks from the list.

Syntax: `delete <task numbers in list>`

Example of usage:

`delete 2 1`

Expected outcome:

Deletes tasks 2 and 1 in the list from the list.

