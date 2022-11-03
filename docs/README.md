# User Guide

## Features 

### Tasklist

Add todo, deadline and event-based tasks to your tasklist. Mark as done or delete tasks whenever you feel so.

### Organise

Find tasks by their descriptions or deadlines. 

### Joke

Jokebear will tell you a random joke. 

## Usage

### `todo` - Create todo task

Create simple todo task with description.

Example of usage: 

`todo Return book`

Expected outcome:

Adds todo task to list.

```
[T][✘] Return book
```

### `deadline` - Create deadline task

Create task with description and deadline.

Example of usage:

`deadline Return book 12/12/2022`

Expected outcome:

Adds deadline task to list.

```
[D][✘] Return book (by: 12/12/2022 0000)
```

### `event` - Create event task

Create event task with description and deadline.

Example of usage:

`event Book fair 12/12/2022`

Expected outcome:

Adds event task to list.

```
[E][✘] Book fair (at: 12/12/2022)
```

### `done` - Mark task as done

Marks task specified by index as done.

Example of usage:

`done 1`

Expected outcome:

Changes done status of task.

```
[T][✓] Return book
```

### `undone` - Mark task as done

Marks task specified by index as not done.

Example of usage:

`undone 1`

Expected outcome:

Changes done status of task.

```
[T][✘] Return book
```

### `find` - Finds task with matching description

Finds and returns all tasks containing given description.

Example of usage:

`find book`

Expected outcome:

```
1. [T][✘] Return book
2. [E][✘] Throw book (at: 12/12/2022)
```

### `before` - Finds task before given deadline

Finds and returns all tasks before given deadline.

Example of usage:

`before 1/01/2023`

Expected outcome:

```
1. [E][✘] Throw book (at: 12/12/2022)
```

### `delete` - Delete task

Delete task with specified index.

Example of usage:

`delete 1`

Expected outcome:

Task at index 1 is deleted.

### `list` - Display full tasklist

Finds and returns all tasks before given deadline.

Example of usage:

`before 1/01/2023`

Expected outcome:

```
Think you are free?
1. [E][✘] Book fair (at: 12/12/2022)
2. [D][✘] Return book (by: 12/12/2022)
3. [T][✘] Throw book 
```

### `joke` - Generate random joke

Generates random joke from database.

Example of usage:

`joke`

### `help` - Display list of commands

Click on the help button in the GUI to display a list of commands.

### `clear` - Clear chat box

Click on the clear button to clear and refresh the chat box.

### `bye` - Exit

Type 'bye' to exit and then type any key to terminate the programme. 