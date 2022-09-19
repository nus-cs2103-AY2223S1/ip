# User Guide

---

## Overview 

---

Duke is a chatbot that can track your tasks, consisting of Todo, Deadline and Event tasks. 
You are also able to archive tasks, so that you can refer to them in the future. 

##Installation Steps

---
1. Go to .. 
2. Download the latest release of Duke. 
3. In the terminal, go to the directory and execute command `java -jar {filename}.jar`. 
Where `{filename}` is the file name of Duke downloaded.


## Features

---

The following features are available on Duke: 

### Tasks

- Tasks
  - Adding tasks
  - Deleting tasks
  - Listing tasks
  - Marking tasks as completed or incomplete
  - Finding tasks by name
  - Archiving tasks

- Saving 
  - Autosaving of tasks at the end of every command

## Usage

---

### `todo` - Creates a Todo task

Creates and adds a todo task with the description provided by the user. 
The task is marked as incomplete by default.

Format: `todo DESCRIPTION`
- DESCRIPTION is the title/description of task 

Example of usage:

`todo CS2103T Project`

Expected outcome:

A new todo task titled `CS2103T Project` will be added. 

```
Got it. I've added this task: 
[T][] CS2103T Project
Now you have 1 task in the list.
```

### `deadline` - Creates a Deadline task

Creates and adds a Deadline task with the description provided by the user
and a compulsory date field, and an optional time field. The task will be 
marked as incomplete by default.

Format: `deadline DESCRIPTION /by DATE TIME`
- DESCRIPTION is the title/description of task
- DATE is the date of the deadline task in format `dd/mm/yyyy` or `yyyy-mm-dd`
- TIME is the time of the deadline task in format `HHMM`

Example of usage:

`deadline CS2103T IP /by 16/09/2022 1800`

Expected outcome:

A new deadline task titled `CS2103T Project` with deadline date
`Sep 16 2022` and time `6:00pm` will be added.

```
Got it. I've added this task: 
[D][] CS2103T IP (by: Sep 16 2022, 6:00pm)
Now you have 2 tasks in the list.
```

### `event` - Creates an Event task

Creates and adds a Event task with the description provided by the user
and a compulsory date field, and an optional time field. The task will be
marked as incomplete by default.

Format: `event DESCRIPTION /at DATE TIME`
- DESCRIPTION is the title/description of task
- DATE is the date of the deadline task in format `dd/mm/yyyy` or `yyyy-mm-dd`
- TIME is the time of the deadline task in format `HHMM`

Example of usage:

`event CS2103T Group Meeting /at 19/09/2022 0900`

Expected outcome:

A new event task titled `CS2103T Group Meeting` with event date
`Sep 19 2022` and time `9:00am` will be added.

```
Got it. I've added this task: 
[E][] CS2103T IP (by: Sep 19 2022, 9:00am)
Now you have 3 tasks in the list.
```

### `list` - Lists all the tasks of user

Lists down all the tasks that are currently in users active task list. 

The indices attached to the task shown in the list will be the index used for 
the `mark`, `unmark`, `delete` and `archive` commands. These indices attached
to the task may change on `delete` and `archive`. 

Format: `list`

Expected outcome:

```
1. [T][] CS2103T Project
2. [D][] CS2103T IP (by: Sep 16 2022, 6:00pm)
3. [E][] CS2103T IP (by: Sep 19 2022, 9:00am)
```

### `mark` - Marks a task as completed

Marks a task corresponding to a specific index given by the user as completed. 
Index of task can be found by the `list` command.

Format: `mark INDEX`
- INDEX refers to the index of the task to be marked as done. The index should be a positive integer.

Example of usage:

`mark 1`

Expected outcome:

The task at index 1 in the list will be marked as completed.

```
Nice! I have marked this task as done: 
[T][X] CS2103T Project
```

### `unmark` - Marks a task as incomplete

Marks a task corresponding to a specific index given by the user as incomplete.
Index of task can be found by the `list` command.

Format: `mark INDEX`
- INDEX refers to the index of the task to be marked as incomplete. The index should be a positive integer.

Example of usage:

`unmark 1`

Expected outcome:

The task at index 1 in the list will be marked as incomplete.

```
OK, I have marked this task as not done yet: 
[T][] CS2103T Project
```

### `delete` - Deletes existing task

Deletes a task corresponding to a specific index given by the user. 
Index of task can be found by the `list` command.

Format: `delete INDEX`
- INDEX refers to the index of the task to be deleted. The index should be a positive integer.

Example of usage:

`delete 1`

Expected outcome:

The task at index 1 in the list will be removed from the list. 

```
Noted, I have removed this task: 
[T][] CS2103T Project
Now you have 2 tasks in the list.
```

### `archive` - Archives existing task / access archive list

Archives task corresponding to a specific index given by the user or all the tasks.
Index of task can be found by the `list` command. You can also access the archive list.

Formats: 

`archive INDEX`
- INDEX refers to the index of the task to be deleted. The index should be a positive integer.

`archive all`
- Archives all the tasks in the active list. 

`archive list`
- Shows the user the tasks in the archive.

Example of usage:

`archive all`

Expected outcome:

All the tasks in the active list should be removed and available to be seen in
the archive list.

```
You have successfully archived all tasks! 
These are the current tasks in your archive list!
1. [D][] CS2103T IP (by: Sep 16 2022, 6:00pm)
2. [E][] CS2103T IP (by: Sep 19 2022, 9:00am)
```

### `find` - Search tasks by keyword 

Finds the list of tasks whose description matches the keyword provided by the user. 
The keyword is case-insensitive.

Format: `find KEYWORD`
- KEYWORD refers to the search keyword.

Example of usage:

`find cs2103T`

Expected outcome:

The list of tasks that contain the `cs2103T`. 

```
Here are the matching tasks in your list. 
1. [T][] CS2103T Project
2. [D][] CS2103T IP (by: Sep 16 2022, 6:00pm)
3. [E][] CS2103T IP (by: Sep 19 2022, 9:00am)
```

### `bye` - Exit duke

Exits the duke chatbot and closes the program.

Format: `bye`
