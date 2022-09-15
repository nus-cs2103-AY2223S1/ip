# User Guide

## Features 

### Feature-Add new Task

Adds a normal Task or Task with a deadline or an Event with a date.

### Feature-Edit Task

Edit the status or name of the specified task.

### Feature-Delete Task

Remove unwanted task.

### Feature-Find Task

Search for a task specified by the keyword.

### Feature-List all Tasks

View all the tasks currently in the list.

## Usage

### Usage-Add Task/Deadlines/Events

Adds a normal Task or Task with a deadline or an Event with a date.

Example of usage: 

`todo laundry`

Expected outcome:

Returns the task specified

```
I've added to the list:
[T][ ] laundry
Now you have 5 tasks in the list.
```

### Usage-Mark/Unmark Tasks

Edit the status of the task.

Example of usage: 

`mark 1`

Expected outcome:

Marks the first task on the list.

```
Nice! I've marked this task as done:
[T][X] laundry
```

### Usage-Find Tasks

Find tasks based on keywords.

Example of usage: 

`find book`

Expected outcome:

Search for the task with the keyword, else returns none.

```
I found some matching tasks!
Here are the tasks in your list:
1. [D][ ] return book (by: Mon, Oct 10 2022)
```

### Usage-Delete Tasks

Delete the task from the list.

Example of usage: 

`delete 1`

Expected outcome:

Removes the first task from the list.

```
Noted. I've removed this task:
[T][X] laundry
Now you have 4 tasks in the list.
```

### Usage-Update Tasks

Updates and edit the task name.

Example of usage: 

`update`

Expected outcome:

Enters into update mode where user can specify what changes to make.

```
You have entered update mode. Please use the following format to update your task.
(choose index of task):(rename):(changes to made)
E.g. 1: rename: borrow book
Here are the tasks in your list:
1. [D][ ] return book (by: Mon, Oct 10 2022)
2. [E][ ] attend open house (at: Sat, Dec 10 2022)
3. [T][ ] laundry

```

`3:rename:dry clothes`

Expected outcome:

Updates task based on the input.

```
I've updated task number 3 name to: dry clothes
```

### Usage-List Tasks

Returns a full list of current tasks.

Example of usage: 

`list`

Expected outcome:

Returns the list of tasks.

```
Here are the tasks in your list:
1. [D][ ] return book (by: Mon, Oct 10 2022)
2. [E][ ] attend open house (at: Sat, Dec 10 2022)
3. [T][ ] laundry
```


