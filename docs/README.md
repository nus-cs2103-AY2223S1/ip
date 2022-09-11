# User Guide

## Features 

### Add a Task to your Duke!

Add a Todo Task, Event or Deadline to your duke to keep track of all your Tasks so that 
you will never forget your commitments again!

### Search for Tasks

Searching for your tasks has never been this easy. Forgot a deadline? simply search a keyword
and a list of similar tasks will be shown

&nbsp;

## Usage

### `todo` - Adds a Todo task to your Task List

The simplest form of a task

Example of usage: 

`todo buy groceries`

Expected outcome:

New task 'buy groceries' will be displayed in your task list'

```
Got it. I've added this task:
    [T][] buy groceries
Now you have 1 task(s) in the list
```

&nbsp;

### `event` - Adds a event task to your Task List

Adds a new event with a date to your task list

Example of usage:

`event family dinner /at 2020-08-08`

Expected outcome:

New event 'family dinner' will be displayed in your task list'

```
Got it. I've added this task:
    [E][] family dinner (at Aug 8 2020)
Now you have 1 task(s) in the list
```

&nbsp;

### `deadline` - Adds a deadline task to your Task List

Adds a new deadline with a date to your task list

Example of usage:

`deadline sign up for internship /by 2020-08-08`

Expected outcome:

New event 'sign up for internship' will be displayed in your task list'

```
Got it. I've added this task:
    [D][] sign up for internship (at Aug 8 2020)
Now you have 1 task(s) in the list
```

&nbsp;

### `list` - Displays your current task list

Displays your current task list

Example of usage:

`list`

Expected outcome:

```
1.[E][] family dinner (at Aug 8 2020)
2.[T][] buy groceries
3.[D][] sign up for internship (at 8 Aug 2020)
```

&nbsp;

### `delete` - Deletes a task

Delete the task from your task list at the specified index

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
    [E][] family dinner (at Aug 8 2020)
Now you have 2 task(s) in the list.
```

&nbsp;

### `mark` - Marks a task

Mark the task as done from your task list at the specified index

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [E][X] family dinner (at Aug 8 2020)
```

&nbsp;

### `unmark` - Unmarks a task

Unmark the task from your task list at the specified index

Example of usage:

`unmark 1`

Expected outcome:

```
OK! I've marked this task as not done yet:
    [E][] family dinner (at Aug 8 2020)
```

&nbsp;

### `find` - Finds a task

find a task with the specified keyword

Example of usage:

`find buy`

Expected outcome:

```
1.[E][] buy concert tickets (at Aug 8 2020)
2.[T][] buy groceries
3.[D][] contact buyers (by Sep 9 2021)
```
&nbsp;

### `update` - Updates a task

update a task by the specified category

Format: 
update {index} {category} {detail}

Example of usage:

`update 1 description buy concert ticket`

Expected outcome:

```
OK, I've updated this task:
    [E][] buy concert ticket (at Aug 8 2020)
```

&nbsp;

### `bye` - Exits duke

Exits the program

Example of usage:

`bye`

