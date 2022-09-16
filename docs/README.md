# Carbon User Guide

Welcome to Carbon, a fast and sleek task management CLI/GUI tool.

## Features

### Adding Tasks

Users can add three types of tasks:

1. To-do
2. Deadline
3. Event

The specific commands for these three types are covered under the Usage section [here](#add-tasks).

### Deleting Tasks

Users can delete any task already added. Instructions to use are listed [here](#delete-tasks).

### Viewing Tasks

All added tasks can be listed out and viewed with [a single command](#view-tasks).

### Marking Tasks

A task that has been added can be marked as completed or unmarked to mean uncompleted. The command to do this is [here](#mark-tasks).

### Finding Tasks

Tasks can be searched by using a word prompt, using [this command](#find-tasks).

## Usage

### Add Tasks

Generally, the command to add tasks are similar, but they differ in that the initial keyword must be the type of the task to be added. Additionally, deadlines and events require user input to specify time as well.

#### Todo

Adds a to-do task. The first word of the command (`todo`) is case-insensitive.

**Command Format**

```
todo {task-description}
```

**Example of Usage**

```
todo Buy groceries
```

**Expected Outcome**:

Carbon will return the full To-do task if it is added successfully.

```
I have added:
(TODO) [] Buy groceries !
```

#### Deadline

Adds a deadline task. Requires a time parameter in the form of "YYYY-MM-DD", included after a flag "/by". Time can be included optionally as well, in 24hr Format (HHMM).
The first word of the command (`deadline`) is case-insensitive.

**Command Format**

```
deadline {task-description} /by {time}
```

**Example of Usage**

```
deadline Math Assignment 1 /by 2022-09-22 2359
```

**Expected Outcome**:

Carbon will return the full Deadline task if it is added successfully.

```
I have added:
(DEAD) [] Math Assignment 1 < Sep 22 2022 23:59
```

#### Event

Adds a event task. Requires a time parameter in the form of "YYYY-MM-DD", included after a flag "/at". Time can be included optionally as well, in 24hr format (HHMM).
The first word of the command (`event`) is case-insensitive.

**Command Format**

```
event {task-description} /at {time}
```

**Example of Usage**

```
event Hall Symposium /at 2022-09-27 1500
```

**Expected Outcome**:

Carbon will return the full Event task if it is added successfully.

```
I have added:
(EVNT) [] Hall Symposium @ Sep 27 2022 15:00
```

### Delete Tasks

Deletes a task that has already been added. The way of deletion is through specifying the number of the item listed. Deletion of task through task description is **not supported**.
The first word of the command (`delete`) is case-insensitive.

**Command Format**

```
delete {item-number}
```

**Example of Usage**

```
delete 1
```

**Expected Outcome**

Carbon will return the task that has been deleted, if the command is confirmed.

```
I have removed:
(TODO) [] Buy groceries !
```

### View Tasks

Lists out all current tasks, marked or unmarked, for users to view. The tasks are also numbered with their corresponding index.
The command `list` is case-insensitive.

**Command Format**

```
list
```

**Example of Usage**

```
list
```

**Expected Outcome**

Carbon will return a list of all tasks.

```
Here are the lists so far.

1: (TODO) [] Buy groceries !
2: (DEAD) [] Math Assignment 1 < Sep 22 2022 23:59
3: (EVNT) [] Hall Symposium @ Sep 27 2022 15:00
```

### Mark Tasks

#### Mark as Completed

Marks a task as completed. The way to specify which task is through the item number that is accesible through listing the tasks.
The first word of the command (`mark`) is case-insensitive.

**Command Format**

```
mark {task-number}
```

**Example of Usage**

```
mark 1
```

**Expected Outcome**

Carbon will return the task, showing that it is marked.

```
Got it!

(TODO) [X] Buy groceries !
```

#### Mark as Uncompleted (Unmark)

Marks a task as uncompleted. The way to specify which task is through the item number that is accesible through listing the tasks.
The first word of the command (`unmark`) is case-insensitive.

**Command Format**

```
unmark {task-number}
```

**Example of Usage**

```
unmark 1
```

**Expected Outcome**

Carbon will return the task, showing that it is marked.

```
Got it!

(TODO) [] Buy groceries !
```

### Find Tasks

Searches for tasks using a word prompt.
The first word of the command (`find`) is case-insensitive.

**Command Format**

```
find {text-prompt}
```

**Example of Usage**

```
find grocer
```

**Expected Outcome**

Carbon will list all out tasks that matches the text input provided.

```
Here's what I could find.

1: (TODO) [] Buy groceries !
```

### Exit

Exits the task management application. The command is case-insensitive.

**Command Format**

```
bye
```

**Example of Usage**

```
bye
```

**Expected Outcome**

Carbon will reply with a short farewell script, before closing the program after a few seconds.
