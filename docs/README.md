# User Guide

DukePro is a **ChatBot application for managing tasks with Command Line style syntax** with a Graphical User Interface (GUI). You get to talk to the ChatBot **_Christina_**!

## Quick start

1. Ensure that you have Java `11` or above installed in your Computer.

2. Download `DukePro.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Double-click the file to start the app and you should be greeted by **_Christina_**!

4. Type in the text box and press `ENTER` or click on the `SEND` button and watch **_Christina_** respond!
    
    Some example commands that **_Christina_** can do:
    
    * **`todo`** : Adds a simple todo task.
    * **`list`** : List the current tasks in the list.
    * **`mark`** `1` : Marks the 1st task in the list as completed.
    * **`delete`** `3` : Deletes the 3rd task in the list.
    * **`bye`** : **_Christina_** says goodbye and exits.

> **_Christina_** will save its contents in the file _dukeSave.txt_ in its current folder and will read from the same file the next time it starts up.

## Features 

### Adds a todo task : `todo`

Adds a todo task to the task list.

Format: `todo`

### Adds a deadline task : `deadline`

Adds a deadline task with added deadline information to the task list.

Format: `deadline`

### Adds a event task : `event`

Adds a event task with added event time information to the task list.

Format: `event`

### Listing all tasks : `list`

Displays the list of task present in the current task list.

Format: `list`

### Marks a task : `mark`

Marks a given task in the task list as completed.

Format: `mark`

### Unmarks a task : `unmark`

Marks a given task in the task list as not completed.

Format: `unmark`

### Deletes a task : `delete`

Deletes the given task in the task list.

Format: `delete`

### Locate tasks that matches a keyword : `find`

Displays the list of task that matches the given keyword in the current task list.

Format: `find`

### Updates a task : `update`

Updates an exisiting task in the task list with updated information.

Format: `update`

### Exiting the program : `bye`

Exits the program with a goodbye message!

Format: `bye`



### Feature-XYZ

Description of the feature.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Command | Format, Examples
--------|------------------
**Todo** | `todo TASK_NAME​` <br> e.g., `todo Buy Book`
**Deadline** | `deadline TASK_NAME /by DATE_TIME` <br> e.g., `deadline Return Book /by 12/12/2022,0900`
**Event** | `event TASK_NAME /at DATE_TIME` <br> e.g., `event Book Sale /at 16/06/2022,1800`
**List** | `list`
**Mark** | `mark INDEX`<br> e.g., `mark 1`
**Unmark** | `unmark INDEX`<br> e.g., `unmark 2`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Book`
**Update** | `update INDEX []​`<br> e.g.,`update 2 []`
**Bye** | `bye`