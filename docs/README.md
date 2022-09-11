# User Guide

TedBot is a simple **desktop chatbot app**, meant to help you keep track
of your day-to-day tasks. Designed with fast typers in mind, simply type
in your commands and TedBot will handle the rest.

## Features

### Command-based Interactions

As TedBot is originally designed for Command-Line Interfaces, interactions
with TedBot is strictly done through typing commands into the chatbot.

This allows fast typers to be more efficient at their work when compared to
GUI-based counterparts.

### Task List

TedBot keeps track of a list of tasks that it has recorded.

There are three types of tasks that are handled by TedBot:
1. Todo, these are tasks that do not have a set time associated with them.
2. Deadline, these are tasks that have a deadline date associated with them.
3. Event, these are tasks that have an associated start and ending dates.

### Local Storage

TedBot stores user data locally on the disk, so information such as
the task list persist between sessions.

### User Data Archival

TedBot allows users to archive their user data, such that tasks that are
no longer important to the user can be easily cleared away but still be
accessible if need-be.

## Usage

### `todo` - Adds Todo Task

> Format: `todo DESC`

Adds a Todo-type task named `DESC` to TedBot's task list.

Example:
- `todo Buy Groceries` will add a Todo-type task named **Buy Groceries** into
  TedBot's task list.

### `deadline` - Adds Deadline Task

> Format: `deadline DESC /by YYYY-MM-DD`

Adds a Deadline-type task named `DESC` with its deadline at the specified
`YYYY-MM-DD` date into TedBot's task list.

Example:
- `deadline Math Homework /by 2023-01-01` will add a Deadline-type task named
  **Math Homework** that is due on **January 1st, 2023** into TedBot's task list.

### `event` - Adds Event Task

> Format: `event DESC /at YYYY-MM-DD [YYYY2-MM2-DD2]`

Adds an Event-type task named `DESC` that occurs on `YYYY-MM-DD` into TedBot's task list.
If the second date `YYYY2-MM2-DD2` is provided, the event will be recorded to occur in between those days.

Example:
- `event Physics Exam /at 2023-01-01` will add an Event-type task named **Physics Exam** that occurs on
  **January 1st, 2023** into TedBot's task list.
- `event Career Fair /at 2022-08-16 2022-08-17` will add an Event-type task named **Career Fair**
  that occurs from **August 16th, 2022** to **August 17th, 2022** into TedBot's task list.


### `list` - Lists Tasks

> Format: `list`

Prints out the list of tasks recorded by TedBot.

### `mark` - Marks Task as Completed

> Format: `mark TASK_NUMBER`

Marks the task numbered `TASK_NUMBER` as completed.

Example:
- `mark 3` will mark the **third** task in the list as completed.

### `unmark` - Marks Task as Not Completed

> Format: `unmark TASK_NUMBER`

Marks the task numbered `TASK_NUMBER` as **not** completed.

Example:
- `unmark 2` will mark the **second** task in the list as not completed.

### `delete` - Deletes Task

> Format: `delete TASK_NUMBER`

Deletes the task numbered `TASK_NUMBER` from TedBot's task list.

Example:
- `delete 1` will delete the **first** task from the task list.

### `find` - Finds Tasks

> Format: `find KEYWORD`

Finds all tasks in TedBot's task list that contains the provided `KEYWORD` in its description.
It should be noted that `KEYWORD` is **case-sensitive**.

Example:
- For the following examples, let's assume TedBot's task list currently contains **2103 Project**, **2101 Reflection**,
  **1231 Assignment**, and **3216 Project**.
- `find 210` will return **2103 Project** and **2101 Reflection**.
- `find Project` will return **2103 Project** and **3216 Project**.

### `archive` - Archives Task List

> Format: `archive [PATH] [/force]`

Archives the current task list into the provided `PATH` as a text file and clears the task list afterwards.
It should be noted that the provided `PATH` should be relative to the location of the executable.

If `PATH` is not provided, the task list will be archived into `data/archive.txt` by default.

If there exists a file at the specified `PATH`, the task list will not be archived into `PATH` to prevent
accidental overwrites. This behaviour can be overriden by adding the `/force` flag.

Example:
- `archive` will store the current task list at `data/archive.txt` and clear the task list.
- `archive /force` will store the current task list at `data/archive.txt` and clear the task list.
  Contents of `data/archive.txt` file will be overwritten if it exists.
- `archive save/backup1.bak` will store the current task list at `save/backup1.bak` and clear the task list.


### `bye` - Stops Chatbot Session

> Format: `bye`

Terminates the Chatbot session. Note that the chatbot window is **not** automatically closed.
