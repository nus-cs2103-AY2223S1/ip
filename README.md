# User Guide
Ploopy is a personal chatbot who can manage your tasks so you don't have to worry about
constantly keeping track of them!

![image](https://user-images.githubusercontent.com/97420952/189854100-433189ce-8b31-40df-a333-1e578ebb96ec.png)

Some example commands you can try:

`list` : Lists all tasks.

`todo clean room `: Adds a ToDo task called clean room.

## Features
- [x] Add a ToDo task.
- [x] Add a Deadline task.
- [x] Add an Event task.
- [x] List all tasks.
- [x] Mark/ unmark a task as done/ undone.
- [x] Delete a task.
- [x] Find a task by name.
- [x] Set the priority of a task.

# Usage

## `todo` - Adds a ToDo task
Adds a ToDo task with the provided name.

Format: `todo <task name>`

Example of usage: 
`todo clean room`

Expected Outcome: 
Adds a ToDo task called "clean room" to the list of tasks.

```
I've added this task to your list. Here you go: [T][] clean room You have X tasks in you
```

## `deadline` - Adds a Deadline task
Adds a Deadline task with the provided name and deadline

Format: `deadline <task name> /by <task deadline>`

Notes:

- name and deadline must be seperated by `/`
- deadline **must** be in the `dd/mm/yyyy hhmm` format
- time is **required** for deadline and must be in 24-hour format
- if time is single digits then 0 must be inserted. e.g. `630` must be `0630`.

Example of usage:
`deadline assignment 1 /by 15/09/2022 1800`

Expected Outcome: 
Adds a Deadline tasks called "assignment 1" which has the date: 15/09/2022 and time: 1800.

```
I've added this task to your list. Here you go: [D][] assignment 1 (by: Sep 15 2022 1800) You have x tasks in your list
```

##  `event` - Adds an Event task
Adds an Event task with the provided name and date

Format: `event <task name> /at <task date>`

Notes:
- name and date must be seperated by `/`
- date **must** be in the `dd/mm/yyyy hhmm` format
- time is **required** for date and must be in 24-hour format
- if time is single digits then 0 must be inserted. e.g. `630` must be `0630`.

Example of usage:
`event graduation /by 15/04/2025 1200`

Expected Outcome:
Adds an event task called "graduation" with the date: 15/04/2025 and time: 1200

```
I've added this task to your list. Here you go: [E][] graduation (at: Apr 15 2025 1200) You have x tasks in your list
```

## `list` - Lists all tasks 
Displays all tasks

Format: `list`

Example of usage:
`list`

Expected Outcome:

```
1.[T][ ] homework Priority: high
2.[D][ ] assignment 1 (by: Aug 12 2022 1800)
3.[T][ ] clean room
4.[E][ ] karoake (at: Sep 16 2022 2100)
```

## `mark` - Marks a task
Marks the specified task as done.

Format: `mark <task number>`


Notes:
- number is according to `list`

Example of usage:
`mark 2`

Expected Outcome:

Marks task number 2 as done.

```
Nice! You've completed this task. I'll mark it as done.
[D][X] assignment 1 (by: Aug 12 2022 1800)
```

##  `unmark` - Unmarks a task
Marks the specified task as done.

Format: `unmark <task number>`

Notes:
- number is according to `list`

Example of usage:
`unmark 2`

Exapected Outcome:
Marks task number 2 as undone.

```
Alright this task has been marked as undone.
[D][ ] assignment 1 (by: Aug 12 2022 1800)
```

## `delete` - Deletes a task
Deletes the specified task.

Format: `delete <task number>`

Notes:
- number is according to `list`

Example of usage:
`delete 4`

Expected Outcome:
Deletes task number 4

```
Deleted [E][ ] karoake (at: Sep 16 2022 2100)
You have 3 task(s) remaining.
```

##  `find` - Finds matching tasks

Finds tasks matching the given description.

Format: `find <description>`

Example of usage:
`find assignment`

Expected Outcome:

Displays all tasks whose name has "assignment" in it.

```
1.[D][ ] assignment 1 (by: Aug 12 2022 1800)
2.[D][ ] assignment 2 (by: Oct 13 2022 1800)
```

##  `priority` - Set task priority
Sets the specified task's priority to high or none.

Format: `priority <task number> <priority to set to>

Notes:
- use "high" for high priority and "none" to remove priority
- only high and no priority is supported


Example of usage and outcome

`priority 2 high`

```
Changed this task's priority: [T][ ] homework Priority: high
```

`priority 2 none`

```
Changed this task's priority: [T][ ] homework
```

## `bye` - Exits the program
Exits the program

Format: `bye`

Expected Outcome:

```
Okay then, see ya later :)
```
