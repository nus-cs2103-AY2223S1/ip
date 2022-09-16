# Table of Contents
- [Introduction](#introduction)
- [Getting Started](#getting_started)
- [Features](#features)
- [Commands](#commands)
  - [Add a todo task: `todo`](#todo)
  - [Add a deadline task: `deadline`](#deadline)
  - [Add an event task: `event`](#event)
  - [List all tasks: `list`](#list)
  - [Mark a task as done: `check`](#check)
  - [Mark a task as not done: `uncheck`](#uncheck)
  - [Delete a task: `delete`](#delete)
  - [Find tasks by keyword: `find`](#find)
  - [Show help message: `help`](#help) 
  - [Exit the program: `exit`](#exit)
- [Advanced Features](#advanced_features)
  - [Data File](#data_file)
  
# Introduction <a name="introduction"></a>
![](Ui.png)

Duke is a personal chat-bot to help you keep track of your tasks, events, or deadlines.
It is optimized for keyboard users, but has a GUI for the looks as well.

# Getting Started <a name="getting_started"></a>
- Ensure you have Java 11 or above installed in your Computer.
- Download the latest jar from the [release page](https://github.com/RezwanArefin01/ip/releases/).
- Copy the file to the folder you want to use as the home folder for your Duke.
- Make sure the directory has read and write permissions.
- Run `java -jar duke.jar` in a terminal to start Duke. A data directory will be created in the location you execute the command from.
  - Do not use double click to start the application. Because your OS can decide to execute the command from a different place.
- Enter `help` to get started!

# Features <a name="features"></a>
- [x] Add a todo task.
- [x] Add a task with a deadline.
- [x] Add an event.
- [x] Check/uncheck a task.
- [x] Find tasks by keyword.
- [x] Delete a task.
- [x] List all tasks.

# Commands <a name="commands"></a>

## Add a todo task: `todo DESCRIPTION` <a name="todo"></a>
Adds a task with the given `DESCRIPTION`.

**Example of usage:**
```
todo Return CLRS book to the library.
> I've added the following task:
>   [📝][ ] Return CLRS book to the library.
```

## Add a deadline task: `deadline DESCRIPTION / DATE TIME` <a name="deadline"></a>
Adds a task with the given `DESCRIPTION` and a deadline specified by `DATE` and `TIME`.

**Date format**
- Recommended: `dd-mm-yyyy`.
- `-` may be replaced by `/`.
- Number of digits in the day and month may be 1 or 2.
- Year may be 2 or 4 digits.

**Time format:**
- Recommended: `hhmm`.
- Alternative: `hh:mm`.
- Number of digits in the hour and minute must be 2.
- Hour must be in 24-hour format.

**Example of usage:**
```
deadline CS2103T iP / 16-09-2022 2359
> I've added the following task:
>   [⏰][ ] CS2103T iP (by: 16 Sep 2022 11:59 PM)
```

## Add an event: `event DESCRIPTION / DATE TIME` <a name="event"></a>
Adds an event with the given `DESCRIPTION` and a time specified by `DATE` and `TIME`.

Date and time format is the same as the deadline command.

**Example of usage**:
```
event CS2103T lecture / 16-09-2022 1600
> I've added the following task:
>   [📅][ ] CS2103T lecture (at: 16 Sep 2022 4:00 PM)
```

## List all tasks: `list` <a name="list"></a>
Lists all tasks in the task list.

**Example of usage:**
```
list
> List of tasks:
>   1. [📝][ ] Return CLRS book to the library.
>   2. [⏰][ ] CS2103T iP (by: 16 Sep 2022 11:59 PM)
>   3. [📅][ ] CS2103T lecture (at: 16 Sep 2022 4:00 PM)
```

## Mark a task as done: `check INDEX` <a name="check"></a>
Marks the task at the given `INDEX` as done.

You may want to use the `list` command to find the index of the task you want to mark as done.

**Example of usage:**
```
check 1
> I've updated the following task:
>   [📝][✔] Return CLRS book to the library.
```

## Mark a task as not done: `uncheck INDEX` <a name="uncheck"></a>
Marks the task at the given `INDEX` as not done.

**Example of usage:**
```
uncheck 1
> I've updated the following task:
>   [📝][ ] Return CLRS book to the library.
```

## Delete a task - `delete INDEX` <a name="delete"></a>
Deletes the task at the given `INDEX`.

**Example of usage:**
```
delete 1
> I've deleted the following task:
>   [📝][ ] Return CLRS book to the library.
list
> List of tasks:
>   1. [⏰][ ] CS2103T iP (by: 16 Sep 2022 11:59 PM)
>   2. [📅][ ] CS2103T lecture (at: 16 Sep 2022 4:00 PM)
```

## Find tasks by keyword: `find KEYWORD` <a name="find"></a>
Finds all tasks that contain the given `KEYWORD`.
The search is case-insensitive.

**Example of usage:**
```
find cS
> Task containing the keyword "cS":
>   1. [⏰][ ] CS2103T iP (by: 16 Sep 2022 11:59 PM)
>   2. [📅][ ] CS2103T lecture (at: 16 Sep 2022 4:00 PM)
```

## Show help message: `help` <a name="help"></a>
Shows a help message with a list of commands and their usage.

## Exit the program: `exit` <a name="exit"></a>
Exits the program with a goodbye message.

# Advanced Features <a name="advanced_features"></a>
## Data File <a name="data_file"></a>
All the data is saved in the file `./data/data.txt`, relative to the directory you execute the jar from. 
You can modify task list by directly editing this file. Each line of the file describes one task. 
The format is the following:
```
TASK SYMBOL | IS DONE? | DESCRIPTION [| ARGS...]
```
- `TASK SYMBOL` is `T` for todo task, `D` for deadline, and `E` for event tasks.
- `IS DONE?` is 0 or 1 depending whether the task is due or not.
- `DESCRIPTION` is the description of the task.
- Currently, the `ARGS...` take in a date time for deadline and events. The date-time must be in the
format `h-M-yy HHmm`. Specifically, day and month number should not have leading zeros, and year must have
2 digits.

**Example data file:**
```
T | 0 | Return CLRS book to the library.
D | 0 | CS2103T iP. | 16-9-22 2359
E | 0 | CS2103T lecture. | 16-9-22 1800
```

However, do note that if the data file is corrupted, Duke will not startup. It will show you a warning
about the corruption and you can choose to keep the file and investigate, or start with a new file.

