# User Guide for DukeMaxPro

## Features 

### Add Tasks

Add up to 3 different types of tasks!

[todo]

A todo task denoted by [T]

input format: "todo" + task description

[deadline]

A deadline task denoted by [D]

A deadline task with a deadline to be input, denoted by [D]

Input format: "deadline" + task description + "/by" + deadline

[Event]

An event task with a time of event, denoted by [E]

Input format: "event" + task description + "/at" + time of event

### Mark / Unmark tasks as done

Marks tasks as done with an "X" or undone without an "X" and with an empty box [ ]
to keep track of task completion.

### View task list

Displays the entire task list

### Detect duplicates

Detects duplicate tasks inside the task lists and alerts the user about which 
task is duplicated and how many tasks are duplicated


## Usage

### `list` - Displays current task list


Example of usage: 

`list`

Expected outcome:

```
[T][X] Borrow book
[D][ ] Return Book (by: Monday 2pm)
[E][ ] Meeting (at: Monday 2pm-4pm)
```

### `todo` - Adds a todo task to the task list


Example of usage: 

`todo`


Expected outcome:

```
[T][X] Borrow book
```

### `deadline` - Adds a deadline task to the task list


Example of usage: 

`deadline`


Expected outcome:

```
[D][ ] Return Book (by: Monday 2pm)
```

### `event` - Adds a event task to the task list


Example of usage: 

`event`


Expected outcome:

```
[E][ ] Meeting (at: Monday 2pm-4pm)
```

### `mark` - Marks a task as done in the task list


Example of usage: 

`mark 1`


Expected outcome:

```
[E][X] Meeting (at: Monday 2pm-4pm)
```

### `unmark` - Unarks a task as not done in the task list


Example of usage: 

`unmark 1`


Expected outcome:

```
[E][ ] Meeting (at: Monday 2pm-4pm)
```






