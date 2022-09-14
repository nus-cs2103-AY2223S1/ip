# User Guide

Primer is a app that helps you to manage tasks, deadlines and events.

--------------------------------------------------------------------------------------------------------------------

## Features 

- [x] Manages your todos, deadlines and events.
- [x] Mark or unmark them as completed
- [x] Sort your deadlines/events chronologically.

## Usage

### `help` - View help

help would tell you the different commands available.

Use help COMMAND to check how to use the command.

Examples:

- `help find`
- `help unmark`

Expected outcome:

```
find: Find tasks in the task list.
Parameters: DESCRIPTION. Example: find book
```

```
unmark: Mark a task as not done.
Parameters: INDEX. Example: unmark 2
```

### `deadline` - Create a new deadline

Adds a new deadline to the task list and shows you the new updated list

Example of usage: 

`deadline return book /by 2022-08-22`

Expected outcome:

Shows you the new updated task list.

```
I shall add this task.
[D][] return book (by: Aug 22 2022 0000)
Now you have 1 tasks in the list.
```

### `delete` - Delete a task

Deletes a task in the task list, showing you the deleted task and the number of tasks left in the list

Example of usage:

`delete 1`

Expected outcome:

Shows you the deleted task and the number of tasks left in the list

```
Noted. I've removed this task:
[D][] return book (by: Aug 22 2022 0000)
Now you have 0 tasks in the list
```

### `event` - Create a new event

Adds a new event in the task list and shows you the new updated list

Example of usage:

`event go to library /at 2022-08-22`

Expected outcome:

Shows you the new updated task list.

```
I shall add this task.
[E][] return book (at: Aug 22 2022 0000)
Now you have 1 tasks in the list
```

### `find` - Find a task in the task list

Shows you the tasks in the task list that matches the search string

Example of usage:

`find library`

Expected outcome:

Matching tasks in the task list

```
Here are the matching tasks in your list:
1.[E][] go to library (at: Aug 22 2022 0000)
```

### `list` - List the tasks in the task list

List the tasks in the task list

Example of usage:

`list`

Expected outcome:

List the tasks in the task list

```
Here are the tasks in your list:
1.[E][] go to library (at: Aug 22 2022 0000)
2.[D][] return book (by: Aug 22 2022 0000)
```

### `mark` - Mark a task as done

Mark a task as done according to its index

Example of usage:

`mark 1`

Expected outcome:

Shows you the task that is being marked as done

```
Nice! I've marked this task as done:
[E][X] go to library (at: Aug 22 2022 0000)
```

### `sort` - Sorts deadlines in the task list in chronological order

Sorts deadlines in chronological order

Example of usage:

`sort`

Expected outcome:

Displays the sorted list

```
Here are the tasks in your list:
1.[D][] return book (by: Aug 22 2022 0000)
```

### `todo` - Create a new todo

Create a new todo

Example of usage:

`todo borrow book`

Expected outcome:

Adds the new todo into the task list

```
I shall add this task.
[T][] borrow book
Now you have 3 tasks in the list.
```

### `unmark` - Mark a task as not done

Mark a task as not done according to its index

Example of usage:

`unmark 1`

Expected outcome:

Shows you the task that is being marked as not done

```
OK, I've marked this task as not done:
[E][] go to library (at: Aug 22 2022 0000)
```
