# User Guide
Margi Bot is a desktop app for managing your tasks, optimized for use via a 
Command Line Interface while still having the benefits of a Graphical User 
Interface. 


## Quick Start
1. Ensure you have Java 11 or above installed in your Computer
2. Download the latest`duke.jar` from [here](https://github.com/Dilysss/ip/releases/tag/A-Release).
3. Copy file to the folder you want to use as the home folder for your Margi Bot.
4. Go into the folder containing the jar file.
5. Run `java -jar duke.jar` to launch the application.
6. Begin your Margi adventure!

## Features 

### `todo` - Adding Todos

Adds a todo task to to-do list.

Format: `todo TODO`

Example of usage:

`todo buy fruits`

Expected outcome:
```
Okay! I've added this task: 
[T][] buy fruits
You have 1 tasks in your list.
```
<br>

### `deadline` - Adding Deadlines

Adds deadlines that have due dates.

Format: `deadline DEADLINE /by YYYY-MM-DDTHH:MM`

Example of usage:
`deadline tutorial 1 /by 2022-10-02T23:59`

Expected outcome: 
```
Okay! I've added this task: 
[D][] tutorial 1 (by: 02 Oct 2022 23:59)
You have 1 tasks in your list.
```
<br>

### `event` - Adding Events

Adds events that have a date and time.

Format: `event EVENT /at YYYY-MM-DDTHH:MM`

Example of usage:
`event party /at 2022-12-20T22:00`

Expected outcome:
```
Okay! I've added this task: 
[E][] party (at: 20 Dec 2022 22:00)
You have 1 tasks in your list.
```
<br>

### `mark` - Marking Tasks as Done

Marks specified task as done.

Format: `mark INDEX`

Example of usage: \
To mark task 1 as done: 
`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
[X] buy fruits
```
<br>

### `unmark` - Unmarking tasks

Unmarks specified task as not done.

Format: `unmark INDEX`

Example of usage: \
To mark task 1 as not done:
`unmark 1`

Expected outcome:
```
Hmm...I've marked this task as undone:
[] buy fruits
```
<br>

### `delete` - Deleting tasks

Deletes a completed task from to-do list.

Format: `delete INDEX`

Example of usage: \
To delete task 1 from to-do list:
`delete 1`

Expected outcome:
```
Nice! I've deleted this task: 
[T][] buy fruits
You have 3 tasks in your list.
```
<br>

### `list` - Listing Tasks

Lists down all the tasks you have in your list.

Format: `list`

Example of usage:

`list`

Expected outcome:
```
Here are the tasks that you have:
1. [D][] 2102 project (by: 28 Sep 2022 23:59)
2. [T][] buy fruits 
3. [E][] party (at: 20 Dec 2022 22:00)
```
<br>

### `find` - Finding Tasks by Keyword

Searches for tasks in to-do list that matches the keyword.

Format: `find KEYWORD`

Example of usage:

`find buy`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][] buy fruits
```
<br>

### `count todo` - Counting Number of Todos

Counts the number of todos in the to-do list.

Format: `count todo`

Example of usage:

`count todo`

Expected outcome:
```
You have 1 todos. XD
```
<br>

### `count deadline` - Counting Number of Deadlines

Counts the number of deadlines in the to-do list.

Format: `count deadline`

Example of usage:

`count deadline`

Expected outcome:
```
You have 2 deadlines. :)
```
<br>

### `count event` - Counting Number of Todos

Counts the number of events in the to-do list.

Format: `count event`

Example of usage:

`count event`

Expected outcome:
```
You have 2 events. ;)
```

## Command Summary
| Action          | Format, Example                          |
|-----------------|------------------------------------------|
| Add Todo        | `todo TODO`                              |
| Add Deadline    | `deadline DEADLINE /by YYYY-MM-DDTHH:MM` |
| Add Event       | `event EVENT /at YYYY-MM-DDTHH:MM`       |
| Mark            | `mark INDEX`                             |
| Unmark          | `unmark INDEX`                           |
| Delete          | `delete INDEX`                           |
| List            | `list`                                   |
| Find            | `find KEYWORD`                           |
| Count Todos     | `count todo`                             |
| Count Deadlines | `count deadline`                         |
| Count Events    | `count event`                            |

