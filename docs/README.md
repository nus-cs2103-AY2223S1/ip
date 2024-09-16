# User Guide

## Features 

### Manage tasks

- Add `ToDo`, `Event` and `Deadline` tasks
- Mark tasks as done
- Sort tasks in ascending order
- Find tasks by keyword

## Usage

### `todo` - Adds a todo task to the list

Format: `todo <insert todo task here>`

Example of usage: 

`todo CS2103T quiz`

Expected outcome:

Roofus will echo your newly added task and give the total number of tasks in the list.

```
Got it. I've added this task:
[T][] CS2103T quiz
Now you have 4 tasks in the list.
```

### `event` - Adds an event task to the list

Format: `event <insert event> /at <insert date in yyyy-mm-dd format>`

Example of usage:

`event CS2103T quiz /at 2022-02-02`

Expected outcome:

Roofus will echo your newly added task and give the total number of tasks in the list.

```
Got it. I've added this task:
[E][] CS2103T quiz at: 2022-02-02
Now you have 4 tasks in the list.
```

### `deadline` - Adds deadline task to the list

Format: `deadline <insert event> /by <insert date in yyyy-mm-dd format>`

Example of usage:

`deadline CS2103T quiz /by 2022-02-02`

Expected outcome:

Roofus will echo your newly added task and give the total number of tasks in the list.

```
Got it. I've added this task:
[D][] CS2103T quiz by: 2022-02-02
Now you have 4 tasks in the list.
```

### `mark` - Marks a task on the list

Format: `mark <task index>`

Example of usage:

`mark 4`

Expected outcome:

Roofus marks tasks with an 'X'.

```
Nice! I've mark this task as done:
[D][X] CS2103T quiz by: 2022-02-02
```

### `unmark` - Unmarks a task on the list

Format: `unmark <task index>`

Example of usage:

`unmark 4`

Expected outcome:

Roofus unmarks tasks by removing the 'X'.

```
Ok. I've marked this task as not done yet:
[D][] CS2103T quiz by: 2022-02-02
```

### `list` - Lists all tasks in the list

Example of usage:

`list`

Expected outcome:

Roofus will print out all the tasks.

```
Here are the tasks in your list:
1. [T][] first task 
2. [D][X] second task /by 2022-02-10
3. [E][] third task /at 2022-01-20
```

### `sort` - Sorts the tasks in the list based on task's date

Example of usage:

`sort`

Expected outcome:

Roofus will print out the current order of tasks.

```
Here are the tasks in your list:
1. [E][] third task /at 2022-01-20
2. [D][X] second task /by 2022-02-10
3. [T][] first task 
```

### `find` - Find a list of tasks that contains a keyword

Format: `find <keyword>`

Example of usage:

`find first`

Expected outcome:

Roofus will print out the tasks that matches the keyword.

```
Here are the matching tasks in your list:
3. [T][] first task 
```

### `delete` - Deletes a task from the list

Format: `delete <task index>`

Example of usage:

`delete 4`

Expected outcome:

Roofus will echo the deleted task and give the total number of tasks in the list.

```
Noted. I've removed this task:
[D][X] CS2103T quiz by: 2022-02-02
Now you have 3 tasks in the list.
```

### `clear` - Clears all tasks from the list

Example of usage:

`clear`

Expected outcome:

All data in storage will be cleared.

```
Storage has been cleared :)
```