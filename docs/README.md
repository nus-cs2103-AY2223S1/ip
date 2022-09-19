# Duke: User Guide

## Features

### Commands:


| Command | Description |
| --- | --- |
| [todo](#create-a-todo-todo) | Adds a new Todo task |
| [deadline](#create-a-deadline-deadline) | Adds a new Deadline task |
| [event](#create-an-event-event) | Adds a new Event task |
| [list](#list-out-all-tasks-list) | Shows info on all stored tasks |
| [mark](#mark-a-task-as-done-mark) | Indicates a task is done |
| [unmark](#mark-a-finished-task-as-incomplete-unmark) | Indicates a task is incomplete |
| [delete](#delete-a-task-delete) | Remove a task |
| [find](#find-tasks-containing-a-keyword-find) | List out the tasks whose description contains a certain keyword |
| [sort](#sort-tasks-in-chronological-order-sort) | Sort tasks in ascending order of time value,  and number of time attributes |
| [SAVE](#ensure-data-on-tasks-is-saved-save ) | Make Duke save data on exit |
| [WIPE](#ensure-data-on-tasks-is-deleted-wipe) | Make Duke clear data on exit |
| [bye](#quit-bye) | Exit the app |


## Usage

### Format Annotations:

[] indicates a required argument, [[]] indicates an optional argument

### Create a Todo: `todo`

Adds a new Todo task

Format: `todo [DESCRIPTION]`

Example of usage: `todo TP team meeting`

Expected outcome:
```  
Got it. I've added this task:  
  [T][ ] TP team meeting
Now you have 1 task in the list.  
```  

### Create a Deadline: `deadline`

Adds a new Deadline task

Format: `deadline [DESCRIPTION] /by [D/M/YYYY] [[HH:MM(AM/PM)]]`

Example of usage: `deadline IP /by 19/9/2022 11:59PM`

Expected outcome:
```  
Got it. I've added this task:  
  [D][ ] IP (by 19 Sep 2022, 11:59PM)
Now you have 2 tasks in the list.  
```
### Create an Event: `event`

Adds a new Event task

Format: `event [DESCRIPTION] /at [D/M/YYYY] [[HH:MM(AM/PM)]]`

Example of usage: `event TP tutorials /at 21/9/2022`

Expected outcome:
```  
Got it. I've added this task:  
  [E][ ] TP tutorials (by 21 Sep 2022)
Now you have 3 tasks in the list.  
```
### List out all tasks: `list`

Shows info on all stored tasks

Format: `list`

Example of usage: `list`

Expected outcome:
```  
Ok, here are your tasks:
  1. [T][ ] TP team meeting
  2. [D][ ] IP (by 19 Sep 2022, 11:59PM)
  3. [E][ ] TP tutorials (by 21 Sep 2022)  
```
### Mark a task as done: `mark`

Indicates a task is done

Format: `mark [TASK NUMBER]`

Example of usage: `mark 1`

Expected outcome:
```  
Nice! I've marked this task as done:
  [T][X] TP team meeting
```
### Mark a finished task as incomplete: `unmark`

Indicates a task is incomplete

Format: `unmark [TASK NUMBER]`

Example of usage: `unmark 1`

Expected outcome:
```  
OK, I've marked this task as not done yet:
  [T][ ] TP team meeting
```
### Delete a task: `delete`

Remove a task

Format: `delete [TASK NUMBER]`

Example of usage: `delete 3`

Expected outcome:
```
Noted. I've removed this task:
  [E][ ] TP tutorials (at: 21 Sep 2022)
Now you have 2 tasks in the list.
```
### Find tasks containing a keyword: `find`

List out the tasks whose description contains a certain keyword

Format: `find [KEYWORD]`

Example of usage: `find TP`

Expected outcome:
```
Here are the tasks containing the keyword "TP" :
  1. [T][ ] TP team meeting
  3. [E][ ] TP tutorials (at: 21 Sep 2022)
```
### Sort tasks in chronological order: `sort`

Sort tasks in ascending order of time value,  and number of time attributes

Format: `sort`

Example of usage: `sort`

Expected outcome:
```
Ok, here are your tasks:
  1. [T][ ] TP team meeting
  2. [E][ ] TP tutorials (at: 21 Sep 2022)
  3. [D][ ] IP (by: 19 Sep 2022, 11:59PM)
```
### Ensure data on tasks is saved: `SAVE`

Make Duke save data on exit

Format: `SAVE`

Example of usage: `SAVE`

Expected outcome:
```
Data will be saved on exit
```
### Ensure data on tasks is deleted: `WIPE`

Make Duke clear data on exit

Format: `WIPE`

Example of usage: `WIPE`

Expected outcome:
```
Data will be wiped on exit
```
### Quit: `bye`

Exit the app

Format: `bye`

Example of usage: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
[App closes after a few seconds]
```