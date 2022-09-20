# User Guide

## Features

### Task List

You can keep track of your tasks, whether that would be todos, deadlines, or
events!

### Automatic Help

If you don't know how to use a command, writing that command by itself will
give you a brief explanation of its usage (except for list and sort).

### Marking Tasks

You can mark tasks as done or not done, which makes keeping track of your todos
and deadlines easier. You can also mark an event as done or not done, but I
don't know why you would ever want to do that.

### Automatic save and save files

Any action you do will be automatically saved. The save file is named
`.data.txt` and should be located in the same folder as your `smiley.jar`, so
remember to move `smiley.jar` along with `.data.txt` when you want your task
list to be saved. Also don't forget to delete this save file if you no longer
need it.

The save files are hidden from the user. To make them appear, go to
[this link for Mac users](https://www.pcmag.com/how-to/how-to-access-your-macs-hidden-files)
or
[this link for Windows users](https://support.microsoft.com/en-us/windows/show-hidden-files-0320fe58-0117-fd59-6851-9b7f9840fdb2).

### A friendly smile

The user interface is in the form of a chatting robot to hopefully brighten up
your day. The chatting robot also features a cute smiling picture to calm you
down.

The user, on the other hand, is the image of the creator of this application.
You can see this profile picture on every account of this person except for
LinkedIn.

## Usage

### `list` - Lists your tasks

The command will simply list all your tasks (todos, deadlines, and events).

Example of usage:

`list`

Expected outcome:

```
> Here are the tasks in your list.
  1. [E][X] The entire year of 2023 (from 2023/1/1 at 00:00:00 to 2023/12/31 at 23:59:59)
  2. [D][ ] Make a user guide for the iP for CS2103T (by 2022/9/19 at 23:59:59)
  3. [T][ ] Take out trash
```

### `sort` - Sorts and lists your tasks

The command will simply sort and list all your tasks (todos, deadlines, and
events). Todos will always come first. For events, the time that matters
depends on whether the event has started.

Example of usage:

`sort`

Expected outcome:

```
> Here are the tasks in your list, sorted by time.
  1. [T][ ] Take out trash
  2. [D][ ] Make a user guide for the iP for CS2103T (by 2022/9/19 at 23:59:59)
  3. [E][X] The entire year of 2023 (from 2023/1/1 at 00:00:00 to 2023/12/31 at 23:59:59)
```

### `todo` - Creates a todo type task

The command adds an item of todo type (marked with the letter T) to your list
of tasks. If your todo has a deadline, it is recommended to use a deadline
instead.

Example of usage:

`todo Take out trash`

expected outcome:

```
> Successfully added the following task
  [T][ ] Take out trash
  You now have 3 tasks in the list.
```

### `deadline` - Creates a deadline type task

The command adds an item of deadline type (marked with the letter D) to your
list of tasks. You need to specify a deadline for this type. The date must be
written just after `/by` and its format must follow the exact format requested.

Example of usage:

`deadline Make a user guide for the iP for CS2103T /by 2022/9/19 23:59:59`

Expected outcome:

```
> Successfully added the following task
  [D][ ] Make a user guide for the iP for CS2103T (by 2022/9/19 at 23:59:59)
  You now have 3 tasks in the list.
```

### `event` - Creates an event type task

The command adds an item of event type (marked with the letter E) to your
list of tasks. You need to specify a start time and an end time for this
type. The start time must be written just after `/from` and the end time,
`/to` and its format must follow the exact format requested.

Example of usage:

`event The entire year of 2023 /from 2023/1/1 00:00:00 /to 2023/12/31 23:59:59`

Expected outcome:

```
> Successfully added the following task
  [E][ ] The entire year of 2023 (from 2023/1/1 at 00:00:00 to 2023/12/31 at 23:59:59)
  You now have 3 tasks in the list.
```

### `mark` - Marks a task as completed

The command lets you mark a task as completed by its index.

Example of usage:

`mark 1`

Expected outcome:

```
> Ok, I'm marking this as done
  [E][X] The entire year of 2023 (from 2023/1/1 at 00:00:00 to 2023/12/31 at 23:59:59)
```

### `unmark` - Marks a task as not completed

The command lets you mark a task as not completed by its index.

Example of usage:

`unmark 1`

Expected outcome:

```
> Ok, I'm marking this as not done
  [E][ ] The entire year of 2023 (from 2023/1/1 at 00:00:00 to 2023/12/31 at 23:59:59)
```

### `delete` - Deletes a task

The command lets you delete a task by its index.

Example of usage:

`delete 1`

Expected outcome:

```
> Ok, I'm deleting this
  [E][ ] The entire year of 2023 (from 2023/1/1 at 00:00:00 to 2023/12/31 at 23:59:59)
```

### `filter` - Filters the task list by a phrase

The command lets you filter your tasks by a phrase. You can only filter by one phrase.

Example of usage:

`filter CS2103T`

Expected outcome:

```
> Here are the tasks in your list which matches the search phrase.
  1. [T][ ] CS2103T homework make a user guide.
  3. [T][ ] do the tP user stories, a CS2103T homework.
  50. [T][ ] test test CS2103T homework. 
```
