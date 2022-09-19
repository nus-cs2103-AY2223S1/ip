# User Guide: MyDuke

MyDuke is a **desktop app that helps users keep track of their tasks, optimised for use via a Control Line Interface** (CLI).

## Table of Content

* [Quick Start](#quick-start)

* [Features](#features)

* [Command summary](#command-summary)

## Quick Start

1. Ensure you have Java `11` installed in your Computer.

2. Download the latest `duke.jar` from [here](https://github.com/turretDive/ip/releases).

3. Launch the app by double-clicking the file or by using the command `java -jar duke.jar`. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](Ui.png)

4. Type the command in the command box and press Enter to execute it.

5. Refer to the [Features](#features) below for details of each command.

## Features

### Listing all tasks: `list`

Shows a list of all the tasks added.

Format: `list`

Example outcome(s):

```
Your list is empty.
```

```
Here are the tasks in your list:
1. [T][ ] Go School
2. [D][ ] Homework (by: May 5 2022) 
3. [E][ ] Lecture (at: May 5 2022) 
```

### Adding a ToDo: `todo`

Adds a task with the provided description.

Format: `todo DESCRIPTION`

Example of usage:

```
todo Go School
```

Example outcome:

```
Got it. I've added this task: 
[T][ ] Go School
Now you have 1 tasks in the list.
```

### Adding a Deadline: `deadline`

Adds a task with the provided description and due date.

Format: `deadline DESCRIPTION /by DATE`

Example of usage:

```
deadline Homework /by 2022-05-05
```

Example outcome:

```
Got it. I've added this task: 
[D][ ] Homework (by: May 5 2022)
Now you have 2 tasks in the list.
```

### Adding an Event: `event`

Adds a task with the provided description and start date.

Format: `event DESCRIPTION /at DATE`

Example of usage:

```
event Lecture /at 2022-05-05
```

Example outcome:

```
Got it. I've added this task: 
[E][ ] Lecture 3 (at: May 5 2022) 
Now you have 3 tasks in the list.
```

### Marking a task: `mark`

Marks the task with the provided index as completed.

Format: `mark INDEX`

Example of usage:

```
mark 1
```

Example outcome:

```
Nice! I've marked this task as done: 
[T][X] Go School
```

### Unmarking a task: `unmark`

Marks the task with the provided index as uncompleted.

Format: `unmark INDEX`

Example of usage:

```
unmark 1
```

Example outcome:

```
OK. I've marked this task as not done yet: 
[T][ ] Go School
```

### Finding a task: `find`

Finds the task(s) with description containing the provided keyword.

Format: `find KEYWORD`

Example of usage:

```
find Go
```

Example outcome:

```
Here are the matching tasks in your list: 
[T][ ] Go School
```

### Deleting a task: `delete`

Deletes the task with the provided index.

Format: `delete INDEX`

Example of usage:

```
delete 1
```

Example outcome:

```
Noted. I've removed this task: 
[T][ ] Go School
Now you have 2 tasks in the list.
```

### Tagging a task: `tag`

Tags the task with the provided index and hashtag.

Format: `tag INDEX #DESCRIPTION`

Example of usage:

```
tag 2 #fun
```

Example outcome:

```
Got it. I've tagged this task: 
[E][ ] Lecture (at: May 5 2022)
with #fun
```


### Listing the tags of a task: `listtag`

Tags the task with the provided index and hashtag.

Format: `listtag INDEX`

Example of usage:

```
listtag 2
```

Example outcome:

```
Here are the tags of this task:
1. #fun
2. #excited
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

Task data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Command summary

| Action           | Format | Example                            |
|------------------| --- |------------------------------------|
| **List**         | `list` | `list`                             |
| **Add ToDo**     | `todo DESCRIPTION` | `todo Go School`                   |
| **Add Deadline** | `deadline DESCRIPTION /by DATE` | `deadline Homework /by 2022-05-05` |
| **Add Event**    | `event DESCRIPTION /at DATE` | `event Lecture /at 2022-05-05`     |
| **Mark**         | `mark INDEX` | `mark 1`                           |
| **Unmark**       | `unmark INDEX` | `unmark 1`                         |
| **Find**         | `find KEYWORD` | `find Go`                          |
| **Delete**       | `delete INDEX` | `delete 1`                         |
| **Tag**          | `tag INDEX #DESCRIPTION` | `tag 2 #fun`                         |
| **List Tags**    | `listtag INDEX` | `listtag 2`                         |
| **Exit**         | `bye` | `bye`                              |
