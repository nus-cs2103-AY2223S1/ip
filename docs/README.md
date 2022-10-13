# User Guide for Meowmeow (=^-ω-^=)
Meowmeow is a **desktop app** that can help you keep track of your tasks.,
It is optimized for use via a **Command Line Interface (CLI)** while still having the benefits
of a **Graphical User Interface (GUI)**. 

## Table of Content
- [User Guide for Meowmeow](#user-guide-for-meowmeow)
    - [Table of Content](#table-of-content)
    - [Quick start](#quick-start)
    - [Features](#features)
        - [Add, delete and list tasks](#add-delete-and-list-tasks)
        - [Mark tasks as done](#mark-tasks-as-done)
        - [Find task](#find-task)
        - [Undo last command](#undo-last-command)
    - [Usage](#usage)
        - [`hi` - Say hi to Meowmeow](#hi---say-hi-to-meowmeow)
        - [`list` - Get a list of all tasks](#list---get-a-list-of-all-tasks)
        - [`find` - Search for tasks by keyword](#find---search-for-tasks-by-keyword)
        - [`todo` - Add a Todo task](#todo---add-a-todo-task)
        - [`deadline` - Add a Deadline task](#deadline---add-a-deadline-task)
        - [`event` - Add an Event task](#event---add-an-event-task)
        - [`delete` - Delete a task](#delete---delete-a-task)
        - [`undo` - Undo the last command](#undo---undo-the-last-command)
        - [`mark` - Mark a task as done](#mark---mark-a-task-as-done)
        - [`unmark` - Mark a finished task as undone](#unmark---mark-a-finished-task-as-undone)
        - [`bye` - Quit the program](#bye---quit-the-program)
    - [FAQ](#faq)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `meowmeow.jar` from [here](https://github.com/reneeyeow02/ip/releases/download/A-Release/meowmeow.jar).

1. Copy the file to the folder you want to use as the _home folder_ for Meowmeow.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. <br>
   ![Ui](Ui.png)

1. Type the command in the command box and press Enter to execute it. <br>
   Some example commands you can try:

* **`todo`** `Buy fish` : Adds a task named `Buy fish` to the task list.

* **`list`** : Lists all tasks.

* **`delete`** `1` : Deletes the 1st task shown in the current list.

* **`bye`** : Exits the app.

Refer to the [Usage](#usage) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features
### Add, delete and list tasks

You can add, delete and list all the tasks you have
added. The types of tasks you can add are `todo`, `deadline` and `event`.

* **todo**: tasks with no parameters.
* **deadline**: tasks with a deadline
* **event**: tasks that are at a specific date or time

### Mark tasks as done

You can mark tasks as done and an `X` will be placed beside
the task.

### Find task

You can search for the task you need with the `find` keyword.

### Undo last command
You can undo your last command with the `undo` keyword.

--------------------------------------------------------------------------------------------------------------------
## Usage

### `hi` - Say hi to meowmeow
Meowmeow will introduce itself.

### `list` - Get a list of all tasks
Meowmeow will list all tasks and their completion status.

### `find` - Search for tasks by keyword
Meowmeow will list all tasks that contain the keyword.

### `todo` - Add a Todo task
Meowmeow will add a Todo task to the list.

Example of usage:

`todo Buy fish for Meowmeow`

Expected outcome:

```
(=^-w-^=) [T] [ ] Buy fish for Meowmeow has been added to your task list!

You now have 1 tasks >w<
```


### `deadline` - Add a Deadline task
Meowmeow will add a Deadline task to the list.

Example of usage:

`deadline Buy fish for Meowmeow /by 2022-10-22T23:59:59`

Expected outcome:

```
(=^-w-^=) [D] [ ] Feed Meowmeow the fish (by: 11:59 PM on 22/10/2022) has been added to your task list!

You now have 2 tasks >w<
```

### `event` - Add an Event task
Meowmeow will add an Event task to the list.

Example of usage:

`event Meowmeow eats fish /at 5pm today`

Expected outcome:

```
(=^-w-^=) [E] [ ] Meowmeow eats fish (at: 5pm today) has been added to your task list!

You now have 3 tasks >w<
```

### `delete` - Delete a task
Meowmeow will delete the task with the specified number.

Example of usage:

`delete 3`

Expected outcome:

```
(=^-w-^=) [E] [ ] Meowmeow eats fish (at: 5pm today) has been deleted from your task list!

You now have 2 tasks >w<
```

### `undo` - Undo the last command
Meowmeow will undo your last command.

Example of usage:

`undo` after `delete 3`

Expected outcome:

```
Meowmeow has restored the task you deleted! (=^>w<^=)
```

### `mark` - Mark a task as done
Meowmeow will mark the task with the specified number as done.

Example of usage:

`mark 1`

Expected outcome:

```
Good job (=OwO=) You finished this task! 
[T] [X] Buy fish for Meowmeow
```

### `unmark` - Mark a finished task as undone
Meowmeow will mark the task with the specified number as undone.

Example of usage:

`unmark 1`

Expected outcome:

```
uwu this task has been marked as not done...
[T] [ ] Buy fish for Meowmeow
```

### `bye` - Quit the program
Meowmeow will close the program.

--------------------------------------------------------------------------------------------------------------------
## FAQ

**Qn**: Why can't I enter my command? <br>
**Ans**: Check the formatting of your command. If you are still having trouble, try restarting Meowmeow.

--------------------------------------------------------------------------------------------------------------------

