# User Guide

## Features 

### Ability to add tasks!

Adds the respective tasks to be done to the schedule.

### List tasks!

Lists current tasks in the list.

### Mark completed and uncompleted tasks!

Indicate which tasks are complete/incomplete.

### Delete tasks!

Delete chosen task from the list.

### Find tasks!

Find tasks with keyword in the list.

## Usage

### `Todo` - Adds a task to be done

Adds a todo task.

Example of usage: 

`todo borrow book`

Expected outcome:

Added todo task.

```
Got it. I've added this task:
       [T][ ] borrow book
     Now you have 2 tasks in the list.
```

### `Event` - Adds an event to be happening

Adds an event.

Example of usage:

`event bro's wedding /at 2020-09-15 18:00`

Expected outcome:

Added event.

```
Got it. I've added this task:
       [E][ ] bro's wedding (at: 15 Sep 2020, 18:00)
     Now you have 3 tasks in the list.
```

### `Deadline` - Adds a deadline

Adds a deadline.

Example of usage:

`deadline biology assignment /by 2022-10-01 23:59`

Expected outcome:

Added task to do by deadline.

```
Got it. I've added this task:
       [D][ ] biology assignment (by: 1 Oct 2022, 23:59)
     Now you have 4 tasks in the list.
```

### `mark` - marks a completed task

Marks a task as completed.

Example of usage:

`mark 2`

Expected outcome:

Marks task as done.

```
Nice! I've marked this task as done:
       [T][X] borrow book
     Now you have 2 tasks in the list.
```

### `unmark` - unmark a task as incomplete

Marks a task as incomplete.

Example of usage:

`unmark 2`

Expected outcome:

Marked task as undone.

```
OK, I've marked this task as not done yet:
       [T][ ] borrow book
     Now you have 2 tasks in the list.
```

### `delete` - Deletes a task

Deletes a task.

Example of usage:

`delete 2`

Expected outcome:

Deleted a task.

```
Noted, I've removed this task:
       [T][X] borrow book
     Now you have 3 tasks in the list.
```

### `find` - Adds a task to be done

Finds a task with the given keywords.

Example of usage:

`find wedding`

Expected outcome:

Returns a list of task with keyword.

```
Here are the matching tasks in your list:
1. [E][ ] bro's wedding (at: 15 Sep 2020, 18:00)
2. [E][ ] friend's wedding (at: 20 Oct 2021, 10:00)
```


