# User Guide

## Features 

### Stores tasks locally

All tasks are stored locally.

### Easy to use GUI with extensive usages

Cute beaver helps you to manage your tasks!

## Usage

### `list` - Lists all current tasks


Example of usage: 

`list`

Expected outcome:
A list of all tasks on the todo list

```
1. [T][X] Sample Todo
2. [E][] Sample Event <02:01 Saturday 01 February 2020>
```

### `todo` - Adds a todo to task list


Example of usage:

`todo sleep`

Expected outcome:
Adds a task to the end of the task list

```
Added todo:
[T][] Sleep
```

### `event` - Adds new Event to task list


Example of usage:

`event sleep /at 2022-09-14 22:00`

Expected outcome:
Adds new event to the end of the task list

```
Added event:
[E][] sleep <22:00 Wednesday 14 September 2022>
```

### `deadline` - Adds new Deadline to task list


Example of usage:

`deadline sleep /by 2022-09-14 22:00`

Expected outcome:
Adds new deadline to the end of the task list

```
Added deadline:
[D][] sleep <22:00 Wednesday 14 September 2022>
```

### `mark` - Marks or unmarks a task as done


Example of usage:

`mark 1`

Expected outcome:
Marks a task at that index as done

```
Nice! I've marked this task as done:
[T][X] Sample Todo

Or

:( Stop procrastinating! I've marked it as undone.
[T][] Sample Todo
```

### `delete` - Deletes task from task list


Example of usage:

`delete 1`

Expected outcome:
Deletes a task from the task list at the specified index
```
Wow! Good job, I have removed the following task:
[T][X] Sample Todo
```

### `find` - Finds tasks containing the keyword


Example of usage:

`find sleep`

Expected outcome:
Returns all tasks containing that substring
```
Here are the matching tasks in your list:
[T][] Sleep
[E][] sleep <22:00 Wednesday 14 September 2022>
[D][] sleep <22:00 Wednesday 14 September 2022>
```
### `reminder` - Returns the task with the nearest, valid deadline


Example of usage:

`reminder`

Expected outcome:
Returns the expected time till the task expires
```
Due in 0 days, 1 hours, 33 minutes
[E][] sleep <22:00 Wednesday 14 September 2022>
```
