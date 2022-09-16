# User Guide

Duke is a desktop app for **managing your tasks via a Command Line Interface**(CLI) while still ahving the benefits
of a Graphical User Interface(GUI).

## Quick start
1. Ensure you have Java 11 or above installed in your Computer.


2. Download the latest Duke.jar from [here](https://github.com/LJXSean/ip/releases/tag/A-Release).


4. Copy the file to the folder you want to use as the home folder for your AddressBook.


5. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 

![](Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
   Some example commands you can try:

- **/help** : Displays all commands
- **list** : Lists all tasks.
- **todo Go for a run!** : Adds a todo task "Go for a run!" to the task list
- **delete 1** : Deletes the 1st task shown in the task list.
- **bye** : Exits the app.

6. Refer to the [Features](#Features) below for details of each command.

## Features
### Notes about the command format:
* Words in [UPPER_CASE] are the parameters to be supplied by the user. <br>
eg. in 'todo [DESCRIPTION]', '[DESCRIPTION]' is a parameter which is used to specify the todo task.

## To-Do

A todo item contains a description of the task.

### `todo` - Adds a To-do item to duke
Format: **todo DESCRIPTION**

Example of usage: `todo Buy lunch`

Expected outcome:
> Got it. I've added this task:
><br>    [T][ ] Buy lunch
><br>Now you have 1 tasks in the list.

## Deadline

A Deadline item contains a description of the task as well as the due date and time.

### `deadline` - Adds a deadline item to duke
format: **deadline DESCRIPTION /by dd-MM-yy HH:mm**

Example of usage: `deadline CS2103T ip submission /by 16-09-22 23:59`

Expected outcome:
> Got it. I've added this task:
><br>    [D][ ] CS2103T ip submission  (by:Sep 16 2022, 1159PM)
><br>Now you have 1 tasks in the list.

## Event

An event item contains a description of the task, the due date, starting and ending time.

### `deadline` - Adds an event item to duke
format: **event DESCRIPTION /at dd-MM-yy HH:mm-HH:mm**

Example of usage: `event CS2103T Lecture /at 16-09-22 16:00-18:00`

Expected outcome:
> Got it. I've added this task:
><br>    [E][ ] CS2103T Lecture  (at:Sep 16 2022, 0400PM to 0600PM)
><br>Now you have 1 tasks in the list.

## List

A list of the tasks stored in Duke.

### `list` - Displays a list of the tasks
format: **list**

Expected outcome:
> Here are the tasks in your list: 
>1.  [T][ ] Buy lunch
>2.  [D][ ] CS2103T ip submission (by:Sep 16 2022, 1159PM)

## Find

Lists out all tasks whose descriptions match the phrase to be found.

### `find` - Displays a tasks with descriptions matching the find phrase
format: **find KEYPHRASE**

Example of usage: `find lunch`

Expected outcome:
> Here are the matching tasks in your list:
>1.  [T][ ] Buy lunch

## Mark

Marks a task that has been completed with an 'x'.

### `mark` - Mark a task
format: **mark INDEX**

Example of usage: `mark 1`

Expected outcome:
> Nice! I've marked this task as done:
><br>[T][X] Buy lunch

## Unmark

Removes the 'X' mark from a done task.

### `unmark` - Unmark a task
format: **unmark INDEX**

Example of usage: `unmark 1`

Expected outcome:
> OK, I've marked this task as not done yet:
><br>[T][ ] Buy lunch

## Delete

Deletes a task from Duke

### `delete` - Delete a task
format: **delete INDEX**

Example of usage: `delete 1`

Expected outcome:
> Noted. I've removed this task:
><br>[T][ ] Buy lunch
> Now you have 1 task in the list.


## Bye

Closes the Duke window

### `bye` - Close and exit DUke
format: **bye**
