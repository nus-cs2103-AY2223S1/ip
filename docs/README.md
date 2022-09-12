# User Guide

## Overview

Jude the chatbot is a task tracker which helps keep track of todos, deadlines and events. It can be used as a reminder tool to remind you of upcoming or overdue deadlines, as well as upcoming events.

## Features

### Three types of tasks

There are three types of tasks which Jude the chatbot supports.
- Todo tasks: Tasks without a deadline or a start time and end time
- Deadline tasks: Tasks with a deadline
- Event tasks: Tasks with a start and end time

### Track completion of tasks

Tasks can be marked, which means to indicate that the task is done. Likewise, tasks can be
unmarked, which means to indicate that the task is not done.

### Remind deadlines and events

Upon startup, Jude the chatbot displays a list which includes the following:
- upcoming or overdue deadlines which are yet marked as done
- upcoming events
to ensure that the user does not miss them. Reminders can be subsequently triggered by using the command `remindme`.

## Command summary:
- `todo DESCRIPTION`: Add todo task
- `deadline DESCRIPTION /by DEADLINE`: Add deadline task
- `event DESCRIPTION /from START_TIME /to END_TIME`: Add event task
- `list`: List all tasks
- `mark INDEX`: Mark task as done
- `unmark INDEX`: Mark task as undone
- `bye`: Exit the program
- `find KEYWORD`: Search tasks by keyword
- `delete INDEX`: Delete task
- `remindme`: Remind user of deadlines and events

## Usage
* remindme - reminds the user of any event about to begin and any deadlines of tasks, which are
*   not marked as complete, coming soon (default: within the next 24 hours)

### `todo` - Add todo task

Adds a todo task with a description provided by the user. The todo task is not marked as done by default.

Format: `todo DESCRIPTION`
- DESCRIPTION is the description provided by the user

Example of usage: `todo CS2103T iP submission`

Expected outcome: A new todo task with the description `CS2103T iP submission` will be added.

Sample output:

```
The following todo task has been added:
  [T][ ] CS2103T iP submission
The task list now contains X task(s).
```

### `deadline` - Add deadline task

Adds a deadline task with a description provided by the user, and a deadline which must be in a [supported date/time format](#supported-date-and-time-formats).

Format: `deadline DESCRIPTION /by DEADLINE`
- DESCRIPTION is the description provided by the user
- DEADLINE is the corresponding deadline of the task

Example of usage: `deadline CS2103T iP submission /by 2022-09-16 23:59`

Expected outcome: A new deadline task with the description `CS2103T iP submission` will be added, with the deadline set as 16 September 2022, 11.59pm.

Sample output:

```
The following deadline task has been added:
  [D][ ] CS2103T iP submission (by: 16 Sep 2022 23:59)
The task list now contains X task(s).
```

### `event` - Add event task

Adds an event task with a description provided by the user, and a start time and an end time which must be in a [supported date/time format](#supported-date-and-time-formats).

Format: `event DESCRIPTION /from START_TIME /to END_TIME`
- DESCRIPTION is the description provided by the user
- START_TIME is the corresponding start time of the event
- END_TIME is the corresponding end time of the event

Example of usage: `event CS2103T Week 6 Lecture /from 2022-09-16 16:00 /to 2022-09-16 17:59`

Expected outcome: A new event task with the description `CS2103T Week 6 Lecture` will be added, with the start time set as 16 September 2022, 4:00pm
and the end time set as 16 September 2022, 5:59pm.

Sample output:

```
The following event task has been added:
  [E][ ] CS2103T Week 6 Lecture (from 16 Sep 2022 16:00 to from 16 Sep 2022 17:59)
The task list now contains X task(s).
```

### `list` - List all tasks

Lists down all tasks which are currently stored in Jude the chatbot.

The index numbers in this command are used for command-line arguments involving index such as `mark`, `unmark` and `delete`.
The index numbers may change only during deletion. In other words, after a series of any operations (except deletion),
the index numbers generated for tasks which exist before the operations will remain the same after the operations.

Format: `list`

Sample output:

```
1. [D][ ] CS2103T iP submission (by: 16 Sep 2022 23:59)
2. [E][X] CS2103T Lecture (from 09 Sep 2022 16:00 to 09 Sep 2022 17:59)
...
```

### `mark` - Mark task as done

Marks a task corresponding to a specified index as done. The index to mark can be found via the `list` command.

Format: `mark INDEX`
- INDEX refers to the index number of the task to mark as done, which can be found in the `list` command

Example of usage: `mark 2`

Expected outcome: The second task (corresponding to index 2 in the output of the `list` command) will be marked as done.

Sample output:
```
The following task has been marked as done:
  [E][X] CS2103T Lecture (from 09 Sep 2022 16:00 to 09 Sep 2022 17:59)
```

### `unmark` - Mark task as undone

Marks a task corresponding to a specified index as undone. The index to mark can be found via the `list` command.

Format: `unmark INDEX`
- INDEX refers to the index number of the task to mark as undone, which can be found in the `list` command

Example of usage: `unmark 2`

Expected outcome: The second task (corresponding to index 2 in the output of the `list` command) will be marked as undone.

Sample output:
```
The following task has been marked as undone:
  [E][ ] CS2103T Lecture (from 09 Sep 2022 16:00 to 09 Sep 2022 17:59)
```

### `find` - Search tasks by keyword

Finds the list of tasks whose descriptions match a user-provided keyword. This command searches by contiguous substring, and is case-insensitive.

Note: The index numbers in `find` are not necessarily the same as that of the `list` command.
Therefore, these index numbers should not be used as reference when marking tasks as done and when deleting tasks.

For example, the following search terms will match `computing`
- `comp`
- `ting`
- `PUT`

The following search terms will not match `computing`
- `comg`
- `abcde`

Format: `find KEYWORD`
- KEYWORD refers to the search keyword

Example of usage: `find cs2103t`

Expected outcome: Lists all the tasks whose description matches `cs2103t` by contiguous case-insensitive substring search.

Sample Output:
```
Here are the matching results in your task list (6 in total):
1.[D][ ] CS2103T iP submission (by: 16 Sep 2022 23:59)
2.[E][ ] CS2103T Lecture (from 09 Sep 2022 16:00 to 09 Sep 2022 17:59)
...
```

### `delete` - Delete task

Delete a task corresponding to a user-provided index. The index to delete can be found via the `list` command.

Format: `delete INDEX`
- INDEX refers to the index number of the task to be deleted, which can be found in the `list` command

Expected outcome: The second task (corresponding to index 2 in the output of the `list` command) will be deleted.

Sample output:
```
The following task has been removed:
  [E][ ] CS2103T Lecture (from 09 Sep 2022 16:00 to 09 Sep 2022 17:59)
The task list now contains X task(s).
```

### `remindme`: Remind user of deadlines and events

Reminds the user of the following
- Deadlines which are not marked as done and are either due within 24 hours or are already overdue
- Events which are yet to start and will start within 24 hours

The reminders will also be displayed when Jude the chatbot starts.

Note: The index numbers in `remindme` are not necessarily the same as that of the `list` command.
Therefore, these index numbers should not be used as reference when marking tasks as done and when deleting tasks.

Format: `remindme`

Sample output:
```
I will remind you of the important tasks you have:
1. [E][ ] CS2103T Week 6 Lecture (from 16 Sep 2022 16:00 to 16 Sep 2022 17:59)
```

### `bye` - Exit the program

Exits Jude the chatbot.

Format: `bye`

## Supported Date and Time Formats

For a date to be recognised by Jude the chatbot, it needs to be in the form:

`DATE [TIME]`

where `DATE` is in one of the supported date formats and `TIME` (optional) is in one of the supported time formats.
If `TIME` is not provided, then it is assumed to be midnight (00:00 or 12.00AM).

The supported date formats are:
- 21 Aug 2022
- Aug 21 2022
- 2022-08-21

The supported time formats are:
- 14:00
- 2:00PM
- 2:00 PM

Note that the format 2PM is not supported.

Date and time inputs are case-insensitive.

In addition, ISO 8601-like dates in the format of 2022-08-21T14:00 are also supported.
