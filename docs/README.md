# User Guide

## Features 

### Add/delete tasks

You can ask catBot to add or delete todo, deadline or event tasks in the tasklist.

### Mark tasks

You can mark or unmark tasks in your tasklist as completed.

### Find tasks

catBot can help you to find tasks from your tasklist that contain the word you are looking for.

### Display tasklist

catBot can show you your current tasklist, with all the tasks added thus far.

### Save your tasklist

catBot automatically saves your tasklist everytime you make changes to it.

## Usage

### `todo` - creates a todo in the tasklist

Todos are tasks that you have to do but do not have a specified date of completion.

Example of usage: 

`todo {description of task}`

Example input:

`todo read book`

Expected outcome:

```
Roger nya! Added this task:
  [T][] read book
Now you have 1 task(s) in your list nya
```
<br>

### `deadline` - creates a deadline in the tasklist

Deadlines are tasks which have to be completed by a certain date.

Example of usage:

`deadline {description of task} /by {Date to be completed by}(in YYYY-MM-DD format)`

Example input:

`deadline math homework /by 2022-10-10`

Expected outcome:

```
Roger nya! Added this task:
  [D][] math homework (by: OCT-10-2022)
Now you have 1 task(s) in your list nya
```

<br>

### `event` - creates an event in the tasklist

Events are tasks which occur at a certain date.

Example of usage:

`event {description of task} /at {Date to be completed by}(in YYYY-MM-DD format)`

Example input:

`event party /at 2022-10-09`

Expected outcome:

```
Roger nya! Added this task:
  [E][] math homework (at: OCT-9-2022)
Now you have 1 task(s) in your list nya
```

<br>

### `mark` - marks a task in the tasklist

Allows you to mark a specified task in the tasklist based on its number in the list

Example of usage:

`mark {task number}`

Example input:

`mark 1`

Expected outcome:

```
I've marked this task as done. Great job nya!
```

<br>

### `unmark` - unmarks a task in the tasklist

Allows you to unmark a specified task in the tasklist based on its number in the list

Example of usage:

`unmark {task number}`

Example input:

`unmark 1`

Expected outcome:

```
Roger nya! I've marked this task as not done.
```

<br>

### `delete` - deletes a task in the tasklist

Allows you to delete a specified task in the tasklist based on its number in the list

Example of usage:

`delete {task number}`

Example input:

`delete 1`

Expected outcome:

```
Roger nya! I've removed this task:
  [T][] read book
Now you have 2 tasks(s) left in the list.
```

<br>

### `list` - displays the current tasklist with all tasks added

Shows you the list of tasks that have been added thus far

Example of usage:

`list`

Example input:

`list`

Expected outcome:

```
Here are the tasks in your list nya:
1.[T][] read book
```

<br>

### `find` - finds specific tasks based on name

Shows you a filtered list of tasks with descriptions containing the input string.

Example of usage:

`find {String to filter description by}`

Example input:

`find read`

Expected outcome:

```
Here are matching tasks in your list nya:
1.[T][] read book
```

<br>

### `bye` - exits catBot

closes the application.

Example of usage:

`bye`

Expected outcome:

Application closes.


