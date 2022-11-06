<img src="icon.gif" alt="header" width="250"/>

# Toothless User Guide

## Overview

**Toothless** from How To Train Your Dragon has become your chatbot! With adorable reactions, Toothless can help you keep track of your tasks in a cute yet efficient way!

## Quick start

1. Ensure `Java 11` or above is installed on your computer.

2. Download the latest `Duke.jar` from [here](https://github.com/anthonyhoth/ip/releases/).

3. Copy the file to the folder you want to use as the **home** folder for Toothless.

4. Double click on `Duke.jar` to launch.

5. The interface should look like this:

   ![image](Ui.png)

6. There you go! Toothless is officially on your computer and ready to help you track your tasks!

7. Refer to [Features](#features) for the various commands available and [Usage](#usage) for how to use each one!

## Features

### Add 📝

Add new tasks to your task list. There are 3 kinds of tasks: `todo`, `event`, `deadline`

### Delete 🗑

Deletes task from the task list.

### View 🧾

View all tasks in the task list.

### Find 🔍

Search for tasks in the task list that contain a given keyword.

### Mark 📑

Mark a task as complete.

### Unmark ❌ 📑

Mark a task as incomplete.

### Note 🏷

Add a note to a task.

## Usage

### 📝 `todo` - Adding a Todo task

Adds a todo task with the given task description.

Example of usage:

`todo Go for a run`

Expected outcome:

Success message containing the task and the current number of tasks in your task list.

```
Meow!
Added: [T][] Go for a run
You meow have 1 task(s) in your task list!
```

### 📝 `event` - Adding an Event task

Adds an event task with the given task description and timing in YYYY-MM-DD HHmm format.

Example of usage:

`event Attend talk /at 2020-09-15 1250`

Expected outcome:

```
Meow!
Added: [E][] Attend talk (at: Sep 15 2020 1250)
You meow have 2 task(s) in your task list!
```

### 📝 `deadline` - Adding a Deadline task

Adds an deadline task with the given task name and deadline in YYYY-MM-DD HHmm format.

Example of usage:

`deadline 2103T Quiz /by 2018-10-13 1000`

Expected outcome:

```
Meow!
Added: [D][] 2103T Quiz (by: Oct 13 2018 1000)
You meow have 3 task(s) in your task list!
```

### 🗑 `delete` - Deleting a task

Delete task with a given task index.

Example of usage:

`delete 1`

Success message containing task that has been deleted.

```
Meow! I've re-meow-ved this task:
[T][] Go for a run
```

### 🧾 `list` - Displaying all tasks

Displays all tasks in the task list.

Example of usage:

`list`

Current task list.

```
1. [T][ ] Go for a run
2. [D][ ] 2103T Quiz (by: Oct 13 2018 1000)
```

### 🔍 `find` - Searching for tasks with keyword

Display tasks that contain a given keyword.

Example of usage:

`find talk`

Expected outcome:

```
Here are the tasks that have "talk"!
1. [E][ ] Attend talk (at: Sep 13 2022 1200)
```

### 📑 `mark` - Marking a task as done

Mark a task as done given with a given task index.

Example of usage:

`mark 1`

Expected outcome:

```
Goodjob! This task is meow completed :)
1. [E][X] Attend talk (at: Sep 13 2022 1200)
```

### ❌ 📑 `unmark` - mark a task as undone

Mark a task as undone with a given task index.

Example of usage:

`unmark 1`

Expected outcome:

```
Purr... Ok, I'll meow-rk this task as uncompleted!
1. [E][] Attend talk (at: Sep 13 2022 1200)
```

### 🏷 `note` - Adding a note to a task

Add a note to a task with a given task index.

Example of usage:

`note 1 IMPORTANT`

Expected outcome:

```
Meow! I've added "IMPORTANT" to the task!
1. [E][] Attend talk [Note: IMPORTANT] (at: Sep 13 2022 1200)
```
