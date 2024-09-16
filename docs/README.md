# User Guide
Duke is a desktop chatbot for keeping track of tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). With easy to learn commands, anyone can pick up Duke as a tool to keep track of their tasks.

## Features 

### Adding a ToDo - `todo`

### Adding an Event - `event`

### Adding a Deadline - `deadline`

### Marking a task - `mark`

### Unmarking a task - `unmark`

### Finding a task - `find`

### Deleting a task - `delete`

### Listing all tasks - `list`

### Setting the priority of a task - `priority`

### Exiting the chatbot - `bye`


## Usage

### `todo` - Creates a ToDo task

Adds a ToDo task to the Duke chatbot.

Format: `todo TASK_TO_BE_DONE`

Examples:
* `todo Buy groceries`
* `todo Paint living room white`

### `event` - Creates an Event task

Adds an Event task to the Duke chatbot.

Format `event EVENT /at DATE`
* Date has to be in yyyy-MM-dd format like `2022-09-16`

Examples:
* `event Career Fair /at 2022-06-28`
* `event Seminar /at 2020-12-31`

### `deadline` - Creates a Deadline task

Adds a Deadline task to the Duke chatbot.

Format `deadline DEADLINE /by DATE`
* Date has to be in yyyy-MM-dd format like `2022-09-16`

Examples:
* `deadline Project submission /by 2022-06-28`
* `deadline Sending out New Year's Cards /by 2020-12-31`

### `mark` - Marking a task

Marks a Task as complete.

Format `mark TASK_INDEX`

Example: `mark 1`
Expected outcome:
```
Nice! I've marked this task as done:
    [T][X][LOW] Buy groceries
```

### `unmark` - Unmarking a task

Unmarks a complete task to be incomplete.

Format `unmark TASK_INDEX`

Example: `unmark 1`
Expected outcome:
```
OK, I've marked this task as not done yet:
    [T][][LOW] Buy groceries
```

### `find` - Finding a task

Finds a task with description containing the specified keyword.

Format: `find KEYWORD`

### `delete` - Deleting a task

Deletes a task from the chatbot.

Format: `delete TASK_INDEX`

### `list`

Lists all the tasks within the chatbot.

Example: `list`

### `priority` - Setting the priority of a task

Sets the priority of a task to one of the three priorities:
1. low
2. medium
3. high

Format: `priority TASK_INDEX PRIORITY`

Examples:
* `priority 2 medium`
* `priority 1 high`

Expected outcome:
```
I have changed the priority of this task:
    [T][][LOW] Buy groceries
```

### `bye` - Exiting the chatbot

Exits the chatbot and saves the tasks in a storage file.

Example: `bye`