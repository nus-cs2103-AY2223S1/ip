# User Guide 

<img src="demo/logo.png"> 

Lily is a desktop chatbot app for managing and storing tasks.

--------------------------------------------------------------------------------------------------------------------

## Getting Started
###Installation
1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `lily-v1.0.jar` from [here](https://github.com/lilythchu/ip/releases).

3. Copy the file to the folder you want to use as the home folder.

###Quick start
1. Double-click the file to start the app.
2. Type the command in the command box and press Enter or click send button to execute it.
3. You may want to entering `help` first to see the list of supported commands.
4. Refer to the [Features](#features) below for more details.

--------------------------------------------------------------------------------------------------------------------

## Features
### <img src="demo/logo"> Add a todo task: `todo`

Adds a todo task without date/time attached to the list.<br>
Format: `todo <decription>`

### <img src="demo/logo"> Add a deadline task: `deadline`

Adds a task need to be done before a specific date to the list.<br>
Format: `deadline <decription> /by yyyy-mm-dd`

### <img src="demo/logo"> Add an event task: `event`

Adds an event task that start at a specific time and ends at a specific time to the list.<br>
Format: `event <description> /at <duration>`

### <img src="demo/logo"> List all tasks: `list`

Shows the list of all the tasks stored.<br>
Format: `list`

### <img src="demo/logo">Mark task as done: `mark`

Marks a task at the given index number in the list as done.<br>
Format: `mark <index>`

### <img src="demo/logo">Unmark task: `unmark`

Mark status a task at the given index number in the list as not done.<br>
Format: `umark <index>`

### <img src="demo/logo"> Delete task: `delete`

Deletes a task from the list.<br>
Format: `delete <index>`

### <img src="demo/logo">Find tasks: `find`

Finds a task by searching for a keyword.<br>
Format: `find <keyword>`

### <img src="demo/logo">View help: `help`

Shows all available commands.<br>
Format: `help`

### <img src="demo/logo">Exit program: `bye`

Exits the program.<br>
Format: `bye`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Syntax and Examples
--------|------------------
**Todo** | `todo <description>` <br> e.g., `todo watch movies`
**Deadline** | `deadline <description> /by YYYY-MM-DD` <br> e.g., `deadline CS2103T quiz /by 2022-09-23`
**Event** | `event <description> /at <duration>` <br> e.g., `event team meeting /at 9-11pm`
**List** | `list`
**Mark** | `mark <index>`<br> e.g., `mark 3`
**Unmark** | `unmark <index>`<br> e.g., `unmark 3`
**Delete** | `delete <index>`<br> e.g., `delete 3`
**Find** | `find <keyword>`<br> e.g., `find watch`
**Help** | `help`
**Exit** | `bye`
