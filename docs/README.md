
#User Guide

--------------------------------------------------------------------------------------------------------------------

Bishop is **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Bishop will get your tasks sorted faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `bishop.jar` from [here](https://github.com/CeereeC/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your app.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   <img src="https://github.com/CeereeC/ip/blob/master/docs/Ui.png?raw=true" alt="BishopStartup"/> <br>

1. Type the command in the command box and press Enter to execute it. <br>
   Some example commands you can try:    
    * **`list`** : Lists all tasks.
    
    * **``todo prepare papers``** : Adds a todo task to the task list.

    * **``event attend talk /at Sunday``** : Adds an event task to the task list.
    
    * **``deadline complete homework /by 09-09-2000``** : Adds a deadline task to the task list.
    
    * **``mark 1``** : Marks the task at index `0` on the list as done.

    * **``unmark 1``** : Marks the task at index `0` on the list as not done.

    * **`delete`**`3` : Deletes the 3rd task shown in the current list.
    
    * **`bye`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Adding a todo task: `todo` 

Adds a todo task to the task list.

Format: `todo {description}`

Examples:
* `todo Finish Homework`

### Adding a deadline task: `deadline`

Adds a deadline to the task list.

Format: `deadline {description} /by {yyyy-mm-dd}`

Examples:
* `deadline Finish Homework /by 2022-12-21`

### Adding a event task: `event`

Adds an event to the task list.

Format: `event {description} /at {description of time and place}`

Examples:
* `event Attend Talk /at Jurong Point, Tommorow`


### Listing all tasks : `list`

Shows a list of all tasks in the task list.

Format: `list`

### Mark a task : `mark`

Marks the specified task from the task list as done.

Format: `mark INDEX`

* Marks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

### Unmark a task : `ummark`

Marks the specified task from the task list as not done.

Format: `unmark INDEX`

* Unmarks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

### Deleting a task : `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete 2` deletes the 2nd task in the task list.

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

TaskList data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TaskList data are saved as a text file. Advanced users are welcome to update data directly by editing that data file.



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Bishop home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**List** | `list`
**todo** | `todo {description}`
**event** | `event {description} /at {description of time and place}`
**deadline** | `deadline {description} /by {yyyy-mm-dd}`
**mark** | `mark INDEX`
**unmark** | `unmark INDEX`
**delete** | `delete INDEX`
**bye** | `bye`
