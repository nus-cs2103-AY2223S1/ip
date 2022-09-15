# Cerebro User Guide

Cerebro is an interactive Command line based application used to keep track of your daily tasks such as events, tasks and deadlines so that you can plan your schedule efficiently.

## Table of Contents

- [Features](#features)
- [Available Commands](#commands)
    - [`todo`](#todo)
    - [`deadline`](#deadline)
    - [`event`](#event)
    - [`list`](#list)
    - [`mark`](#mark)
    - [`unmark`](#unmark)
    - [`delete`](#delete)
    - [`find`](#find)
    - [`bye`](#bye)

## Features

1. ### Keep track of your tasks

Cerebro allows you to **save, store and retrieve** your tasks, even after closing the application so that you can view your tasks anytime you need to.

2. ### Add a different type of tasks

Cerebro allows you to add a variety of tasks like **Todos**, **Deadlines**, and **Events**.

3. ### Find tasks with keywords

You can also search for tasks using keywords so that you can find your tasks with ease as your list of tasks continue growing. 

## Commands

### `todo`

Add new todo to task list.

**Syntax:** `todo <description>`

**Sample usage:** `todo Go to the Gym`

Expected outcome: A new todo will be added to the end of the task list.

```
Gotcha! I've added this task:
  [T][ ] Go to the Gym
Now you have 5 tasks in your list.
```

### `deadline`

Add new deadline with a due date to task list.

**Syntax:** `todo <description> /by <due date>`

- Deadline must be in `dd/MM/yyyy HH:mm` or `dd-MM-yyyy HH:mm` format.

**Sample usage:** `deadline IP Submission /by 16-09-2022 23:59`

Expected outcome: A new deadline will be added to the end of the task list.

```
Gotcha! I've added this task:
  [D][ ] IP Submission (by: Fri, Sep 16, 11:59 PM)
Now you have 6 tasks in your list.
```

### `event`

Add new event with start time to task list.

**Syntax:** `event <description> /at <start time>`

- Deadline must be in `dd/MM/yyyy HH:mm` or `dd-MM-yyyy HH:mm` format.

**Sample usage:** `event CS2100 Midterms /at 25-09-2022 09:00`

Expected outcome: A new event will be added to the end of the task list.

```
Gotcha! I've added this task:
  [D][ ] CS2100 Midterms (at: Sun, Sep 25, 09:00 AM)
Now you have 7 tasks in your list.
```

### `list`

Display the task list.

**Syntax:** `list`

- `[T]` represents a todo. `[D]` represents a deadline. `[E]` represents an event.
- `[X]` indicates task is completed. `[ ]` indicates task is incomplete.

Expected outcome:
```
Here are the tasks in your list.
	1. [D][ ] Complete Week 2 Tasks (by: Mon, Sep 26, 06:00 PM)
	2. [T][ ] Gym
	3. [E][ ] CS2103T Lecture (at: Wed, Sep 28, 09:00 AM)
```

### `mark`

Mark a task as done.

**Syntax:** `mark <task number>`

**Sample usage:** `task 1`

Expected outcome: The first task will be marked as done.

```
Nice! I;ve marked this task as done:
  [T][X] Go to the Gym
```

### `unmark`

Mark a task as incomplete.

**Syntax:** `unmark <task number>`

**Sample usage:** `unmark 1`

Expected outcome: The first task will be marked as incomplete.

```
OK, I've marked this task as not done yet:
  [T][X] Go to the Gym
```

### `delete`

Delete a task from the task list.

**Syntax:** `delete <task number>`

**Sample usage:** `delete 1`

Expected outcome: The first task will be deleted.

```
Noted. I've removed this task:
  [T][ ] Go to the Gym
```

### `find`

Search for a task from the task list.

**Syntax:** `find <search query>`

**Sample usage:** `find gym`

- Search is case-sensitive.

Expected outcome: A list of tasks with descriptions containing the given query will be displayed.

```
1. [T][ ] Go to the gym
2. [T][X] Sign up for Gym
```

### `bye`

Saves your tasks to your local storage and exits the program

**Syntax:** `bye`