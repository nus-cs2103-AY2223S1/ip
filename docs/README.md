# Duke User Guide

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes))

Duke is a chatbot that helps you manage your day to day tasks.

## Features 

### Task Types
1. _ToDo_ - A simple task with only a description.
2. _Deadline_ - A task which has a due date that can be specified.
3. _Event_ - An event with a date and time that can be specified.

### Marking and Unmarking
- Mark tasks as complete with the `mark` command.
- Mark tasks as incomplete with the `unmark` command.

### Deleting
Delete tasks with the `delete <index>` command.

### Tagging
Tag tasks with the `tag <index> <tag>` command.

### Finding
Find tasks with the `find <keyword>` command.

## Usage
Duke creates, loads data from, and stores data to a file `Duke.txt` in
the same location as where Duke was run. Deleting this file will result
in the loss of all data stored there previously.

The first word of your user input will be the command passed to Duke.
Subsequent parameters will be separated by spaces (e.g., `tag <param1> <param2>`).
When parameters can have spaces, parameters will be separated by `/`
(e.g., `deadline <param1 has spaces> /by <param2 has spaces>`)

If there are any errors encountered during running, Duke will print
error messages that inform you how to modify your input to continue
using Duke effectively.

### `todo` - Adds a todo task

Creates a todo task with a description and adds it to Duke's list.

**Example of usage:**

`todo borrow book`

**Expected outcome:**
```
______________________________________________________
Got it. I've added this task:
[T][ ] borrow book
______________________________________________________
```

**Description of the outcome.**

Duke prints `[T]` to indicate that it is a todo and `[ ]` to indicate
that the task is incomplete. The rest of the output is the description
from the user input.

**Parameters**

`/d` - The description of the todo.

### `deadline` - Adds a deadline task

Creates a deadline task with a description and due date, and adds it to Duke's list.

**Example of usage:**

`deadline return book /by 2022-09-20 1200`

**Expected outcome:**
```
______________________________________________________
Got it. I've added this task:
[D][ ] return book (by: Tue, 20th Sep 2022, 12:00)
______________________________________________________
```

**Description of the outcome.**

Duke prints `[D]` to indicate that it is a deadline and `[ ]` to indicate
that the task is incomplete. This is then followed by the description
as per the user input. `by:` indicates the due date of the deadline task
in the format `EE, dd MMM yyyy, HH:mm`.

**Parameters**

`/d` - The description of the deadline task.

`/by` - The due date of the deadline task in the format `yyyy-MM-dd HHmm`

### `event` - Adds an event

Creates an event with a description, with the date and time that it will
occur, and adds it to Duke's list.

**Example of usage:**

`event book discussion /at 2022-09-21 1000`

**Expected outcome:**
```
______________________________________________________
Got it. I've added this task:
[E][ ] return book (by: Wed, 21th Sep 2022, 10:00)
______________________________________________________
```

**Description of the outcome.**

Duke prints `[E]` to indicate that it is an event and `[ ]` to indicate
that the task is incomplete. This is then followed by the description
as per the user input. `at:` indicates the date and time that the event
will take place in the format `EE, dd MMM yyyy, HH:mm`.

**Parameters**

`/d` - The description of the deadline task.

`/at` - The date and time that the event will take place in the format `yyyy-MM-dd HHmm`

### `list` - Prints all tasks

Prints all tasks that Duke is aware of.

**Example of usage:**

`list`

**Expected outcome:**
```
______________________________________________________
here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Tue, 20th Sep 2022, 12:00)
3. [E][ ] return book (by: Wed, 21th Sep 2022, 10:00)
______________________________________________________
```

**Description of the outcome.**

Duke prints out all the tasks in the same format as during the tasks'
creation, and orders them according to when they were created.

### `mark / unmark` - Mark tasks as complete or incomplete

Marks tasks as complete or incomplete by specifying their index number.

**Example of usage:**

- `mark 1`
- `unmark 1`

**Expected outcome:**
```
______________________________________________________
Nice! I've marked this task as done:
[T][X] borrow book
______________________________________________________
```
```
______________________________________________________
OK, I've marked this task as not done yet:
[T][ ] borrow book
______________________________________________________
```

**Description of the outcome.**

- `[X]` indicates that the task is complete.
- `[ ]` indicates that it is incomplete.

**Parameters**

`/i` - The index of the task as indicated by the number beside it when calling
`list`.

### `delete` - Delete tasks

Delete tasks by specifying their index number.

**Example of usage:**

`delete 1`

**Expected outcome:**
```
______________________________________________________
Noted. I've removed this task:
[T][ ] borrow book
______________________________________________________
```

**Description of the outcome.**

The description of the task that has been removed is printed.

**Parameters**

`/i` - The index of the task as indicated by the number beside it when calling
`list`.

### `tag` - Add tags to tasks

Add tags to help the user classify tasks.

**Example of usage:**

- `tag 3 friends`
- `tag 3 academics`

**Expected outcome:**
```
______________________________________________________
Alright, I've tagged this task:
[E][ ] book discussion [#friends]
______________________________________________________
```
```
______________________________________________________
Alright, I've tagged this task:
[E][ ] book discussion [#academics, #friends]
______________________________________________________
```

**Description of the outcome.**

- Duke prints out the task and the new tag.
- Duke prints out the task, the old tags, and the new tag.

**Parameters**

`/i` - The index of the task to be tagged as per the list when calling
`list`.

`/t` - The tag that Duke will add to the specified task.

### `find` - Find tasks with keywords

Find tasks that have descriptions or tags that contain the specified
keyword.

**Example of usage:**

- `find friends`
- `find discussion`

**Expected outcome:**
```
______________________________________________________
Here are the matching tasks in your list:
1. [T][X] borrow book [#friends]
2. [E][ ] book discussion [#friends, #academics]
______________________________________________________
```
```
______________________________________________________
Here are the matching tasks in your list:
1. [E][ ] book discussion [#friends, #academics]
______________________________________________________
```

**Description of the outcome.**

- Duke printed out these two tasks as their tags contained `#friends`.
- Duke printed out this task as its description contained the word `discussion`.

**Parameters**

`/k` - The keyword that Duke will search for in the tasks.
