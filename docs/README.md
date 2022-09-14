# User Guide

Duke is a **desktop app for managing a task list via a Graphical User Interface (GUI).** There are 3 types of supported tasks: todos, events, and deadlines. 

## Features 

### Todo

A todo is a regular task without a date or deadline.

### Event

An event is a task that occurs on a specific date. An event can be recurring (e.g. A group meeting every 3 days). 

### Deadline

An deadline is a task that has to be done by a specific date. A deadline can be recurring (e.g. An online quiz due every 7 days).

### Marking/Unmarking Tasks

A task can be marked as done or undone. When a recurring event/deadline is marked as done, the next event/due date is automatically fetched.

## Usage

### Viewing all commands

All available commands are listed whenever an invalid command is entered.


### Adding tasks: `todo`

Adds a todo to the task list.

Format: `todo TASK_DESCRIPTION`

Example(s): 
- `todo wash dishes` Adds a todo with description `wash dishes` to the task list.
- `todo homework` Adds a todo with description `homework` to the task list.


### Adding tasks: `event`

Adds an event to the task list. 

Format: `event TASK_DESCRIPTION /at EVENT_DATE [/every NUMBER_OF_DAYS]`

> Note: Square brackets denote optional arguments.

Example(s): 
- `event party /at 14-10-2022` Adds an event with description `party` and date `14-10-2022`.
- `event meeting /at 13-11-2022 /every 7` Adds a recurring event with description `meeting` and date `13-11-2022` that recurs every `7` days.


### Adding tasks: `deadline`

Adds a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /by DUE_DATE [/every NUMBER_OF_DAYS]`

> Note: Square brackets denote optional arguments.

Example(s): 
- `deadline assignment /by 26-04-2022` Adds a deadline with description `assignment` and due-date `26-04-2022`.
- `deadline quiz /by 12-08-2022 /every 3` Adds a recurring event with description `quiz` and due-date `12-08-2022` that recurs every `3` days.


### Deleting a task: `delete`

Deletes the task at specified index.

Format: `delete TASK_INDEX`

Examples(s):
- `delete 1` Deletes the task at index `1`.


### Marking tasks: `mark`

Marks the task at the specified index as done.

Format: `mark TASK_INDEX`

Examples(s):
- `mark 1` Marks the task at index `1` as done.


### Unmarking tasks: `unmark`

Marks the task at the specified index as **not** done.

Format: `unmark TASK_INDEX`

Examples(s):
- `unmark 2` Marks the task at index `2` as not done.


### Listing all tasks: `list`

Lists all tasks in the task list.

Format: `list`


### Finding tasks with keyword: `find`

Finds all tasks containing the given key word or phrase. Key phrases containing multiple words have to be enclosed in quotation marks (").

Format: `find KEY_WORD` or `find "KEY_PHRASE"`

Example(s):
- `find homework` Finds all tasks containing the key word "homework".
- `find "wash dishes"` Finds all tasks containing the key phrase "wash dishes".


### Exiting the program: `bye`

Exits the program.

Format: `bye`


### Saving the data

All data is automatically saved by Duke whenever a command is performed.

