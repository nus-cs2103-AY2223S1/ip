Author: se-edu
Adapted from https://raw.githubusercontent.com/se-edu/addressbook-level3/master/docs/UserGuide.md

# User Guide
Duke is a desktop application that comes in a snow-themed Graphical User Interface (GUI).
It is a task management system with a priority scheduling which allows users to easily prioritise and add their data onto the system.

- [Quick Start](#quick-start)
- [Features](#features)
    * [Add a todo task](#add-a-todo-task-todo)
    * [Add a deadline Task](#add-a-deadline-task-deadline)
    * [Add an event Task](#add-an-event-task-event)
    * [List all tasks](#list-all-tasks-list)
    * [Mark a task](#mark-a-task-mark)
    * [Unmark a task](#unmark-a-task-unmark)
    * [Find a task by keyword](#find-a-task-by-keyword-find)
    * [Delete a task](#delete-a-task-delete)
    * [Exit the program](#exiting-the-program-bye)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Duke.jar` file from [here](https://github.com/wongyewjon/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Double-click the file to start the app.
5. Refer to the [Features](#Features) below for details on how to use each command.
   <br><br>

## Features
### Add a todo task: `todo`
Adds a todo task with LOW priority to the task list.

Commands: `todo {description}` `/` `todo {description} /p {level}`

Example: `todo buy a book` `/` `todo buy a book /p low`

Expected outcome:
```
Got it. I've added this task:
    [T][] buy a book (Priority: LOW)
Now you have 1 task in the list.
```
<br><br>

### Add a deadline task: `deadline`
Adds a deadline task with MEDIUM priority to the task list.

Command: `deadline {description} /by {DD/MM/YYYY} /p {leve}`

Example: `deadline assignment due /by 24/09/2022 /p medium`

Expected outcome:
```
Got it. I've added this task:
    [D][] assignment due (by: September 24 2022) (Priority: MEDIUM)
Now you have 2 tasks in the list.
```
<br><br>

### Add an event task: `event`
Adds an event task with URGENT priority to the task list.

Command: `event {description} /at {date} /p {level}`

Example: `event play Valorant /at 25 Sep 2022 19:00 /p urgent`

Expected outcome:
```
Got it. I've added this task:
    [E][] play Valorant (at: 25 Sep 2022 19:00) (Priority: URGENT)
Now you have 3 tasks in the list.
```
<br><br>

### List all tasks: `list`
Lists out all tasks in a descending priority. Tasks with highest priority will be at the top while tasks with the lowest priority will be at the bottom of the task list.

Command: `list`

Expected outcome:
```
Here are the tasks in your list:
1.[E][] play Valorant (at: 25 Sep 2022 19:00) (Priority: URGENT)
2.[D][] assignment due (by: September 24 2022) (Priority: MEDIUM)
3.[T][] read a book (at: Sep 10 2022) (Priority: LOW)
```
<br><br>

### Mark a task: `mark`
Marks a task in the list as done.

Command: `mark {index}`

Example: `mark 1` marks the task at index 1 of the list.

Expected outcome:
```
Nice! I've marked this task as done:
[T][X] read a book (at: Sep 10 2022) (Priority: LOW)
```
<br><br>

### Unmark a task: `unmark`
Marks a task in the list as not done.

Command: `unmark {index}`

Example: `unmark 1` unmark the task at index 1 of the list.

Expected outcome:
```
Ok, I've marked this task as not done yet:
[T][] read a book (at: Sep 10 2022) (Priority: LOW)
```
<br><br>

### Find a task by keyword: `find`
Finds all tasks matching the keyword.

Command: `find {keyword}`

Example: `find play` will find all tasks with keyword "play"

Expected outcome:
```
Here are the matching tasks in your list:
1.[E][] play Valorant play Valorant (at: 25 Sep 2022 19:00) (Priority: URGENT)
2.[T][] play badminton (Priority: MEDIUM)
```
<br><br>

### Delete a task: `delete`
Deletes a task from the task list.

Command: `delete {index}`

Example: `delete 1` deletes the task at index 1 of the list

Expected outcome:
```
Noted. I've removed this task.
[T][] read a book (Priority: LOW)
Now you have 2 tasks in the list.
```
<br><br>

### Exiting the program: `bye`
Exits the program immediately.

Command: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```
<br><br>

## Command summary

Action | Format, Examples
--------|------------------
**Todo** | `todo {description} /p {level}` <br> e.g., `todo read a book /p low`
**Deadline** | `deadline {description} /by {YYYY-MM-DD} /p {level}` <br> e.g., `deadline assignment due /by 24/09/2022 /p medium`
**Event** | `event {description} /at {date} /p {level}` <br> e.g., `event play badminton /at 15 Sep 19:00 /p high`
**Mark** | `mark {index}`<br> e.g., `mark 1`
**Unmark** | `unmark {index}`<br> e.g., `unmark 1`
**Delete** | `delete {index}`<br> e.g., `delete 1`
**Find** | `find {keyword}` <br> e.g., `find play`
**List** | `list`
**Exit** | `bye`

## Acknowlegements
- [GUI adaptation](https://se-education.org/guides/tutorials/javaFx.html)
- [JavaFX library](https://openjfx.io/)