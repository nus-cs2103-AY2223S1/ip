# User Guide


## Features 
___

### Add Tasks

Add 3 kinds of tasks to Duke: ToDos, Deadlines, and Events!

### Mark Tasks

Mark and unmark your tasks when you are done/not done with them!

### Delete Tasks

Don't want to see the task anymore? Simply delete it!

### Edit Tasks

Fat fingers caused a typo? Not to worry, just edit it!

### Find Tasks

Too many tasks to keep track of? Forgot the exact description of a task?
Fret not, for Duke has a simple search function for you to find any task!

### List Tasks

Want to know what tasks you currently have? Duke can show you all!

## Usage
___

### `todo DESCRIPTION` - Add a todo task

Add a todo task with the given DESCRIPTION

Example of usage: 

`todo return book`

Expected outcome:

```
> Got it. I've added this task:
[T][ ] return book
Now you have 1 tasks in the list
```
___

### `event DESCRIPTION /at DATETIME` - Add an event task

Add an event task with the given DESCRIPTION and DATETIME

DATETIME format:

` yyyy-MM-dd HH:mm`

Example of usage:

`event book reading /at 2022-09-14 18:00`

Expected outcome:

```
> Got it. I've added this task:
[E][ ] book reading (14-Sep-2022 18:00)
Now you have 2 tasks in the list
```
___

### `deadline DESCRIPTION /at DATETIME` - Add a deadline task

Add a deadline task with the given DESCRIPTION and DATETIME

DATETIME format:

` yyyy-MM-dd HH:mm`

Example of usage:

`deadline submit homework /by 2022-09-14 18:00`

Expected outcome:

```
> Got it. I've added this task:
[D][ ] submit homework (14-Sep-2022 18:00)
Now you have 3 tasks in the list
```
___

### `list` - List all tasks

List all current tasks in the tasklist

Example of usage:

`list`

Expected outcome:

```
> Here are the tasks in your list:
[T][ ] return book
[E][ ] book reading (14-Sep-2022 18:00)
[D][ ] submit homework (14-Sep-2022 18:00)
```
___

### `edit INDEX DESCRIPTION` - Edit a task

Edit the task at INDEX with the given DESCRIPTION

Example of usage:

` edit 1 read book`

Expected outcome:

```
> I have edited the description of the task. It is now:
[T][ ] read book
```
___

### `mark INDEX` - Mark a task

Mark the task at INDEX as done

Example of usage:

`mark 1`

Expected outcome:

```
> Nice! I've marked this task as done:
[T][X] read book
```
___

### `unmark INDEX` - Unmark a task

Mark the task at INDEX as not done

Example of usage:

`unmark 1`

Expected outcome:

```
> Ok, I've marked this task as not done yet:
[T][] read book
```
___

### `delete INDEX` - Delete a task

Delete the task at INDEX

Example of usage:

`delete 2`

Expected outcome:

```
> Noted, I've removed this task:
[E][ ] book reading (14-Sep-2022 18:00)
Now you have 2 tasks in the list
```
___

### `find DESCRIPTION` - Find a task

Find all tasks in the tasklist that contain DESCRIPTION

Example of usage:

`find book`

Expected outcome:

```
> Here are the matching tasks in your list:
[T][ ] read book
```
___

### `bye` - Exit the program



Example of usage:

`bye`

___

## For advanced users
___
The tasklist file is located in {project_root}/duke/duke.txt

The format for tasks is 

`[Task type] [Checkmark] Task description (Datetime) `

Task type:
* T for todo tasks
* D for deadline tasks
* E for event tasks

Checkmark
* X for done
* Empty for not done
