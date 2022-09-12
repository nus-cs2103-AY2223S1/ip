# User Guide

## Features 

### Feature - Add Tasks

Able to add a variety of tasks to the todo list. 
This includes:
- todo 
- deadline
- event

### Feature - View all Tasks

Able to view all the tasks added to the todo list

### Feature - Delete Tasks

Able to delete the tasks added to the todo list

### Feature - Exit

Able to exit the program

### Feature - Completion

Able to mark a task as completed or incomplete

### Feature - Undo

Able to restore the list to the state in the previous command

### Feature - Searching

Able to filter tasks by searching for a keyword or date

## Usage

### `list` - View all Tasks

The user is able to see all tasks on the right screen beside the main chat.

### `todo` - Add a task marked as todo

The `todo` command allows the user to add a task as todo.

Format: `todo {task to be added}`

Example of usage: `todo borrow book`

Expected output: 
Nice reply by Deku, followed by the task added and number of tasks in list.
```
Deku Responds:
I've added this task:
[T][] - hello
Now you have 1 task(s) in total.
```

### `deadline` - Add a task marked as deadline

The `deadline` command allows the user to add a task as deadline.
This command allows users to add a task combine with a date. Users
can then search tasks via dates.

Format: `deadline {task to be added} /by {date}`

Example of usage: `deadline return book /by 02/09/2022`
>[!note]
> Supported date format:
> dd/mm/yyyy
> Other formats might not be processed properly and affect search results.

Expected output:
Nice reply by Deku, followed by the task added and number of tasks in list.
```
Deku Responds:
I've added this task:
[D][] - hello (by: 6 Sep 2022)
Now you have 1 task(s) in total.
```

### `event` - Add a task marked as an event

The `event` command allows the user to add a task as todo.

Format: `event {task to be added} /at {date}`

Example of usage: `event book club /at 03/09/2022`

Expected output:
Nice reply by Deku, followed by the task added and number of tasks in list.
```
Deku Responds:
I've added this task:
[E][] - How is your day? (at 2-4pm)
Now you have 1 task(s) in total.
```