# User Guide

## Features

### Feature-Remembers **four** different types of items

1. Todo (a regular todo task)
2. Deadline (a deadline task due by a certain date)
3. Event (a event occurring at a certain date)
4. Note (a note for you to remember)

### Feature-**Backup** your task list locally

JennyBot will save your task list locally at "USER_HOME/.jenny/storage/tasks".

### Feature-Mark and unmark your tasks as **completed** or **uncompleted**

With the ability to mark or unmark tasks as completed or uncompleted, you can keep track your deliverables.

### Feature-**Finds** which tasks you are looking for

If your task list gets too long, you can search for a task by name.

## Usage

### `todo` - Add a todo task to your task list

Add a todo task to your task list. Your task list will be updated with the new todo task.

Example of usage:

`todo Buy a loaf of bread.`

Expected outcome:

Your task list will be updated with the new todo task.

```
Got it. I've added this task:
  [T][ ] Buy a loaf of bread.`
Now you have 1 tasks in the list.
```

### `deadline` - Add a deadline task to your task list

Add a deadline task to your task list. Your task list will be updated with the new deadline task.

Example of usage:

`deadline Submit assignment 1. /by 2022-09-12`

Expected outcome:

Your task list will be updated with the new deadline task.

```
Got it. I've added this task:
  [D][ ] Submit assignment 1. (by: 2022-09-12)`
Now you have 2 tasks in the list.
```

### `event` - Add a event task to your task list

Add a event task to your task list. Your task list will be updated with the new event task.

Example of usage:

`event Attend team meeting. /at 2022-09-12`

Expected outcome:

Your task list will be updated with the new event task.

```
Got it. I've added this task:
  [E][ ] Attend team meeting. (at: 2022-09-12)`
Now you have 3 tasks in the list.
```

### `note` - Add a note to your task list

Add a note to your task list. Your task list will be updated with the new note.

Example of usage:

`note Bob said hi to me today.`

Expected outcome:

Your task list will be updated with the new note.

```
Got it. I've added this note:
  [N][ ] Bob said hi to me today.`
Now you have 4 tasks in the list.
```

### `list` - List all the tasks in your task list

List all the tasks in your task list. Your task list will be shown to you.

Example of usage:

`list`

Expected outcome:

Your task list will be shown to you.

```
  [T][ ] Buy a loaf of bread.`
  [D][ ] Submit assignment 1. (by: 2022-09-12)`
  [E][ ] Attend team meeting. (at: 2022-09-12)`
  [N][ ] Bob said hi to me today.
```

### `mark` - Mark a task as complete

Mark a task as complete in your task list. Your task list will be updated with the new status.

Example of usage:

`mark 1`

Expected outcome:

Your task list will be updated with the new status.

```
Nice! I've marked this task as done:
  [T][X] Buy a loaf of bread.`
```

### `unmark` - Mark a task as incomplete

Unmark a task as complete in your task list. Your task list will be updated with the new status.

Example of usage:

`unmark 1`

Expected outcome:

Your task list will be updated with the new status.

```
OK, I've marked this task as not done yet:
  [T][ ] Buy a loaf of bread.`
```

### `delete` - Delete a task from your task list.

Delete a task from your task list. Your task list will be updated without the deleted task.

Example of usage:

`delete 2`

Expected outcome:

Your task list will be updated without the deleted task.

```
Got it. I've deleted this task:
  [D][ ] Submit assignment 1. (by: 2022-09-12)`
Now you have 3 tasks in the list.
```

### `find` - Find a task from your task list.

Find a task from your task list. Your found tasks will be shown to you.

Example of usage:

`find Bob`

Expected outcome:

Your found tasks will be shown to you.

```
[[N][ ] Bob said hi to me today.]
```

### `bye` - Exit the application

Exit the application. The window will be disabled, no input will be registered.

Example of usage:

`exit`

Expected outcome:

The window will be disabled, no input will be registered.

```
Bye Bye Forest!
```
