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
expected output
```


### Add `todo`

Adds a task to be completed to your list.

Example of usage: 

`todo read book`

Expected outcome: 

A `todo` task `read book` is added to the list.

```
expected output
```


### Add `deadline`

Adds a task with a date to be completed by.

Example of usage: 

`deadline return book /by 2022-09-16`

Expected outcome:

A `deadline` task `return book` with deadline `16 Sept 2022` is added to the list.


```
expected output
```

### Add `event`

Adds a task that happens at a specific date.

Example of usage: 

`event bookclub meeting /at 2022-10-11`

Expected outcome:

An `event` task `bookclub meeting` with date `11 Oct 2022` is added to the list.

```
expected output
```


### `mark` task as done

Marks the task at the number as done.

Example of usage: 

`mark 2`

Expected outcome:

The second item on the list is now marked as done with an "X".

```
expected output
```


### `unmark` task as done

Marks the task at the number as not done. 

Example of usage: 

`unmark 2`

Expected outcome:

The second item on the list is now marked as not done without an "X".

```
expected output
```


### `delete` task from list

Removes the task at the number.

Example of usage: 

`delete 2`

Expected outcome:

The second item on the list is now removed, and all tasks after it shifts up.

```
expected output
```

### `find` tasks with a keyword

Displays every task on the list with the keyword in its description

Example of usage: 

`find book`

Expected outcome:

A list of tasks that contain "book" is displayed.

```
expected output
```

### `sort` the list

Sort your list of tasks in chronological order. Tasks with no dates are after those with dates.

Example of usage: 

`sort`

Expected outcome:

Your sorted list is displayed.

```
expected output
```
