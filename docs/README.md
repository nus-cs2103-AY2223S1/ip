# User Guide: Duke
Duke is a desktop productivity app for managing tasks.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/ish1506/ip/releases/).
3. Copy the file to the folder you want to use as the home folder for your Duke chatbot.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

## Features

### Viewing all tasks: `list` or `l`

Displays all tasks in the Task List.

Format: `list`

Shortcut: `l`

### Adding a Todo : `todo` or `td`

Adds a Todo task to the List.

Format: `todo [name of task]`

Shortcut `td [name of task]`

### Adding an Event : `event` or `ev`

Adds an Event task to the List. Event items must have a specified time.

Format: `event [name of event] \at [time]`

Shortcut: `ev [name of event] \at [time]`

Examples of usage:
- `event TP meeting \at Sun 11am`
- `ev Presentation \at W7 Mon`

### Adding a Deadline : `deadline` or `dl`

Adds a Deadline task to the List. Deadline items must have a specified deadline.

Format: `deadline [name of task] \by [date]`

Shortcut: `dl [name of task] \by [date]`

- `date` must have the `YYYY-MM-DD` format.

Examples of usage:
- `deadline assignment \by 2023-01-21`
- `dl assignment \by 2022-12-06`


### Marking a task : `mark` or `m`

Marks a task in the List as done.

Format: `mark [task index]`

Shortcut: `m [task index]`

- The `[task index]` **must** be a positive integer.

Examples of usage:
- `mark 2`
- `m 1`


### Unmarking a task : `unmark` or `um`

Marks a task in the List as undone.
 
Format: `unmark [task index]`

Shortcut: `um [task index]`

- The `[task index]` **must** be a positive integer.

Examples of usage:
- `unmark 2`
- `um 1`


### Deleting a task : `delete` or `d`

Deletes a task in the List.

Format: `delete [task index]`

Shortcut: `d [task index]`

- The `[task index]` **must** be a positive integer.
  
Examples of usage:
- `delete 2`
- `d 1`

### Finding tasks by keyword : `find` or `f`

Finds all tasks in the List which contain a given keyword.

Format: `find [keyword]`

Shortcut: `f [keyword]`

Examples of usage:
- `find CS2103T TP`
- `f meeting`

### Exiting the program : `bye` or `b`

Exits the program.

Format: `bye`

Shortcut: `b`

## Command Summary

| Action   | Format                               | Shortcut                        |
|----------|--------------------------------------|---------------------------------|
| List     | `list`                               | `l`                             |
| Todo     | `todo [name of task]`                | `td [name of task]`             |
| Event    | `event [name of event] \at [time]`   | `ev [name of event] \at [time]` |
| Deadline | `deadline [name of task] \by [date]` | `dl [name of task] \by [date]`  |
| Mark     | `mark [task index]`                  | `m [task index]`                |
| Unmark   | `unmark [task index]`                | `um [task index]`               |
| Delete   | `delete [task index]`                | `d [task index]`                |
| Find     | `find [keyword]`                     | `f [keyword]`                   |
| Exit     | `bye`                                | `b`                             |
