# User Guide for Sky

Sky is an interactive chatbot for managing tasks, and can be used to track tasks such as deadlines, events, and todos.  
It is backed up by a Graphical User Interface (GUI) and is optimized for use on it.   
If you have trouble remembering things to do, Sky can free your mind from doing so by acting as a one-stop solution for
tracking tasks.

![Screenshot](./Ui.png)

## Classifcation of tasks

Sky supports 3 classifcations of tasks.  
`ToDo`: Tasks without any date/time attached to it <span style="color:grey">e.g., *visit new theme park*</span>  
`Deadline`: Tasks that need to be done before a specific date/time <span style="color:grey">e.g., *submit report by
11/10/2019 5pm*</span>  
`Event`: Tasks that start at a specific time and ends at a specific time <span style="color:grey">e.g., *team project
meeting on 2/10/2019 2-4pm*</span>  

## Features 

### Add a task

Adds a task for Sky to track and do operations on. The task to be added should be one of either `ToDo`, `Deadline`,
`Event`.  
See more on usage: [deadline](#deadline---adds-a-deadline), [event](#event---adds-an-event),
[todo](#todo---adds-a-todo)

### List the tasks

Lists all the tasks currently tracked by Sky.  
See more on usage: [list](#list---lists-all-the-tasks)

### Mark and unmark tasks as done

Marks completed tasks as done or unmarks done tasks as undone. All tasks are unmarked by default.  
See more on usage: [mark](#mark---marks-a-task-as-done), [unmark](#unmark---unmarks-a-task-as-undone)

### Delete a task

Deletes a task for Sky to untrack.  
See more on usage: [delete](#delete---deletes-a-task)

### Save to hard disk

Sky automatically saves tracked tasks on the hard disk, allowing for future-use continuation.

### Find tasks by keyword

Finds and lists all tasks that contain a specified keyword.  
See more on usage: [find](#find---locates-tasks-with-keyword)

### Undo commands

Undoes task-list-altering commands operated on in the current application window.  
See more on usage: [undo](#undo---undoes-list-altering-commands)

### Exit the program

Exits the program by closing the application window.
See more on usage: [bye](#bye---exits-the-program)

## Command Summary

- [todo](#todo---adds-a-todo): Adds a `ToDo`  
- [deadline](#deadline---adds-a-deadline): Adds a `Deadline`  
- [event](#event---adds-an-event): Adds an `Event`  
- [list](#list---lists-all-the-tasks): Lists all the tasks  
- [mark](#mark---marks-a-task-as-done): Marks a task as done  
- [unmark](#unmark---unmarks-a-task-as-undone): Unmarks a task as undone  
- [delete](#delete---deletes-a-task): Deletes a task  
- [find](#find---locates-tasks-with-keyword): Locates tasks with keyword  
- [undo](#undo---undoes-list-altering-commands): Undoes list-altering commands  
- [bye](#bye---exits-the-program): Exits the program

## Usage

### `todo` - Adds a ToDo

Adds a `ToDo` with a description provided by the user.

Format: `todo DESCRIPTION`
- `DESCRIPTION` is the task description.

Example of usage: 

`todo visit new theme park`

Expected outcome: A `ToDo` task with the specified task description is tracked by Sky.

Description of the outcome.

```
Got it. I've added this task:
  [T][] visit new theme park
Now you have 1 task in the list.
```

### `deadline` - Adds a deadline

Adds a `Deadline` with a description, date, and possibly time provided by the user.

Format: `deadline DESCRIPTION /by YYYY-MM-DD HHMM`
- `DESCRIPTION` is the task description.
- `YYYY-MM-DD` is the format of the date. The number of digits should match accordingly.
- `HHMM` is the format of the time in 24 hours standard.

Examples of usage:

`deadline submit math assignment 3 /by 2022-09-15`  

`deadline submit math assignment 3 /by 2022-09-15 1800`

Expected outcome: A `Deadline` task with the specified task description, date, and possibly time is tracked by Sky.

Description of the outcomes.

```
Got it. I've added this task:
  [D][] submit math assignment 3 (by: Sep 15 2022)
Now you have 2 tasks in the list.
```

```
Got it. I've added this task:
  [D][] submit math assignment 3 (by: Sep 15 2022, 6:00PM)
Now you have 2 tasks in the list.
```

### `event` - Adds an event

Adds a `Event` with a description, date, and time period provided by the user.

Format: `event DESCRIPTION /at YYYY-MM-DD HHMM-HHMM`
- `DESCRIPTION` is the task description.
- `YYYY-MM-DD` is the format of the date. The number of digits should match accordingly.
- `HHMM-HHMM` is the format of the time period in 24 hours standard.

Example of usage:

`event play with my dog Jon /at 2022-09-18 1800-1930`

Expected outcome: A `Event` task with the specified task description, date, and time period is tracked by Sky.

Description of the outcome.

```
Got it. I've added this task:
  [E][] play with my dog Jon (at: Sep 18 2022, 6:00PM-7:30PM)
Now you have 3 tasks in the list.
```

### `list` - Lists all the tasks

Lists all tasks currently tracked by Sky, showing the task type, task completion status, and task description for each
task.

Example of usage:

`list`

Expected outcome: A list of currently tracked tasks is outputted.

Description of the outcome.

```
1.[T][] visit new theme park
2.[D][] submit math assignment 3 (by: Sep 15 2022, 6:00PM)
3.[E][] play with my dog Jon (at: Sep 18 2022, 6:00PM-7:30PM)
```

### `mark` - Marks a task as done

Marks a task corrosponding to an index as done.

Format: `mark INDEX`
- `INDEX` is determined by the index number of the task in the list.

Example of usage:

`mark 3`

Expected outcome: The third task in the list is marked as done.

Description of the outcome.

```
Amazing stuff. I've marked this task as done:
  [E][X] play with my dog Jon (at: Sep 18 2022, 6:00PM-7:30PM)
```

### `unmark` - Unmarks a task as undone

Unmarks a task corrosponding to an index as undone.

Format: `unmark INDEX`
- `INDEX` is determined by the index number of the task in the list.

Example of usage:

`unmark 3`

Expected outcome: The third task in the list is marked as undone.

Description of the outcome.

```
Well, that's disappointing. I've marked this task as undone:
  [E][] play basketball with John (at: Sep 19 2022, 6:00PM-9:00PM)
```

### `delete` - Deletes a task

Deletes a task from the list and untracks it.

Format: `delete INDEX`
- `INDEX` is determined by the index number of the task in the list.

Example of usage:

`delete 2`

Expected outcome: The second task in the list is deleted and untracked by Sky.

Description of the outcome.

```
Splendid. I've removed this task:
  [D][] submit math assignment 3 (by: Sep 15 2022, 6:00PM)
Now you have 2 tasks in the list.
```

### `find` - Locates tasks with keyword

Finds and lists all tasks that contain a specified keyword.

Format: `find KEYWORD`
- `KEYWORD` can consists of multiple words separated by spaces.

Example of usage:

`find Jon`

Expected outcome: All tasks that contain "Jon" in its description is outputted.

Description of the outcome.

```
1.[E][] play with my dog Jon (at: Sep 18 2022, 6:00PM-7:30PM)
```

### `undo` - Undoes list-altering commands

Undoes task-list-altering commands operated on in the current application window by reverting state to a past state.

Format: `undo NUMBER`
- `NUMBER` is the number of steps you want to revert.

Example of usage:

`undo 2`

Expected outcome: Sky reverts its tracking state by two steps, essentially undoing the two most recent list-altering
-commands.

Description of the outcome.

```
Successfully reverted state back by 2 steps.
```

### `bye` - Exits the program

Closes the application window.

Example of usage:

`bye`

Expected outcome: A good-bye message is outputted and the application window closes.

Description of the outcome.

```
Bye. May all your endeavours fly high!
```
