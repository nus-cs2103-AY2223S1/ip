# User Guide: CHAN BOT

CHAN BOT is a **desktop app for managing tasks** with a Graphical User Interface (GUI).

## Table of Content

* [Quick Start](#quick-start)

* [Features](#features)

* [Command summary](#command-summary)

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `duke.jar` from [here](https://github.com/chantellyu/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your task manager.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](Ui.png)

5. Type the command in the command box and press Enter to execute it.

6. Refer to the [Features](#features) below for details of each command.

## Features

### Viewing help: `help`

Shows a list of all the commands that can be entered.

Format: `help`

### Adding a todo task: `todo`

Adds a todo to the task list.

Format: `todo DESCRIPTION`

### Adding a deadline task: `deadline`

Adds a dated task to the task list.

Format: `deadline DESCRIPTION /by DATETIME`

How to format the date (DD/MM/YYYY HH:mm):

```
deadline iP /by 05/10/2022 15:30
```

### Adding an event task: `event`

Adds a dated event to the task list.

Format: `event DESCRIPTION /at DATETIME`

How to format the date (DD/MM/YYYY HH:mm):

```
event tP meeting /at 05/10/2022 15:30
```

### Listing all tasks: `list`

Shows a list of all the tasks in the task manager.

Format: `list`

### Marking a task: `mark`

Marks task at specified position index in the list as completed.

Format: `mark INDEX`

### Unmarking a task: `unmark`

Marks task at specified position index in the list as incomplete.

Format: `unmark INDEX`

### Deleting a task: `delete`

Deletes task at specified position index in the list.

Format: `delete INDEX`

### Locating tasks by keyword: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

### Showing task statistics: `statistics`

Shows the number of completed and incomplete tasks in the list.

Format: `statistics`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

## Command summary

| Action          | Format                              | Example                                 |
|-----------------|-------------------------------------|-----------------------------------------|
| **Help**        | `help`                              | `help`                                  |
| **Add todo**    | `todo DESCRIPTION`                  | `todo iP`                               |
| **Add deadline** | `deadline DESCRIPTION /by DATETIME` | `deadline iP /by 21/12/2022 15:30`      |
| **Add vent**    | `event DESCRIPTION /at DATETIME`    | `event tP meeting /at 21/12/2022 15:30` |
| **List**        | `list`                              | `list`                                  |
| **Mark**        | `mark INDEX`                        | `mark 1`                                |
| **Unmark**      | `unmark INDEX`                      | `unmark 1`                              |
| **Delete**      | `delete INDEX`                      | `delete 1`                              |
| **Find**        | `find KEYWORD`                      | `find meeting`                          |
| **Statistics**  | `statistics`                        | `statistics`                            |
| **Exit**        | `bye`                               | `bye`                                   |