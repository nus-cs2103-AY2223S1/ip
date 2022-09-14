# User Guide

![Ui.png](./Ui.png)

## Features 

### Tracking To-dos

Keep track of what tasks you have to do, with the ability to mark each to-dos as done. 

### Tracking events and deadlines

Keep track of events and deadlines, with support for dates and the ability to mark each event or
deadline as done.

### Note-taking

Take notes on the go!

## Usage

### `todo (description)`

Adds a to-do to your list.

Example of usage: 

`todo Buy milk and cereal`

Expected outcome:

```
Got it, I've added this task:
    [T][ ] Buy milk and cereal
Now you have 1 task in the list.
```

### `event (description) /on (date in YYYY-MM-DD format)`

Adds an event to your list.

Example of usage: 

`event GEC1028 mid-terms /on 2022-10-04`

Expected outcome:

```
Got it, I've added this task:
    [E][ ] GEC1028 mid-terms (on 2022-10-04)
Now you have 2 tasks in the list.
```

### `deadline (description) /on (date in YYYY-MM-DD format)`

Adds a deadline to your list.

Example of usage: 

`deadline Submit final version of CS2103T iP /by 2022-09-16`

Expected outcome:

```
Got it, I've added this task:
    [D][ ] Submit final version of CS2103T iP (by 2022-09-16)
Now you have 3 tasks in the list.
```

### `note (description)`

Adds a note to your list.

Example of usage: 

`note Software Architecture shows the overall organisation of the system.`

Expected outcome:

```
Got it, I've added this note:
    Software Architecture shows the overall organisation of the system.
```

### `mark (index of task)`

Mark a task as done.

Example of usage: 

`mark 1`

Expected outcome:

```
Nice! I've marked task 1 as done:
    [T][X] Buy milk and cereal
```

### `unmark (index of task)`

Mark a task as undone.

Example of usage: 

`unmark 1`

Expected outcome:

```
Ok! I've marked task 1 as not done yet:
    [T][ ] Buy milk and cereal
```

### `delete (index of task/note)`

Deletes a task or note

Example of usage: 

`delete N1`

Expected outcome:

```
Noted. I've removed this note:
Contents of the notes here.
Now you have 2 notes in the list.
```

### `find (what to find)`

Searches through your descriptions of to-dos, events, deadlines, as well as the contents of your
notes for the text you specify.

Example of usage: 

`find CS2103T`

Expected outcome:

```
Here are the tasks in your list:
1. [D][ ] Submit final version of CS2103T iP (by 2022-09-16)

No notes found containing "CS2103T".
```

### `list`

Displays everything on your list so far.

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] Buy milk and cereal
2. [E][ ] GE1028 mid-terms (on 2022-10-04)
3. [D][ ] Submit final version of CS2103T iP (by 2022-09-16)

Here are your notes:
N1. Software Architecture shows the overall organisation of the system.
```

### `bye`

Saves everything in your list and quit the program.
