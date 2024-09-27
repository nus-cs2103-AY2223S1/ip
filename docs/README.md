# User Guide
Jarvis is **a desktop app for managing tasks** (to-dos, 
deadlines, and events), **optimised for use via a Command 
Line Interface** (CLI) while still having the benefits of 
a Graphical User Interface (GUI). If you can type fast, 
Jarvis can get your task management done faster than 
traditional GUI apps.

## Features 

### Adding a task

Adds a task to the list. There are 3 types of task that can 
be added: to-dos, deadlines, and events. To add a to-do, 
you need to provide a description of the task. To add a 
deadline or an event, a time associated with the task is also 
required.

### Deleting a task

Deletes a task from the list. To delete a task, you need to 
provide the index of the task in the list.

### Marking a task

Marks a task in the list as done. To mark a task, you need 
to provide the index of the task in the list.

### Unmarking a task

Unmarks a task in the list as done. To unmark a task, you 
need to provide the index of the task in the list.

### Cloning a task

Clones a task in the list. To clone a task, you need to 
provide the index of the task in the list.

### Searching for tasks by keyword

Searches for task(s) in the list containing the given keyword. 

### Listing tasks

Shows a list of the current tasks in the list.

### Exiting the programme

Exits the programme.

## Usage

### `todo` - Adding a to-do

Format: `todo DESCRIPTION`

Example of usage: 

`todo check mailbox`

### `deadline` - Adding a deadline

Format: `deadline DESCRIPTION /by yyyy-mm-dd`

Example of usage:

`deadline ip /by 2022-09-16`

### `event` - Adding an event

Format: `event DESCRIPTION /at TIME`

Example of usage:

`event meeting /at Saturday 3pm`

### `delete` - Deleting a task

Format: delete INDEX

Example of usage:

`delete 2`

### `mark` - Marking a task as done

Format: mark INDEX

Example of usage:

`mark 1`

### `unmark` - Unmarking a task as done

Format: unmark INDEX

Example of usage:

`unmark 4`

### `clone` - Cloning a task

Format: clone INDEX

Example of usage:

`clone 3`

### `find` - Searching for tasks by keyword

Format: find KEYWORD

Example of usage:

`find meeting`

### `list` - Listing tasks in the list

Format: list

### `exit` - Exiting the programme

Format: exit
