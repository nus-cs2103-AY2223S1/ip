# User Guide

## Features 

### Add a task

3 types of tasks can be added. (Todo, Event, Deadline)

### Delete a task

A task can be deleted from the task list stored

### Mark a task

A task can be marked as done or undone

### Find by keyword

Search results can be filtered by keywords to locate tasks with the keyword

### Tag a task

Labels can be used to categorise tasks

### Save a task

Tasks added to the list can be stored and loaded when the program starts


## Usage

### `bye` - exit the program

Exit the program

Format:

```
bye
```

Examples:

```
bye
```

Expected outcome:

Returns a string as output before closing the program window

```
Bye! Thanks for using Luke!
```

### `deadline` - create a task with its deadline

Add a task consisting of its deadline to the list of tasks

Format: 

```
deadline [name of task] /by [YYYY-MM-DD] [HHMM]
```

Examples:

```
deadline CS2103T /by 2022-05-14 1800
```

Expected outcome:

Task with the associated deadline will be added to the tasklist

```
Now you have [number of tasks] tasks in the list.
```

### `delete` - delete a task 

Delete a task when given its index

Format: 

```
delete [index]
```

Examples:

```
delete 1
```

Expected outcome:

Task with the given index will be removed from the list

```
Now you have [number of tasks] tasks in the list.
```

### `event` - create an event

Add an event to the list of tasks

Format: 

```
event [name of task] /at [YYYY-MM-DD] [HHMM]
```

Examples:

```
event meeting /at 2022-10-15 1600
```

Expected outcome:

The event and its associated date will be added to the task list 

```
Now you have [number of tasks] tasks in the list.
```

### `find` - find by keyword

Find tasks which match the keyword input

Format: 

```
find [keyword]
```

Examples:

```
find project
```

Expected outcome:

Matching tasks in the list which match the keyword will be displayed

```
Here are the matching tasks in your list:
```

### `list` - show all tasks

Display all the tasks in the task list

Format: 

```
list
```

Examples:

```
list
```

Expected outcome:

All the tasks in the list will be displayed

```
Here are the tasks in your list:
```

### `mark` - mark an existing task

Set the status of a task as done

Format: 

```
mark [index]
```

Examples:

```
mark 1
```

Expected outcome:

The task with the index will be marked as done

```
Nice! I've marked this task as done:
[x] [name of task]
```

### `tag` - tag task

Give a tag to an existing task

Format: 

```
tag [index] [tag name]
```

Examples:

```
tag 1 urgent
```

Expected outcome:

The task with the index will be given the tag name as input

```
Got it! I have set the tag of this task:
CS2103T project #urgent
```

### `todo` - add a todo task

Add a todo task to the list of tasks

Format: 

```
todo [name of task]
```

Examples:

```
todo project
```

Expected outcome:

The todo task will be added to the task list

```
Now you have [number of tasks] tasks in the list.
```

### `unmark` - unmark a task

Set the status of a task as undone

Format: 

```
unmark [index]
```

Examples:

```
unmark 1
```

Expected outcome:

The task with the index will be set to undone

```
OK, I've marked this task as not done yet:
[] project
```
