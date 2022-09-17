# User Guide

## Features 

### ToDo

Creates a todo object and adds it to the task list.

## Usage

### `todo`

Example of usage:

`todo CS2107 CTF`

Expected outcome:

```
Got it! I've added this task:
  [T][] CS2107 CTF
You now have 1 tasks in the list :)
```

Description of the outcome.



### Deadline

Creates a deadline object and adds it to the task list.

## Usage

### `deadline`

Example of usage:

`deadline CS2107 CTF /by 18/09/2022,2359`

Expected outcome:

```
Got it! I've added this task:
  [D][] CS2107 CTF (by:Sun, 18 September 2022, 11:59PM)
You now have 1 tasks in the list :)
```

Description of the outcome.



### Event

Creates an event object and adds it to the task list.

## Usage

### `event`

Example of usage:

`event CS2107 CTF submission /at 18/09/2022,2359`

Expected outcome:

```
Got it! I've added this task:
  [E][] CS2107 CTF submission (at:Sun, 18 September 2022, 11:59PM)
You now have 1 tasks in the list :)
```

Description of the outcome.



### Fixed Duration Task

Creates a Fixed duration task object and adds it to the task list.

## Usage

### `fixed`

Example of usage:

`fixed CS2107 CTF /takes 100`

Expected outcome:

```
Got it! I've added this task:
  [F][] CS2107 CTF (takes: 100 hours)
You now have 1 tasks in the list :)
```

Description of the outcome.



### List

Lists all the tasks in the task list.

## Usage

### `list`

Example of usage:

`list`

Expected outcome:

```
Here are your tasks
1.[T][ ] CS2107 CTF 
2.[D][ ] CS2107 CTF  (by: Sun, 18 September 2022, 11:59PM)
3.[F][ ] CS2107  (takes: 100 hours)
```

Description of the outcome.



### Delete

Deletes a task from the task list.

## Usage

### `delete`

Example of usage:

`delete 1`

`mass delete 1 2 3`

`mass delete ALL`

Expected outcome:

```
Ok, I've removed this task:
  [T][ ] CS2107 CTF 
You now have 2 tasks in the list :)
```

Description of the outcome.



### mark

Marks a task as done.

## Usage

### `mark`

Example of usage:

`mark 1`

`mark 1 2 3`

`mass mark ALL`

Expected outcome:

```
Ok! I've marked it as done.
  [T][X] CS2107 CTF 
```

Description of the outcome.



### unmark

Marks a task as undone.

## Usage

### `unmark`

Example of usage:

`unmark 1`

`unmark 1 2 3`

`mass unmark ALL`

Expected outcome:

```
Ok! I've marked it as undone.
  [T][] CS2107 CTF 
```

Description of the outcome.



### find

Finds the task with the specified keyword

## Usage

### `find`

Example of usage:

`find submission`

`find CTF`

Expected outcome:

```
There's no such task!
```
```
Here are your tasks
1.[T][ ] CS2107 CTF 
2.[D][ ] CS2107 CTF  (by: Sun, 18 September 2022, 11:59PM)
```

Description of the outcome.



### mass

Allows the use of mass operations

## Usage

### `mass`

Example of usage:

`mass mark 1 2 3`

`mass unmark ALL`

`mass delete ALL`

Expected outcome:

```
Ok! I've marked it as done.
  [F][X] CS2107  (takes: 100 hours)
Ok! I've marked it as done.
  [D][X] CS2107 CTF  (by: Sun, 18 September 2022, 11:59PM)
Ok! I've marked it as done.
  [T][X] CS2107 CTF 
```
```
Ok! I've marked all tasks as undone.
```
```
These are the tasks I have deleted:
1.[T][ ] CS2107 CTF 
2.[D][ ] CS2107 CTF  (by: Sun, 18 September 2022, 11:59PM)
3.[F][ ] CS2107  (takes: 100 hours)

```

Description of the outcome.



### bye

Closes the application.

## Usage

### `bye`

Example of usage:

`bye`

Expected outcome:

Application will close

Description of the outcome.