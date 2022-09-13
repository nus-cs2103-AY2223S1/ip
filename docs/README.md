# SocDuke User Guide

## Features 

### Feature- Adding Task

You are able to add three types of tasks, which are, Todo, Deadline and Event.
- Todo is a task that does not have any dates associated with it. It only has a description.
- Deadline is a task that has a by date associated with it. It also includes a description.
- Event is a task that has a at date associated with it. It also includes a description.

### Feature- Marking Tasks

You are able to mark your task as completed or not completed. This allows you to manage your tasks more effectively.
- [X] This is a completed todo
- [ ] This is an uncompleted todo

### Feature- Edit Tasks date

You are able to edit the dates associated with the deadline and event. This allows users to edit dates of tasks as such dates may frequently change.

### Feature- Search Tasks

Lazy to find your task. Fret not, we have implemented a search feature that allows you to search for task by their description. As long as the description contains the substring that you have searched it would appear on the screen.

### Feature- Deleting Task

You are able to delete tasks through a command. This allows you to get rid of obsolete tasks in your list.

## Usage

### `todo` - Create a Todo

Create Todo by simiply providing a description. SocDuke will then add it into your list.

Format: `todo {DESCRIPTION}`

Example of usage: 
`todo revise for CS2100 Midterms`

Expected Outcome:
```
Got it nya. I've added this task:
[T][] revise for CS2100 Midterms
Now you have 1 tasks in the list
```

### `deadline` - Create a Deadline

Create deadline by simply providing a description and date. The date **MUST** be in YY-MM-DD format.

Format: `deadline {DESCRIPTION} /by {YY-MM-DD}`

Example of usage: 
`deadline CS2100 Tutorial /by 2022-09-09`

Expected Outcome:
```
Got it nya. I've added this task:
[D][] CS2100 Tutorial (by: Sep 9 2022)
Now you have 2 tasks in the list
```

### `event` - Create an Event

Create event by simply providing a description and date. The date **MUST** be in YY-MM-DD format.

Format: `deadline {DESCRIPTION} /at {YYYY-MM-DD}`

Example of usage: 
`event Summer CS Intership Interview /by 2022-09-10`

Expected Outcome:
```
Got it nya. I've added this task:
[E][] Summer CS Internship Interview (by: Sep 10 2022)
Now you have 3 tasks in the list
```

### `list` - Show the current list of tasks

Prints out the current list of task that are registered in SocDuke. This will also print out the task number of all the tasks.

Format: `list`

Example of usage: 
`list`

Expected Outcome:
```
Here are the tasks in your list nya:
1. [T][] revise for CS2100 Midterms
2. [D][] CS2100 Tutorial (by: Sep 9 2022)
3. [E][] Summer CS Internship Interview (by: Sep 10 2022)
```

### `mark` - Mark a task as done

Mark a existing task as completed. The task number on the list must be provided.

Format: `mark {TASK_NUMBER}`

Example of usage: 
`mark 1`

Expected outcome:
```
Nice nya! I've marked this task as done:
[T][X] revise for CS2100 Midterms
```

### `unmark` - unMark a task as done

Mark a existing task as incomplete. The task number on the list must be provided.

Format: `unmark {TASK_NUMBER}`

Example of usage: 
`unmark 1`

Expected outcome:
```
Ok nya, I've marked this task as not done yet nya:
[T][] revise for CS2100 Midterms
```

### `update` - Update a Deadline/Event date

Replace the date associated to Deadline/Event with a new date. The task number on the list must be provided. The new date **MUST** be in YY-MM-DD format.

Format: `update {TASK_NUMBER} {YYYY_MM_DD}`

Example of usage: 
`update 3 2022-09-11`

Expected outcome:
```
Noted nya! I've updated this task's date:
[E][] Summer CS Internship Interview (by: Sep 11 2022)
```

### `find` - Find a Task based on description

Search for task based on description. As long as your search string is a substring of the task description, it will show up in the search result.

Format: `find {TASK_DESCRIPTION}`

Example of usage: 
`find cs`

Note that this feature is not case-sensitive. Thus, searching for 'cs' will also bring up results containing 'CS'.

Expected outcome:
```
Here are the matching tasks in your list nya:
1. [D][] CS2100 Tutorial (by: Sep 9 2022)
2. [E][] Summer CS Internship Interview (by: Sep 11 2022)
```

### `delete` - Delete a Task

Delete an existing task. The task number associated to the task must be provided.

Format: `delete {TASK_NUMBER}`

Example of usage: 
`delete 1`

Expected outcome:
```
Ok nya. I've removed this task:
[T][] revise for CS2100 Midterms
Now you have 2 tasks in the list
```
### `bye` - Exit program

Exit the SocDuke program.

Format: `bye`

Example of usage:
`bye`

Expected outcome:
The SocDuke Program will be closed. Same effect as pressing the "X" button on GUI.