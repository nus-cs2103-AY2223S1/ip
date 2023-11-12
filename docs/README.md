# User Guide
Rabbit is a short-tempered girl who helps you manage your task as her part-time job. 

## Features 

### Manage your tasks

Do you have a busy schedule? Fret not. Despite her reluctance, Rabbit is here to help because she really needs pocket money to buy cattor tea. With simple command, Rabbit helps you add, delete and edit your tasks.  

tasks appear in this format:

`[][]the content (the time)`
- `the first[]` the type of the task, which can be T, D and E, indicating todo, deadline and event respectively
- `the second[]` whether the task is done. If it is, the second bracket is [X], otherwise it is []
- `the content` the content of the task
- `the time` this is only applicable for tasks of types deadline and event

Examples of tasks:
`[T][X]do laundry`
This is a task of type todo, and is done.
`[D][]write essay by 2012-12-12 12:00`
This is a task of type deadline. It is not done, and the deadline is 2012 12 December 12:00.

### Talk to Rabbit

What is better than provoking Rabbit when she's already upset about the tons of work? Plus, you can even change her look from the six looks at random.

## Usage

There are three types of tasks which you can manage: todo, deadline and event. You can manage the tasks that are finished as done, and mark them as not done.

### `todo` - Add a task(todo)

Format:
`todo` `the content`
This creates a task of type todo.

Example of usage: 

`todo do laundry`

### `deadline` - Add a task(deadline)

Format: 
`deadline` `the content` `/the time`
This creates a task of type deadline.

Note that the time must be in the form: yyyy-mm-dd-hhmm

Example of usage:

`deadline write essay /2012-12-12-1200`

### `event` - Add a task(event)

Format: 
`event` `the content` `/the time`
This creates a task of type event.

Note that the time must be in the form: yyyy-mm-dd-hhmm

Example of usage:

`event watch concert /2012-12-12-1200`

### `delete` - Delete a task

Format:
`delete` `index of the task`

Example of usage:

`delete 1`

This deletes the first task in the list.

### `mark` - Mark a task as done

Format:
`mark` `index of the task`

Example of usage:

`mark 1`

### `unmark` - Mark a task as not done

Format:
`unmark` `index of the task`

Example of usage:

`unmark 1`

The task must have been marked as done before it is marked as not done.
Every task is marked as not done by default.

### `list` - List out all the tasks

Example of usage:
`list`

Expected outcome:

```
1.[T][]do laundry
2.[D][]write essay by 2012-12-12-1200
3.[E][]watch concert at 2012-12-12-1200
```

This lists out all the tasks in the list.

### `find` - Find tasks that match the specified keywords

Format:
`find` `the keywords`

Example of usage:
`find do laundry`

Expected outcome:
`1.[T][]do laundry`

### `edit` - Edit the content ot the time of a specified task

Format:
- `edit content` `the index of the task` `the new content`
- `edit time` `the index of the task` `the new time`

Example of usage:
`edit content 1 do homework`
`edit time 2 2012-12-13-1300`

Note that edit time is only applicable for tasks of types deadline and event.

### `change` - Change Rabbit's look

Format:
`change`

Rabbit will pick a look at random from the six existing looks.
