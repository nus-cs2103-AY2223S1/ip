# User Guide
Dude is a chatting bot that allows you to keep track of your tasks.

## Features 

| feature  | description                                  |
|----------|----------------------------------------------|
| todo     | adds task with no specific date              
| deadline | adds task due by specific date               
| event    | adds task to be done on specific date        
| list     | lists all your current task                  |
|date| get deadlines/events from a specific date
| delete   | delete your task                             |
| mark     | yay done with my task!! Mark it as done!     
| unmark   | oh noooooo marked by mistake, unmark it now! |
|find| only remember part of the description? No worries just find it!
|undo | did something wrongly? Don't worry just undo it!
|bye| oh no Dude will miss you!

## Usage

### `todo` - adds task with no specific date

Adds a task to Dude 

Example of usage: 

`todo read Detective Conan latest manga`

Expected outcome:

Todo added!
```
Dude:
Got it. I've added the task:
[T][ ] read Detective Conan latest manga
Now you have 1 tasks in the list.
```
---
### `deadline` - adds deadline 

Adds a deadline to Dude

Example of usage:

`deadline write poem /by 2022-08-07`

Expected outcome:

Deadline added!
```
Dude:
Got it. I've added the task:
[D][ ] write poem (by: Aug 7 2022)
Now you have 2 tasks in the list.
```
---
### `event` - adds event 

Adds an event to Dude

Example of usage:

`event party /at 2022-08-07`

Expected outcome:

Event added!
```
Dude:
Got it. I've added the task:
[E][ ] party (by: Aug 7 2022)
Now you have 3 tasks in the list.
```
---

### `list` - shows the tasks

Shows the tasks

Example of usage:

`list`

Expected outcome:

Shows list of tasks
```
Dude:
Here are the tasks in your list: 
1. [T][ ] read Detective Conan latest manga
2. [D][ ] write poem (by: Aug 7 2022)
3. [E][ ] party (by: Aug 7 2022)
```
---

### `date` - shows the tasks on that date

Shows the tasks on that date

Example of usage:

`date 2022-08-07`

Expected outcome:

Shows list of tasks
```
Dude:
Here are the tasks in your list: 
1. [D][ ] write poem (by: Aug 7 2022)
2. [E][ ] party (by: Aug 7 2022)
```
---


### `delete` - deletes a task 
Shows the tasks on that date

Example of usage:

`delete 3`

Expected outcome:

Shows list of tasks
```
Dude:
Noted. I've removed the task:
[E][ ] party (by: Aug 7 2022)
Now you have 2 tasks in the list.
```
---

### `mark` - marks a task
Marks a task

Example of usage:

`mark 2`

Expected outcome:

Marks task
```
Dude:
Nice! I've marked this task as done:
[D][X] write poem (by: Aug 7 2022)
```
---

### `unmark` - unmarks a task
Unmarks a task

Example of usage:

`unmark 2`

Expected outcome:
Unmarks task
```
Dude:
OK, I've marked this task as not done yet:
[D][] write poem (by: Aug 7 2022)
```
---
### `find` - finds a task
Finds a task

Example of usage:

`find Conan`

Expected outcome:
Finds task that match the keyword
```
Dude:
Here are the matching tasks in your list:
1. [T][ ] read Detective Conan latest manga
```
---
### `undo` - reverse action
Go back to before you have done that action

Example of usage:

`undo`

Expected outcome:
Reverses what you have done
```
Dude:
Undo successful!
```