# `Duke` User Guide

`Duke` is task manager that helps you keep track of **Todos**, **Deadlines** and **Events** so that you don't need to.

## Features 

### Type of Tasks supported
- `todo`: A simple TODO tracker
- `event`: A event with a date
- `deadline`: A task with a deadline

### Adding a `Todo`
- Add a todo to your task list.

Command format: `todo <todo description>`

### Adding a `Event`
- Add an event to your task list.

Command format: `event <description> /at <event date>`

### Adding a `Deadline`
- Add a deadline to your task list.

Command format: `deadline <description> /by <yyyy-mm-dd>`

## Operations

### `list` - shows your current Task List

- Display your current Task List

Example of usage: 

Command format: `list`
````
Expected Output : 

    list

1. [T][X] buy pen
2. [D][] art submission (by: Nov 19 2012)
````

### `mark` - indicate a completed Task

- Marks a completed Task with a cross in the checkbox

Example of usage:

Command format:  `mark <taskIndex>`

````
Expected Output : 

Before 
1. [T][] buy pen
    
    mark 1
    
After
1. [T][X] buy pen
````
### `unmark` - revert status of a completed Task

- unmarks a completed Task by removing the cross in the checkbox

Example of usage:

Command format:  `unmark <taskIndex>`

````
Expected Output : 

Before 
1. [T][X] buy pen
    
    unmark 1
    
After
1. [T][] buy pen
````

### `delete` - remove a task from the Task List

- completely remove a task from the List

Example of usage:

Command format:  `delete <taskIndex>`

````
Expected Output : 

Before 
1. [T][X] buy pen
2. [D][] art submission (by: Nov 19 2012)
    
    delete 1
    
After
1. [D][] art submission (by: Nov 19 2012)
````

### `find` - search for task with corresponding key letters

- search for tasks that matches user's input

Example of usage:

Command format:  `find <description>`

````
Expected Output : 

Before 
1. [T][X] buy pen
2. [D][] art submission (by: Nov 19 2012)
    
    find art
    
After
1. [D][] art submission (by: Nov 19 2012)
````

### `snooze` - change the date/deadline of chosen task

- reschedule date or deadline of specific task

Example of usage:

Command format:  `snooze <TaskIndex>/<YYYY-MM-DD>`

````
Expected Output : 

Before 
1. [T][X] buy pen
2. [D][] art submission (by: Nov 19 2012)
    
    snooze 2/2022-12-12
    
After
1. [D][] art submission (by: Dec 12 2022)
````