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

Adding tasks that are not time sensitive: `todo {name of the task}`

Example of usage: 

`todo learn how to play the guitar`

Expected outcome:

Adds a todo task with the task name being "learn how to play the guitar" and displays how many tasks are currently inside the task list.

```
I gotchu fam. Your task has been added:
[T][ ] learn how to play the guitar
You have 1 task(s). Stop procrastinating bro.
```

### `deadline`

Adding tasks that have a deadline: `deadline {name of the task} /by {date of the deadline} {optional: time of the deadline}`

- The date of the deadline should be written in the form of DD/MM/YYYY.

- Adding the time of the deadline is optional. Times should be written in the 24-hour format (1000).

Example of usage: 

`deadline math assignment /by 23/3/2022`

`deadline math assignment /by 23/3/2022 1000`

Expected outcome:

Adds a deadline task with the task name being "math assignment" and deadline being 23/3/2022. Also displays how many tasks are currently inside the task list.

- If a time was inputted, the time of the deadline will also be displayed, specifically 10:00.

```
I gotchu fam. Your task has been added:
[D][ ] math assignment (by: 23 Mar 2022)
You have 2 task(s). Stop procrastinating bro.
```

```
I gotchu fam. Your task has been added:
[D][ ] math assignment (by: 23 Mar 2022 10:00)
You have 2 task(s). Stop procrastinating bro.
```

### `event`

Adding events that occur on a certain date and during a time frame: `event {name of the event} /at {date of the event} {optional: start time of the event - end time of the event}`

- The date of the event should be written in the form of DD/MM/YYYY.

- Adding the time frame of the event is optional. Times should be written in the 24-hour format (1000).

Example of usage: 

`event birthday celebration /at 23/3/2022`

`event birthday celebration /at 23/3/2022 1000-1200`

Expected outcome:

Adds an event with the event name being "birthday celebration" and event date being 23/3/2022. Also displays how many tasks are currently inside the task list.

- If a time frame was inputted, the start and end time of the event will also be displayed, specifically 10:00-12:00.

```
I gotchu fam. Your task has been added:
[E][ ] birthday celebration (at: 23 Mar 2022)
You have 3 task(s). Stop procrastinating bro.
```

```
I gotchu fam. Your task has been added:
[E][ ] birthday celebration (at: 23 Mar 2022 10:00-12:00)
You have 3 task(s). Stop procrastinating bro.
```

### `list`

Listing out all the events currently in the task list: `list`

Expected outcome:

Lists out all the events currently in the task list.

- The first letter represents what type of task it is (T is a todo task, D is a task with a deadline and E is an event)
- The second box represents whether the task is finished (`[ ]` for unfinished and `[X]` for finished)

```
Here are your tasks. You better start now:
1.[T][X] learn how to play the guitar
2.[D][ ] math assignment (by: 23 Mar 2022 10:00)
3.[E][ ] birthday celebration (at: 23 Mar 2022 10:00-12:00)
```

### `mark`

Marks a task as finished: `mark {number of the task}`

Example of usage: 

`mark 1`

Expected outcome:

Marks and displays the first task as finished.

```
Nice one lah, finally doing your work.
[T][X] learn how to play the guitar
```

### `unmark`

Marks a task as unfinished: `unmark {number of the task}`

Example of usage: 

`mark 1`

Expected outcome:

Marks and displays the first task as unfinished.

```
What happened bro.
[T][ ] learn how to play the guitar
```

### `delete`

Deletes a task from the task list: `delete {number of the task}`

Example of usage: 

`delete 1`

Expected outcome:

Deletes the first task from the task list and displays the remaining number of tasks in the task list.

```
I hope you're not deleting this just to avoid work:
[T][ ] learn how to play the guitar
You have 2 task(s). Stop procrastinating bro.
```

### `find`

### `undo`

### `bye`
