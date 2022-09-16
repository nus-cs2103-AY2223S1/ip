# User Guide

Floren is a **desktop app for managing tasks, optimized for use via a Command-Line Interface (CLI)** 
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
- [FAQ / Troubleshooting](#faq--troubleshooting)
- [Command Summary](#command-summary)
- [Uncovered Issues?](#uncovered-issues)

## Quick Start / Setting Up
1. Ensure you have Java `11` installed in your computer. 

   > _Not familiar with Java?_ Refer to [this](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
   > installation guide.
2. Download the latest iteration of Floren [here](https://github.com/florentianayuwono/ip/releases/tag/A-Release). The specific file to download is `floren-{version}.jar`, e.g. `floren-v0.2.jar`.
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
Got it! I've added this task:
[T][ ] Homework
You have 2 tasks in the list.
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
Got it! I've added this task:
[E][ ] Concert (at: YST Conservatory)
You have 2 tasks in the list.
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
Got it! I've added this task:
[D][ ] iP (by: 2022-09-16)
You have 3 tasks in the list.
```
### Listing tasks : `list`
Lists the tasks that have been added to the bot.

**Format:** `list`

**Expected output:**
```
Aight. Here is your complete tasks.
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
Yay. Successfully removed this unwanted task :p
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
- `find meeting`

**Expected Output:**
```
1.[D][ ] meeting ambassador (by: 2022-09-16)
2.[T][ ] meeting President
```
### Marking your tasks as done: `mark`

Marks a task as done. Tasks that have been marked will be denoted with an "X".

**Format:** `mark <TASK_INDEX>`

**Examples:**
- `list` followed by `mark 2` marks the second task in the list.

**Expected output:**
```
Congrats on completing this task!
[D][X] iP (by: 2022-09-16) 
You have 9 tasks in the list.
```

### Unmark your tasks: `unmark`

Unmarks a task as done. Tasks that have been unmarked will be denoted with 
a white space [ ].

**Format:** `unmark <TASK_INDEX>`

**Examples:**
- `list` followed by `unmark 2` unmarks the second task in the list.

**Expected output:**
```
Got it. I've unmarked this task for you ;)
[D][ ] iP (by: 2022-09-16)
You have 2 tasks in the list.
```

## FAQ / Troubleshooting

### I cannot seem to start the bot.

> Please make sure you have Java `11` installed; refer to [this](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
> installation guide.

### Floren keeps telling me these messages and I don't know what to do.

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
> Floren failed to read the file where your tasks have been saved to (duke.txt). Please restart Floren and
> try again.
9. **☹ OOPS!!! Failed to write to file.**
> Isara failed to write to the file where your tasks should have been saved to (duke.txt). 
> Please restart Floren and try again.

## Command Summary

| Action                                                    |       Format       | Example                         | Output                                                                                                                                         | 
|-----------------------------------------------------------|:------------------:|---------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| [Add ToDo](#1-todo--todo)                                              | `todo <TASK_NAME>` | `todo Homework`                 | Got it! I've added this task: <br>[T][ ] Homework <br> You have 1 tasks in the list.                                                       |
 | [Add Event](#2-event-event)                                 | `event <EVENT_NAME> /at <PLACE_NAME>` | `event Concert /at Buona Vista` | Got it! I've added this task: <br> [E][ ] Concert (at: Buona Vista) <br> You have 2 tasks in the list.                                     |
 | [Add Deadline](#3-deadline--deadline)                        | `deadline <TASK_NAME> /by <YYYY-MM-DD>`  | `deadline iP /by 2022-09-16`    | Got it! I've added this task: <br>[D][ ] iP (by: 2022-09-16) <br> You have 3 tasks in the list.                                            |
 | [Listing tasks](#listing-tasks--list)                      | `list` | `list`                          | Aight. Here is your complete tasks. <br> 1. [T][ ] Run in the morning <br> 2. [D][ ] iP (by: 2022-09-16) <br> 3. [E][ ] Concert (at: Buona Vista) |
| [Deleting your tasks](#deleting-your-tasks--delete)       | `delete <TASK_INDEX>` | `delete 2`                      | Yay, successfully removed this unwanted task :p <br>[D][ ] iP (by: 2022-09-16) <br>Now you have 2 tasks in the list.                                            |
| [Finding your tasks](#finding-your-tasks--find)           | `find <KEYWORD>` | `find morning`                  |  1. [T][ ] Run in the morning                                                                     |
| [Marking your tasks as done](#marking-your-tasks-as-done-mark) | `mark <TASK_INDEX>` | `mark 2` | Congrats on completing this task! <br> [D][X] iP (by: 2022-09-16)                                                                           |
| [Unmark your tasks](#unmark-your-tasks-unmark)            | `unmark <TASK_INDEX>` | `unmark 2` | Got it. I've unmarked this task for you ;) <br> [D][ ] iP (by: 2022-09-16)                                                                     |
                                                                |

## Uncovered Issues?
If you have any other issue that has not been covered in this guide, please 
bring your concerns [here](https://github.com/florentianayuwono/ip/issues).
