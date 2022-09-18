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

```java
I gotchu fam. Your task has been added:
[T][ ] learn how to play the guitar
You have 1 task(s). Stop procrastinating bro.
```

### `deadline`

Adding tasks that have a deadline:`deadline {name of the task} /by {deadline}`

Example of usage: 

`deadline math assignment /by 23/3/2022`

Expected outcome:

Adds a deadline task with the task name being "math assignment" and deadline being 23/3/2022.

Also displays how many tasks are currently inside the task list.

```java
I gotchu fam. Your task has been added:
[D][ ] math assignment (by: 23 Mar 2022)
You have 2 task(s). Stop procrastinating bro.
```
