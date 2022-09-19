# User Guide

## Meet Baymax, your personalized task management bot!

Baymax keeps tracks of all your day-to-day tasks, and also helps you to keep track of task completion,
assign priorities to tasks and also find your tasks from the list.

## Features

- [x] List all tasks.
- [x] Add a ToDo task.
- [x] Add an Event.
- [x] Add a Deadline.
- [x] Mark/unmark a task as done/ undone.
- [x] Delete a task.
- [x] Find a task by name.
- [x] Assign priority to a task.

## Usage

### list

Lists all the tasks in your current task list, with their type, completion status and priority.

Notes:

- Each item in the list has a general format: `ItemNo.[Task type][Completion status][Priority] description`
- For priority, 1 shows high priority and 0 shows low priority.

For example

```
1.[T][][0] read book
2.[E][X][0] party (at: 5pm)
3.[D][][1] project (by: 2022-03-04)
```

### Adding a task to the list

#### Adding a ToDo task

To add a todo task (task with no time limitation) to the list, use the following command:

```
todo <task-description>
```

Example:

```
todo Read Book
```

#### Adding an Event

To add an event that occurs at a specific time, use the command:

```
event <task-description> /at <time>
```

Example:

```
event project meeting /at 5pm
```

#### Adding a Deadline

To add a deadline which must be completed by a given date, use the command:

```
deadline <task-description> /by <date>
```

The date must be in YYYY-MM-DD format.

Example:

```
deadline report submission /by 2022-03-04
```

### Marking a task as done/undone

To mark a task as done use the command:

```
mark <item-number>
```

To un-mark a task you have previously marked as done, use the command:

```
unmark <item-number>
```

### Deleting a task

To delete a task from the list, use the command:

```
delete <item-number>
```

### Finding a task

To find all tasks that contain a specific keyword, use the command:

```
find <keyword>
```

### Assign priority to a task

To assign a task as high/low priority, use the command:

```
priority <item-number> <priority>
```

Notes:

- use "high" for high priority and "low" to remove priority
- only high and low priority is supported

Example:

```
priority 3 high
```

### End Session

To end the session, simply use the `bye` command! BayMax will store all your tasks locally,
and load them the next time you open BayMax.
