# User Guide

NyanDuke is a cute desktop app for **managing your upcoming tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). :3

## Features 

### Track your tasks

Have a lot of tasks to keep track of?<br>
Add your tasks to NyanDuke and easily recover the full list of tasks you have left!
You can also mark tasks as complete or delete them at any time.

### Transferrable data file

Finally upgrading to that new computer you've wanted?<br>
NyanDuke saves your task data in a file on your hard disk so you can keep your data even if you move computers!

### Find your tasks

Have too many tasks in the list and you want to find something specific? <br>
NyanDuke can find tasks using keywords or by dates so you won't have to scour through the list yourself!

## Usage

### Notes:

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo sleep`.

* When a date/time is specified as a parameter, it should be input as follows:<br>
  * `DATE` - Date in year-month-day notation, e.g. `2022-10-22`
  * `TIME` - Time in 24hr clock notation, e.g. `1800`

* When a parameter is trailed by `...`, you can call that parameter multiple times, separating each with whitespace.<br>
  e.g. in `delete TASK_NUMBER ...`, `TASK_NUMBER` is a parameter which can be used as `delete 1` or `delete 1 3 5`.

* The parameter `TASK_NUMBER` refers to the number index of the task as given by the `list` command.

### `todo` - Adding a todo task

Add a task that you are planning to do (eventually) to NyanDuke.

Format: `todo DESCRIPTION`

Example of usage: `todo sleep`

Expected outcome:

A Todo-type task (represented by the letter T) will be added to NyanDuke.

```
Okie! Me has added this task:
  [T][ ] sleep
Now you have 1 task in your list.
```

### `deadline` - Adding a deadline task

Add a task that you have to do by a specified deadline to NyanDuke.<br>
The deadline can be (i) a date and time; (ii) a date; or (iii) a written description.

Formats:
1. `deadline DESCRIPTION /by DATE TIME`
1. `deadline DESCRIPTION /by DATE`
1. `deadline DESCRIPTION /by TIME_DESCRIPTION`

Examples of usage:
1. `deadline submit HW1 /by 2022-10-12 2359`
1. `deadline submit HW1 /by 2022-10-13`
1. `deadline submit HW1 /by next week`

Expected outcome:

A Deadline-type task (represented by the letter D) will be added to NyanDuke.

Example for usage 1:

```
Okie! Me has added this task:
  [D][ ] submit HW1 (by: Oct 12 2022, 11.59PM)
Now you have 2 tasks in your list.
```

Example for usage 2:

```
Okie! Me has added this task:
  [D][ ] submit HW1 (by: Oct 13 2022)
Now you have 2 tasks in your list.
```

Example for usage 3:

```
Okie! Me has added this task:
  [D][ ] submit HW1 (by: next week)
Now you have 2 tasks in your list.
```

### `event` - Adding an event task

Add an event task that you have to do at a specified time to NyanDuke.<br>
The time can be (i) a date and time; (ii) a date; or (iii) a written description.

Formats:
1. `event DESCRIPTION /by DATE TIME`
1. `event DESCRIPTION /by DATE`
1. `event DESCRIPTION /by TIME_DESCRIPTION`

Examples of usage:
1. `event party /at 2022-10-12 2000`
1. `event party /at 2022-10-13`
1. `event party /at tonight!!`

Expected outcome:

An Event-type task (represented by the letter E) will be added to NyanDuke.

Example for usage 1:

```
Okie! Me has added this task:
  [E][ ] party (at: Oct 12 2022, 8.00PM)
Now you have 3 tasks in your list.
```

Example for usage 2:

```
Okie! Me has added this task:
  [E][ ] party (at: Oct 13 2022)
Now you have 3 tasks in your list.
```

Example for usage 3:

```
Okie! Me has added this task:
  [E][ ] party (at: tonight!!)
Now you have 3 tasks in your list.
```

### `list` - List your tasks

List all the tasks that you have added to NyanDuke.

Format: `list`

Expected outcome:

A list of all the tasks you have added to NyanDuke will be shown.

```
Here are the tasks in your list:
1. [T][ ] sleep
2. [D][ ] submit HW1 (by: Oct 12 2022, 11.59PM)
3. [E][ ] party (at: Oct 12 2022, 8.00PM)
```

### `mark` - Mark your tasks as complete

Mark specified tasks in NyanDuke as complete.

Format: `mark TASK_NUMBER ...`

Examples of usage:
1. `mark 1`
1. `mark 1 3`

Expected outcome:

The tasks that you have specified by number to NyanDuke will no longer be marked with an `X`.

Example for usage 1:

```
OK, I've marked this task as not done ;-; :
  [T][ ] sleep
Now you have 3 tasks in your list.
```

Example for usage 2:

```
OK, I've marked these tasks as not done ;-; :
  [T][ ] sleep
  [E][ ] party (at: Oct 12 2022, 8.00PM)
Now you have 3 tasks in your list.
```

### `unmark` - Mark your tasks as not complete

Mark specified tasks in NyanDuke as not complete.

Format: `unmark TASK_NUMBER ...`

Examples of usage:
1. `unmark 1`
1. `unmark 1 3`

Expected outcome:

The tasks that you have specified by number to NyanDuke will be marked with an `X`.

Example for usage 1:

```
Yay! Me has marked this task as done:
  [T][X] sleep
Now you have 3 tasks in your list.
```

Example for usage 2:

```
Yay! Me has marked these tasks as done:
  [T][X] sleep
  [E][X] party (at: Oct 12 2022, 8.00PM)
Now you have 1 task in your list.
```

### `delete` - Delete your tasks

Delete specified tasks from NyanDuke.

Format: `delete TASK_NUMBER ...`

Examples of usage:
1. `delete 1`
1. `delete 1 3`

Expected outcome:

The tasks that you have specified by number to NyanDuke will be deleted.

Example for usage 1:

```
Okie! Me has removed this task:
  [T][ ] sleep
Now you have 2 tasks in your list.
```

Example for usage 2:

```
Okie! Me has removed these tasks:
  [T][ ] sleep
  [E][ ] party (at: Oct 12 2022, 8.00PM)
Now you have 1 task in your list.
```

### `find` - Find your tasks using a keyword (or phrase)

Find all the tasks that you have added to NyanDuke containing a specified keyword (or phrase).

Format: `find KEYWORD`

Example of usage: `find sleep`

Expected outcome:

A list of all the tasks you have added to NyanDuke will be shown.

```
Here are the meow-tching tasks in your list:
1. [T][ ] sleep
```

### `on` - Find your tasks using a date

Find all the tasks that you have added to NyanDuke to be done by or at a specified date.

Format: `on DATE`

Example of usage: `on 2022-10-12`

Expected outcome:

A list of all the tasks you have added to NyanDuke will be shown.

```
Here are the tasks in your list on this date:
1. [D][ ] submit HW1 (by: Oct 12 2022, 11.59PM)
2. [E][ ] party (at: Oct 12 2022, 8.00PM)
```

### `bye` - Exit from NyanDuke

Exit the NyanDuke app by bidding NyanDuke goodbye!

Format: `exit`

Expected outcome:

The app will close. Until you open it again!
