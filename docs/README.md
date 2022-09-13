# Tutter User Guide

## Features 

### Add 

Add new tasks to your task list. Tasks can be of the following types: (1) `Todo` (2) `event` (3) `deadline`

### Delete 

Deletes task from task list given the task index.

### View

View current task list.

### Find

Search for list of tasks in task list that contain a given search term.

### Mark 

Mark a task as complete in the task list.

### Unmark

Mark a task as incomplete in the task list. Tasks are marked as incomplete by default.

<hr />

## Usage

### `todo` - adds a todo task to the task list.

Adds a todo task with the given task name. 
Displays a success message when task is added successfully.

Example of usage: 

`todo Buy Lunch`

Expected outcome:

Success message containing the task and the current number of tasks in your task list.

```
You have added "[T][ ] Buy Lunch" into your Task List!
You have 1 task in your Task List!
```

### `event` - adds an event task to the task list.

Adds an event task with the given task name and task duration in YYYY-MM-DD HHmm format. 
Displays a success message when task is added successfully.

Example of usage: 

`event Lunch /at 2022-09-13 1250 to 2022-09-13 1400`

Expected outcome:

Success message containing the task and the current number of tasks in your task list.

```
You have added "[E][ ] Lunch (at: SEP 13 2022 1250 to SEP 13 2022 1400)" into your Task List!
You have 1 task in your Task 

```

### `deadline` - adds a deadline task to the task list.

Adds an deadline task with the given task name and deadline date in YYYY-MM-DD HHmm format. 
Displays a success message when task is added successfully.

Example of usage: 

`deadline Homework /by 2022-09-13 1400`

Expected outcome:

Success message containing the task and the current number of tasks in your task list.

```
You have added "[D][ ] Homework (by: SEP 13 2022 1400)" into your Task List!
You have 1 task in your Task 

```

### `mark` - mark a task as complete

Mark a task as complete given the task index.

Example of usage: 

`mark 1`

Expected outcome:

Description of the outcome.

```
Good Job! The following task "{Task}" has been marked as done!"
```
