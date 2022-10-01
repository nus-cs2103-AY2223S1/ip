# User Guide
Duke is a personal assistant chatbot that helps you to manage your tasks. You can 
easily create new tasks and search for them with just a simple command. 

## Table of contents
1. [Quick Start](#quick-start)
2. [Overview](#overview-of-features)
3. [Features](#features-usage)
   1. [`todo`](#todo---creates-a-new-todo-task) - creating a new todo task
   2. [`deadline`](#deadline---creates-a-new-deadline-task) - creating a new deadline task
   3. [`event`](#event---creates-a-new-event-task) - creating a new event task
   4. [`list`](#list---list-all-tasks) - listing of tasks
   5. [`find`](#find---find-tasks-matching-the-query) - finding tasks that matches query
   6. [`mark`](#mark---mark-a-task-as-completed) - marking a task as complete
   7. [`unmark`](#unmark---mark-a-task-as-incomplete) - marking a task as incomplete
   8. [`remove`,`delete`](#remove--delete---delete-a-task-from-duke) - removing a task from the list
   9. [`update`](#update---update-a-task-description)  - updating task description
   10. [`quit`,`exit`,`bye`](#quit--exit--bye---quit-the-application) - exiting the application
   
## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/justins00/ip/releases).
3. Double-click on the file to start the application.
4. Type **`list`** in the command box and press _Enter_ to execute. You should see an empty list of tasks being returned

## Overview of Features 

### Creation of tasks

Create a task of type (todo, deadline, event)

### Listing of tasks

List the tasks stored in Duke

### Finding of tasks

Find the list of tasks that matches the query

### Marking and unmarking of tasks

Mark and unmark tasks

### Removing of tasks

Remove tasks from Duke 

### Update tasks

Update tasks previously stored in Duke


## Features Usage
<div markdown="block" class="alert alert-info">
**:information_source: Notes about the command format:**<br>
* Words in `< >` are parameters to be supplied by the user.<br>
  e.g. in `todo <description>`, `description` is a parameter which can be used as `todo cs2103 ip`.
* Extraneous parameters for commands that do not take in parameters (such as `exit` and `list`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.
</div>

### `todo` - Creates a new todo task

Example of usage:

`todo <description>`

Expected outcome:

Creates a new todo



### `deadline` - Creates a new deadline task

Example of usage:

`deadline <description> /by <date>`

Expected outcome:

Creates a new deadline



### `event` - Creates a new event task

Example of usage:

`event <description> /at <place>`

Expected outcome:

Creates a new event

### `list` - List all tasks

Example of usage: 

`list`

Expected outcome:

Duke will display a list of tasks previously created



### `find` - Find tasks matching the query

Example of usage: 

`find <query>`

Expected outcome:

Duke will display a list of tasks matching the query (case-sensitive)



### `mark` - Mark a task as completed

Example of usage: 

`mark <index>`

Expected outcome:

Mark a task as completed 



### `unmark` - Mark a task as incomplete

Example of usage: 

`mark <index>`

Expected outcome:

Unmark a completed task



### `remove` / `delete` - Delete a task from Duke

Example of usage: 
`remove <index>`
`delete <index>`

Expected outcome:

Delete a task from Duke



### `update` - Update a task description

Example of usage: 

`update <index> <new description>`

Expected outcome:

Update the description of a task


### `quit` / `exit` / `bye` - Quit the application

Example of usage: 

`quit`
`exit`
`bye`

Expected outcome:

Quit the application
