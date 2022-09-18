# User Guide

DukeManage is a desktop application for managing your daily tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, DukeManage can get your contact management tasks done faster than traditional GUI apps.

![alt text](https://dr-arrgghh.github.io/ip/Ui.png)

## Features

### `todo`  `t` Adding todos

Adding task that is to be done

Format: 
- `todo TASK_TODO` 
- `t TASK_TODO`

Example: 
- `todo read book` 
- `t read book`

Expected outcome:
```
Got it! I've added this task: 
[T][] buy fruits
You have 1 tasks in your list.
```
### `event` `e` Adding events

Adding upcoming events

Format: 
- `event EVENT_TO_ADD /at D/MM/YYYY HHmm`  
- `e EVENT_TO_ADD /at D/MM/YYYY HHmm`

Example: 
- `event exam /at 2/09/2022 1800`  
- `e exam /at 2/09/2022 1800`

Expected outcome:
```
Got it! I've added this task: 
[E][] go gym (at: Monday, 12 December 2022)
You have 1 tasks in your list.
```
### `deadline` `dl` Adding deadlines

Adding upcoming deadlines

Format: 
- `deadline DEADLINE_TO_ADD /by D/MM/YYYY HHmm`
- `d DEADLINE_TO_ADD /by D/MM/YYYY HHmm`

Example:
- `deadline work /by 2/09/2022 1800`
- `d work /by 2/09/2022 1800`

Expected outcome:
```
Got it! I've added this task: 
[D][] work (by: Monday, 12 December 2022)
You have 1 tasks in your list.
```

### `mark` `m` Mark tasks as done

Mark tasks in the list as done

Format:
- `mark NUMBER`
- `m NUMBER`

Example:
- `mark 2`
- `m 2`

Expected outcome:
```
I've marked this task as done:
[T][X] go gym
```

### `unmark` `um` Unmarking tasks as done

Unmark task that was initially mark as done

Format:
- `unmark NUMBER`
- `um NUMBER`

Example:
- `unmark 2`
- `um 2`

Expected outcome:
```
Ok, i've unmarked this task as done:
[T][ ] go gym
```

### `delete` `del` Deleting Tasks

Deleting tasks from the list

Format:
- `delete NUMBER`
- `del NUMBER`

Example:
- `delete 2`
- `del 2`

Expected outcome:
```
Got it, i've deleted this task:
[T][ ] go gym
```

### `list` `l` List of tasks

Show the current list of tasks

Format:
- `list`
- `l`

Example:
- `list`
- `l`

Expected outcome:
```
Here are the tasks in your list:
1. [D][] 2103 project (by: 28 Sep 2022 23:59)
2. [T][] go gym 
3. [E][] nus open house (at: 20 Dec 2022 22:00)
```

### `find` `f` Finding Tasks

Find existing tasks in the list

Format:
- `find KEYWORD`
- `f KEYWORD`

Example:
- `find gym`
- `f gym`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][] go gym
```

