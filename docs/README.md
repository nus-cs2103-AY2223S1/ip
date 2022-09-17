# User Guide
Bloop keeps track of your tasks so that you don't have to worry about them!

## Features 
1. Add todo 
2. Add event
3. Add deadline
4. Delete 
5. Mark task as done
6. Unmark done task as not done
7. List all tasks
8. Sort deadlines by date and time
9. Find a task 
10. Termination of current session


### `todo` - Add ToDo 

Adds a task of type ToDo.

Example of usage:

`todo Organise bookshelf`

Expected outcome:

adds Organise bookshelf as a ToDo task to the list of tasks.

```
I've added this task-
[T][]Organise bookshelf
    Now you have 2 tasks in the list
```

### `deadline` - Add Deadline 

Adds a task of type Deadline.

Example of usage:

`deadline CS2103T quiz /by 17/09/2022 1650`

Expected outcome:

adds CS2103T quiz with a deadline of 17 Sep 2022 4:50 pm to the list of tasks.

```
I've added this task-
[D][]CS2013T quiz (by: 17 Sep 2022 04:50 pm)
    Now you have 3 tasks in the list
```


### `event` - Add Deadline 

Adds a task of type Event.

Example of usage:

`event Karaoke /at 15/09/2022 1830`

Expected outcome:

adds an event Karaoke at 15 Sep 2022 6:30 pm to the list of tasks.

```
I've added this task-
[E][]Karaoke (at: 15 Sep 2022 06:30 pm)
    Now you have 4 tasks in the list
```


### `delete` - Deletes a task

Deletes a task from the list.

Example of usage:

'delete 2'

Expected outcome:

Deletes the second task from the list.

```
This task has been removed-
[T][]Organise bookshelf
    Now you have 3 tasks in the list
```


### `mark` - Mark task as done

Marks a task as done.

Example of usage:

`mark 2`

Expected outcome:

Marks with a cross the specified task as done.

```
This task has been marked as done-
    [D][X]CS2013T quiz (by: 17 Sep 2022 04:50 pm)
```


### `unmark` - Unmark task as not done

Marks a task as not done.

Example of usage:

`unmark 2`

Expected outcome:

Unarks task as not done by removing the marked sign.

```
This task has been marked as not done-
    [D][]CS2013T quiz (by: 17 Sep 2022 04:50 pm)
```


### `list` - Displays tasks

Displays all tasks in the list.

Example of usage:

`list`

Expected outcome:

Displays all the tasks in the list.

```
Tasks in your list-
    1. [T][]Laundry
    2. [D][]CS2013T quiz (by: 17 Sep 2022 04:50 pm)
    3. [E][]Karaoke (at: 15 Sep 2022 06:30 pm)
```


### `sort` - Sort tasks 

Sorts the tasks with deadlines by their date and time.

Example of usage:

`sort`

Expected outcome:

List of deadlines sorted by the closest date and time.

```
    1. [D][]CS2013T quiz (by: 17 Sep 2022 04:50 pm)
    2. [D][]CS2013T iP (by: 20 Sep 2022 11:59 pm) 
```


### `find` - Finds a task

Finds a task containing the keyword.

Example of usage:

`find CS2103`

Expected outcome:

All tasks containing CS2103T in their name.

```
Matching tasks-
    1. [D][]CS2013T quiz (by: 17 Sep 2022 04:50 pm)
    2. [D][]CS2013T iP (by: 20 Sep 2022 11:59 pm)
```


### `bye` - Termination of current session

Ends the current session.

Example of usage:

`bye`

Expected outcome:

Exit message

```
Until next time :)
```





