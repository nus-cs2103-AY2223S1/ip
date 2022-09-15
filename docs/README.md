# User Guide

## Features

1. Add `todos`, `deadlines` and `events`.
2. `list` all recorded items.
3. `mark` and `unmark` them as complete.
4. `delete` them when you wish.
5. `find` items easily using keywords

**Your data is saved everytime you make a change, so don't be afraid of closing Duke after you've added your tasks!**

## Usage

### Add `todo`

A `todo` `[T]` is the most basic item you can add, all it contains is a `description` of the task.
Usage:
`todo <description>`
Example of usage:
`todo wash the dishes`
Expected outcome:
A `todo` with the `description` of `wash the dishes` is added.
Expected output:

```
added:[T][] wash the dishes
```

### Add `deadline`

A `deadline` `[D]` is a `todo` that has a - you guessed it - `deadline` attached.
Usage:
`deadline <description> /by <dd/mm/yy>`
Example of usage:
`deadline CS2100 Quiz 55 /by 25/09/22`
Expected outcome:
A `deadline` with the `description` of `CS2100 Quiz 55` and `deadline` of `25 Sep 22` is added.
Expected output:

```
added:[D][] CS2100 Quiz 55 (by: 25 Sep 22)
```

### Add `event`

An `event` `[E]` has a `description` and `event date`.
Usage:
`event <description> /at <dd/mm/yy>`
Example of usage:
`event CS2100 Mid Term /at 03/10/22`
Expected outcome:
An `event` with the `description` of `CS2100 Mid Term` and `event date` of `03 Oct 22` is added.
Expected output:

```
added:[E][] CS2100 Mid Term (at: 03 Oct 22)
```

### `list` Tasks

List all items recorded by Duke.
Usage:
`list`
Expected output:

```
1. [T][] wash the dishes
2. [D][] CS2100 Quiz 55 (by: 25 Sep 22)
3. [E][] CS2100 Mid Term (at: 03 Oct 22)
```

### `mark` Tasks as done

Mark item(s) as done.
Usage:
`mark <item number>[, <item number>,...]`
Example of usage:
`mark 1, 2`
Expected outcome:
Marks the 1st and 2nd item as done.
Expected output:

```
I've marked these tasks as done:
[T][X] wash the dishes
[D][X] CS2100 Quiz 55 (by: 25 Sep 22)
```

### `unmark` Tasks as done

Marks items as not done yet.
Usage:
`unmark <item number>[, <item number>,...]`
Example of usage:
`unmark 1, 2>
Expected outcome:
Marks the 1st and 2nd item as not done.
**By default, new items are created added with not done status.**
Expected output:

```
I've marked these tasks as not done:
[T][] wash the dishes
[D][] CS2100 Quiz 55 (by: 25 Sep 22)
```

### `delete` Tasks/Deadlines/Events

Delete items that are complete or that you do not want Duke to record anymore.
Usage:
`delete <item number>[, <item number>,...]`
Example of usage:
`delete 2`
Expected outcome:
Deletes the 2nd item from Duke.
Expected output:

```
I've removed this task:
[D][X] CS2100 Quiz 55 (by: 25 Sep 22)
```

### `find` Tasks

Find an entry using a keyword
Usage:
`find <keyword>`
Example of usage:
`find Quiz`
Expected outcome:
Returns a list of records that contain the word `Quiz`
Expected output:

```
Here are the matching tasks in your list:
2. [D][] CS2100 Quiz 55 (by: 25 Sep 22)
```
