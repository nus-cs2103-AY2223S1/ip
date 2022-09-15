# User Guide

Mr Meseeks is a Task Manager that can help you track your tasks. 
You can use it by **typing your command** into the **GUI** provided.

Tasks that you can add include: 
- todo 
- event
- deadline 

## Quick Start 
1. Ensure you have **Java 11** or above installed in your computer
2. Download the file [here](https://github.com/ReubenChay/ip/releases/tag/A-Release)
3. Move the file to the folder where you want to store this application 
4. Double-click the file to open it and begin managing your tasks! 


## Features 

### Add Tasks

This allows you to add any of the 3 tasks mentioned above to your task list 

### List Tasks 

This allows you to list all the tasks in your task list 

### Edit Task

This allows you to edit the date/time of a deadline or event task. 
This feature is not available for a todo task! 

### Search Task 

This allows you to search for a task using keywords found in the task name. 
This feature does not work for searching for a date and time! 

### Mark / Unmark Task

This allows you to mark a task as done, or unmark a previously marked task. 

### Delete Task 

This allows you to delete a task from your task list. 

### Hard Disk Storage 

This ensures that your task list is stored in your hard disk, so you will not lose your task list even if you close and reopen the app! 

### GUI 

A graphical user interface for ease of viewing and using the app. 


## Usage

### `Keyword` - todo 

Adds a todo type task to your task list.

`todo <task_name>`

Example of usage: 
`todo CS9999 assignment 1` 

This will add the task name "CS9999 assignment 1" to your task list. 

### `Keyword` - deadline 

Adds a deadline type task to your task list. 

`deadline <task_name> /by <yyyy-MM-dd HHmm>` 

Example of usage: 

`deadline CS9999 quiz 10 /by 2022-12-23 2359` 

This will add the task name "CS9999 quiz 10" with a date-time of 23rd December 2022 @ 2359 hrs to your task list.


### `Keyword` - event 

Adds a event type task to your task list. 

`event <task_name> /at <yyyy-MM-dd HHmm>` 

Example of usage: 

`event dinner /at 2022-12-23 1900` 

This will add the task name "dinner" with a date-time of 23rd December 2022 @ 1900 hrs to your task list.

### `Keyword` - list 

Lists all the tasks in your task list, ordered from the oldest to newest task added. 

Example of usage: 

`list` 

Your tasks will be fully displayed 

### `Keyword` - update 

Updates deadline or event tasks' date and time. 

`update <task_number> /<yyyy-MM-dd HHmm>`

The task number can be retrieved by typing `list` and observing the task number in the list. 

**Important:** This command **does not** work for todo type tasks as it does not have a date-time attached to it.

Example of usage: 

`update 2 /2022-12-23 0900` 

This will update task number 2 in the list, its new date-time will be 23rd December 2022 @ 0900 hrs.  

### `Keyword` - delete 

Deletes a task from your task list. 

`delete <task_number>` 

The task number can be retrieved by typing `list` and observing the task number in the list. 

Example of usage: 

`delete 2` 

This will delete task number 2 in the list. 

### `Keyword` - find 

Finds tasks by their name using a search keywords 

`find <keyword>`

Example of usage:

`find assignment` 

This will list all the task in your task list which include the word "assignment". 

### `Keyword` - mark 

Marks a task as done 

`mark <task_number>` 

The task number can be retrieved by typing `list` and observing the task number in the list. 

Example of usage: 

`mark 2` 

This will mark task number 2 as done. 

### `Keyword` - unmark 

Marks a task as undone 

`unmark <task_number>` 

The task number can be retrieved by typing `list` and observing the task number in the list. 

Example of usage: 

`unmark 2` 

This will unmark task number 2 as done. 




