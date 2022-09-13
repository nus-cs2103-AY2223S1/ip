# User Guide

## DukePro
DukePro contains two managers - one for your tasks, and one for your expenses.

### Task Manager

The Task Manager comes with the following features
1. Add a task.
2. Delete a task.
3. Mark a task as done.
4. Displays all tasks done on a specific date.
5. Display all tasks stored in your Task Manager.
6. Search for all tasks with matching keyword.

### Expense Manager

The Expense Manager comes with the following features
1. Add an expense.
2. Delete an expense.
3. Display the total amount of money spent.
4. Display the total amount of money spent on a particular date.
5. Display all expenses in the Expense Manager.

## Usage (Task Manager)

### `deadline` - Add a deadline

This command adds a task with a deadline to the Task Manager.

Example of usage: 

`deadline CS2103T IP /by 2022-09-16`

Expected outcome:

A task with a deadline is added to your Task Manager. The format of the date is in YYYY-MM-DD.

```
Duke heard: Got it. I've added this task:
[D][X] CS2103T IP (by:Sep 16 2022)
Now you have 5 tasks in your list
```
### `Event` - Add an event

Add an event/task where you need to be at a particular place.

Example of usage:

`event concert /at Esplanade`

Expected outcome:

An event with a specified location is added to your Task Manager.

```
Duke heard: Got it. I've added this task:
[E][X] Concert IP (at: Esplanade)
Now you have 5 tasks in your list
```

### `Todo` - Add a task

Add a generic task with no deadline or specific location.

Example of usage:

`todo CS2100 Lab`

Expected outcome:

A genetic task is added to your Task Manager.

```
Duke heard: Got it. I've added this task:
[T][X] CS2100 Lab
Now you have 5 tasks in your list
```

### `delete/Delete` - Deletes a task

Deletes a generic task with no deadline or specific location.

Example of usage:

`delete 1` or `Delete 1`

Expected outcome:

The task with the corresponding ID (Task Number) is deleted.

```
Duke heard: The following task has been deleted:
[T][X] CS2100 Lab
```

### `done/Done` - Marks a task as "done"

Marks a task as "done", to indicate the task is finished

Example of usage:

`done 1` or `Done 1`

Expected outcome:

The task with the corresponding ID (Task Number) is marked as "done".

```
Duke heard: Nice! I've marked this task as done:
[T][/] CS2100 Lab
```

### `list/List` - Displays all tasks stored

Displays every task stored in the Task Manager for the user to see.

Example of usage:

`list 1` or `List 1`

Expected outcome:

All tasks are displayed and shown to the user

```
Duke heard: Here are the task in your list:
1. [D][X] CS2103T IP (by:Sep 16 2022)
2. [E][X] Concert IP (at: Esplanade)
3. [T][X] CS2100 Lab
```

### `date/Date` - Displays all tasks due on a date

Displays every task due on a specific date, which must be in the format of YYYY-MM-DD.

Example of usage:

`date 2022-09-16` or `Date 2022-09-16`

Expected outcome:

All tasks due on the matching date are displayed and shown to the user

```
Duke heard: These tasks are due on 2022-09-16:
[D][X] CS2103T IP (by:Sep 16 2022)
```

### `find/Find` - Search for tasks with keyword

Search for every task with a matching keyword that you key in.

Example of usage:

`find 2022-09-16` or `Date 2022-09-16`

Expected outcome:

All tasks due on the matching date are displayed and shown to the user

```
Duke heard: The following tasks match your search
[D][X] CS2103T IP (by:Sep 16 2022)
```