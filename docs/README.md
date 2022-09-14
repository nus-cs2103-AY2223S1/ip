# User Guide
Hello, Master, I'm Duke, your new virtual servant. If you're feeling overwhelmed by your workload, simply dump them all off on me, and I will keep track of your tasks for you.

## Features 
- A friendly and unassuming personality
- The ability to remember your tasks indefinitely... until you decide that I shouldn't
- A flexible way to keep track of dates and times
- Coffee :coffee:

### Personality
Welcoming from the get-go and your servant through-and-through. Can I make you a cuppa?

### Memory
I'll remember your tasks, their due dates (if applicable), and your events for you. You can choose to mark them as done or simply delete them.

I'll save your task list when you say bye, and not before, so simply force close the program to undo everything you have done this session. I will be waiting with the list for you to visit me again.

### Date/Time Formatting
I understand the format YYYY-MM-DD. However, any other format of date or time will be remembered as well.

### Coffee
Would you like a cup of coffee, Master?

## Usage

### `Todo` - adds 'todo' item
A task without a due date will be added to your list.

Example of usage: 

`todo eat apple`

Expected outcome:

A task named 'eat apple' will be added to your list.

```
I have added [T][ ] eat apple to the list, Master
```

### `Deadline` - adds 'deadline' item
A task with a due date will be added to your list.

Example of usage: 

`deadline eat apple /by 7pm`

Expected outcome:

A task named 'eat apple' with the deadline '7pm' will be added to your list.

```
I have added [D][ ] eat apple (by: 7pm) to the list, Master
```

### `Event` - adds 'event' item
A task with date will be added to your list.

Example of usage: 

`event party /on 2022-07-10`

Expected outcome:

An event named 'party' with the date '7pm' will be added to your list.

```
I have added [E][ ] party (on: Jul 10 2022) to the list, Master
```

### `Mark` - marks an undone task as done
The specified task will be marked as done.

Example of usage:
`mark 1`

Expected outcome:
The first task on your list will be marked as done.

```
Well done, Master! I have marked [T][X] eat apple as done.
```

### `Unmark` - marks a done task as undone
The specified task will be marked undone.

Example of usage:
`unmark 1`

Expected outcome:
The first task on your list will be marked as undone.

```
Oh no :( I have marked [T][ ] eat apple as undone, Master.
```

### `Delete` - removes task
The specified task will be removed from your list.

Example of usage:
`delete 1`

Expected outcome: the first task on your list will be removed.

```
Very well. I have deleted [T][ ] eat apple from your list, master.
```

### `Edit` - edits task
The name or date/time of the specified task will be edited.

Example of usage:
`edit 1 sleep`

Expected outcome: the name of the first task on your list will be changed to `sleep`

```
I have edited task 1 to [D][ ] sleep (by: 7pm), Master.
```

Example of usage:
`edit 1 deadline 12am`

Expected outcome: the deadline of the first task on your list will be changed to `12am`

```
I have edited task 1 to [D][ ] sleep (by: 12am), Master.
```

Example of usage:
`edit 2 time 2022-07-11`

Expected outcome: the deadline of the first task on your list will be changed to July 11 2022

```
I have edited task 2 to [E][ ] party (on: Jul 11 2022), Master.
```

### `List` - displays your task list

Example of usage:
`list`

Expected outcome: your task list

```
Here is your to-do list, Master:
1. [D][ ] sleep (by: 12am)
2. [E][ ] party (on: Jul 11 2022)
```

### `Find` - finds task
The subset of your task list that matches the specified keyword(s) will be displayed.

Example of usage:
`find party`

Expected outcome: all tasks that contain the word 'party'

```
I have found the following task matching "party":
2. [E][ ] party (on: Jul 11 2022)
```

### `Coffee` - makes you a cup of coffee

Example of usage:
`coffee`

```
I have prepared a steaming cup of coffee for you, Master. Enjoy it while it's hot!
```

### `Bye` - saves your task list
Your task list be saved, and the old save will be overwritten.

Example of usage:
`bye`

```
Goodbye! Thank you for visiting
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
I have saved your list:
1. [D][ ] sleep (by: 12am)
2. [E][ ] party (on: Jul 11 2022)
```
