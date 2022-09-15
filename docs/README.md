# User Guide

Duke is a task-management application that uses command line input.

## Quick Start
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest release from [here](https://github.com/leeianee/ip).
3. Double-click the file to start the application.
4. Or run `java -jar duke.jar` at the directory that the jar file is located.

## Usage

### `list` - Lists all tasks

Lists all the tasks stored.

Example of usage: 

`list`

Expected outcome:

Displays all tasks currently stored in the application.

```
1.[D][ ] Task 1 (at: January 1st)
2.[E][X] Task 2 (at: Aug 6th 2-4pm)
3.[T][ ] Task 3
```

### `todo` - Adds a todo task

Adds a todo task.

Example of usage:

`todo Task`

Expected outcome:

Adds a todo task named `Task`.

```
Got it. I've added this task:
    [T][ ] Task
```

### `deadline` - Adds a deadline task

Adds a deadline task.

Example of usage:

`deadline Task /by Date`

Expected outcome:

Adds a deadline task named `Task`, with deadline `Date`.

```
Got it. I've added this task:
    [D][ ] Task (at: Date)
```

### `event` - Adds an event task

Adds an event task.

Example of usage:

`event Task /at Date`

Expected outcome:

Adds an event task named `Task`, that occurs at `Date`.

```
Got it. I've added this task:
    [E][ ] Task (at: Date)
```

### `mark` - Marks a task as done

Marks a specified task as done.

Example of usage:

`mark 1`

Expected outcome:

Marks the first task as done.

```
Nice! I've marked thish task as done:
    [T][X] Task
```

### `unmark` - Marks a task as not done

Marks a specified task as not done.

Example of usage:

`unmark 1`

Expected outcome:

Marks the first task as not done.

```
Ok, I've marked this task as not done yet:
    [T][ ] Task
```

### `delete` - Deletes a task

Delete a specifed task.

Example of usage:

`delete 1`

Expected outcome:

Deletes the first task.

```
Noted. I've removed this task:
    [T][ ] Task
```

### `find` - Finds tasks containing a keyword

Lists all the tasks stored containing the specified keyword.

Example of usage:

`find Task 1`

Expected outcome:

Displays all tasks currently stored in the application containing the keyword.

```
Here are the tasks in your list:
1.[T][ ] Task 1
2.[T][ ] Task 10
3.[T][ ] Task 11
```

### `undo` - Undo the previous command.

Undoes the last command that affects the tasks stored.

Example of usage:

```
todo Task
undo
```

Expected outcome:

the new `todo` task `Task` is deleted.

```
Got it. I've added this task:
    [T][ ] Task

Noted. I've removed this task:
    [T][ ] Task
```

### `bye` - Exits the program.

Displays an exit message and stops the program.

Example of usage:

`bye`

Expected outcome:

Displays the exit message and stops the program.

```
Bye. Hope to see you again soon!
```