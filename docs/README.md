#Duke User Guide

## Features 
1. Track various types of tasks
2. View all the tasks you want to complete
3. Mark/unmark your tasks once done
4. Delete you tasks if not needed anymore
5. Find your tasks in your list 
6. Postpone any task with a deadline

## Usage

### `todo` - Add a todo task

A todo task will be added to your list.

Example of usage: 

`todo borrow book`

Expected outcome:

```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 tasks in the list.
```

### `deadline` - Add a deadline task

A deadline task will be added to your list.

Example of usage:

`deadline finish homework /by 2022-12-12 `

Expected outcome:

```
Got it. I've added this task:
[D][ ] finish homework (by: 12 December 2022)
Now you have 2 tasks in the list.
```
### `event` - Add a event task

A event task will be added to your list.

Example of usage:

`event geralds birthday /at 2022-04-14 `

Expected outcome:

```
Got it. I've added this task:
[E][ ] geralds birthday (at: 14 April 2022)
Now you have 3 tasks in the list.
```
### `list` - List all tasks

All the tasks in your list will be displayed

Example of usage:

`list`

Expected outcome:

```
1. [T][ ] borrow book
2. [D][ ] finish homework (by: 12 December 2022)
3. [E][ ] geralds birthday (at: 14 April 2022)
```
### `mark` - Mark a task

Mark a task as completed

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[X] borrow book 
```

### `delete` - Delete a task

Delete a task from your list

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
[D][ ] finish homework (by: 12 December 2022)
Now you have 2 tasks in your list.
```

### `find` - Find a task

Find a task with the given keyword

Example of usage:

`find birthday`

Expected outcome:

```
Here are the matching tasks in your list:
1. [E][ ] geralds birthday (at: 14 April 2022)
```

### `postpone` - Postpone a task

Postpone a deadline or event

Example of usage:

`postpone 2 2022-04-15`

Expected outcome:

```
I've changed the date of this task:
[E][ ] geralds birthday (at: 15 April 2022)
```