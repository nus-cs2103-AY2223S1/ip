# User Guide
BobTheBot is an application made for **managing tasks**. The application comes with all
the benefits of a sleek GUI and allows you to stay organised in a hassle-free way!

## Quick Start
1. Ensure that you have `java 11` installed on your device.
2. Download the latest version of BobTheBot

---

## Features 
BobTheBot handles **3 types of tasks** and various **commands** to handle such tasks

### ToDo
ToDos are tasks which you can add that do not have a date attached. Can be used for non-urgent tasks.

### Deadline
Deadlines are tasks which need to be done before a specific date and time.

### Event
Events are tasks which occur at a specific date and time.

### Mark/Unmark
Allows the user to mark a task as done or undone.

### Delete
Allows the user to delete a task.

### List
Allows the user to see all the tasks Bob is helping to keep track of.

### Find
Finds tasks which correspond with a given keyword.

----

## Usage

### `todo` - Add a todo

Add a todo (an event without specific time and date).

- Command must be given in the following form `todo <description>`

**Example of usage:**

`todo MA2104 homework`

**Expected outcome:**

Todo is added to the list.

```
Got it. I've added this task:
[T][] MA2104 tutorial
You now have 1 task in the list.
```

### `deadline` - Add a deadline

Adds a deadline (a task that needs to be done by a specified date and time).

- Command must be given in the following form: `deadline <description> /by <date> <time>`.
- The `<date>` should be given in the form `YYYY-MM-DD`.
- The `<time>` should be given in the form `HHMM`.
- The keyword `/by` should be used to separate the `<description>` from the `<date> <time>` given.


**Example of usage:**

`deadline CS2103T iP /by 2022-09-16 2359`

**Expected outcome:**

Deadline is added to the list.

```
Got it. I've added this task:
[D][] CS2103T iP (by: Sep 16 2022, 2359)
You now have 2 tasks in the list.
```
------
### `event` - Add an event

Adds an event (a task that occurs at a specific date and time).

- Command must be given in the following form: `event <description> /at <date> <time>`.
- The `<date>` should be given in the form `YYYY-MM-DD`.
- The `<time>` should be given in the form `HHMM`.
- The keyword `/by` should be used to separate the `<description>` from the `<date> <time>` given.


**Example of usage:**

`event CS2103T team meeting /at 2022-09-17 2100`

**Expected outcome:**

Event is added to the list.

```
Got it. I've added this task:
[E][] CS2103T team meeting (at: Sep 17 2022, 2100)
You now have 3 tasks in the list.
```
---
### `mark` - Mark a task as done

Marks a task as done.

- Command must be given in the following form `mark <index>`.
- Index must be the 1-index of the task you wish to mark as done.

**Example of usage:**

`mark 1`

**Expected outcome:**

1st item on the list is marked as done.

```
GOOD JOB! I'm marking this task as done:
[T][X] MA2104 tutorial 
```
----
### `unmark` - Mark a task as undone

Marks a task as undone.

- Command must be given in the following form `unmark <index>`.
- Index must be the 1-index of the task you wish to mark as undone.

**Example of usage:**

`unmark 1`

**Expected outcome:**

1st item on the list is marked as undone.

```
It's sad that you thought you finished your work but didn't.
But alright, marking this task as undone:
[T][] MA2104 tutorial 
```
---
### `delete` - Delete a task

Deletes a task.

- Command must be given in the following form `delete <index>`.
- Index must be the 1-index of the task you wish to delete.

**Example of usage:**

`delete 1`

**Expected outcome:**

1st item on the list deleted.

```
Got it. I've removed this task:
    [T][] MA2104 tutorial
You now have 2 tasks.
```
---

### `list` - Lists all tasks.

Lists all the tasks Bob is helping keep track of.

**Example of usage:** 

`list`

**Expected outcome:**

All items are listed in the order which you added them.

```
Here are your tasks:
1. [D][X] CS2103T iP (by: Sep 16 2022, 2359)
2. [E][] CS2103T team meeting (by: Sep 17 2022, 2100)
3. [T][] MA2104 tutorial
```
-----
### `find` - Find certain tasks

Find items containing a certain keyword.

- Command must be given in the following form `find <keyword>`.

**Example of usage:**

`find CS2103T`

**Expected outcome:**

Finds all tasks in list containing keyword _CS2103T_

```
Here are the matching items on your list:
1. [D][] CS2103T iP (by: Sep 16 2022, 2359)
2. [E][] CS2103T team meeting (by: Sep 17 2022, 2100)
```
---
### `bye` - Quit program

Before quitting program, will remind the user about upcoming deadlines/events and updates the user with
the number of tasks left in the list.

- Command must be given in the following form `find <keyword>`.

**Example of usage:**

`find CS2103T`

**Expected outcome:**

Finds all tasks in list containing keyword _CS2103T_

```
Watch out! These tasks are due soon!
1. [D][] CS2103T iP (by: Sep 16 2022, 2359)
2. [E][] CS2103T team meeting (by: Sep 17 2022, 2100)

Bye! Hope to see you again soon! You still have 4 tasks to do 😊!
```


