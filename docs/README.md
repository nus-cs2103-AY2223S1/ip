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

