# User Guide
Ekud is a **desktop app** for managing **tasks, deadlines, events and notes**. It accepts input through a Command Line Interface (CLI).

## Quick Start
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest `ekud.jar` from the [releases](https://github.com/vvidday/ip/releases) page.
3. Copy the file to the folder you want to use as the  *home folder*.
4. Double-click the file to start the app.
5. Start typing commands in the command box and press Enter to execute it! Refer to the [Features](#Features) section for details of each command.

## Features
Items are grouped into two categories - **tasks** and **notes**.

**Tasks** refer to items that can be marked as **done** or **undone**, whereas **notes** do not have such a state.

**Tasks** are split into three categories - **Todos**, **Deadlines** and **Events**.

Note that all commands are case sensitive.

### General

#### Exiting: `bye`
Format: `bye`
Exits the app.

### Tasks
#### Listing all tasks: `list`
Format: `list`

Lists all **todos**, **deadlines** and **events** along with a prefix indicating the index of the task, the type of task, and whether the task is done.

`[T]` indicates that the task is a **todo**, `[D]` indicates that the task is a **deadline**, and `[E]` indicates that the task is an **event**.

`[ ]` indicates that the task is undone, while `[x]` indicates the task is done.

#### Marking task as done: `mark`
Format: `mark <index>`

Marks the task at the specified index as done.

An error message will be displayed to the user if the user tries to mark a task that is already done.

Example usage:
- `mark 5`

#### Marking task as undone: `unmark`
Format: `unmark <index>`

Marks the task at the specified index as undone.

An error message will be displayed to the user if the user tries to mark a task that is already undone.

Example usage:
- `unmark 5`

#### Adding a todo: `todo`
Format: `todo <description>`

Adds a todo with specified description.

Todo is added with status undone.

Example usage:
- `todo task`

#### Adding a deadline: `deadline`
Format: `deadline <description> /by YYYY-MM-DD`

Adds a deadline with a specific date deadline.

Deadline is added with status undone.

Date must be specified, and must be in `YYYY-MM-DD` format.

Example usage:
- `deadline homework /by 2022-08-13`

#### Adding an event: `event`
Format: `event <description> /at <location>`

Adds an event with a specific location.

Event is added with status undone.

Example usage:
- `event party /at macpherson`

#### Searching tasks: `find`
Format: `find <search string>`

Searches for tasks that contain the specified string.

String is case sensitive.

Example usage:
- `find party`

#### Deleting a task: `delete`
Format: `delete <index>`

Deletes task with the specified index.

Example usage:
- `delete 5`

### Notes
#### Listing all notes: `listnote`
Format: `listnote`

Lists all notes along with their index.

Example usage:
- `listnote`

#### Add a note: `note`
Format: `note <description>`

Adds a note with the specified description.

Example usage:
- `note my note`

#### Delete a note: `deletenote`
Format: `deletenote <index>`

Deletes a note at the specified index.

Example usage:
- `deletenote 5`

## FAQ
**Q**: How do I find the index of the task that I am trying to mark/delete?

**A**: Use `list` to get a list of all tasks, including their index.

**Q**: How do I transfer my data to another computer?

**A**: Install the app on your computer, and overwrite the data files it creates with the current data files. They are located under the `data` directory, and are named `notes.txt` and `tasks.txt` respectively.


