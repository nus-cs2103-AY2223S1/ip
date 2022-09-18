# User Guide for UNC

## Features 

- List out all the tasks you have
- Add `todo`, `deadline`, or `event` tasks
- Mark and mark tasks as done
- Delete tasks
- Find tasks with a keyword
- Sort the list of tasks by time

## Usage

### `list` all current tasks

Displays all the tasks you have as a list.

Example of usage: 

`list`

Expected outcome:

Your list is displayed by UNC.

```
Here are your tasks:
1. [D][X] Trade (by: 02 Aug 2022)
2. [T][] Jog
3. [D][] assignment (by: 09 Sep 2022)
You have 3 tasks on the list.
```


### Add `todo`

Adds a task to be completed to your list.

Example of usage: 

`todo read book`

Expected outcome: 

A `todo` task `read book` is added to the list.

```
Added:
[T][] read book
Now you have 4 tasks on the list.
```


### Add `deadline`

Adds a task with a date to be completed by.

Example of usage: 

`deadline return book /by 2022-09-16`

Expected outcome:

A `deadline` task `return book` with deadline `16 Sept 2022` is added to the list.


```
Added:
[D][] return book (by: 16 Sep 2022)
Now you have 5 tasks on the list.
```

### Add `event`

Adds a task that happens at a specific date.

Example of usage: 

`event bookclub meeting /at 2022-08-11`

Expected outcome:

An `event` task `bookclub meeting` with date `11 Oct 2022` is added to the list.

```
Added:
[E][] bookclub meeting (at: 11 Aug 2022)
Now you have 6 tasks on the list.
```


### `mark` task as done

Marks the task at the number as done.

Example of usage: 

`mark 2`

Expected outcome:

The second item on the list is now marked as done with an "X".

```
Marked as done:
[T][X] Jog
```


### `unmark` task as done

Marks the task at the number as not done. 

Example of usage: 

`unmark 2`

Expected outcome:

The second item on the list is now marked as not done without an "X".

```
Marked as not done:
[T][] Jog
```


### `delete` task from list

Removes the task at the number.

Example of usage: 

`delete 2`

Expected outcome:

The second item on the list is now removed, and all tasks after it shifts up.

```
Deleted:
[T][] Jog
Now you have 5 tasks on the list.
```

### `find` tasks with a keyword

Displays every task on the list with the keyword in its description.
Only full words will be matched.

Example of usage: 

`find book`

Expected outcome:

A list of tasks that contain "book" is displayed.

```
Here are your tasks:
1. [T][] read book
2. [D][] return book (by: 16 Sep 2022)
You have 2 tasks on the list.
```

### `sort` the list

Sort your list of tasks in chronological order. Tasks with no dates are after those with dates.

Example of usage: 

`sort`

Expected outcome:

Your sorted list is displayed.

```
Here are your tasks:
1. [D][X] Trade (by: 02 Aug 2022)
2. [E][] bookclub meeting (at: 11 Aug 2022)
3. [D][] assignment (by: 09 Sep 2022)
4. [D][] return book (by: 16 Sep 2022)
5. [T][] read book
```

### `bye` and close the app

Stops the program and closes the application window.

Example of usage:

`bye`

Expected outcome:

The window is closed.
