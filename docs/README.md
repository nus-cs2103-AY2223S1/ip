# Duke User Guide

Duke is a chatbot that can track your tasks for you!

## Features

1. Add 3 types of tasks (ToDo, Deadline, Event)
2. List tasks in sorted order
3. Find tasks based on a keyword
4. Mark tasks as done or not done
5. Delete tasks


## Usage

### `todo` - Add a ToDo task

Adds a ToDo task. A ToDo task is a task without any date/time associated with it.

`todo DESCRIPTION`


Example:
`todo watch movie`

Expected outcome:
```
Got it, I've added this task:
[T][] watch movie
```


### `deadline` - Add a Deadline task

Adds a Deadline task. A Deadline task is a task that should be done before a specific date and time.

`deadline DESCRIPTION /by DATE_AND_TIME`

`DATE_AND_TIME` must be specified in the following format: YYYY-MM-DD HH:MM


Example:
`deadline return book /by 2022-12-16 18:00`

Expected outcome:
```
Got it, I've added this task:
[D][] return book (by: 16 Dec 2022 at 06:00 PM)
```


### `event` - Add an Event task

Adds an Event task. An Event task takes place during a certain time period.

`event DESCRIPTION /at DATE_AND_TIME_PERIOD`

There is no specific format for `DATE_AND_TIME_PERIOD`. Any string is acceptable.


Example:
`event project meeting /at Fri 9-10am`

Expected outcome:
```
Got it, I've added this task:
[E][] project meeting (at: Fri 9-10am)
```


### `list` - List all tasks

Lists all tasks. Optional to specify the field to sort by. If no field is specified, tasks are listed in the order they were added.

`list [/sort FIELD]`

`FIELD` must take one of the following values:
* `type`          Sort by task type (Todo, Deadline, Event)
* `status`        Sort by task status (done or not done)
* `description`   Sort by task description

Sort is always in **ascending** order.


Example:
`list`

Expected outcome:
Lists all tasks without sorting them.
```
Here are the tasks in your list:
1. [T][] watch movie
2. [D][] return book (by: 16 Dec 2022 at 06:00 PM)
3. [E][] project meeting (at: Fri 9-10am)
```

Example:
`list /sort description`

Expected outcome:
Lists all tasks sorted by their description.
```
Here are the tasks in your list:
1. [E][] project meeting (at: Fri 9-10am)
2. [D][] return book (by: 16 Dec 2022 at 06:00 PM)
3. [T][] watch movie
```


### `find` - Find tasks based on a keyword

Finds all tasks that contain the specified keyword in the description.

`find KEYWORD`

Note: Only 1 keyword is allowed.


Example:
`find book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [D][] return book (by: 16 Dec 2022 at 06:00 PM)
```



### `mark` - Mark a task as "done"

Marks the specified task as "done".

`mark TASK_NUMBER`

`TASK_NUMBER` is the index number of the task in the list view (without sorting).


Example:
`mark 3`

Expected outcome:
```
Nice! I've marked this task as done:
[E][X] project meeting (at: Fri 9-10am)
```


### `unmark` - Mark a task as "not done"

Marks the specified task as "not done".

`unmark TASK_NUMBER`

`TASK_NUMBER` is the index number of the task in the list view (without sorting).


Example:
`unmark 2`

Expected outcome:
```
OK, I've marked this task as not done:
[D][] return book (by: 16 Dec 2022 at 06:00 PM)
```


### `delete` - Delete a task

Deletes the specified task.

`delete TASK_NUMBER`

`TASK_NUMBER` is the index number of the task in the list view (without sorting).


Example:
`delete 1`

Expected outcome:
```
Noted. I've removed this task:
[T][] watch movie
```

## Notes
* All changes are saved to the hard disk automatically. So there is no need to manually save.
* To exit Duke, simply close the window. There is no command to exit Duke.

