# User Guide

## Features 

### Tasks

Add various types of tasks to your task list.

#### Todo Task

Add a Todo to your task list.

#### Deadline Task

Add a Deadline to your task list. Deadline is a task with a deadline.

#### Event Task

Add an event to your task list. Event is a task with a event time.

### List

Lists all your tasks.

### Mark

Mark a task as done.

### Un-mark

Un-mark a task as done.

### Find

Find tasks by keyword.

### Undo

Undo previous commands.

### Command History

Navigate through your command history with up and down arrow keys.

### Delete

Delete a task.

### Exit

Exit the application with `bye`.

### Resizable

Application is resizable! Just re-size the window as you would do with any other application.

## Usage

### `todo` - Create a Todo task

Creates a Todo task with the specified description.

`todo <description>`

Example of usage: 

`todo Nice task.`

Expected outcome:

A Todo is created with the description and date/time provided.

```
Added this todo!
  [T][ ] Nice task.
Now you have <x> tasks.
```

### `deadline` - Create a Deadline task

Creates a Deadline task with the specified description and deadline.

`deadline <description> /by <date>` (date is YYYY-MM-DD HH:mm)

Example of usage:

`deadline Submit XXX. /by 2022-09-11 2359`

Expected outcome:

A Deadline is created with the description and date/time provided.

```
Added this deadline!
  [D][ ] Submit XXX. (by: 11 Sep 22 23:59)
Now you have <x> tasks.
```

### `event` - Create an Event task

Creates an Event task with the specified description and date/time.

`event <description> /by <date>` (date is YYYY-MM-DD HH:mm)

Example of usage:

`event Nice Event. /at 2022-09-11 2359`

Expected outcome:

An Event is created with the description and date/time provided.

```
Added this event!
  [E][ ] Nice Event. (at: 11 Sep 22 23:59)
Now you have <x> tasks.
```

### `list` - List all tasks

List all your tasks.

`list` (any arguments are ignored)

Example of usage:

`list`

Expected outcome:

A list of all the tasks.

```
1. [T][ ] Nice task.
2. [D][ ] Submit XXX (by: 11 Sep 22 23:59).
3. [E][ ] Nice Event. (at: 11 Sep 22 23:59).
```

### `mark` - Mark task as done

Marks a task as done.

`mark <index>`

Example of usage:

`mark 2`

Expected outcome:

Task 2 will be marked as done.

```
Marked task 2 as done!
  [D][X] Submit XXX. (by: 11 Sep 22 23:59).
```

### `unmark` - Un-mark task as done

Marks a task as done.

`unmark <index>`

Example of usage:

`unmark 2`

Expected outcome:

Task 2 will be marked as not done.

```
Marked task 2 as not done!
  [D][ ] Submit XXX. (by: 11 Sep 22 23:59).
```

### `find` - Find tasks by keywords

Finds tasks by keywords.

`find <keywords>`

Example of usage:

`find Nice`

Expected outcome:

A list of tasks that match the keywords provided.

```
Here are the matching tasks in your list!
1. [T][ ] Nice task.
2. [E][ ] Nice Event. (at: 11 Sep 22 23:59).
```

### `undo` - Undoes the previous command

Undoes the previous command.

`undo` (any arguments are ignored)

Example of usage:

`undo`

Expected outcome:

The previous command will be undone, if there are any.

```
Undoing previous command!
```

### `delete` - Delete a task

Deletes a task.

`delete <index>`

Example of usage:

`delete 1`

Expected outcome:

Task at index 1 is deleted.

```
Removing this task!
  [T][ ] Nice task.
Now you have <x> tasks left.
```

### `bye` - Exit the application

Exits the application.

`bye` (any arguments are ignored)

Example of usage:

`bye`

Expected outcome:

The application closes.
