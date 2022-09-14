# User Guide

## Features

### Task Manager

You can:

1. Create tasks of different types
    - ToDo
    - Deadline
    - Event
2. List all tasks
3. Mark tasks as done
4. Delete tasks

### Finding Tasks

You can look for tasks by keywords in the task description.

### Sorting Tasks

You are also able to sort tasks by their due dates or starting times.

## Usage

### `todo` - Create a ToDo task

This command creates a new ToDo task, which only has a description.

Example of usage:

`todo read Atomic Habits`

Expected outcome:

Creates a new ToDo task, with "read Atomic Habits" as a description.

```
Got it. I've added this task:
[T][] read Atomic Habits
Now you have 1 task in the list.
```

### `deadline` - Create a Deadline task

This command creates a new Deadline task, which has a description and a due date.

Example of usage:

`deadline return Atomic Habits /by 14/09/2022`

Expected outcome:

Creates a new Deadline task, with "return Atomic Habits" as a description and "Sep 14 2022" as the due date.

```
Got it. I've added this task:
[D][] return Atomic Habits (by: Sep 14 2022)
Now you have 2 tasks in the list.
```

### `event` - Create an Event task

This command creates a new Event task, which has a description and a start time.

Example of usage:

`event CS2103T Team Meeting /at 14/09/2022 1600`

Expected outcome:

Creates a new Event task, with "CS2103T Team Meeting" as a description and "Sep 14 2022 04:00 PM" as the start time.

```
Got it. I've added this task:
[E][] CS2103T Team Meeting (at: Sep 14 2022 04:00 PM)
Now you have 3 tasks in the list.
```

### `list` - List all tasks

This command shows all tasks on the list.

Example of usage:

`list`

Expected outcome:

Lists all tasks.

```
Here are your tasks:
1. [T][] read Atomic Habits
2. [D][] return Atomic Habits (by: Sep 14 2022)
3. [E][] CS2103T Team Meeting (at: Sep 14 2022 04:00 PM)
```

### `mark` - Marks task as done

This command marks the specified task as done.

Example of usage:

`mark 1`

Expected outcome:

Marks task 1 as done. "X" indicates that the task is done.

```
Nice! I've marked this task as done:
[T][X] read Atomic Habits
```

### `unmark` - Marks task as not done

This command marks the specified task as not done.

Example of usage:

`unmark 1`

Expected outcome:

Marks task 1 as not done.

```
Okay, I've marked this task as not done yet:
[T][] read Atomic Habits
```

### `delete` - Delete specified task

This command deletes the specified task from the list.

Example of usage:

`delete 1`

Expected outcome:

Deletes task 1 from the list.

```
Noted. I've removed this task:
[T][] read Atomic Habits
Now you have 2 tasks in the list.
```

### `find` - Find tasks

This command finds tasks from the list with matching descriptions.

Example of usage:

`find Atomic`

Expected outcome:

Find tasks with matching descriptions.

```
Here are the matching tasks in your list:
1. [D][] return Atomic Habits (by: Sep 14 2022)
```

### `sort` - Sort tasks

This command sorts tasks by their due dates and starting times.

Example of usage:

`sort`

Expected outcome:

Lists all tasks in their sorted order.

```
Here are your tasks:
1. [D][] return Atomic Habits (by: Sep 14 2022)
2. [E][] CS2103T Team Meeting (at: Sep 14 2022 04:00 PM)
3. [D][] watch CS2100 Lecture (by: Sep 28 2022)
```