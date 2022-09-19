# User Guide

PAL chatbot is a **desktop app for managing your daily tasks**, 
optimized for use via a **Command Line Interface** (CLI) while 
still having the benefits of a Graphical User Interface (GUI).

## Features

### `exit` - Exiting the program

### `help` - Showing instructions
Shows all commands together with a short description of each command.

### `todo` - Adding ToDos
Adds a todo task to the list.

Format: `todo CONTENT`

Example of usage: `todo borrow books`
```
Got it, I've added this task:
[T][ ] borrow books
Now you have 1 task(s) in the list.
```

### `deadline` - Adding Deadlines
Adds a deadline task to the list.

Format: `deadline CONTENT /by YYYY-MM-DD`

Example of usage: `deadline cs2103t iP /by 2022-09-19`
```
Got it, I've added this task:
[D][ ] cs2130t iP (by: 19 Sep 2022)
Now you have 2 task(s) in the list.
```

### `event` - Adding Events
Adds an event task to the list.

Format: `event CONTENT /at YYYY-MM-DD HHMM`

Example of usage: `event book fair /at 2022-10-10 1500`
```
Got it, I've added this task:
[E][ ] book fair (at: 10 Oct 2022 3:00PM)
Now you have 3 task(s) in the list.
```
### `list` - Listing tasks
Displays all the tasks that were added.

Example of usage: `list`
```
Here are the tasks in your list:
1. [T][ ] borrow books
2. [D][ ] cs2130t iP (by: 19 Sep 2022)
3. [E][ ] book fair (at: 10 Oct 2022 3:00PM)
```

### `mark` - Marking tasks
Marks the specified task as done.

Format: `mark INDEX`
- Marks the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer**

Example of usage: `mark 2`
```
Nice! I've marked this task as done:
[D][X] cs2130t iP (by: 19 Sep 2022)
```

### `unmark` - Unmarking tasks
Marks the specified task as undone.

Format: `unmark INDEX`
- Unmarks the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer**

Example of usage: `unmark 2`
```
OK, I've marked this task as not done yet:
[D][ ] cs2130t iP (by: 19 Sep 2022)
```

### `delete` - Deleting tasks
Deletes the specified task.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer**

Example of usage: `delete 2`
```
Noted I've removed this task:
[D][X] cs2130t iP (by: 19 Sep 2022)
Now you have 2 task(s) in the list.
```

### `sort` - Sorting the task list
Sorts the task list according to type of task followed by date and time.
- The order: Events > Deadlines > ToDos
- Within each type of task, it will sort in ascending order

Example of usage: `sort`
```
1. [E][ ] book fair (at: 10 Oct 2022 3:00PM)
2. [E][ ] meeting (at: 10 Oct 2022 8:00PM)
3. [D][ ] cs2130t iP (by: 19 Sep 2022)
4. [D][ ] presentation script draft (by: 20 Oct 2022)
5. [T][ ] borrow books
6. [T][ ] house chores
7. [T][ ] read book
```
### `find` - Finding tasks
Searches for tasks using a keyword.

Format: `find KEYWORD`

Example of usage: `find book`
```
Here are the matching tasks in your list:
1. [T][ ] borrow books
2. [T][ ] read book
3. [E][ ] book fair (at: 10 Oct 2022 3:00PM)
```



