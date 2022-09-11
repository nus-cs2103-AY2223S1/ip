# User Guide

Ado is a desktop chatbot app for managing and storing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

- [Quick Start](#quick-start)
- [Features](#features)
    * [Viewing help: `help`](#viewing-help-help)
    * [Adding a todo task : `todo`](#adding-a-todo-task-todo)
    * [Adding a deadline Task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding an event Task: `event`](#adding-an-event-task-event)
    * [Marking a task: `mark`](#marking-a-task-mark)
    * [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Finding a task: `find`](#finding-a-task-find)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
    * [Saving the data](#saving-the-data)
- [FAQ](#faq)
- [Command Summary](#command-summary)


## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Ado.jar` file from [here](https://github.com/jovitaanderson/ip/releases/tag/V0.1).
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Double-click the file to start the app. If done correctly, the GUI should be similar as below. <br>
[insert ado startup image]
5. Type the command in the command box and press Enter to execute it. You can try the follow example commands:
    - `list` : Lists all the tasks.
    - `deadline submit assignment /by 25 Sep 2022` : Adds a deadline task `submit assignment (by:25 Sep 2022)` to the list.
    - `event family outing /at 30 Sep 2022 18:30` : Adds an event task `family outing (at: 30 Sep 2022 18:30)`
    - `mark 2` : Marks the task at index `1` on the list as done.
    - `delete 1` : Deletes the first at index `2` on the list.
    - `find cat` : Finds a;; task with the matching keyword `cat`
    - `bye` : Exits the app.
6. Refer to the [Features](#Features) below for details of each command.

## Features 
Notes about the command format: <br>
Words in `{curly brackets}` are the parameters to be supplied by the user. <br>
e.g. in `todo {description}` , `{description}` is a parameter which can be used as `todo homework`

### Viewing help: `help`
Displays a list of avaible commands with examples. <br>
[insert ado help image 1,2] <br>
Format: `help` **or** click on the `?` icon beside the send button

### Adding a todo task: `todo`
Adds a todo task to the task list. <br>
Format: `todo {description}` <br>
Exmaple: `todo buy groceries`

### Adding a deadline task: `deadline`
Adds a deadline task to the task list. <br>
Format: `deadline {description} /by {dd MMM yyyy}` <br>
Exmaple: `deadline presentation /by 15 Sep 2022`

### Adding an event task: `event`
Adds a deadline task to the task list. <br>
Format: `event {description} /at {dd MMM yyyy HH:mm}` <br>
Exmaple: `event project meeting /at 18 Sep 2022 19:00`

### Marking a task: `mark`
Marks a task in the list as completed.<br>
Format: `mark {index}`

### Unmarking a task: `unmark`
Unmarks a task in the list as incompleted.<br>
Format: `unmark {index}`

### Deleting a task: `delete`
Deletes a task from the task list.<br>
Format: `delete {index}`

### Finding a task: `find`
Finds all tasks with matching keyword.<br>
Format: `find {keyword}`
-  All task description with the matching sequence of characters will be returned. e.g. me will match assignment and homework.

### Listing all tasks: `list`
Lists all the tasks in the task list.<br>
Format: `list`

### Exiting the program: `bye`
Exits the program.<br>
Format: `bye`

### Saving the data
Ado saves data automatically in the hard disk after any command that changes the data. There is no need to save manually.

## FAQ
Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the data.txt file it creates with the data.txt that contains the data of your previous Ado chatbot located at `[JAR file location]/data/tasks.txt`.

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Todo** | `todo {description}` <br> e.g., `todo do homework`
**Deadline** | `deadline {description} /by {dd MMM yyyy}` <br> e.g., `deadline submit assignment /by 25 Sep`
**Event** | `event {description} /at {dd MMM yyyy HH:mm}` <br> e.g., `event family outing /at 30 Sep 18:00`
**Mark** | `mark {index}`<br> e.g., `mark 1`
**Unmark** | `unmark {index}`<br> e.g., `unmark 1`
**Delete** | `delete {index}`<br> e.g., `delete 1`
**Find** | `find {keyword}`<br> e.g., `find cat`
**List** | `list`
**Exit** | `bye`
