# User Guide

## Quickstart
1. Ensure you have Java 11 or above installed.
2. Download the latest version of Knight from [here](https://github.com/jontmy/ip/releases).
3. Copy the `.jar` file to the folder you want to use.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. Enter commands in the command box and press Enter to execute them.

## Command Summary

| action                    | command                                    |
|---------------------------|--------------------------------------------|
| add a todo                | `todo <description>`                       |
| add a deadline            | `deadline <description> /by <date> <time>` |
| add an event              | `event <description> /at <date> <time>`    |
| list all tasks            | `list`                                     |
| find a task               | `find <search term>`                       |
| mark a task               | `mark <index>`                             |
| unmark a task             | `unmark <index>`                           |
| delete a task             | `delete <index>`                           |
| create a command shortcut | `alias add <alias> <command>`              |
| remove a command shortcut | `alias remove <alias> <command>`           |
| list all command shortcut | `alias list`                               |
| exit the program          | `bye`                                      |

## Features 

Notes about the command format:

- All commands are case sensitive and mandatory.
- Words surrounded by `<>` are parameters to be supplied by the user.
- Some commands have alternative formats which are separated by `|`.

### Adding a todo: `todo`

Adds a todo to the task list.

Format: `todo <description>`

- `<description>` may contain spaces for multi-word descriptions.
- `<description>` must not contain the pipe character `|` as it is used as a delimiter internally.

Examples:
- `todo read book`
- `todo watch movie`

### Adding a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline <description> /by <date> <time>`

- `<description>` may contain spaces for multi-word descriptions.
- `<description>` must not contain the pipe character `|` as it is used as a delimiter internally.
- `<date>` must be in the format `dd/mm/yyyy`.
- `<time>` must be in the format `hh:mm`.

Examples:
- `deadline return book /by 11/09/2022 18:00`
- `deadline submit assignment /by 12/09/2022 23:59`

### Adding an event: `event`

Adds an event to the task list.

Format: `event <description> /at <date> <time>`

- `<description>` may contain spaces for multi-word descriptions.
- `<description>` must not contain the pipe character `|` as it is used as a delimiter internally.
- `<date>` must be in the format `dd/mm/yyyy`.
- `<time>` must be in the format `hh:mm`.

Examples:
- `event project meeting /at 13/09/2022 18:00`
- `event friend's wedding /at 02/12/2022 10:00`

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Finding tasks by keyword: `find`

Finds tasks whose descriptions contain a given search term.

Format: `find <search term>`

- `<search term>` may contain spaces for multi-word search queries.
- The search is case-sensitive, i.e. `book` will not match `Book`.
- The position of the search term in the description does not matter, e.g. `book` will match `read book`.
- Full words will be matched, e.g. `book` will match `books`.

Example:
- `find book` returns `read book` and `return book`

### Marking a task as done: `mark`

Marks a task as done.

Format: `mark <index>`

- `<index>` refers to the index number shown in the displayed task list.
- `<index>` must be a positive integer starting from 1.
- An error message will be shown if `<index>` is invalid.

Example:

- `mark 1` marks the first task in the task list as done.

### Un-marking a marked task: `unmark`

Un-marks a task that is marked as done.

Format: `unmark <index>`

- `<index>` refers to the index number shown in the displayed task list.
- `<index>` must be a positive integer starting from 1.
- An error message will be shown if `<index>` is invalid.

Example:

- `unmark 1` un-marks the first task in the task list.

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete <index>`

- `<index>` refers to the index number shown in the displayed task list.
- `<index>` must be a positive integer starting from 1.
- An error message will be shown if `<index>` is invalid.

Example:

- `delete 1` deletes the first task in the task list.

### Creating a command shortcut: `alias add`

Creates an alias for a command, which can be used instead to execute the command.

Only existing commands can be aliased; an alias cannot be created for another alias.
This prevents circular loops of aliases.

Format: `alias add <alias> <command>`

- `<alias>` must be a single word with no spaces and not already used as a command.
- `<command>` must be an valid pre-existing single-word command and not an alias.

Examples:

- `alias add l list` creates an alias `l` for the `list` command.
- `alias add l ls` is not allowed as `ls` is an alias.
- `alias add aa alias add` is not allowed as the command must be a single word.

### Deleting a command shortcut: `alias remove`

Removes an alias.

Format: `alias remove <alias>`

- `<alias>` must be an existing alias.

Example:

- `alias remove ls` removes the default alias `ls` for `list`.

### Listing all aliases: `alias list`

Shows a list of all aliases.

### Exiting the program: `bye`

Exits the program automatically after 3 seconds.
