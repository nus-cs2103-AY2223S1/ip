# User Guide

Duke is a chatbot for managing tasks, optimized for use via a command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Features

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

### Add a todo task: `todo`

Adds a todo task to the task list.

Format: `todo DESCRIPTION`

Example of usage:

`todo read book`

Expected outcome:

```

Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.

```

### Add a deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by DD-MM-YYYY`

Example of usage:

`deadline return book /by 10-12-2022`

Expected outcome:

```

Got it. I've added this task:
  [D][ ] return book (by: 10 Dec 2022)
Now you have 1 task in the list.

```

### Add an event task: `event`

Adds an event task to the task list.

Format: `event DESCRIPTION /at TIME`

Example of usage:

`event lesson /at 2-4pm`

Expected outcome:

```

Got it. I've added this task:
  [E][ ] lesson (at: 2-4pm)
Now you have 1 task in the list.

```

### List all tasks: `list`

Shows all tasks currently in task list.

Format: `list`

Example of usage:

`list`

Expected outcome:

```

Here are the tasks in your list:
  1.[T][ ] read book
  2.[D][ ] return book (by: 10 Dec 2022)
  3.[E][ ] lesson (at: 2-4pm)

```

### Delete a task: `delete`

Deletes task at particular index.

Format: `delete INDEX`

Example of usage:

`delete 1`

Expected outcome:

```

Noted. I've removed this task::
  [T][X] read book
Now you have 2 tasks in the list.

```

### Mark task as done: `mark`

Marks task at particular index as done.

Format: `mark INDEX`

Example of usage:

`mark 1`

Expected outcome:

```

Nice! I've marked this task as done:
  [T][X] read book

```

### Mark task as undone: `unmark`

Marks task at particular index as not done.

Format: `unmark INDEX`

Example of usage:

`unmark 1`

Expected outcome:

```

OK, I've marked this task as not done yet:
  [T][ ] read book

```

### Search for tasks: `find`

Shows list of all tasks with descriptions containing keyword.

Format: `find KEYWORD`

Example of usage:

`find book`

Expected outcome:

```

Matching results:
  1.[T][X] read book
  2.[D][ ] return book (by: 10 Dec 2022)

```

### Tag a task: `tag`

Tags task at particular index with tag.

Format: `tag INDEX TAG`

Example of usage:

`tag 1 fun`

Expected outcome:

```

Got it. I've tagged this task:
  [T][X] read book #fun

```

### Untag a task: `untag`

Untags task at particular index.

Format: `untag INDEX`

Example of usage:

`untag 1`

Expected outcome:

```

Got it. I've untagged this task:
  [T][X] read book

```

### Exit chatbot: `bye`

Exits the chatbot.

Format: `bye`

Example of usage:

`bye`
