# User Guide

## About
Duke is a desktop app designed for task management, optimized for use via
a Command-Line interface (CLI) while still having the benefits of a 
Graphical-User Interface (GUI). If you can type fast, Duke is an efficient
program to manage all your tasks.

## Features 
- todo
- deadline
- event
- list
- mark
- unmark
- delete
- find
- bye

## Adding Tasks

There are 3 types of tasks that can be added to Duke

1. Todo
2. Deadline
3. Event

### `todo` - Adds a Todo task to Duke list
Command format: `todo TODO_ITEM`

This adds a todo task to Duke list, along with a short note corresponding to `TODO_ITEM`.

#### Example of Usage:

Input:
```
todo sweep the floor
```

Output: 
```
Got it. I've added this task:
[T][ ] sweep the floor
Now you have 2 tasks in the list.
```
### `deadline` - Adds a Deadline task to Duke list
Command format: `deadline DEADLINE_ITEM /by DATE TIME`

This adds a deadline task to Duke list, that is to be done by `DATE` and at time `TIME`. 
Note that `DATE` is of the format `YYYY-MM-DD` and `TIME` is of the format `hh:mm:ss`.

#### Example of Usage:

Input:
```
deadline finish ip /by 2022-09-19 23:59:59
```

Output:
```
Got it. I've added this task:
[D][ ] finish ip (by: Sep 19 2022 23:59:59)
Now you have 2 tasks in the list.
```

### `event` - Adds an Event task to Duke list
Command format: `event EVENT_ITEM /at DATE START_TIME END_TIME`

This adds an event task to Duke list, that occurs on `DATE` from `START_TIME` to `END_TIME`.

Note that `DATE` is of the format `YYYY-MM-DD` and both `START_TIME` and `END_TIME` are of the format `hh:mm:ss`.

#### Example of Usage:

Input:
```
event cs2100 midterm /at 2022-10-08 09:00:00 10:30:00
```

Output:
```
Got it. I've added this task:
[E][ ] finish ip (at: Oct 8 2022 09:00:00 - 10:30:00)
Now you have 2 tasks in the list.
```

## Managing Tasks
### `list` - List all tasks in Duke list
Command format: `list`

Returns a list of all tasks currently in Duke list.

#### Example of Usage:

Input:
```
list
```

Output:
```
Here are the tasks in your list:
1. [T][ ] sweep the floor
2. [D][ ] finish ip (by: Sep 19 2022 23:59:59)
3. [E][ ] finish ip (at: Oct 8 2022 09:00:00 - 10:30:00)
```
### `mark` - Marks a specified task as completed
Command format: `mark INDEX`

Marks the task stored at `INDEX` in Duke list as completed.

#### Example of Usage:

Input:
```
mark 1 
```

Output:
```
Nice! I've marked this task as done:
  [T][X] sweep the floor
```
### `unmark` - Unmarks a specified task as incomeplete
Command format: `unmark INDEX`

Unmarks the task stored at `INDEX` in Duke list as incomplete.

#### Example of Usage:

Input:
```
unmark 1 
```

Output:
```
OK, I've marked this task as not done yet:
  [T][ ] sweep the floor
```
### `delete` - Deletes a specified task from Duke list
Command format: `delete INDEX`

Deletes the task stored at `INDEX` in Duke list.

#### Example of Usage:

Input:
```
delete 3 
```

Output:
```
Noted, I've removed this task:
  [E][ ] finish ip (at: Oct 8 2022 09:00:00 - 10:30:00)
Now you have 2 tasks in the list.
```
### `find` - Searches for tasks that match a given string
Command format: `find SEARCH_STRING`

Searches for all tasks that match `SEARCH_STRING`

#### Example of Usage:

Input:
```
find sweep
```

Output:
```
1. [T][ ] sweep the floor
```
### `bye` - Exits the application and saves all tasks in Duke list
Command format: `bye`

Exits the application and saves all tasks currently within Duke list.

#### Example of Usage:

Input:
```
bye
```

Output:

The application terminates and the window closes.