# User Guide

Duke is a simple desktop application for keeping track of tasks.

## Features 

### Task management

Duke allows you to add, delete, view, find, as well as mark or unmark tasks as done.

### Graphical User Interface (GUI)

The graphical interface is a simple and intuitive way for you to interact with Duke.

### Commands

All interactions will be done through commands, with support for short form commands. If you like typing, Duke will be 
very easy to use.

### Data storage

All your application data will be saved into a text file, and you do not have to worry about losing data when using 
Duke.

## Usage

### `help` - View the list of commands

Lists all available user commands.

Usage: `help`/`h`

Expected outcome:
```
> help

Here are the list of commands:
1. h / help
2. b / bye
3. l / list
4. m / mark <task index>
5. u / unmark <task index>
6. t / todo <task description>
7. e / event <event description> /at <date>
8. d / deadline <task description> /by <date>
9. del / delete <task index>
10. f / find <keyword>
```

### `bye` - Exit Duke

Exits the application.

Usage: `bye`/`b`

Expected outcome:
```
> bye

Bye. Hope to see you again soon!
```
The GUI window will close.

### `list` - List all tasks

Shows all tasks you currently have.

Usage: `list`/`l`

Expected outcome:
```
> list

1.[D][ ] return book (by: 20 Sep 2022)
2.[T][X] read book
```

### `mark` - Mark a task as done

Marks a task as done.

Usage: `mark <task index>`/`m <task index>`

Expected outcome:
```
> mark 1

Nice! I've marked this task as done:
[D][X] return book (by: 10 Sep 2022)
```

### `unmark` - Unmark a task as done

Marks a task as not done.

Usage: `unmark <task index>`/`u <task index>`

Expected outcome:
```
> unmark 1

Ok, I've marked this task as not done yet:
[D][ ] return book (by: 10 Sep 2022)
```

### `todo` - Add a new todo task

Adds a new todo task.

Usage: `todo <task description>`/`t <task description>`

Expected outcome:
```
> todo go home

Got it. I've added this task:
[T][ ] go home
Now you have 4 tasks in the list.
```

### `event` - Add a new event task

Adds a new event task.

Usage: `event <task description> /at <date>`/`e <task description> /at <date>`

Expected outcome:
```
> event go school /at 2022-09-12

Got it. I've added this task:
[E][ ] go school (at: 12 Sep 2022)
Now you have 5 tasks in the list.
```

### `event` - Add a new deadline task

Adds a new task with a deadline.

Usage: `deadline <task description> /by <date>`/`d <task description> /by <date>`

Expected outcome:
```
> deadline do homework /by 2022-09-15

Got it. I've added this task:
[D][ ] do homework (by: 15 Sep 2022)
Now you have 6 tasks in the list.
```

### `delete` - Delete a task

Deletes a task from Duke.

Usage: `delete <task index>`/`del <task index>`

Expected outcome:
```
> delete 6

Noted. I've removed this task:
[D][ ] do homework (by: 15 Sep 2022)
Now you have 5 tasks in the list.
```

### `find` - Find a task

Finds all tasks using the given keyword.

Usage: `find <keyword>`/`f <keyword>`

Expected outcome:
```
> find book

Tasks found with keyword: book
1.[D][ ] return book (by: 10 Sep 2022)
2.[T][X] read book
3.[T][ ] buy book
```
