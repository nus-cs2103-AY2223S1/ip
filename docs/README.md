# User Guide

## Features 

### Adding to-do task: `todo`
Adds to-do task to the task list<br>

Format: `todo TASK_DESCRIPTION`<br>

Examples:
- `todo CS2103T iP`
- `todo CS2100 Assignment 1`
<br><br><br>
### Adding event task: `event`
Adds event task to the task list with specified time<br>

Format: `event TASK_DESCRIPTION /at EVENT_TIME`

- `EVENT_TIME` must be written in YYYY-MM-DD format

Examples:
- `event CS2103T Finals /at 2022-11-25`
- `event CS2100 Finals /at 2022-11-26`
  <br><br><br>
### Adding deadline task: `deadline`
Adds deadline task to the task list with specified time of deadline<br>

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

- `DEADLINE` must be written in YYYY-MM-DD format

Examples:
- `deadline CS2103T iP Submission /by 2022-09-17`
- `deadline CS2100 Assignment 1 /by 2022-10-16`
<br><br><br>
### List all tasks: `list`
List all tasks on the task list<br>

Format: `list`
  <br><br><br>
### Delete task: `delete`
Deletes specified task from task list<br>

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`
- The index refers to the index shown in the displayed task list
- The index must be a positive integer less than or equal to the length of the task list

Examples:
- `delete 1` deletes the 1st task in the task list
  <br><br><br>
### Marking a task: `mark`
Marks a task as done <br>

Format: `mark INDEX`<br>

- Marks the task at the specified `INDEX`
- The index refers to the index shown in the displayed task list
- The index must be a positive integer less than or equal to the length of the task list
- If specified task was previously marked, nothing changes


Examples:
- `mark 1` marks the first task in the task list as done
  <br><br><br>
### Unmarking a task: `unmark`
Marks a task as done <br>

Format: `unmark INDEX`<br>

- Unmarks the task at the specified `INDEX`
- The index refers to the index shown in the displayed task list
- The index must be a positive integer less than or equal to the length of the task list
- If specified task was previously unmarked, nothing changes


Examples:
- `unmark 1` unmarks the first task in the task list
  <br><br><br>
### Tagging a task: `tag`
Tags a task <br>

Format: `tag INDEX TAG_NAME [MORE_TAG_NAMES]`<br>

- Tags the task at the specified `INDEX`
- The index refers to the index shown in the displayed task list
- The index must be a positive integer less than or equal to the length of the task list
- Tags must be one word only


Examples:
- `tag 1 important` tags the first task in the task list as `#important`
- `tag 2 fun noHurry` tags the second task in the task list as `#fun` and `#noHurry`
  <br><br><br>
### Locating tasks by keyword: `find`
Finds tasks that contain the given keyword <br>

Format: `find KEYWORD`<br>

- The search is case-sensitive
- The tasks are searched based on how they are shown in `list`. Task descriptions, tags and any dates associated 
with the task are searched

Examples:
- `find Sep` finds all deadlines due or events occurring in September
- `find #important` finds all tasks tagged as `#important`
<br><br><br>
### End the program: `bye`
Ends the program <br>

Format: `bye`<br><br><br>
### Saving the data
Task list data are saved in the hard disk after the `bye` command and the program ends
