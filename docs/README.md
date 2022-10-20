# User Guide

## Features 

### Task management

View, add, and mark tasks as complete. 


## Usage

### `list` - show tasks

Example of usage: 

`list`

Expected outcome:

```
1. ...
2. ...
```


### `todo` - add todo task

Example of usage: 

```
todo {todo description}
todo walk dog
```

Expected outcome:

```
Got it. I've added this task:
  [T][] walk dog
Now you have 2 tasks in the list.
```


### `event` - add event task

Example of usage: 

```
event {description} /at {date}
event gina's wedding /at 2022-09-19
```

Expected outcome:

```
Got it. I've added this task:
  [E][] gina's wedding (at: 2022-09-19)
Now you have 3 tasks in the list.
```


### `deadline` - add deadline task

Example of usage: 

```
deadline {description} /by {date}
deadline chem assignment /by 2022-09-19
```

Expected outcome:

```
Got it. I've added this task:
  [D][] chem assignment (by: 2022-09-19)
Now you have 2 tasks in the list.
```


### `mark` - mark task as completed

Example of usage: 

```
mark {task number}
mark 1
```

Use `list` command to find a task's number.

Expected outcome:

Before:
```
[T][] walk dog 
```

After:
```
[T][X] walk dog 
```


### `unmark` - mark task as not completed

Example of usage: 

```
unmark {task number}
unmark 1
```

Use `list` command to find a task's number.

Expected outcome:

Before:
```
[T][X] walk dog 
```

After:
```
[T][] walk dog 
```

### `delete` - remove task from list

Example of usage: 

```
delete {task number}
delete 1
```

Use `list` command to find a task's number.

Expected outcome:

```
Now you have 4 tasks in the list.
```

### `find` - search for tasks

Example of usage:

```
find {part of task description}
find party
```

Expected outcome:

```
Here are the matching tasks in your list:
[E][] birthday party (at: 2022-02-02)
[T][X] buy party gifts
```