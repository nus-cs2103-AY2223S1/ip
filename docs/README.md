# Duke: User Guide

## Quick start
1. Ensure you have Java 11 or above installed.

2. Download the latest duke.jar from [here](https://github.com/jamietan2002/ip/releases/tag/Level-10).

3. Copy the file to the folder you want to use as the home folder for Duke.

4. Double-click the file to start the app.

5. Enter a command in the command box to execute it.

## Features 

### Display list

Displays list of all tasks.

Format: `list`

Expected outcome: 
```
Here are the tasks in your list:
1. [E][ ] attend lecture (at: Sep 9 2022) 
2. [T][ ] assignment 1
3. [D][ ] submit tutorial (by: Sep 15 2022)
```

### Add a Todo task

Adds a Todo task to the list

Format: `todo <description>`

Example: 
```
todo assignment 1
```

Expected outcome:
```
Got it. I've added this task:
[T][] assignment 1
Now you have 2 tasks in the list.
```

### Add a Deadline task

Adds a deadline task to the list

Format: `deadline <description> /by <date>`

Example:
```
deadline submit tutorial /by 2022-09-15
```

Expected outcome:
```
Got it. I've added this task:
[D][] submit tutorial (by: Sep 15 2022)
Now you have 3 tasks in the list.
```
### Add an Event task

Adds an event task to the list

Format: `event <description> /at <date>`

Example:
```
event attend lecture /at 2022-09-09
```

Expected outcome:
```
Got it. I've added this task:
[E][] attend lecture (by: Sep 9 2022)
Now you have 4 tasks in the list.
```

### Mark task 

Marks task in the list as done 

Format: `mark <task number>`

Example:
```
mark 2
```

Expected outcome:
```
Nice! I've marked this task as done:
[T][X] assignment 1
```

### Unmark task

Marks task in the list as not done

Format: `unmark <task number>`

Example:
```
unmark 2
```

Expected outcome:
```
OK, I've marked this task as not done yet:
[T][] assignment 1
```

### Find task(s)

Finds all task(s) with description containing the keyword(s)

Format: `find <keywords>`

Example:
```
find assignment
```

Expected outcome:
```
Here are the matching tasks in your list:
[T][] assignment 1
```

### Delete task

Deletes a task in the list

Format: `delete <task_number>`

Example:
```
delete 2
```

Expected outcome:
```
Noted. I've removed this task:
[T][] assignment 1
Now you have 3 tasks in the list.
```
### Find all Todo/ Event/ Deadline tasks

Lists all todo/ event/ deadline tasks

Format: `all <task category>`

Example:
```
all todo
```

Expected outcome:
```
Here are the TODOs in your list:
[T][] assignment 1
```

### Exit the program

Exits the program

Format: `bye`

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
