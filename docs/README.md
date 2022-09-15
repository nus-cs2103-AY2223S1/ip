# User Guide
DaDuke Bot is a desktop app for managing your tasks, optimized for use via a
Command Line Interface while still having the benefits of a Graphical User
Interface.

<br> 

<img src="Ui.png" />

<br>

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer
2. Download the latest`duke.jar` from [here](https://github.com/Jnwkm/ip/releases/tag/v2.0).
3. Copy file to the folder you want to use as the home folder for DaDuke.
4. Double-click the file to start the app & let DaDuke do the rest!

## Features

### `todo` - Adding Todos

Adds a todo task to to-do list.

Format: `todo TODO_TASK`

Example of usage:

`todo chinese homework`

Expected outcome:
```
Okay! I've added this task: 
[T][] chinese homework
Now You have 1 task in your list.
```
<br>

### `deadline` - Adding Deadlines

Adds deadlines that have due dates.

Format: `deadline DEADLINE_TASK /by DD-MM-YYYY HHMM`

Example of usage:
`deadline tutorial 1 /by 01-11-2000 1800`

Expected outcome:
```
Okay! I've added this task: 
[D][] tutorial 1 (by: Wednesday, 1 November 2000 6:00PM)
Now You have 1 task in your list.
```
<br>

### `event` - Adding Events

Adds events that have a date and time.

Format: `event EVENT_TASK /at dd-MM-yyyy HHmm`

Example of usage:
`event party /at 01-12-2005 2100`

Expected outcome:
```
Okay! I've added this task: 
[E][] party (at: Thursday, 1 December 2005 9:00PM)
Now You have 1 task in your list.
```
<br>

### `mark` - Marking Tasks As Done

Marks specified task as done.

Format: `mark NUMBER`

Example of usage: \
To mark task 1 as done:
`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T/D/E] [X] chinese homework
```
<br>

### `unmark` - Unmarking tasks

Unmarks specified task as not done.

Format: `unmark NUMBER`

Example of usage: \
To mark task 1 as not done:
`unmark 1`

Expected outcome:
```
Nice! I've marked this task as not done yet:
[T/D/E] [] chinese homework
```
<br>

### `delete` - Deleting tasks

Deletes a completed task from the TaskList.

Format: `delete NUMBER`

Example of usage: \
To delete task 1 from TaskList:
`delete 1`

Expected outcome:
```
Noted. I've removed this task: 
[T][] chinese homework
Now You have 3 tasks in your list.
```
<br>

### `list` - Listing Tasks

Lists down all the tasks you have in your TaskList.

Format: `list`

Example of usage:

`list`

Expected outcome:
```
Here are the tasks that you have:
1. [D][] 2100 assignment (by: 29 Sep 2022 23:59)
2. [T][] chinese homework 
3. [E][] going out with friends (at: 25 Dec 2022 22:00)
```
<br>

### `find` - Finding Tasks by Keyword

Searches for task descriptions or dates in TaskList that matches the case insensitive keyword.

Format: `find KEYWORD`

Example of usage with description:

`find chi`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][] chinese homework
```
<br> 

Example of usage with date:

`find December`

Expected outcome:
```
Here are the matching tasks in your list:
1. E][] going out with friends (at: 25 Dec 2022 22:00)
```
<br>

