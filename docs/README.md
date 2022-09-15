# Uncle Cheong User Guide

Uncle Cheong is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

- [Quick Start](#quick-start)
- [Features](#features)
   * [Adding tasks](#1-adding-tasks)
       1. [Add a todo](#--add-a-todo)
       2. [Add an event](#--add-an-event)
       3. [Add a deadline](#--add-a-deadline)
   * [List tasks](#2-list-tasks)
   * [Mark tasks](#3-mark-tasks)
   * [Unmark tasks](#4-unmark-tasks)
   * [Delete tasks](#5-delete-tasks)
   * [Find tasks](#6-find-tasks)
   * [View Schedule](#7-view-schedule)
- [Command Summary](#command-summary)

## Quick Start
Ensure you have Java 11 or above installed in your Computer.

Download the latest uncle-cheong.jar from [here](https://github.com/MuhdMusab/ip/releases/tag/v0.2).

Copy the file to the folder you want to use as the home folder for your Uncle Cheong jar file.

Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

## Features 

### 1. Adding tasks

#### - Add a Todo

Command: `todo {task details}`

Example: `todo make the bed`

Expected outcome:
```
Swee lah! I added this task liao:
[T][ ] make the bed
Boss, you got 1 task now
```

#### - Add an Event

Command: `event {task details} /at {date and time in MMM d yyyy hh:mm a}`

Example: `event JB trip /at Sep 15 2022 08:00 AM`

Expected outcome:
```
Swee lah! I added this task liao:
[E][ ] JB trip (at: 15-Sep-2022 08:00 AM)
Boss, you got 2 tasks now
```

#### - Add a Deadline

Command: `deadline {task details} /by {date and time in MMM d yyyy hh:mm a}`

Example: `deadline tutorial submission /by Sep 16 2022 11:59 PM`

Expected outcome:
```
Swee lah! I added this task liao:
[D][ ] tutorial submission (by: 16-Sep-2022 11:59 PM)
Boss, you got 3 tasks now
```

### 2. List tasks

Description: lists out all the tasks in the list according to the order at which the task was added

Command: `list`

Expected outcome:
```
Boss ah, this one your tasks:
1. [T][ ] make the bed
2. [E][ ] JB trip (at: 15-Sep-2022 08:00 AM)
3. [D][ ] tutorial submission (by: 16-Sep-2022 11:59 PM)
```

### 3. Mark tasks

Description: marks the task at the input task number as completed

Command: `mark {task number}`

Example: `mark 2`

Expected outcome:
```
Swee lah! I marked this task as done liao:
[E][X] JB trip (at: 15-Sep-2022 08:00 AM)
```

### 4. Unmark tasks

Description: marks the task at the input task number as completed

Command: `unmark {task number}`

Example: `unmark 2`

Expected outcome:
```
Eh? Not done yet? Okay I change liao:
[E][] JB trip (at: 15-Sep-2022 08:00 AM)
```

### 5. Delete tasks

Description: delete the task at the input task number

Command: `delete {task number}`

Example: `delete 3`

Expected outcome:
```
Okay boss, this task I delete le:
[D][ ] tutorial submission (by: 16-Sep-2022 11:59 PM)
```

### 6. Find tasks

Description: View all the tasks in your list that contain the keyword

Command: `find {keyword}`

Example: `find CS2102`

Expected outcome:
```
Boss ah, this one all your tasks matching 'CS2102':
1. [T][ ] CS2102 Lecture Recording
2. [D][ ] CS2102 Tutorial (by: 12-Sep-2022 05:00 PM)
```

### 7. View schedule

Description: View all the events in your list at the input date

Command: `schedule /at {date in MMM d yyyy}`

Example: `schedule /at Sep 15 2022`

Expected outcome:
```
Here are your tasks at 2022-09-15
[E][] JB trip (at: 15-Sep-2022 08:00 AM)
```

## Command Summary

Action | Format, Examples
--------|------------------
**Todo** | `todo {description}` <br> e.g., `todo make the bed`
**Event** | `event {task details} /at {date and time in MMM d yyyy hh:mm a}` <br> e.g., `event JB trip /at Sep 15 2022 08:00 AM`
**Deadline** | `deadline {task details} /by {date and time in MMM d yyyy hh:mm a}` <br> e.g., `deadline submit assignment /by 25 Sep`
**Mark** | `mark {task number}`<br> e.g., `mark 2`
**Unmark** | `unmark {task number`<br> e.g., `unmark 2`
**Delete** | `delete {task number}`<br> e.g., `delete 3`
**Find** | `find {keyword}`<br> e.g., `find cs2102`
**List** | `list`
**Exit** | `bye`
