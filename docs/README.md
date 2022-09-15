# Duke User Guide
Duke is a graphical task manager that helps you manage your tasks, through a command line.
## Features 

### Type of tasks
- `todo`
- `event`
- `deadline`
- `expense`

Users can create the following type of tasks inside their Duke Task tracker.
### Viewing of all tasks
Users can view all their tasks.
- Code CS2103T
- Code CS2100
- Play Game
### Deletion of tasks
Users can delete their tasks.
- ~~Code CS2103T~~
### Marking of tasks as done 

Users can mark and unmark their tasks.
- [ ] Code CS2103T



### Finding of tasks
Users can find tasks that match a certain name.
- <mark>Code</mark> CS2103T
- <mark>Code</mark> CS2100

## Usage

### `help` - Helps the user on a specified command.
Displays the format of the specified command.

Format: `help <OPTIONAL: command_name>`

Example of usage: 

- `help deadline`

Expected outcome:
```
Adds a new deadline
Format: deadline <name> /by <yyyy-MM-dd HHmm>
```

### `list` - Lists Tasks.
List all existing tasks to the user.

Example of usage: 

- `list`

```
Here is the list of all your tasks!
1. [TD][] eat
2. [DL][] exam (by: 2022-09-14 1200)
3. [EV][] party (at: utown)
4. [EX][] food (amt: 5)
```

### `todo` - Adds a new Todo Task
Format: `todo <name>`

Example of usage:
- `todo borrow book`

```
Got it. I've added this task:
[TD][] borrow book
Now you have 5 tasks in the list.
```

### `deadline` - Adds a new Deadline Task
Format: `deadline <name> /by <yyyy-MM-dd HHmm>`

Example of usage:
- `deadline submit book /by 2023-12-22 2359`
```
Got it. I've added this task:
[DL][] submit book (by: 2023-12-22 2359)
Now you have 6 tasks in the list.
```

### `event` - Adds a new Event Task
Format: `event <name> /at <location>`

Example of usage:
- `event party /at home`
```
Got it. I've added this task:
[DL][] party (at: home)
Now you have 7 tasks in the list.
```

### `expense` - Adds a new Expense Task
Format: `expense <name> /amt <INTEGER`

Example of usage:
- `expense drink /amt 10`
```
Got it. I've added this task:
[EX][] drink (amt: 10)
Now you have 8 tasks in the list.
```

### `find` - Find Tasks
Find and displays the tasks with the given substring.

Format: `find <substring_to_search>`

Example of usage:
- `find book`
```
Here are the matching tasks in your list
[TD][] borrow book
[DL][] submit book (by: 2023-12-22 2359)
```
### `mark` - Mark Tasks
Mark the tasks at the specified indexes.

Format: `mark <index_1> <index_2> ...`

Example of usage:
- `mark 1 2`
```
Nice! I've marked the following task(s) as done
[TD][X] eat
[DL][X] exam (by: 2022-09-14 1200)
```
### `unmark` - Unmark Tasks
Unmark the tasks at the specified indexes.

Format: `unmark <index_1> <index_2> ...`

Example of usage:
- `unmark 1 2`
```
OK, I've marked the following task(s) as not done yet:
[TD][] eat
[DL][] exam (by: 2022-09-14 1200)
```

### `delete` - Delete Tasks
Deletes the tasks at the specified indexes.

Format: `delete <index_1> <index_2> ...`

Example of usage:
- `delete 1 2 3 4`
```
Noted. I've removed the following task(s):
[TD][] eat
[DL][] exam (by: 2022-09-14 1200)
[EV][] party (at: utown)
[EX][] food (amt: 5)
```

### `bye` -  Exits the program
Exits the program in 3 seconds.

Example of usage:
- `bye`

```
Bye. Hope to see you soo!
Platform will close in 3 seconds...
```
