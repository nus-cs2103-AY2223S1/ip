# User Guide

## Features

### Creation of tasks

Create a task of type (todo, deadline, event)

### Listing of tasks

List the tasks stored in Duke

### Marking and unmarking of tasks

Mark and unmark tasks

### Removing of tasks

Remove tasks from Duke

### Update tasks

Update tasks previously stored in Duke

## Usage

### `list` - List all tasks

Example of usage:

list

Expected outcome:

Duke will display a list of tasks previously created



### `find` - Find tasks matching the query

Example of usage:

find <task name>
find <task index>
find <task type>

Expected outcome:
Duke will display a list of tasks matching the query (case-sensitive)



### `mark` - Mark a task as completed

Example of usage:

mark <index>

Expected outcome:

Mark a task as completed



### `unmark` - Mark a task as incomplete

Example of usage:

mark <index>

Expected outcome:

Unmark a completed task



### `delete` - Delete a task from Duke

Example of usage:
delete <index>

Expected outcome:

Delete a task from Duke




### `todo` - Creates a new todo task

Example of usage:

todo <description>

Expected outcome:

Creates a new todo



### `deadline` - Creates a new deadline task

Example of usage:

deadline <description> /by <date>

Expected outcome:

Creates a new deadline



### `event` - Describe action

Example of usage:

event <description> /at <place>

Expected outcome:

Creates a new event




### `bye` - Quit the application

Example of usage:

bye

Expected outcome:

Quit the application