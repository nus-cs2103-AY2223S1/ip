# User Guide

## Features 

### Task List
Chick Bot features a task list which helps us keep track of and manage our tasks.

**Functionalities:**
1. Adding and removing tasks
2. Marking and unmarking tasks as done
3. Searching for tasks with keyword
4. Displaying of task list
5. Loading and saving of task list
6. Preventing duplicate tasks

**Commands available:**
1. deadline
2. event
3. todo
4. mark
5. unmark
6. delete
7. find
8. list
9. bye
10. help

## Usage

Chick Bot can be used with the following 10 commands.

### 1. `deadline`

Add a deadline task to the task list managed by Chick Bot.

*Format*: `deadline {task_name} \by {yyyy-MM-dd}`

**Example of usage:**

`deadline ip \by 2022-09-16`

**Expected outcome:**

Adds the deadline task to task list.

```
Added:
[D][ ] ip (by: Sep-16-2022)
```

### 2. `event`

Add an event task to the task list managed by Chick Bot.

*Format*: `event {task_name} \at {yyyy-MM-dd}`

**Example of usage:**

`event project meeting \at 2022-09-21`

**Expected outcome:**

Adds the event task to task list.

```
Added:
[E][ ] project meeting (at: Sep-21-2022)
```

### 3. `todo`

Add a todo task to the task list managed by Chick Bot.

*Format*: `todo {task_name}`

**Example of usage:**

`todo sample todo task`

**Expected outcome:**

Adds the todo task to task list.

```
Added:
[T][ ] sample todo task
```

### 4. `mark`

Marks a task in the task list as done.

*Format*: `mark {task_index}`

**Example of usage:**

`mark 1`

**Expected outcome:**

Marks the task at the index as done.

```
Marked:
[T][X] sample todo task
```

### 5. `unmark`

Marks a task in the task list as not done.

*Format*: `unmark {task_index}`

**Example of usage:**

`unmark 1`

**Expected outcome:**

Marks the task at the index as not done.

```
Unmarked:
[T][ ] sample todo task
```

### 6. `delete`

Removes a task in the task list.

*Format*: `delete {task_index}`

**Example of usage:**

`delete 1`

**Expected outcome:**

Removes the task at the index.

```
Removed:
[T][ ] sample todo task
```

### 7. `find`

Search for tasks given by a keyword and displays them.

*Format*: `find {keyword}`

**Example of usage:**

`find sample`

**Expected outcome:**

Display tasks containing "sample".

```
Here:
[T][ ] sample todo task
```

### 8. `list`

Displays all tasks in the task list.

*Format*: `list`

**Example of usage:**

`list`

**Expected outcome:**

Display all tasks.

```
Here:
1. [T][ ] sample todo task
```

### 9. `bye`

Exits Chick Bot and saves task list.

*Format*: `bye`

**Example of usage:**

`bye`

**Expected outcome:**

Exit and save task list.

```
bye
```

### 10. `help`

Displays a list of commands available for Chick Bot.

*Format*: `help`

**Example of usage:**

`help`

**Expected outcome:**

Display list of available commands.

```
Commands:
deadline {task_name} /by {YYYY-mm-dd}
event {task_name} /at {YYYY-mm-dd}
todo {task_name}
mark {task_index}
unmark {task_index}
delete {task_index}
find {keyword}
list
bye
help
```