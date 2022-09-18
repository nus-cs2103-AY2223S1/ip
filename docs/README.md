# `Duke` User Guide

![Duke Ui](./Ui.png)

`Duke` is task manager that helps you keep track of **Todos**, **Deadlines** and **Events** so that you don't need to.

## Quick start

1. Ensure you have Java `11` or above installed.

2. Download the latest `duke.jar` from [here](https://github.com/xiaobill8/ip/releases).

3. Copy the file to the folder you want to use as the home folder for Duke.

4. Double-click the file to start the app.

5. Enter a command in the command box to execute it.

## Features 

### Type of Tasks supported
- `todo`: A simple todo task.
- `deadline`: A task that must be done by a "by" date.
- `event`: An event that happens at an "at" date.
- Recurring `event`: An event that when completed, generates a new event in specified interval.

### Adding a `Todo`
- Add a todo to your task list.

Command format: `todo <todo description>`

### Adding a `Deadline`
- Add a deadline to your task list.

Command format: `deadline <description> /by <yyyy-MM-dd>`

### Adding a `Event`
- Add an event to your task list.

Command format: `event <description> /at <yyyy-MM-dd>`

### Adding a recurring `Deadline`/`Event`
- Adds a recurring event to your task list.
- Append `/r <recurring interval>` to respectice Command format.
- Recurring interval options
  - Daily - `D`
  - Weekly - `W`
  - Monthly - `M`

Command format: `[event/deadline] <description> /at <yyyy-MM-dd> /r <[D|W|M]>`

## Operations

### `list` - shows your current Task List

- Display your current Task List

Command format: `list`

Example of usage:

````
Expected Output : 

    list

1. [T][ ][X] buy pen
2. [D][ ][ ] art submission (by: 2022-05-12)
````

#### Understanding the Checkboxes
In order from left to right:
1. Task type `T/D/E`
2. Recurring Interval `D/W/M/<empty>`
3. Completion Status `X/<empty>`

### `mark` - marks a Task as completed

- Marks a completed Task with a cross in the 3rd checkbox

Command format:  `mark <taskIndex>`

Example of usage:

````
Expected Output : 

Before 
1. [T][ ][ ] buy pen
    
    mark 1
    
After
1. [T][ ][X] buy pen
````

#### Marking a recurring Task

- Marking a recurring Task adds a cross in the 3rd checkbox
- It also generates a new unmarked recurring Task at `date + specified interval`

Command format: `mark <taskIndex>`

Example of usage:

````
Expected Output : 

Before 
1. [T][W][ ] Piano Lesson (at: 2022-05-20)
    
    mark 1
    
After
1. [T][W][X] Piano Lesson (at: 2022-05-20)
2. [T][W][ ] Piano Lesson (at: 2022-05-27)
````

### `unmark` - revert status of a completed Task

- unmarks a completed Task by removing the cross in the checkbox

Command format:  `unmark <taskIndex>`

Example of usage:

````
Expected Output : 

Before 
1. [T][ ][X] buy pen
    
    unmark 1
    
After
1. [T][ ][ ] buy pen
````

### `delete` - remove a task from the Task List

- completely remove a task from the List

Command format:  `delete <taskIndex>`

Example of usage:

````
Expected Output : 

Before 
1. [T][ ][X] buy pen
2. [D][ ][ ] art submission (by: 2022-05-12)
    
    delete 1
    
After
1. [D][ ][ ] art submission (by: 2022-05-12)
````

### `find` - search for task with corresponding key letters

- search for tasks that matches user's input

Command format:  `find <description>`

Example of usage:

````
Expected Output : 

Before 
1. [T][ ][X] buy pen
2. [D][ ][ ] art submission (by: 2022-05-12)
    
    find art
    
After
1. [D][ ][ ] art submission (by: 2022-05-12)
````