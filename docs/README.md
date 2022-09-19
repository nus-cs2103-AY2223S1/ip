# `Duke` User Guide

`Duke` is a task manager that helps you keep track of **Tasks** such as **Todos**, **Deadlines**
and **Events**.

## Quick start

1. Ensure you have Java `11` or above installed.

2. Download the latest `duke.jar` from [here](https://github.com/tienyu2000/ip/releases).

3. Copy the file to the folder you want to use as the home folder for Duke.

4. Double-click the file to start the app.

5. Enter a command in the command box to execute it.

## Features

### Type of Tasks supported
- `todo`: A simple todo task.
- `deadline`: A task that must be done by a "by" date.
- `event`: An event that happens at an "at" date.

### Adding a `ToDo`

Add a todo task to your task list.

Command format: `todo <todo description>`

### Adding an `Event`

Add an event task with date to your task list.

Command format: `event <event description> /at <yyyy-mm-dd>`

### Adding a `Deadline`

Add a deadline task with date to your task list.

Command format: `deadline <deadline description> /by <yyyy-mm-dd>`

## Operations

### `list` - lists out your current task list

- Display your current task list 

Command format: `list`

Example of usage: 

````
    list

Here are the tasks in your list:
1. [D][] submit iP (by: Sep 19 2022)
2. [E][] tP meeting (at:Sep 20 2022)
````

#### Understanding the Checkboxes
In order from left to right:
1. Task type `T/D/E`
2. Completion Status `X/<empty>`

### `mark` - marks a `Task` as completed

- Marks a task as completed with a cross 

Command format: `mark <taskNumber>`

Example of usage:

````
    list
    
1. [D][] submit iP (by: Sep 19 2022)
2. [E][] tP meeting (at:Sep 20 2022)

    mark 1
    
Nice! I've marked this task as done:
[X] submit iP

    list
    
1. [D][X] submit iP (by: Sep 19 2022)
2. [E][] tP meeting (at:Sep 20 2022)
````

### `unmark` - unmarks a `Task` as not completed 

- Unmarks a task as not completed with a blank space

Command format: `unmark <taskNumber>`

Example of usage:

````
    list 
    
1. [D][X] submit iP (by: Sep 19 2022)
2. [E][] tP meeting (at:Sep 20 2022)

    unmark 1
  
OK, I've marked this task as not done yet:
[] submit iP

    list 
    
1. [D][] submit iP (by: Sep 19 2022)
2. [E][] tP meeting (at:Sep 20 2022)
````

### `delete` - deletes a `Task` from the current task list

- Deletes a task from the current task list and updates other task numbers accordingly

Command format: `delete <taskNumber>`

Example usage:

````
    list
    
1. [D][X] submit iP (by: Sep 19 2022)
2. [E][] tP meeting (at:Sep 20 2022)
3. [T][] clean my room

    delete 1
    
Noted. I've removed this task:
[D][X] submit iP (by: Sep 19 2022)
Now you have 2 tasks left in the list.

    list
    
1. [E][] tP meeting (at:Sep 20 2022)
2. [T][] clean my room
 
````

### `find` - search for `Task` with matching keywords

- search for tasks that match with corresponding user inputs

Command format: `find <keyword>`

Example usage:

````
    list
     
1. [D][] submit iP (by: Sep 19 2022)
2. [E][] tP meeting (at:Sep 20 2022)

    find iP
    
Here are the matching tasks in your list:
1. [D][] submit iP (by: Sep 19 2022)

````
### `snooze` - snooze `Task` till a later date

- snooze a task till a later date provided by the user

Command format: `snooze <taskNumber>/<yyyy-mm-dd>`

Example usage:

````
    list 
    
1. [D][] submit iP (by: Sep 19 2022)
2. [E][] tP meeting (at:Sep 20 2022)

    snooze 2/2022-10-11
    
Got it! I've snoozed the task!

    list

1. [D][] submit iP (by: Sep 19 2022)
2. [E][] tP meeting (at:Oct 11 2022)

````
