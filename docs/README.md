# User Guide

## Features 

### Add Todo Task

Add a new task of type todo, for tasks that do not have a deadline.

### Add Event Task
Add a new task of type event, to note down events that occur at a certain date.

### Add Deadline Task
Add a new task of type deadline, with a specified deadline for tasks that should be completed
by a certain date.

### Delete Task
Removes the specified task from the todo list.

### View Tasks
View all the tasks that have been added in the form of a list.

### Mark Complete Tasks
Mark a task in the list as complete upon completion to keep track of tasks.

### Unmark Tasks
Remove the completion mark on a marked task to indicate that a task is not completed yet.

### Search Tasks
Search for tasks based on keywords.

### Statistics
View summary statistics of your Duke usage. 

## Usage

### `todo` - Add Todo Task

Add a new todo task.

Example of usage: 

`todo Read Book`

Expected outcome:

This todo task will be added to the list of tasks.

If the task is successfully added, Duke will respond with a message to verify.

```
Got it. I've added this task:
[T][] Read Book
Now you have 4 tasks in the list
```

### `event` - Add Event Task

Add a new event task.

Example of usage:

`event Meeting /at 2022-10-10`

Expected outcome:

This event task will be added to the list of tasks.

If the task is successfully added, Duke will respond with a message to verify.

```
Got it. I've added this task:
[E][] Meeting (at: Oct 10 2022)
Now you have 5 tasks in the list
```

### `deadline` - Add Deadline Task

Add a new deadline task.

Example of usage:

`deadline Complete User Guide /by 2022-10-10`

Expected outcome:

This deadline task will be added to the list of tasks.

If the task is successfully added, Duke will respond with a message to verify.

```
Got it. I've added this task:
[D][] Complete User Guide (at: Oct 10 2022)
Now you have 6 tasks in the list
```
### `delete` - Delete Task

Delete an existing task.

Example of usage:

`delete 1`

Expected outcome:

This task will be deleted from the list of tasks.

If the task is successfully deleted, Duke will respond with a message to verify.

```
Noted. I've removed this task:
[D][] Complete User Guide (at: Oct 10 2022)
Now you have 5 tasks in the list
```

### `list` - View Tasks

View all existing tasks in the form of a list.

Example of usage:

`list`

Expected outcome:

The list of existing tasks will be displayed to the user.

Duke will respond to the user with the list of existing tasks.

```
Here are the tasks in the list:
1. [D][] Complete User Guide (at: Oct 10 2022)
2. [E][] Meeting (at: Oct 10 2022)

```

### `mark` - Mark Complete Tasks

Mark tasks as complete upon completion.

Example of usage:

`mark 1`

Expected outcome:

The task will be marked as complete by having a X put in the checkbox.

Duke will respond to the user with the task that has been marked as complete.

```
Nice! I've marked this task as done:
[X] Complete User Guide 
```

### `unmark` - Unmark incomplete Tasks

Mark tasks as complete upon completion.

Example of usage:

`umark 1`

Expected outcome:

The task will be marked as incomplete by removing the X in the checkbox.

Duke will respond to the user with the task that has been marked as complete.

```
Nice! I've marked this task as done:
[X] Complete User Guide 
```

### `find` - Search Tasks

Search for tasks based on keywords.

Example of usage:

`find Guide`

Expected outcome:

A list of tasks that has a matching keyword as what the user has searched will be displayed.

```
Here are the matching tasks in your list:
1. [D][] Complete User Guide (at: Oct 10 2022)
```

### `statistics` - View Statistics Summary of Duke usage

View summary statistics of your Duke usage .

Example of usage:

`statistics`

Expected outcome:

A summary of the statistics of how much the user has used Duke will be displayed to the user.
```
DUKE STATISTICS
You have created:
2 Todo, 3 Events, 2 Deadlines.
You have completed 2 tasks
in Duke's lifetime.
```