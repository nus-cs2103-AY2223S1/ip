# User Guide

## Features

### Feature - View all Tasks

Able to view all the tasks added to the todo list

### Feature - Add Tasks

Able to add a variety of tasks to the todo list.
This includes:
- todo
- deadline
- event

### Feature - Delete Tasks

Able to delete the tasks added to the todo list

### Feature - Completion

Able to mark a task as completed or incomplete

### Feature - Undo

Able to restore the list to the state in the previous command

### Feature - Searching

Able to filter tasks by searching for a keyword or date

### Feature - Exit

Able to exit the program
## Usage

---
### `list` - View all Tasks

The user is able to see all tasks on the right screen beside the main chat.

---
### `todo` - Add a task marked as todo

The `todo` command allows the user to add a task as todo.

Format: `todo {task to be added}`

Example of usage: `todo borrow book`

Expected output: 

Nice reply by Deku, followed by the task added.

```
Deku Responds:
I've added this task:
[T][] - hello
Now you have 1 task(s) in total.
```

---
### `deadline` - Add a task marked as deadline

The `deadline` command allows the user to add a task as deadline.
This command allows users to add a task combine with a date. Users
can then search tasks via dates.

Format: `deadline {task to be added} /by {date}`

Example of usage: `deadline return book /by 02/09/2022`

> **Note**
> 
> Supported date format:\
> dd/mm/yyyy\
> Other formats might not be processed properly and affect search results.

Expected output:

Nice reply by Deku, followed by the task added.
```
Deku Responds:
I've added this task:
[D][] - hello (by: 6 Sep 2022)
Now you have 1 task(s) in total.
```

---
### `event` - Add a task marked as an event

The `event` command allows the user to add a task as todo.

Format: `event {task to be added} /at {date}`

Example of usage: `event book club /at 03/09/2022`

> **Note**
> Supported date format:
> dd/mm/yyyy
> Other formats might not be processed properly and affect search results.

Expected output:

Nice reply by Deku, followed by the task added.
```
Deku Responds:
I've added this task:
[E][] - How is your day? (at 2-4pm)
Now you have 1 task(s) in total.
```

---
### `delete` - Deletes a task on the list

The `delete` command allows the user to delete a task.

Format: `delete {index}`

Example of usage: `delete 1`

Expected outputs:

1) Nice reply by Deku, followed by the task deleted.
   ```
   Deku Responds:
   Noted.
   [E][] - How is your day? (at 2-4pm)
   has been deleted
   ```
   
2) If the index is out of range, Deku will reply with annoyance
   ```
   Deku Responds:
   AUUUUUGH! There is not task: -1
   ```

---
### `mark` - Add a task marked as an event

The `mark` command allows the user to mark a task as completed.

Format: `mark {index}`

Example of usage: `mark 1`

Expected outputs:

1) Nice reply by Deku, followed by the task deleted.
   ```
   Deku Responds:
   Good Job! This task is now completed:
   [E][X] - How is your day? (at 2-4pm)
   ```
   
2) If the index is out of range, Deku will reply with annoyance
   ```
   Deku Responds:
   AUUUUUGH! There is not task: -1
   ```
   
---
### `unmark` - Add a task marked as an event

The `unmark` command allows the user to mark a task as incomplete.

Format: `unmark {index}`

Example of usage: `unmark 1`

Expected outputs:

1) Nice reply by Deku, followed by the task deleted.
   ```
   Deku Responds:
   This task is not yet to be done
   [E][] - How is your day? (at 2-4pm)
   ```
   
2) If the index is out of range, Deku will reply with annoyance
   ```
   Deku Responds:
   AUUUUUGH! There is not task: -1
   ```

---
### `undo` - undo the previous command

The `undo` command returns the list to the state before the previous command.

Format: `undo`

Example of usage: `undo`

Expected outputs:

1) Nice reply by Deku, confirming the undo.
   ```
   Deku Responds:
   I have undo-ed your last command
   ```
   
2) Deku cannot undo the command. This occurs for commands such as `find_word`, `undo` etc.
   ```
   Deku Responds:
   I cannot undo that!
   ```
   
---
### `find_word` & `find_date` - Find a task by a word or date

The `find_word` & `find_date` command allows the user to find tasks within the list based
on a word or a date.

Format: `find_word {word}`\
Example of usage: `find_word is`

Format: `find_date {date}`\
Example of usage: `find_date 02/02/2022`

> **Note**
> 
> Supported date format:\
> dd/mm/yyyy\
> Other formats might not return the correct results.

Expected output:

Nice reply by Deku, followed by a list of tasks that matches the search criteria.
```
Deku Responds:
Here are the tasks containing the word: day

1) [E][] - How is your day? (at 2-4pm)
```

---
### `bye` - Exit program

The `bye` command exits the program.

Format: `bye`

Example of usage: `bye`

Expected output:
Program closes
---