# Duke

**Duke is a Personal Assistant Chatbot that helps a person to keep track of various things**, such as their tasks. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

- [Duke](#duke)
  - [Quick start](#quick-start)
  - [Features](#features)
    - [Adding a to-do: `todo`](#adding-a-to-do-todo)
    - [Adding an event: `event`](#adding-an-event-event)
    - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Finding matching tasks: `find`](#finding-matching-tasks-find)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Marking a task: `mark`](#marking-a-task-mark)
    - [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    - [Undoing modification: `undo`](#undoing-modification-undo)
    - [Exiting Duke: `bye`](#exiting-duke-bye)
  - [Command Summary](#command-summary)

![](docs/Ui.png)

## Quick start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `duke.jar` from [here](https://github.com/rama-pang/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for Duke.
4. Double-click the file to start the app.
5. Type the command in the command box and press `Enter` to execute it. e.g. typing `list` and pressing `Enter` will list all your ongoing tasks.
6. Refer to the [Features](#features) below for details of each command.

## Features

Notes about the command format:
- Words in `UPPER_CASE` are the parameters to be supplied by the user.
- Each parameter must be used exactly once in the command.
- Multiple white spaces are intepreted as a single white space.
  e.g. if the command specifies:
  ```
  todo my example    123
  ````
  it will be interpreted as:
  ```
  todo my example 123
  ```
- Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.
   e.g. if the command specifies `list 123`, it will be interpreted as `list`.
- After each command which modifies the task list, it will save the task list to `data/tasks.txt`.

### Adding a to-do: `todo`

Adds a to-do task to the task list.

Format: `todo DESCRIPTION`
- `DESCRIPTION` cannot be blank, may consist of white spaces, and must not contain `/`.

Examples:
- `todo my example` will add a `todo` with description `my example`.
- `todo 123 4` will add a `todo` with description `123 4`.

### Adding an event: `event`

Adds an event to the task list.

Format: `event DESCRIPTION /at DATE`
- `DESCRIPTION` cannot be blank, may consist of white spaces, and must not contain `/`.
- `DATE` must be specified in the `yyyy-mm-dd` format.

Examples:
- `event my example /at 2020-12-01` will add a `event` with description `my example` and date `Dec 01, 2020`.
- `event 123 /at 2022-02-02` will add a `event` with description `123` and date `Feb 02, 2022`.


### Adding a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DATE`
- `DESCRIPTION` cannot be blank, may consist of white spaces, and must not contain `/`.
- `DATE` must be specified in the `yyyy-mm-dd` format.

Examples:
- `deadline my example /by 2020-12-01` will add a `deadline` with description `my example` and date `Dec 01, 2020`.
- `deadline 123 /by 2022-02-02` will add a `deadline` with description `123` and date `Feb 02, 2022`.


### Listing all tasks: `list`

Lists all tasks.

Format: `list`
- Each task will be specified in the following format: `[TYPE][DONE] DESCRIPTION DATE`.
- `[TYPE]` denotes the type of the task:
   - If the task is a to-do task, `[TYPE]` is equal to `[T]`.
   - If the task is an event, `[TYPE]` is equal to `[E]`.
   - If the task is a deadline, `[TYPE]` is equal to `[D]`.   
- `[DONE]` denotes whether the task is marked as done or not:
   - If the task is marked as done, `[DONE]` is equal to `[X]`.
   - If the task is marked as not done, `[DONE]` is equal to `[ ]`.
- `DESCRIPTION` contains the description of the task.
- `DATE` contains the date information of the task.
   - If the task is a `todo`, there will be no `DATE` specified.
   - If the task is an `event`, `DATE` will be in the following format: `(at: Mon dd, yyyy)`.
   - If the task is a `deadline`, `DATE` will be in the following format: `(by: Mon dd, yyyy)`.   
- Examples:
   - The command `todo 123 4` will add `[T][ ] 123 4` to the task list.
   - The command `event graduation /at 2024-01-01` followed by marking the event as done will add `[E][X] graduation (at: Jan 01, 2024)` to the task list.   
   

### Finding matching tasks: `find`

Lists all matching tasks to the specified pattern.

Format: `find PATTERN`
- If `PATTERN` is blank, then Duke will list all tasks.
- Otherwise, Duke will list all tasks whose description contains `PATTERN` as a substring.

Examples:
- `find read` will list all tasks with `read` as a substring in its description, such as `read book` and `tread carefully`.
- `find to do` will list all tasks with `to do` as a substring in its description, such as `my to do list`.

### Deleting a task: `delete`

Deletes an existing task in the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the task list when using `list`. The index **must be a positive integer** 1, 2, 3, …​, and does not exceed the number of tasks in the task list.

Example:
- `delete 1` will delete the 1st task in the task list.

### Marking a task: `mark`

Marks an existing task in the task list as done.

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as done. The index refers to the index number shown in the task list when using `list`. The index **must be a positive integer** 1, 2, 3, …​, and does not exceed the number of tasks in the task list.

Example:
- `mark 1` will mark the 1st task in the task list as done.

### Unmarking a task: `unmark`

Marks an existing task in the task list as not done.

Format: `unmark INDEX`
- Marks the task at the specified `INDEX` as not done. The index refers to the index number shown in the task list when using `list`. The index **must be a positive integer** 1, 2, 3, …​, and does not exceed the number of tasks in the task list.

Example:
- `unmark 1` will mark the 1st task in the task list as not done.

### Undoing modification: `undo`

Undoes the last command which modifies the task list.

Format: `undo`
- Undo-able commands are `todo`, `event`, `deadline`, `delete`, `mark`, and `unmark`. Note that it cannot undo an `undo` command.

### Exiting Duke: `bye`

Exits the application. 

Format: `bye`

## Command Summary

| Action         | Format                       |
| -------------- | ---------------------------- |
| Add `todo`     | `todo DESCRIPTION`           |
| Add `event`    | `event DESCRIPTION /at DATE` |
| Add `deadline` | `todo DESCRIPTION /by DATE`  |
| List           | `list`                       |
| Find           | `find PATTERN`               |
| Delete         | `delete INDEX`               |
| Mark           | `mark INDEX`                 |
| Unmark         | `unmark INDEX`               |
| Undo           | `undo`                       |
| Exit           | `bye`                        |
