Author: se-edu

Adapted from https://raw.githubusercontent.com/se-edu/addressbook-level3/master/docs/UserGuide.md
# User Guide


Duke is a **desktop chatbot app for managing and storing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). <br>

- [Quick Start](#quick-start)
- [Features](#features)
    * [Adding a todo task : `todo`, `t`](#adding-a-todo-task-todo)
    * [Adding a deadline Task: `deadline`, `d`](#adding-a-deadline-task-deadline)
    * [Adding an event Task: `event`, `e`](#adding-an-event-task-event)
    * [Marking a task: `mark`](#marking-a-task-mark)
    * [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    * [Deleting a task: `delete`, `rm`](#deleting-a-task-delete)
    * [Finding a task by description: `find`, `f`](#finding-a-task-by-description-find)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Exiting the program: `bye`, `exit`, `quit`](#exiting-the-program-bye)
    * [Saving the data](#saving-the-data)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Duke.jar` file from [here](https://github.com/jovitaanderson/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Double-click the file to start the app. 
5. Type the command in the command box and press Enter to execute it. You can try the follow example commands:
    - `list` : Lists out all the tasks.
    - `deadline return book /by 2022-09-17` : Adds a deadline task `return book (by:17 Sep 2022)` to the list.
    - `event read book /at 18 Sep 2022 12:00` : Adds an event task `read book (at: 18 Sep 2022 12:00)`
    - `mark 1` : Marks the 1st task on the list as done.
    - `delete 1` : Deletes the 1st task on the list.
    - `find book` : Finds a task with the matching keyword `book`
    - `bye` : Exits the app.
6. Refer to the [Features](#Features) below for details of each command.

##  Features
💡 Notes about the command format: <br>
Words in `{curly brackets}` are the parameters to be supplied by the user. <br>
e.g. in `todo {description}` , `{description}` is a parameter which can be used as `todo homework`

### Adding a todo task: `todo`
Adds a todo task to the task list. <br>
Format: `todo {description}` <br>
Example: `todo buy book`
<br> <br>
Format: `t {description}` <br>
Example: `t buy book`

### Adding a deadline task: `deadline`
Adds a deadline task to the task list. <br>
Format: `deadline {description} /by {YYYY-MM-DD}` <br>
Example: `deadline {description} /by 2022-09-18`
<br> <br>
Format: `d {description} /by {YYYY-MM-DD}` <br>
Example: `d {description} /by 2022-09-18`
### Adding an event task: `event`
Adds a deadline task to the task list. <br>
Format: `event {description} /at {date}` <br>
Exmaple: `event read book /at 18 Sep 2022 19:00`
<br> <br>
Format: `e {description} /at {date}` <br>
Exmaple: `e read book /at 18 Sep 2022 19:00`

### Marking a task: `mark`
Marks a task in the list as done.<br>
Format: `mark {index}`
Example: `mark 2` marks the task at index 2 of the list <br>
Expected outcome:
```
Nice! I've marked this task as done:
[E][X] Read book (at: 22 Sep 2022 19:30)
```
💡 `[X]` indicates a completed task

### Unmarking a task: `unmark`
Marks a task in the list as not done.<br>
Format: `unmark {index}`
Example: `unmark 1` unmark the task at index 1 of the list <br>
Expected outcome:
```
Ok, I've marked this task as not done yet:
[T][ ] Read book
```
💡 `[ ]` indicates an incomplete task

### Deleting a task: `delete`
Deletes a task from the task list.<br>
Format: `delete {index}`, `d {index}` <br>
Example: `delete 1` and `d 1` deletes the task at index 1 of the list

### Finding a task by description: `find`
Finds all tasks matching the keyword.<br>
Format: `find {keyword}`, `f {keyword}`
-  All task descriptions with the matching sequence of characters will be returned. e.g. `bo` matches with `book` and `bought`.

### Listing all tasks: `list`
Lists all out tasks in the task list.<br>
Format: `list`

### Exiting the program: `bye`
Exits the program immediately.<br>
Format: `bye`, `exit`, `quit`

### Saving the data
Duke automatically saves the task data after a valid command is executed. 

## FAQ
**Q**: How do I transfer my data to another Computer? <br>
**A**: Install the app on another computer and overwrite the data.txt file it creates with the data.txt that contains the data of your previous Duke chatbot located at `[location of JAR file]/tasks.txt`. <br>

## Command summary

Action | Format, Examples
--------|------------------
**Todo** | `todo {description}` <br> e.g., `todo do homework` <br> <br> `t {description}` <br> e.g., `t do homework`
**Deadline** | `deadline {description} /by {YYYY-MM-DD}` <br> e.g., `deadline cs2100 lab /by 2022-09-17`<br> <br> `d {description} /by {YYYY-MM-DD}`<br> e.g., `d cs2100 lab /by 2022-09-17`
**Event** | `event {description} /at {date}` <br> e.g., `event read book /at 17 Sep 20:00` <br> <br> `e {description} /at {date}`<br> e.g., `e read book /at 17 Sep 20:00`
**Mark** | `mark {index}`<br> e.g., `mark 1`
**Unmark** | `unmark {index}`<br> e.g., `unmark 1`
**Delete** | `delete {index}`<br> e.g., `delete 1`<br> <br>`rm {index}` <br> e.g., `rm 1`
**Find** | `find {keyword}` <br> e.g., `find cat`  <br> <br>`f {keyword}` <br> e.g., `f cat`
**List** | `list`
**Exit** | `bye`<br><br>`exit`<br><br>`quit`
