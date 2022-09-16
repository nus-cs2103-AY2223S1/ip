# User Guide
Zeus is a chat-bot like **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 
## Features 
### Adds a todo - `todo <description>`
Adds a simple todo task without any date or time.

Example of usage: 

`todo Feed cat`

Expected outcome:

Adds the todo to the task list.

```
Very well, your task has been added:
    [T][] Feed cat 
Now you have 1 tasks in the list.
```

### Adds a deadline - `deadline <description> /by <date>`

Adds a deadline task that has to be done before a specific date. 
If date provided is in specific format (`YYYY-MM-DD HHmm`), it will be parsed;
otherwise, the date is treated as a  description.

**Format 1:**

`deadline <description> /by <YYYY-MM-DD HHmm>`

Example of usage:

`deadline math assignment /by 2022-09-08 2359`

Expected outcome:

Adds the deadline to the task list, with the datetime parsed to a more readable format.

```
Very well, your task has been added:
    [D][] math assignment (by: Sep 8 2022 2359) 
Now you have 2 tasks in the list.
```

**Format 2:** 

`deadline <description> /by <date description>`

Example of usage:

`deadline math assignment /by Friday 16 Sept 2022 11.59pm`

Expected outcome:

Adds the deadline to the task list, with the datetime treated 
as a description.
```
Very well, your task has been added:
    [D][] math assignment (by: Friday 16 Sept 2022 11.59pm) 
Now you have 2 tasks in the list.
```

### Adds an event - `event <description> /at <date>`

Adds an event that happens on a certain date. 
If date provided is in specific format (`YYYY-MM-DD HHmm`), it will be parsed;
otherwise, the date is treated as a description.

**Format 1:** 

`event <description> /at <time description>` 

Example of usage: 

`event cs2103T tutorial /at Wed 9-10am`

Expected outcome:

Adds the event to the task list, with the datetime treated as a string.

```
Very well, your task has been added:
    [E][] cs2103T tutorial (at: Wed 9-10am) 
Now you have 3 tasks in the list.
```

**Format 2:** 

`event <description> /at <YYYY-MM-DD HHmm>`

Example of usage: 

`event CCA fair /at 2022-08-22 1600`

Expected outcome:

Adds the event to the task list, with the datetime parsed to a more readable format.

```
Very well, your task has been added:
    [E][] CCA fair (at: Aug 22 2022 1600) 
Now you have 3 tasks in the list.
```

### Lists all tasks - `list`

List all tasks currently in the task list.

Example of usage:

`list`

Expected outcome:

Shows the current saved tasks in the list.

```
1. [T][] Feed cat
2. [D][X] Math assignment (by: Oct 10 2022 2359)
3. [E][] CS2103T tutorial (at: Wed 9-10am)
```

### Marks a task - `mark <task number>`

Marks a task as done using its index in the task list. 

Example of usage:

`mark 3`

Expected outcome:

Marks the 3rd task in the task list as done 
as indicated with a 'X' in the box.

```
Excellent! I've marked this task as done:
    [E][X] CS2103T tutorial (at: Wed 9-10am)
```

### Unmarks a task - `unmark <task number>`

Marks a task as not done using its index in the task list.

Example of usage:

`unmark 3`

Expected outcome:

Marks the 3rd task in the task list as not done
as indicated with the absence of 'X' in the box.

```
OK, I've marked this task as not done yet:
    [E][] CS2103T tutorial (at: Wed 9-10am)
```

### Deletes a task - `delete <task number>`

Deletes a task according to its index in the task list.

Example of usage:

`delete 3`

Expected outcome:

Deletes the 3rd task in the task list.

```
Very well, I've removed this task:
    [E][] CS2103T tutorial (at: Wed 9-10am)
Now you have 2 tasks in the list.
```

### Finds the tasks with keyword - `find <keyword>`

Lists all tasks that contain the given keyword.

Example of usage:

`find cat`

Expected outcome:

Lists all the tasks that contain the keyword 'cat'.
```
These are the matching tasks in your list:
    1. [T][] Feed cat
```
### Undo the last command - `undo`

Example of usage:

Assume the last user input was `mark 1`.

`undo`

Expected outcome:

Undoes the command to mark the 1st task in the list as done.

```
Your last command has been undone!
These are the tasks in your list:
    1. [T][] Feed cat
    2. [D][X] Math assignment (by: Oct 10 2022 2359)
```

### Terminate the program - `bye`

Terminates the program and prompts a goodbye message from Zeus.
Any subsequent user inputs will close the application automatically.
