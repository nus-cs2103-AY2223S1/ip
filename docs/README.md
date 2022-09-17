<!-- TOC -->

- [Quick Start](#quick-start)
- [Command Summary](#command-summary)
- [Features](#features)
  - [Listing all tasks - `list`](#listing-all-tasks---list)
  - [Adding a Todo task - `todo`](#adding-a-todo-task---todo)
  - [Adding an Activity task - `activity`](#adding-an-activity-task---activity)
  - [Adding a Deadline task - `deadline`](#adding-a-deadline-task---deadline)
  - [Adding an Event task - `event`](#adding-an-event-task---event)
  - [Marking a task as done - `mark`](#marking-a-task-as-done---mark)
  - [Marking a task as undone - `unmark`](#marking-a-task-as-undone---unmark)
  - [Deleting a task - `delete`](#deleting-a-task---delete)
  - [Searching for tasks - `find`](#searching-for-tasks---find)
  - [Exiting the program - `bye`](#exiting-the-program---bye)
  - [Saving task data - `save`](#saving-task-data---save)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)

<!-- /TOC -->

---

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest [`duke.jar`](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the *home folder* for your AddressBook.

4. Double-click the file to start the app. Alternatively, run `java -jar duke.jar` in the *home folder* of the file.

5. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   ![Ui](Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing **Enter** will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all tasks.

    * **`todo`** `homework` : Adds a `Todo` task with the description *homework*.

    * **`delete`** `3` : Deletes the 3rd task in the list.

    * **`bye`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

---

## Command Summary

Command | Format, Examples
--------|------------------
**List** | `list`
**Todo** | `todo DESCRIPTION​` <br> e.g., `todo Homework`
**Activity** | `activity DESCRIPTION /for HOURS​` <br> e.g., `activity workout /for 2`
**Deadline** | `deadline DESCRIPTION /by DATE [TIME]​` <br> e.g., `deadline assignment 1 /by 13/09/2022 23:59`
**Event** | `event DESCRIPTION /at DATE [TIME]​` <br> e.g., `event birthday /at 11/03/2023`
**Mark** | `mark INDEX`<br> e.g., `mark 1`
**Unmark** | `unmark INDEX`<br> e.g., `unmark 2`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find email john`
**Save** | `save`
**Bye** | `bye`

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Command Format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `delete INDEX`, `INDEX` is a parameter which can be used as `delete 3`.

* Items in square brackets are optional.<br>
  e.g `event DESCRIPTION /at DATE [TIME]` can be used as `event birthday party /at 11/03/2023` or as `event birthday party /at 11/03/2023 20:00`.

</div>

### Listing all tasks - `list`

Shows a list of all current tasks.

Format: `list`

### Adding a Todo task - `todo`

Adds a basic task to the task list. This is indicated by `[T]`.

Format: `todo DESCRIPTION​`

Examples:
*  `todo Homework`

### Adding an Activity task - `activity`

Adds a task with a fixed duration in hours to the task list. This is indicated by `[A]`.

Format: `activity DESCRIPTION /for HOURS​`
* `HOURS` must be a postive integer

Examples:
* `activity workout /for 2`
* `activity sleep /for 8`

### Adding a Deadline task - `deadline`

Adds a task with a deadline to the task list. This is indicated by `[D]`.

Format: `deadline DESCRIPTION /by DATE [TIME]​`
* `DATE` is formatted as `dd/MM/yyyy`
* `TIME` is formatted as `HH:mm`

Examples:
* `deadline finish report /by 23/11/2022`
* `deadline assignment 1 /by 13/09/2022 23:59`

### Adding an Event task - `event`

Adds a task that occurs on a certain day or time to the task list. This is indicated by `[E]`.

Format: `event DESCRIPTION /at DATE [TIME]​`
* `DATE` is formatted as `dd/MM/yyyy`
* `TIME` is formatted as `HH:mm`

Examples:
* `event birthday /at 11/03/2023`
* `event birthday party /at 11/03/2023 20:00`

### Marking a task as done - `mark`

Marks the task at the specified index of the list as done. This is indicated by `[X]`.

Format: `mark INDEX`
* The index refers to the index number of the task as shown using the `list` command
* The index **must be a positive integer**

Examples:
*  `mark 1` marks the 1st task in the list as done.

### Marking a task as undone - `unmark`

Marks the task at the specified index of the list as undone. This is indicated by `[ ]`.

Format: `unmark INDEX`
* The index refers to the index number of the task as shown using the `list` command
* The index **must be a positive integer**

Examples:
*  `unmark 2` marks the 2nd task in the list as undone.

### Deleting a task - `delete`

Deletes the task at the specified index of the list.

Format: `delete INDEX`
* The index refers to the index number of the task as shown using the `list` command
* The index **must be a positive integer**

Examples:
*  `delete 3` deletes the 3rd task in the list.

### Searching for tasks - `find`

Finds all tasks which contain all of the given keywords in its description.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-sensitive. e.g `email` will **NOT** match `Email`
* The order of the keywords does not matter. e.g. `email send` will match `send email`
* Only the description is searched.
* Partial words will be matched e.g. `submi` will match `submit` and `submission`.
* Only tasks matching **ALL** keywords will be returned (i.e. `AND` search). <br>
  e.g., `send email` will **NOT** return `email john`, `send letter`

Examples:
* `find email` returns `read emails` and `send email to john`
* `find submi` returns `submit report`, `assignment submission`

### Exiting the program - `bye`

Saves task data and exits the program.

Format: `bye`

### Saving task data - `save`

Saves task data in the file `data.txt`.

Format: `save`

### Editing the data file

MakiBot's task data is saved in a TXT file `data.txt` in the *home folder*.<br>
Advanced users are welcome to update data directly by editing that data file.

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and move the `data.txt` file that contains your task data from the *old home folder* to the *new home folder*.

---
