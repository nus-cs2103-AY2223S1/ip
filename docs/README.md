# User Guide

## Features 

### Add task

User can add todo, deadline and event tasks.

### List tasks

User can list all tasks.

### Mark task as done

User can mark a task as done.

### Delete task

User can delete a task.

### Find task

User can find a task by specific keyword.

### Exit

User can exit the program.

### Better find task

User can find a task with a general keyword.

## Usage

### `todo` - Add todo task

Add a todo task to the task list.

Example of usage: 

`todo read book`

Expected outcome:

Adds a ToDo task `read book` to the task list.

`Got it. I've added this task:`

`[T][✘] read book`

### `deadline` - Add deadline task

Add a deadline task to the task list.

Example of usage:

`deadline return book /by 2020-08-08`

Expected outcome:

Adds a Deadline task `return book` to the task list.

`Got it. I've added this task:`

`[D][✘] return book (by: Aug 8 2020)`

### `event` - Add event task

Add an event task to the task list.

Example of usage:

`event project meeting /at 2020-08-08`

Expected outcome:

Adds an Event task `project meeting` to the task list.

`Got it. I've added this task:`

`[E][✘] project meeting (at: Aug 8 2020)`

### `list` - List tasks

List all tasks in the task list.

Example of usage:

`list`

Expected outcome:

Lists all tasks in the task list.

`Here are the tasks in your list:`

`1. [T][✘] read book`

`2. [D][✘] return book (by: Aug 8 2020)`

`3. [E][✘] project meeting (at: Aug 8 2020)`

### `done` - Mark task as done

Mark a task as done.

Example of usage:

`done 1`

Expected outcome:

Marks the first task in the task list as done.

`Nice! I've marked this task as done:`

`[T][✓] read book`

### `delete` - Delete task

Delete a task.

Example of usage:

`delete 1`

Expected outcome:

Deletes the first task in the task list.

`Noted. I've removed this task:`

`[T][✓] read book`

`Now you have 2 tasks in the list.`

### `find` - Find task

Find a task by specific keyword.

Example of usage:

`find book`

Expected outcome:

Finds all tasks with keyword `book`.

`Here are the matching tasks in your list:`

`1. [D][✘] return book (by: Aug 8 2020)`

### `bye` - Exit

Exit the program.

Example of usage:

`bye`

Expected outcome:

Exits the program.

`Bye. Hope to see you again soon!`

### `find` - Better find task

Find a task by general keyword.

Example of usage:

`find bo`

Expected outcome:

Finds all tasks with keyword `bo`.

`Here are the matching tasks in your list:`

`1. [D][✘] return book (by: Aug 8 2020)`

## Command Summary

Action | Format, Examples

------------ | -------------

todo | `todo DESCRIPTION` <br> e.g., `todo read book`

deadline | `deadline DESCRIPTION /by DATE` <br> e.g., `deadline return book /by 2020-08-08`

event | `event DESCRIPTION /at DATE` <br> e.g., `event project meeting /at 2020-08-08`

list | `list`

done | `done INDEX` <br> e.g., `done 1`

delete | `delete INDEX` <br> e.g., `delete 1`

find | `find KEYWORD` <br> e.g., `find book`

bye | `bye`

find | `find KEYWORD` <br> e.g., `find bo`

## Acknowledgements

- [JavaFX Tutorial](https://se-education.org/guides/tutorials/javaFx.html)

- [JavaFX Tutorial Part 2](https://se-education.org/guides/tutorials/javaFxPart2.html)

- [JavaFX Tutorial Part 3](https://se-education.org/guides/tutorials/javaFxPart3.html)

- [JavaFX Tutorial Part 4](https://se-education.org/guides/tutorials/javaFxPart4.html)

##Appendix A: Product Scope

###Target user profile:

- has a need to manage a list of tasks

- can type fast

- prefers typing to mouse interactions

- is reasonably comfortable using CLI apps

###Value proposition:

- manage tasks faster than a typical mouse/GUI driven app

- manage tasks more efficiently than a typical mouse/GUI driven app

- learn how to use CLI apps
```
expected output
```
