# User Guide

This repo contains the code for the Snoopy chatbot.
Snoopy is optimized for use via a Graphical User Interface (GUI).

Let Snoopy help you with your task management, starting today!

## Features 

### Add a todo task: `todo`

Adds a todo task to the list.

### Add a deadline task: `deadline`

Adds a deadline task to the list.

### Add an event task: `event`

Adds an event task to the list.

### List out all tasks: `list`

Obtains full list of tasks.

### Mark task as complete: `mark`

Mark selected task as complete.

### Unmark task as complete: `unmark`

Mark selected task as not completed.

### Find tasks: `find`

Obtains list of tasks that match specified keyword.

### Delete tasks: `delete`

Delete specified task from the list.

### Mark as high priority: `priority`

Marks selected task as high priority. High priority tasks will be bumped to the top of the list.

### Exit Snoopy: `bye`

Closes program.

### Save tasks

Tasks are automatically saved and loaded up the next time Snoopy is run.

## Usage

Keyword | Usage
---------|-----------
**Todo** | `todo DESCRIPTION` <br> e.g. `todo have lunch`
**Deadline** | `deadline DESCRIPTION /by yyyy-mm-dd HH:mm` <br> e.g. `deadline submit homework /by 2022-09-10 18:00`
**Event** | `event DESCRIPTION /at DATE` <br> e.g. `event graduation /at 6 March 9-11am`
**List** | `list`
**Mark** | `mark TASKNUMBER` <br> e.g. `mark 2`
**Unmark** | `unmark TASKNUMBER` <br> e.g. `unmark 2`
**Delete** | `delete TASKNUMBER` <br> e.g. `delete 2`
**Find** | `find KEYWORD` <br> e.g. `find eat`
**Priority** | `priority TASKNUMBER` <br> e.g. `priority 3`
**Bye** | `bye``
