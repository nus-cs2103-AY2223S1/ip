# User Guide

- [User Guide](#user-guide)
    - [Features](#features)
        - [Manage your tasks effortless](#manage-your-tasks-effortless)
    - [Usage](#usage)
        - [`help` - Show Guide](#help---show-guide)
        - [`list` - List all tasks](#list---list-all-tasks)
        - [`todo` - Create a todo](#todo---create-a-todo)
        - [`event` - Create an event](#event---create-an-event)
        - [`deadline` - Create a deadline](#deadline---create-a-deadline)
        - [`mark` - To mark a task as done](#mark---to-mark-a-task-as-done)
        - [`unmark` - To unmark a task](#unmark---to-unmark-a-task)
        - [`delete` - To delete a task](#delete---to-delete-a-task)\

## Features

### Manage your tasks effortless

Never forgetting your tasks anymore. With Ted, he will help you remember all the things you need to do. 
In addition, it can manage different types of tasks such as todo, event and deadline. 

## Usage

### `help` - Show Guide

Show how-to guide.

Expected outcome:

```
usage: <command> [<args>] 
list      list out all the tasks

These are commands to be used for creating various types of task:
todo      create a simplest task
event     create an event with /at operator (event hello world /at 2022-03-12)
deadline  create a deadline tasks with /by operator (deadline /by yyyy-mm-dd)

These are commands to be used for modifying the task list:
mark      mark task as completed
unmark    mark task as incomplete
delete    delete a task from task list"
```

### `list` - List all tasks

List all out the created tasks.

Example of usage:

`list`

Expected outcome:

It will display the type of task accordingly:
- `[T]`: Todo
- `[E]`: Event
- `[D]`: Deadline

The following `[ ]` indicate incomplete or `[X]` indicate completed task.

```
1.[T][ ] hello
```

### `todo` - Create a todo

Create a todo.

Example of usage:

`todo CS2103T Revision`

Expected outcome:

```
Got it. I've added this task:
[T][ ] CS2103T Revision
Now you have 4 tasks in the list.
```

### `event` - Create an event

Create an event

Example usage:

`event meeting /at 9pm`

Expected outcome:

```
Got it. I've added this task:
[E][ ] meeting (at: 9pm)
Now you have 4 tasks in the list.
```

### `deadline` - Create a deadline

Create a task with deadline

`deadline <title> /by <date>`

Date is in `yyyy-MM-dd` format or you can supply `today` or `tomorrow`.

Example usage:

`deadline homework /by 2022-10-12`

Expected outcome:

```
Got it. I've added this task:
[D][ ] homework (by: 22/10/2022)
Now you have 4 tasks in the list.
```

### `mark` - To mark a task as done

Mark a task as done.

`mark <number>`

Example usage:

`mark 1`

Expected outcome:

```
Marked task 1 as done.
```

### `unmark` - To unmark a task

Mark a task as incomplete.

Example usage:

`unmark 1`

Expected outcome:

```
Marked task 1 as incomplete.
```

### `delete` - To delete a task

Delete a task from current task list.

`delete <number>`

Example usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][ ] hello
Now you have 7 tasks in the list.
```




