# User Guide

**Note**: This guide is adapted from [AB-3's User Guide](https://se-education.org/addressbook-level3/UserGuide.html)

Duke is a desktop application for managing tasks.
It is optimized for usage for fast typists familiar with the Command Line Interface (CLI), while still having the benefits of a Graphical User Interface (GUI).
Furthermore, it has a gentle learning curve and is fast to use, enabling you to supercharge your productivity and *get things done*.

![Duke](./Ui.png)

> Duke frees your mind of having to remember things you need to do

---

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. You can check whether Java 11 is installed by opening your terminal window and running the command `java -version`
3. If Java 11 is not installed, please download Java 11 from [Oracle](https://www.oracle.com/java/technologies/downloads/#java11).
4. For Mac users, you may use the [Azul build of OpenJDK 11 (JDK FX) version](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).
5. Download the latest duke.jar from [the application's release page](https://github.com/EmilyOng/ip/releases/tag/v0.2).
6. Copy the file to the folder you want to use as the home folder for your Duke.
7. Double-click the file to start the app.

---

## Features

**Notes about the command format**

- Words enclosed in diamond brackets `<>` are the parameters to be supplied by the user.
- Items with `...` after them can be used one or more times.
- Parameters must respect the order of the command format
- Extra parameters for commands that do not take in parameters will be ignored.


In Duke, there are 3 types of tasks:
- **Deadline `D`**: Used when there are deadlines to meet, and you need to keep track of what to do
- **Event `E`**: Used when there is an event that you need to attend, and you need to keep track of it
- **ToDo `T`**: The most fundamental type of tasks that denote an item that you intend to accomplish

All tasks come with the ability to be described, and support marking or unmarking tasks as completed.

### Create Tasks

Tasks, such as a Deadline, Event or ToDo, can be created in the application and are stored in a local file.

### Toggle Task Completion

Finished a task? Or accidentally marked a task as completed?
In Duke, you can mark or unmark tasks as completed to track the tasks' completion status.
Furthermore, we keep track of the date in which tasks are marked as completed, and provide statistics on the percentage of tasks that you have completed in the past week.

### Delete Tasks

Tasks can be deleted as well. Deleted tasks are permanently removed from the local file storage.

### List Tasks

In Duke, you can easily view the list of tasks, and even provide additional filters to view the list of tasks occurring before a certain date, or on a certain date.

### Find Tasks

In Duke, you can conveniently look up existing tasks by providing some search keywords.

---

## Usage

### `deadline <description> /by <deadline>` - Create a Deadline Task

Creates a Deadline task with a description and a deadline.
By default, the task will be marked as undone.

Example of usage: 

`deadline return book /by 2022-12-02`

Expected outcome:

```
Got it! I've added this task:
    [D] [ ] return book (by: Dec 2 2022)
Now you have 4 task(s) in the list.
```

Description of the outcome.
- A Deadline task described by "return book" is created and added to the existing list of tasks.
- The task will be due on 2nd December 2022.

### `event <description> /at <date>` - Create an Event Task

Creates an Event task with a description and a date on which the event occurs.
By default, the task will be marked as undone.

Example of usage:

`event project meeting /at 2022-12-02`

Expected outcome:

```
Got it! I've added this task:
    [E] [ ] project meeting (at: Dec 2 2022)
Now you have 5 task(s) in the list.
```

Description of the outcome.
- An Event task described by "project meeting" is created and added to the existing list of tasks.
- The event will occur on 2nd December 2022.

### `todo <description>` - Create a ToDo Task

Creates a ToDo task with a description.
By default, the task will be marked as undone.

Example of usage:

`todo borrow book`

Expected outcome:

```
Got it! I've added this task:
    [T] [ ] borrow book
Now you have 6 task(s) in the list.
```

Description of the outcome.
- A ToDo task described by "borrow book" is created and added to the existing list of tasks.

### `mark <task>` - Mark a task as done

Marks the task corresponding to the provided task number as done.
The task number can be obtained from the `list` command, which will display the list of tasks along with the task number.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [T] [X (Done on: Sep 16 2021)] project work
```

Description of the outcome.
- The task corresponding to the task number `1` is marked as done.

### `unmark <task>` - Mark a task as undone

Marks the task corresponding to the provided task number as undone.
The task number can be obtained from the `list` command, which will display the list of tasks along with the task number.

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
    [T] [ ] project work
```

Description of the outcome.
- The task corresponding to the task number `1` is marked as undone.

### `find <keyword> ...` - Find task(s)

Finds the task(s) whose description matches all the provided search keywords.

Example of usage:

`find project meeting`

Expected outcome:

```
Here are the matching tasks in your list:
1. [E] [ ] project meeting (at: Dec 2 2022)
```

Description of the outcome.
- The task(s) whose description contains both "project" and "meeting" keywords are displayed.

### `list` - List all the tasks

Lists all the tasks in the application.

Example of usage:

`list`

Expected outcome:

```
1. [D] [ ] return book (by: Dec 2 2022)
2. [E] [ ] project meeting (at: Dec 2 2022)
3. [T] [ ] borrow book
```

Description of the outcome.
- The list of all the tasks in the application are displayed.

### `list /before <date>` - List all the tasks occurring before a date

Lists all the tasks in the application occurring before the provided date.

Example of usage:

`list /before 2022-12-22`

Expected outcome:

```
1. [D] [ ] return book (by: Dec 2 2022)
2. [E] [ ] project meeting (at: Dec 2 2022)
```

Description of the outcome.
- The list of all the tasks in the application occurring before 22nd December 2022 are displayed.

### `list /on <date>` - List all the tasks occurring on a date

Lists all the tasks in the application occurring on the provided date.

Example of usage:

`list /on 2022-12-02`

Expected outcome:

```
1. [D] [ ] return book (by: Dec 2 2022)
2. [E] [ ] project meeting (at: Dec 2 2022)
```

Description of the outcome.
- The list of all the tasks in the application occurring on 22nd December 2022 are displayed.

### `delete` - Delete a task

Deletes the task corresponding to the provided task number.
The task number can be obtained from the `list` command, which will display the list of tasks along with the task number.

`delete <task>`

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
    [T] [ ] project work
Now you have 5 task(s) in the list.
```

Description of the outcome.
- The task corresponding to the task number `1` is deleted.

### `bye` - Terminate the application

Terminates the application.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

Description of the outcome.
- Duke bids goodbye!

### Saving the data

Duke's data is saved in a local file automatically after any command that changes the data.
There is no need to save manually.

---

## Command Summary

| **Action**                                     | **Format**                              | **Examples**                           |
|------------------------------------------------|-----------------------------------------|----------------------------------------|
| **Create a Deadline Task**                     | `deadline <description> /by <deadline>` | `deadline return book /by 2022-12-02`  |
| **Create a Event Task**                        | `event <description> /at <date>`        | `event project meeting /at 2022-12-02` |
| **Create a ToDo Task**                         | `todo <description>`                    | `todo borrow book`                     |
| **Mark a task as done**                        | `mark <task>`                           | `mark 1`                               |
| **Mark a task as undone**                      | `unmark <task>`                         | `unmark 1`                             |
| **Find task(s)**                               | `find <keyword> ...`                    | `find project meeting`                 |
| **List all the tasks**                         | `list`                                  | `list`                                 |
| **List all the tasks occurring before a date** | `list /before <date>`                   | `list /before 2022-12-22`              |
| **List all the tasks occurring on a date**     | `list /on <date>`                       | `list /on 2022-12-02`                  |
| **Delete a task**                              | `delete <task>`                         | `delete 1`                             |
| **Terminate the application**                  | `bye`                                   | `bye`                                  |
