Duke User Guide
===

- [Duke User Guide](#duke-user-guide)
  - [About](#about)
    - [Features](#features)
  - [Usage](#usage)
    - [`list` - List all tasks](#list---list-all-tasks)
    - [`todo` - Add a to-do](#todo---add-a-to-do)
    - [`deadline` - Add a task with a deadline](#deadline---add-a-task-with-a-deadline)
    - [`event` - Add an event with timespan](#event---add-an-event-with-timespan)
    - [`mark` - Mark a task complete](#mark---mark-a-task-complete)
    - [`unmark` - Mark a task incomplete](#unmark---mark-a-task-incomplete)
    - [`find` - Search for tasks](#find---search-for-tasks)
    - [`delete` - Delete a task](#delete---delete-a-task)
    - [`undo` - Revert to previous state](#undo---revert-to-previous-state)
    - [`bye` - Save tasks and exit](#bye---save-tasks-and-exit)

---
## About

Duke is your personal task manager! Manage all your tasks, events, and deadlines with Duke!

### Features
1. Mark and unmark tasks
   1. todo's
   2. events
   3. deadlines
2. Delete tasks once you're done with them (for good)
3. Undo if you've made a mistake
4. Task data is saved *locally*, don't worry about people stealing your data!
5. Find tasks matching a keywords

---
## Usage

### `list` - List all tasks

Duke will show all tasks in your task list.

Example of usage: 

`list`

Expected outcome: A list of tasks, in the order they were added.

Output:
```
ALL TASKS:
1.  [T][] Take a break
2.  [E][x] Tutorial COM1 (At: Tuesday 8am - 10am)
```

### `todo` - Add a to-do

Add a to-do task to your task list.

Example of usage: 

`todo Clean my room`

Listing all tasks:
```
ALL TASKS:
1.  [T][] Take a break
2.  [E][x] Tutorial COM1 (At: Tuesday 8am - 10am)
3.  [T][] Clean my room
```

### `deadline` - Add a task with a deadline

Add a task and its deadline to your task list.

Format: `deadline {description} /by {d/m/YYYY HHmm}

Example of usage: 

`deadline Tutorial 3 /by 14/7/2022 2359`

Listing all tasks:
```
ALL TASKS:
1.  [T][] Take a break
2.  [E][x] Tutorial COM1 (At: Tuesday 8am - 10am)
3.  [T][] Clean my room
4.  [D][] Tutorial 3 (Deadline: 14 July, 23:59)
```

### `event` - Add an event with timespan

Add an event and its timespan to your task list.

Format: `event {description} /at {string}`

Example of usage: 

`event Family meeting /at Tuesday 4-5pm`

Listing all tasks:
```
ALL TASKS:
1.  [T][] Take a break
2.  [E][x] Tutorial COM1 (At: Tuesday 8am - 10am)
3.  [T][] Clean my room
4.  [D][] Tutorial 3 (Deadline: 14 July, 23:59)
5.  [E][] Family meeting (At: Tuesday 4-5pm)
```

### `mark` - Mark a task complete

You can mark a task as complete once it is done.

Format: `mark {task_index}`

Example of usage: 

`mark 3`

Listing all tasks:
```
ALL TASKS:
1.  [T][] Take a break
2.  [E][x] Tutorial COM1 (At: Tuesday 8am - 10am)
3.  [T][x] Clean my room
4.  [D][] Tutorial 3 (Deadline: 14 July, 23:59)
5.  [E][] Family meeting (At: Tuesday 4-5pm)
```

### `unmark` - Mark a task incomplete

You can mark a task as incomplete too.

Format: `unmark {task_index}`

Example of usage: 

`unmark 2`

Listing all tasks:
```
ALL TASKS:
1.  [T][] Take a break
2.  [E][] Tutorial COM1 (At: Tuesday 8am - 10am)
3.  [T][x] Clean my room
4.  [D][] Tutorial 3 (Deadline: 14 July, 23:59)
5.  [E][] Family meeting (At: Tuesday 4-5pm)
```

### `find` - Search for tasks

Find tasks matching your specified keyword.

Format: `find {keyword}`

Example of usage: 

`find Tutorial`

Listing all tasks:
```
ALL TASKS:
Tasks matching search keyword "Tutorial":
1.  [E][] Tutorial COM1 (At: Tuesday 8am - 10am)
2.  [D][] Tutorial 3 (Deadline: 14 July, 23:59)
```

### `delete` - Delete a task

Delete a task once its done.

Format: `delete {task_index}`

Example of usage: 

`delete 3`

Listing all tasks:
```
ALL TASKS:
1.  [T][] Take a break
2.  [E][] Tutorial COM1 (At: Tuesday 8am - 10am)
3.  [D][] Tutorial 3 (Deadline: 14 July, 23:59)
4.  [E][] Family meeting (At: Tuesday 4-5pm)
```

### `undo` - Revert to previous state

Undo an edit to the task list.

Example of usage: 

`undo`

Listing all tasks:
```
ALL TASKS:
1.  [T][] Take a break
2.  [E][] Tutorial COM1 (At: Tuesday 8am - 10am)
3.  [T][x] Clean my room
4.  [D][] Tutorial 3 (Deadline: 14 July, 23:59)
5.  [E][] Family meeting (At: Tuesday 4-5pm)
```

### `bye` - Save tasks and exit

Saves tasks to the hard disk and exits the app.

Example of usage: 

`bye`
