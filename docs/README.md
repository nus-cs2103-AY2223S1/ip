# User Guide

Rattus **is a desktop application that keeps track of various tasks**.
The application makes use of a Command Line Interface (CLI), while having a
Graphical User Interface (GUI) to enhance user experience.

## Quick start

1. [Install](https://www.oracle.com/java/technologies/downloads/#java11) 
**Java 11** if you have not done so.
2. [Download](https://github.com/jq1836/ip/releases)
the latest application file **Rattus.jar**.
3. Move the file into any folder you would like to use as the home folder for Rattus.
4. Left click the `Rattus.jar` twice. The application should start up, showing the 
page below.
5. Type a command in the text box to the bottom of the application. **Either**
click on the **Send** button or press the **Enter** key on your keyboard.
6. Refer to the [Features](#features) section for more details of each command or
alternatively, take a look at the [Command Summary]() section for a table of all
commands.

![UI](Ui.png)

## Features

**Notes about command formatting:**
- Words in `UPPERCASE` are the compulsory parameters to be supplied by the user.
<br> e.g. in `find KEYWORD`, `KEYWORD` is a parameter to be supplied by the user.
- Strictly **no** other parameters to be supplied.

### Adding a todo : `todo`

Creates a todo task and adds it to the list of current working file.

Format: `todo DESCRIPTION`
- Adds a todo task with the description `DESCRIPTION` to the list of tasks.
- The `DESCRIPTION` parameter is a description of the todo task and can be disjoint.

Example:
- `todo Wash the car` is an example where `Wash the car` is a disjoint `DESCRIPTION`.
<br>
- `todo Sleep` is an example where `Sleep` is a non-disjoint `DESCRIPTION`.


### Adding an event : `event`

Creates a timed event task and adds it to the list of current working file.

Format: `event DESCRIPTION /at DATE TIME`
- Adds an event task with the description `DESCRIPTION`, the date `DATE` and time
`TIME` of the event to the list of tasks.
- For more information about `DESCRIPTION`, click [here](#adding-a-todo--todo).
- The format of the `DATE` parameter is strictly `YYYY-MM-DD`.
- The format of the `TIME` parameter is strictly `HHmm`, in 24-hour time notation.

Example:
- `event Concert /at 2022-09-06 2000` is an example where the `MM` and `DD` are
1-digit numbers. The `0` in `09` and `06` is compulsory.
- `event Breakfast with the family /at 2022-11-20 0900` is an example where the
`HH` in the `TIME` parameter is a 1-digit number. The `0` in `09` is compulsory.

### Adding a deadline : `deadline`

Creates a task with a deadline and adds it to the list of current working file.

Format: `deadline DESCRIPTION /by DATE TIME`
- Adds a deadline task with the description `DESCRIPTION`, the date `DATE` and time
`TIME` of the event to the list of tasks.
- For more information about `DESCRIPTION`, click [here](#adding-a-todo--todo).
- For more information about `DATE` and `TIME`, click [here](#adding-an-event--event).

Example:
- `deadline Homework /by 2022-10-21 2359`

### Listing tasks : `list`

Shows all tasks within the current working file.

Format: `list`
- The tasks are displayed in the format `INDEX. [TYPE][STATUS] CONTENT`
- The `INDEX` is the index of the task, with the first task having an index of `1`.
- The `TYPE` is the type of task, `T`, `E` and `D` representing todo, event and
deadline respectively.
- The `STATUS` is either empty or `X`.
- The `CONTENT` includes the description and if applicable, the date and time of
associated with the task.

Example:
- `1. [T][] Complete iP` indicates that the task is a todo task, is the first task 
on the list and that the task has not been completed.
- `2. [E][] Family gathering (at: Oct 20 2022, 1700hrs)` indicates that the task is
an event task, is the 2nd task on the list, that the event has not passed and that
the event is on the 20th of October 2022 at 5:00pm.
- `3. [D][X] Finish homework (by: Sep 18 2022, 2359hrs)` indicates that the task is
a deadline task, is the 3rd task on the list, that the task has been completed,
and that the deadline of the task is on the 18th of September 2022 at 11:59pm.

### Exit from the application : `bye`

Exits the application.

Format: `bye`

### Delete a task : `delete`

Deletes a task within the current working file.

Format: `delete INDEX`
- The `INDEX` parameter is the index of the task to be deleted.
- The `INDEX` parameter must be a positive integer that corresponds to an existing
task in the current working file.

Example:
- `delete 2` deletes the 2nd task in the current working file.
- `delete 0` has an invalid `INDEX` input.

### Mark a task : `mark`

Marks a task within the current working file as completed.

Format: `mark INDEX`
- The `INDEX` parameter is the index of the task to be marked as completed.
- The `INDEX` parameter must be a positive integer that corresponds to an existing
task in the current working file.

Example:
- `mark 3` marks the 3rd task in the current working file as completed.

### Unmark a task : `unmark`

Marks a task within the current working file as not completed.

Format: `unmark INDEX`
- The `INDEX` parameter is the index of the task to be marked as not completed.
- The `INDEX` parameter must be a positive integer that corresponds to an existing
task in the current working file.

Example:
- `unmark 4` marks the 4th task in the current working file as not completed.

### Find a task with a keyword : `find`

Displays a list of tasks with a description that matches the keyword provided.

Format: `find KEYWORD`
- The `KEYWORD` parameter is case-sensitive and can be disjoint.

Example:
- Suppose that your list contains tasks with descriptions:
  1. `CS2100 assignment`
  2. `CS2109 assignment`
- `find assignment` would display both tasks.
- `find CS2100` would only display task (i).
- `find CS2109 A` would not display any tasks.
- `find CS2109 a` would only display task (ii).

### Check a task by date : `check`

Displays a list of timed-tasks with a corresponding date.

Format: `check DATE`
- The format of the `DATE` parameter is strictly `YYYY-MM-DD`.

Example:
- Suppose that your list contains timed-tasks with dates:
  1. `2022-09-10`
  2. `2022-09-10`
  3. `2022-09-11`
  4. `2023-09-10`
  5. `2022-10-10`
- `check 2022-09-10` would only display tasks (i) and (ii).

### Saving of tasks

Tasks are saved automatically after each command. The data files are stored within
the `[home directory]/data` directory. The home directory is the directory with
`Rattus.jar`.

### Adding a new data file : `filenew`

Creates a new data file. Does not redirect to new file.

Format: `filenew FILENAME`
- The `FILENAME` parameter is the name of the file to be created.
- If `FILENAME` parameter is the name of an existing file, no new file is created.
- The `FILENAME` parameter **should** not include the file type.

Example:
- `filenew Sem1` creates a new data file `Sem1.txt`.
- Another `filenew Sem1` command does not result in a new data file being created.
- `filenew Sem1.txt` creates a new data file `Sem1.txt.txt`, which is still usable.

### Deleting a data file : `filedel`

Deletes the corresponding data file if it exists.

Format: `filedel FILENAME`
- The `FILENAME` parameter is the name of the file to be deleted.
- The `FILENAME` parameter **must** not include the file type.

Example:
- `filedel Sem1` deletes the `Sem1.txt` file.

### Changing to another data file : `filechange`

Changes the working file to another data file if it exists.

Format: `filechange FILENAME`
- The `FILENAME` parameter is the name of the file to use.
- The `FILENAME` parameter **must** not include the file type.

Example:
- `filechange Sem1` changes the current working file to the `Sem1.txt` file.

## Command summary

| Action          | Format, Examples                                                                       |
|-----------------|----------------------------------------------------------------------------------------|
| **Todo**        | `todo DESCRIPTION`<br> e.g., `todo Sleep`                                              |
| **Event**       | `event DESCRIPTION /at DATE TIME`<br> e.g., `event Concert /at 2022-09-06 2000`        |
| **Deadline**    | `deadline DESCRIPTION /by DATE TIME`<br> e.g., `deadline Homework /by 2022-10-21 2359` |
| **List**        | `list`                                                                                 |
| **Exit**        | `bye`                                                                                  |
| **Delete**      | `delete INDEX`<br> e.g., `delete 1`                                                    |
| **Mark**        | `mark INDEX`<br> e.g., `mark 2`                                                        |
| **Unmark**      | `unmark INDEX` <br> e.g., `unmark 3`                                                   |
| **Find**        | `find KEYWORD`<br> e.g., `find assignment`                                             |
| **Check**       | `check DATE` <br> e.g., `check 2022-09-16`                                             |
| **New File**    | `filenew FILENAME` <br> e.g., `filenew Sem1`                                           |
| **Delete File** | `filedel FILENAME` <br> e.g., `filedel Sem2`                                           |                                            
| **Change File** | `filechange FILENAME` <br> e.g., `filechange Sem2`                                     |