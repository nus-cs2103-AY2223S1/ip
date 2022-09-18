# User Guide

## Features

### Create tasks

In Drake, you can create 4 different types of tasks.

- ✨ _Todo_ ✨ tasks that do not have any associated dates
- ✨ _Deadline_ ✨ tasks that have an associated deadline
- ✨ _Event_ ✨ events that occur at a specific date
- ✨ _Within_ ✨ events that occur between two specific dates

### Mark a task as done

Once you have completed a task, mark it as completed. 🤯

### Delete a task

Delete a task that you no longer want to track from the list. ❌

### Show all tasks

See all your tasks (and their associated task numbers) in a numbered list. 👀

### Search for a specific task by its name or date

Search for tasks using their names or dates (if they have dates) as search filters. 🧐

### Tasks don't disappear

Drake remembers your tasks! Tasks are automatically saved and retrieved when you open the app again. 🧠 

## Usage

### `list` - Displays all tasks

Displays all tasks you are currently tracking.

Format: `list`

### `find` - Displays tasks with matching names

Displays tasks that match the search string. 

Format: `find <search-string> <search-string>...`

### `todo` - Creates a new Todo type task

Creates a new Todo and adds it to the current list.

Format: `todo <name>`

### `deadline` - Creates a new Deadline type task

Creates a new Deadline and adds it to the current list. The deadline must be in `YYYY-MM-dd` format.

Format: `deadline <name> /by <due-date>`

Example: `deadline return book /by 2021-09-31`

### `event` - Creates a new Event type task

Creates a new Event and adds it to the current list. The deadline must be in `YYYY-MM-dd` format.

Format: `event <name> /at <event-date>`

Example: `event NUS Literary Society Book Club and Exchange /at 2022-09-18`

### `within` - Creates a new Within type task

Creates a new Within task and adds it to the current list. The date range must be in `YYYY-MM-dd` format.

Format: `within <name> /range <start-date> <end-date>`

Example: `within Recess Week /range 2022-09-18 2022-09-25`

### `mark` - Marks a task as completed

Marks a task as completed.

Format: `mark <task-number>`

### `unmark` - Marks a task as not completed

Marks a task as not completed.

Format: `unmark <task-number>`

### `delete` - Deletes a task

Removes a task from the list.

Format: `delete <task-number>`

### `bye` - Closes the application

Exits the app.

Format: `bye`