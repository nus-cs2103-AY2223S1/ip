# JustinBot User Guide

## Features

### Add tasks

Ever feel like you tend to forget things? You can add tasks in JustinBot to make sure you *never* forget them again!

### List out what tasks you have

You can see what upcoming tasks you have through JustinBot!

### Mark tasks as done/undone

You can mark tasks as done whenever you are finished with them. Likewise, you can make them as undone as you wish!

### Remove tasks

You can remove tasks that are not important anymore!

### Find tasks

You can find tasks that contain certain keywords!

## Usage

### `todo <task>`

Add a new ToDo task that is to be completed in the near future

Example of usage:

`todo homework`

Expected outcome:

Description of the outcome.

```
Got it, I have added the following into the list:

T | Undone | homework
You now have 1 tasks in your list
```

### `deadline <task> /by <date> <time>`

Add a new Deadline task that is to be completed before a certain time and date.

Example of usage:

`deadline assignment /by 2022-10-11 22:00'

Expected outcome:

```
Got it, I have aded the following into the list:

D | Undone | assignment | Oct 11 2022 1000PM
You now have 2 tasks in your list
```

### `event <task> /at <date> <time>`

Add a new Event that is to happen at that particular time.

Example of usage:

`event meeting /at 2022-10-12 10:00`

Expected outcome:

```
Got it, I have added the following into the list:

E | Undone | meeting | Oct 12 2022 1000AM
You now have 3 tasks in your list
```

### `list`

Lists out all the upcoming tasks.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:

1. T | Undone | homework
2. D | Undone | assignment | Oct 11 2022 1000PM
3. E | Undone | meeting | Oct 12 2022 1000AM
```

### `mark <index>`

Mark a particular task as done.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I have marked the following task(s) as done:

1. T | Done! | homework
```

### `unmark <index>`

Mark a particular task as undone.

Example of usage:

`unmark 1`

Expected outcome:

```
Sure, I've marked the following task(s) as undone:

1. T | Undone | homework
```

### `delete <index>`

Remove a particular task from the list.

Example of usage:

`delete 1`

Expected outcome:

```
OK, I have removed the following task(s) from the list:

T | Undone | homework
You now have 2 tasks in your list
```

### `find <keywords>`

Find tasks that contains certain keywords.

Example of usage:

`find assign`

Expected outcome:

```
Here are the matching tasks in your list:

1. D | Undone | assignment | Oct 11 2022 1000PM
```