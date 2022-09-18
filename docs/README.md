# User Guide

This is Duke - a user-friendly chatbot which helps you to keep track of your tasks, deadlines and events.

![](Ui.png)

## Quick Start

1. Ensure that you have Java 11 or above installed in your Computer.
2. Download the latest `Duke.jar` from our Github.
3. Copy the file to the folder where you want to store Duke.
4. Double click to open the application.
5. Start entering commands!

## Features 

### Adding a todo task: `todo`

Adds a todo task to the list.

Format: `todo <description>`

Examples:
* `todo water the plants`
* `todo watch tv`
* `t fix bicycle`

### Adding a deadline: `deadline`

Adds a task with a deadline to the list.

Format: `deadline <description> /by YYYY-MM-DD` 

Examples:
* `deadline programming assignment /by 2022-02-10`

### Adding an event: `event`

Adds an event to the list.

Format: `event <description> /at YYYY-MM-DD`

Examples:
* `event friend's party /at 2022-03-11`

### List all tasks: `list`

Lists all of your tasks. You can see the task id for each task using this command.

Format: `list`

### Marking a task: `mark`

Mark a task as complete. The task id can be obtained using `list`.

Format: `mark <taskid>`

Examples:
* `mark 2`

### Unmarking a task: `unmark`

Unmark a completed task. The task id can be obtained using `list`.

Format: `unmark <taskid>`

Examples:
* `unmark 2`

### Deleting a task: `delete`

Delete. The task id can be obtained using `list`.

Format: `delete <taskid>`

Examples:
* `delete 2`

### Finding a task: `find`

Finding a task containing `<string>` in their description.

Format: `find <string>`

Examples:
* `find CS2103T homework`

### Aliasing a command: `alias`

Alias a command so that a different command name can be used.

Format: `alias <alias> <command to be aliased>`

Examples:
* `alias t todo`
* `alias rm delete`

### Unaliasing a command: `unalias`

Alias a command so that a different command name can be used.

Format: `unalias <alias>`

Examples:
* `unalias t`


## Saving Data

All changes made will be saved automatically to storage. No manual save is required.

## Editing the Data File

All data are saved as a txt file in teh same folder as `Duke.jar`. Advanced users are welcome to update data directly 
by editing that file, but invalid rows will be ignored and deleted upon updating of tasks.