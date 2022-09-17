# User Guide

Duke is an app for managing tasks.
It uses a Command Line Interface (CLI) with a basic Graphical User Interface (GUI).
It is optimized for users who can type fast and have some experience with CLIs.


## Features

### Task List

- 3 types of tasks: ToDos, Deadlines, and Events
- Creating, displaying, and deleting tasks
- Marking and unmarking of tasks as done
- Searching of tasks by name
- Sorting of tasks by name


## Usage

### `help` - View help

Displays a list of available commands.

#### Example of usage:

`help`

#### Expected output:

```
Available commands:

hello
bye
list
mark
unmark
todo
deadline
event
delete
find
sort
help
```

<br/>


### `bye` - Exit application

Saves the current task list and quits the application.

#### Example of usage:

`bye`

#### Expected outcome:

Duke displays the exit message, saves and quits. The window is only closed the
next time the user sends an input (i.e. presses enter or the send button).

#### Expected output:

```
Goodbye
```

<br/>


### `list` - View all tasks

Displays all tasks in a list format.

#### Example of usage:

`list`

#### Expected outcome:

Duke displays all tasks in a numbered list, along with information about the
whether the task is done, and the date of the task (if any).

#### Expected output:

```
1. [E][X] midterms 1 (at: 2022-02-02)
2. [E][ ] midterms 2 (at: 2022-01-01)
3. [D][X] assignment 7 (by: 2022-01-01)
4. [T][X] project D

You have 4 task(s) in your list
```

<br/>


### `mark` - Mark a task as done

Sets the completion status of the task at the specified index as done.
If the task is already marked as done, nothing will be changed.

#### Command format:

`mark [index of the task]`

#### Example of usage:

`mark 2`

#### Expected outcome:

Duke marks the task at index 2 as done.

#### Expected output:

```
Task marked as complete:
[E][X] midterms 2 (at: 2022-01-01)
```

<br/>


### `unmark` - Mark a task as not done

Sets the completion status of the task at the specified index as not done.
If the task is already marked as not done, nothing will be changed.

#### Command format:

`unmark [index of the task]`

#### Example of usage:

`unmark 1`

#### Expected outcome:

Duke marks the task at index 1 as not done.

#### Expected output:

```
Task marked as incomplete:
[E][ ] midterms 1 (at: 2022-02-02)
```

<br/>


### `todo` - Create a ToDo

Adds a ToDo task to the list. ToDos are tasks that do not have
a date associated with them.

#### Command format:

`todo [name of the task]`

#### Example of usage:

`todo set up repository`

#### Expected outcome:

Duke creates a ToDo task called 'set up repository' and adds it to the list of tasks.

#### Expected output:

```
Task added:
[T][ ] set up repository
You now have 5 task(s) in your list
```

<br/>


### `deadline` - Create a Deadline

Adds a Deadline task to the list. Deadlines are tasks that need to be 
completed by a certain date.

#### Command format:

`deadline [name of the task] /by [date in the format YYYY-MM-DD]`

#### Example of usage:

`deadline implement feature X /by 2022-10-31`

#### Expected outcome:

Duke creates a Deadline task called "**implement feature X**" that should 
be completed by "**2022-10-31 (31st October 2022)**" and 
adds it to the list of tasks.

#### Expected output:

```
Task added:
[D][ ] implement feature x (by: 2022-10-31)
You now have 6 task(s) in your list
```

<br/>


### `event` - Create an Event

Adds a Event task to the list. Events are tasks that start at a certain date.

#### Command format:

`event [name of the task] /at [date in the format YYYY-MM-DD]`

#### Example of usage:

`event product demo /at 2022-11-01`

#### Expected outcome:

Duke creates an Event task called "**product demo**" that
starts on "**2022-11-301 (1st November 2022)**" and
adds it to the list of tasks.

#### Expected output:

```
Task added:
[E][ ] product demo (at: 2022-11-01)
You now have 7 task(s) in your list
```

<br/>


### `delete` - Delete a task

Deletes the task at the specified index from the list.

#### Command format:

`delete [index of the task]`

#### Example of usage:

`delete 1`

#### Expected outcome:

Duke removes the task at index 1 (the first task) from the list.

#### Expected output:

```
Task deleted:
[E][ ] midterm 1 (at: 2022-02-02)
You now have 6 task(s) in your list
```

<br/>


### `find` - Search for tasks

Looks up and displays a list of tasks whose name contains the specified
string of text.

#### Command format:

`find [string of text to check for]`

#### Example of usage:

`find midt`

#### Expected outcome:

Duke displays a list of tasks whose name contains "**midt**".

#### Expected output:

```
Displaying search results for "midt":
1. [E][ ] midterms 1 (at: 2022-02-02)
2. [E][ ] midterms 2 (at: 2022-01-01)

2 task(s) found
```

<br/>


### `sort` - Sort tasks by name

Sorts all tasks in the list by name alphabetically in ascending or
descending order, specified by /a and /d respectively, then displays the list.

#### Command format:

`sort /a` - sort in ascending order

`sort /d` - sort in descending order

#### Example of usage:

`sort /a`

#### Expected outcome:

Duke sorts all tasks by name in alphabetical ascending order, then displays
the list.

#### Expected output:

```
Tasks sorted by name in ascending order
1. [D][ ] implement feature x (by: 2022-10-31)
2. [E][ ] midterms 1 (at: 2022-02-02)
3. [E][ ] midterms 2 (at: 2022-01-01)
4. [E][ ] product demo (at: 2022-11-01)
5. [T][ ] set up repository

You have 5 task(s) in your list
```

<br/>
