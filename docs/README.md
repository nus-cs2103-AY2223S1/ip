# Duke User Guide
>"Everything has beauty, but not everyone sees it." — Confucius

Whichever needs to be done, whenever the time.
Duke is a **simple** and **easy-to-use** Java app to track your tasks on the go.

![Duke Ui Screenshot](/docs/Ui.png)

## Task-Tracking Features 
- Find
- List
- Mark
- Unmark
- Delete

### Supported Task Types
-[X] Todo
-[X] Deadline
-[X] Event

## Usage

### `todo`

Add a Task of type *todo* to your task list

Example of usage: 

`todo borrow book on clouds`

Example outcome:

```
Got it. I've added this task:
    [T][ ] borrow book on clouds
Now you have 1 tasks in the list.
```  
### `deadline`

Add a Task of type *deadline* to your task list

Due dates can be added to deadline tasks using the [YYYYMMDD] format (no spacing inbetween)

Example of usage:

`deadline get good/20220917`

Example outcome:

```
Got it. I've added this task:
    [D][ ] get good/20220917
Now you have 2 tasks in the list.
```
### `event`

Add a Task of type *event* to your task list

Event details can be added to event tasks

Example of usage:

`event mid autumn /tomorrow night at the moon`

Example outcome:

```
Got it. I've added this task:
    [E][ ] event mid autumn /tomorrow night at the moon
Now you have 3 tasks in the list.
```

### `find`

Find all tasks containing a given search term

Example of usage:

`find good`

Example outcome:

```
1. [D][ ] get good/20220917
```

### `list`

Display all current tasks

Example of usage:

`list`

Example outcome:

```
1. [T][ ] borrow book on clouds
2. [D][ ] get good/20220917
3. [E][ ] mid autumn /tomorrow night at the moon
```

### `mark`

Declare a task at a given position on list as complete

Example of usage:

'mark 1'`

Example outcome:

```
Marked task as done
[T][X] borrow book on clouds
```

### `unmark`

Declare a task at a given position on list as incomplete

Example of usage:

'unmark 1'`

Example outcome:

```
Marked task as not done
[T][ ] borrow book on clouds
```

### `delete`

Remove a task from a given position on list

Example of usage:

'delete 1'`

Example outcome:

```
Noted. I've removed this task:
    [T][ ] borrow book on clouds
Now you have 2 tasks in the list.
```
### `bye`
Close application window

Example of usage:

`bye`

## Notes
- Ensure spacing between keyword and body of command
- All keywords are case-insensitive

Icons used in Duke obtained from [Freepik](https://www.flaticon.com/authors/freepik)
