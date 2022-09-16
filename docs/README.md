# User Guide

## Introduction
Duke is an application that helps you keep track of your tasks and is designed for 
users who are familiar with Command Line Interface(CLI). All the tasks will be stored 
in the local environment.

## Pre-requisites

- JDK 11
- Download `Duke.jar`

## Features

- Add tasks
  - `Todo`
  - `Event`
  - `Deadline`
- Update tasks
- Delete tasks
- Search tasks by keywords
- Mark task as done
- Unmark task
- List all the tasks

### Add tasks
Adds either todo, deadline or event task to Duke. Time is added in `dd/MM/yyyy HH:mm` format.

**Format**: 
- `todo [task]`
- `deadline [task] /by [time]`
- `event [task] /at [time]`

**Example**: 
- `todo return book`
- `deadline return book /by 01/02/2022 12:00`
- `event return book /at 01/02/2022 12:00`

### Update tasks
Updates a task. Time is added in `dd/MM/yyyy HH:mm` format.

**Format**:
- `update [index] todo [task]`
- `update [index] deadline [task] /by [time]`
- `update [index] event [task] /at [time]`

**Example**:
- `update 1 todo return book`
- `update 1 deadline return book /by 01/02/2022 12:00`
- `update 1 event return book /at 01/02/2022 12:00`

### Delete tasks
Deletes a task by its index.

**Format**:
- `delete [index]`

**Example**:
- `delete 1`

### Search tasks by keywords
Search a task by keyword.

**Format**:
- `find [keyword]`

**Example**:
- `find book`

### Mark task as done
Mark a task as completed by its index.

**Format**:
- `mark [index]`

**Example**:
- `mark 1`

### Unmark task
Unmark a task as not completed by its index.

**Format**:
- `unmark [index]`

**Example**:
- `unmark 1`

### List all the tasks
List all the tasks in the list.

**Format**:
- `list`

**Example**:
- `list`
