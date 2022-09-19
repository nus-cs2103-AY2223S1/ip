# <a name = "top"></a>User Guide

## Kohaku
Tohno Mansion's loyal maid, Kohaku-chan, at your service! How can I help you today, master?

<p align = "left">
<img src= "Ui.png" width = "300">
</p>

## Table of Content
* <a href = "#start">Quick Start</a>
* <a href = "#feature">Features</a>
* <a href = "#usage">Usage</a>
* <a href = "#summary">Command Summary</a>
* <a href = "#Credits">Credits</a>

## <a name = "start"></a>Quick Start
* Please ensure that you have Java 11 or above installed in your computer.
* Download the latest Kohaku.jar from [here](https://github.com/YZTangent/ip/releases/download/A-Release/Kohaku.jar).
* Copy the file to the directory you want to use.
* Run the application by opening the terminal from that directory, and type `java -jar Dave2.jar`
* Press *Enter* to summon kohaku! You should see a GUI similar to the one above.

## <a name = "feature"></a>Features
<a href = "#hi">Say hi to Kohaku</a><br>
<a href = "#todo">Add a ToDo</a><br>
<a href = "#deadline">Add a Deadline</a><br>
<a href = "#event">Add an Event</a><br>
<a href = "#list">View all your tasks</a><br>
<a href = "#mark">Mark your task as done</a><br>
<a href = "#unmark">Mark your task as undone</a><br>
<a href = "#delete">Delete your task</a><br>
<a href = "#find">Find your task by keyword</a><br>
<a href = "#archive">Archive your tasks</a><br>
<a href = "#load">Load your tasks from an archive</a><br>
<a href = "#bye">Exit chatbot</a>

## <a name = "usage"></a>Usage

### <a name = "todo"></a>Say hi to Kohaku - `hi`

Say hi to Kohaku-chan! She misses you >~<

**Command Format :** `hi`

**Example of usage:**

```
hi
> Tohno Mansion's loyal maid, Kohaku-chan, at your service! How can I help you today, master?
```
----

### <a name = "todo"></a>Add a ToDo - `todo`

Adds a To Do task to your task manager.

**Command Format :** `todo [task description]`

**Example of usage:**

```
todo laundry
> Got it, master!. I've added this task:
>   [T][ ] laundry
> Now you have 1 task in the list.
```
----
### <a name = "deadline"></a>Add a Deadline - `deadline`

Adds a deadline to your task manager.

**Command Format :** `deadline [task description] /by [yyyy/MM/dd HH:mm]`

**Example of usage:**

```
deadline CS2105 Assignment /by 2022/09/18 23:59
> Got it, master!. I've added this task:
>   [D][ ] CS2105 Assignment  (by: Sep, 18, 2022)
> Now you have 2 tasks in the list.
```
----
### <a name = "event"></a>Add an Event - `event`

Adds an Event to your task manager.

**Command Format :** `event [task description] /at [yyyy/MM/dd HH:mm]`

**Example of usage:**

```
event badminton /at 2022/09/17 16:00
> Got it, master!. I've added this task:
>   [E][ ] badminton  (by: Sep, 17, 2022)
> Now you have 3 tasks in the list.
```
----
### <a name = "list"></a>View all your tasks - `list`

Shows all the tasks in list of tasks, with numbering and completion status.

**Command Format :** `list`

**Example of usage:**

```
list
> Here are your tasks, master!
> 1. [T][ ] laundry 
> 2. [D][ ] CS2105 Assignment  (by: Sep, 18, 2022) 
> 3. [E][ ] badminton  (by: Sep, 17, 2022) 
```
----
### <a name = "mark"></a> Mark your task as done - `mark`

Marks the task as done with a "X" sign.

**Command Format :** `mark [index]`

**Example of usage:**

```
mark 1
> Yatta~ Congrats master, you've completed this task!
> [T][X] laundry
```
----
### <a name = "unmark"></a>Mark your task as undone - `unmark`

Marks the task as not done by replacing "X" sign with " ".

**Command Format :** `unmark [index]`

**Example of usage:**

```
unmark 1
> All the best for this task, master!
> [T][ ] laundry
```
----
### <a name = "delete"></a>Delete your task - `delete`

Removes the specified task from the task list.

**Command Format :** `remove [index]`

**Example of usage:**

```
remove 1
> Got it, master! I've removed this task:
>   [T][ ] laundry
> Now you have 2 tasks in the list.
```
----
### <a name = "find"></a>Find your task by keyword - `find`

Finds the task(s) with the given keyword, or part of keyword. The tasks will come with the correct index 
as in the original list, so you can perform operations on it easily.

**Command Format :** `find [keyword]`

**Example of usage:**

```
find badmin
> Here are the tasks that you are looking for! 
> 2. [E][ ] badminton  (by: Sep, 17, 2022) 
```
----
### <a name = "archive"></a>Archive your tasks - `archive`

Archive your tasks such that you can remove it from your current list and retrieve them later if needed.

When given an argument, this command will save the current list of tasks, with the given argument as the name of the archive.

When called without an argument, this command will list the list of archives that the bot currently has access to and can load.

**Command Format :** `archive [save message]`

**Example of usage:**

```
archive 17-09-22
> The current list of tasks has been archived under 17-09-22, master!
```

**Command Format :** `archive`

**Example of usage:**

```
archive
> Here is your archive:
> 1. yesterday's stuff
> 2. CS2103T work
> 3. 17-09-22 
```
----
### <a name = "archive"></a>Load your tasks from an archive - `load`

Loads all the tasks from an archive into your current task list.

**Command Format :** `load [save message]`

**Example of usage:**

```
load 17-09-22
> The archive 17-09-22 has been loaded to the current task list!
```
----
### <a name = "bye"></a>Exit chatbot - `bye`

Says bye to Kohaku and closes the program.

**Command Format :** bye

**Example of usage:**

```
bye
> See you soon Akiha-sama!
```
----

<p align = "right">
<a href = "#top">Back to top</a>
</p>

## <a name = "summary"></a> Command Summary


| COMMAND                    | FORMAT, EXAMPLES                                                                                    |
|----------------------------|-----------------------------------------------------------------------------------------------------|
| hi                         | `hi`                                                                                                |
| Add ToDo Task              | todo [descr] <br> e.g., `todo laundry`                                                              |
| Add Deadline Task          | deadline [descr] /by [yyyy/MM/dd HH:mm]<br> e.g., `deadline CS2105 Assignment /by 2022/09/18 23:59` |
| Add Event Task             | event [descr] /at [yyyy/MM/dd HH:mm]<br> e.g., `event badminton /at 2022/09/17 16:00`               |
| Mark Task                  | mark [index] <br> e.g., `mark 1`                                                                    |
| Unmark Task                | unmark [index] <br> e.g., `unmark 1`                                                                |
| Find Task with the keyword | find [keyword] <br> e.g., `find party`                                                              |
| Delete Task                | remove [index] <br> e.g., `remove 1`                                                                |
| View all Tasks             | `list`                                                                                              |
| Archive your tasks         | archive [save name] <br> e.g., `archive 17-09`                                                      |
| View list of archives      | `archive`                                                                                           |
| Load an archive            | load [save name] <br> e.g., `load 17-09`                                                            |
| Exit                       | `bye`                                                                                               |

<p align = "right">
<a href = "#top">Back to top</a>
</p>

## <a name = "credits"></a> Credits page
**User Guide:** User guide adapted from [anuanas2007's User Guide for Bro](https://github.com/anuanas2007/ip/tree/master/docs)