# Duke User Guide

## Features 

* [**Duke User Guide** ](#duke-user-guide)
    * [Add task](#add-task)
        * [Todo task](#todo-task)
        * [Event task](#event-task)
        * [Deadline task](#deadline-task)
    * [View tasks](#view-tasks)
    * [Delete tasks](#delete-task)
    * [Set priority](#set-priority)
    * [Update tasks](#update-tasks)
        * [Mark task](#mark-task)
        * [Unmark task](#unmark-task)
    * [Find tasks](#find-tasks)
        * [By keyword](#by-keyword)
        * [By priority](#by-priority)
     

## Usage

## Add task
#### Todo task

### `todo`

Adds a todo task to your task list.

Example of usage: 

`todo shopping`

Expected outcome:

```
GOT IT. I'VE ADDED THIS TASK:
[T][]L SHOPPING
NOW YOU HAVE 1 TASKS IN THE LIST.
```

#### Event task
### `event`

Adds an event task to your task list.

Example of usage: 

`event wedding /at 12/12/2022 23:59`

Expected outcome:

```
GOT IT. I'VE ADDED THIS TASK:
[E][]L WEDDING (AT: DEC 12 2022 11:59PM) 
NOW YOU HAVE 1 TASKS IN THE LIST.
```

#### Deadline task
### `deadline`

Adds a deadline task to your task list.

Example of usage: 

`deadline ip /by 12/12/2022 23:59`

Expected outcome:

```
GOT IT. I'VE ADDED THIS TASK:
[D][]L IP (BY: DEC 12 2022 11:59PM) 
NOW YOU HAVE 1 TASKS IN THE LIST.
```

## View tasks
### `list` 

List all the tasks in the task list.

Example of usage: 

`list`

Expected outcome:

A list of tasks in your task list.

```
HERE ARE THE TASKS IN YOUR LIST:
1.[T][]L SHOPPING
```

## Delete task
### `delete` 

Delete task in the task list.

Example of usage: 

`delete 1`

Expected outcome:

```
NOTED. I'VE REMOVED THIS TASK:
[T][]L SHOPPING
NOW YOU HAVE 0 TASKS IN THE LIST.
```
## Set priority
### `low` `medium` `high` 

Sets the priority of a task. The default priority for every task is low.

Example of usage: 

`low 1`

Expected outcome:

```
I'VE SET SHOPPING TO LOW PRIORITY.
```
## Update tasks
### Mark task
### `mark` 
Mark task as done.

Example of usage: 

`mark 1`

Expected outcome:

```
NICE! I'VE MARKED THIS TASK AS DONE:
[X] SHOPPING
```
### Unmark task
### `unmark` 
Mark task as undone.

Example of usage: 

`unmark 1`

Expected outcome:

```
OK, I'VE MARKED THIS TASK AS NOT DONE YET:
[] SHOPPING
```
## Find tasks
### By keyword

### `find`

Find tasks by keyword.

Example of usage: 

`find sh`

Expected outcome:

```
HERE ARE THE MATCHING TASKS IN YOUR LIST:
1.[T][] SHOPPING
2.[T][X] SHAVE MY BEARD
```

### By priority

### `low` `medium` `high`

Find tasks by priority level.

Example of usage: 

`medium`

Expected outcome:

```
TASKS THAT MATCHES PRIORITY MEDIUM:
1.[T][]M SHOPPING
```
