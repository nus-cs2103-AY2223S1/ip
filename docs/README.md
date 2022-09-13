# User Guide

Molediver is an app that can help to **manage tasks**.

## Features 

### Adding a todo: `todo` 

Adds a todo to the task list.

Format: `todo DESCRIPTION`

Example: 
 - `todo read book`

### Adding a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`
        `deadline DESCRIPTION /by YYYY-MM-DD HHMM`
- Both deadline formats are accepted.
- Time has to be in 24h format.

Examples:
 - `deadline return book /by 2022-09-13`
 - `deadline return book /by 2022-09-13 1800`

### Adding an event: `event`
Adds an event to the task list.

Format: `event DESCRIPTION /at TIMING`

Example:
- `event project meeting /at Mon 2-4pm`

### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

### Deleting a task: `task`
Delete the specified task from the task list, provided it exists.

Format: `delete INDEX`
- The index **must be a positive integer** 1, 2, 3,...
- The task must exist at that specified index.

Example:
- `delete 1`

### Marking a task as done: `mark`
Mark the specified task from the task list as done, provided it exists.

Format: `mark INDEX`
- The index **must be a positive integer** 1, 2, 3,...
- The task must exist at that specified index.

Example:
- `mark 2`

### Marking a task as not done: `unmark`
Mark the specified task from the task list as not done, provided it exists.

Format: `unmark INDEX`
- The index **must be a positive integer** 1, 2, 3,...
- The task must exist at that specified index.

Example:
- `unmark 5`

### Locating tasks by name: `find`
Find a list of tasks that contain the given keywords in its description.

Format: 'find KEYWORD'
- The search is **case-sensitive**. e.g. `Book` will not match `book`.
- The **order** of the search **does** matter. e.g. `book return` will not match `return book`.
- Only task description is searched.
- Partial words will be matched. e.g. `book` will match `books`.
- Task descriptions matching at least one keyword will be returned. e.g. `return book` will return `read book` and `return magazine`.

Examples:
- `find book` returns `return books` and `read book`.

### Set priority for tasks: `priority`
Set the priority level of the specific task from the task list, provided it exists.

Format: `priority INDEX PRIORITY_LEVEL`
- The index **must be a positive integer** 1, 2, 3,...
- The task must exist at that specified index.
- There are three valid priority levels: `low, medium and high`.
- The priority level has to follow the exact syntax as shown above.

Examples:
- `priority 1 high`
- `priority 4 medium`
- `priority 13 low`

### Exiting the program: `bye`
Exits the program and save all the tasks.

Format: `bye`


### Saving the data
```
WARNING: Molediver data are not saved into hard drive automatically and required the bye command to save the data.
If you exit the program without the bye command, all tasks that the user input will not be saved.
```

### Editing the data
Molediver data are saved as a txt file `[JAR file location]/duke.txt`.
Advanced users are welcomed to update data directly by editing that data file.