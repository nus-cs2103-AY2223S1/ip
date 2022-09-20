# User Guide

## Quick Start
1. Download the latest duke.jar.
2. Copy the file to the folder you want to use as the home folder for your Duke.
3. Open a command window in that folder.
4. Run the command java -jar duke.jar (i.e., run the command in the same folder as the jar file).
5. Type the command in the command box and press Enter to execute it.
6. Refer to the Features below for details of each command.
7. Refer to the Usage below for each command.

## Features 

### Add Todo: todo
Adds a Todo by writing todo {description of todo}.
A message will tell you how many Tasks you currently have in your list.

Note that the description of a todo cannot be empty.

### Add Deadline: deadline
Add a Deadline by writing deadline {description of deadline} /by {the deadline of the task}.
A message will tell you how many Tasks you currently have in your list.

Note that the deadline must be in yyyy-MM-dd hh:mm AM/PM format.
Also note that the description and deadline of a Deadline cannot be empty.

### Add Event: event
Add an Event by writing event {description of event} /at {the time of your event}.
A message will tell you how many Tasks you currently have in your list.

Note that the description and time of an Event cannot be empty.

### Delete Task: delete
Delete a task from your list by writing delete {index of Task}.

The specified Task will be removed from the list.
A message will tell you how many Tasks you currently have in your list.

### Mark Task as done: mark
Mark a task as done by writing mark {index of Task}.

A cross can be seen next to the task index when all tasks are listed, which indicates that this Task is completed.

### Mark Task as not done: unmark
Mark a task as not done by writing unmark {index of Task}.

No cross can be seen at the left of the task when all tasks are listed, which indicates that this Task is not completed.

### List all Tasks: list
Shows a message listing all the Tasks currently in your list.

This list also indicates which Tasks are complete.

### Find Tasks: find
Find tasks matching the given String by writing find {string to match}.

Shows a list of all tasks with descriptions that match the given String.

### Say goodbye: bye
Say goodbye to Duke.

Duke will bid you farewell.

## Usage

### `todo` - Add a Todo to the list

A Task of type Todo will be added to your Task List.

Example of usage: 

`todo example`

Expected outcome:

A message will tell you how many Tasks you currently have in your list.

```
Got it. I've added this task:
[T][ ] example
Now you have 1 task(s) in the list.
```

### `deadline` - Add a Deadline to the list

A Task of type Deadline will be added to your Task List.

Example of usage:

`deadline example /by 2022-05-05 11:00 PM`

Expected outcome:

A message will tell you how many Tasks you currently have in your list.

```
Got it. I've added this task:
[D][ ] example (by: 05 May 2022 11:00 PM)
Now you have 1 task(s) in the list.
```

### `event` - Add an Event to the list

A Task of type Event will be added to your Task List.

Example of usage:

`event example /at tomorrow`

Expected outcome:

A message will tell you how many Tasks you currently have in your list.

```
Got it. I've added this task:
[E][ ] example (at: tomorrow)
Now you have 1 task(s) in the list.
```

### `delete` - Remove a Task from the list

The specified Task will be removed from your Task List.

Example of usage:

`delete 1`

Expected outcome:

A message will tell you how many Tasks you currently have in your list.

```
Noted. I've removed this task:
[T][ ] example
Now you have 0 task(s) in the list.
```

### `mark` - Mark a Task as done

The specified Task will be marked as complete.

Example of usage:

`mark 1`

Expected outcome:

A message will tell you which Task has been marked done.

```
Nice! I've marked this task as done:
[T][X] example
```

### `unmark` - Mark a Task as not done

The specified Task will be marked as not complete.

Example of usage:

`unmark 1`

Expected outcome:

A message will tell you which Task has been marked not done.

```
OK, I've marked this task as not done yet:
[T][ ] example
```

### `list` - List all Tasks

The Tasks in your Task List will be listed.

Example of usage:

`list`

Expected outcome:

A message will list all the Tasks in your list.

```
Here are the tasks in your list:
1. [T][ ] example
```

### `find` - Find tasks.

Find tasks matching the given String

Example of usage:

`find example`

Expected outcome:

A message will list all the Tasks with matching descriptions.

```
Here are the matching tasks in your list:
1. [T][ ] example
```
