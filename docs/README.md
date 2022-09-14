# User Guide
**Blob** is a desktop application to help you manage your tasks, optimized for users who are familar
with Command Line Interface (CLI) applications, or simply prefer typing, while still having the 
benefits of a Graphical User Interface (GUI)

## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding tasks](#adding-tasks)
    * [Priority](#priority)
    * [ToDo](#todo): `todo` 
    * [Event](#event): `event`
    * [Deadline](#deadline): `deadline`
  * [Listing all your tasks](#listing-tasks): `list`
  * [Marking a task as done](#marking-a-task): `mark`
  * [Unmarking a task](#unmarking-a-task): `unmark`
  * [Deleting a task](#deleting-a-task): `delete`
  * [Finding a task](#finding-tasks): `find`

---

## Quick Start
1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `blob.jar` file from [here](https://github.com/donljh/ip/releases/download/A-Release/blob.jar).
3. Copy the `.jar` file to the folder that you want to use as the _home folder_ for the application.
4. Double-click on the file to start the application. You should see a similar graphical user interface.
<img src = "Ui.png" width = "400px">

---

## Features


## Adding Tasks

Adds a task to be tracked by the application. Tasks can range from 3 types (ToDo, Event, Deadline).

#### Priority
All tasks must have an associated priority level. You can determine the priority level (LOW, MEDIUM, 
HIGH) upon initial creation of a task.

**Format:** `todo/event/deadline <TASK_PRIORITY> ...`

**Examples:**
* `todo //h Some Task` creates a ToDo task assigned high priority
* `event //m Some Event ...` creates an Event task assigned medium priority
* `deadline //l Some Deadline ...` creates a Deadline task assigned low priority

### ToDo: `todo`

Regular tasks with no associated date/time attached to it.

**Format:** `todo <TASK_PRIORITY> <TASK_DESCRIPTION>`

**Examples:**
* `todo //h CS Assignment`
* `todo //m Math Assignment`

**Expected Output:**
```
Blob will remember task...

    <HIGH>[T][] CS Assignment
    
Blob now remembers 1 task...
```

### Event: `event`

Tasks that occur at a specific date/time.

**Format:** `event <TASK_PRIORITY> <TASK_DESCRIPTION> /at <TASK_TIME>`

**Examples:**
* `event //h Birthday /at 9 Sep 2022`
* `event //m Lecture /at 22-09-2022`
* `event //l Lunch with friends /at 21-10-2022 12:00`

**Expected Output:**
```
Blob will remember task...

    <LOW>[E][] Lunch with friends (at: 21 Oct 2022 12:00)
    
Blob now remembers 1 task...
```

### Deadline: `deadline`

Tasks that have to be completed before a specific date/time.

**Format:** `deadline <TASK_PRIORITY> <TASK_DESCRIPTION> /by <TASK_TIME>`

**Examples:**
* `deadline //h iP /by 16 Sep 2022 23:59`
* `deadline //m Assignment /by 15 Sep 2022`
* `deadline //l Lecture quizzes /by 16-09-2022 12:00`

**Expected Output:**
```
Blob will remember task...

    <HIGH>[D][] iP (by: 16 Seo 2022 23:59)
    
Blob now remembers 1 task...
```

## Listing Tasks: `list`

Lists all the tasks that are currently being tracked in the application.

**Format:** `list`

**Expected Output:**
```
Blob now remembers 2 tasks...
1.<HIGH>[D][] iP (by: 16 Sep 2022 23:59)
2.<LOW>[E][] Lunch with friends (at: 21 Oct 2022 12:00)
```

## Marking a Task: `mark`

Marks a task as done

**Format:** `mark <TASK_INDEX>`

**Examples:**
* `list` followed by `mark 1` marks the first task in the list as done.

**Expected Output:**
```
Blob congratulates on task well done...

    <HIGH>[T][✓] Assignment
```

## Unmarking a Task: `unmark`

Unmarks a task as done.

**Format:** `unmark <TASK_INDEX>`

**Examples:**
* `list` followed by `unmark 1` unmarks the first task in the list as done.

**Expected Output:**
```
Blob will mark as undone...

    <HIGH>[T][] Assignment
```

## Deleting a Task: `delete`

Deletes a task from the list of tracked tasks.

**Format:** `delete <TASK_INDEX>`

**Examples:**
* `list` followed by `delete 1` deletes the first task in the list.

**Expected Output:**
```
Ok... Blob forget task...

    <HIGH>[T][] Assignment
```

## Finding Tasks: `find`

Finds tasks that have descriptions containing the given keyword.

**Format:** `find <KEYWORD>`

**Examples:**
* `find Assignment` finds and lists all tasks that contain `Assignment` in their task descriptions.

**Expected Output:**
```
Blob found 3 tasks...
1.<HIGH>[D][] CS Assignment (by: 16 Sep 2022)
2.<HIGH>[D][] CS Lab (by: 16 Sep 2022)
3.<HIGH>[D][] CS Tutorial (by: 16 Sep 2022)
```
