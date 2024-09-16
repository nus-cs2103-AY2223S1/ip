# User Guide
In the following User Guide, bracketed terms are terms that depend on the User's usage, while other terms are standard commands.
## Features

### Create tasks

In Duke, there are namely 3 different types of tasks.
- Todo: represents tasks that do not have a timestamp
- Deadline: represents tasks that have to be completed by a certain deadline
- Event: represents events that occur at a location and/or date and time

### Mark a task as done
```mark <index>``` 
Once you have completed a task, you can mark it as completed.

### Delete a task

You can delete a task that you no longer want to track from the list.

### List all tasks

You can see all your tasks at a glance.

### Search for a specific task by its name

You can search for tasks using their names as search filters.


## Usage

### `list` - Displays all tasks

Displays all tasks you are currently tracking.

Format: `list`

### `find` - Displays tasks with matching names

Display tasks that matches the search string. If multiple search strings are provided, then tasks that match at least
one search string are shown.

Format: `find <search-string>`

### `todo` - Creates a new Todo

Creates a new Todo and adds it to the current list.

Format: `todo <name>`

### `deadline` - Creates a new Deadline

Creates a new Deadline and adds it to the current list. The deadline must be in `dd-MM-YYYY` format.

Format: `deadline <name> /by <due-date>`

Example: `deadline return book /by 15-09-2022`

### `event` - Creates a new Event

Creates a new Event and adds it to the current list. 

Format: `event <name> /at <event-details>`

Example: `event book convention /at NUS, 15-09-2022`

### `done` - Marks a task as completed

Marks a task as completed.

Format: `done <task-index>`

### `delete` - Deletes a task

Removes a task from the list.

Format: `delete <task-index>`

### `bye` - Closes the application

Exits the application, saving the task list to the storage file while doing so.

Format: `bye`

### `save` - Saves the current list

Saves the task list to the storage file.

Format: `save`

<sub> UG adapted from https://github.com/ganhongyao/ip/ </sub>