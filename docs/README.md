# User Guide

## Features 

### Keep track of all your important tasks!

Use Duke to manage all your todos, deadlines and event. View their dates, times as well as completion status at a glance.

### Find tasks

Duke can help you search specific task(s) that was previously stored based on keyword.

## Usage

### `todo <description>` - Creates a todo

Adds a new todo task into the list.

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task: 
[T][] read book
Now you have 1 task(s) in your list.
```

### `deadline <description> /by <date>` - Creates a deadline

Adds a new deadline task into the list.

Example of usage:

`deadline return book /by 2022-09-19T14:30:00`

Expected outcome:

```
Got it. I've added this task:
[D][] return book (by: Sep 19 2022 02:30:00)
Now you have 2 task(s) in your list.
```

### `event <description> /at <date>` - Creates an event

Adds a new event task into the list.

Example of usage:

`event zoom meeting /by 2022-09-19T19:30:00`

Expected outcome:

```
Got it. I've added this task:
[E][] zoom meeting (by: Sep 19 2022 07:30:00)
Now you have 3 task(s) in your list.
```

### `list` - Display all current tasks

Shows all current task(s) in the list.

Example of usage:

`list`

Expected outcome:

```
Here are the task(s) in your list:
1. [T][] read book
2. [D][] return book (by: Sep 19 2022 02:30:00)
3. [E][] zoom meeting (at: Sep 19 2022 07:30:00)
```

### `mark <index>` - Marks a task as done

Marks a task in the list as done.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! You've completed this task: 
[T][X] read book
```

### `unmark <index>` - Marks a task as not done

Marks a task in the list as not done.

Example of usage:

`unmark 1`

Expected outcome:

```
Oh no! Try to complete this task ASAP: 
[T][] read book
```

### `delete <index>` - Deletes a task

Remove a task from your list.

Example of usage:

`delete 1`

Expected outcome:

```
Task eliminated: 
[T][] read book
Now you have 2 tasks in your list.
```

### `find <keyword>` - Search for specific task(s)

Searches for matching task(s) using a keyword.

Example of usage:

`find zoom`

Expected outcome:

```
Here are the task(s) you are looking for:
1. [E][] zoom meeting (at: Sep 19 2022 07:30:00)
```

### `bye` - Exits the app

Exits the application.

Example of usage:

`bye`

Expected outcome:

```
Keep moving forward until you finish all your tasks. Goodbye.
```