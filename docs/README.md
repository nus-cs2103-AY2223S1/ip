# User Guide

## Features 

### Add different types of tasks

- Add tasks that are not time sensitive and can be completed at any time.
- Add tasks that have deadlines such as homework or assignments.
- Add events that are happening such as someone's birthday party.

### Keep track of your tasks 

- List out all your current tasks or events.
- Mark your tasks as finished or unfinished.
- Delete tasks that have been finished.

### Find specific tasks and undo commands

- Find certain tasks.
- Undo the latest command.

## Types of commands

### `todo`

Adding tasks that are not time sensitive:`todo {name of the task}`

Example of usage: 

`todo learn how to play the guitar`

Expected outcome:

Adds a todo task with the task name being "learn how to play the guitar".

Also displays how many tasks are currently inside the task list.

```
I gotchu fam. Your task has been added:
[T][ ] learn how to play the guitar
You have 1 task(s). Stop procrastinating bro.
```

### `deadline`

Adding tasks that have a deadline:`deadline {name of the task} /by {date of the deadline} {optional: time of the deadline}`

The date of the deadline should be written in the form of DD/MM/YYYY.

Adding the time of the deadline is optional. Times should be written in the 24-hour format (1000)

Example of usage: 

`deadline math assignment /by 23/3/2022`


`deadline math assignment /by 23/3/2022 1000`

Expected outcome:

Adds a deadline task with the task name being "math assignment" and deadline being 23/3/2022.

If a time was inputted, the time of the deadline will also be displayed, specifically 10:00.

Also displays how many tasks are currently inside the task list.

```
I gotchu fam. Your task has been added:
[D][ ] math assignment (by: 23 Mar 2022)
You have 2 task(s). Stop procrastinating bro.
```

```
I gotchu fam. Your task has been added:
[D][ ] math assignment (by: 23 Mar 2022 10:00)
You have 3 task(s). Stop procrastinating bro.
```

### `event`
