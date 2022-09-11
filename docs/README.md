# User Guide - Duke Task Manager
![Screenshot](Ui.png)

## Features 

## Feature - List all Tasks
Retrieve all tasks from storage.
### Usage
### `list`

Expected outcome: All tasks will be displayed, regardless whether it is marked or not.

## Feature
Mark a task as done.
### Usage
### `mark <index of task shown in the list>`

- Example Usage
  - `mark 1`

Expected outcome: A task specified by the given index will be marked as done

## Feature
Mark a task as un done.
### Usage
### `unmark <index of task shown in the list>`

- Example Usage
    - `unmark 1`

Expected outcome: A task specified by the given index will be marked as undone.

## Feature
Delete a task
### Usage
### `delete <index of task shown in the list>`

- Example Usage
    - `delete 1`

Expected outcome: A task specified by the given index will be deleted.

## Feature - ToDo
Create a new todo task.
### Usage
### `todo <insert todo here>`

- Example Usage
  - `todo Go to Heaven`

Expected outcome: An unmarked Todo task will be created and stored in the list.

## Feature - Deadline
Create a new Deadline task.
### Usage
### `deadline <describe deadline> /by <LocalDateTime string>`

- Example Usage
  - `deadline Submit cheque to heaven /by 1234 12 Dec 2024`

Expected outcome: An unmarked deadline task will be created and stored in the list.

## Feature - Event
Create a new Event task.
### Usage
### `event <describe event> /at <LocalDateTime string>`

- Example Usage
  - `event Heaven Homecoming /at 2359 24 Dec 2022`

Expected outcome: An unmarked event task will be created and stored in the list.

## Feature - Priority
Mark the priority of a task to be H, M or L
### Usage
### `priority <index of task shown in the list> <priority of task>`

- Example Usage
  - `priority 1 H`
  
Expected outcome: Mark task at index 1 to be priority H

## Feature - Exit
Exit the program
### Usage
`bye`

## Supported LocalDateTime strings
```
"dd-MMM-YYYY"
```
