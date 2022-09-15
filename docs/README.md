# User Guide

## Features 

### Schedule Deadlines and Events
Schedule set deadlines for important events and tasks!

### Create TODOs
Create quick to dos to help keep your tasks on track.

### View Deadlines
Easily keep on track with your tasks by viewing upcoming deadlines. 

## Usage

### `todo` - Describe action

Create a new todo, a task with no deadlines or timeframe.

Example of usage: 

`todo a new task`

Expected outcome:

A new task will be created.

```
Got it. I've added this task:[T][] a new task
Now you have 1 tasks in the list.
```

### `deadline` - Describe action

Create a deadline with a due date

Example of usage:

`deadline homework /by 2022-09-22 23:59`

Expected outcome:

A new deadline will be created with this due date

```
Got it. I've added this task:[D][] homework (by: Sep 22 2022 23:59)
Now you have 1 tasks in the list.
```

### `event` - Describe action

Create an event with a ranged date

Example of usage:

`event an event /at friday - saturday`

Expected outcome:

A new event with a ranged date

```
Got it. I've added this task:[E][] an event (at: friday - saturday)
Now you have 1 tasks in the list.
```