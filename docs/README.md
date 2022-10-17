# User Guide

## Features 

### Creating Tasks

You can create three different types of tasks: todos, events and deadlines!
- Deadlines need to be completed by a certain date / time
- Events need to be completed at certain date / time
- Todos are non-time sensitive

### Marking Tasks as Complete
When you've done your task, tell Hazell that you've done it!

## Usage

### `todo` - Create a todo

Creates a todo and add it to your list of tasks.

Example of usage:
`todo buy milk at Utown FairPrice`

A new todo will be created and saved.
```
Got it. I've added this task:
    [T][ ] buy milk at Utown FairPrice
```
### `event` - Create an event

Creates an event and add it to your list of tasks.

Example of usage:
`event CS2103T team meeting /at Wednesday 11pm`

A new event will be created and saved.
```
Got it. I've added this task:
    [E][ ] CS2103T team meeting (at: Wednesday 11pm)
```

### `deadline` - Create a deadline

Creates a deadline and add it to your list of tasks.

Example of usage: 
`deadline CS2105 assignment /by 2022-01-01`

A new deadline will be created and saved.
```
Got it. I've added this deadline:
    [D][ ] CS2105 assignment (by: Jan 1 2022)
```

### `list` - Be reminded of all your tasks

Show all your tasks, both done and undone

Example of usage:
`list`

```
Here are the tasks in your list:
    1. [D][ ] CS2105 assignment (by: Oct 1 2022)
    2. [E][ ] CS2109 tutorial (at: Oct 3 2022)
```

### `mark` - Mark a task as done

Mark a task as done.

Example of usage:
`mark 1`

```
Nice! I've marked this task as done:
    [D][X] CS2105 assignment (by: Oct 1 2022)
```

### `unmark` - Mark a task as undone

Mark a task as undone.

Example of usage:
`unmark 1`

```
OK, I've marked this task as not done yet:
    [D][ ] CS2105 assignment (by: Oct 1 2022)
```

### `postpone` - Postpone a task

Postpone / change the time of a task.
Requires task to be time-sensitive, i.e. only applicable to events and deadlines.

Example of usage:
`postpone 1 2022-12-31`

```
OK, I've postponed this task:
    [D][ ] CS2105 assignment (by: Dec 31 2022)
```
