# User Guide

Duke is a **desktop app for managing tasks**.<br>
It's useful for those who type fast.

## Quick Start

1. Ensure that you have Java `11` or above installed in your computer.
2. Download Duke.jar.
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Double-click the file to launch the app. The GUI similar to the one below should appear in a few seconds.<br>

![image info](./Ui.png)

## Features 

- Adding a todo task: `todo`
- Adding a deadline task: `deadline`
- Adding an event task: `event`
- Listing all tasks: `list`
- Marking a task as done: `mark`
- Marking a task as not done: `unmark`
- Finding a task: `find`
- Deleting a task: `delete`
- Exiting the program: `bye`
- Automatically saves data

### Managing Tasks

Manage your todo, deadline and event tasks by adding, deleting and marking them.

### Viewing Tasks

View all your tasks in a list or find using keywords.

### Automatically Saves Data

Data is automatically saved after every action.

## Usage

### Adding a todo task: `todo`

Adds a todo task to the list.

Example of usage:

`todo Buy AA batteries`

Expected outcome:

Added todo task to the list.

```
Got it. I've added this task:
[T][ ] Buy AA batteries
Now you have 2 tasks in the list
```

### Adding a deadline task: `deadline`

Adds a deadline to the list the date and time of the deadline can be of format YYYY-MM-DD TTTT.<br>
When a day is specified instead, the deadline will be set as 12am of the closest date with that day.<br>
However if the input is today, the deadline will be set as 12am exactly a week later.

Example of usage:

`deadline Homework 7 /by 2022-10-01 2359`

Expected outcome:

Added a deadline to the list with the date and time of the deadline.

```
Got it. I've added this task:
[D][ ] Homework 7 (by: 1 Oct 2022, 11:59:00 PM)
Now you have 3 tasks in the list.
```

### Adding an event task: `event`

Adds a event to the list the date and time of the event can be of format YYYY-MM-DD TTTT.<br>
When a day is specified instead, the event will be set as 12am of the closest date with that day.<br>
However if the input is today, the event will be set as 12am exactly a week later.

Example of usage:

`event Math Final Exam /at 2022-11-24 1400`

Expected outcome:

Added a event to this list with the date and time of the event.

```
Got it. I've added this task:
[E][ ] Math Final Exam (at: 24 Nov 2022, 2:00:00 PM)
Now you have 4 tasks in the list.
```

### Listing all tasks: `list`

Shows all the tasks on your list.

Example of usage: 

`list`

Expected outcome:

All the tasks on your list are shown.

```
Here are the tasks in your list:
1.[D][ ] test (by: 2 Dec 2019, 6:00:00 PM)
2.[T][ ] Buy AA batteries
3.[D][ ] Homework 7 (by: 1 Oct 2022, 11:59:00 PM)
4.[E][ ] Math Final Exam (at: 24 Nov 2022, 2:00:00 PM)
```

### Marking a task as done: `mark`

Marks a task as done.

Example of usage:

`mark 2`

Expected outcome:

The task is marked as done.

```
Nice! I've marked this task as done:
[T][X] Buy AA batteries
```

### Marking a task as not done: `unmark`

Marks a task as done.

Example of usage:

`unmark 2`

Expected outcome:

The task is marked as not done.

```
Nice! I've marked this task as not done yet:
[T][ ] Buy AA batteries
```

### Finding a task: `find`

Finds a task based on a keyword given.

Example of usage:

`find Exam`

Expected outcome:

Tasks that have words that match the keyword given are found.

```
Here are the matching tasks in your list:
1.[E][ ] Math Final Exam (at: 24 Nov 2022, 2:00:00 PM)
```

### Deleting a task: `delete`

Deletes a task.

Example of usage:

`delete 1`

Expected outcome:

The task is deleted from the list.

```
Noted. I've removed this task:
[D][ ] test (by: 2 Dec 2019, 6:00:00 PM)
Now you have 15 tasks in the list.
```

### Exiting the program: `bye`

Exits the program.

Example of usage:

`bye`

Expected outcome:

The application closes.

## FAQ

**Question:** How do I use this application on another computer with my data transferred over?<br>
**Answer:** Install the application and overwrite the empty file it creates with the Duke file on your original computer that contains the data.