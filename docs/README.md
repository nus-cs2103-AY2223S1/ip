# User Guide - Echo

Echo is a CLI-based Chatbot that is able to store and keep track of tasks inputted by the user.

- [Quick Start](#quick-start)
- [Features](#features)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/t1mzzz/ip/releases).
3. Copy the file to the folder you want to use as the home folder.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.\
![Example Echo UI](./Ui.png)
5. Start using Echo by typing commands in the command box.
6. Refer to the [Features](#features) below for details of each command.

## Features 

### Viewing Tasks: `list`

Shows a list of all tasks.

Format: `list`

Example of usage:
```
list
```

Example outcomes:
```
OOPS!!! You have no tasks in the list.
```
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] Submit Project (by: Sep 16 2022) 
3.[E][ ] Play Badminton (at: Sep 1 2022) 
```

### Adding a todo: `todo`

Adds a todo task with a description.

Format: `todo DESCRIPTION`

Example of usage:
```
todo read book
```

Example outcome:
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

### Adding a deadline: `deadline`

Adds a deadline task with a description and a deadline date.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Example of usage:
```
deadline Submit Project /by 2022-09-16
```

Example outcome:
```
Got it. I've added this task:
  [D][ ] Submit Project (by: Sep 16 2022)
Now you have 2 tasks in the list.
```

### Adding an event: `event`

Adds an event task with a description and an event date.

Format: `event DESCRIPTION /at YYYY-MM-DD`

Example of usage:
```
event PLay Badminton /at 2022-09-01
```

Example outcome:
```
Got it. I've added this task:
  [E][ ] Play Badminton (at: Sep 1 2022)
Now you have 3 tasks in the list.
```

### Marking a task as done: `mark`

Marks the specified task as completed.

Format: `mark INDEX`

Example of usage:
```
mark 1
```

Example outcome:
```
OK, I've marked this task as done:
  [T][X] read book
```

### Marking a task as not done: `unmark`

Marks the specified task as not completed.

Format: `unmark INDEX`

Example of usage:
```
unmark 1
```

Example outcome:
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

### Sorting the list of tasks: `sort`

Sorts the task chronologically or reverse chronologically, based on task's dates. 
> `todo`s are set as earliest in terms of dates.

Format: `sort Chrono` or `sort rChrono`

Example of usage:
```
sort Chrono // sorts the tasks in chronological order (earlier dates to latest)
list
```

Example outcome:
```
Tasks sorted chronologically
```

```
Here are the tasks in your list:
1.[T][ ] read book
2.[E][ ] Play Badminton (at: Sep 1 2022)
3.[D][ ] Submit Project (by: Sep 16 2022)
```

### Searching for tasks: `find`

Finds tasks whose description contain any of the given keywords.

Format: `find KEYWORD`

Example of usage:
```
find book
```

Example outcome:
```
Here are the matching tasks in your list:
1.[T][ ] read book
```

### Deleting a task: `delete`

Deletes the specified task.

Format: `delete INDEX`

Example of usage:
```
delete 1
```

Example outcome:
```
Noted. I've removed this task:
  [T][ ] read book
Now you have 2 tasks in the list.
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

Example of usage:
```
bye
```

Example outcome:
```
Bye. Hope to see you again soon!
```

### Saving the data

Echo data are saved in the hard disk automatically after exiting the program (executing `bye`). There is no need to save manually.

## Command Summary

| Action   | Format                                | Examples                               |
|----------|---------------------------------------|----------------------------------------|
| List     | `list`                                | `list`                                 |
| Todo     | `todo DESCRIPTION`                    | `todo read book`                       |
| Deadline | `deadline DESCRIPTION /by YYYY-MM-DD` | `deadline return book /by 2022-09-09`  |
| Event    | `event DESCRIPTION /at YYYY-MM-DD`    | `event CS2100 Midterms /at 2022-10-08` |                     |
| Mark     | `mark INDEX`                          | `mark 1`                               |
| Unmark   | `unmark INDEX`                        | `unmark 1`                             |
| Sort     | `sort [Chrono/rChrono]`               | `sort Chrono`                          |
| Find     | `find KEYWORD`                        | `find book`                            |
| Delete   | `delete INDEX`                        | `delete 1`                             |
| Exit     | `bye`                                 | `unmark 1`                             |
