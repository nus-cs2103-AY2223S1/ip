# Duke User Guide

The latest application to keep track of all the things that you have to get done!
## Features 

### Feature - ToDo

Adds a ToDo task.

`todo DESCRIPTION`

Example of usage: 

`todo Chores`

Expected outcome:

```
todo Chores
Got it. I've added this task:
[T][ ] Chores
Now you have 1 tasks in the list.
```

### Feature - Deadline

Adds a Deadline task.

`deadline DESCRIPTION /by YYYY-MM-DD HH:MM`

Example of usage: 

`deadline Assignment Submission /by 2022-08-17 23:59`

Expected outcome:

```
deadline Assignment Submission /by 2022-08-17 23:59
Got it. I've added this task:
[D][ ] Assignment Submission (by: Aug 17 2022 11:59PM)
Now you have 2 tasks in the list.
```

### Feature - Event

Adds an Event task.

`event DESCRIPTION /at YYYY-MM-DD HH:MM`

Example of usage: 

`event Dinner /at 2022-08-19 18:00`

Expected outcome:

```
event Dinner /at 2022-08-19 18:00
Got it. I've added this task:
[E][ ] Dinner (by: Aug 19 2022 06:00PM)
Now you have 3 tasks in the list.
```

### Feature - List

Lists all recorded tasks.

`list`

Example of usage: 

`list`

Expected outcome:

```
list
Here are the tasks in your list:
1.[T][ ] Chores
2.[D][ ] Assignment Submission (by: Aug 17 2022 11:59PM)
3.[E][ ] Dinner (by: Aug 19 2022 06:00PM)
```

### Feature - Mark

Marks a task as done.

`mark TASKNUMBER`

Example of usage: 

`mark 1`

Expected outcome:

```
mark 1
Nice! I've marked this task as done:
[T][x] Chores
```

### Feature - Unmark

Marks a task as not done.

`unmark TASKNUMBER`

Example of usage: 

`unmark 1`

Expected outcome:

```
unmark 1
Nice! I've marked this task as not done yet:
[T][ ] Chores
```

### Feature - Delete

Deletes a task.

`delete TASKNUMBER`

Example of usage: 

`delete 1`

Expected outcome:

```
delete 1
Nice! I've removed this task:
[T][ ] Chores
Now you have 2 tasks in the list.
```

### Feature - Find

Finds tasks containing the given keywords.

`find KEYWORD`

Example of usage: 

`find Dinner`

Expected outcome:

```
find Dinner
Here are the matching tasks in your list:
1.[E][ ] Dinner (by: Aug 19 2022 06:00PM)
```

### Feature - Tag

Adds a tag to a task .

`tag TASKNUMBER DESCRIPTION`

Example of usage: 

`tag 1 MIPS`

Expected outcome:

```
tag 1 MIPS
Nice! I've tagged this task:
1.[D][ ] Assignment Submission (by: Aug 17 2022 11:59PM) #MIPS
```




