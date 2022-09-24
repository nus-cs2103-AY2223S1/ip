# User Guide

## Features 

### Schedule Deadlines and Events
Schedule set deadlines for important events and tasks!

### Create TODOs
Create quick to dos to help keep your tasks on track.

### View Deadlines
Easily keep on track with your tasks by viewing upcoming deadlines. 

## Usage

### `todo`

Create a new todo, a task with no deadlines or timeframe.

Example of usage: 

`todo a new task`

Expected outcome:

A new task will be created.

```
Got it. I've added this task:[T][] a new task
Now you have 1 tasks in the list.
```

### `deadline` 

Create a deadline with a due date

Example of usage:

`deadline homework /by 2022-09-22 23:59`

Expected outcome:

A new deadline will be created with this due date

```
Got it. I've added this task:[D][] homework (by: Sep 22 2022 23:59)
Now you have 1 tasks in the list.
```

### `event`

Create an event with a ranged date

Example of usage:

`event an event /at friday - saturday`

Expected outcome:

A new event with a ranged date

```
Got it. I've added this task:[E][] an event (at: friday - saturday)
Now you have 1 tasks in the list.
```

### `list`

Show a list of all tasks

Example of usage:

`list`

Expected outcome:

A list of all current tasks

```
1. [E][] an event (at: friday - saturday)
2. [T][] A Todo
```

### `find`

Find a specified task in a list of tasks

Example of usage:

`find A Todo`

Expected outcome:

A list of tasks that match the keyword

```
1. [T][] A Todo
```

### `schedule`

Display all deadlines that are scheduled for this day

Example of usage:

`schedule 2022-09-25`

Expected outcome:

A list of all current tasks due on this date

```
1. [D][] A Deadline (by: Sep 25 2022 12:00)
2. [D][] Another Deadline (by: Sep 25 2022 13:00)
```

### `mark`

Mark a task as complete

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done.
[T][X] A Task
```

### `unmark`

Mark a task as incomplete

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][] A Task
```

### `delete`

delete an unwanted task

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:[T][] A Task
Now you have 2 tasks in the list
```




