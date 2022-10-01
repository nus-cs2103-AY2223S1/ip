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

Creates a new todo task with a given description. Description cannot be empty

Format:

`todo <description>`

Examples:

* `todo ip project`
* `todo watch football`

### `deadline` - Creates a new deadline task

Creates a new deadline task with a given description and deadline. Description and deadline cannot be empty.
If deadline provided is of the following formats:

* yyyy-mm-dd HHmm
* dd-mm-yyyy HHmm
* yyyy-mm-dd
* dd-mm-yyyy

Duke will help you to convert it into a dd-MM-yyyy format

Format:

`deadline <description> /by <deadline>`

Examples:

* `deadline ip project /by 18-09-2022 2359`
* `deadline ip peer evaluation /by 2022-10-08 2359`

### `event` - Creates a new event task

Creates a new event task with a given description and place. Description and place cannot be empty.

Format:

`event <description> /at <place>`

Examples:

* `event CS2102 exam /at mpsh2A`


### `list` - List all tasks

Duke will display a list of tasks previously created

Format:

`list`

### `find` - Find tasks matching the query

Duke will display a list of tasks matching the query (case-sensitive)

Format:

`find <query>`

Examples:

* `find exam`
* `find`


### `mark` - Mark a task as completed

Mark the task at the given index in the **full list of tasks** as completed. Index provided must be valid.
If more than one valid index is provided. Duke will only mark the task at the first index provided.

Format:

`mark <index>`

Examples:

* `mark 1`


### `unmark` - Mark a task as incomplete

Mark the task at the given index in the **full list of tasks** as incomplete. Index provided must be valid.
If more than one valid index is provided. Duke will only unmark the task at the first index provided.

Format:

`unmark <index>`

Examples:

* `unmark 1`


### `remove` / `delete` - Delete a task from Duke

Delete a task from Duke at the given index in the **full list of tasks**. Index provided must be valid.
If more than one valid index is provided. Duke will only delet the task at the first index provided.

Format:

`remove <index>`
`delete <index>`

Examples:

* `remove 1`
* `delete 2`


### `update` - Update a task description

Update the description of a task the given index in the **full list of tasks**. Index provided must be valid.
New description of task cannot be empty.

Format:

`update <index> <new description>`

Examples:
* `update 1 watch united vs city`
* `update 3 cs2102 exam /at mpsh2B`



### `quit` / `exit` / `bye` - Quit the application

Quit the application

Format:
`quit`
`exit`
`bye`




