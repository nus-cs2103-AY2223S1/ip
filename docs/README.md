Author: se-edu

Adapted from https://raw.githubusercontent.com/se-edu/addressbook-level3/master/docs/UserGuide.md
# User Guide


Duke is a **desktop chatbot app for managing and storing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). <br>

- [Quick Start](#quick-start)
- [Features](#features)
    * [Add a todo task : `todo`, `t`](#add-a-todo-task-todo)
    * [Add a deadline Task: `deadline`, `d`](#add-a-deadline-task-deadline)
    * [Add an event Task: `event`, `e`](#add-an-event-task-event)
    * [List all tasks: `list`](#list-all-tasks-list)
    * [Mark a task: `mark`](#mark-a-task-mark)
    * [Unmark a task: `unmark`](#unmark-a-task-unmark)
    * [Find a task by keyowrd: `find`, `f`](#find-a-task-by-keyword-find)
    * [Delete a task: `delete`, `rm`](#delete-a-task-delete)
    * [Exit the program: `bye`, `exit`, `quit`](#exiting-the-program-bye)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Duke.jar` file from [here](https://github.com/jialatteo/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Double-click the file to start the app. 
5. Refer to the [Features](#Features) below for details on how to use each command.
<br><br>

## Features
### Add a todo task: `todo`
Adds a todo task to the task list. <br><br>
Format: `todo {description}` <br>
Example: `todo buy book` <br><br>
Format: `t {description}` <br>
Example: `t buy book`
<br><br>

### Add a deadline task: `deadline`
Adds a deadline task to the task list. <br><br>
Format: `deadline {description} /by {YYYY-MM-DD}` <br>
Example: `deadline {description} /by 2022-09-18`
<br> <br>
Format: `d {description} /by {YYYY-MM-DD}` <br>
Example: `d {description} /by 2022-09-18`
<br><br>

### Add an event task: `event`
Adds an event task to the task list. <br><br>
Format: `event {description} /at {date}` <br>
Example: `event read book /at 18 Sep 2022 19:00`
<br> <br>
Format: `e {description} /at {date}` <br>
Example: `e read book /at 18 Sep 2022 19:00`
<br><br>

### List all tasks: `list`
Lists out all tasks in the task list.<br><br>
Format: `list` <br> <br>
Expected outcome:
```
Here are the tasks in your list:
1.[E][X] Borrow book (at: Sep 10 2022)
2.[T][] Read book
3.[D][] Return book to library (by: Sep 17 2022)
4.[D][] Finish CS2100 lab (by: Sep 17 2022)
```
<br><br>

### Mark a task: `mark`
Marks a task in the list as done.<br><br>
Format: `mark {index}` <br>
Example: `mark 2` marks the task at index 2 of the list <br><br>
Expected outcome:
```
Nice! I've marked this task as done:
[T][X] Read book 
```
<br><br>

### Unmark a task: `unmark`
Marks a task in the list as not done.<br>
Format: `unmark {index}` <br>
Example: `unmark 1` unmark the task at index 1 of the list <br> <br>
Expected outcome:
```
Ok, I've marked this task as not done yet:
[E][] Borrow book (at: Sep 10 2022)
```
<br><br>

### Find a task by keyword: `find`
Finds all tasks matching the keyword.<br>
Format: `find {keyword}`, `f {keyword}` <br>
Example: `find book` will find all tasks with keyword "book"
 <br> <br>
Expected outcome:
```
Here are the matching tasks in your list:
1.[E][] Borrow book (at: Sep 10 2022)
2.[T][] Read book
3.[D][] Return book to library (by: Sep 17 2022)
```
<br><br>

### Delete a task: `delete`
Deletes a task from the task list.<br>
Format: `delete {index}`, `rm  {index}` <br> 
Example: `delete 1` and `rm 1` deletes the task at index 1 of the list
<br> <br>
Expected outcome:
```
Noted. I've removed this task.
[E][] Borrow book (at: Sep 10 2022)
Now you have 3 tasks in the list
```
<br><br>

### Exiting the program: `bye`
Exits the program immediately.<br>
Format: `bye`, `exit`, `quit`
<br><br>

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
