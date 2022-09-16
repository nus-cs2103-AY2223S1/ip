# Scruffles User Guide

Scruffles is a desktop app for tracking tasks, optimized for use via a Command Line Interface (CLI) while still having 
the benefits of a Graphical User Interface (GUI). If you can type fast, Scruffles can save, delete and mark your tasks
 as done faster than traditional GUI apps.

## Features 

### Task Tracking

Enables you to save various different types of tasks and events that you may have. This version currently allows you to
 save 4 types of tasks, namely:
- `todo`: A simple task that needs to be done
- `deadline`: A task that has a specified date as a deadline
- `event`: An event that occurs on a specified date, with a specified start and end time
- `dowithinperiod`: A task that has to be done within a specified period of time

### Task Marking

Enables you to `mark` tasks as done once you have completed said task

### Saving Data

Allows all changes made to the list of tasks entered to be saved in the hard disk via the command `bye`

## Usage

### `todo` - Saves a Todo task

Saves a task that has no date or time associated to it into the program's list of tasks.

Example of usage: 

`todo cook dinner`

Expected outcome:

The Todo task 'cook dinner' is saved into the list of tasks.

```
woof! the task is added woof!
[T][ ] cook dinner
you now have 1 tasks in the list woof!
```
### `deadline` - Saves a Deadline task

Saves a task that has a deadline of a specific date into the program's list of tasks. The codeword ` /by ` is used to 
separate the task's name from the date. Dates **must** be entered in the _**yyyy-mm-dd**_ format.

Example of usage:

`deadline math homework /by 2022-10-15`

Expected outcome:

The Deadline task 'math homework' and its associated deadline is saved into the list of tasks.

```
woof! the task is added woof!
[D][ ] math homework (by: 15 OCTOBER 2022)
you now have 2 tasks in the list woof!
```
### `event` - Saves an Event task

Saves an event that occurs on a specific date during a specfic start and end time into the program's list of tasks. The
codeword ` /at `, ` from ` and ` to ` are used to separate the task's name from the date and time. Dates **must** be
entered in the _**yyyy-mm-dd**_ format while time must be entered in the **_hh:mm_** format.

Example of usage:

`event cool hackathon /at 2022-12-03 from 10:00 to 17:00`

Expected outcome:

The Event task 'cool hackathon' and its associated timings are saved into the list of tasks.

```
woof! the task is added woof!
[E][ ] cool hackathon (at: 3 DECEMBER 2022 10:00 to 17:00)
you now have 3 tasks in the list woof!
```
### `dowithinperiod` - Saves an DoWithinPeriod task

Saves a task that has to be done between 2 specific dates into the program's list of tasks. The
codeword ` /btw ` and ` and ` are used to separate the task's name from the dates. Dates **must**
be entered in the _**yyyy-mm-dd**_ format.

Example of usage:

`dowithinperiod module survey /btw 2022-11-06 and 2022-11-13`

Expected outcome:

The DoWithinPeriod task 'module survey' and its associated dates are saved into the list of tasks.

```
woof! the task is added woof!
[P][ ] module survey (between: 6 NOVEMBER 2022 and 13 NOVEMBER 2022)
you now have 4 tasks in the list woof!
```
### `list` - Lists out all tasks saved

Lists out all the tasks that you have just saved or have saved in your prior usages of Scruffles.

Example of usage:

`list`

Expected outcome:

```
1.[T][ ] cook dinner
2.[D][ ] math homework (by: 15 OCTOBER 2022)
3.[E][ ] cool hackathon (at: 3 DECEMBER 2022 10:00 to 17:00)
4.[P][ ] module survey (between: 6 NOVEMBER 2022 and 13 NOVEMBER 2022)
```
### `delete` - Deletes tasks

Deletes a specified task from the list of tasks by keying in its respective task number from `list`. You can also
delete all tasks in the list by entering the command `delete all`.

Example of usage:

`delete 2`

Expected outcome:

The second task in the list would be deleted from the list of tasks.

```
woof! the task is now deleted woof!
[D][ ] math homework (by: 15 OCTOBER 2022)
you now have 3 tasks in the list woof!
```
### `mark` - Marks tasks as done

Marks a specified task from the list of tasks as done by keying in its respective task number from `list`.

Example of usage:

`mark 1`

Expected outcome:

The first task in the list would be marked as done.

```
woof! the task is now marked as done woof!
[T][X] cook dinner
```
### `find` - Finds tasks with specific keywords

Finds tasks in the `list` that contain matching keywords to the input.

Example of usage:

`find coo`

Expected outcome:

Finds and shows all tasks in the list that contain the keyword `coo`.

```
woof here are the tasks i found that have this keyword woof:
[T][X] cook dinner
[E][ ] cool hackathon (at: 3 DECEMBER 2022 10:00 to 17:00)
```
### `bye` - Saves the list of tasks into the hard drive

Saves the latest list of tasks into the hard drive when this command is called.

Example of usage:

`bye`

Expected outcome:

All data has been saved.

```
woof see you again woof!
```