# Duke Task Manager User Guide

## Features 

| Feature                                                 | description                               |
|---------------------------------------------------------|-------------------------------------------|
 | [Create Todo](#todo---create-a-todo-task)               | Add a todo task to the task list.         |
 | [Create Event](#event---create-an-event)                | Add an event to the task list.            |
 | [Create Deadline](#deadline---create-a-deadline)        | Add a deadline to the task list.          |
 | [List Tasks](#list---list-all-tasks)                    | List all tasks in the task list.          |
 | [Find Tasks](#find---find-a-task)                       | Find tasks matching description.          |
 | [Complete task](#mark---mark-a-task-as-completed)       | Mark a task as completed.                 |
| [Uncomplete task](#unmark---mark-a-task-as-uncompleted) | Mark a task as uncompleted.               |
 | [Delete Unwanted Tasks](#delete---delete-a-task)        | Delete unwanted tasks from the task list. |
 | [Exit program](#bye---exit-the-program)                 | Exit the program.                         |

## Usage

### `todo` - Create a todo task

Create a todo task with a description.

Example of usage: 

`todo task description`

Expected outcome:

```
Got it. I've added this todo:
    [T][] task description
Now you have 1 task in your list.
```
### `event` - Create an event

Create an event with a name and description.

Example of usage:

`event name /at details`

Expected outcome:

```
Got it. I've added this event:
    [E][] name (at: details)
Now you have 2 tasks in your list.
```

### `deadline` - Create a deadline

Create a deadline with a name and datetime.

Example of usage:

`deadline name /by 13/01/2022 23:59`

Expected outcome:

```
Got it. I've added this deadline:
    [D][] name (by: Jan 13 2022)
Now you have 3 tasks in your list.
```

### `list` - List all tasks

List all tasks in the task list.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
  1. [T][] task description
  2. [E][] name (at: details)
  3. [D][] name (by: Jan 13 2022)
```

### `mark` - Mark a task as completed

Mark a task as completed.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
  [T][X] task description
```

### `unmark` - Mark a task as uncompleted

Mark a task as uncompleted.

Example of usage:

`unmark 1`

Expected outcome:

```
Nice! I've marked this task as not done yet:
  [T][] task description
```

### `find` - Find a task

Find a task from the task list.

Example of usage:

`find task`

Expected outcome:

```
Here are the matching tasks in your list:
  1. [T][] task description
```

### `delete` - Delete a task

Delete a task from the task list.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
  [T][] task description
Now you have 2 tasks in your list
```

### `bye` - Exit the program

Exit the program.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

