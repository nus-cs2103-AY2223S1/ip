# User Guide

## Features

<table>
  <tr>
    <th>Feature</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>[Todo](#adding-a-todo-todo)</td>
    <td>Creates a new To-do Task</td>
  </tr>
  <tr>
    <td>[Event](#creating-an-event-event)</td>
    <td>Creates a new event that occurs </br>at a specific time</td>
  </tr>
  <tr>
    <td>[Deadline](#creating-a-deadline-deadline)</td>
    <td>Creates a new deadline that must be </br>completed by a specific time</td>
  </tr>
  <tr>
    <td>[List](#list-all-tasks-list)</td>
    <td>List out current tasks in Duke</td>
  </tr>
  <tr>
    <td>[Mark](#mark-a-task-as-complete-mark)</td>
    <td>Mark a task as completed</td>
  </tr>
  <tr>
    <td>[Unmark](#mark-a-task-as-incomplete-unmark)</td>
    <td>Mark a task as incomplete</td>
  </tr>
  <tr>
    <td>[Delete](#delete-a-task-delete)</td>
    <td>Delete a specified task from Duke</td>
  </tr>
  <tr>
    <td>[Find](#finds-a-specific-task-find)</td>
    <td>Searches for tasks by their description</td>
  </tr>
  <tr>
    <td>[Help](#produce-help-message-help)</td>
    <td>Produces a help message</td>
  </tr>
</table>

## Usage

### Creating a todo: `todo`

Creates a new To-do Task.

Format: `todo [DESCRIPTION]`

Example of usage: `todo Study for midterms`

Expected Outcome:

```
Got it. I added this task:
    [T][ ] Study for midterms
Now you have 2 tasks in the list.
```

### Creating an event: `event`

Creates a new event that occurs at a specific time.
Time must be indicated in `YYYY-MM-DD HHMM` format.

Format: `event [DESCRIPTION] /at [DATETIME]`

Example of usage: `event CS2103T Finals /at 2022-11-03 1100`

Expected Outcome:

```
Got it. I added this task:
    [E][ ] CS2103T Finals (at: Nov 3 2022 1100H)
Now you have 3 tasks in the list.
```

### Creating a deadline: `deadline`

Creates a new deadline that must be completed by a specific time.
Time must be indicated in `YYYY-MM-DD HHMM` format.

Format: `deadline [DESCRIPTION] /by [DATETIME]`

Example of usage: `deadline CS2103T iP /by 2022-09-16 2359`

Expected Outcome:

```
Got it. I added this task:
    [D][ ] CS2103T ip (by: Sep 16 2022 2359H)
Now you have 4 tasks in the list.
```

### List all tasks: `list`

Lists all current tasks.

Format: `list`

Expected Outcome:

```
You currently have 2 tasks:
1. [T][X] Study for midterms
2. [T][ ] CS2103T iP
```

### Mark a task as complete: `mark`

Marks a specified task as complete.
The task numbers of tasks can be retrieved by executing the `list` command.

Format: `mark [TASK NUMBER]`

Example of usage: `mark 3`

Expected Outcome:

```
Nice! I've marked this task as done:
    [T][X] CS2103T iP 
```

### Mark a task as incomplete: `unmark`

Marks a specified task as incomplete.
The task numbers of tasks can be retrieved by executing the `list` command.

Format: `mark [TASK NUMBER]`

Example of usage: `unmark 3`

Expected Outcome:

```
Ok, I've marked this task as not done yet:
    [T][ ] CS2103T iP 
```

### Delete a task: `delete`

Deletes a specified task.
The task numbers of tasks can be retrieved by executing the `list` command.

Format: `delete [TASK NUMBER]`

Example of usage: `delete 3`

Expected Outcome:

```
Noted. I've removed this task:
    [T][X] CS2103T iP 
```

### Finds a specific task: `find`

Looks for all tasks that match your search term. Does a search on task description only. 
Search terms are case-sensitive; and Duke searches by matching whole words.

Format: `find [SEARCH TERM]`

Example of usage: `find CS2103T`

Expected Outcome:

```
Here are the matching tasks in your list:
1. [T][X] CS2103T iP 
```

### Produce help message: `help`

Links users to the online user guide.

Format: `help`

Expected Outcome:

```
Welcome to Duke! Duke is a CLI application 
designed to help you with tracking your tasks.
To view our user guide, please proceed to this link 
here: https://charleslimjh.github.io/ip/
```