# User Guide

## Features

### Three types of tasks: to-dos, deadlines, and events

Three different types of tasks to suit your productivity needs.

### List tasks

See everything that you need to do.

### See your schedule in a time period

View which deadlines or events are happening in a time period. Always stay on the ball.

### Mark and unmark tasks as done

Tasks that are done are clearly shown.

### Find, add, and delete tasks.

Organise tasks with our intuitive search system.



## Usage

### `list` - List all tasks

Lisk all tasks, including both done and not-done tasks. Optionally, include a date to list tasks that occur on a certain date.

Example of usage: 

`list`

Expected outcome: 

A list of tasks.

```
Nephew got a lot of things to do:
1. [T][ ] Read book
2. [T][ ] Read magazine
3. [D][ ] CS2100 assignment (by: 2022-10-22)
```



### `schedule` - View your schedule

View your schedule in a given time period.

Example of usage:

`schedule 2022-10-21 2022-10-28`

Expected outcome:

A schedule for the given time period

```
Nephew's schedule for 2022-10-21 - 2022-10-28:
3. [D][] CS2100 assignment (by: 2022-10-22)
```



### `mark` - Mark a task as done

Mark a task as done.

Example of usage:

`mark 1`

Expected outcome:

Displays the marked task

```
Fuiyoh, nephew so efficient! Finished this task:
[T][X] Read magazine
```



### `unmark` - Unmark a task as done

Unmark a task as done

Example of usage:

`unmark 1`

Expected outcome:

Displays the unmarked task

```
Haven't done yet, mark what mark? Unmarked ths task:
[T][ ] Read magazine
```



### `find` - Find tasks by name

Search for any tasks that contain the given query term

Example of usage:

`find read`

Expected outcome:

All tasks that contain the `read` keyword

```
Heer are the matching tasks in your list:
1. [T][ ] Read book
2. [T][ ] Read magazine
```



### `todo` - Add a to-do

Add a to-do. A to-do is a generic all-purpose task without a date.

Example of usage:

`todo laundry`

Expected outcome:

The new to-do, and the number of tasks currently in the list.

```
Nephew got new to-do:
[T][ ] laundry 
Nephew now have 4 tasks in the list
```



### `deadline` - Add a deadline

Add a deadline. A deadline is a task with a due date.

Example of usage:

`deadline CS2105 assignment /by 2022-10-22`

Expected outcome:

The new deadline, and the number of tasks currently in the list.

```
Newphew got new deadline, hurry up before I beat you:
[D][ ] CS2105 assignment (by: 2022-10-22)
Nephew now have 4 tasks in the list
```



### `event` - Add an event

Add an event. An event is a task with a date which it occurs on.

Example of usage:

`event Doraemon's birthday /at 2022-09-03`

Expected outcome:

The new event, and the number of tasks currently in the list.

```
Nephew so busy, got new event:
[E][ ] Doraemon's birthday (at: 2022-09-03)
Nephew now have 6 tasks in the list
```



### `delete` - Delete a task

Delete a task. It is wiped from the database, and will not be in future `list` or `find` commands.

Example of usage:

`delete 1`

Expected outcome:

The deleted task, and the number of tasks currently in the list.

```
Haiya so lazy. Deleted this task:
[T][ ] read book
Nephew now have 5 tasks in the list
```



### `bye` - Exit the roger program

Save your tasks and exit the program.

Example of usage:

`bye`

Expected outcome:

No outcome

```
expected output
```
