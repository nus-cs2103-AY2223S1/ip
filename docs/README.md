# User Guide
![](Ui.png)
---

## Quick Start

---
1. Ensure you have Java ```11``` or above installed on your computer.
2. Download the latest ```duke.jar``` from [here](https://github.com/Nephelite/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Double-click ```duke.jar``` to start it.
5. Enter a command in the command box at the bottom of the opened window to execute it.
6. Refer to the Features below for details of each command.

## Features 

---
### Feature-Bye
Exits the program.
### Feature-List
Lists all tasks and their associated information in order of addition to the list.</br>
Information attached to tasks:</br>
- Task Description
- Completion status
- Time (In the case of Deadlines and Events)
- Tags

### Feature-Help
View all commands available to Duke within the application.
### Feature-Mark
Marks a task as done.
### Feature-Unmark
Marks a task as not done.
### Feature-Delete
Deletes a task from the list.
### Feature-ToDo
Creates a task to do and adds it to the task list.
### Feature-Deadline
Creates a task with a deadline and adds it to the task list.
### Feature-Event
Creates a task of an event to attend to at some date and adds it to the task list.
### Feature-Find
View all tasks with a word in their description or tags matching your keyword.
### Feature-Tag
Tag a task with some additional information
## Usage

---
> :information_source: **Notes about the command format:**
> <br/>Words in <> are argument types
> <br/>Parameters of commands must be in the exact order shown
> <br/>Commands cannot take in extra parameters.
> <br/>Excess words will be taken as an extension of the last argument String.
> <br/>Due to the above, in certain commands, this will cause them to fail.
> <br/>Words in {} are required syntax for a command. The command will fail without these exact characters.
> <br/>In the context of {}, the excess words after the last argument before a key syntax in {} will also be
> <br/>taken as an extension of the last argument string.

### `bye` - Exits the program

Terminates the program safely.

Format: ```bye```

Example of usage:</br>
`bye`

Expected outcome:</br>
End of the program.

```
Suisei:
See you again, have a nice day!
Otsumachi!
```

### `list` - List all tasks

Lists all tasks added in Chronological order based on time of addition, and all attached information.</br>
Information attached to tasks:
- Task Description
- Completion status
- Time (In the case of Deadlines and Events)
- Tags

Format: ```list```

Example of usage:</br>
`list`

Expected outcome:</br>
A list of tasks.

```
Suisei:
Here are the current tasks in your list:
1. [T] [ ] CS2103T iP
```

### `help` - List of commands

Lists all the commands and their proper usage within the application window.

Format: ```help```

Example of usage:</br>
`help`

Expected outcome:</br>
A list of commands and their usage formats.

### `Mark` - Mark task as done

Marks a task as done.

Format: ```mark <index of task in list>```

Example of usage:</br>
`mark 1`

Expected outcome:</br>
First task in the task list marked as done by filling the [ ] with an 'X'.

```
Suisei:
Yay! You completed thiis task:
[T] [X] CS2103T iP
Good job!
```

### `unmark` - Mark task as not done

Marks a task as not done.

Format: ```unmark <index of task in list>```

Example of usage:</br>
`unmark 1`

Expected outcome:</br>
First task is marked as not done.

```
Suisei:
Alright, I will mark this task as not done:
[T] [ ] CS2103T iP
```

### `delete` - Delete a task

Deletes a task from the task list.

Format: ```delete <index of task in list>```

Example of usage:</br>
`delete 1`

Expected outcome:</br>
First task deleted from task list

```
Suisei:
Yes. I shall purge this task:
[T] [ ] CS2103T iP
0 tasks remain in your list.
```

### `todo` - Add a task to do

Adds a task to do. A task to do is a generic task without a specific time attached to it.</br>
In the list, a to do task is indicated by a T in the first square brackets [ ].

Format: ```todo <description>```

Example of usage:

`todo CS2105 Pop Quiz`

Expected outcome:
Adds a task with the given description

```
Suisei:
I am adding your task to the list:
[T] [ ] CS2106 Pop Quiz
1 tasks remain in your list.
```

### `deadline` - Add a task with a Deadline

Adds a task with a deadline. A task with a deadline is a task with some date by which it must be done.</br>
In the list, a task with a deadline is indicated by a D in the first square brackets [ ].

Format: ```{deadline }<description>{ /by }<date in YYYY-MM-DD format>```

Example of usage:</br>
`deadline CS3230 Assignment 5 /by 1999-09-13`

Expected outcome:</br>
Adds a task with a deadline to the task list.
```
Suisei:
I am adding your task to the list:
[D] [ ] CS3230 Assignment 5 (by: 13 Sep 1999)
2 tasks remain in your list.
```

### `event` - Add a task to go to an Event

Adds a task to go to an event. An event is a task with some date by which the user must attend.</br>
In the list, a task with a deadline is indicated by an E in the first square brackets [ ].

Format: ```{event }<description>{ /at }<date in YYYY-MM-DD>```

Example of usage:</br>
`event CS2101 Team Meeting /at 2022-01-04`

Expected outcome:</br>
Adds a task to go to an event to the task list.

```
Suisei:
I am adding your task to the list:
[E] [ ] CS2101 Team Meeting (at: 1 Apr 2022)
3 tasks remain in your list.
```

### `Find` - Find tasks with a keyword

Searches for all tasks with descriptions or tags containing a given keyword.
> Note: Because the specified term is key**word**, the implication is that find only accepts **1** argument.
> This means your keyword must not contain a space/whitespace.

Format: ```find <keyword>```

Example of usage:</br>
`find CS3230`

Expected outcome:</br>
List of tasks with the given keyword.

```
Suisei:
Here are the matching tasks in your list:
1. [D] [ ] CS3230 Assignment 5 (by: 13 Sep 1999)
```

### `Tag` - Tags a task

Tags a task with additional information. In a list, tags are prefixed with '#'.</br>
A task has no practical limit as to how many tags it can have, but too many tags can make a 
task difficult to read. </br>
User Discretion is advised.

Format: ```tag <index of task in task list> <tag>```

Example of usage:</br>
`tag 1 Really Important`

Expected outcome:
First task in list is tagged with the given tag.

```
Suisei:
[T] [ ] CS2106 Pop Quiz has been tagged with #Really Important
```

Running ```list``` now should result in

```
Suisei:
Here are the current tasks in your list:
1. [T] [ ] CS2106 Pop Quiz #Really Important
2. [D] [ ] CS3230 Assignment 5 (by: 13 Sep 1999)
3. [E] [ ] CS2101 Team Meeting (at: 1 Apr 2022)
```

