# User Guide

Duke is a desktop app for <u>managing your upcoming tasks</u>, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Features

### Track Tasks

You can track 3 different types of tasks:
1. Todos
1. Deadlines (date and time of deadline)
1. Events (date and time of event)

### Mark Tasks

You can mark and unmark tasks as done.

### Save Tasks to Hard Disk 

Duke saves your tasks in a file on your hard disk so you can continue accessing them even after you change computers

### Find Tasks

You can find tasks using keywords.

### Sort Tasks by Time and Description

Tasks can be sorted chronologically or alphabetically in an ascending or descending order of your choice.

## Usage

### Notes:

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo buy gift`.

  * Parameters in square brackets are optional.
    e.g. `sort [SORT_TYPE] [SORT_ORDER]` can be used as `sort`, `sort time` or `sort time /d`.

* When the parameter is `DATE` or `TIME`, it should be inputted as follows:<br>
    * `DATE` - Date in day-month-year format, e.g. `19-10-2022`
    * `TIME` - Time in 24hr format, e.g. `2359`

* `TASK_NUMBER` refers to the index of the task as given by the `list` command.

* `SORT_TYPE` can be either:
  * `a`, `alphabet`, or `alphabetically` to sort by description alphabetically
  * `t`, `time`, `chronologically`, or any other string to sort by time chronologically.

* `SORT_ORDER` can be either:
  * `/d` to sort in descending order
  * `/a` or any other string to sort in ascending order

### `todo` - Adding a todo task

Add a task that you are planning to do to Duke.

**Format:** `todo DESCRIPTION`

**Example of usage:** `todo buy gift for Ben`

**Expected outcome:**

A Todo task (represented by the symbol T) will be added to Duke.

```
Got it. I've added this task:
  [T][ ] buy gift for Ben
Now you have 1 task in your list.
```

### `deadline` - Adding a deadline task

Add a task that you have to do by a specified deadline to Duke.<br>
The deadline includes both the date and time.

**Format:** `deadline DESCRIPTION /by DATE TIME`

**Example of usage:** `deadline do ip /by 19-10-2022 2359`

**Expected outcome:**

A Deadline task (represented by the symbol D) will be added to Duke.

```
Got it. I've added this task:
  [D][ ] do ip (by: 19 Oct 2022, 11.59PM)
Now you have 2 tasks in your list.
```

### `event` - Adding an event task

Add an event task that you have to do at a specified date and time to Duke.<br>

**Format:** `event DESCRIPTION /at DATE TIME`

**Example of usage:** `event team meeting /at 19-10-2022 2000`

**Expected outcome:**

An Event task (represented by the symbol E) will be added to Duke.

```
Got it. I've added this task:
  [E][ ] team meeting (at: 19 Oct 2022, 8.00PM)
Now you have 3 tasks in your list.
```

### `list` - List your tasks

List all tasks that you have added to Duke.

**Format:** `list`

**Expected outcome:**

A list of all tasks you have added to Duke will be displayed.

```
Here are the tasks in your list:
1. [T][ ] buy gift for Ben
2. [D][ ] do ip (by: 19 Oct 2022, 11.59PM)
3. [E][ ] team meeting (at: 19 Oct 2022, 8.00PM)
```

### `mark` - Mark your tasks as done

Mark a specified task in Duke as done.

**Format:** `mark TASK_NUMBER`

**Example of usage:** `mark 1`

**Expected outcome:**

The task that you have marked as done will be marked with an `X`.

```
Nice! I've marked this task as done:
  [T][X] buy gift for Ben
```

### `unmark` - Mark your tasks as not done

Mark a specified task in Duke as not done.

**Format:** `unmark TASK_NUMBER`

**Example of usage:** `unmark 1`

**Expected outcome:**

The task that you have marked as not done will no longer be marked with an `X`.

```
OK, I've marked this task as not done yet:
  [T][ ] buy gift for Ben
```

### `delete` - Delete your tasks

Delete a specified task from Duke.

**Format:** `delete TASK_NUMBER`

**Example of usage:** `delete 1`

**Expected outcome:**

The task that you have specified by number to Duke will be deleted.

```
Noted. I've removed this task:
  [T][ ] buy gift for Ben
```

### `find` - Find your tasks using a keyword

Find all the tasks that you have added to Duke containing a specified keyword.

**Format:** `find KEYWORD`

**Example of usage:** `find do`

**Expected outcome:**

A list of all the tasks containing the specified keyword will be displayed.

```
Here are the matching tasks in your list:
1. [D][ ] do ip (by: 19 Oct 2022, 11.59PM)
```

### `sort` - Sort your tasks by time or description

Sort you tasks chronologically or alphabetically in an ascending or descending order of your choice.
Default sort type is by time and in ascending order.

**Format:** `sort [SORT_TYPE] [SORT_ORDER]`

**Example of usage:** `sort a /d`

**Expected outcome:**

A list of the sorted tasks will be displayed.

```
Here is the alphabetically sorted list of tasks in descending order:
1. [E][ ] team meeting (at: 19 Oct 2022, 8.00PM)
2. [D][ ] do ip (by: 19 Oct 2022, 11.59PM)
```

### `bye` - Exit from Duke

Exits Duke and initiates a 3-second countdown timer in the response box.

**Format:** `bye`

**Expected outcome:**

```
Bye. Hope to see you again soon! Closing in 3
```

After 3 seconds Duke will close.
