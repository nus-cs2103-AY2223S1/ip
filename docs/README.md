# User Guide

## Features

### Manage your tasks

You can add, delete, mark your tasks stored in the app.

### Feature-XYZ

Description of the feature.

## Usage

### `add` - Add a new task to your task list.

- There are 3 tpyes of tasks: todo, deadline, and event.

  Examples of adding tasks:

  - `add todo todo_name`

  - `add deadline deadline_name /by YYYY/MM/DD`

  - `add event event_name /at YYYY/MM/DD`

- Expected outcome:

  - Add the respective tasks into the task list.

  _Dates must follow the YYYY/MM/DD format_

### `list` - List all of the tasks in your task list.

- Example of listing tasks:

  - `list`

- Expected outcome:

  - A list of tasks.

### `mark` `unmark` - mark or unmark one task in your task list.

Example of marking tasks as done:

`mark index_in_the_list`

Expected outcome:

mark the task at given index as done.

_Index must be within range_

Example of marking tasks as undone:

`unmark index_in_the_list`

Expected outcome:

mark the task at given index as undone.

_Index must be within range_

### `delete` - delete tasks in your task list.

Example of marking tasks as done:

`delete index_in_the_list`

Expected outcome:

Delete the task at the given index.

_Index must be within range_

### `sort` - sort tasks in your task list.

Sort tasks based on types and due dates.

Example of marking tasks as done:

`sort`
