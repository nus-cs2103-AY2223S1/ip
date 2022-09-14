# User Guide

DukeTasks is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

![alt text](https://jsincorporated.github.io/ip/Ui.png)

# Quick Start

1) Ensure you have Java 11 or above installed in your Computer

2) Download the latest `duke.jar` from [here](https://github.com/jsincorporated/ip/releases).

3) Copy file to the folder you want to use as the home folder for your DukeTasks.

4) Double-click the file to start the app.

5) Begin you DukeTasks adventure!

## Features 

### `todo` Adding todos

Adds a todo task to to-do list.

Format: `todo THING_TO_ADD`

Example usage: `todo buy fruits`

Expected outcome:
```
Got it! I've added this task: 
[T][] buy fruits
You have 1 tasks in your list.
```

### `deadline` Adding deadlines

Adds a deadline task to to-do list.

Format: `deadline THING_TO_ADD /by DD/MM/YYYY HHmm`

Example usage: `deadline buy fruits /by 12/12/2022 1200`

Expected outcome:
```
Got it! I've added this task: 
[D][] buy fruits (by: Monday, 12 December 2022)
You have 1 tasks in your list.
```

### `event` Adding events

Adds a event task to to-do list.

Format: `event THING_TO_ADD /at DD/MM/YYYY HHmm`

Example usage: `event buy fruits /by 12/12/2022 1200`

Expected outcome:
```
Got it! I've added this task: 
[D][] buy fruits (at: Monday, 12 December 2022)
You have 1 tasks in your list.
```

### `mark` Marking tasks as done

Marks specified task as **done**.

Format: `mark NUMBER`

Example usage: `mark 1`

Expected outcome:
```
I've marked this task as done:
[T][X] buy fruits
```

### `unmark` Unmarking tasks

Marks specified task as **undone**.

Format: `unmark NUMBER`

Example usage: `unmark 1`

Expected outcome:
```
Ok, i've unmarked this task as done:
[T][ ] buy fruits
```

### `delete` Deleting tasks

Deletes the specified task

Format: `delete NUMBER`

Example usage: `delete 1`

Expected outcome:
```
Got it, i've deleted this task:
[T][ ] buy fruits
```

### `list` Listing tasks

Lists down all the tasks you have in your list.

Format: `list`

Example usage: `list`

Expected outcome:
```
Here are the tasks in your list:
1. [D][] 2102 project (by: 28 Sep 2022 23:59)
2. [T][] buy fruits 
3. [E][] party (at: 20 Dec 2022 22:00)
```

### `find` Finding tasks by keyword

Searches for tasks in to-do list that matches the keyword.

Format: `find KEYWORD`

Example usage: `find fruits`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][] buy fruits
```

### `help` Get help

Get help within DukeTasks.

Format: `help`

Example usage: `help`

Expected outcome:
```
It looks like you're having some trouble!
Here are some example usages:
todo Laundry
deadline Math Assignment /by 20/20/2020 1200
event Concert /at 19/20/2020 1200
list
mark 1
unmark 1
find Laundry
```


