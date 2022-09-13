# User Guide

## Features 

### Adding a To-do task:`todo`

Adds a to-do task to duke.

Format: `todo {TASK_DESCRIPTION}`

Example:
`todo read book`

Expected outcome:

```
Got it. I've added this task:
[T][] read book
Now you have 1 tasks in the list.
```

### Adding a deadline task:`deadline`

Adds a deadline task to duke.

Format: `deadline {TASK_DESCRIPTION} /by {DATE}`

Date is in yyyy-mm-dd format.

Example:
`deadline return book /by 2022-09-16`

Expected outcome:

```
Got it. I've added this task:
[D][] return book (by:Sep 16 2022)
Now you have 2 tasks in the list.
```

### Adding an event task:`event`

Adds an event task to duke.

Format: `event {TASK_DESCRIPTION} /at {DATE}`

Date is in yyyy-mm-dd format.

Example:
`event conference /at 2022-09-19`

Expected outcome:

```
Got it. I've added this task:
[E][] conference (by:Sep 19 2022)
Now you have 3 tasks in the list.
```

### Listing all tasks:`list`

List all tasks in duke.

Format: `list`

Example:
`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][] read book
2.[D][] return book (by:Sep 16 2022)
3.[E][] conference (by:Sep 19 2022)
```

### Marks a task as done:`mark`

Marks a task as done in duke.

Format: `mark {INDEX}`

Example:
`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] read book
```

### Unmarks a task as not done:`unmark`

Unmarks a task as not done in duke.

Format: `unmark {INDEX}`

Example:
`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][] read book
```
### Deletes a task:`delete`

Deletes a task from duke.

Format: `delete {INDEX}`

Example:
`delete 1`

Expected outcome:

```
Noted, I've removed this task:
[T][] read book
Now you have 2 tasks in the list.
```

### Finds tasks:`find`

Finds tasks from duke.

Format: `find {KEYWORD}`

Example:
`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1.[D][] return book(by:Sep 16 2022)
```

### Make aliases for commands:`friendly`

Make aliases for commands in duke.
So that you can customise your command names.

Format: `friendly {ORIGINAL_COMMAND} {ALIAS}`

Example:
`friendly list ls`

Expected outcome:

```
Add alias: ls for list
```

### Delete aliases for commands:`unfriendly`

Delete aliases for commands in duke.

Format: `unfriendly {ALIAS}`

Example:
`unfriendly ls`

Expected outcome:

```
Delete alias: ls
```
