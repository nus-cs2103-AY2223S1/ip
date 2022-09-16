# User Guide

Welcome to Dukegg, your _final_ task management application. Dukegg is a **desktop app for managing tasks, optimised for
use via a Command Line Interface (CLI) while still having the benefits of a GUI**. If you can type fast, Dukegg can help
you manage your tasks **_faster_** than traditional GUI apps.

<div style="text-align: center;">
    <img src="./Ui.png" style="max-width: 800px; " alt="Screenshot of Dukegg"/>
</div>

## Features

### Create tasks

Dukegg allows you to create 3 different types of tasks:

1. Todos
2. Events
3. Deadlines

Each task type allows you to store different information.

#### Todos

Todos are tasks that have a description and completion status.

#### Deadlines

Deadlines are tasks that have a description, completion status, and a due date.

#### Events

Events are tasks that have a description, completion status, and an at time.

### Tag tasks

Dukegg allows you to tag your tasks, to easily differentiate between different tasks. All task types can be tagged.

### Delete tasks

Dukegg also allows you to delete your tasks whenever you wish to.

### View tasks

Dukegg allows you to see all your tasks in a readable format, i.e. in a numbered list.

## Usage

### `help` - Get list of available commands

Prints the list of available commands and the syntax for each command.

Example of usage:

`help`

Expected outcome:

```
bye
deadline DESCRIPTION /by DEADLINE
delete TASK_NUMBER
event DESCRIPTION /at TIMING
find DESCRIPTION
help
list
mark TASK_NUMBER
tag DESCRIPTION TASK_NUMBER
todo DESCRIPTION
unmark TASK_NUMBER
untag TASK_NUMBER
```

### `list` - Get all tasks

Retrieves all the tasks in the current task list.

Command format:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[D] [ ] sample description (by: Jan 01 2022 00:00)
2.[E] [ ] sample event (at: Jan 01 2022 00:00)
```

### `todo` - Add todo

Add new Todo to current task list.

Command format:

`todo DESCRIPTION`

Example of usage:

`todo sample todo`

Expected outcome:

```
Got it. I've added this task:
  [T] [ ] sample todo
Now you have 4 task(s) in the list.
```

### `deadline` - Add deadline

Add new deadline to current task list.

Command format:

`deadline DESCRIPTION /by DEADLINE`
> 💡 DEADLINE should be in the format `YYYY-MM-DD HH:MM`

Example of usage:

`deadline sample description /by 2022-01-01 00:00`

Expected outcome:

```
Got it. I've added this task:
  [D] [ ] sample description (by: Jan 01 2022 00:00)
Now you have 3 task(s) in the list.
```

### `event` - Add event

Add new event to current task list.

Command format:

`event DESCRIPTION /at TIME`
> 💡 TIME should be in the format `YYYY-MM-DD HH:MM`

Example of usage:

`event sample event /at 2022-01-01 00:00`

Expected outcome:

```
Got it. I've added this task:
  [E] [ ] sample event (at: Jan 01 2022 00:00)
Now you have 3 task(s) in the list.
```

### `delete` - Delete task

Deletes the task at the given index. The index is 1-indexed and references the number next to the task when `list`
command is run. An error is thrown if there is no task at the index given.

Command format:

`delete TASK_NUMBER`

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
  [D] [ ] [#potato] asdsd (by: Jan 01 2022 00:00)
Now you have 2 task(s) in the list.
```

### `find` - Find task

Find tasks based on input. A list of tasks with descriptions that match the input will be shown.

Command format:

`find DESCRIPTION`

Example of usage:

`find sample`

Expected outcome:

```
Here are the matching tasks in your list:
1.[D] [ ] sample description (by: Jan 01 2022 00:00)
2.[E] [ ] sample event (at: Jan 01 2022 00:00)
```

### `mark` - Marks a task as completed

Marks a task at the given index to be completed. The index is 1-indexed and references the number next to the task
when `list`command is run. An error is thrown if there is no task at the index given. Command format:

`mark TASK_NUMBER`

Example of usage:

`mark 2`

Expected outcome:

```
Nice! I've marked this task as done:
  [D] [X] sample description (by: Jan 01 2022 00:00)
```

### `unmark` - Marks a task as not done

Sets the task at the specified index to be not done. The index is 1-indexed and references the number next to the task
when `list` command is run. An error is thrown if there is no task at the index given.

Command format:

`unmark TASK_NUMBER`

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
  [D] [ ] [#newtag] sample description (by: Jan 01 2022 00:00)
```

### `tag` - Tags a task

Tags a task with some text. The index is 1-indexed and references the number next to the task when `list`
command is run. An error is thrown if there is no task at the index given.

Command format:

`tag DESCRIPTION TASK_NUMBER`

Example of usage:

`tag newtag 2`

Expected outcome:

```
OK, I've tagged this task:
  [D] [X] [#newtag] sample description (by: Jan 01 2022 00:00)
```

### `untag` - Removes the tag for a task

Removes the tag for the task at the specified index. The index is 1-indexed and references the number next to the task
when `list` command is run. An error is thrown if there is no task at the index given.

Command format:

`untag TASK_NUMBER`

Example of usage:

`untag 1`

Expected outcome:

```
OK, I've untagged this task:
  [D] [ ] sample description (by: Jan 01 2022 00:00)
```

### `bye` - Exit the program

Exits the program and saves the current task list into storage. This task list will be loaded once Dukegg is opened
again.

Command format:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon! (Application closing in 2 seconds)
```
