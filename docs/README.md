# Duke-it User Guide
Captain Duke-it is here to help you organise and manage your everyday tasks.

## Features Overview

### Adding a task

`todo <task>`

`deadline <task> /by <date>` 

`event <task> /at <date>`

### Listing all tasks

`list`

### Marking tasks as done/undone

`mark <task no.(s)>` 

`unmark <task no.(s)>`

### Deleting tasks

`delete <task no.(s)>`

### Finding tasks

`find <keyword>`

### Updating task

`update desc <task no.>` 

### Exiting the program

`bye`

#


## Usage

### `todo`

Saves a ToDo task.

Example of usage: 

`todo read book`

Expected outcome:

```
New task added:
  T |   | read book
Now you have 4 tasks in the list.
```

### `deadline`

Saves a task with a deadline.

Example of usage:

`deadline homework /by 2022-01-01`

Expected outcome:

```
New task added:
  D |   | homework | 01 Jan 2022
Now you have 5 tasks in the list.
```

### `event`

Saves an event with a date.

Example of usage:

`event birthday party /at 2022-07-18`

Expected outcome:

```
New task added:
  E |   | birthday party | 18 Jul 2022
Now you have 6 tasks in the list.
```

### `list`

Lists out all the tasks you have


Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
 1. T |   | read book
 2. D |   | homework | 01 Jan 2022
 3. E |   | birthday party | 18 Jul 2022
```

### `mark`

Marks all the tasks stated as done

Example of usage:

`mark 1, 2`

Expected outcome:

```
Nice! I've marked the task(s) as done: 
T | X | read book
D | X | homework | 01 Jan 2022
```

### `unmark`

Marks all the tasks stated as undone

Example of usage:

`unmark 1, 2`

Expected outcome:

```
OK, I've marked the task(s) as not done yet: 
T |   | read book
D |   | homework | 01 Jan 2022
```

### `delete`

Deletes all the tasks stated

Example of usage:

`delete 1, 2`

Expected outcome:

```
Noted. I've removed the task(s):
T |   | read book
D |   | homework | 01 Jan 2022
Now you have 1 tasks in the list.
```

### `find`

Finds all tasks containing the keyword

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
 1. T |   | read book
 2. T |   | return book
```

### `update desc`

Updates the description of the task stated

Example of usage:

`update desc 4 /to assignment`

Expected outcome:

```
The following task has been updated from:
  D |   | homework | 01 Jan 2022
to: 
  D |   |  assignment | 01 Jan 2022
```

### `bye`

Exits the program

Example of usage:

`bye`

Expected outcome:

```
// The program will automatically exit.
```