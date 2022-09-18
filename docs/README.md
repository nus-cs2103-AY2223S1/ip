# User Guide

## Features 
- list
- todo
- deadline
- event
- mark
- unmark
- remind
- bye


## Usage

### `list` - lists out all the tasks

Example of usage: 

`list`

Expected outcome:

list of tasks printed out

```
Here are your tasks:
...

```
### `todo` - creates a new todo task.

Example of usage: 

`todo (item)`

Expected outcome:

new todo task, added to the task list

```
Added Task
[T][ ] (item) 
now you have xxx tasks in the list
```

### `deadline` - creates a new deadline task

Example of usage: 

`deadline (item) /by (YYYY-MM-DD)`

Expected outcome:

creates a new deadline task and adds it into the tasklist.

```
Added Task
[D][ ] (item) 
now you have xxx tasks in the list
```

### `event` - creates a new event task

Example of usage: 

`event (item) /at (time)`

Expected outcome:

creates a new event task and adds it into the tasklist.

```
Added Task
[E][ ] (item) 
now you have xxx tasks in the list
```

### `mark` - marks a certain task as done

Example of usage: 

`mark (item no)`

Expected outcome:

changes status of task to marked

```
I have marked this task as done:
[T][X] (item)
```

### `unmark` - marks a certain task as not done

Example of usage: 

`unmark (item no)`

Expected outcome:

changes status of task to marked

```
I have marked this task as not done:
[T][X] (item)
```

### `remind` - lists out all deadlines that are due in a week or less

Example of usage: 

`deadline`

Expected outcome:

list of deadlines due in a week or less

```
[D][ ] item 
(by ...)
```

### `bye` - saves and exit from duke

Example of usage: 

`bye`

Expected outcome:

saves current task list and exits duke

```
Bye! Shutting down...
```
