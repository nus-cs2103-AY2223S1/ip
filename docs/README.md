# User Guide

Duke+ is a ***desktop app for keeping track of and managing various tasks via a Command Line Interface (CLI)***, while still having the benefits of a Graphical User Interface.
The name Duke+ was chosen in honor of Duke, the Java Mascot.

## Quick start



1. Ensure you have Java **`11`** or above installed in your Computer.
2. Download the latest **`Duke.jar`** from [here](https://github.com/nealetham/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Duke+.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
![Ui](Ui.png)
5. Type the command in the command box and press Enter to execute it.  Some example commands you can try:
    - **`list`** : Lists all tasks.
    - **`todo Homework`** : Adds a todo item with description **`Homework`** into your current list.
    - **`deadline Assignment /by 2022-10-12 23:59`** : Adds a deadline item with description **`Assignment`** and due date **`2022-10-12 23:59`**.
    - **`event Party /at @Keith's House`** : Adds an event item with description **`Party`** and remark **`@Keith's House`**. 
    - **`delete 3`** : Deletes the 3rd item shown in the current list.
    - **`bye`** : Exits the application. 
    
6. Refer to the [Features](#Features) below for details of each command.

---
## Features

**Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Homework`.

* All parameters are compulsory and to be specified by the user. 


### Viewing list : `list`

Shows a list of all pending tasks in storage.

Format: `list`
* Tasks displayed using `list` are formatted as `INDEX. [TYPE][STATUS] CONTENT`.
* The `INDEX` is the index of the task within the list. It is one-indexed.
* The `TYPE` has values `T`, `D` and `E` for todo, deadline and event respectively. 
* The `STATUS` has values `O` and `X`, indicating that a task has been completed or not respectively.

Example:
* `[T][O] Homework` indicates an *uncompleted todo task*, with description *Homework*.
* `[E][X] Party (at: Today)` indicates a *completed event*, with remark *Today*. 



### Adding a todo: `todo`
Adds a todo item to storage.

Format: `todo DESCRIPTION`
* Adds a todo task to the task list with description `DESCRIPTION`.
* The `DESCRIPTION` parameter can be a word, or a sentence.

Examples:
* `todo CS2103T Homework` Adds a todo task with description `CS2103T Homework` to task list.
* `todo House_Chores` Adds a todo task with description `House_Chores` to task list.

### Adding a deadline: `deadline`
Adds a deadline item to the task list.

Format: `deadline DESCRIPTION /by DUE_DATE`
* Adds a deadline task to storage with description `DESCRIPTION` and 
  due date `DUE_DATE` to task list.
* The `DESCRIPTION` parameter can be a word, or a sentence.
* The format of the due date is `YYYY-MM-DD HH:MM`.

Examples:
* `deadline CS2100 Lab Report /by 2022-09-14 23:59` Adds a deadline task with description `CS2100 Lab Report` and due date `2022-09-14 23:59` to task list.
* `deadline Assignment /by 2022-09-18 10:00` Adds a deadline task with description `Assignment` and due date `2022-09-18 10:00` to task list.

### Adding a event: `event`
Adds an event item to the task list.

Format: `event DESCRIPTION /at REMARKS`
* Adds an event task to storage with description `DESCRIPTIONS` and additional remarks `REMARKS` to task list.
* The `DESCRIPTION` and `REMARKS` parameter can be a single word, or a sentence.

Examples:
* `event House Party /at at Keith's House` Adds an event with description `House Party` and remark `at Keith's House` to task list.
* `event Clubbing /at at Zouk` Adds an event with description `Clubbing` and remark `at Zouk` to task list. 

### Deleting a task: `delete`
Deletes an item from storage from the given index.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* The index refers to the index number of the task shown in `list`.
* The index must be a positive integer 1, 2, 3, ... and must exist on the list.

Examples:
* `list` followed by `delete 2` deletes the 2nd task on the list.

### Checking-off a task: `mark`
Marks an existing task as completed.

Format: `mark INDEX`
* Marks the task as completed at the specified `INDEX`.
* The index refers to the index number of the task shown in `list`.
* The index must be a positive integer 1, 2, 3, ... and must exist on the list.

Examples:
* `list` followed by `mark 2` marks the 2nd task on the list as completed.

### Finding a task with strict querying: `find`
Searches for an item that has strict matching with the given query.

Format: `find STRICT_QUERY`
* The query is case-sensitive.
* There must be a one-to-one correspondence between the query and an **entire** word of a task in the list.


Examples:
* Suppose your list contains `[T][O] Mathematics Homework`.
* `find Math` would return no results, but `find Mathematics` would return the task above.

### Finding a task with partial querying: `match`
Searches for an item that partially matches with the given query.

Format: `match PARTIAL_QUERY`
* The query is case-sensitive.
* There must be a one-to-one correspondence between the query and a **portion** of a word of a task in the list. 

Examples:
* Suppose your list contains `[T][O] Mathematics Homework`.
* Both `match Math` and `match Mathematics` would return the task above.

### Exiting the program : `bye`
Exits the program.

Format: `bye`

### Saving the data
Duke+ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

---

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke+ home folder.

---


## Command summary

Action | Format, Examples
-------|------------------
**List** | `list`
**Todo** | `todo DESCRIPTION`<br> e.g., `todo CS2103T Homework` 
**Deadline** | `deadline DESCRIPTION /by DUE_DATE`<br> e.g., `deadline CS2100 Lab Report /by 2022-09-14 23:59`
**Event** | `event DESCRIPTION /at REMARKS`<br> e.g., `event House Party /at at Keith's House`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Mark** | `mark INDEX`<br> e.g., `mark 3`
**Find** | `find STRICT_QUERY`<br> e.g., `find Math`
**Match** | `find PARTIAL_QUERY`<br> e.g., `find Mathematics`
**Bye** | `bye`
