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


### `t` - add todo task

Example of usage: 

```
t {todo name}
t walk dog
```

Expected outcome:

```
[T][] walk dog
```


### `event` - add event task

Example of usage: 

```
event {event name} /at {date}
event gina's wedding /at 2022-09-19
```

Expected outcome:

```
[E][] gina's wedding (at: 2022-09-19)
```


### `deadline` - add deadline task

Example of usage: 

```
deadline {deadline name} /by {date}
deadline chem assignment /by 2022-09-19
```

Expected outcome:

```
[D][] chem assignment (by: 2022-09-19)
```


### `mark` - mark task as completed

Example of usage: 

```
mark {task number}
mark 1
```

Get task number using `list`.

Expected outcome:

```
[T][X] walk dog
```


### `unmark` - mark task as not completed

Example of usage: 

```
unmark {task number}
unmark 1
```

Get task number using `list`.

Expected outcome:

```
[T][] walk dog
```


### `delete` - remove task from list

Example of usage: 

```
delete {task number}
delete 1
```

Get task number using `list`.

Expected outcome:

```
Now you have 4 tasks in the list.
```
