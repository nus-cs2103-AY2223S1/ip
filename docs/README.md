# User Guide

RatatouilleBot is a **desktop chat-bot allows you to manage your tasks
by mimicking a real personal manager**.
It is optimized for use via a **Command Line Interface** (CLI) while 
still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, RatatouilleBot can get your task management 
tasks done faster than traditional GUI apps.

* [Quick start](#quick-start)
* [Features](#features)
  * [Adding a task : `todo`/`event`/`deadline`](#1-adding-a-task-todo-deadline-event)
  * [Viewing the task list : `list`](#2-viewing-the-task-list-list)
  * [Deleting a task : `delete`](#3-deleting-a-task-delete)
  * [Finding a task : `find`](#4-finding-a-task-find)
  * [Marking a task : `mark`/`undone`](#4-marking-a-task)
  * [Exiting the program : `exit`](#5-exiting-the-program)
* [FAQ](#faq)

---

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest RatatouilleBot.jar 
from [here](https://github.com/jhchee18/ip/releases).
3. Copy the file to the folder you want to use as the home folder 
for your RatatouilleBot.
4. Double-click the file to start the app. The GUI similar to the 
below should appear in a few seconds. Note how the app contains some 
sample data.
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
   Some example commands you can try:

   * `list` : Lists all tasks.
   * `todo study midterm /by 2022-09-15 1030` : 
   Adds a task with task description _study midterm_, date and time to 
   the task list.
   * `delete 3` : Deletes the 3rd task shown in the current list.
   * `mark 2` : Mark the 2nd task as done.
   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.


---

## Features 

:closed_book: _Notes about the command format:_

- Words in UPPER_CASE are the parameters to be supplied by the user. 
<br>
e.g. in `find KEYWORD`, `KEYWORD` is a parameter which can be used as
`find midterm`.


- Items in square brackets are optional.
<br>
e.g. `TASK_DESCRIPTION [/by DATE TIME]` can be used as `study midterm` or 
as `study midterm /by 2020-12-12 1234`.


- Extraneous parameters for commands that do not take in 
parameters (such as `list` and `exit`) will be ignored.
e.g. if the command specifies `list 123`, it will be interpreted as `help`.

<br>

### 1. Adding A Task: `todo`, `deadline`, `event`

Adds a new task into your task list by using the following 
keywords (`TASK_TYPE`) to start your command:
- `todo`
- `deadline`
- `event`

Enter your command by following the format below:
<br>
`TASK_TYPE TASK_DESCRIPTION [/by DATE TIME]`

_Note: `DATE TIME` must follow the format of `YYYY-MM-DD HHMM`_

Examples:
<br>
- `todo study Monday quiz /by 2022-12-31 2359`
- `deadline cs2103 iP`

<br>

### 2. Viewing The Task List: `list`

Views the whole task list consisting of the added tasks.

Enter your command by following the format below:
<br>
`list`

<br>

### 4. Finding A Task: `find`

Finds the task(s) which contains the specified keyword.

Enter your command by following the format below:
<br>
`find KEYWORD`

_Note:_
- _`KEYWORD` is perceived as one whole phrase._
- _e.g. `cs2103 assignment` will match `complete cs2103 assignment`
instead of `cs2103 iP assignment`._

Examples:
<br>
- `find assignment` returns `cs2103 iP assignment` and `me4291 assignment 2`.
- `find 2022-09-15` returns `me4291 quiz`.

<br>

### 3. Deleting A Task: `delete`

Deletes the specified numbered task.

Enter your command by following the format below:
<br>
`delete TASK_INDEX`

_Note:_
- _`TASK_INDEX` must not exceed the size of the task list._
- _`TASK_INDEX` must be positive integer e.g. 1,2,3..._

Examples:
<br> 
- `list` followed by `delete 5` deletes the 5th task
in the task list.

<br>

### 4. Marking A Task

Marks/un-marks task from the task list
by using the following keywords (`MARK_TYPE`):
- `mark`
- `unmark`

Enter your command by following the format below:
<br>
`MARK_TYPE TASK_NUMBER`

_Note:_
- _`TASK_INDEX` must not exceed the size of the task list._
- _`TASK_INDEX` must be positive integer e.g. 1,2,3..._

Examples:
- `mark 3`
- `unmark 1`

<br>

### 5. Exiting The Program

Exits the program.

Enter your command by following the format below:
<br>
`bye`

---

## FAQ

**Q**: Where can I find my task list stored in my file explorer?
<br>
**A**: Task list is stored in the `data/duke.txt` file.
It is located at the root directory of the RatatouilleBot.

**Q**: How do I transfer my data to another Computer?
<br>
**A**: Install the app in the other computer and overwrite the empty data 
file it creates with the file that contains the data of your previous
RatatouilleBot home folder.
