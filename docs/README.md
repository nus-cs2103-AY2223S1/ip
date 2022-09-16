# User Guide

Isara is a **desktop app for managing tasks, optimized for use via a Command-Line Interface (CLI)** 
while still having a Graphical User Interface (GUI). 

> New to the terms _CLI_ and _GUI_? See [here](https://www.geeksforgeeks.org/difference-between-cli-and-gui/)
for a short summary on the differences between CLI and GUI.

## Table of Contents
- [Quick Start / Setting Up](#quick-start--setting-up)
- [Features](#features)
  - [Adding tasks :](#adding-tasks)
    - [ToDo `todo`](#1-todo--todo)
    - [Event `event`](#2-event-event)
    - [Deadline `deadline`](#3-deadline--deadline)
  - [Listing your tasks : `list`](#listing-tasks--list)
  - [Deleting your tasks : `delete`](#deleting-your-tasks--delete)
  - [Finding your tasks : `find`](#finding-your-tasks--find)
  - [Marking your tasks as done : `mark`](#marking-your-tasks-as-done-mark)
  - [Unmark your tasks : `unmark`](#unmark-your-tasks-unmark)
  - [Rescheduling your tasks (deadlines) : `reschedule`](#rescheduling-your-tasks-deadlines-reschedule)
- [FAQ / Troubleshooting](#faq--troubleshooting)
- [Command Summary](#command-summary)
- [Uncovered Issues?](#uncovered-issues)

## Quick Start / Setting Up
1. Ensure you have Java `11` installed in your computer. 

   > _Not familiar with Java?_ Refer to [this](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
   > installation guide.
2. Download the latest iteration of Isara [here](https://github.com/melissaharijanto/ip/releases/tag/A-Release). The specific file to download is `isara-{version}.jar`, e.g. `isara-v0.2.jar`.
3. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 

<img src = "Ui.png" width = "300px">

> **Note:** The list is filled with placeholder data.

## Features

### Adding Tasks

Adds a task to the bot's list. There are three types of tasks: 

### 1. **ToDo** : `todo` 
Quick tasks that need to be done soon without the need of a specific time or place.

**Format:** `todo <TASK_NAME>`

**Examples:**
- `todo Homework`
- `todo Watch CS2103T Lectures`
- `todo CS1231S Homework`

**Expected output:**
```
Got it. I've added this task:
[T][ ] Homework
Now you have 2 tasks in the list.
```
### 2. **Event**: `event`
Tasks that need to be done with the need of a specific place.

**Format:** `event <EVENT_NAME> /at <PLACE_NAME>`

**Examples:**
- `event Concert /at YST Conservatory`
- `event CAC+US /at Yusof Ishak House`
- `event Mom's Birthday /at HaiDiLao`

**Expected output:**
```
Got it. I've added this task:
[E][ ] Concert (at: YST Conservatory)
Now you have 2 tasks in the list.
```
### 3. **Deadline** : `deadline` 
Tasks that need to be done before a specific time.

**Format:** `deadline <TASK_NAME> /by <YYYY-MM-DD>`

**Examples:**
  - `deadline iP /by 2022-09-16`
  - `deadline User Guide /by 2022-09-16`
  - `deadline Lab Assignment /by 2022-08-13`

**Expected output:**
```
Got it. I've added this task:
[D][ ] iP (by: 2022-09-16)
Now you have 3 tasks in the list.
```
### Listing tasks : `list`
Lists the tasks that have been added to the bot.

**Format:** `list`

**Expected output:**
```
Here are the tasks in your list:
1. [T][ ] Run in the morning
2. [D][ ] iP (by: 2022-09-16)
3. [E][ ] Concert (at: Buona Vista)
```
### Deleting your tasks : `delete`

Deletes a task that has been added to the bot.

**Format:** `delete <TASK_INDEX>`
- Deletes task at a specific index.
- The index refers to the index number shown in the displayed person list. 
- The index must be a positive integer 1, 2, 3...

**Examples:**
- `list` followed by `delete 2` deletes the second task in the list.

**Expected output:**
```
Noted. I've removed this task:
[D][ ] iP (by: 2022-09-16)
Now you have 2 tasks in the list.
```
### Finding your tasks : `find`

Finds a task with the given keyword.

**Format:** `find <KEYWORD>`
- Keywords can consist of more than one word.
- Keywords can only be the **task name**, not the places or dates.
- This feature is case-sensitive.

**Examples:**

- `find in the Morning`
- `find iP`

**Expected Output:**
```
Here are the matching tasks in your list:
1.[D][ ] iP (by: 2022-09-16) 
```
### Marking your tasks as done: `mark`

Marks a task as done. Tasks that have been marked will be denoted with an "X".

**Format:** `mark <TASK_INDEX>`

**Examples:**
- `list` followed by `mark 2` marks the second task in the list.

**Expected output:**
```
Nice! I've marked this task as done:
[D][X] iP (by: 2022-09-16) 
```

### Unmark your tasks: `unmark`

Unmarks a task as done. Tasks that have been unmarked will be denoted with 
a white space [ ].

**Format:** `unmark <TASK_INDEX>`

**Examples:**
- `list` followed by `unmark 2` unmarks the second task in the list.

**Expected output:**
```
OK, I've marked this task as not done yet:
[D][ ] iP (by: 2022-09-16)
```

### Rescheduling your tasks (deadlines): `reschedule`
Reschedules your deadlines to another date.

**Format:** `reschedule <TASK_INDEX> /by <YYYY-MM-DD>`
- Cannot apply to other tasks aside from deadlines. 

**Examples:**
- `list` followed by a `reschedule 2 /by 2022-09-18` will reschedule a task.

**Expected output:**
```
You have just rescheduled the following task:
[D][ ] iP (by: 2022-09-18)
```
## FAQ / Troubleshooting

### I cannot seem to start the bot.

> Please make sure you have Java `11` installed; refer to [this](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
> installation guide.

### Isara keeps telling me these messages and I don't know what to do.

1. **☹ OOPS!!! Please specify what you want to do!**
> It is likely that you did not include the **description** of the task when adding a **ToDo**. Please
> make sure to refer to [this](#1-todo--todo) guide.
2. **☹ OOPS!!! The description of an event cannot be empty.**
> It is likely that you did not include the **description** of the task when adding an **Event**. Please
> make sure to refer to [this](#2-event-event) guide.
3. **☹ OOPS!!! The description of a deadline cannot be empty.**
> It is likely that you did not include the **description** of the task when adding a **Deadline**. Please
> make sure to refer to [this](#3-deadline--deadline) guide.
4. **☹ OOPS!! Date is invalid! Hint: Make it YYYY-MM-DD.**
> You inserted a wrong date format. Like stated, please make it YYYY-MM-DD. Example: `2022-09-15` means `15 September 2022`.
5. **☹ OOPS!!! I'm sorry, but I don't know what that means :-(**
> You likely inputted a wrong command line. Please try again!
6. **☹ OOPS!! I don't know when you are rescheduling this to.**
> You might have added a date of the wrong format, or you did not add a date at all.
7. **☹ OOPS!! Please enter the task number after the command!**
> Please enter a valid positive integer after the command, e.g. `mark 2`, `delete 5`, `unmark 3`
8. **☹ OOPS!!! Failed to read file.**
> Isara failed to read the file where your tasks have been saved to (isara.txt). Please restart Isara and
> try again.
9. **☹ OOPS!!! Failed to write to file.**
> Isara failed to write to the file where your tasks should have been saved to (isara.txt). 
> Please restart Isara and try again.

## Command Summary

| Action                                                    |       Format       | Example                         | Output                                                                                                                                         | 
|-----------------------------------------------------------|:------------------:|---------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| [Add ToDo](#1-todo--todo)                                              | `todo <TASK_NAME>` | `todo Homework`                 | Got it. I've added this task: <br>[T][ ] Homework <br> Now you have 1 tasks in the list.                                                       |
 | [Add Event](#2-event-event)                                 | `event <EVENT_NAME> /at <PLACE_NAME>` | `event Concert /at Buona Vista` | Got it. I've added this task: <br> [E][ ] Concert (at: Buona Vista) <br> Now you have 2 tasks in the list.                                     |
 | [Add Deadline](#3-deadline--deadline)                        | `deadline <TASK_NAME> /by <YYYY-MM-DD>`  | `deadline iP /by 2022-09-16`    | Got it. I've added this task: <br>[D][ ] iP (by: 2022-09-16) <br> Now you have 3 tasks in the list.                                            |
 | [Listing tasks](#listing-tasks--list)                      | `list` | `list`                          | Here are the tasks in your list: <br> 1. [T][ ] Run in the morning <br> 2. [D][ ] iP (by: 2022-09-16) <br> 3. [E][ ] Concert (at: Buona Vista) |
| [Deleting your tasks](#deleting-your-tasks--delete)       | `delete <TASK_INDEX>` | `delete 2`                      | Noted. I've removed this task: <br>[D][ ] iP (by: 2022-09-16) <br>Now you have 2 tasks in the list.                                            |
| [Finding your tasks](#finding-your-tasks--find)           | `find <KEYWORD>` | `find morning`                  | Here are the matching tasks in your list: <br>1. [T][ ] Run in the morning                                                                     |
| [Marking your tasks as done](#marking-your-tasks-as-done-mark) | `mark <TASK_INDEX>` | `mark 2` | Nice! I've marked this task as done: <br> [D][X] iP (by: 2022-09-16)                                                                           |
| [Unmark your tasks](#unmark-your-tasks-unmark)            | `unmark <TASK_INDEX>` | `unmark 2` | OK, I've marked this task as not done yet: <br> [D][ ] iP (by: 2022-09-16)                                                                     |
| [Rescheduling your tasks](#rescheduling-your-tasks-reschedule) | `reschedule <TASK_INDEX> /by <YYYY-MM-DD>` | `reschedule 2 /by 2022-09-18` | You have just rescheduled the following task: <br>[D][ ] iP (by: 2022-09-18)                                                                   |

## Uncovered Issues?
If you have any other issue that has not been covered in this guide, please 
bring your concerns [here](https://github.com/melissaharijanto/ip/issues).