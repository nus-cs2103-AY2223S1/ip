# User Guide

## Quick Start
1. Ensure a minimum of Java 11 is installed on your computer.
2. Download the latest Duke release from [here](https://github.com/highorbit25/ip/releases/tag/A-Release).
3. Place the jar file at the directory where you wish to use as the home directory for duke.
4. Run Duke.jar to start the chatbot.
5. Start using Duke by typing the commands in the textbox.



## Features 
- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `todo n/TASK`, `TASK` is a parameter which can be used as `todo Revise CS2100 Midterms` 

### Adding to-do: `todo`

Adds a todo task.

Format: `todo TASK`

Example:
- `todo Revise CS2100 Midterms`

### Adding event: `event`

Adds an event task.

Format: `event TASK /at YYYY-MM-DD`

Example:
- `event return book /at 2019-12-01`

### Adding deadline: `deadline`

Adds a deadline task.

Format: `deadline TASK /by YYYY-MM-DD`

Example:
- `deadline submit iP /by 2022-09-16`

### Listing all tasks: `list`

Lists all tasks.

Format: `list`

### Marking task as done: `mark`

Marks a task as done.

Format: `mark INDEX`
- Marks the task at the specified `INDEX`. 
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example:
- `mark 1`

### Unmarking a task: `unmark`

Unmarking a task.

Format: `unmark INDEX`
- Umarks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example:
- `unmark 1`

### Deleting a task: `delete`

Deleting a task.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example:
- `delete 1`

### Locating tasks by description: `find`

Find tasks whose description contain any of the given keywords.

Format: `find KEYWORD`
- The search is case-sensitive. e.g `book` will not match `Book`
- Partial words will be matched. e.g `re` will match `return` and `read`

Example:
- `find revise`

### Archiving the tasks: `archive`

Archives existing tasks and start afresh. Save file can be located at `data/archive-dd-mm-yyyy-hh-mm-ss`

Format: `archive`

### Saying bye: `bye`

Bids farewell to Duke as affirmation for his efforts.
Does not terminate Duke, please use the "X" on the top right corner of the GUI to terminate.

Format: `bye`
