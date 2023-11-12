<img align="right" src="demo/logo.png"> 

# User Guide

Lily is a desktop chatbot app for managing and storing tasks.

--------------------------------------------------------------------------------------------------------------------

## Getting Started
### Installation
1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest version from [here](https://github.com/lilythchu/ip/releases).

3. Copy the file to the folder you want to use as the home folder.

### Quick start
1. Double-click the file to start the app.
2. Type the command in the command box and press Enter or click send button to execute it.
3. You may want to entering `help` to see the list of supported commands.
4. Refer to the [Features](#features) below for more details.

--------------------------------------------------------------------------------------------------------------------

## Features

<img align="left" width="30" height="30" style="margin-right: 10px; margin-top: 10px" src="demo/logo.png"> 

### Add a todo task: `todo`

Adds a todo task without date/time attached to the list.<br>
Format: `todo <decription>`

<img align="left" width="30" height="30" style="margin-right: 10px; margin-top: 10px" src="demo/logo.png"> 

### Add a deadline task: `deadline`

Adds a task need to be done before a specific date to the list.<br>
Format: `deadline <decription> /by yyyy-mm-dd`

<img align="left" width="30" height="30" style="margin-right: 10px; margin-top: 10px" src="demo/logo.png"> 

### Add an event task: `event`

Adds an event task that start at a specific date and time list.<br>
Format: `event <description> /at <time>`

<img align="left" width="30" height="30" style="margin-right: 10px; margin-top: 10px" src="demo/logo.png"> 

### List all tasks: `list`

Shows the list of all the tasks stored.<br>
Format: `list`

<img align="left" width="30" height="30" style="margin-right: 10px; margin-top: 10px" src="demo/logo.png"> 

### Mark a task as done: `mark`

Marks a task at the given index number in the list as done.<br>
Format: `mark <index>`

<img align="left" width="30" height="30" style="margin-right: 10px; margin-top: 10px" src="demo/logo.png"> 

### Unmark a task: `unmark`

Mark status a task at the given index number in the list as not done.<br>
Format: `umark <index>`

<img align="left" width="30" height="30" style="margin-right: 10px; margin-top: 10px" src="demo/logo.png"> 

### Delete a task: `delete`

Deletes a task from the list.<br>
Format: `delete <index>`

<img align="left" width="30" height="30" style="margin-right: 10px; margin-top: 10px" src="demo/logo.png"> 

### Find tasks: `find`

Finds a task by searching for a keyword.<br>
Format: `find <keyword>`

<img align="left" width="30" height="30" style="margin-right: 10px; margin-top: 10px" src="demo/logo.png"> 

### View help: `help`

Shows all available commands.<br>
Format: `help`

<img align="left" width="30" height="30" style="margin-right: 10px; margin-top: 10px" src="demo/logo.png"> 

### Exit program: `bye`

Exits the program.<br>
Format: `bye`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Syntax and Examples
--------|------------------
**Todo** | `todo <description>` <br> e.g., `todo watch movies`
**Deadline** | `deadline <description> /by YYYY-MM-DD` <br> e.g., `deadline CS2103T quiz /by 2022-09-23`
**Event** | `event <description> /at <duration>` <br> e.g., `event team meeting /at 2022-10-10 19:00`
**List** | `list`
**Mark** | `mark <index>`<br> e.g., `mark 3`
**Unmark** | `unmark <index>`<br> e.g., `unmark 3`
**Delete** | `delete <index>`<br> e.g., `delete 3`
**Find** | `find <keyword>`<br> e.g., `find watch`
**Help** | `help`
**Exit** | `bye`

--------------------------------------------------------------------------------------------------------------------

## Demo

<img src="Ui.png">
<img src="demo/deadline.png" style="margin-top: 20px">
<img src="demo/find.png" style="margin-top: 20px">
