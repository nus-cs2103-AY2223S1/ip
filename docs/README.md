# Duke's User Guide

## Features 

### Bye (`bye`)

Exit the program. 

### Todo (`todo`)

Add a new todo task to the list. 

### Event (`event`)

Add a new event (with event date) to the list. 

### Deadline (`deadline`)

Add a new deadline (with deadline due date) to the list. 

### List (`list`)

Return a list of tasks stored and managed by Duke. 

### Mark (`mark`)

Mark a task as completed. 

### Unmark (`unmark`)

Mark a task as incomplete. 

### Delete (`delete`)

Delete a task from the list, according to the list index referenced. 

### Find (`find`)

Find the task with corresponding words included as part of the task description. 

### Sort (`sort`)

Sort the tasks in the list in a certain order (by name, by due date, by completion status, by type of task).

## Usage

### `bye` - Exit the program

Exits the program. 

Example of usage: 

`bye`

Expected outcome:

Exits and closes the program. 


### `todo` - Add a todo task to be managed

Creates a new todo task and adds it to the list of tasks to be managed. 

Example of usage:

`todo Reading assignment pg 1-2`

Expected outcome:

Adds a new todo task, Reading assignment pg 1-2, to the task list. 

```
Ah, more reality stuff. Here, I've added:
T |   |  Reading assignment pg 1-2
Now you have 1 tasks in the list.
```

### `event` - Add an event task to be managed

Creates a new event task (with date of occurrence in the form yyyy-mm-dd after the keyword /at) 
that is added to the list of tasks to be managed.

Example of usage:

`event tp team meeting /at 2022-09-10`

Expected outcome:

Adds a new event task, tp team meeting, happening at 10 September 2022,
to the task list.

```
Ah, more reality stuff. Here, I've added:
E |   |  tp team meeting | 10 Sep 2022
Now you have 2 tasks in the list.
```

### `event` - Add an event task to be managed

Creates a new event task (with date of occurrence in the form yyyy-mm-dd after the keyword "/at")
and adds it to the list of tasks to be managed.

Example of usage:

`event tp team meeting /at 2022-09-10`

Expected outcome:

Adds a new event task, tp team meeting, happening at 10 September 2022,
to the task list.

```
Ah, more reality stuff. Here, I've added:
E |   |  tp team meeting | 10 Sep 2022
Now you have 2 tasks in the list.
```

### `deadline` - Add a deadline task to be managed

Creates a new deadline task (with due date in the form yyyy-mm-dd after the keyword "/by")
and adds it to the list of tasks to be managed.

Example of usage:

`deadline ip final submission /by 2022-09-16`

Expected outcome:

Adds a new deadline task, ip final submission, due by 16 September 2022,
to the task list.

```
Ah, more reality stuff. Here, I've added:
D |   |  ip final submission | 16 Sep 2022
Now you have 3 tasks in the list.
```

### `list` - Return list of tasks currently managed

Returns a list of the tasks currently managed. 

Example of usage:

`list`

Expected outcome:

Returns a list of tasks managed by Duke. 

```
1. T |   |  Reading assignment pg 1-2
2. E |   |  tp team meeting | 10 Sep 2022
3. D |   |  ip final submission | 16 Sep 2022
```

### `mark` - Mark a task as completed

Marks the task at indicated index as completed. 

Example of usage:

`mark 1`

Expected outcome:

Marks the task at index 1 as completed

```
Congratulations on smashing reality!
T | X | Reading assignment pg 1-2
```

### `unmark` - Mark a task as incomplete

Marks the task at indicated index as incomplete.

Example of usage:

`unmark 1`

Expected outcome:

Marks the task at index 1 as incomplete.

```
Oops reality is catching up... this is still undone:
T |   |  Reading assignment pg 1-2
```

### `delete` - Delete a task

Deletes the task at indicated index.

Example of usage:

`delete 1`

Expected outcome:

Deletes the task at index 1.

```
This task has perished from reality:
T |   |  Reading assignment pg 1-2
Now you have 2 tasks in the list.
```

### `find` - Search for tasks

Finds all tasks whose description contains indicated keyword. 

Example of usage:

`find tp`

Expected outcome:

Returns the task(s) containing "tp" in their description.

```
Here are the tasks that match your search: 
1. E |   |  tp team meeting | 10 Sep 2022
```

### `sort` - Sort the task list

Sorts the task list according to the criteria indicated. 

Possible sorting criteria:
1. Sort by name (keyword: `name`)
2. Sort by date (keyword: `dates`)
3. Sort by completion status (keyword: `done`)
4. Sort by type of task (keyword: `task`)

Example of usage:

`sort task`

Expected outcome:

Returns the list of tasks sorted by task type. 

```
Here are the tasks, sorted by task
1. D |   |  ip final submission | 16 Sep 2022
2. E |   |  tp team meeting | 10 Sep 2022
```
