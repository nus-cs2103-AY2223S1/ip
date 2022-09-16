# User Guide

Welcome to this user guide for the chatbot Duke! 
You may find the list of available commands below.

## Features 

Here you may finnd a list of features.

### Adding a Deadline task

Adds a task that has a deadline.

Usage: `deadline TASK_DESCRIPTION /by DATE TIME`

`DATE` is in the form of day followed by month followed by year,
separated by either a `.`, `-` or `/`.

`TIME` (optional) is in the 24-hour clock format.

Example 1: `deadline submit homework /by 30-9-2022 2359`

Outcome: 
```
I've added a new deadline task:
[D][0] submit homework | 30 Sep 2022 2359
```

Example 2: `deadline sleep /by 1-1-2022`

Outcome:
```
I've added a new deadline task:
[D][0] sleep | 01 Jan 2022
```

### Adding an Event task

Adds a task that occurs at a certain time

Usage: `event TASK_DESCRIPTION /at DATE TIME`

`DATE` is in the form of day followed by month followed by year,
separated by either a `.`, `-` or `/`.

`TIME`(optional) is in the 24-hour clock format.

Example 1: `event party /at 30-9-2022 2000`

Outcome:
```
I've added a new event task:
[E][0] party | 30 Sep 2022 2000
```

Example 2: `event give speech /at 1-1-2022`

Outcome:
```
I've added a new event task:
[E][0] give speech | 01 Jan 2022
```

### Adding a Todo Task

Usage: `todo TASK_DESCRIPTION`

Example: `todo read book`

Outcome:
```
I've added a new todo task:
[T][0] read book
```

### Listing all tasks

Usage: `list`

Example: `list`

Outcome:
```
[T][0] read book
[E][1] celebrate new year | 01 Jan 2022
```

### Deleting a task

Usage: `delete TASK_NUMBER`

Example: `delete 1`

Outcome:
```
I've deleted this task:
[T][1] watch movie
```

### Finding a task

Usage: `find KEYWORD`

Only tasks that contain `KEYWORD` in the description will be shown.

Example: `find book`

Outcome:
```
[T][1] read book
[D][0] return books | 31 Dec 2022
```

### Marking a task as done

Usage: `mark TASK_NUMBER`

Example: `mark 2`

Outcome: 
```
I've marked this task as complete:
[T][1] write book
```

### Marking a task as undone

Usage: `unmark TASK_NUMBER`

Example: `unmark 3`

Outcome:
```
I've marked this task as incomplete:
[T][0] write reflections
```

### Setting the filepath for local storage

Usage: `file FILE_PATH`

`FILE_PATH` is the file location of the local storage file.

Example: `file data/data.txt`

### Exiting Duke

Usage: `bye`

Example: `bye`

Outcome:
```
Bye!
```

##  End

Thank you for reading this user guide!