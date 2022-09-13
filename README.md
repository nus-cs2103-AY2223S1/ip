# Gigachad User Guide

Gigachad will help you keep track of all your tasks.

## Features

### Autosave
Tasks are automatically written into your home directory in a dukeData folder in a Duke.txt.

Disclaimer: Ensure this file doesn't exist (from previous iP checks) so that it works properly.

***

### Viewing Help : `help`
Views a list of available commands.

Format: `help`

***

### Adding a ToDo : `todo`
Adds a todo task into the tasklist.

Format:  `todo priority description`

Note: priority is optional, default priority is set to low.

Examples:
- `todo high read book`
- `todo read book`

***

### Adding a Deadline : `deadline`
Adds a deadline task into the tasklist.

Format:  `deadline priority description /by date time`

Note: 
- priority is optional, default priority is set to low.
- time is optional.

Examples:
- `deadline medium lab /by 2019-12-10`
- `deadline lab /by 2019-12-10 16:30`

***

### Adding an Event : `event`
Adds an todo task into the tasklist.

Format:  `event priority description /on date time-time`

Note: priority is optional, default priority is set to low.

Examples:
- `event low club meeting /on 2019-12-10 16:30-20:30`
- `event club meeting /on 2019-12-10 16:30-20:30`

***

### Listing all tasks : `list`
Shows a list of all tasks, sorted based on priority.

Format:  `list`

***

### Marking task as done : `mark`
Marks a task as done at the given index, assuming a task exists there.

Format:  `mark index`

Example: `mark 3`

***

### Marking task as undone : `unmark`
Marks a task as undone at the given index, assuming a task exists there.

Format:  `unmark index`

Example: `unmark 3`

***

### Deleting a task : `delete`
Delete the task at the given index, assuming a task exists there.

Format:  `delete index`

Example: `delete 3`

***

### Setting task priority : `priority`
Sets a task's priority at the given index with the given priority.

Assumptions: 
- Task exists at index.
- Given priority is valid.

Format:  `priority index value`

Example: `priority 3 medium`

***

### Retrieving due tasks : `due`
Shows a list of tasks due at the given date.

Format:  `due date`

Example: `due 2022-10-10`

***

### Finding tasks by description : `find`
Shows a list of tasks found with the given queries.

Note: Can have single query or multiple queries at once.

Format:  `find query`

Examples: 
- `find book lab`
- `find lab`

***

### Exiting the program : `bye`
Exits the program.

Format:  `bye`

***
