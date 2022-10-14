# User Guide

## Get Start

1. Download the JAR file from the GitHub release

2. Run the app by either (1) clicking to run the JAR file, or (2) running the Duke.java [CLI version] in IntelliJ

## Features 
_p.s   All the command keywords are case-insensitive. <br>_
_For example, `DeAdliNe` will match `deadline`_

### `todo` - to add a ToDo task

Add a **ToDo** task to your task list.
The command should be in the format of `todo [task name]`

Example of usage:

`todo Random task`

Expected outcome:

A message shows that the task is added will be shown.

```
---------------------------------------------------------
Got it. I've added this task:
[T][ ] Random task
Now you have 1 tasks in the list.
---------------------------------------------------------
```

### `deadline` - to add a Deadline task

Add a **Deadline** task to your task list.
The command should be in the format of `deadline [task name] /by yyyy-MM-dd`

Example of usage:

`deadline CS2103 IP /by 2022-10-17`

Expected outcome:

A message shows that the task is added will be shown.

```
---------------------------------------------------------
Got it. I've added this task:
[D][ ] CS2103 IP (by: Oct 17 2022)
Now you have 1 tasks in the list.
---------------------------------------------------------
```

### `event` - to add an Event task

Add an **Event** task to your task list.
The command should be in the format of `event [task name] /at yyyy-MM-dd hh:mm`

Example of usage:

`event Career Fair /at 2022-10-17 17:30`

Expected outcome:

A message shows that the task is added will be shown.

```
---------------------------------------------------------
Got it. I've added this task:
[E][ ] Career Fair (at: Oct 17 2022 17:30)
Now you have 1 tasks in the list.
---------------------------------------------------------
```

### `mark` - to mark done for the task with given index

Mark done a task specified by an index.
Note that the mark command is only valid when the task is not marked done before.
The command should be in the format of `mark [index]`

Example of usage:

`mark 3`

Expected outcome:

A message shows that the task is marked done. (Or an error message if the index is not given properly, or no index given
, or the task is already done)
```
---------------------------------------------------------
Nice! I've marked this task as done:
[E][X] project meeting (at: Aug 6 2022 14:00)
---------------------------------------------------------
```

### `unmark` - to unmark done for the task with given index

Unmark done a task specified by an index.
Note that the unmark command is only valid when the task is marked done before.
The command should be in the format of `unmark [index]`

Example of usage:

`unmark 3`

Expected outcome:

A message shows that the task is unmarked. (Or an error message if the index is not given properly, or no index given
, or the task is done already)
```
---------------------------------------------------------
OK, I've marked this task as not done yet:
[E][ ] project meeting (at: Aug 6 2022 14:00)
---------------------------------------------------------
```

### `find` - to find the corresponding task

For the list of keywords given, the task manager will search for you any tasks that contain one of the keywords.
The command should be in the format of `find String1 [String 2 String 3 ...]`

Example of usage:

For the list of tasks below in particular, if we run `find book`
```
---------------------------------------------------------
1.[T][X] borrow book
2.[D][X] return book (by: Jun 6 2022)
3.[E][ ] project meeting (at: Aug 6 2022 14:00)
4.[T][X] join sports club
5.[T][X] HSBC ot
---------------------------------------------------------
```

Expected outcome:

A list of tasks that satisfy the requirement.
```
---------------------------------------------------------
Here are the matching tasks in your list:
1.[T][X] borrow book
2.[D][X] return book (by: Jun 6 2022)
---------------------------------------------------------
```

### `delete` - to delete the task with given index

Delete the task specified by the user.
The command should be in the format of `delete [index]`

Example of usage:

`delete 1`

Expected outcome:

A message shows that the task is deleted. (Or an error message if the index is not given properly, or no index given)
```
---------------------------------------------------------
Noted. I've removed this task:
[T][X] borrow book
Now you have 4 tasks in the list.
---------------------------------------------------------
```

### `undo` - to undo last command

Undo the previous action (including adding a task, marking/unmarking a task, deleting a task).
Note that undo cannot be undone.
The command should be in the format of `undo`

Example of usage:

`undo`

Expected outcome:

A message shows that the previous action is undone. (Or an error message shows that no previous action)
```
---------------------------------------------------------
Last command has been undone. Yay!
---------------------------------------------------------
```
If no previous actions:
```
---------------------------------------------------------
Opps:( Nothing has been done, cos there is no history.
---------------------------------------------------------
```

### `list` - to list all ongoing tasks

List all the opening tasks.
The command should be in the format of `list`

Example of usage:

`list`

Expected outcome:

The task list stored in the system.
```
---------------------------------------------------------
1.[T][X] borrow book
2.[D][ ] return book (by: Jun 6 2022)
3.[E][ ] project meeting (at: Aug 6 2022 14:00)
4.[T][X] join sports club
5.[T][X] HSBC ot
---------------------------------------------------------```
```

### `bye` - to exit Duke and save task list to local

The command should be in the format of `bye`

Example of usage:

`bye`

Expected outcome:

```
---------------------------------------------------------
Bye. Hope to see you again soon!
---------------------------------------------------------
```