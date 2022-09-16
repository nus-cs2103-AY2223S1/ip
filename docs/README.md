# Duke - User Guide

Duke is a task management application that assist you to keep track of your daily tasks and activities.

# Features 

## Displaying list of tasks

This command allows you to list the tasks that have been previously added.

### Usage

### `list` - Displays list of tasks

Describe the action and its outcome.

Example of usage:

`list`

Expected outcome:
```
1. [E] [] Wash dishes (at: Oct 01 2022)
2. [T] [X] Plant tree
```
## Creating a Todo task

This command allows you to create a to-do task that does not have a specific date or time.

### Usage

### `todo` - Creates a to-do task

Describe the action and its outcome.

Example of usage:

`todo wash dishes`

Expected outcome:
```
Got it. I've added this task:
[T] [] wash dishes
Now you have 3 tasks in the list
```
## Creating a Deadline task

This command allows you to create a deadline task that has to be done by a specific date.

### Usage

### `deadline` - Creates a deadline task

Describe the action and its outcome.

Example of usage:

`deadline wash dishes /by 2022-10-12`

Expected outcome:
```
Got it. I've added this task:
[D] [] wash dishes (by: Oct 12 2022)
Now you have 4 tasks in the list
```
## Creating an Event task

This command allows you to create a event task that occurs on a specific date.

### Usage

### `event` - Creates an event task

Describe the action and its outcome.

Example of usage:

`event wash dishes /at 2022-10-12`

Expected outcome:
```
Got it. I've added this task:
[E] [] wash dishes (at: Oct 12 2022)
Now you have 4 tasks in the list
```
## Marking a task as done

This command allows you to mark a task as done once it is completed.

### Usage

### `mark` - marks a task as done

Describe the action and its outcome.

Example of usage:

`mark 2`

Expected outcome:
```
Nice! I've marked this task as done:
[E] [X] wash dishes (at: Oct 12 2022)
```
## Marking a task as undone

This command allows you to mark a task as undone once it is completed.

### Usage

### `unmark` - Creates an event task

Example of usage:

`unmark 2`

Expected outcome:
```
OK, I've marked this task as not done yet:
[E] [] wash dishes (at: Oct 12 2022)
```
## Finding a task

This command allows you find tasks using their names.

### Usage

### `find` - Finding a task

Example of usage:

`find he`

Expected outcome:
```
Here are the matching tasks in your list:
1. [D] [] help mom (by: Oct 12 2022)
4. [T] [] head to store
6. [E] [] wash dishes (at: Oct 12 2022)
```
## Deleting a task

This command allows you to delete a task according to it's serial number on the list.

### Usage

### `delete` - deleting a task

Example of usage:

`delete 2`

Expected outcome:
```
Noted. I have removed this task:
[D] [] help mom (by: Oct 12 2022)
Now you have 3 tasks in the list
```
## Displaying the schedule

This command allows you to display the schedule of you tasks according to the dates for events and deadlines.

### Usage

### `schedule` - deleting a task

Example of usage:

`schedule`

Expected outcome:
```
Tasks for Oct 12 2022: 
[D] [] help mom (by: Oct 12 2022)
[D] [X] do homework (by: Oct 12 2022)
Tasks for Oct 15 2022: 
[E] [] football game (at: Oct 15 2022)
[D] [X] print files (by: Oct 15 2022)
```