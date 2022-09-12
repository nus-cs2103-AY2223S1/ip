# User Guide

## Features 

### Feature- Adding tasks

Allows for the addition of three types of tasks:
* To-do
* Events
* Deadlines

### Feature- Deleting tasks

Delete tasks that are no longer necessary.

### Feature- Mark/Unmark

Mark a task as done or unmark it.

### Feature- List

Displays all remaining tasks.

### Feature- List All On Date:

Displays all tasks that are due on a given date.

### Feature- find:

Displays all tasks matching a given search key.


## Usage

### `todo` - Add a todo task to the list

Adds a todo task to the list

Example of usage: 

`todo task`

Expected outcome:

A todo task will be saved and the following output will be displayed:

```
Got it! I've added your task:

[T][ ] task
Now you have 1 tasks in the list!
```

### `deadline` - Add a todo task to the list

Adds a deadline task to the list

Example of usage:

`deadline task /by 2000-01-01`

Expected outcome:

A deadline task will be saved and the following output will be displayed:

```
Got it! I've added your task:

[D][ ] task (by: Jan 1 2000)
Now you have 1 tasks in the list!
```

### `event` - Add a todo task to the list

Adds a event task to the list

Example of usage:

`event task /at 2000-01-01`

Expected outcome:

A event task will be saved and the following output will be displayed:

```
Got it! I've added your task:

[E][ ] task (by: Jan 1 2000)
Now you have 1 tasks in the list!
```

### `delete` - Delete a task

Deletes the task at a given index.

Example of usage:

```
todo task
delete 1
```

Expected outcome:

The task will be deleted and the following output will be displayed:

```
I've removed this task!
[T][ ] task
Now you have 0 tasks!
```

### `mark` - Mark a task as done

Marks the task at a given index as done.

Example of usage:

```
todo task
mark 1
```

Expected outcome:

The task will be marked as done and the following output will be displayed:

```
Nice! I've marked this task as done!:
[T][X] task
```

### `unmark` - Mark a task as not done

Marks the task at a given index as not done.

Example of usage:

```
todo task
mark 1
unmark 1
```

Expected outcome:

The task will be marked as not done and the following output will be displayed:

```
Okay! I've marked this task as not done yet!:
[T][ ] task
```

### `list` - List all tasks

Displays all the tasks, sorted by date.

Example of usage:

```
todo td
deadline dl /by 2000-01-01
event ev /at 1999-01-01
list
```

Expected outcome:

The following output will be displayed:

```
Here are your tasks!:
[T][ ] td
[E][ ] ev (at: Jan 1 1999)
[D][ ] ev (at: Jan 1 2000)
```

### `listallondate` - List all tasks on a given date

Displays all the tasks on a given date.

Example of usage:

```
todo td
deadline dl /by 2000-01-01
event ev /at 1999-01-01
listallondate 1999-01-01
```

Expected outcome:

The following output will be displayed:

```
Here are the tasks on Jan 1 1999!:
[E][ ] ev (at: Jan 1 1999)
```

### `find` - Find tasks by a search keyword

Displays all the tasks matching a given keyword

Example of usage:

```
todo td
todo hello world
todo hello mom
todo i love 2103

list
```

Expected outcome:

The following output will be displayed:

```
Here are your tasks!:
[T][ ] td
[E][ ] ev (at: Jan 1 1999)
[D][ ] ev (at: Jan 1 2000)
```

